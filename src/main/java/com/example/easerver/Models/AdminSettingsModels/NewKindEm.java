package com.example.easerver.Models.AdminSettingsModels;

public class NewKindEm {
    private String kind_name;
    private String char_name;

    public String getKind_name() {
        return kind_name;
    }

    public void setKind_name(String kind_name) {
        this.kind_name = kind_name;
    }

    public String getChar_name() {
        return char_name;
    }

    public void setChar_name(String char_name) {
        this.char_name = char_name;
    }

    public NewKindEm(String kind_name, String char_name) {
        this.kind_name = kind_name;
        this.char_name = char_name;
    }
}
