package com.example.easerver.Models.AdminSettingsModels;

public class NewRelation {
    private String service_name;
    private String kind_name;

    public String getService_name() {
        return service_name;
    }

    public void setService_name(String service_name) {
        this.service_name = service_name;
    }

    public String getKind_name() {
        return kind_name;
    }

    public void setKind_name(String kind_name) {
        this.kind_name = kind_name;
    }

    public NewRelation(String service_name, String kind_name) {
        this.service_name = service_name;
        this.kind_name = kind_name;
    }
}
