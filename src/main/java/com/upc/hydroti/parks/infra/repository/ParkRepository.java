package com.upc.hydroti.parks.infra.repository;

import com.upc.hydroti.parks.infra.entity.ParkEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ParkRepository extends JpaRepository<ParkEntity, Long> {
}
