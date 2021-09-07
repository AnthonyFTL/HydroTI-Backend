package com.upc.hydroti.devices.application.dto;

import java.util.Date;

public class UpdateDeviceRequest {

    private String name;

    private Date lastUseDate;

    private String state;

    public UpdateDeviceRequest() {
    }

    public UpdateDeviceRequest(String name, Date lastUseDate, String state) {
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
