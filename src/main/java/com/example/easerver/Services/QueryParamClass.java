package com.example.easerver.Services;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class QueryParamClass {

    public static Map<String, String> parseQueryParams(String uri) {
        Map<String, String> params = new HashMap<>();
        try {
            String[] parts = uri.split("\\?", 2);
            if (parts.length > 1) {
                String query = parts[1];
                String[] pairs = query.split("&");
                for (String pair : pairs) {
                    int idx = pair.indexOf("=");
                    String key = URLDecoder.decode(pair.substring(0, idx), StandardCharsets.UTF_8);
                    String value = URLDecoder.decode(pair.substring(idx + 1), StandardCharsets.UTF_8);
                    params.put(key, value);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return params;
    }
}
