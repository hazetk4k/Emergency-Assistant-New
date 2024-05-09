package com.example.easerver.ServerManagers;

import com.example.easerver.Handlers.DispatcherHandlers.ConfirmChosenServicesHandler;
import com.example.easerver.Handlers.DispatcherHandlers.ConfirmReceivedDataHandler;
import com.example.easerver.Handlers.DispatcherHandlers.EmergencyDataHandler;
import com.example.easerver.Handlers.DispatcherHandlers.GetDispChoicesHandler;
import com.example.easerver.Handlers.DispatcherHandlers.GetKindCharByTypeHandler;
import com.example.easerver.Handlers.DispatcherHandlers.GetServicesByKindHandler;
import com.example.easerver.Handlers.DispatcherHandlers.KindsOfCharHandler;
import com.example.easerver.Handlers.DispatcherHandlers.ReportDataHandler;
import com.example.easerver.Handlers.DispatcherHandlers.StartActionsHandler;
import java.io.IOException;
import java.net.InetSocketAddress;

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
import com.example.easerver.Handlers.AdminHandlers.UserSettings.UpdateUserStatusHandler;
import com.example.easerver.Handlers.ApplicantsHandlers.*;
import com.example.easerver.Handlers.DispatcherHandlers.AllReportsHandler;
import com.example.easerver.Handlers.SystemSignInHandler;
import com.sun.net.httpserver.HttpServer;

public class HttpServerManager {
    private static HttpServer httpServer;
    private static boolean httpServerRunning = false;

    public static void startHttpServer() {
        try {
            EntityManagerUtil.getEntityManager();
            httpServer = HttpServer.create(new InetSocketAddress(8080), 0);

            //общие контексты
            httpServer.createContext("/system-sign-in", new SystemSignInHandler());

            //контексты администратора
            //Настройки видов ЧС
            httpServer.createContext("/set-up-chars", new SetUpCharsHandler()); //done
            httpServer.createContext("/get-all-kinds", new GetAllKindsHandler()); //done
            httpServer.createContext("/add-new-kind", new AddNewKindHandler());
            httpServer.createContext("/delete-kind", new DeleteKindHandler());

            //Настройки пользователей
            httpServer.createContext("/add-new-user", new AddNewUserHandler());
            httpServer.createContext("/delete-user", new DeleteUserHandler());
            httpServer.createContext("/update-user-status", new UpdateUserStatusHandler());
            httpServer.createContext("/get-all-system-users", new GetAllUsersHandler()); //done

            //Настройки типов ЧС
            httpServer.createContext("/set-up-kinds", new SetUpKindsHandler()); //done
            httpServer.createContext("/get-all-types", new GetAllTypesHandler()); //done
            httpServer.createContext("/add-new-type", new AddNewTypeHandler());
            httpServer.createContext("/delete-type", new DeleteTypeHandler());

            //Настройка Связей видов и служб реагирования
            httpServer.createContext("/get-relations-list", new GetRelationsHandler()); //done
            httpServer.createContext("/set-up-services", new SetUpServicesHandler()); //done
            httpServer.createContext("/add-new-relation", new AddNewRelationHandler());
            httpServer.createContext("/delete-service-relation", new DeleteServiceRelationHandler());
            httpServer.createContext("/delete-all-kind-relations", new DeleteAllRelationsHandler());

            //контексты диспетчера
            httpServer.createContext("/get-all-reports", new AllReportsHandler()); //done
            httpServer.createContext("/set-up-emergency-data", new EmergencyDataHandler()); //done
            httpServer.createContext("/get-report-applicant-data", new ReportDataHandler()); //done
            httpServer.createContext("/get-kinds-of-char", new KindsOfCharHandler()); //done
            httpServer.createContext("/get-services-by-kind", new GetServicesByKindHandler()); //done
            httpServer.createContext("/start-action-time", new StartActionsHandler()); //done
            httpServer.createContext("/get-dispatcher-choice", new GetDispChoicesHandler()); //done
            httpServer.createContext("/confirm-chosen-services", new ConfirmChosenServicesHandler()); //done
            httpServer.createContext("/get-kind-char-by-type", new GetKindCharByTypeHandler()); //done
            httpServer.createContext("/confirm-received-data", new ConfirmReceivedDataHandler());

            //контексты заявителя
            httpServer.createContext("/auth/signup", new ApplicantSignUpHandler()); //done
            httpServer.createContext("/auth/signin", new ApplicantSignInHandler()); //done
            httpServer.createContext("/profile", new ApplicantProfileHandler()); //done
            httpServer.createContext("/sendreport", new ReportHandler()); //done
            httpServer.createContext("/emergencytypes", new EmergencyTypesHandler()); //done


            httpServer.setExecutor(null);
            httpServer.start();
            httpServerRunning = true;
            System.out.println("HTTP сервер прослушивает. Порт: 8080");
        } catch (IOException e) {
            System.out.println("Ошибка при запуске HTTP сервера: " + e.getMessage());
        }
    }

    public static void stopHttpServer() {
        if (httpServer != null) {
            httpServer.stop(0);
            httpServerRunning = false;
            System.out.println("HTTP сервер остановлен");
        }
    }

    public static boolean isHttpServerRunning() {
        return httpServerRunning;
    }
}
