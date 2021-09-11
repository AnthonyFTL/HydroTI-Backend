package com.upc.hydroti.devices.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class DeviceResponse {

    private Long id;

    private String name;

    private String location;

    private String state;

    private Date lastUseDate;
}
