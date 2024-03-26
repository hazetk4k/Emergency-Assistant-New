package com.example.easerver.Handlers.BaseHandlers;

import com.sun.net.httpserver.HttpExchange;

import java.io.OutputStream;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public abstract class GetHandler extends BaseHandler {
    @Override
    protected void makeHandle(HttpExchange exchange) {
        try {
            String requestMethod = exchange.getRequestMethod();
            if (requestMethod.equalsIgnoreCase("GET")) {
                String requestURI = exchange.getRequestURI().toString();
                Map<String, String> params = parseQueryParams(requestURI);

                String response = handleGet(params);

                byte[] bytes = response.getBytes();
                exchange.sendResponseHeaders(200, bytes.length);
                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(bytes);
                }
            } else {
                exchange.sendResponseHeaders(405, -1);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private Map<String, String> parseQueryParams(String uri) {
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

    protected abstract String handleGet(Map<String, String> params);
}
