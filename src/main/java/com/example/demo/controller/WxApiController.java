package com.example.demo.controller;

import com.example.demo.util.WxUtil;
import com.example.demo.util.XMLUtil;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@RestController
@RequestMapping("/wx/")
public class WxApiController {

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
        System.out.println("get");
    }
    @PostMapping(value = "getToken",produces = "application/xml; charset=utf-8")
    public String  getToken1(HttpServletRequest request, HttpServletResponse response){
        try {
            Enumeration<String> stringEnumeration = request.getParameterNames();
            while(stringEnumeration.hasMoreElements()){
                String name =stringEnumeration.nextElement();
                System.out.println(name);
                System.out.println(request.getParameter(name));
            }
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
            Map<String,Object> responseMap = new HashMap<>();
            responseMap.put("ToUserName",map.get("FromUserName"));
            responseMap.put("FromUserName",map.get("ToUserName"));
            responseMap.put("CreateTime",new Date().getTime());
            responseMap.put("MsgType","text");
            responseMap.put("Content","你好");
            String reMes = XMLUtil.toXml(responseMap,"xml",true);
            return reMes;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
