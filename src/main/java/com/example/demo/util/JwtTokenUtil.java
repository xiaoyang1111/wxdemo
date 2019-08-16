package com.example.demo.util;

import com.alibaba.fastjson.JSONObject;
import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.DefaultClock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

//@Component
public class JwtTokenUtil implements Serializable {

    public static Integer ILLEGAL_STATUS = 0;//非法
    public static Integer EXP_STATUS = 1;//超时
    public static Integer EFFECT_STATUS = 2;//有效

    private static Logger log = LoggerFactory.getLogger(JwtTokenUtil.class);

    private static final long serialVersionUID = -3301605591108950415L;

    //@SuppressFBWarnings(value = "SE_BAD_FIELD", justification = "It's okay here")
    private Clock clock = DefaultClock.INSTANCE;

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public String getUserAccountIdFromToken(String token) {
        try {
            final Claims claims = getAllClaimsFromToken(token);
            return claims.get("id") + "";
        } catch (Exception e) {
            //e.printStackTrace();
            log.info("######.错误提示：JwtTokenUtil.message, 无效的jwtToken。");
        }
        return null;
    }

    public Date getIssuedAtDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getIssuedAt);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    public Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 验证token是否过期了。
     *
     * @param token
     * @return
     */
    public Boolean isTokenExpired(String token) {
        try {
            final Date expiration = getExpirationDateFromToken(token);
            return expiration.before(clock.now());
        } catch (Exception e) {
            //e.printStackTrace();
            log.warn("Have some uncheck exceptions, maybe the token is invalid. here we totally return the true for expired token.");
        }
        return Boolean.TRUE;
    }

    private Boolean isCreatedBeforeLastPasswordReset(Date created, Date lastPasswordReset) {
        return (lastPasswordReset != null && created.before(lastPasswordReset));
    }

    private Boolean ignoreTokenExpiration(String token) {
        // here you specify tokens, for that the expiration is ignored
        return false;
    }

    /**
     * 获取token
     *
     * @param claims
     * @param subject
     * @return
     */
    public String doGenerateToken(Map<String, Object> claims, String subject) {
        final Date createdDate = clock.now();
        final Date expirationDate = calculateExpirationDate(createdDate);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(createdDate)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * 获取token
     *
     * @param json    必须是jsonObject，不能包含其它的
     * @param subject
     * @return
     */
    public String doGenerateToken(JSONObject json, String subject) {
        final Date createdDate = clock.now();
        final Date expirationDate = calculateExpirationDate(createdDate);
        Map<String, Object> claims = new HashMap<String, Object>();
        for (Object vKey : json.keySet()) {
            Object vObjVal = json.get(vKey);
            claims.put(vKey.toString(), vObjVal);
        }
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(createdDate)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public Boolean canTokenBeRefreshed(String token, Date lastPasswordReset) {
        final Date created = getIssuedAtDateFromToken(token);
        return !isCreatedBeforeLastPasswordReset(created, lastPasswordReset)
                && (!isTokenExpired(token) || ignoreTokenExpiration(token));
    }

    public String refreshToken(String token) {
        final Date createdDate = clock.now();
        final Date expirationDate = calculateExpirationDate(createdDate);

        final Claims claims = getAllClaimsFromToken(token);
        claims.setIssuedAt(createdDate);
        claims.setExpiration(expirationDate);

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public Boolean validateToken(String token, String userName) {
        final String username = getUsernameFromToken(token);
        return username.equals(userName) && !isTokenExpired(token);
    }

    private Date calculateExpirationDate(Date createdDate) {
        return new Date(createdDate.getTime() + expiration * 1000);
    }

    //检验token合法性
    public int isValid(String token) {
        try {
            Jws<Claims> jwsClaims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token.trim());
            return EFFECT_STATUS;
        } catch (ExpiredJwtException e) {
            return EXP_STATUS;
        } catch (Exception e) {
            e.printStackTrace();
            return ILLEGAL_STATUS;
        }
    }

}
