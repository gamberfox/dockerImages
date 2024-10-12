package com.emazon.stock_api_service.application.mapper;

import com.emazon.stock_api_service.application.dto.BrandRequest;
import com.emazon.stock_api_service.domain.model.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IBrandRequestMapper {
    Brand toBrand(BrandRequest brandRequest);
}
