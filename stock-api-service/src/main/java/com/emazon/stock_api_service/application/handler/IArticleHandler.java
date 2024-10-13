package com.emazon.stock_api_service.application.handler;

import com.emazon.stock_api_service.application.dto.ArticleRequest;
import com.emazon.stock_api_service.application.dto.ArticleResponse;
import com.emazon.stock_api_service.domain.usecase.PageResponse;

public interface IArticleHandler {
    void createArticle(ArticleRequest articleRequest);
    ArticleResponse getArticleResponseById(Long id);
    PageResponse<ArticleResponse> getArticleResponses(Boolean ascendingOrder, String comparator,Long pageSize,Long pageNumber);
}
