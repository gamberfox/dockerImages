package com.emazon.stock_api_service.infrastructure.input.rest;

import com.emazon.stock_api_service.application.dto.ArticleRequest;
import com.emazon.stock_api_service.application.dto.ArticleResponse;
import com.emazon.stock_api_service.application.dto.BrandResponse;
import com.emazon.stock_api_service.application.handler.IArticleHandler;
import com.emazon.stock_api_service.domain.model.Article;
import com.emazon.stock_api_service.domain.usecase.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.emazon.stock_api_service.util.ArticleConstants.*;

@RestController
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleRestController {
    private final IArticleHandler articleHandler;

    @PostMapping("/")
    public ResponseEntity<Map<String, Object>> createArticle(@RequestBody ArticleRequest articleRequest) {
        articleHandler.createArticle(articleRequest);
        RestResponse response=new RestResponse(ARTICLE_CREATED, articleRequest);
        return new ResponseEntity<>(response.getResponse(), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<ArticleResponse> getArticleById(
            @RequestParam(defaultValue="0") Long id) {
        return ResponseEntity.ok(articleHandler.getArticleResponseById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<Page<ArticleResponse>> getArticles(
            @RequestParam(defaultValue="true") boolean ascendingOrder,
            @RequestParam(defaultValue = "article") String comparator) {
        List<ArticleResponse> articles = articleHandler.getArticles(ascendingOrder,comparator);
//        Pageable pageable = PageRequest.of(page, size);
        Pageable pageable = PageRequest.of(0, articles.size());
        return ResponseEntity.ok(new PageImpl<>(articles, pageable, articles.size()));
    }

//    @GetMapping("/a/{id}")
//    public ResponseEntity<String> test1(@PathVariable(name="id") Long id) {
//        return ResponseEntity.ok(
//                articleHandler.getArticleResponseById(id).getName()
//        +articleHandler.getArticleResponseById(id).getId()
//        +articleHandler.getArticleResponseById(id).getBrand().getName());
//    }

//    @GetMapping("/page")
//    public ResponseEntity<Page<ArticleResponse>> getArticles(
//            @RequestParam(defaultValue = "0") int page,//page you want to get
//            @RequestParam(defaultValue = "10") Long pageSize,
//            @RequestParam(defaultValue = "true") Boolean ascendingOrder,
//            @RequestParam(defaultValue = "article") String comparator) {
//        PageResponse<ArticleResponse> articleResponses =
//                 articleHandler.getArticleResponses(ascendingOrder,comparator,pageSize, (long)page);
//        Pageable pageable = PageRequest.of(page, Math.toIntExact(pageSize));
//        return ResponseEntity.ok(new PageImpl<>
//                (articleResponses.getContent()
//                        ,pageable, articleResponses.getTotalElements()));
//    }
}
