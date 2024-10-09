package com.emazon.stock_api_service.infrastructure.output.jpa.mapper;


import com.emazon.stock_api_service.domain.model.Category;
import com.emazon.stock_api_service.infrastructure.output.jpa.entity.CategoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
unmappedTargetPolicy = ReportingPolicy.IGNORE,
unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ICategoryEntityMapper {
    CategoryEntity toEntity(Category category);
    Category toCategory(CategoryEntity categoryEntity);
    List<Category> toCategories(List<CategoryEntity> categoryEntities);
}
