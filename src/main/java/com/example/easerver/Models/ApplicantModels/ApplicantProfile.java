package com.example.easerver.Models.ApplicantModels;

public class ApplicantProfile {
    private String name;
    private String surname;
    private String patronymic;
    private String homeAddress;
    private String workAddress;
    private String email;

    public ApplicantProfile(String name, String surname, String patronymic, String homeAddress, String workAddress, String email) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.homeAddress = homeAddress;
        this.workAddress = workAddress;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getWorkAddress() {
        return workAddress;
    }

    public void setWorkAddress(String workAddress) {
        this.workAddress = workAddress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
