package com.example.easerver.ServerManagers;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketError;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebSocket
public class WebSocketServer {

    private static final List<Session> sessions = new ArrayList<>();

    @OnWebSocketConnect
    public void onConnect(Session session) {
        System.out.println("Клиент подключен: " + session.getRemoteAddress());
        sessions.add(session);
    }

    @OnWebSocketClose
    public void onClose(Session session, int statusCode, String reason) {
        System.out.println("Клиент отключен: " + session.getRemoteAddress());
        sessions.remove(session);
    }

    @OnWebSocketError
    public void onError(Session session, Throwable error){
        System.err.println("Ошибка подключения клиента " + session.getRemoteAddress() + ": "+ error);
    }

    public static void closeAllSessions() {
        for (Session session : sessions) {
            session.close();
        }
        sessions.clear();
    }

    public static void sendMessageToAll(String message) {
        for (Session session : sessions) {
            try {
                session.getRemote().sendString(message);
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
    }

}
