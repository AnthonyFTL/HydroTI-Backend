package com.upc.hydroti.iot.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostBody {
    private ValueWrapper datum;

    public PostBody(String value) {
        this.datum = new ValueWrapper(value);
    }
}
