package com.example.easerver.Handlers.BaseHandlers;

import com.example.easerver.Services.QueryParamClass;
import com.sun.net.httpserver.HttpExchange;

import java.util.Map;

public abstract class DeleteHandler extends BaseHandler {
    @Override
    protected void makeHandling(HttpExchange exchange) {
        try {
            String requestMethod = exchange.getRequestMethod();
            if (requestMethod.equalsIgnoreCase("DELETE")) {
                System.out.println("DELETE");
                String requestURI = exchange.getRequestURI().toString();
                Map<String, String> params = QueryParamClass.parseQueryParams(requestURI);

                int code = handleDeleteRequest(params);
                System.out.println(code);

                exchange.sendResponseHeaders(code, -1);
            } else {
                exchange.sendResponseHeaders(405, -1);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    protected abstract int handleDeleteRequest(Map<String, String> params);
}
