package com.example.easerver.Entities;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "auto", schema = "emergency", catalog = "")
public class AutoEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "auto_id")
    private int autoId;
    @Basic
    @Column(name = "mark")
    private String mark;
    @Basic
    @Column(name = "auto_num")
    private String autoNum;
    @OneToMany(mappedBy = "autoByAutoId")
    private Collection<ServiceAutoRelationEntity> serviceAutoRelationsByAutoId;

    public int getAutoId() {
        return autoId;
    }

    public void setAutoId(int autoId) {
        this.autoId = autoId;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getAutoNum() {
        return autoNum;
    }

    public void setAutoNum(String autoNum) {
        this.autoNum = autoNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AutoEntity that = (AutoEntity) o;

        if (autoId != that.autoId) return false;
        if (mark != null ? !mark.equals(that.mark) : that.mark != null) return false;
        if (autoNum != null ? !autoNum.equals(that.autoNum) : that.autoNum != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = autoId;
        result = 31 * result + (mark != null ? mark.hashCode() : 0);
        result = 31 * result + (autoNum != null ? autoNum.hashCode() : 0);
        return result;
    }

    public Collection<ServiceAutoRelationEntity> getServiceAutoRelationsByAutoId() {
        return serviceAutoRelationsByAutoId;
    }

    public void setServiceAutoRelationsByAutoId(Collection<ServiceAutoRelationEntity> serviceAutoRelationsByAutoId) {
        this.serviceAutoRelationsByAutoId = serviceAutoRelationsByAutoId;
    }
}
