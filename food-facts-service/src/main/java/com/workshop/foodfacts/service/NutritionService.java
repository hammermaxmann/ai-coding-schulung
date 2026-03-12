package com.workshop.foodfacts.service;

import com.workshop.foodfacts.dto.NutritionSummaryDto;
import com.workshop.foodfacts.model.Product;

/**
 * Service fuer Naehrwert-Analysen und Bewertungen.
 */
public class NutritionService {

    /**
     * Erstellt eine Naehrwert-Zusammenfassung fuer ein Produkt.
     */
    public NutritionSummaryDto getNutritionSummary(Product product) {
        if (product == null) {
            return null;
        }

        NutritionSummaryDto summary = new NutritionSummaryDto();
        summary.setBarcode(product.getBarcode());
        summary.setProductName(product.getProductName());
        summary.setCaloriesPerServing(product.getEnergyKcal100g() != null ? product.getEnergyKcal100g() : 0.0);
        summary.setFat100g(product.getFat100g() != null ? product.getFat100g() : 0.0);
        summary.setSaturatedFat100g(product.getSaturatedFat100g() != null ? product.getSaturatedFat100g() : 0.0);
        summary.setCarbohydrates100g(product.getCarbohydrates100g() != null ? product.getCarbohydrates100g() : 0.0);
        summary.setSugar100g(product.getSugars100g() != null ? product.getSugars100g() : 0.0);
        summary.setProtein100g(product.getProteins100g() != null ? product.getProteins100g() : 0.0);
        summary.setSalt100g(product.getSalt100g() != null ? product.getSalt100g() : 0.0);
        summary.setFiber100g(product.getFiber100g() != null ? product.getFiber100g() : 0.0);

        // Nutri-Score uebernehmen oder berechnen
        if (product.getNutriScoreGrade() != null) {
            summary.setNutriScore(product.getNutriScoreGrade().toUpperCase());
        } else {
            summary.setNutriScore(calculateApproximateNutriScore(product));
        }

        // Gesundheitsbewertung
        summary.setHealthRating(calculateHealthRating(product));

        // NOVA-Gruppe
        summary.setNovaGroup(product.getNovaGroup() != null ? product.getNovaGroup() : 0);
        summary.setNovaDescription(getNovaDescription(product.getNovaGroup()));

        return summary;
    }

    /**
     * Berechnet eine ungefaehre Gesundheitsbewertung basierend auf den Naehrwerten.
     * Skala: "Sehr gut", "Gut", "Maessig", "Schlecht", "Sehr schlecht"
     */
    public String calculateHealthRating(Product product) {
        if (product == null) return "Unbekannt";

        int score = 0;

        // Zucker bewerten
        Double sugar = product.getSugars100g();
        if (sugar != null) {
            if (sugar < 5) score += 2;
            else if (sugar < 12.5) score += 1;
            else if (sugar < 25) score -= 1;
            else score -= 2;
        }

        // Fett bewerten
        Double fat = product.getFat100g();
        if (fat != null) {
            if (fat < 3) score += 2;
            else if (fat < 10) score += 1;
            else if (fat < 20) score -= 1;
            else score -= 2;
        }

        // Salz bewerten
        Double salt = product.getSalt100g();
        if (salt != null) {
            if (salt < 0.3) score += 2;
            else if (salt < 1.5) score += 1;
            else if (salt < 3) score -= 1;
            else score -= 2;
        }

        // Protein bewerten (positiv)
        Double protein = product.getProteins100g();
        if (protein != null) {
            if (protein > 10) score += 2;
            else if (protein > 5) score += 1;
        }

        // Ballaststoffe bewerten (positiv)
        Double fiber = product.getFiber100g();
        if (fiber != null) {
            if (fiber > 5) score += 2;
            else if (fiber > 2) score += 1;
        }

        // Score in Bewertung umrechnen
        if (score >= 6) return "Sehr gut";
        if (score >= 3) return "Gut";
        if (score >= 0) return "Maessig";
        if (score >= -3) return "Schlecht";
        return "Sehr schlecht";
    }

    /**
     * Berechnet einen ungefaehren Nutri-Score falls keiner vorhanden ist.
     */
    String calculateApproximateNutriScore(Product product) {
        Double energy = product.getEnergyKcal100g();
        Double sugar = product.getSugars100g();
        Double fat = product.getSaturatedFat100g();
        Double salt = product.getSalt100g();

        if (energy == null && sugar == null && fat == null && salt == null) {
            return "?";
        }

        int negativePoints = 0;
        if (energy != null) {
            if (energy > 800) negativePoints += 10;
            else if (energy > 600) negativePoints += 7;
            else if (energy > 400) negativePoints += 5;
            else if (energy > 200) negativePoints += 3;
            else negativePoints += 1;
        }
        if (sugar != null) {
            if (sugar > 45) negativePoints += 10;
            else if (sugar > 27) negativePoints += 7;
            else if (sugar > 13.5) negativePoints += 5;
            else if (sugar > 4.5) negativePoints += 3;
        }
        if (fat != null) {
            if (fat > 10) negativePoints += 10;
            else if (fat > 6) negativePoints += 7;
            else if (fat > 3) negativePoints += 5;
            else if (fat > 1) negativePoints += 3;
        }
        if (salt != null) {
            if (salt > 3.6) negativePoints += 10;
            else if (salt > 2.4) negativePoints += 7;
            else if (salt > 1.2) negativePoints += 5;
            else if (salt > 0.36) negativePoints += 3;
        }

        if (negativePoints <= 5) return "A";
        if (negativePoints <= 10) return "B";
        if (negativePoints <= 18) return "C";
        if (negativePoints <= 25) return "D";
        return "E";
    }

    /**
     * Gibt die Beschreibung einer NOVA-Gruppe zurueck.
     */
    String getNovaDescription(Integer novaGroup) {
        if (novaGroup == null) return "Unbekannt";
        return switch (novaGroup) {
            case 1 -> "Unverarbeitete oder minimal verarbeitete Lebensmittel";
            case 2 -> "Verarbeitete kulinearische Zutaten";
            case 3 -> "Verarbeitete Lebensmittel";
            case 4 -> "Hochverarbeitete Lebensmittel";
            default -> "Unbekannt";
        };
    }
}
