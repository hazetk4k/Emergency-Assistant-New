package com.example.easerver.Handlers.BaseHandlers;

import com.example.easerver.Services.QueryParamClass;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

public abstract class GetHandler extends BaseHandler {
    @Override
    protected void makeHandling(HttpExchange exchange) {
        try {
            String requestMethod = exchange.getRequestMethod();
            if (requestMethod.equalsIgnoreCase("GET")) {
                System.out.println("GET");
                String requestURI = exchange.getRequestURI().toString();
                Map<String, String> params = QueryParamClass.parseQueryParams(requestURI);

                String response = handleGetRequest(params);
                System.out.println("\nОтвет клиенту: " + response);

                byte[] bytes = response.getBytes();
                exchange.sendResponseHeaders(200, bytes.length);
                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(bytes);
                }
            } else {
                exchange.sendResponseHeaders(405, -1);
            }
        } catch (Exception e) {
            try {
                exchange.sendResponseHeaders(400, -1);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            System.err.println(e.getMessage());
        }
    }

    protected abstract String handleGetRequest(Map<String, String> params);
}
