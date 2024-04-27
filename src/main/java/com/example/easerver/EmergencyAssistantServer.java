package com.example.easerver;

import com.example.easerver.ServerManagers.HttpServerManager;
import com.example.easerver.ServerManagers.WebSocketServerManager;

import java.util.Scanner;

public class EmergencyAssistantServer {
    public static void main(String[] args) {
        HttpServerManager.startHttpServer();
        WebSocketServerManager.startWebSocketServer();
        handleConsoleInput();
    }

    private static void handleConsoleInput() {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("Введите '0' для остановки сервера или '1' для выхода:");
                String input = scanner.nextLine().toLowerCase();

                switch (input) {
                    case "0":
                        tryStopServers();
                        WebSocketServerManager.stopWebSocketServer();
                        break;
                    case "1":
                        tryStopServers();
                        System.out.println("Завершение работы приложения.");
                        System.exit(0);
                    default:
                        System.out.println("Неверный ввод. Попробуйте еще раз.");
                        break;
                }
            }
        }
    }

    private static void tryStopServers() {
        if (HttpServerManager.isHttpServerRunning()) {
            HttpServerManager.stopHttpServer();
        } else {
            System.out.println("Http-Сервер не запущен.");
        }

        if (WebSocketServerManager.isWebSocketServerRunning()) {
            WebSocketServerManager.stopWebSocketServer();
        } else {
            System.out.println("WebSocket-Сервер не запущен.");
        }
    }
}

