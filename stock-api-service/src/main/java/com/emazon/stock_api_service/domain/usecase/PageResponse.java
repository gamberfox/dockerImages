package com.emazon.stock_api_service.domain.usecase;

import java.util.List;

public class PageResponse<T>{
    private Long totalPages;
    private Long totalElements;
    private Long pageSize;
    private Long currentPage;
    private Long previousPage;
    private Long nextPage;
    private List<T> content;
    public PageResponse(List<T> content,Long pageSize) {
        this.totalPages = Long.valueOf((content.size()/pageSize)+1);
        this.totalElements = Long.valueOf(content.size());
        this.pageSize = pageSize;
        this.currentPage = 0L;
        this.previousPage = this.totalPages-1L;
        this.nextPage = (this.totalPages==1)?0L:this.currentPage+1L;
        this.content = content;
    }
    public PageResponse(List<T> content,Long totalPages, Long totalElements, Long pageSize, Long currentPage) {
        this.content = content;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
        this.pageSize = pageSize;
        this.currentPage = currentPage;
        this.previousPage = (this.totalPages==1 || this.currentPage==0)?(this.totalPages-1):this.currentPage-1L;
        this.nextPage = (this.currentPage==this.totalPages-1)?0L:this.currentPage+1L;
    }
    public void setContent(List<T> content) {
        this.content = content;
    }
    public List<T> getContent() {
        return content;
    }
    public Long getTotalPages() {
        return totalPages;
    }
    public Long getTotalElements() {
        return totalElements;
    }
    public Long getPageSize() {
        return pageSize;
    }
    public Long getCurrentPage() {
        return currentPage;
    }
    public Long getPreviousPage() {
        return previousPage;
    }
    public Long getNextPage() {
        return nextPage;
    }
}
