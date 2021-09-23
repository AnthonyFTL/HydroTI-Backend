package com.upc.hydroti.iot.application.services;

import com.upc.hydroti.iot.application.dto.IoTResponse;
import com.upc.hydroti.iot.application.dto.LastValuesResponse;
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

        return new LastValuesResponse(humidity.getLastValue(), lights.getLastValue(), moisture.getLastValue(),
                temperature.getLastValue());
    }

    private IoTResponse getIOTResponse(String key) {
        String url = dataEndpoint + key;
        return restTemplate.exchange(url, HttpMethod.GET, getRequestEntity(), IoTResponse.class).getBody();
    }

    private HttpEntity getRequestEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.set(apiKeyHeader, apiKey);
        return new HttpEntity(headers);
    }
}
