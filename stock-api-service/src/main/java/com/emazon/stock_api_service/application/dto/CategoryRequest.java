package com.emazon.stock_api_service.application.dto;

import lombok.Getter;
import lombok.Setter;

//Request is a petition we want to make to the system
@Getter
@Setter
public class CategoryRequest {
    //there are cases where the id won't need to be sent
    private Long id;
    private String name;
    private String description;
}
