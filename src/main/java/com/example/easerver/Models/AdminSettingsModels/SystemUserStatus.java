package com.example.easerver.Models.AdminSettingsModels;

public class SystemUserStatus {
    int id;
    byte status;

    String login;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public SystemUserStatus(int id, byte status, String login) {
        this.id = id;
        this.status = status;
        this.login = login;
    }
}
