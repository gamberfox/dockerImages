package com.emazon.stock_api_service.domain;

import com.emazon.stock_api_service.domain.exception.ArticleUseCaseException;
import com.emazon.stock_api_service.domain.exception.ResourceNotFoundException;
import com.emazon.stock_api_service.domain.model.Article;
import com.emazon.stock_api_service.domain.model.Brand;
import com.emazon.stock_api_service.domain.model.Category;
import com.emazon.stock_api_service.domain.spi.IArticlePersistencePort;
import com.emazon.stock_api_service.domain.spi.IBrandPersistencePort;
import com.emazon.stock_api_service.domain.spi.ICategoryPersistencePort;
import com.emazon.stock_api_service.domain.usecase.ArticleUseCase;
import com.emazon.stock_api_service.domain.usecase.PageResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.emazon.stock_api_service.util.ArticleConstants.*;
import static com.emazon.stock_api_service.util.BrandConstants.*;
import static com.emazon.stock_api_service.util.CategoryConstants.CATEGORY_NOT_FOUND;
import static com.emazon.stock_api_service.util.GenericConstants.*;
import static com.emazon.stock_api_service.util.GenericConstants.PARAMETER_NEGATIVE_PAGE_NUMBER_VALUE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

//@ExtendWith(MockitoExtension.class)
//@SpringBootTest//this is used for integration tests.
class ArticleUseCaseTest {

    @Mock
    private IArticlePersistencePort articlePersistencePort;
    @Mock
    private ICategoryPersistencePort categoryPersistencePort;
    @Mock
    private IBrandPersistencePort brandPersistencePort;

