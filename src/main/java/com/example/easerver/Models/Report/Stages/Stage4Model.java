package com.example.easerver.Models.Report.Stages;

public class Stage4Model {

    private final String additional_services;
    private final int report_id;
    private final String additional_services_time;

    public String getAdditional_services() {
        return additional_services;
    }

    public int getReport_id() {
        return report_id;
    }

    public String getAdditional_services_time() {
        return additional_services_time;
    }

    public Stage4Model(String additional_services, int report_id, String additional_services_time) {
        this.additional_services = additional_services;
        this.report_id = report_id;
        this.additional_services_time = additional_services_time;
    }
}
