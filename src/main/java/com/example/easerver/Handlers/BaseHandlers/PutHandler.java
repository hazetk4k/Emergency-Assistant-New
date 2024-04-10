package com.example.easerver.Handlers.BaseHandlers;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;

public abstract class PutHandler extends RequestBodyHandler {
    @Override
    protected void handleRequestBody(HttpExchange exchange, String requestBody) {
        try {
            String requestMethod = exchange.getRequestMethod();
            if (requestMethod.equalsIgnoreCase("PUT")) {
                int code = handlePutRequest(requestBody);
                System.out.println(code);
                exchange.sendResponseHeaders(code, -1);
            } else {
                exchange.sendResponseHeaders(405, -1);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    protected abstract int handlePutRequest(String requestBody);
}
