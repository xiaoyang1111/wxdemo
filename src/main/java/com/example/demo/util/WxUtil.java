package com.example.demo.util;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.menu.AbstractButton;
import com.example.demo.entity.menu.MenuButton;
import com.github.kevinsawicki.http.HttpRequest;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Date;


/**
 * 微信工具类
 */
public class WxUtil {
    //appid
    private static final  String APPID ="wxc957d81994ad6084";
    //appsecret
    private static final String APPSECRET = "f302feb8a6b31f00ff1efd589cfd2daa";
    //Token
    private static final  String TOKEND = "123456";
    //accesstoken
    private static  String ACCESSTOKEN ;
    //过期时间
    private static  Long ACCESSTOKENOUTTIME =1L ;
    //获取accesstokenUrl
    private static final  String GET_ACCESS_TOKEN = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";

    private static  final String POST_MENU = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=%s";

    /**
     * 获取accesstoken
     * @return
     */
    public static String getAccessToken(){
        Long now =new Date().getTime();
        if( now> ACCESSTOKENOUTTIME ){
            String url = String.format(GET_ACCESS_TOKEN,APPID,APPSECRET);
            String result = HttpRequest.get(url).body();
            ACCESSTOKENOUTTIME  = new Date().getTime() + 7000*1000;
            JSONObject jsonObject = JSON.parseObject(result);
            ACCESSTOKEN = jsonObject.getString("access_token");
        }
        return ACCESSTOKEN;
    }

    /**
     * 创建自定义菜单
     */
    public static Boolean createMenu(){
        MenuButton button = new MenuButton();
        AbstractButton button1 = AbstractButton.builder().name("菜单1").type("click").key("V1001_TODAY_MUSIC").build();
        button.setButton(new ArrayList<>());
        button.getButton().add(button1);
        System.out.println(JSON.toJSONString(button));
        HttpRequest httpRequest = new HttpRequest(String.format(POST_MENU,WxUtil.getAccessToken()),"POST");
        httpRequest.send(JSON.toJSONString(button));
        System.out.println(httpRequest.body());
        return null;
    }

    /**
     * 校验微信连接
     */
    public static  Boolean checkSignature(String signature,String nonce,String timestamp){
        Boolean flat = false;
        //1）将token、timestamp、nonce三个参数进行字典序排序
       String[] strings = new String[]{TOKEND,timestamp,nonce};
       String result = strings[0]+strings[1]+strings[2];
        //2）将三个参数字符串拼接成一个字符串进行sha1加密
        String params = WxUtil.sha1(result);
        //3）开发者获得加密后的字符串可与signature对比
        if(StringUtils.equals(signature,params)){
            flat =true;
        }
        return flat;
    }
    /**
     * sha1算法计算摘要
     * @param param
     * @return
     */
    public static String sha1(String param){
        String encodeStr = "";
        try {
            //获取加密对象
            MessageDigest messageDigest =  MessageDigest.getInstance("sha1") ;
            messageDigest.update(param.getBytes("UTF-8"));
            encodeStr = byte2Hex(messageDigest.digest());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encodeStr;
    }
    /**
     * 将byte转为16进制 返回字符串
     * @param bytes
     * @return
     */
    public static String byte2Hex(byte[] bytes){
        StringBuffer stringBuffer = new StringBuffer();
        String temp = null;
        for (int i=0;i<bytes.length;i++){
            temp = Integer.toHexString(bytes[i] & 0xFF);
            if (temp.length()==1){
                //1得到一位的进行补0操作
                stringBuffer.append("0");
            }
            stringBuffer.append(temp);
        }
        return stringBuffer.toString();
    }

}
