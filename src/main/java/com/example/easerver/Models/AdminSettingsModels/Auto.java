package com.example.easerver.Models.AdminSettingsModels;

public class Auto {
    public String district_name;
    public String service_name;
    public String mark;
    public String number;

    public String getDistrict_name() {
        return district_name;
    }

    public String getService_name() {
        return service_name;
    }

    public String getMark() {
        return mark;
    }

    public String getNumber() {
        return number;
    }

    public Auto(String district_name, String service_name, String mark, String number) {
        this.district_name = district_name;
        this.service_name = service_name;
        this.mark = mark;
        this.number = number;
    }
}
