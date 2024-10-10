package com.emazon.stock_api_service.infrastructure.exception;

public class CategoryPersistenceException extends RuntimeException {
    public CategoryPersistenceException(String message) {
        super(message);
    }
}
