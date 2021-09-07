package com.upc.hydroti.devices.application.services;

import com.upc.hydroti.devices.infra.entity.DeviceEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DeviceService {
    List<DeviceEntity> getAllDevicesByParkId(Long parkId);
    DeviceEntity getDeviceByIdAndParkId(Long parkId, Long deviceId);
    DeviceEntity addDevice(Long parkId, DeviceEntity device);
    DeviceEntity updateDevice(Long parkId, Long deviceId, DeviceEntity deviceDetails);
    ResponseEntity<?> deleteDevice(Long parkId, Long deviceId);
}
