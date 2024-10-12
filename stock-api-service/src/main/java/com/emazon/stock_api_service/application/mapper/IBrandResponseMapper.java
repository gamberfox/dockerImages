package com.emazon.stock_api_service.application.mapper;

import com.emazon.stock_api_service.application.dto.BrandResponse;
import com.emazon.stock_api_service.domain.model.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IBrandResponseMapper {
    BrandResponse toBrandResponse(Brand brand);
}
