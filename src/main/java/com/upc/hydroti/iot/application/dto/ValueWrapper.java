package com.upc.hydroti.iot.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ValueWrapper {
    private String value;

    public ValueWrapper(String value) {
        this.value = value;
    }
}
