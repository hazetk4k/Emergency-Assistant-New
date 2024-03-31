package com.example.easerver.Handlers.BaseHandlers;

import com.sun.net.httpserver.HttpExchange;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public abstract class PostHandler extends BaseHandler {
    @Override
    protected void makeHandle(HttpExchange exchange) {
        try {
            String requestMethod = exchange.getRequestMethod();
            if (requestMethod.equalsIgnoreCase("POST")) {
                //чтение данных
                InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), StandardCharsets.UTF_8);
                BufferedReader br = new BufferedReader(isr);
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line).append("\n");
                }
                br.close();
                isr.close();
                //получение тела запроса
                String requestBody = sb.toString();
                //выполнение хэндла запроса и возвращение кода запроса
                int code = handlePostInput(requestBody);
                System.out.println(code);
                //проверки
                if (code != 200) {
                    //ошибка (отправляется только код и пустое тело)
                    exchange.sendResponseHeaders(code, -1);
                } else if (shouldReturnResponseBody(requestBody)) {
                    //все хорошо, но кроме кода нужно вернуть на клиент тело
                    String responseBody = generateResponseBody(requestBody);
                    System.out.println("Ответ клиенту: " + responseBody);
                    exchange.sendResponseHeaders(code, responseBody.getBytes().length);
                    try (OutputStream os = exchange.getResponseBody()) {
                        os.write(responseBody.getBytes());
                    }
                } else {
                    //или все хорошо, но нужно вернуть только код
                    exchange.sendResponseHeaders(code, -1);
                }
            } else {
                //не тот метод (не Post)
                exchange.sendResponseHeaders(405, -1); // Method Not Allowed
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    protected abstract int handlePostInput(String requestBody);

    protected boolean shouldReturnResponseBody(String requestBody) {
        return false;
    }
    protected String generateResponseBody(String requestBody) {
        return "";
    }
}
