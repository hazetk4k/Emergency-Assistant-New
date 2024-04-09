package com.example.easerver.Models.Report;

public class ReportTableItem {

    private int id;
    private String type;
    private String timestamp;
    private String place;
    private String fio;
    private Boolean wasSeen;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public Boolean getWasSeen() {
        return wasSeen;
    }

    public void setWasSeen(Boolean wasSeen) {
        this.wasSeen = wasSeen;
    }

    public ReportTableItem(int id, String type, String timestamp, String place, String fio, Boolean wasSeen) {
        this.id = id;
        this.type = type;
        this.timestamp = timestamp;
        this.place = place;
        this.fio = fio;
        this.wasSeen = wasSeen;
    }
}
