package com.emazon.stock_api_service.util;

public class GenericConstants {
    private GenericConstants() {
        throw new IllegalStateException("Utility class: this class cannot be instantiated");
    }
    public static final String PARAMETER_PAGE_SIZE_VALUE="pageSize cannot be less than 0";
    public static final String PARAMETER_NEGATIVE_PAGE_NUMBER_VALUE="negative pageNumber values are not allowed";
    public static final String PARAMETER_PAGE_NUMBER="pageNumber cannot be greater than totalPages";
}
