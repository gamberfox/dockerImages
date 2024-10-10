package com.emazon.stock_api_service.application.handler;

import com.emazon.stock_api_service.application.dto.CategoryRequest;
import com.emazon.stock_api_service.application.dto.CategoryResponse;
import com.emazon.stock_api_service.application.mapper.ICategoryRequestMapper;
import com.emazon.stock_api_service.application.mapper.ICategoryResponseMapper;
import com.emazon.stock_api_service.domain.api.ICategoryServicePort;
import com.emazon.stock_api_service.domain.model.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor//the private final variables will be injected
@Transactional
public class CategoryHandler implements ICategoryHandler{
    //very similar methods to the domain infrastructure, but with elements
    //of the same layer, we're using requests and responses.
    //we'll make the orchestration of the different use cases
    //and the injection using annotations
    private final ICategoryServicePort categoryServicePort;
    private final ICategoryRequestMapper categoryRequestMapper;
    private final ICategoryResponseMapper categoryResponseMapper;
    @Override
    public void createCategory(CategoryRequest categoryRequest) {
        Category category= categoryRequestMapper.toCategory(categoryRequest);
        categoryServicePort.createCategory(category);
    }

    @Override
    public CategoryResponse getCategoryResponseById(Long id) {
        //validation will belong to the infrastructure
        Category category = categoryServicePort.getCategoryById(id);
        return categoryResponseMapper.toCategoryResponse(category);
    }
    @Override
    public CategoryResponse getCategoryResponseByName(String name) {
        //validation will belong to the infrastructure
        Category category = categoryServicePort.getCategoryByName(name);
        return categoryResponseMapper.toCategoryResponse(category);
    }
    @Override
    public List<CategoryResponse> getCategoryResponses(Boolean ascendingOrder) {
        List<Category> categories = categoryServicePort.getCategories(ascendingOrder);
        return categories
                .stream()
                .map(categoryResponseMapper::toCategoryResponse)
                .toList();
    }
}
