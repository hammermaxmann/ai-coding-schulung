package com.workshop.foodfacts;

import com.workshop.foodfacts.service.ProductService;
import com.workshop.foodfacts.service.NutritionService;
import com.workshop.foodfacts.model.Product;
import com.workshop.foodfacts.dto.NutritionSummaryDto;

/**
 * Hauptklasse fuer den Food Facts Service.
 * Demonstriert die Verwendung des Lebensmittel-Datenservice.
 */
public class FoodFactsApplication {

    public static void main(String[] args) {
        ProductService productService = new ProductService();
        NutritionService nutritionService = new NutritionService();

        System.out.println("=== Food Facts Service ===");
        System.out.println();

        // Produkt per Barcode abrufen (Nutella)
        String barcode = "3017620422003";
        System.out.println("Suche Produkt mit Barcode: " + barcode);

        Product product = productService.getProductByBarcode(barcode);
        if (product != null) {
            System.out.println("Gefunden: " + product.getProductName());
            System.out.println("Marke: " + product.getBrands());
            System.out.println("Nutri-Score: " + product.getNutriScoreGrade());
            System.out.println("Kalorien (100g): " + product.getEnergyKcal100g() + " kcal");
            System.out.println();

            // Naehrwert-Zusammenfassung
            NutritionSummaryDto summary = nutritionService.getNutritionSummary(product);
            System.out.println("--- Naehrwert-Zusammenfassung ---");
            System.out.println("Bewertung: " + summary.getHealthRating());
            System.out.println("Kalorien: " + summary.getCaloriesPerServing() + " kcal");
            System.out.println("Fett: " + summary.getFat100g() + "g");
            System.out.println("Zucker: " + summary.getSugar100g() + "g");
            System.out.println("Salz: " + summary.getSalt100g() + "g");
        } else {
            System.out.println("Produkt nicht gefunden!");
        }

        System.out.println();

        // Produktsuche
        System.out.println("=== Produktsuche: 'Schokolade' ===");
        var searchResults = productService.searchProducts("Schokolade", 1, 5);
        if (searchResults != null) {
            System.out.println("Treffer: " + searchResults.getTotalCount());
            for (Product p : searchResults.getProducts()) {
                System.out.println("  - " + p.getProductName() + " (" + p.getBrands() + ")");
            }
        }
    }
}
