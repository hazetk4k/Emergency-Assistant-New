package com.example.easerver.Models.AdminSettingsModels;

public class TypesEm {
    private String name;
    private String recommendations;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(String recommendations) {
        this.recommendations = recommendations;
    }

    public TypesEm(String name, String recommendations) {
        this.name = name;
        this.recommendations = recommendations;
    }
}
