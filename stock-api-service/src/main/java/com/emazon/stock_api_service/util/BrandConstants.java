package com.emazon.stock_api_service.util;

public class BrandConstants {
    private BrandConstants() {
        throw new IllegalStateException("Utility class: this class cannot be instantiated");
    }
    public static final String BRAND_CREATED = "The brand was successfully created";
    public static final String BRAND_NOT_FOUND = "The brand does not exist";
    public static final String BRAND_ID_OBLIGATORY = "you must include a brand id";
    public static final Integer MAXIMUM_BRAND_NAME_LENGTH=50;
    public static final Integer MAXIMUM_BRAND_DESCRIPTION_LENGTH=90;
    public static final String BRAND_NAME_ALREADY_EXISTS =
            "This brand name already exists";
    public static final String BRAND_NAME_TOO_LONG =
            "The brand name cannot be longer than"
                    +MAXIMUM_BRAND_NAME_LENGTH
                    +"characters";
    public static final String BRAND_NAME_CANNOT_BE_EMPTY =
            "The brand name cannot be empty";
    public static final String BRAND_DESCRIPTION_TOO_LONG =
            "The brand description cannot be longer than"
                    +MAXIMUM_BRAND_DESCRIPTION_LENGTH
                    +"characters";
    public static final String BRAND_DESCRIPTION_CANNOT_BE_EMPTY =
            "The brand description cannot be empty";
}
