package com.upc.hydroti.devices.application.dto;


import java.util.Date;

public class AddDeviceRequest {

    private String name;
    private String location;

    public AddDeviceRequest() {
    }

    public AddDeviceRequest(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
