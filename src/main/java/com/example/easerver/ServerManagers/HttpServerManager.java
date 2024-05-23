package com.example.easerver.ServerManagers;

import com.example.easerver.Handlers.AdminHandlers.AutoSettings.AddNewAutoHandler;
import com.example.easerver.Handlers.AdminHandlers.AutoSettings.DeleteAutoHandler;
import com.example.easerver.Handlers.AdminHandlers.AutoSettings.GetAllAutosHandler;
import com.example.easerver.Handlers.AdminHandlers.DistrictsSettings.AddNewDistrictHandler;
import com.example.easerver.Handlers.AdminHandlers.DistrictsSettings.DeleteDistrictHandler;
import com.example.easerver.Handlers.AdminHandlers.DistrictsSettings.SetUpDistrictsHandler;
import com.example.easerver.Handlers.DispatcherHandlers.ReportLoadingHandlers.GetAutoByServiceHandler;
import com.example.easerver.Handlers.DispatcherHandlers.ReportLoadingHandlers.GetStageTimings;
import com.example.easerver.Handlers.DispatcherHandlers.StageHandlers.ConfirmChosenServicesHandler;
import com.example.easerver.Handlers.DispatcherHandlers.StageHandlers.ConfirmOtherChosenServicesHandler;
import com.example.easerver.Handlers.DispatcherHandlers.StageHandlers.ConfirmReceivedDataHandler;
import com.example.easerver.Handlers.DispatcherHandlers.ReportLoadingHandlers.EmergencyDataHandler;
import com.example.easerver.Handlers.DispatcherHandlers.ReportLoadingHandlers.GetDispChoicesHandler;
import com.example.easerver.Handlers.DispatcherHandlers.ReportLoadingHandlers.GetKindCharByTypeHandler;
import com.example.easerver.Handlers.DispatcherHandlers.ReportLoadingHandlers.GetServicesByKindHandler;
import com.example.easerver.Handlers.DispatcherHandlers.ReportLoadingHandlers.KindsOfCharHandler;
import com.example.easerver.Handlers.DispatcherHandlers.ReportLoadingHandlers.ReportDataHandler;
import com.example.easerver.Handlers.DispatcherHandlers.StageHandlers.EndActionsHandler;
import com.example.easerver.Handlers.DispatcherHandlers.StageHandlers.StartActionsHandler;
import com.example.easerver.Handlers.DispatcherHandlers.StatisticsHandlers.GetTodayReports;
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

            //Настройка транспорта
            httpServer.createContext("/get-all-autos", new GetAllAutosHandler());
            httpServer.createContext("/set-up-district", new SetUpDistrictsHandler());
            httpServer.createContext("/add-new-district", new AddNewDistrictHandler());
            httpServer.createContext("/add-new-auto", new AddNewAutoHandler());
            httpServer.createContext("/delete-district", new DeleteDistrictHandler());
            httpServer.createContext("/delete-auto", new DeleteAutoHandler());


            //Настройки пользователей
            httpServer.createContext("/add-new-user", new AddNewUserHandler());
            httpServer.createContext("/delete-user", new DeleteUserHandler());
            httpServer.createContext("/update-user-status", new UpdateUserStatusHandler());
            httpServer.createContext("/get-all-system-users", new GetAllUsersHandler());

            //Настройки типов ЧС
            httpServer.createContext("/set-up-kinds", new SetUpKindsHandler());
            httpServer.createContext("/get-all-types", new GetAllTypesHandler());
            httpServer.createContext("/add-new-type", new AddNewTypeHandler());
            httpServer.createContext("/delete-type", new DeleteTypeHandler());

            //Настройка Связей видов и служб реагирования
            httpServer.createContext("/get-relations-list", new GetRelationsHandler());
            httpServer.createContext("/set-up-services", new SetUpServicesHandler());
            httpServer.createContext("/add-new-relation", new AddNewRelationHandler());
            httpServer.createContext("/delete-service-relation", new DeleteServiceRelationHandler());
            httpServer.createContext("/delete-all-kind-relations", new DeleteAllRelationsHandler());

            //контексты диспетчера
            //Общее окно
            httpServer.createContext("/get-all-reports", new AllReportsHandler());

            //Окно заявления
            httpServer.createContext("/set-up-emergency-data", new EmergencyDataHandler());
            httpServer.createContext("/get-report-applicant-data", new ReportDataHandler());
            httpServer.createContext("/get-kinds-of-char", new KindsOfCharHandler());
            httpServer.createContext("/get-services-by-kind", new GetServicesByKindHandler());
            httpServer.createContext("/get-dispatcher-choice", new GetDispChoicesHandler());
            httpServer.createContext("/get-kind-char-by-type", new GetKindCharByTypeHandler());
            httpServer.createContext("/get-auto-by-service", new GetAutoByServiceHandler());
            httpServer.createContext("/get-stage-timings", new GetStageTimings());

            //обработчики подтверждений стадий реагирования
            httpServer.createContext("/start-action-time", new StartActionsHandler());
            httpServer.createContext("/confirm-chosen-services", new ConfirmChosenServicesHandler());
            httpServer.createContext("/confirm-received-data", new ConfirmReceivedDataHandler());
            httpServer.createContext("/confirm-other-chosen-services", new ConfirmOtherChosenServicesHandler());
            httpServer.createContext("/end-action-time", new EndActionsHandler());

            //контексты заявителя
            httpServer.createContext("/auth/signup", new ApplicantSignUpHandler());
            httpServer.createContext("/auth/signin", new ApplicantSignInHandler());
            httpServer.createContext("/profile", new ApplicantProfileHandler());
            httpServer.createContext("/sendreport", new ReportHandler());
            httpServer.createContext("/emergencytypes", new EmergencyTypesHandler());

            // Контексты аналитики:
            httpServer.createContext("/get-today-reports", new GetTodayReports());


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
