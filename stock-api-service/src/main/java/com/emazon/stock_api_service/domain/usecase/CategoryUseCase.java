package com.emazon.stock_api_service.domain.usecase;

import com.emazon.stock_api_service.domain.api.ICategoryServicePort;
import com.emazon.stock_api_service.domain.exception.CategoryUseCaseException;
import com.emazon.stock_api_service.domain.exception.ResourceNotFoundException;
import com.emazon.stock_api_service.domain.model.Category;
import com.emazon.stock_api_service.domain.spi.ICategoryPersistencePort;

import java.util.ArrayList;
import java.util.List;

import static com.emazon.stock_api_service.util.CategoryConstants.*;

public class CategoryUseCase implements ICategoryServicePort {
    //@AutoWired is not recommended, if you want to do dependency injection,
    //you need to do it through injections in the class constructor.
    //we can't use spring because we're being agnostic of the technology,
    //so we won't use any annotation, therefore we'll make the injection
    //manually
    private final ICategoryPersistencePort categoryPersistencePort;
    //we'll generate a constructor with our attribute
    //we're performing dependency injection through a constructor
    public CategoryUseCase(ICategoryPersistencePort categoryPersistencePort) {
        this.categoryPersistencePort = categoryPersistencePort;
    }

    //we need to communicate what we're receiving with the thing that will
    //go through the domain, and what will be sent to the persistence
    @Override
    public void createCategory(Category category) {
        this.validate(category);
        this.categoryPersistencePort.createCategory(category);
    }

    @Override
    public Category getCategoryById(Long id) {
        if(Boolean.FALSE.equals(idExists(id))) {
            throw new ResourceNotFoundException(CATEGORY_NOT_FOUND);
        }
        return this.categoryPersistencePort.getCategoryById(id);
    }
    @Override
    public Category getCategoryByName(String name) {
        if(Boolean.FALSE.equals(nameExists(name))) {
            throw new ResourceNotFoundException(CATEGORY_NOT_FOUND);
        }
        return this.categoryPersistencePort.getCategoryByName(name);
    }

    @Override
    public List<Category> getCategories(Boolean ascendingOrder) {
        List<Category> categories= this.categoryPersistencePort.getCategories();
        sortCategories(categories, ascendingOrder);
        return categories;
    }
    @Override
    public void validate(Category category) {
        List<String> errorList=new ArrayList<>();
        if(Boolean.TRUE.equals(nameExists(category.getName()))) {
            errorList.add(CATEGORY_NAME_ALREADY_EXISTS);
        }
        if(category.getName().length()>MAXIMUM_CATEGORY_NAME_LENGTH){
            errorList.add(CATEGORY_NAME_TOO_LONG);
        }
        if(category.getDescription().length()>MAXIMUM_CATEGORY_DESCRIPTION_LENGTH){
            errorList.add(CATEGORY_DESCRIPTION_TOO_LONG);
        }
        if(category.getName().isEmpty()){
            errorList.add(
                    CATEGORY_NAME_CANNOT_BE_EMPTY);
        }
        if(category.getDescription().isEmpty()){
            errorList.add(CATEGORY_DESCRIPTION_CANNOT_BE_EMPTY);
        }
        if(!errorList.isEmpty()){
            throw new CategoryUseCaseException(errorList);
        }
    }
    public void sortCategories(List<Category>categories,Boolean ascendingOrder) {
        if(Boolean.TRUE.equals(ascendingOrder)){
            categories.sort((a, b) -> a.getName().compareTo(b.getName()));
        }
        else{
            categories.sort((a, b) -> b.getName().compareTo(a.getName()));
        }
    }
    public Boolean idExists(Long id) {
        return this.categoryPersistencePort.categoryIdExists(id);
    }
    public Boolean nameExists(String name) {
        return this.categoryPersistencePort.categoryNameExists(name);
    }
}
