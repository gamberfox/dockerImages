package com.emazon.stock_api_service.infrastructure.output.jpa.adapter;

import com.emazon.stock_api_service.domain.model.Brand;
import com.emazon.stock_api_service.domain.spi.IBrandPersistencePort;
import com.emazon.stock_api_service.infrastructure.exception.BrandPersistenceException;
import com.emazon.stock_api_service.infrastructure.output.jpa.entity.BrandEntity;
import com.emazon.stock_api_service.infrastructure.output.jpa.mapper.IBrandEntityMapper;
import com.emazon.stock_api_service.infrastructure.output.jpa.repository.IBrandRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.emazon.stock_api_service.util.BrandConstants.BRAND_NAME_ALREADY_EXISTS;
import static com.emazon.stock_api_service.util.BrandConstants.BRAND_NOT_FOUND;

@RequiredArgsConstructor
public class BrandJpaAdapter implements IBrandPersistencePort {
    private final IBrandRepository brandRepository;
    private final IBrandEntityMapper brandEntityMapper;
    @Override
    public void createBrand(Brand brand) {
        if(brandRepository.findByName(brand.getName()).isPresent()) {
            throw new BrandPersistenceException(BRAND_NAME_ALREADY_EXISTS);
        }
        BrandEntity brandEntity = brandEntityMapper.toBrandEntity(brand);
        brandRepository.save(brandEntity);
    }

    @Override
    public Brand getBrandById(Long id) {
        BrandEntity brandEntity = brandRepository.findById(id)
                .orElseThrow(()->new BrandPersistenceException(
                        BRAND_NOT_FOUND));
        return brandEntityMapper.toBrand(brandEntity);
    }

    @Override
    public Brand getBrandByName(String name) {
        BrandEntity brandEntity = brandRepository.findByName(name)
                .orElseThrow(()-> new BrandPersistenceException(
                        BRAND_NOT_FOUND));
        return brandEntityMapper.toBrand(brandEntity);
    }

    @Override
    public boolean brandNameExists(String name) {
        return brandRepository.findByName(name).isPresent();
    }

    @Override
    public boolean brandIdExists(Long id) {
        return brandRepository.findById(id).isPresent();
    }
    @Override
    public List<Brand> getBrands() {
        List<BrandEntity> brands=brandRepository.findAll();
        return brandEntityMapper.toBrands(brands);
    }
}
