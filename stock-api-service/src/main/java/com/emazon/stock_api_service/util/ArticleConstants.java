package com.emazon.stock_api_service.util;

public class ArticleConstants {
    private ArticleConstants() {
        throw new IllegalStateException("Utility class: this class cannot be instantiated");
    }
    public static final String ARTICLE_CREATED = "The article was successfully created";
    public static final String ARTICLE_NOT_FOUND = "The article does not exist";
    public static final String BRAND_ID_NOT_FOUND = "The brand id does not exist";
    public static final String BRAND_OBLIGATORY = "you must include a brand";
    public static final String CATEGORY_LIST_OBLIGATORY = "you must include category list";
    public static final String CATEGORY_REPEATED = "category is repeated";
    public static final Integer MAXIMUM_ARTICLE_NAME_LENGTH=50;
    public static final Integer MAXIMUM_ARTICLE_DESCRIPTION_LENGTH=90;
    public static final Integer MINIMUM_CATEGORIES_ASSOCIATED=1;
    public static final Integer MAXIMUM_CATEGORIES_ASSOCIATED=3;
    public static final String ARTICLE_NAME_ALREADY_EXISTS =
            "This article name already exists";
    public static final String ARTICLE_NAME_TOO_LONG =
            "The article name cannot be longer than"
                    +MAXIMUM_ARTICLE_NAME_LENGTH
                    +"characters";
    public static final String ARTICLE_NAME_CANNOT_BE_EMPTY =
            "The article name cannot be empty";
    public static final String ARTICLE_DESCRIPTION_TOO_LONG =
            "The article description cannot be longer than"
                    +MAXIMUM_ARTICLE_DESCRIPTION_LENGTH
                    +"characters";
    public static final String ARTICLE_DESCRIPTION_CANNOT_BE_EMPTY =
            "The article description cannot be empty";

    public static final String MINIMUM_CATEGORY =
            "the number of categories associated with the article must be at least "
            +MINIMUM_CATEGORIES_ASSOCIATED;
    public static final String MAXIMUM_CATEGORY =
            "the number of categories associated with the article must be less than "
                    +MAXIMUM_CATEGORIES_ASSOCIATED;
}
