package com.workshop.foodfacts.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * DTO fuer Produktdetails.
 *
 * HINWEIS: Diese Klasse ist nahezu identisch mit Product.java!
 * Das ist ein typisches Beispiel fuer Code-Duplikation in gewachsenen Projekten.
 * Urspruenglich sollte sie eine "abgespeckte" Variante fuer API-Responses sein,
 * aber ueber die Zeit wurden immer mehr Felder hinzugefuegt.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductDetailDto {

    @JsonProperty("code")
    private String barcode;

    @JsonProperty("product_name")
    private String productName;

    @JsonProperty("brands")
    private String brands;

    @JsonProperty("quantity")
    private String quantity;

    @JsonProperty("categories")
    private String categories;

    @JsonProperty("labels")
    private String labels;

    @JsonProperty("labels_tags")
    private List<String> labelsTags;

    // Naehrwerte - gleiche Felder wie in Product.java
    private Double energyKcal100g;
    private Double fat100g;
    private Double saturatedFat100g;
    private Double carbohydrates100g;
    private Double sugars100g;
    private Double proteins100g;
    private Double salt100g;
    private Double fiber100g;

    // Bewertungen
    private String nutriScoreGrade;
    private Integer novaGroup;
    private String ecoScoreGrade;

    // Allergene
    private String allergens;
    private List<String> allergensTags;
    private String ingredientsText;

    // Bilder
    private String imageUrl;
    private String imageFrontUrl;

    // === Getter & Setter ===

    public String getBarcode() { return barcode; }
    public void setBarcode(String barcode) { this.barcode = barcode; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public String getBrands() { return brands; }
    public void setBrands(String brands) { this.brands = brands; }

    public String getQuantity() { return quantity; }
    public void setQuantity(String quantity) { this.quantity = quantity; }

    public String getCategories() { return categories; }
    public void setCategories(String categories) { this.categories = categories; }

    public String getLabels() { return labels; }
    public void setLabels(String labels) { this.labels = labels; }

    public List<String> getLabelsTags() { return labelsTags; }
    public void setLabelsTags(List<String> labelsTags) { this.labelsTags = labelsTags; }

    public Double getEnergyKcal100g() { return energyKcal100g; }
    public void setEnergyKcal100g(Double energyKcal100g) { this.energyKcal100g = energyKcal100g; }

    public Double getFat100g() { return fat100g; }
    public void setFat100g(Double fat100g) { this.fat100g = fat100g; }

    public Double getSaturatedFat100g() { return saturatedFat100g; }
    public void setSaturatedFat100g(Double saturatedFat100g) { this.saturatedFat100g = saturatedFat100g; }

    public Double getCarbohydrates100g() { return carbohydrates100g; }
    public void setCarbohydrates100g(Double carbohydrates100g) { this.carbohydrates100g = carbohydrates100g; }

    public Double getSugars100g() { return sugars100g; }
    public void setSugars100g(Double sugars100g) { this.sugars100g = sugars100g; }

    public Double getProteins100g() { return proteins100g; }
    public void setProteins100g(Double proteins100g) { this.proteins100g = proteins100g; }

    public Double getSalt100g() { return salt100g; }
    public void setSalt100g(Double salt100g) { this.salt100g = salt100g; }

    public Double getFiber100g() { return fiber100g; }
    public void setFiber100g(Double fiber100g) { this.fiber100g = fiber100g; }

    public String getNutriScoreGrade() { return nutriScoreGrade; }
    public void setNutriScoreGrade(String nutriScoreGrade) { this.nutriScoreGrade = nutriScoreGrade; }

    public Integer getNovaGroup() { return novaGroup; }
    public void setNovaGroup(Integer novaGroup) { this.novaGroup = novaGroup; }

    public String getEcoScoreGrade() { return ecoScoreGrade; }
    public void setEcoScoreGrade(String ecoScoreGrade) { this.ecoScoreGrade = ecoScoreGrade; }

    public String getAllergens() { return allergens; }
    public void setAllergens(String allergens) { this.allergens = allergens; }

    public List<String> getAllergensTags() { return allergensTags; }
    public void setAllergensTags(List<String> allergensTags) { this.allergensTags = allergensTags; }

    public String getIngredientsText() { return ingredientsText; }
    public void setIngredientsText(String ingredientsText) { this.ingredientsText = ingredientsText; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public String getImageFrontUrl() { return imageFrontUrl; }
    public void setImageFrontUrl(String imageFrontUrl) { this.imageFrontUrl = imageFrontUrl; }

    @Override
    public String toString() {
        return "ProductDetailDto{" +
                "barcode='" + barcode + '\'' +
                ", productName='" + productName + '\'' +
                ", brands='" + brands + '\'' +
                ", nutriScoreGrade='" + nutriScoreGrade + '\'' +
                '}';
    }
}
