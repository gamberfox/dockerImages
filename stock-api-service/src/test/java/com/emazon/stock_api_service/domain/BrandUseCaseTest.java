package com.emazon.stock_api_service.domain;


import com.emazon.stock_api_service.domain.exception.BrandUseCaseException;
import com.emazon.stock_api_service.domain.exception.ResourceNotFoundException;
import com.emazon.stock_api_service.domain.model.Brand;
import com.emazon.stock_api_service.domain.spi.IBrandPersistencePort;
import com.emazon.stock_api_service.domain.usecase.BrandUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.emazon.stock_api_service.util.BrandConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

//@SpringBootTest //used for integration tests
@ExtendWith(MockitoExtension.class)
class BrandUseCaseTest {
    @Mock
    private IBrandPersistencePort brandPersistencePort;
    @InjectMocks
    private BrandUseCase brandUseCase;

    @Test
    void testCreateBrand() {
        Brand brand=new Brand(null,"a","description");
        brandUseCase.createBrand(brand);
        verify(brandPersistencePort, times(1)).createBrand(brand);

        //when(brandPersistencePort.getBrandById(anyLong())).thenReturn(brand);
    }

    @Test
    void testGetBrandById() {
        Brand brand=new Brand(1L,"a","description");
        ResourceNotFoundException ex = assertThrows(ResourceNotFoundException.class
                , () -> brandUseCase.getBrandById(1L));
        assertEquals(BRAND_NOT_FOUND, ex.getMessage());

        when(brandUseCase.idExists(1L)).thenReturn(true);
        when(brandPersistencePort.getBrandById(anyLong())).thenReturn(brand);
        assertEquals(brand, brandUseCase.getBrandById(1L));
    }

    @Test
    void testGetBrandByName() {
        Brand brand=new Brand(1L,"a","description");
        ResourceNotFoundException ex = assertThrows(ResourceNotFoundException.class
                , () -> brandUseCase.getBrandByName("name"));
        assertEquals(BRAND_NOT_FOUND, ex.getMessage());
        when(brandPersistencePort.getBrandByName(anyString())).thenReturn(brand);
        when(brandUseCase.nameExists("name")).thenReturn(true);
        assertEquals(brand,brandUseCase.getBrandByName("name"));
    }

    @Test
    void testSortBrands(){
        Brand c1=new Brand(1L,"a","description");
        Brand c2=new Brand(2L,"b","description");
        Brand c3=new Brand(3L,"c","description");
        List<Brand> organizedBrands = Arrays.asList(c1,c2,c3);
        List<Brand> brands = Arrays.asList(c1,c3,c2);
        List<Brand> testList = new ArrayList<>(brands);
        brandUseCase.sortBrands(testList,true);
        for(int i=0; i<organizedBrands.size(); i++){
            assertEquals(organizedBrands.get(i).getId(),testList.get(i).getId());
            assertEquals(organizedBrands.get(i).getName(),testList.get(i).getName());
            assertEquals(organizedBrands.get(i).getDescription(),testList.get(i).getDescription());
        }

        Collections.reverse(organizedBrands);
        testList = new ArrayList<>(brands);
        brandUseCase.sortBrands(testList,false);
        for(int i=0; i<organizedBrands.size(); i++){
            assertEquals(organizedBrands.get(i).getId(),testList.get(i).getId());
            assertEquals(organizedBrands.get(i).getName(),testList.get(i).getName());
            assertEquals(organizedBrands.get(i).getDescription(),testList.get(i).getDescription());
        }
    }
    @Test
    void testBrandValidateNameAlreadyExists() {
        when(brandUseCase.nameExists("lego")).thenReturn(true);
        Brand brand = new Brand(null,"lego", "description");
        BrandUseCaseException ex = assertThrows(BrandUseCaseException.class
                , () -> brandUseCase.validate(brand));
        assertEquals(BRAND_NAME_ALREADY_EXISTS
                ,ex.getErrorList().get(0));
    }
    @Test
    void testBrandValidateNameTooLong(){
        Brand brand = new Brand(null, "a".repeat(51), "description");
        BrandUseCaseException ex = assertThrows(BrandUseCaseException.class
                , () -> brandUseCase.validate(brand));
        assertEquals(BRAND_NAME_TOO_LONG
                ,ex.getErrorList().get(0));
    }

    @Test
    void testBrandValidateNameEmpty(){
        Brand brand = new Brand(4L, "", "description");
        BrandUseCaseException ex = assertThrows(BrandUseCaseException.class
                , () -> brandUseCase.validate(brand));
        assertEquals(BRAND_NAME_CANNOT_BE_EMPTY
                ,ex.getErrorList().get(0));

    }
    @Test
    void testBrandValidateDescriptionTooLong(){
        Brand brand = new Brand(3L, "lego", "a".repeat(91));
        BrandUseCaseException ex = assertThrows(BrandUseCaseException.class
                , () -> brandUseCase.validate(brand));
        assertEquals(BRAND_DESCRIPTION_TOO_LONG
                ,ex.getErrorList().get(0));
    }
    @Test
    void testBrandValidateDescriptionEmpty(){
        Brand brand = new Brand(3L, "lego", "");
        BrandUseCaseException ex = assertThrows(BrandUseCaseException.class, () -> brandUseCase.validate(brand));
        assertEquals(BRAND_DESCRIPTION_CANNOT_BE_EMPTY
                ,ex.getErrorList().get(0));
    }
}