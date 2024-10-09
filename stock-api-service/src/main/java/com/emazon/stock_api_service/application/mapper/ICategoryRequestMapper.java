package com.emazon.stock_api_service.application.mapper;

import com.emazon.stock_api_service.application.dto.CategoryRequest;
import com.emazon.stock_api_service.domain.model.Category;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

//the mapper that will convert the things that come from the command
//@Mapper annotation will make spring detect the class as a map-struct
//componentModel is telling spring to take this as a component that is
//injected as a dependency so we don't have to create an instance of
//the mapper when we want to make the transformations
@Mapper(componentModel = "spring",
//any unmapped element won't generate annoying warnings
        //while we're building the project
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface ICategoryRequestMapper {
    //convert the request into a Category
    Category toCategory(CategoryRequest categoryRequest);
    List<Category> toCategories(List<CategoryRequest> categoryRequestList);
}
