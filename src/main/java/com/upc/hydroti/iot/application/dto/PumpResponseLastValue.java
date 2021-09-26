package com.upc.hydroti.iot.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PumpResponseLastValue {
    @JsonProperty("last_value")
    private String lastValue;
}
