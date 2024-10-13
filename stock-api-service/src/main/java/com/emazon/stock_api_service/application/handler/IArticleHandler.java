package com.emazon.stock_api_service.application.handler;

import com.emazon.stock_api_service.application.dto.ArticleRequest;
import com.emazon.stock_api_service.application.dto.ArticleResponse;
import com.emazon.stock_api_service.domain.usecase.PageResponse;

import java.util.List;

public interface IArticleHandler {
    void createArticle(ArticleRequest articleRequest);
    ArticleResponse getArticleResponseById(Long id);
    List<ArticleResponse> getArticles(Boolean ascendingOrder, String comparator);
    PageResponse<ArticleResponse> getArticlePage(Boolean ascendingOrder, String comparator,Long pageSize,Long pageNumber);
    void updateArticle(ArticleRequest articleRequest);
}
