package com.example.easerver.Handlers.BaseHandlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public abstract class BaseHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            makeHandle(exchange);
        } catch (Exception e) {
            handleException(exchange, e);
        }
    }

    protected abstract void makeHandle(HttpExchange exchange) throws IOException;

    protected void handleException(HttpExchange exchange, Exception e) throws IOException {
        exchange.sendResponseHeaders(500, 0);
        String errorMessage = "Внутренняя ошибка сервера: " + e.getMessage();
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(errorMessage.getBytes());
        }
    }
}
