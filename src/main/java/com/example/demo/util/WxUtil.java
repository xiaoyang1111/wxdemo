package com.example.demo.util;

import com.alibaba.druid.util.StringUtils;

import java.security.MessageDigest;


public class WxUtil {
    private static final  String TOKEND = "123456";
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
