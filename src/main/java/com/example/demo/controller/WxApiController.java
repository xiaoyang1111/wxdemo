package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.annotation.PassToken;
import com.example.demo.entity.annotation.UserLoginToken;
import com.example.demo.entity.message.TextMessage;
import com.example.demo.util.ShunTentWxUtil;
import com.example.demo.util.WxUtil;
import com.example.demo.util.XMLUtil;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@RestController
@RequestMapping("/wx/")
@Slf4j
public class WxApiController {

    @PassToken
    @GetMapping("getToken")
    public void  getToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String signature = request.getParameter("signature");
        String echostr = request.getParameter("echostr");
        String timestamp = request.getParameter("timestamp");
        String nonce = request.getParameter("nonce");
        if(WxUtil.checkSignature(signature,nonce,timestamp)){
            PrintWriter printWriter = response.getWriter();
            printWriter.print(echostr);
            printWriter.flush();
            printWriter.close();
        }
    }
    @PostMapping(value = "getToken",produces = "application/xml; charset=utf-8")
    public String  getToken1(HttpServletRequest request, HttpServletResponse response){
        try {
            Map<String, String> map = new HashMap<>();
            ServletInputStream inputStream = request.getInputStream();
            SAXReader reder = new SAXReader();
            Document document = reder.read(inputStream);
            //获取根元素
            Element root = document.getRootElement();
            List<Element> list = root.elements();
            //遍历子元素
            for (Element element : list) {
                map.put(element.getName(), element.getStringValue());
            }
           log.info("requestMessage"+ JSON.toJSONString(map));
            String responseMes =  ShunTentWxUtil.event(map);
            TextMessage imageMessage = TextMessage.builder()
                    .CreateTime(new Date().getTime())
                    .FromUserName(map.get("ToUserName"))
                    .MsgType("text")
                    .ToUserName(map.get("FromUserName"))
                    .Content(responseMes)
                    .build();
            Map  responseMap = XMLUtil.object2Map(imageMessage);
            log.info("responseMessage:"+JSON.toJSONString(responseMap));
            String reMes = XMLUtil.toXml(responseMap,"xml",true);
            return reMes;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 微信授权
     * @param code
     * @return
     */
    @GetMapping("wxUrl")
    public String wxUrl(String code){
        JSONObject jsonObject = WxUtil.getUserAccessToken(code);
        WxUtil.getAuthUserInfo(jsonObject.getString("access_token"),jsonObject.getString("openid"));
        return null;
    }

    /**
     * 获取头部head
     */
    @UserLoginToken
    @PostMapping("getHead")
    public void getHead(HttpServletRequest request,HttpServletResponse response){
        String headParams  =request.getHeader("token");
        log.info(headParams);
    }
}
