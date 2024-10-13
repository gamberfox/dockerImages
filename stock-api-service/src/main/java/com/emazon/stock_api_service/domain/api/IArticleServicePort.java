package com.emazon.stock_api_service.domain.api;

import com.emazon.stock_api_service.domain.model.Article;
import com.emazon.stock_api_service.domain.usecase.PageResponse;

import java.util.List;


public interface IArticleServicePort {
    void validate(Article article);
    void createArticle(Article article);
    Article getArticleById(Long id);
    List<Article> getArticles(Boolean ascendingOrder, String comparator);
    PageResponse<Article> getArticlePage(Boolean ascendingOrder, String comparator, Long pageSize, Long pageNumber);
    void validateGetArticlesRequestParam(Long pageSize, Long pageNumber,List<Article> articles);
}
