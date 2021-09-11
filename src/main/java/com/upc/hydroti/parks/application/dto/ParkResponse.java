package com.upc.hydroti.parks.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParkResponse {

    private Long id;

    private String name;

    private String address;

    private String district;
}
