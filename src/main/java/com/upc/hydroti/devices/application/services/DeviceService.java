package com.upc.hydroti.devices.application.services;

import com.upc.hydroti.devices.infra.entity.DeviceEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DeviceService {
    List<DeviceEntity> getAllDevices();
    List<DeviceEntity> getAllDevicesByParkId(Long parkId);
    DeviceEntity getDeviceByIdAndParkId(Long parkId, Long deviceId);
    DeviceEntity addDevice(Long parkId, DeviceEntity device);
    DeviceEntity updateDevice( Long deviceId, DeviceEntity deviceDetails);
    ResponseEntity<?> deleteDevice(Long deviceId);
    void updateLastUsedDate();
}
