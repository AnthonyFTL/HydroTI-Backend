package com.upc.hydroti.parks.infra.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "parks")
public class ParkEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "district", nullable = false)
    private String district;

    @Column(name = "latitude", nullable = false)
    private Double latitude;

    @Column(name = "longitude", nullable = false)
    private Double longitude;

    @Column(name = "province", nullable = false)
    private String province;

    @Column(name = "state", nullable = false)
    private String state = "INACTIVO";

    @Column(name = "devices_connected", nullable = false)
    private Integer devicesConnected = 0;

    @Column(name = "manual_irrigation", nullable = false)
    private Boolean manualIrrigation = true;

    public void Updatepark(ParkEntity park) {
        this.name = park.name;
        this.address = park.address;
        this.country = park.country;
        this.district = park.district;
        this.latitude = park.latitude;
        this.longitude = park.longitude;
        this.province = park.province;
        this.state = park.state;
        this.devicesConnected = park.devicesConnected;
        this.manualIrrigation = park.manualIrrigation;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getCountry() {
        return country;
    }

    public String getDistrict() {
        return district;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public String getProvince() {
        return province;
    }

    public Boolean getManualIrrigation() {
        return manualIrrigation;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public void setManualIrrigation(Boolean manualIrrigation) {
        this.manualIrrigation = manualIrrigation;
    }
}
