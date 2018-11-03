package com.jiaxiaohudong.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.StringWriter;

public class JsonUtil {
    static ObjectMapper mapper = new ObjectMapper();
    public static String toJson(Object obj){
        String json = null;
        try {
            StringWriter writer = new StringWriter();
            JsonGenerator generator = mapper.getFactory().createGenerator(writer);
            mapper.writeValue(generator, obj);
            json = writer.toString();
            generator.close();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return json;
    }

}
