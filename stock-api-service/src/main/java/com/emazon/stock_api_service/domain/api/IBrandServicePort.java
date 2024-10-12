package com.emazon.stock_api_service.domain.api;

import com.emazon.stock_api_service.domain.model.Brand;

import java.util.List;

public interface IBrandServicePort {
    void validate(Brand brand);
    void createBrand(Brand brand);
    Brand getBrandById(Long id);
    Brand getBrandByName(String name);
    List<Brand> getBrands(Boolean ascendingOrder);
}
