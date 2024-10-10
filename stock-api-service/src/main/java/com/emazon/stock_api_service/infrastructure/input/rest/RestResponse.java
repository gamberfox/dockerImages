package com.emazon.stock_api_service.infrastructure.input.rest;

import java.util.LinkedHashMap;
import java.util.Map;

public class RestResponse {
    Map<String, Object> response = new LinkedHashMap<>();
    public RestResponse(String message,Object response) {
        this.response.put("status", "success");
        this.response.put("message", message);
        this.response.put("data", response);
    }
    public Map<String, Object> getResponse() {
        return response;
    }
}
