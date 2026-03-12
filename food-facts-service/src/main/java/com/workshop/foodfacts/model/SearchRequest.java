package com.workshop.foodfacts.model;

/**
 * Suchparameter fuer die Produktsuche.
 */
public class SearchRequest {

    private String query;
    private int page = 1;
    private int pageSize = 20;

    public SearchRequest() {}

    public SearchRequest(String query) {
        this.query = query;
    }

    public SearchRequest(String query, int page, int pageSize) {
        this.query = query;
        this.page = page;
        this.pageSize = pageSize;
    }

    public String getQuery() { return query; }
    public void setQuery(String query) { this.query = query; }

    public int getPage() { return page; }
    public void setPage(int page) { this.page = page; }

    public int getPageSize() { return pageSize; }
    public void setPageSize(int pageSize) { this.pageSize = pageSize; }

    @Override
    public String toString() {
        return "SearchRequest{query='" + query + "', page=" + page + ", pageSize=" + pageSize + '}';
    }
}
