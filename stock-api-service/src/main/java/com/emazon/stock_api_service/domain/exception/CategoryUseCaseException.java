package com.emazon.stock_api_service.domain.exception;

import java.util.List;

public class CategoryUseCaseException extends RuntimeException {
    private final List<String> errorList;
    public CategoryUseCaseException(List<String> errorList) {
        super("VALIDATION_ERROR");
        this.errorList = errorList;
    }
    public List<String> getErrorList() {
        return errorList;
    }
}
