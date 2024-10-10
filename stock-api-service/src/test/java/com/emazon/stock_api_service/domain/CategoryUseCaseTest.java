package com.emazon.stock_api_service.domain;

import com.emazon.stock_api_service.domain.exception.CategoryUseCaseException;
import com.emazon.stock_api_service.domain.exception.ResourceNotFoundException;
import com.emazon.stock_api_service.domain.model.Category;
import com.emazon.stock_api_service.domain.spi.ICategoryPersistencePort;
import com.emazon.stock_api_service.domain.usecase.CategoryUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


import static com.emazon.stock_api_service.util.CategoryConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

//@ExtendWith(MockitoExtension.class) // I'm sure this is useful
//@SpringBootTest //used for integration tests
class CategoryUseCaseTest {
    @Mock
    private ICategoryPersistencePort categoryPersistencePort;
    @InjectMocks
    private CategoryUseCase categoryUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateCategory() {
        Category category=new Category(null,"name","description");
        categoryUseCase.createCategory(category);
        verify(categoryPersistencePort, times(1)).createCategory(category);
        //when(categoryPersistencePort.getCategoryById(anyLong())).thenReturn(category);
    }

    @Test
    void testGetCategoryById() {
        Category category=new Category(1L,"a","description");
        ResourceNotFoundException ex = assertThrows(ResourceNotFoundException.class
                , () -> categoryUseCase.getCategoryById(1L));
        assertEquals(CATEGORY_NOT_FOUND, ex.getMessage());

        when(categoryUseCase.idExists(1L)).thenReturn(true);
        when(categoryPersistencePort.getCategoryById(anyLong())).thenReturn(category);
        assertEquals(category, categoryUseCase.getCategoryById(1L));
    }

    @Test
    void testGetCategoryByName() {
        Category category=new Category(1L,"a","description");
        ResourceNotFoundException ex = assertThrows(ResourceNotFoundException.class
                , () -> categoryUseCase.getCategoryByName("name"));
        assertEquals(CATEGORY_NOT_FOUND, ex.getMessage());
        when(categoryPersistencePort.getCategoryByName(anyString())).thenReturn(category);
        when(categoryUseCase.nameExists("name")).thenReturn(true);
        assertEquals(category,categoryUseCase.getCategoryByName("name"));
    }

    @Test
    void testSortCategories(){
        Category c1=new Category(1L,"a","description");
        Category c2=new Category(2L,"b","description");
        Category c3=new Category(3L,"c","description");
        List<Category> organizedCategories = Arrays.asList(c1,c2,c3);
        List<Category> categories = Arrays.asList(c1,c3,c2);
        List<Category> testList = new ArrayList<>(categories);
        categoryUseCase.sortCategories(testList,true);
        for(int i=0; i<organizedCategories.size(); i++){
            assertEquals(organizedCategories.get(i).getId(),testList.get(i).getId());
            assertEquals(organizedCategories.get(i).getName(),testList.get(i).getName());
            assertEquals(organizedCategories.get(i).getDescription(),testList.get(i).getDescription());
        }

        Collections.reverse(organizedCategories);
        testList = new ArrayList<>(categories);
        categoryUseCase.sortCategories(testList,false);
        for(int i=0; i<organizedCategories.size(); i++){
            assertEquals(organizedCategories.get(i).getId(),testList.get(i).getId());
            assertEquals(organizedCategories.get(i).getName(),testList.get(i).getName());
            assertEquals(organizedCategories.get(i).getDescription(),testList.get(i).getDescription());
        }
    }

    @Test
    void testCategoryValidateNameAlreadyExists() {
        when(categoryUseCase.nameExists("categoryName")).thenReturn(true);
        Category category = new Category(null,"categoryName", "description");
        CategoryUseCaseException ex = assertThrows(CategoryUseCaseException.class
                , () -> categoryUseCase.validate(category));
        assertEquals(CATEGORY_NAME_ALREADY_EXISTS
                ,ex.getErrorList().get(0));
    }

    @Test
    void testCategoryValidateNameTooLong(){
        Category category = new Category(null, "a".repeat(MAXIMUM_CATEGORY_NAME_LENGTH+1), "description");
        CategoryUseCaseException ex = assertThrows(CategoryUseCaseException.class
                , () -> categoryUseCase.validate(category));
        assertEquals(CATEGORY_NAME_TOO_LONG
                ,ex.getErrorList().get(0));
    }
    @Test
    void testCategoryValidateNameEmpty(){
        Category category = new Category(4L, "", "description");
        CategoryUseCaseException ex = assertThrows(CategoryUseCaseException.class
                , () -> categoryUseCase.validate(category));
        assertEquals(CATEGORY_NAME_CANNOT_BE_EMPTY
                ,ex.getErrorList().get(0));

    }
    @Test
    void testCategoryValidateDescriptionTooLong(){
        Category category = new Category(3L, "lego", "a".repeat(MAXIMUM_CATEGORY_DESCRIPTION_LENGTH+1));
        CategoryUseCaseException ex = assertThrows(CategoryUseCaseException.class
                , () -> categoryUseCase.validate(category));
        assertEquals(CATEGORY_DESCRIPTION_TOO_LONG
                ,ex.getErrorList().get(0));
    }
    @Test
    void testCategoryValidateDescriptionEmpty(){
        Category category = new Category(3L, "lego", "");
        CategoryUseCaseException ex = assertThrows(CategoryUseCaseException.class, () -> categoryUseCase.validate(category));
        assertEquals(CATEGORY_DESCRIPTION_CANNOT_BE_EMPTY
                ,ex.getErrorList().get(0));
    }
}
