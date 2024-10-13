package com.emazon.stock_api_service.application.mapper;

import com.emazon.stock_api_service.application.dto.ArticleRequest;
import com.emazon.stock_api_service.domain.model.Article;
import com.emazon.stock_api_service.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
uses={IArticleRequestMapper.class,
IBrandRequestMapper.class,
ICategoryRequestMapper.class})
public interface IArticleRequestMapper {
    @Mapping(source = "brandId", target = "brand.id")
    @Mapping(source = "categoryIds", target = "categories")
    Article toArticle(ArticleRequest articleRequest);
    default Category mapCategoryIdToCategory(Long categoryId) {
        Category category= new Category();
        category.setId(categoryId);
        return category;
    }
    List<Category> mapCategoryIdsToCategories(List<Long> categoryIds);
}
