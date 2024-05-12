package com.example.easerver.Entities;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.Collection;

@Entity
@Table(name = "districts", schema = "emergency", catalog = "")
public class DistrictsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "district_id")
    private int districtId;
    @Basic
    @Column(name = "district_name")
    private String districtName;
    @OneToMany(mappedBy = "districtsByDistrictId")
    private Collection<AutoEntity> autosByDistrictId;

    public int getDistrictId() {
        return districtId;
    }

    public void setDistrictId(int districtId) {
        this.districtId = districtId;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DistrictsEntity that = (DistrictsEntity) o;

        if (districtId != that.districtId) return false;
        if (districtName != null ? !districtName.equals(that.districtName) : that.districtName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = districtId;
        result = 31 * result + (districtName != null ? districtName.hashCode() : 0);
        return result;
    }

    public Collection<AutoEntity> getAutosByDistrictId() {
        return autosByDistrictId;
    }

    public void setAutosByDistrictId(Collection<AutoEntity> autosByDistrictId) {
        this.autosByDistrictId = autosByDistrictId;
    }
}
