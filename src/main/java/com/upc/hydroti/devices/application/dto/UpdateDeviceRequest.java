package com.upc.hydroti.devices.application.dto;

import java.util.Date;

public class UpdateDeviceRequest {

    private String name;

    private String location;

    private Date lastUseDate;

    private String state;

    public UpdateDeviceRequest() {
    }

    public UpdateDeviceRequest(String name, Date lastUseDate, String state, String location) {
        this.name = name;
        this.lastUseDate = lastUseDate;
        this.state = state;
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getLastUseDate() {
        return lastUseDate;
    }

    public void setLastUseDate(Date lastUseDate) {
        this.lastUseDate = lastUseDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