    @InjectMocks
    private ArticleUseCase articleUseCase;
    private Article article;
    private Brand brand;
    private Brand brand2;
    private Brand brand3;
    private Category category;
    private Category category2;
    private Category category3;
    private Category category4;
    private Category category5;
    private List<Category> categories;
    ArticleUseCaseException ex;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        brand=new Brand(1L,null,null);
        category=new Category(1L,null,null);
        article=new Article(1L,"article","description",2L,
                new BigDecimal("12.12"),brand,null);
        categories=new ArrayList<>();
        article.setBrand(brand);
        categories.add(category);
        article.setCategories(categories);
        when(articleUseCase.categoryIdExists(1L)).thenReturn(true);
        when(articleUseCase.brandIdExists(1L)).thenReturn(true);
    }
    @Test
    void testCreateArticle() {
        when(articlePersistencePort.articleIdExists(1L)).thenReturn(true);
        articleUseCase.createArticle(article);
        verify(articlePersistencePort, times(1)).createArticle(article);
    }
    @Test
    void testGetArticleById(){
        when(articlePersistencePort.getArticleById(1L)).thenReturn(article);
        when(articlePersistencePort.articleIdExists(1L)).thenReturn(true);
        when(articlePersistencePort.articleIdExists(2L)).thenReturn(true);

        assertEquals(article, articleUseCase.getArticleById(1L));

        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class
                , () -> articleUseCase.getArticleById(5L));
        assertEquals(ARTICLE_NOT_FOUND
                ,exception.getMessage());
    }
    @Test
    void testValidateBrandNotNull() {
        article.setBrand(null);
        ex = assertThrows(ArticleUseCaseException.class
                , () -> articleUseCase.validate(article));

        assertEquals(BRAND_OBLIGATORY
                ,ex.getErrorList().get(0));
        assertEquals(1, ex.getErrorList().size());
    }
    @Test
    void testValidateBrandExists() {
        when(articleUseCase.brandIdExists(1L)).thenReturn(false);
        ex = assertThrows(ArticleUseCaseException.class
                , () -> articleUseCase.validate(article));

        assertEquals(BRAND_NOT_FOUND
                ,ex.getErrorList().get(0));
        assertEquals(1, ex.getErrorList().size());
    }

    @Test
    void testValidateCategoriesNotNull() {
        article.setCategories(null);

        ex = assertThrows(ArticleUseCaseException.class
                , () -> articleUseCase.validate(article));

        assertEquals(MINIMUM_CATEGORY
                ,ex.getErrorList().get(0));
        assertEquals(1, ex.getErrorList().size());
    }

    @Test
    void testValidateCategoriesRepeated() {
        categories.add(category);

        ex = assertThrows(ArticleUseCaseException.class
                , () -> articleUseCase.validate(article));


        assertEquals(CATEGORY_REPEATED
                ,ex.getErrorList().get(0));
        assertEquals(1,ex.getErrorList().size());
    }

    @Test
    void testValidateCategoriesExist() {
        when(articleUseCase.categoryIdExists(2L)).thenReturn(false);
        category2=new Category(2L,null,null);
        categories.add(category2);

        ex = assertThrows(ArticleUseCaseException.class
                , () -> articleUseCase.validate(article));

        assertEquals(CATEGORY_NOT_FOUND
                ,ex.getErrorList().get(0));
        assertEquals(1, ex.getErrorList().size());
    }

    @Test
    void testValidateCategoriesMaximumCategoriesAssociated() {
        when(articleUseCase.categoryIdExists(2L)).thenReturn(true);
        when(articleUseCase.categoryIdExists(3L)).thenReturn(true);
        when(articleUseCase.categoryIdExists(4L)).thenReturn(true);
        when(articleUseCase.categoryIdExists(5L)).thenReturn(true);
        category2=new Category(2L,null,null);
        category3=new Category(3L,null,null);
        category4=new Category(4L,null,null);
        category5=new Category(5L,null,null);
        categories.add(category2);
        categories.add(category3);
        categories.add(category4);
        categories.add(category5);

        ex = assertThrows(ArticleUseCaseException.class
                , () -> articleUseCase.validate(article));

        assertEquals(MAXIMUM_CATEGORY
                ,ex.getErrorList().get(0));
        assertEquals(1, ex.getErrorList().size());
    }

    @Test
    void testValidateCategoriesMinimumCategoriesAssociated() {
        categories=new ArrayList<>();
        article.setCategories(categories);
        ex = assertThrows(ArticleUseCaseException.class
                , () -> articleUseCase.validate(article));

        assertEquals(MINIMUM_CATEGORY
                ,ex.getErrorList().get(0));
        assertEquals(1, ex.getErrorList().size());
    }

    @Test
    void testGetArticles(){
        category.setName("catName");
        category.setDescription("catDesc");
        Article article2=new Article(2L,"article2","description",2L,
                new BigDecimal("12.12"),brand,null);
        Article article3=new Article(3L,"article3","description",2L,
                new BigDecimal("12.12"),brand,null);
        List<Article> articles=new ArrayList<>();
        articles.add(article);
        articles.add(article2);
        articles.add(article3);
        when(articlePersistencePort.getArticles()).thenReturn(articles);
        assertEquals(3,articlePersistencePort.getArticles().size());
        PageResponse<Article> response=
                articleUseCase.getArticlePage
                        (true,"article",10L,0L);
        assertEquals(3,response.getContent().size());
        response=
                articleUseCase.getArticlePage
                        (true,"article",1L,0L);
        assertEquals(1,response.getContent().size());

        response=
                articleUseCase.getArticlePage
                        (true,"article",2L,0L);
        assertEquals(2,response.getContent().size());

    }

    @Test
    void testValidateGetArticlesRequestParam(){
        category.setName("catName");
        category.setDescription("catDesc");
        Article article2=new Article(2L,"article2","description",2L,
                new BigDecimal("12.12"),brand,null);
        Article article3=new Article(3L,"article3","description",2L,
                new BigDecimal("12.12"),brand,null);
        List<Article> articles=Arrays.asList(article,article2,article3);
        when(articlePersistencePort.getArticles()).thenReturn(articles);

        ex = assertThrows(ArticleUseCaseException.class
                , () -> articleUseCase
                        .validateGetArticlesRequestParam
                                (-2L,0L,articles));

        assertEquals(PARAMETER_PAGE_SIZE_VALUE
                ,ex.getErrorList().get(0));
        assertEquals(1L, ex.getErrorList().size());

        ex = assertThrows(ArticleUseCaseException.class
                , () -> articleUseCase
                        .validateGetArticlesRequestParam
                                (2L,-1L,articles));

        assertEquals(PARAMETER_NEGATIVE_PAGE_NUMBER_VALUE
                ,ex.getErrorList().get(0));
        assertEquals(1L, ex.getErrorList().size());

        ex = assertThrows(ArticleUseCaseException.class
                , () -> articleUseCase
                        .validateGetArticlesRequestParam
                                (2L,10L,articles));

        assertEquals(PARAMETER_PAGE_NUMBER
                ,ex.getErrorList().get(0));
        assertEquals(1L, ex.getErrorList().size());
    }

    @Test
    void testValidateGetArticlesByArticleName() {
        category.setName("catName");
        category.setDescription("catDesc");

        Article article2=new Article(2L,"article2","description",2L,
                new BigDecimal("12.12"),brand,null);
        category2=new Category(2L,"catName2","description");
        List<Category> categories2=new ArrayList<>();
        categories2.add(category2);
        article2.setCategories(categories2);

        Article article3=new Article(3L,"article3","description",2L,
                new BigDecimal("12.12"),brand,null);
        category3=new Category(3L,"catName3","description");
        List<Category> categories3=new ArrayList<>();
        categories3.add(category3);
        article3.setCategories(categories3);

        //articles organized ascendingly by article name
        List<Article> organizedArticles = Arrays.asList(article,article2,article3);
        List<Article> articlesToReturn = Arrays.asList(article3,article,article2);
        when(articlePersistencePort.getArticles()).thenReturn(articlesToReturn);
        List<Article> articles=articlePersistencePort.getArticles();
        articleUseCase.sortArticles(articles,true,"article");

        assertEquals(organizedArticles,articles);

        Collections.reverse(organizedArticles);
        articleUseCase.sortArticles(articles,false,"article");
        assertEquals(organizedArticles,articles);
    }
    @Test
    void testValidateGetArticlesByCategoryName() {
        category.setName("catName");
        category.setDescription("catDesc");

        Article article2=new Article(2L,"article2","description",2L,
                new BigDecimal("12.12"),brand,null);
        category2=new Category(2L,"catName2","description");
        List<Category> categories2=new ArrayList<>();
        categories2.add(category2);
        article2.setCategories(categories2);

        Article article3=new Article(3L,"article3","description",2L,
                new BigDecimal("12.12"),brand,null);
        category3=new Category(3L,"catName3","description");
        List<Category> categories3=new ArrayList<>();
        categories3.add(category3);
        article3.setCategories(categories3);

        //articles organized ascendingly by article name
        List<Article> organizedArticles = Arrays.asList(article,article2,article3);
        List<Article> articles = Arrays.asList(article3,article,article2);
        articleUseCase.sortArticles(articles,true,"category");

        assertEquals(organizedArticles,articles);

        Collections.reverse(organizedArticles);
        articleUseCase.sortArticles(articles,false,"category");
        assertEquals(organizedArticles,articles);
    }

    @Test
    void testValidateGetArticlesByBrandName() {
        brand=new Brand(1L,"brandName","description");
        article.setBrand(brand);

        brand2=new Brand(2L,"brandName2","description");
        Article article2=new Article(2L,"article2","description",2L,
                new BigDecimal("12.12"),brand2,null);
        List<Category> categories2=new ArrayList<>();
        categories2.add(new Category(2L,"catName2","description"));
        article2.setCategories(categories2);

        brand3=new Brand(3L,"brandName3","description");
        Article article3=new Article(3L,"article3","description",2L,
                new BigDecimal("12.12"),brand3,null);
        List<Category> categories3=new ArrayList<>();
        categories3.add(new Category(3L,"catName3","description"));
        article3.setCategories(categories3);

        //articles organized ascendingly by brand name
        List<Article> organizedArticles = Arrays.asList(article,article2,article3);
        List<Article> articles = Arrays.asList(article3,article,article2);
        articleUseCase.sortArticles(articles,true,"brand");

        assertEquals(organizedArticles,articles);

        Collections.reverse(organizedArticles);
        articleUseCase.sortArticles(articles,false,"brand");
        assertEquals(organizedArticles,articles);
    }
}
