package com.example.easerver.Handlers.BaseHandlers;

import com.sun.net.httpserver.HttpExchange;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public abstract class RequestBodyHandler extends BaseHandler {
    @Override
    protected void makeHandling(HttpExchange exchange) throws IOException {
        InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), StandardCharsets.UTF_8);
        BufferedReader br = new BufferedReader(isr);
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line).append("\n");
        }
        br.close();
        isr.close();

        String requestBody = sb.toString();

        handleRequestBody(exchange, requestBody);
    }

    protected abstract void handleRequestBody(HttpExchange exchange, String requestBody) throws IOException;
}
