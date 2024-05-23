package com.example.easerver.ServerManagers;

import java.net.InetSocketAddress;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.websocket.server.WebSocketHandler;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;

public class WebSocketServerManager {
    private static Server webSocketServer;
    private static Thread webSocketThread;

    InetSocketAddress address = new InetSocketAddress(8085);

    private static Boolean webSocketServerRunning = false;

    public static void startWebSocketServer() {
        webSocketThread = new Thread(() -> {
            webSocketServer = new Server( 8085);
            webSocketServer.setHandler(new WebSocketHandler() {
                @Override
                public void configure(WebSocketServletFactory factory) {
                    factory.register(WebSocketServer.class);
                    factory.getPolicy().setIdleTimeout(600000);
                }
            });
            try {
                webSocketServer.start();
                webSocketServerRunning = true;
                System.out.println("WebSocket сервер создан. Порт: 8085");
            } catch (Exception e) {
                System.err.println("Ошибка при запуске WebSocket сервера: " + e.getMessage());
            }
        });
        webSocketThread.start();
    }

    public static void stopWebSocketServer() {
        if (webSocketThread != null && webSocketThread.isAlive()) {
            webSocketThread.interrupt();
            try {
                webSocketThread.join();
            } catch (InterruptedException e) {
                System.err.println("Прерывание ожидания завершения потока: " + e.getMessage());
                Thread.currentThread().interrupt(); // Восстанавливаем флаг прерывания
            }
        }

        if (webSocketServer != null) {
            try {
                WebSocketServer.closeAllSessions();
                webSocketServer.stop();
                webSocketServerRunning = false;
            } catch (Exception e) {
                System.err.println("Ошибка при остановке WebSocket сервера: " + e.getMessage());
            }
        }
    }

    public static boolean isWebSocketServerRunning() {
        return webSocketServerRunning;
    }
}
