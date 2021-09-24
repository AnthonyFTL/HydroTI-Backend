package com.upc.hydroti.iot.api;

import com.upc.hydroti.devices.application.services.DeviceService;
import com.upc.hydroti.devices.infra.entity.DeviceEntity;
import com.upc.hydroti.iot.application.dto.LastValuesResponse;
import com.upc.hydroti.iot.application.dto.PumpResponse;
import com.upc.hydroti.iot.application.services.DataService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@EnableScheduling
@Controller
@RestController("/iot")
public class IoTController {

    private final DataService dataService;
    private final DeviceService deviceService;
    private final SimpMessagingTemplate messagingTemplate;

    public IoTController(DataService dataService, DeviceService deviceService, SimpMessagingTemplate messagingTemplate) {
        this.dataService = dataService;
        this.deviceService = deviceService;
        this.messagingTemplate = messagingTemplate;
    }

    @Scheduled(fixedRate = 1000)
    public void getAllLastValues() {
        LastValuesResponse lastValuesResponse = dataService.getLastValues();
        messagingTemplate.convertAndSend("/iot/last-values", lastValuesResponse);
    }

    @PostMapping("/pump")
    public ResponseEntity<PumpResponse> switchPumpValue() {
        PumpResponse response = dataService.switchPump();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
