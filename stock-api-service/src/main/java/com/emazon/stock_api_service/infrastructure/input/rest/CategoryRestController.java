package com.emazon.stock_api_service.infrastructure.input.rest;

import com.emazon.stock_api_service.application.dto.CategoryRequest;
import com.emazon.stock_api_service.application.dto.CategoryResponse;
import com.emazon.stock_api_service.application.handler.ICategoryHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.emazon.stock_api_service.util.CategoryConstants.*;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class    CategoryRestController {
    //every independence we inject are interfaces
    private final ICategoryHandler categoryHandler;
    @PostMapping("/")
    //we'll return a response entity of a  Void type because we're not interested
    //showing the user/client anything beyond the creation being made
    public ResponseEntity<Map<String,Object>> createCategory(@RequestBody CategoryRequest categoryRequest) {
        categoryHandler.createCategory(categoryRequest);
        CategoryResponse categoryResponse=categoryHandler.getCategoryResponseByName(categoryRequest.getName());
        RestResponse response= new RestResponse(CATEGORY_CREATED,
                categoryResponse);
        return new ResponseEntity<>(response.getResponse(),
        HttpStatus.CREATED);
    }
    @GetMapping("/aa")
    public ResponseEntity<String> responseTest(){
        return ResponseEntity.ok("another aa test");
    }
    @GetMapping("/")
    public ResponseEntity<CategoryResponse> getCategoryById(
            @RequestParam(name="id") Long id) {
        return ResponseEntity.ok(categoryHandler.getCategoryResponseById(id));
    }

    @GetMapping("/name/")
    public ResponseEntity<CategoryResponse> getCategoryByName(
            @RequestParam(defaultValue="") String name) {
        return ResponseEntity.ok(categoryHandler.getCategoryResponseByName(name));
    }

    @GetMapping("/all/")
    public ResponseEntity<Page<CategoryResponse>> getCategories(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue="true") boolean ascendingOrder) {
        List<CategoryResponse> categoryResponses = categoryHandler.getCategoryResponses(ascendingOrder);
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(new PageImpl<>(categoryResponses, pageable, categoryResponses.size()));
    }

    @PutMapping("/")
    public ResponseEntity<Map<String,Object>> updateCategory(@RequestBody CategoryRequest categoryRequest) {
        categoryHandler.updateCategory(categoryRequest);
        RestResponse response= new RestResponse(CATEGORY_UPDATED,
                categoryRequest);
        return new ResponseEntity<>(response.getResponse(),
                HttpStatus.OK);
    }
    @DeleteMapping("/")
    public ResponseEntity<Map<String,Object>> deleteCategory(
            @RequestParam(defaultValue = "0") Long id
    ) {
        categoryHandler.deleteCategoryById(id);
        RestResponse response= new RestResponse(CATEGORY_DELETED,
                id);
        return new ResponseEntity<>(response.getResponse(),
                HttpStatus.OK);
    }
}
