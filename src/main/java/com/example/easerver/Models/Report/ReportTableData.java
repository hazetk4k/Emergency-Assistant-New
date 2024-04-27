package com.example.easerver.Models.Report;

public class ReportTableData {
    private int id;
    private String type;
    private String fio;
    private String timestamp;
    private String place;
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

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
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

    public Boolean getWasSeen() {
        return wasSeen;
    }

    public void setWasSeen(Boolean wasSeen) {
        this.wasSeen = wasSeen;
    }

    public ReportTableData(int id, String type, String fio, String timestamp, String place, Boolean wasSeen) {
        this.id = id;
        this.type = type;
        this.fio = fio;
        this.timestamp = timestamp;
        this.place = place;
        this.wasSeen = wasSeen;
    }
}
