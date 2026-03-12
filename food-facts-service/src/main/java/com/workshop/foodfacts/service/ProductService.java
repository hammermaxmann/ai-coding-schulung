package com.workshop.foodfacts.service;

import com.workshop.foodfacts.client.OpenFoodFactsClient;
import com.workshop.foodfacts.model.Product;
import com.workshop.foodfacts.model.SearchResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Service fuer den Zugriff auf Produktdaten.
 *
 * HINWEIS: Diese Klasse macht absichtlich zu viel - sie kombiniert
 * API-Aufrufe, Validierung und Caching in einer Klasse.
 * In einem realen Projekt wuerde man diese Verantwortlichkeiten trennen.
 */
public class ProductService {

    private final OpenFoodFactsClient client;
    private final Map<String, Product> cache = new HashMap<>();

    public ProductService() {
        this.client = new OpenFoodFactsClient();
    }

    public ProductService(OpenFoodFactsClient client) {
        this.client = client;
    }

    /**
     * Ruft ein Produkt per Barcode ab.
     * Ergebnisse werden im Cache gespeichert.
     */
    public Product getProductByBarcode(String barcode) {
        if (barcode == null || barcode.isBlank()) {
            System.err.println("Barcode darf nicht leer sein!");
            return null;
        }

        // Cache pruefen
        if (cache.containsKey(barcode)) {
            System.out.println("Cache-Treffer fuer Barcode: " + barcode);
            return cache.get(barcode);
        }

        // Produkt abrufen
        Product product = client.getProductByBarcode(barcode);
        if (product == null) {
            System.err.println("Produkt nicht gefunden: " + barcode);
            return null;
        }

        // Validierung
        if (product.getProductName() == null || product.getProductName().isBlank()) {
            System.err.println("Warnung: Produkt hat keinen Namen (Barcode: " + barcode + ")");
        }
        if (product.getEnergyKcal100g() != null && product.getEnergyKcal100g() < 0) {
            System.err.println("Warnung: Negative Kalorien bei Produkt " + barcode);
            product.setEnergyKcal100g(0.0);
        }
        if (product.getFat100g() != null && product.getFat100g() < 0) {
            product.setFat100g(0.0);
        }
        if (product.getSugars100g() != null && product.getSugars100g() < 0) {
            product.setSugars100g(0.0);
        }
        if (product.getSalt100g() != null && product.getSalt100g() < 0) {
            product.setSalt100g(0.0);
        }
        if (product.getProteins100g() != null && product.getProteins100g() < 0) {
            product.setProteins100g(0.0);
        }

        // In Cache speichern
        cache.put(barcode, product);

        return product;
    }

    /**
     * Sucht Produkte anhand eines Suchbegriffs.
     * HINWEIS: Dupliziert teilweise die Logik aus SearchService.
     */
    public SearchResponse searchProducts(String query, int page, int pageSize) {
        if (query == null || query.isBlank()) {
            return new SearchResponse(0, page, pageSize, 0, new ArrayList<>());
        }

        return client.searchProducts(query, page, pageSize);
    }

    /**
     * Leert den Produkt-Cache.
     */
    public void clearCache() {
        cache.clear();
    }

    /**
     * Gibt die Anzahl der gecachten Produkte zurueck.
     */
    public int getCacheSize() {
        return cache.size();
    }
}
