package com.example.demo.util;

import cn.hutool.core.date.DateUtil;
import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.menu.MenuButton;
import com.example.demo.entity.template.DataValue;
import com.example.demo.entity.template.SendTemplate;
import com.example.demo.entity.template.TemplateList;
import com.github.kevinsawicki.http.HttpRequest;
import lombok.extern.slf4j.Slf4j;
import java.security.MessageDigest;
import java.util.*;


/**
 * 微信工具类
 */
@Slf4j
public class WxUtil {
    /**
     * appid
     */
    private static String APPID = "wxc957d81994ad6084";
    /**
     * appsecret
     */
    private static String APPSECRET = "f302feb8a6b31f00ff1efd589cfd2daa";
    /**
     * Token
     */
    private static String TOKEND = "123456";
    /**
     * accesstoken
     */
    private static String ACCESSTOKEN;
    /**
     * 过期时间
     */
    private static Long ACCESSTOKENOUTTIME = 1L;
    /**
     * 获取accesstokenUrl
     */
    private static String GET_ACCESS_TOKEN = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s";
    /**
     * 创建菜单
     */
    private static String POST_MENU = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=%s";
    /**
     * 获取设置的行业信息
     */
    private static String GET_INDUSTRY = "https://api.weixin.qq.com/cgi-bin/template/get_industry?access_token=%s";
    /**
     * 获得模板ID
     */
    private static String API_ADD_TEMPLATE = "https://api.weixin.qq.com/cgi-bin/template/api_add_template?access_token=%s";
    /**
     * 获取模板列表
     */
    private static String GET_ALL_PRIVATE = "https://api.weixin.qq.com/cgi-bin/template/get_all_private_template?access_token=%s";
    /**
     * 发送模板消息
     */
    private static String SEND_TM_MESSAGE = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=%s";
    /**
     * 设置所属行业
     */
    private static String API_SET_INDUSTRY = "https://api.weixin.qq.com/cgi-bin/template/api_set_industry?access_token=%s";

    /**
     * 获取accesstoken
     *
     * @return
     */
    public static String getAccessToken() {
        Long now = new Date().getTime();
        if (now > ACCESSTOKENOUTTIME) {
            String url = String.format(GET_ACCESS_TOKEN, APPID, APPSECRET);
            String result = HttpRequest.get(url).body();
            ACCESSTOKENOUTTIME = new Date().getTime() + 7000 * 1000;
            JSONObject jsonObject = JSON.parseObject(result);
            ACCESSTOKEN = jsonObject.getString("access_token");
        }
        return ACCESSTOKEN;
    }

    /**
     * 创建自定义菜单
     */
    public static Boolean createMenu(MenuButton button) {
        Boolean flat = true;
        HttpRequest httpRequest = new HttpRequest(String.format(POST_MENU, WxUtil.getAccessToken()), "POST");
        httpRequest.send(JSON.toJSONString(button));
        String body = httpRequest.body();//微信返回信息
        JSONObject jsonObject = JSON.parseObject(body);
        log.info("createMenu result:" + JSON.toJSONString(jsonObject));
        if (!StringUtils.equals("ok", jsonObject.getString("errmsg"))) {
            flat = false;
            return flat;
        }
        return flat;
    }

    /**
     * 校验微信连接
     */
    public static Boolean checkSignature(String signature, String nonce, String timestamp) {
        Boolean flat = false;
        //1）将token、timestamp、nonce三个参数进行字典序排序
        String[] strings = new String[]{TOKEND, timestamp, nonce};
        String result = strings[0] + strings[1] + strings[2];
        //2）将三个参数字符串拼接成一个字符串进行sha1加密
        String params = WxUtil.sha1(result);
        //3）开发者获得加密后的字符串可与signature对比
        if (StringUtils.equals(signature, params)) {
            flat = true;
        }
        return flat;
    }

    /**
     * 关注事件处理
     *
     * @return 回复内容
     */
    public static String event(Map<String, String> map) {
        String response = null;
        if (null != map.get("Event") && StringUtils.equals("subscribe", map.get("Event"))) {
            //关注事件
            response = "欢迎关注微信公众号,\n有什么能帮到您";
        }
        return response;
    }

