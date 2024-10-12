package com.emazon.stock_api_service.infrastructure.output.jpa.mapper;

import com.emazon.stock_api_service.domain.model.Brand;
import com.emazon.stock_api_service.infrastructure.output.jpa.entity.BrandEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IBrandEntityMapper {
    BrandEntity toBrandEntity(Brand brand);
    Brand toBrand(BrandEntity brandEntity);
    List<Brand> toBrands(List<BrandEntity> brandEntities);
}
