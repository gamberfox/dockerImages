package com.emazon.stock_api_service.infrastructure.input.rest;


import com.emazon.stock_api_service.application.dto.BrandRequest;
import com.emazon.stock_api_service.application.dto.BrandResponse;
import com.emazon.stock_api_service.application.handler.IBrandHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.emazon.stock_api_service.util.BrandConstants.BRAND_CREATED;

@RestController
@RequestMapping("/brand")
@RequiredArgsConstructor
public class BrandRestController {
    private final IBrandHandler brandHandler;

    @PostMapping("/")
    public ResponseEntity<Map<String, Object>> createBrand(@RequestBody BrandRequest brandRequest) {
        brandHandler.createBrand(brandRequest);
        BrandResponse brandResponse=brandHandler.getBrandResponseByName(brandRequest.getName());
        RestResponse response=new RestResponse(BRAND_CREATED, brandResponse);
        return new ResponseEntity<>(response.getResponse(),HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<BrandResponse> getBrandById(
            @RequestParam(defaultValue="0") Long id) {
        return ResponseEntity.ok(brandHandler.getBrandResponseById(id));
    }

    @GetMapping("/name/")
    public ResponseEntity<BrandResponse> getBrandByName(
            @RequestParam(defaultValue="") String name) {
        return ResponseEntity.ok(brandHandler.getBrandResponseByName(name));
    }

    @GetMapping("/all/")
    public ResponseEntity<Page<BrandResponse>> getCategories(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue="true") boolean ascendingOrder) {
        List<BrandResponse> brandResponses = brandHandler.getBrandResponses(ascendingOrder);
        Pageable pageable = PageRequest.of(page, size);
        return ResponseEntity.ok(new PageImpl<>(brandResponses, pageable, brandResponses.size()));
    }
}
