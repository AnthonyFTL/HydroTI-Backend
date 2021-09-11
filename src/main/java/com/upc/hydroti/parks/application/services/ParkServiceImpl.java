package com.upc.hydroti.parks.application.services;

import com.upc.hydroti.common.application.exception.ResourceNotFoundException;
import com.upc.hydroti.parks.infra.entity.ParkEntity;
import com.upc.hydroti.parks.infra.repository.ParkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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

}
