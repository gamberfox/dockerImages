package com.emazon.stock_api_service.infrastructure.exception;

import org.springframework.http.HttpStatus;

import java.util.LinkedHashMap;
import java.util.Map;

public class JsonErrorResponse {
    Map<String, Object> response = new LinkedHashMap<>();
    public JsonErrorResponse(Integer errorType ,Object error) {
        switch(errorType){
            case 400:
                response.put("VALIDATION ERROR: ", error);
                response.put("statusCode",HttpStatus.BAD_REQUEST.value());
                break;
            case 404:
                response.put("RESOURCE NOT FOUND: ", error);
                response.put("statusCode",HttpStatus.NOT_FOUND.value());
                break;
            case 430:
                response.put("REQUEST LEVEL ERROR: ", error);
                response.put("statusCode",HttpStatus.BAD_REQUEST.value());
                break;
            default:
                response.put("UNHANDLED ERROR: ", error);
        }
        response.put("timestamp", System.currentTimeMillis());
    }
    public Map<String, Object> getResponse() {
        return response;
    }
}
