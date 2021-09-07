package com.upc.hydroti.parks.application.dto;

public class UpdateParkRequest {

    private String name;

    private String address;

    private String district;

    private String state;

    public UpdateParkRequest() {
    }

    public UpdateParkRequest(String name, String address, String district, String state) {
        this.name = name;
        this.address = address;
        this.district = district;
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
