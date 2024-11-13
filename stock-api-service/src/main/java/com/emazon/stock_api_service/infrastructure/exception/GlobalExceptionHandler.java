package com.emazon.stock_api_service.infrastructure.exception;

//import com.emazon.stock_api_service.domain.exception.ArticleUseCaseException;
//import com.emazon.stock_api_service.domain.exception.BrandUseCaseException;
import com.emazon.stock_api_service.domain.exception.ArticleUseCaseException;
import com.emazon.stock_api_service.domain.exception.BrandUseCaseException;
import com.emazon.stock_api_service.domain.exception.CategoryUseCaseException;
import com.emazon.stock_api_service.domain.exception.ResourceNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        return new ResponseEntity<>("test for validation", HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(MethodArgumentNotValidException ex) {

        return new ResponseEntity<>("test for handleIllegalArgument", HttpStatus.BAD_REQUEST);
    }
    //@ExceptionHandler(MethodArgumentNotValidException.class)
    //@ExceptionHandler(IllegalArgumentException.class)
//    @ExceptionHandler(InvalidDataAccessApiUsageException.class)
//    public ResponseEntity<String> handleInvalidData(MethodArgumentNotValidException ex) {
//
//        return new ResponseEntity<>("test for handleInvalidData", HttpStatus.BAD_REQUEST);
//    }
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleConstraintViolationException(MethodArgumentNotValidException ex) {

        return new ResponseEntity<>("test for ConstraintViolationException", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CategoryUseCaseException.class)
    public ResponseEntity<Map<String, Object>> handleCategoryUseCaseException(CategoryUseCaseException ex){
        JsonErrorResponse errorResponse =
                new JsonErrorResponse(HttpStatus.BAD_REQUEST.value(),
                        ex.getErrorList());
        return new ResponseEntity<>(errorResponse.getResponse(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleResourceNotFoundException(ResourceNotFoundException ex){
        List<String> errorList=new ArrayList<>();
        errorList.add(ex.getMessage());
//        JsonErrorResponse errorResponse =
//                new JsonErrorResponse(HttpStatus.NOT_FOUND.value(),
//                        ex.getMessage());
        JsonErrorResponse errorResponse =
                new JsonErrorResponse(HttpStatus.NOT_FOUND.value(),
                        errorList);
        return new ResponseEntity<>(errorResponse.getResponse(),HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(BrandUseCaseException.class)
    public ResponseEntity<Map<String, Object>> handleBrandUseCaseException(BrandUseCaseException ex){
        JsonErrorResponse errorResponse =
                new JsonErrorResponse(HttpStatus.BAD_REQUEST.value(),
                        ex.getErrorList());
        return new ResponseEntity<>(errorResponse.getResponse(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ArticleUseCaseException.class)
    public ResponseEntity<Map<String, Object>> handleArticleUseCaseException(ArticleUseCaseException ex){
        JsonErrorResponse errorResponse =
                new JsonErrorResponse(HttpStatus.BAD_REQUEST.value(),
                        ex.getErrorList());
        return new ResponseEntity<>(errorResponse.getResponse(), HttpStatus.BAD_REQUEST);
    }
}
