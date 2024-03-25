package com.example.easerver.Models.Report;

public class ReportComponents {
    public String type;
    public String additionalInfo;
    public String place;
    public long timestamp;
    public Boolean areThereAnyCasualties;
    public String casualtiesAmount;
    public Boolean isUserInDanger;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public Boolean getAreThereAnyCasualties() {
        return areThereAnyCasualties;
    }

    public void setAreThereAnyCasualties(Boolean areThereAnyCasualties) {
        this.areThereAnyCasualties = areThereAnyCasualties;
    }

    public String getCasualtiesAmount() {
        return casualtiesAmount;
    }

    public void setCasualtiesAmount(String casualtiesAmount) {
        this.casualtiesAmount = casualtiesAmount;
    }

    public Boolean getUserInDanger() {
        return isUserInDanger;
    }

    public void setUserInDanger(Boolean userInDanger) {
        isUserInDanger = userInDanger;
    }

    public ReportComponents(String type, String additionalInfo, String place, long timestamp, Boolean areThereAnyCasualties, String casualtiesAmount, Boolean isUserInDanger) {
        this.type = type;
        this.additionalInfo = additionalInfo;
        this.place = place;
        this.timestamp = timestamp;
        this.areThereAnyCasualties = areThereAnyCasualties;
        this.casualtiesAmount = casualtiesAmount;
        this.isUserInDanger = isUserInDanger;
    }
}
