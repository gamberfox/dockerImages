package com.emazon.stock_api_service.infrastructure.output.jpa.repository;

import com.emazon.stock_api_service.infrastructure.output.jpa.entity.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IArticleRepository extends JpaRepository<ArticleEntity, Long> {
    Optional<ArticleEntity> findByName(String brandName);
    Optional<ArticleEntity> findById(Long id);
}
