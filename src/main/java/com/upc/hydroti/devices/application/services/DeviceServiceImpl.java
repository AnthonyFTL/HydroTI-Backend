package com.upc.hydroti.devices.application.services;

import com.upc.hydroti.common.application.exception.ResourceNotFoundException;
import com.upc.hydroti.devices.infra.entity.DeviceEntity;
import com.upc.hydroti.devices.infra.repository.DeviceRepository;
import com.upc.hydroti.parks.infra.repository.ParkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceServiceImpl implements DeviceService{

    @Autowired
    private ParkRepository parkRepository;

    @Autowired
    private DeviceRepository deviceRepository;

    @Override
    public List<DeviceEntity> getAllDevices() {
        return deviceRepository.findAll();
    }

    @Override
    public List<DeviceEntity> getAllDevicesByParkId(Long parkId) {
        return deviceRepository.findByParkId(parkId);
    }

    @Override
    public DeviceEntity getDeviceByIdAndParkId(Long parkId, Long deviceId) {
        return deviceRepository.findByIdAndParkId(deviceId,parkId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Device not found with Id " + deviceId +
                                " and ParkId " + parkId));
    }

    @Override
    public DeviceEntity addDevice(Long parkId, DeviceEntity device) {
        return parkRepository.findById(parkId).map(park -> {
            device.setPark(park);
            return deviceRepository.save(device);
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Park", "Id", parkId));
    }

    @Override
    public DeviceEntity updateDevice(Long parkId, Long deviceId, DeviceEntity deviceDetails) {
        if(!parkRepository.existsById(parkId))
            throw new ResourceNotFoundException("Park", "Id", parkId);

        return deviceRepository.findById(deviceId).map(device -> {
            device.setName(deviceDetails.getName());
            device.setState(deviceDetails.getState());
            return deviceRepository.save(device);
        }).orElseThrow(() -> new ResourceNotFoundException("Device", "Id", deviceId));
    }

    @Override
    public ResponseEntity<?> deleteDevice(Long parkId, Long deviceId) {
        return deviceRepository.findByIdAndParkId(deviceId, parkId).map(device -> {
            deviceRepository.delete(device);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException(
                "Device not found with Id " + deviceId + " and ParkId " + parkId));
    }
}
