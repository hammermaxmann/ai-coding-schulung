package com.workshop.foodfacts.dto;

/**
 * Zusammenfassung der Naehrwertdaten eines Produkts.
 */
public class NutritionSummaryDto {

    private String barcode;
    private String productName;
    private Double caloriesPerServing;
    private Double fat100g;
    private Double saturatedFat100g;
    private Double carbohydrates100g;
    private Double sugar100g;
    private Double protein100g;
    private Double salt100g;
    private Double fiber100g;
    private String nutriScore;
    private String healthRating;
    private int novaGroup;
    private String novaDescription;

    public String getBarcode() { return barcode; }
    public void setBarcode(String barcode) { this.barcode = barcode; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public Double getCaloriesPerServing() { return caloriesPerServing; }
    public void setCaloriesPerServing(Double caloriesPerServing) { this.caloriesPerServing = caloriesPerServing; }

    public Double getFat100g() { return fat100g; }
    public void setFat100g(Double fat100g) { this.fat100g = fat100g; }

    public Double getSaturatedFat100g() { return saturatedFat100g; }
    public void setSaturatedFat100g(Double saturatedFat100g) { this.saturatedFat100g = saturatedFat100g; }

    public Double getCarbohydrates100g() { return carbohydrates100g; }
    public void setCarbohydrates100g(Double carbohydrates100g) { this.carbohydrates100g = carbohydrates100g; }

    public Double getSugar100g() { return sugar100g; }
    public void setSugar100g(Double sugar100g) { this.sugar100g = sugar100g; }

    public Double getProtein100g() { return protein100g; }
    public void setProtein100g(Double protein100g) { this.protein100g = protein100g; }

    public Double getSalt100g() { return salt100g; }
    public void setSalt100g(Double salt100g) { this.salt100g = salt100g; }

    public Double getFiber100g() { return fiber100g; }
    public void setFiber100g(Double fiber100g) { this.fiber100g = fiber100g; }

    public String getNutriScore() { return nutriScore; }
    public void setNutriScore(String nutriScore) { this.nutriScore = nutriScore; }

    public String getHealthRating() { return healthRating; }
    public void setHealthRating(String healthRating) { this.healthRating = healthRating; }

    public int getNovaGroup() { return novaGroup; }
    public void setNovaGroup(int novaGroup) { this.novaGroup = novaGroup; }

    public String getNovaDescription() { return novaDescription; }
    public void setNovaDescription(String novaDescription) { this.novaDescription = novaDescription; }

    @Override
    public String toString() {
        return "NutritionSummaryDto{" +
                "productName='" + productName + '\'' +
                ", calories=" + caloriesPerServing +
                ", nutriScore='" + nutriScore + '\'' +
                ", healthRating='" + healthRating + '\'' +
                '}';
    }
}
