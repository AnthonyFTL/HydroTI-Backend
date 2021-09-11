package com.upc.hydroti.devices.application.dto;


import java.util.Date;

public class AddDeviceRequest {

    private String name;

    private Date lastUseDate;

    private String state;

    public AddDeviceRequest() {
    }

    public AddDeviceRequest(String name, Date lastUseDate, String state) {
        this.name = name;
        this.lastUseDate = lastUseDate;
        this.state = state;
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
}
