package com.upc.hydroti.devices.api;

import com.upc.hydroti.devices.application.dto.AddDeviceRequest;
import com.upc.hydroti.devices.application.dto.DeviceResponse;
import com.upc.hydroti.devices.application.dto.UpdateDeviceRequest;
import com.upc.hydroti.devices.application.services.DeviceService;
import com.upc.hydroti.devices.infra.entity.DeviceEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class DevicesController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private DeviceService deviceService;

    @GetMapping("/devices")
    public List<DeviceResponse> getAllDevices(){
        List<DeviceResponse> devices = deviceService.getAllDevices().stream()
                .map(this::convertToResponse).collect(Collectors.toList());
        return devices;
    }

    @GetMapping("/parks/{parkId}/devices")
    public List<DeviceResponse> getAllDevicesByParkId( @PathVariable(name = "parkId") Long parkId){
        List<DeviceResponse> devices = deviceService.getAllDevicesByParkId(parkId).stream()
                .map(this::convertToResponse).collect(Collectors.toList());
        return  devices;
    }

    @GetMapping("/parks/{parkId}/devices/{deviceId}")
    public DeviceResponse getDeviceByIdAndParkId(@PathVariable(name = "parkId") Long parkId,
                                                 @PathVariable(name = "deviceId") Long deviceId) {
        return convertToResponse(deviceService.getDeviceByIdAndParkId(parkId, deviceId));
    }

    @PostMapping("/devices")
    public DeviceResponse addDevice(@Valid @RequestBody AddDeviceRequest request) {
       return convertToResponse(deviceService.addDevice(request.getParkId(), convertToEntity(request)));
    }

    @PutMapping("/parks/{parkId}/devices/{deviceId}")
    public void updateDevice(@PathVariable (name = "parkId") Long parkId,
                             @PathVariable (name = "deviceId") Long deviceId,
                             @Valid @RequestBody AddDeviceRequest request) {
        deviceService.updateDevice(parkId, deviceId, convertToEntity(request));
    }

    @DeleteMapping("/devices/{deviceId}")
    public ResponseEntity<?> deleteDevice(@PathVariable(name = "deviceId") Long deviceId) {
        return deviceService.deleteDevice( deviceId);
    }

    private DeviceEntity convertToEntity(AddDeviceRequest request) {return mapper.map(request, DeviceEntity.class);}

    private DeviceResponse convertToResponse(DeviceEntity entity) {return mapper.map(entity, DeviceResponse.class);}
}
