package com.emazon.stock_api_service.application.dto;

import lombok.Getter;
import lombok.Setter;

//response is what we'll return to the user/client
@Getter
@Setter
public class CategoryResponse {
    //there are cases where the id won't be sent because we don't want to show
    //the user sensitive information
    private Long id;
    private String name;
    private String description;
}
