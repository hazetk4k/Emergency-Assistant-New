package com.example.easerver;

import com.example.easerver.DBTransactions.EntityManagerUtil;
import com.example.easerver.Handlers.AdminHandlers.KindSettings.AddNewKindHandler;
import com.example.easerver.Handlers.AdminHandlers.KindSettings.DeleteKindHandler;
import com.example.easerver.Handlers.AdminHandlers.KindSettings.GetAllKindsHandler;
import com.example.easerver.Handlers.AdminHandlers.KindSettings.SetUpCharsHandler;
import com.example.easerver.Handlers.AdminHandlers.RelationsSettings.*;
import com.example.easerver.Handlers.AdminHandlers.TypeSettings.AddNewTypeHandler;
import com.example.easerver.Handlers.AdminHandlers.TypeSettings.DeleteTypeHandler;
import com.example.easerver.Handlers.AdminHandlers.TypeSettings.GetAllTypesHandler;
import com.example.easerver.Handlers.AdminHandlers.TypeSettings.SetUpKindsHandler;
import com.example.easerver.Handlers.AdminHandlers.UserSettings.AddNewUserHandler;
import com.example.easerver.Handlers.AdminHandlers.UserSettings.DeleteUserHandler;
import com.example.easerver.Handlers.AdminHandlers.UserSettings.GetAllUsersHandler;
import com.example.easerver.Handlers.AdminHandlers.UserSettings.UpdateUserStatus;
import com.example.easerver.Handlers.ApplicantsHandlers.*;
import com.example.easerver.Handlers.DispatcherHandlers.AllReportsHandler;
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
        //TODO: Ошибки PostHandler - ов
        //TODO: Ошибки GetHandler - ов
        //контексты администратора
        //Настройки видов ЧС
        server.createContext("/set-up-chars", new SetUpCharsHandler()); //done
        server.createContext("/get-all-kinds", new GetAllKindsHandler()); //done
        server.createContext("/add-new-kind", new AddNewKindHandler());
        server.createContext("/delete-kind", new DeleteKindHandler());
        //Настройки пользователей
        server.createContext("/add-new-user", new AddNewUserHandler());
        server.createContext("/delete-user", new DeleteUserHandler());
        server.createContext("/update-user-status", new UpdateUserStatus());
        server.createContext("/get-all-system-users", new GetAllUsersHandler()); //done
        //Настройки типов ЧС
        server.createContext("/set-up-kinds", new SetUpKindsHandler()); //done
        server.createContext("/get-all-types", new GetAllTypesHandler()); //done
        server.createContext("/add-new-type", new AddNewTypeHandler());
        server.createContext("/delete-type", new DeleteTypeHandler());
        //Настройка Связей видов и служб реагирования
        server.createContext("/get-relations-list", new GetRelationsHandler()); //done
        server.createContext("/set-up-services", new SetUpServicesHandler()); //done
        server.createContext("/add-new-relation", new AddNewRelationHandler());
        server.createContext("/delete-service-relation", new DeleteServiceRelationHandler());
        server.createContext("/delete-all-kind-relations", new DeleteAllRelationsHandler());

        //контексты диспетчера
        server.createContext("/get-all-reports", new AllReportsHandler()); //done
        //TODO:Разобраться с get-that-report
        server.createContext("/get-that-report", new AllReportsHandler());

        //контексты заявителя
        server.createContext("/auth/signup", new ApplicantSignUpHandler()); //done
        server.createContext("/auth/signin", new ApplicantSignInHandler()); //done
        server.createContext("/profile", new ApplicantProfileHandler()); //done
        server.createContext("/sendreport", new ReportHandler()); //done
        server.createContext("/emergencytypes", new EmergencyTypesHandler()); //done


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
