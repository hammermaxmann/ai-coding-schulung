package com.workshop.foodfacts.service;

import com.workshop.foodfacts.client.OpenFoodFactsClient;
import com.workshop.foodfacts.model.Product;
import com.workshop.foodfacts.model.SearchResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Service fuer die Produktsuche.
 *
 * HINWEIS: Diese Klasse dupliziert groesstenteils die Such-Logik
 * aus ProductService.searchProducts(). In einem realen Projekt
 * wuerde man die Suche in genau EINEM Service buendeln.
 */
public class SearchService {

    private final OpenFoodFactsClient client;

    public SearchService() {
        this.client = new OpenFoodFactsClient();
    }

    public SearchService(OpenFoodFactsClient client) {
        this.client = client;
    }

    /**
     * Sucht Produkte - ACHTUNG: Fast identisch mit ProductService.searchProducts()!
     */
    public SearchResponse search(String query, int page, int pageSize) {
        if (query == null || query.isBlank()) {
            return new SearchResponse(0, page, pageSize, 0, new ArrayList<>());
        }

        SearchResponse response = client.searchProducts(query, page, pageSize);
        if (response == null) {
            return null;
        }

        // Produkte ohne Namen herausfiltern (gleiche Logik wie in ProductService!)
        List<Product> filtered = new ArrayList<>();
        for (Product product : response.getProducts()) {
            if (product.getProductName() != null && !product.getProductName().isBlank()) {
                filtered.add(product);
            }
        }

        return new SearchResponse(filtered.size(), page, pageSize, response.getPageCount(), filtered);
    }

    /**
     * Sucht Produkte nach Kategorie.
     */
    public SearchResponse searchByCategory(String category, int page, int pageSize) {
        return search(category, page, pageSize);
    }
}
