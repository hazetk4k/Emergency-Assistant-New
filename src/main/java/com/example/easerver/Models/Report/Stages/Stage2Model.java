package com.example.easerver.Models.Report.Stages;

public class Stage2Model {
    private String services;
    private int report_id;
    private String confirm_services_time;

    public String getServices() {
        return services;
    }

    public void setServices(String services) {
        this.services = services;
    }

    public int getReport_id() {
        return report_id;
    }

    public void setReport_id(int report_id) {
        this.report_id = report_id;
    }

    public String getConfirm_services_time() {
        return confirm_services_time;
    }

    public void setConfirm_services_time(String confirm_services_time) {
        this.confirm_services_time = confirm_services_time;
    }

    public Stage2Model(String services, int report_id, String confirm_services_time) {
        this.services = services;
        this.report_id = report_id;
        this.confirm_services_time = confirm_services_time;
    }
}
