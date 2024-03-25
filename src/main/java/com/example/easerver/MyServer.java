package com.example.easerver;

import com.example.easerver.DBTransactions.EntityManagerUtil;
import com.example.easerver.Handlers.AdminHandlers.AddNewUserHandler;
import com.example.easerver.Handlers.ApplicantsHandlers.ApplicantProfileHandler;
import com.example.easerver.Handlers.ApplicantsHandlers.ApplicantSignInHandler;
import com.example.easerver.Handlers.ApplicantsHandlers.ApplicantSignUpHandler;
import com.example.easerver.Handlers.ApplicantsHandlers.ReportHandler;
import com.example.easerver.Handlers.SystemSignInHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Scanner;

public class MyServer {
    private static HttpServer server;
    private static boolean serverRunning = false;

    public static void main(String[] args) {
        try {
            startServer();
            handleConsoleInput();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void startServer() throws IOException {
        EntityManagerUtil.getEntityManager();
        server = HttpServer.create(new InetSocketAddress(8080), 0);

        //общие контексты
        server.createContext("/system-sign-in", new SystemSignInHandler());

        //контексты администратора
        server.createContext("/add-new-user", new AddNewUserHandler());

        //контексты диспетчера


        //контексты заявителя
        server.createContext("/auth/signup", new ApplicantSignUpHandler());
        server.createContext("/auth/signin", new ApplicantSignInHandler());
        server.createContext("/profile", new ApplicantProfileHandler());
        server.createContext("/sendreport", new ReportHandler());


        server.setExecutor(null);
        server.start();
        serverRunning = true;
        System.out.println("Сервер прослушивает. Порт: 8080");
    }

    private static void stopServer() {
        EntityManagerUtil.closeEntityManagerFactory();

        if (server != null) {
            server.stop(0);
            serverRunning = false;
            System.out.println("Сервер остановлен");
        }
    }

    private static void handleConsoleInput() {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("Введите '0' для остановки сервера или '1' для выхода:");
                String input = scanner.nextLine().toLowerCase();

                switch (input) {
                    case "0":
                        if (serverRunning) {
                            stopServer();
                        } else {
                            System.out.println("Сервер не запущен.");
                        }
                        break;
                    case "1":
                        if (serverRunning) {
                            stopServer();
                        }
                        System.out.println("Завершение работы приложения.");
                        System.exit(0);
                    default:
                        System.out.println("Неверный ввод. Попробуйте еще раз.");
                        break;
                }
            }
        }
    }
}
