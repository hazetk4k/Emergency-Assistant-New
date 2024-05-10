package com.example.easerver.Services;

import com.google.gson.JsonObject;

public class ModelManager {

    public String getStageName(String stage) {
        String stage_name = "";
        switch (stage) {
            case "1":
                stage_name = "Начало реагирования";
                break;
            case "2":
            case "4":
            case "3":
                stage_name = "Реагирование в процессе";
                break;
            case "5":
                stage_name = "Действия завершены";
                break;
        }
        return stage_name;
    }

    public JsonObject getStageName(String stage, int report_id) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("report_id", report_id);
        ModelManager modelManager = new ModelManager();
        jsonObject.addProperty("stage_name", modelManager.getStageName(stage));
        return jsonObject;
    }
}
