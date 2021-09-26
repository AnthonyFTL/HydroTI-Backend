package com.upc.hydroti.parks.application.services;

import com.upc.hydroti.common.application.exception.ResourceNotFoundException;
import com.upc.hydroti.devices.infra.entity.DeviceEntity;
import com.upc.hydroti.parks.infra.entity.ParkEntity;
import com.upc.hydroti.parks.infra.repository.ParkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ParkServiceImpl implements ParkService {

    @Autowired
    private ParkRepository parkRepository;

    @Override
    public ParkEntity addPark(ParkEntity park) {
        return parkRepository.save(park);
    }

    @Override
    public ParkEntity updatePark(Long id, ParkEntity parkRequest) {
        ParkEntity park = parkRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Park", "Id", id));
        park.setName(parkRequest.getName());
        park.setAddress(parkRequest.getAddress());
        park.setCountry(parkRequest.getCountry());
        park.setDistrict(parkRequest.getDistrict());
        park.setLatitude(parkRequest.getLatitude());
        park.setLongitude(parkRequest.getLongitude());
        park.setProvince(parkRequest.getProvince());
        return parkRepository.save(park);
    }

    @Override
    public ResponseEntity<?> deletePark(Long id) {
        ParkEntity park = parkRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Park", "Id", id));
        parkRepository.delete(park);
        return null;
    }

    @Override
    public List<ParkEntity> getAllParks() { return parkRepository.findAll(); }

    @Override
    public ParkEntity getParkById(Long id) {
        return parkRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Park", "Id", id));
    }

    @Override
    public void switchIrrigationType() {
        ParkEntity park = parkRepository.findAll().get(0);
        park.setManualIrrigation(!park.getManualIrrigation());
        parkRepository.save(park);
    }

}
