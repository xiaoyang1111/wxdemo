package com.example.demo.util;

import cn.hutool.core.lang.Editor;
import cn.hutool.core.util.StrUtil;
import io.netty.util.internal.ObjectUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

public class XMLUtil {

    public static String toXml(Map<String, Object> params, String rootElement, boolean cdata) {
        StringBuilder buf = new StringBuilder();
        List<String> keys = new ArrayList(params.keySet());
        Collections.sort(keys);
        buf.append("<").append(rootElement).append(">");

        String key;
        for (Iterator var5 = keys.iterator(); var5.hasNext(); buf.append("</").append(key).append(">\n")) {
            key = (String) var5.next();
            buf.append("<").append(key).append(">");
            if (cdata) {
                buf.append("<![CDATA[");
            }

            buf.append(null == params.get(key) ? "" : params.get(key));
            if (cdata) {
                buf.append("]]>");
            }
        }
        buf.append("</").append(rootElement).append(">");
        return buf.toString();
    }

    /**
     * * 实体对象转成Map
     * * @param obj 实体对象
     * * @return
     *      
     */
    public static Map<String, Object> object2Map(Object obj) {
        Map<String, Object> map = new HashMap<>();
        if (obj == null) {
            return map;
        }
        Class clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                map.put(field.getName(), field.get(obj));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }


}
