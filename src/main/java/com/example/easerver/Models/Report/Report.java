package com.example.easerver.Models.Report;

public class Report {
    public String email;
    public ReportComponents report;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ReportComponents getReportComponents() {
        return report;
    }

    public void setReportComponents(ReportComponents report) {
        this.report = report;
    }

    public Report(String email, ReportComponents report) {
        this.email = email;
        this.report = report;
    }
}
