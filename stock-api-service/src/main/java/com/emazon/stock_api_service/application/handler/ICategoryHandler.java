package com.emazon.stock_api_service.application.handler;


import com.emazon.stock_api_service.application.dto.CategoryRequest;
import com.emazon.stock_api_service.application.dto.CategoryResponse;

import java.util.List;
public interface ICategoryHandler {
    void createCategory(CategoryRequest categoryRequest);
    CategoryResponse getCategoryResponseById(Long id);
    CategoryResponse getCategoryResponseByName(String id);
    List<CategoryResponse> getCategoryResponses(Boolean ascendingOrder);
}
