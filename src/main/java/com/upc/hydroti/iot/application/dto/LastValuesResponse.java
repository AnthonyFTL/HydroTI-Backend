package com.upc.hydroti.iot.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LastValuesResponse {

    private Double humidity;
    private Double lights;
    private Double moisture;
    private Double temperature;

}