package com.emazon.stock_api_service.infrastructure.output.jpa.repository;

import com.emazon.stock_api_service.infrastructure.output.jpa.entity.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IBrandRepository extends JpaRepository<BrandEntity, Long> {
    Optional<BrandEntity> findByName(String brandName);
    Optional<BrandEntity> findById(Long id);
    List<BrandEntity> findAll();
}
