package com.workshop.foodfacts.model;

import java.util.List;

/**
 * Suchergebnis mit Paginierung.
 */
public class SearchResponse {

    private int totalCount;
    private int page;
    private int pageSize;
    private int pageCount;
    private List<Product> products;

    public SearchResponse() {}

    public SearchResponse(int totalCount, int page, int pageSize, int pageCount, List<Product> products) {
        this.totalCount = totalCount;
        this.page = page;
        this.pageSize = pageSize;
        this.pageCount = pageCount;
        this.products = products;
    }

    public int getTotalCount() { return totalCount; }
    public void setTotalCount(int totalCount) { this.totalCount = totalCount; }

    public int getPage() { return page; }
    public void setPage(int page) { this.page = page; }

    public int getPageSize() { return pageSize; }
    public void setPageSize(int pageSize) { this.pageSize = pageSize; }

    public int getPageCount() { return pageCount; }
    public void setPageCount(int pageCount) { this.pageCount = pageCount; }

    public List<Product> getProducts() { return products; }
    public void setProducts(List<Product> products) { this.products = products; }

    @Override
    public String toString() {
        return "SearchResponse{totalCount=" + totalCount + ", page=" + page +
                ", pageCount=" + pageCount + ", products=" + (products != null ? products.size() : 0) + '}';
    }
}
