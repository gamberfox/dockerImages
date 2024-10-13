package com.emazon.stock_api_service.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class ArticleResponse {
    private Long id;
    private String name;
    private String description;
    private Long stock;
    private BigDecimal price;
    private BrandResponse brand;
    private List<CategoryResponse> categories;
}
