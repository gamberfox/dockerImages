package com.emazon.stock_api_service.application.handler;

import com.emazon.stock_api_service.application.dto.BrandRequest;
import com.emazon.stock_api_service.application.dto.BrandResponse;
import com.emazon.stock_api_service.domain.api.IBrandServicePort;
import com.emazon.stock_api_service.application.mapper.IBrandRequestMapper;
import com.emazon.stock_api_service.application.mapper.IBrandResponseMapper;
import com.emazon.stock_api_service.domain.model.Brand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class BrandHandler implements IBrandHandler{
    private final IBrandServicePort brandServicePort;
    private final IBrandRequestMapper brandRequestMapper;
    private final IBrandResponseMapper brandResponseMapper;
    @Override
    public void createBrand(BrandRequest brandRequest) {
        Brand brand = brandRequestMapper.toBrand(brandRequest);
        brandServicePort.createBrand(brand);
    }

    @Override
    public BrandResponse getBrandResponseById(Long id) {
        Brand brand= brandServicePort.getBrandById(id);
        return brandResponseMapper.toBrandResponse(brand);
    }

    @Override
    public BrandResponse getBrandResponseByName(String name) {
        Brand brand = brandServicePort.getBrandByName(name);
        return brandResponseMapper.toBrandResponse(brand);
    }
    @Override
    public List<BrandResponse> getBrandResponses(Boolean ascendingOrder) {
        List<Brand> brands = brandServicePort.getBrands(ascendingOrder);
        return brands
                .stream()
                .map(brandResponseMapper::toBrandResponse)
                .toList();
    }
}
