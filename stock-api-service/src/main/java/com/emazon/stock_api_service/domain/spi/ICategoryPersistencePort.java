package com.emazon.stock_api_service.domain.spi;

import com.emazon.stock_api_service.domain.model.Category;
import java.util.List;

public interface ICategoryPersistencePort {
    void createCategory(Category category);
    Category getCategoryById(Long id);
    Category getCategoryByName(String name);
    boolean categoryNameExists(String categoryName);
    boolean categoryIdExists(Long id);
    List<Category> getCategories();
}
