package com.upc.hydroti.devices.infra.repository;

import com.upc.hydroti.devices.infra.entity.DeviceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeviceRepository extends JpaRepository<DeviceEntity, Long> {
    List<DeviceEntity> findByParkId(Long parkId);
    Optional<DeviceEntity> findByIdAndParkId(Long id, Long parkId);
}
