package com.upc.hydroti.iot.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class LastValuesResponse {

    private Double humidity;
    private Double lights;
    private Double moisture;
    private Double temperature;
    private Double waterConsumption;
    private String pump;
    private Boolean manualIrrigation;

    public LastValuesResponse(Double humidity, Double lights, Double moisture, Double temperature,
            Double waterConsumption, String pump, Boolean manualIrrigation) {
        this.humidity = humidity;
        this.lights = lights;
        this.moisture = moisture;
        this.temperature = temperature;
        this.waterConsumption = waterConsumption;
        this.pump = pump;
        this.manualIrrigation = manualIrrigation;
    }

    public Double getHumidity() {
        return humidity;
    }

    public Double getMoisture() {
        return moisture;
    }

    public Double getTemperature() {
        return temperature;
    }

    public Double getLights() {
        return lights;
    }

    public Boolean getManualIrrigation() {
        return manualIrrigation;
    }

    public String getPump() {
        return pump;
    }
}
