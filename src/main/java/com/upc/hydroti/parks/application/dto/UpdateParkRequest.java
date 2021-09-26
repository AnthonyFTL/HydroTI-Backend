package com.upc.hydroti.parks.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateParkRequest {

    private String name;

    private String address;

    private String country;

    private String district;

    private Double latitude;

    private Double longitude;

    private String province;

    private String state;
}
