package com.upc.hydroti.iot.application.services;

import com.upc.hydroti.iot.application.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;


@Service
public class DataService {
    private final RestTemplate restTemplate;

    private final String dataEndpoint;

    private final String apiKeyHeader;
    private final String apiKey;

    @Autowired
    public DataService(
            RestTemplate restTemplate,

            @Value("${app.data.endpoint}") String dataEndpoint,
            @Value("${app.data.api-key-header}") String apiKeyHeader,
            @Value("${app.data.api-key}") String apiKey) {

        this.restTemplate = restTemplate;
        this.dataEndpoint = dataEndpoint;

        this.apiKeyHeader = apiKeyHeader;
        this.apiKey = apiKey;
    }

    public LastValuesResponse getLastValues() {
        IoTResponse humidity = getIOTResponse("humidity");
        IoTResponse lights = getIOTResponse("light");
        IoTResponse moisture = getIOTResponse("moisture");
        IoTResponse temperature = getIOTResponse("temperature");
        IoTResponse waterConsumption = getIOTResponse("water-consumption");

        return new LastValuesResponse(humidity.getLastValue(), lights.getLastValue(), moisture.getLastValue(),
                temperature.getLastValue(), waterConsumption.getLastValue());
    }

    private IoTResponse getIOTResponse(String key) {
        String url = dataEndpoint + key;
        return restTemplate.exchange(url, HttpMethod.GET, getRequestEntity(), IoTResponse.class).getBody();
    }

    public PumpResponse switchPump() {
        String url = dataEndpoint + "pump/data";

        PumpResponseLastValue pump = restTemplate.exchange(dataEndpoint + "pump", HttpMethod.GET, getRequestEntity(), PumpResponseLastValue.class).getBody();
        String value = pump.getLastValue().equals("ON") ? "OFF" : "ON";

        return restTemplate.exchange(url, HttpMethod.POST, postRequestEntity(value), PumpResponse.class).getBody();
    }

    private HttpEntity getRequestEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.set(apiKeyHeader, apiKey);
        return new HttpEntity(headers);
    }
    private HttpEntity postRequestEntity(String value) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(apiKeyHeader, apiKey);
        return new HttpEntity(new PostBody(value), headers);
    }


}
