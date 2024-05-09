package com.example.easerver.Models.Report;

public class DispModel_stage3 {
    private String receivedDataDateTime;
    private int people_in_area_amount;
    private int died_in_disaster_amount;

    private int report_id;

    public String getReceivedDataDateTime() {
        return receivedDataDateTime;
    }

    public void setReceivedDataDateTime(String receivedDataDateTime) {
        this.receivedDataDateTime = receivedDataDateTime;
    }

    public int getPeople_in_area_amount() {
        return people_in_area_amount;
    }

    public void setPeople_in_area_amount(int people_in_area_amount) {
        this.people_in_area_amount = people_in_area_amount;
    }

    public int getDied_in_disaster_amount() {
        return died_in_disaster_amount;
    }

    public void setDied_in_disaster_amount(int died_in_disaster_amount) {
        this.died_in_disaster_amount = died_in_disaster_amount;
    }

    public int getReport_id() {
        return report_id;
    }

    public void setReport_id(int report_id) {
        this.report_id = report_id;
    }

    public DispModel_stage3(String receivedDataDateTime, int people_in_area_amount, int died_in_disaster_amount, int report_id) {
        this.receivedDataDateTime = receivedDataDateTime;
        this.people_in_area_amount = people_in_area_amount;
        this.died_in_disaster_amount = died_in_disaster_amount;
        this.report_id = report_id;
    }
}
