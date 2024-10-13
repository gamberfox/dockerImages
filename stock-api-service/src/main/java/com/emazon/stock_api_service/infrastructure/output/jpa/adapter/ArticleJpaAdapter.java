package com.emazon.stock_api_service.infrastructure.output.jpa.adapter;

import com.emazon.stock_api_service.domain.model.Article;
import com.emazon.stock_api_service.domain.spi.IArticlePersistencePort;
import com.emazon.stock_api_service.infrastructure.output.jpa.entity.ArticleEntity;
import com.emazon.stock_api_service.infrastructure.output.jpa.mapper.IArticleEntityMapper;
import com.emazon.stock_api_service.infrastructure.output.jpa.mapper.ICategoryEntityMapper;
import com.emazon.stock_api_service.infrastructure.output.jpa.repository.IArticleRepository;
import com.emazon.stock_api_service.infrastructure.output.jpa.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ArticleJpaAdapter implements IArticlePersistencePort {
    private final IArticleRepository articleRepository;
    private final IArticleEntityMapper articleEntityMapper;

    @Override
    public void createArticle(Article article) {
        ArticleEntity articleEntity = articleEntityMapper.toArticleEntity(article);
        articleRepository.save(articleEntity);
    }

    @Override
    public Article getArticleById(Long id) {
        //I called .isPresent() before getting to this line, in another class
        ArticleEntity articleEntity= articleRepository.findById(id).get();
        return articleEntityMapper.toArticle(articleEntity);
    }

    @Override
    public List<Article> getArticles() {
        List<ArticleEntity> articleEntityList= articleRepository.findAll();
        return articleEntityMapper.toArticles(articleEntityList);
    }

    @Override
    public Boolean articleIdExists(Long id) {
        return articleRepository.findById(id).isPresent();
    }

    @Override
    public Boolean articleNameExists(String name) {
        return articleRepository.findByName(name).isPresent();
    }
}
