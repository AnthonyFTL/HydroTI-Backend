package com.upc.hydroti.iot.api;

import com.upc.hydroti.iot.application.dto.LastValuesResponse;
import com.upc.hydroti.iot.application.services.DataService;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

@EnableScheduling
@Controller
public class IoTController {

    private final DataService dataService;
    private final SimpMessagingTemplate messagingTemplate;

    public IoTController(DataService dataService, SimpMessagingTemplate messagingTemplate) {
        this.dataService = dataService;
        this.messagingTemplate = messagingTemplate;
    }

    @Scheduled(fixedRate = 1000)
    public void getAllLastValues() {
        LastValuesResponse lastValuesResponse = dataService.getLastValues();
        messagingTemplate.convertAndSend("/iot/last-values", lastValuesResponse);
    }

}
