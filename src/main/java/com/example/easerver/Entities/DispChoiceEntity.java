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

    @Basic
    @Column(name = "receive_data_time")
    private Timestamp receiveDataTime;

    @Basic
    @Column(name = "additional_services_time")
    private Timestamp additionalServicesTime;

    @Basic
    @Column(name = "died_amount")
    private Integer diedAmount;

    @Basic
    @Column(name = "people_amount")
    private Integer peopleAmount;

    @Basic
    @Column(name = "additional_services")
    private String additionalServices;

    @Basic
    @Column(name = "stage")
    private String stage;

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

    public Integer getDispatcherId() {
        return dispatcherId;
    }

    public void setDispatcherId(Integer dispatcherId) {
        this.dispatcherId = dispatcherId;
    }


    public Timestamp getReceiveDataTime() {
        return receiveDataTime;
    }

    public void setReceiveDataTime(Timestamp receiveDataTime) {
        this.receiveDataTime = receiveDataTime;
    }

    public Timestamp getAdditionalServicesTime() {
        return additionalServicesTime;
    }

    public void setAdditionalServicesTime(Timestamp additionalServicesTime) {
        this.additionalServicesTime = additionalServicesTime;
    }

    public Integer getDiedAmount() {
        return diedAmount;
    }

    public void setDiedAmount(Integer diedAmount) {
        this.diedAmount = diedAmount;
    }

    public Integer getPeopleAmount() {
        return peopleAmount;
    }

    public void setPeopleAmount(Integer peopleAmount) {
        this.peopleAmount = peopleAmount;
    }

    public String getAdditionalServices() {
        return additionalServices;
    }

    public void setAdditionalServices(String additionalServices) {
        this.additionalServices = additionalServices;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
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
        if (!Objects.equals(startActionsTime, that.startActionsTime)) return false;
        if (!Objects.equals(endActionsTime, that.endActionsTime)) return false;
        if (!Objects.equals(callServicesTime, that.callServicesTime)) return false;
        if (!Objects.equals(dispatcherId, that.dispatcherId)) return false;
        if (!Objects.equals(receiveDataTime, that.receiveDataTime)) return false;
        if (!Objects.equals(additionalServicesTime, that.additionalServicesTime)) return false;
        if (!Objects.equals(diedAmount, that.diedAmount)) return false;
        if (!Objects.equals(peopleAmount, that.peopleAmount)) return false;
        if (!Objects.equals(additionalServices, that.additionalServices)) return false;
        if (!Objects.equals(stage, that.stage)) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (nameChar != null ? nameChar.hashCode() : 0);
        result = 31 * result + (nameKind != null ? nameKind.hashCode() : 0);
        result = 31 * result + (services != null ? services.hashCode() : 0);
        result = 31 * result + (startActionsTime != null ? startActionsTime.hashCode() : 0);
        result = 31 * result + (endActionsTime != null ? endActionsTime.hashCode() : 0);
        result = 31 * result + (callServicesTime != null ? callServicesTime.hashCode() : 0);
        result = 31 * result + (dispatcherId != null ? dispatcherId.hashCode() : 0);
        result = 31 * result + (receiveDataTime != null ? receiveDataTime.hashCode() : 0);
        result = 31 * result + (additionalServicesTime != null ? additionalServicesTime.hashCode() : 0);
        result = 31 * result + (diedAmount != null ? diedAmount.hashCode() : 0);
        result = 31 * result + (peopleAmount != null ? peopleAmount.hashCode() : 0);
        result = 31 * result + (additionalServices != null ? additionalServices.hashCode() : 0);
        result = 31 * result + (stage != null ? stage.hashCode() : 0);
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
