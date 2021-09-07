package com.upc.hydroti.parks.application.services;

import com.upc.hydroti.parks.infra.entity.ParkEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface ParkService {

    ParkEntity addPark(ParkEntity park);
    ParkEntity updatePark(Long id, ParkEntity parkRequest);
    ResponseEntity<?> deletePark(Long id);
    List<ParkEntity> getAllParks();
    ParkEntity getParkById(Long id);
}
