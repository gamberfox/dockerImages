package com.emazon.stock_api_service.domain.api;

import com.emazon.stock_api_service.domain.model.Article;
import com.emazon.stock_api_service.domain.usecase.PageResponse;

import java.util.List;


public interface IArticleServicePort {
    void validate(Article article);
    void createArticle(Article article);
    Article getArticleById(Long id);
    PageResponse<Article> getArticles(Boolean ascendingOrder, String comparor, Long pageSize, Long pageNumber);
    void validateGetArticlesRequestParam(Long pageSize, Long pageNumber,List<Article> articles);
}