    /**
     * 设置所属行业
     */
    public static Boolean setIndustry(Map<String,String> map) {
        Boolean flat = false;
        log.info("setIndustryRequest:"+JSON.toJSONString(map));
        String url = String.format(API_SET_INDUSTRY, WxUtil.getAccessToken());
        HttpRequest httpRequest = HttpRequest.post(url);
        httpRequest.send(JSON.toJSONString(map));
        String result = httpRequest.body();
        JSONObject jsonObject = JSON.parseObject(result);
        System.out.println("setIndustryResponse:" + JSON.toJSONString(jsonObject));
        if(StringUtils.equals("ok",jsonObject.getString("msg"))){
            flat = true;
        }
        return flat;
    }

    /**
     * 获取设置的行业信息
     * @return
     */
    public static String getIndustry(){
      String url =  String.format(GET_INDUSTRY,WxUtil.getAccessToken());
      HttpRequest httpRequest = HttpRequest.get(url);
      String result = httpRequest.body();
      JSONObject jsonObject = JSON.parseObject(result);
      log.info("get_industrResponse:"+JSON.toJSONString(jsonObject));
      return result;
    }


    /**
     * 获得模板ID
     * @param template_id_short 模板编号
     * @return
     */
    public static String getTemplate(String template_id_short){
        String url =  String.format(API_ADD_TEMPLATE,WxUtil.getAccessToken());
        HttpRequest httpRequest = HttpRequest.post(url);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("template_id_short",template_id_short);
        log.info("getTemplateResquest:"+JSON.toJSONString(jsonObject));
        httpRequest.send(JSON.toJSONString(jsonObject));
        String result = httpRequest.body();
        JSONObject jsonObject1 = JSON.parseObject(result);
        log.info("getTemplateREsponse:"+JSON.toJSONString(jsonObject1));
        String templateId =jsonObject1.getString(template_id_short);
        return templateId;
    }

    /**
     * 发送模板消息
     * @return
     */
    public static Boolean sendTemplate(SendTemplate sendTemplate){
        Boolean flat = false;
        String url =  String.format(SEND_TM_MESSAGE,WxUtil.getAccessToken());
        log.info("sendTemplateRequest:"+JSON.toJSONString(sendTemplate));
        HttpRequest httpRequest = HttpRequest.post(url);

        httpRequest.send(JSON.toJSONString(sendTemplate));
        String result = httpRequest.body();
        log.info("sendTemplateResponse:"+JSON.toJSONString(result));
        JSONObject jsonObject = JSON.parseObject(result);
        if(StringUtils.equals("ok",jsonObject.getString("errmsg"))){
            flat = true;
        }
        return flat;
    }

    /**
     * 获取模板列表
     * @return
     */
    public static TemplateList getTemplateList(){
      String url =  String.format(GET_ALL_PRIVATE,getAccessToken());
      String result = HttpRequest.get(url).body();
      return (TemplateList) JSON.toJSON(result);
    }
    /**
     * sha1算法计算摘要
     *
     * @param param
     * @return
     */
    public static String sha1(String param) {
        String encodeStr = "";
        try {
            //获取加密对象
            MessageDigest messageDigest = MessageDigest.getInstance("sha1");
            messageDigest.update(param.getBytes("UTF-8"));
            encodeStr = byte2Hexb(messageDigest.digest());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encodeStr;
    }

    /**
     * 将byte转为16进制 返回字符串
     *
     * @param bytes
     * @return
     */
    public static String byte2Hexb(byte[] bytes) {
        StringBuffer stringBuffer = new StringBuffer();
        String temp = null;
        for (int i = 0; i < bytes.length; i++) {
            temp = Integer.toHexString(bytes[i] & 0xFF);
            if (temp.length() == 1) {
                //1得到一位的进行补0操作
                stringBuffer.append("0");
            }
            stringBuffer.append(temp);
        }
        return stringBuffer.toString();
    }

    public static void main(String[] args) {
        Map<String, DataValue> map = new HashMap<>();
        map.put("sendTime",DataValue.builder().color("#173177").value(DateUtil.format(new Date(),"yyyy-MM-dd HH:mm:ss")).build());
        map.put("toUserName",DataValue.builder().color("#173177").value("你最爱的猪猪").build());
        map.put("formUserName",DataValue.builder().color("#173177").value("我最爱的大傻猪").build());
        map.put("content",DataValue.builder().color("#173177").value("猪猪1314520").build());
        SendTemplate sendTemplate =SendTemplate.builder()
                .data(map)
                .template_id("jUIM2mpS_9Jg94-_QH7cmEe1H7dCAmlX3aGAXRa3ZCA")
                .touser("oyCVg1bMZa4UzRrx1QR7lMxGyZB8")
                .build();
        WxUtil.sendTemplate(sendTemplate);
    }
}
