package com.distributedgame.userapp.controller.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class Utils {
    public static String serializeJSON(Object obj, boolean pretty) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        String resultJSON = null;
        try {
            if (pretty) {
                resultJSON = mapper.writerWithDefaultPrettyPrinter()
                        .writeValueAsString(obj);
            } else {
                resultJSON = mapper.writeValueAsString(obj);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return resultJSON;
    }
}
