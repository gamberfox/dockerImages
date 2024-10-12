package com.emazon.stock_api_service.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BrandResponse {
    private Long id;
    private String name;
    private String description;
}
