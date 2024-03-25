package com.example.easerver.Models.ApplicantModels;

import com.example.easerver.Models.ApplicantModels.ApplicantProfile;

public class ApplicantReg {

    private ApplicantProfile profile;
    private String password;

    public ApplicantReg(ApplicantProfile profile, String password) {
        this.profile = profile;
        this.password = password;
    }

    public ApplicantProfile getProfile() {
        return profile;
    }

    public void setProfile(ApplicantProfile profile) {
        this.profile = profile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
