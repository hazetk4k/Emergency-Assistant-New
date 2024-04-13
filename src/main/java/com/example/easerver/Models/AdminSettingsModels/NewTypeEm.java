package com.example.easerver.Models.AdminSettingsModels;

public class NewTypeEm {
    String kind_name;
    String type_name;
    String recommendations;

    public String getKind_name() {
        return kind_name;
    }

    public void setKind_name(String kind_name) {
        this.kind_name = kind_name;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public String getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(String recommendations) {
        this.recommendations = recommendations;
    }

    public NewTypeEm(String kind_name, String type_name, String recommendations) {
        this.kind_name = kind_name;
        this.type_name = type_name;
        this.recommendations = recommendations;
    }
}
