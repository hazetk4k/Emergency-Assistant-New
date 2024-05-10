package com.example.easerver.Models.Report.Stages;

public class Stage1Model {
    private String start_action_time;
    private int report_id;
    private String disp_login;
    private String char_name;
    private String kind_name;

    public String getStart_action_time() {
        return start_action_time;
    }

    public void setStart_action_time(String start_action_time) {
        this.start_action_time = start_action_time;
    }

    public int getReport_id() {
        return report_id;
    }

    public void setReport_id(int report_id) {
        this.report_id = report_id;
    }

    public String getDisp_login() {
        return disp_login;
    }

    public void setDisp_login(String disp_login) {
        this.disp_login = disp_login;
    }

    public String getChar_name() {
        return char_name;
    }

    public void setChar_name(String char_name) {
        this.char_name = char_name;
    }

    public String getKind_name() {
        return kind_name;
    }

    public void setKind_name(String kind_name) {
        this.kind_name = kind_name;
    }

    public Stage1Model(String start_action_time, int report_id, String disp_login, String char_name, String kind_name) {
        this.start_action_time = start_action_time;
        this.report_id = report_id;
        this.disp_login = disp_login;
        this.char_name = char_name;
        this.kind_name = kind_name;
    }
}
