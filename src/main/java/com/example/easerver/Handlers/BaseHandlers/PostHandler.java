package com.example.easerver.Handlers.BaseHandlers;

import com.sun.net.httpserver.HttpExchange;

import java.io.OutputStream;

public abstract class PostHandler extends RequestBodyHandler {
    @Override
    protected void handleRequestBody(HttpExchange exchange, String requestBody) {
        try {
            String requestMethod = exchange.getRequestMethod();
            System.out.println(requestMethod);
            if (requestMethod.equalsIgnoreCase("POST")) {

                int code = handlePostRequest(requestBody);
                System.out.println(code);

                if (code != 200) {
                    exchange.sendResponseHeaders(code, -1);
                } else if (shouldReturnResponseBody(requestBody)) {
                    String responseBody = generateResponseBody(requestBody);
                    System.out.println("Ответ клиенту: " + responseBody);
                    exchange.sendResponseHeaders(code, responseBody.getBytes().length);
                    try (OutputStream os = exchange.getResponseBody()) {
                        os.write(responseBody.getBytes());
                    }
                } else {
                    exchange.sendResponseHeaders(code, -1);
                }
            } else {
                exchange.sendResponseHeaders(405, -1);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    protected abstract int handlePostRequest(String requestBody);

    protected boolean shouldReturnResponseBody(String requestBody) {
        return false;
    }

    protected String generateResponseBody(String requestBody) {
        return "";
    }
}
