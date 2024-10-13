package com.emazon.stock_api_service.application.handler;

import com.emazon.stock_api_service.application.dto.ArticleRequest;
import com.emazon.stock_api_service.application.dto.ArticleResponse;
import com.emazon.stock_api_service.application.mapper.IArticleRequestMapper;
import com.emazon.stock_api_service.application.mapper.IArticleResponseMapper;
import com.emazon.stock_api_service.domain.api.IArticleServicePort;
import com.emazon.stock_api_service.domain.model.Article;
import com.emazon.stock_api_service.domain.usecase.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
public class ArticleHandler implements IArticleHandler {
    private final IArticleServicePort articleServicePort;
    private final IArticleRequestMapper articleRequestMapper;
    private final IArticleResponseMapper articleResponseMapper;

    @Override
    public void createArticle(ArticleRequest articleRequest) {
        Article article = articleRequestMapper.toArticle(articleRequest);
        articleServicePort.createArticle(article);
    }

    @Override
    public ArticleResponse getArticleResponseById(Long id) {
        Article article= articleServicePort.getArticleById(id);
        return articleResponseMapper.toArticleResponse(article);
    }

    @Override
    public PageResponse<ArticleResponse> getArticleResponses(Boolean ascendingOrder, String comparator,Long pageSize,Long pageNumber) {
        PageResponse<Article> articles = articleServicePort.getArticles(ascendingOrder,comparator,pageSize,pageNumber);
        List<ArticleResponse> articleResponses=new ArrayList<>();
        for(Article article:articles.getContent()){
            articleResponses.add(articleResponseMapper.toArticleResponse(article));
        }
        return new PageResponse<>(
                articleResponses,
                articles.getTotalPages(),
                articles.getTotalElements(),
                articles.getPageSize(),
                articles.getCurrentPage()
        );
    }
}
