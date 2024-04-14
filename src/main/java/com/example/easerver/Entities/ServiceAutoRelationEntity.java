package com.example.easerver.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "service_auto_relation", schema = "emergency", catalog = "")
public class ServiceAutoRelationEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "relation_id")
    private int relationId;
    @Basic
    @Column(name = "service_id")
    private int serviceId;
    @Basic
    @Column(name = "auto_id")
    private int autoId;
    @ManyToOne
    @JoinColumn(name = "service_id", referencedColumnName = "service_id", insertable = false, updatable = false)
    private ServiceEntity serviceByServiceId;
    @ManyToOne
    @JoinColumn(name = "auto_id", referencedColumnName = "auto_id", insertable = false, updatable = false)
    private AutoEntity autoByAutoId;

    public int getRelationId() {
        return relationId;
    }

    public void setRelationId(int relationId) {
        this.relationId = relationId;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public int getAutoId() {
        return autoId;
    }

    public void setAutoId(int autoId) {
        this.autoId = autoId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServiceAutoRelationEntity that = (ServiceAutoRelationEntity) o;

        if (relationId != that.relationId) return false;
        if (serviceId != that.serviceId) return false;
        if (autoId != that.autoId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = relationId;
        result = 31 * result + serviceId;
        result = 31 * result + autoId;
        return result;
    }

    public ServiceEntity getServiceByServiceId() {
        return serviceByServiceId;
    }

    public void setServiceByServiceId(ServiceEntity serviceByServiceId) {
        this.serviceByServiceId = serviceByServiceId;
    }

    public AutoEntity getAutoByAutoId() {
        return autoByAutoId;
    }

    public void setAutoByAutoId(AutoEntity autoByAutoId) {
        this.autoByAutoId = autoByAutoId;
    }
}
