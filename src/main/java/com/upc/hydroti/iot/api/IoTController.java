package com.upc.hydroti.iot.api;

import com.upc.hydroti.devices.application.services.DeviceService;
import com.upc.hydroti.iot.application.dto.LastValuesResponse;
import com.upc.hydroti.iot.application.dto.PumpResponse;
import com.upc.hydroti.iot.application.services.DataService;
import com.upc.hydroti.parks.application.services.ParkService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableScheduling
@RestController
@RequestMapping("/api/iot")
public class IoTController {

    private final DataService dataService;
    private final DeviceService deviceService;
    private final ParkService parkService;
    private final SimpMessagingTemplate messagingTemplate;

    public IoTController(DataService dataService, DeviceService deviceService, ParkService parkService,
            SimpMessagingTemplate messagingTemplate) {
        this.dataService = dataService;
        this.deviceService = deviceService;
        this.parkService = parkService;
        this.messagingTemplate = messagingTemplate;
    }

    @Scheduled(fixedRate = 1000)
    public void getAllLastValues() {
        LastValuesResponse lastValuesResponse = dataService.getLastValues();
        if (lastValuesResponse.getPump().equals("ON") && lastValuesResponse.getManualIrrigation()) {
            if (lastValuesResponse.getHumidity() < 10 || lastValuesResponse.getMoisture() > 95
                    || lastValuesResponse.getTemperature() > 78 || lastValuesResponse.getLights() > 25)
                switchPump();
        }
        messagingTemplate.convertAndSend("/iot/last-values", lastValuesResponse);
    }

    @PostMapping("/irrigation-type")
    public void setIrrigationType() {
        parkService.switchIrrigationType();
    }

    @PostMapping("/pump")
    public ResponseEntity<PumpResponse> switchPumpValue() {
        PumpResponse response = switchPump();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private PumpResponse switchPump() {
        PumpResponse response = dataService.switchPump();
        deviceService.updateLastUsedDate();
        return response;
    }

}
