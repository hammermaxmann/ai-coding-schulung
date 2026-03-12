package com.workshop.foodfacts.client;

import com.workshop.foodfacts.model.Product;
import com.workshop.foodfacts.model.SearchResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Client fuer den Zugriff auf Lebensmitteldaten.
 *
 * Verwendet statische Beispieldaten anstelle der echten Open Food Facts API,
 * damit das Projekt offline und ohne externe Abhaengigkeiten funktioniert.
 */
public class OpenFoodFactsClient {

    private final Map<String, Product> products;

    public OpenFoodFactsClient() {
        this.products = Product.createSampleProducts();
    }

    /**
     * Ruft ein Produkt per Barcode ab.
     * @param barcode EAN/UPC Barcode (z.B. "3017620422003" fuer Nutella)
     * @return Product oder null wenn nicht gefunden
     */
    public Product getProductByBarcode(String barcode) {
        return products.get(barcode);
    }

    /**
     * Sucht Produkte anhand eines Suchbegriffs.
     * Durchsucht Produktname, Marke und Kategorien (case-insensitive).
     * @param query Suchbegriff (z.B. "Schokolade", "Haribo")
     * @param page Seitennummer (ab 1)
     * @param pageSize Anzahl Ergebnisse pro Seite
     * @return SearchResponse mit gefundenen Produkten
     */
    public SearchResponse searchProducts(String query, int page, int pageSize) {
        String lowerQuery = query.toLowerCase();

        List<Product> matches = new ArrayList<>();
        for (Product product : products.values()) {
            if (matchesQuery(product, lowerQuery)) {
                matches.add(product);
            }
        }

        int totalCount = matches.size();
        int fromIndex = Math.min((page - 1) * pageSize, totalCount);
        int toIndex = Math.min(fromIndex + pageSize, totalCount);
        List<Product> pageResults = matches.subList(fromIndex, toIndex);
        int pageCount = (int) Math.ceil((double) totalCount / pageSize);

        return new SearchResponse(totalCount, page, pageSize, pageCount, new ArrayList<>(pageResults));
    }

    private boolean matchesQuery(Product product, String lowerQuery) {
        return containsIgnoreCase(product.getProductName(), lowerQuery)
                || containsIgnoreCase(product.getBrands(), lowerQuery)
                || containsIgnoreCase(product.getCategories(), lowerQuery)
                || containsIgnoreCase(product.getProductNameDe(), lowerQuery);
    }

    private boolean containsIgnoreCase(String text, String query) {
        return text != null && text.toLowerCase().contains(query);
    }

    /**
     * Gibt alle verfuegbaren Produkte zurueck.
     */
    public Map<String, Product> getAllProducts() {
        return products;
    }
}
