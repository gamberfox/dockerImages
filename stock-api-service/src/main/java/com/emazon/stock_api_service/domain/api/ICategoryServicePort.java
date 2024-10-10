package com.emazon.stock_api_service.domain.api;

import com.emazon.stock_api_service.domain.model.Category;
import java.util.List;

public interface ICategoryServicePort {
    void validate(Category category);
    //this function will have a connection with the persistence
    void createCategory(Category category);
    Category getCategoryById(Long id);
    Category getCategoryByName(String name);
    List<Category> getCategories(Boolean ascendingOrder);
}
