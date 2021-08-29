package com.upc.hydroti.security.infra.repository;

import com.upc.hydroti.security.infra.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    @Query(value = "select * from users u where u.email = :query or u.id = :query", nativeQuery = true)
    Optional<UserEntity> findByEmailOrId(String query );
}
