package com.upc.hydroti.parks.application.dto;


public class AddParkRequest {

    private String name;

    private String address;

    private String district;

    public AddParkRequest() {
    }

    public AddParkRequest(String name, String address, String district) {
        this.name = name;
        this.address = address;
        this.district = district;
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
}
