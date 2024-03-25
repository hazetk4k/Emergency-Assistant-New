package com.example.easerver.Models.Report;

public class Report {
    public String email;
    public ReportComponents reportComponents;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ReportComponents getReportComponents() {
        return reportComponents;
    }

    public void setReportComponents(ReportComponents reportComponents) {
        this.reportComponents = reportComponents;
    }

    public Report(String email, ReportComponents reportComponents) {
        this.email = email;
        this.reportComponents = reportComponents;
    }
}
