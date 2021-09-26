package com.upc.hydroti.devices.application.dto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateDeviceRequest {

    private String name;
    private Long parkId;
    private String state;

}
