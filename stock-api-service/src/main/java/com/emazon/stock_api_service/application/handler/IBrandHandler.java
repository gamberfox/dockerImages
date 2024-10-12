package com.emazon.stock_api_service.application.handler;

import com.emazon.stock_api_service.application.dto.BrandRequest;
import com.emazon.stock_api_service.application.dto.BrandResponse;

import java.util.List;

public interface IBrandHandler {
    void createBrand(BrandRequest brandRequest);
    BrandResponse getBrandResponseById(Long id);
    BrandResponse getBrandResponseByName(String name);
    List<BrandResponse> getBrandResponses(Boolean ascendingOrder);
}
