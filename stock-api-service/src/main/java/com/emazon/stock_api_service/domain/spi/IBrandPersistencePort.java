package com.emazon.stock_api_service.domain.spi;

import com.emazon.stock_api_service.domain.model.Brand;

import java.util.List;

public interface IBrandPersistencePort {
    void createBrand(Brand brand);
    Brand getBrandById(Long id);
    Brand getBrandByName(String name);
    boolean brandNameExists(String categoryName);
    boolean brandIdExists(Long id);
    List<Brand> getBrands();
}
