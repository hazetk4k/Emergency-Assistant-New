package com.example.easerver.Entities;

import jakarta.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "disp_choice", schema = "emergency", catalog = "")
public class DispChoiceEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "name_char")
    private String nameChar;
    @Basic
    @Column(name = "name_kind")
    private String nameKind;
    @Basic
    @Column(name = "services")
    private String services;
//    @Basic
//    @Column(name = "repot_id")
//    private Integer reportId;

    @Basic
    @Column(name = "start_actions_time")
    private Timestamp startActionsTime;

    @Basic
    @Column(name = "end_actions_time")
    private Timestamp endActionsTime;

    @Basic
    @Column(name = "call_services_time")
    private Timestamp callServicesTime;

    @Basic
    @Column(name = "dispatcher_id")
    private Integer dispatcherId;

    @ManyToOne
    @JoinColumn(name = "dispatcher_id", referencedColumnName = "id_syst", insertable = false, updatable = false)
    private SystUserEntity dispatcherBySystemUserId;

    @OneToOne
    @JoinColumn(name = "repot_id", referencedColumnName = "id_report", nullable = false)
    private ReportsEntity reportByReportId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameChar() {
        return nameChar;
    }

    public void setNameChar(String nameChar) {
        this.nameChar = nameChar;
    }

    public String getNameKind() {
        return nameKind;
    }

    public void setNameKind(String nameKind) {
        this.nameKind = nameKind;
    }

    public String getServices() {
        return services;
    }

    public void setServices(String services) {
        this.services = services;
    }

    public Timestamp getStartActionsTime() {
        return startActionsTime;
    }

    public void setStartActionsTime(Timestamp startActionsTime) {
        this.startActionsTime = startActionsTime;
    }

    public Timestamp getEndActionsTime() {
        return endActionsTime;
    }

    public void setEndActionsTime(Timestamp endActionsTime) {
        this.endActionsTime = endActionsTime;
    }

    public Timestamp getCallServicesTime() {
        return callServicesTime;
    }

    public void setCallServicesTime(Timestamp callServicesTime) {
        this.callServicesTime = callServicesTime;
    }

//    public Integer getReportId() {
//        return reportId;
//    }
//
//    public void setReportId(Integer reportId) {
//        this.reportId = reportId;
//    }

    public Integer getDispatcherId() {
        return dispatcherId;
    }

    public void setDispatcherId(Integer dispatcherId) {
        this.dispatcherId = dispatcherId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DispChoiceEntity that = (DispChoiceEntity) o;

        if (id != that.id) return false;
        if (!Objects.equals(nameChar, that.nameChar)) return false;
        if (!Objects.equals(nameKind, that.nameKind)) return false;
        if (!Objects.equals(services, that.services)) return false;
//        if (!Objects.equals(reportId, that.reportId)) return false;
        if (!Objects.equals(startActionsTime, that.startActionsTime)) return false;
        if (!Objects.equals(endActionsTime, that.endActionsTime)) return false;
        if (!Objects.equals(callServicesTime, that.callServicesTime)) return false;
        if (!Objects.equals(dispatcherId, that.dispatcherId)) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (nameChar != null ? nameChar.hashCode() : 0);
        result = 31 * result + (nameKind != null ? nameKind.hashCode() : 0);
        result = 31 * result + (services != null ? services.hashCode() : 0);
//        result = 31 * result + (reportId != null ? reportId.hashCode() : 0);
        result = 31 * result + (startActionsTime != null ? startActionsTime.hashCode() : 0);
        result = 31 * result + (endActionsTime != null ? endActionsTime.hashCode() : 0);
        result = 31 * result + (callServicesTime != null ? callServicesTime.hashCode() : 0);
        result = 31 * result + (dispatcherId != null ? dispatcherId.hashCode() : 0);
        return result;
    }

    public ReportsEntity getReportsByRepotId() {
        return reportByReportId;
    }

    public void setReportsByRepotId(ReportsEntity reportByReportId) {
        this.reportByReportId = reportByReportId;
    }

    public SystUserEntity getDispatcherBySystemUserId() {
        return dispatcherBySystemUserId;
    }

    public void setDispatcherBySystemUserId(SystUserEntity dispatcherBySystemUserId) {
        this.dispatcherBySystemUserId = dispatcherBySystemUserId;
    }
}
