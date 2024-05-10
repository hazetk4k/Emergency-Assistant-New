package com.example.easerver.Models.Report.Stages;

public class Stage5Model {
    private final int report_id;
    private final String end_actions_time;

    public int getReport_id() {
        return report_id;
    }

    public String getEnd_actions_time() {
        return end_actions_time;
    }

    public Stage5Model(int report_id, String end_actions_time) {
        this.report_id = report_id;
        this.end_actions_time = end_actions_time;
    }
}
