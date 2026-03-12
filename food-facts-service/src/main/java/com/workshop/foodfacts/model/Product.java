package com.workshop.foodfacts.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Repraesentiert ein Lebensmittelprodukt aus der Open Food Facts Datenbank.
 *
 * HINWEIS: Diese Klasse enthaelt absichtlich viele Felder aus verschiedenen
 * Bereichen (Stammdaten, Naehrwerte, Allergene, Bewertungen, Bilder, Kategorien).
 * In einem realen Projekt wuerde man diese in separate Klassen aufteilen.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {

    // === Identifikation & Stammdaten ===
    @JsonProperty("code")
    private String barcode;

    @JsonProperty("product_name")
    private String productName;

    @JsonProperty("product_name_de")
    private String productNameDe;

    @JsonProperty("brands")
    private String brands;

    @JsonProperty("quantity")
    private String quantity;

    @JsonProperty("packaging")
    private String packaging;

    @JsonProperty("categories")
    private String categories;

    @JsonProperty("categories_tags")
    private List<String> categoriesTags;

    @JsonProperty("labels")
    private String labels;

    @JsonProperty("labels_tags")
    private List<String> labelsTags;

    @JsonProperty("stores")
    private String stores;

    @JsonProperty("countries")
    private String countries;

    @JsonProperty("countries_tags")
    private List<String> countriesTags;

    // === Naehrwerte (pro 100g) ===
    @JsonProperty("energy-kcal_100g")
    private Double energyKcal100g;

    @JsonProperty("energy_100g")
    private Double energy100g;

    @JsonProperty("fat_100g")
    private Double fat100g;

    @JsonProperty("saturated-fat_100g")
    private Double saturatedFat100g;

    @JsonProperty("carbohydrates_100g")
    private Double carbohydrates100g;

    @JsonProperty("sugars_100g")
    private Double sugars100g;

    @JsonProperty("fiber_100g")
    private Double fiber100g;

    @JsonProperty("proteins_100g")
    private Double proteins100g;

    @JsonProperty("salt_100g")
    private Double salt100g;

    @JsonProperty("sodium_100g")
    private Double sodium100g;

    // === Allergene & Zutaten ===
    @JsonProperty("allergens")
    private String allergens;

    @JsonProperty("allergens_tags")
    private List<String> allergensTags;

    @JsonProperty("traces")
    private String traces;

    @JsonProperty("traces_tags")
    private List<String> tracesTags;

    @JsonProperty("ingredients_text")
    private String ingredientsText;

    @JsonProperty("ingredients_text_de")
    private String ingredientsTextDe;

    // === Bewertungen & Scores ===
    @JsonProperty("nutriscore_grade")
    private String nutriScoreGrade;

    @JsonProperty("nutriscore_score")
    private Integer nutriScoreScore;

    @JsonProperty("nova_group")
    private Integer novaGroup;

    @JsonProperty("ecoscore_grade")
    private String ecoScoreGrade;

    @JsonProperty("ecoscore_score")
    private Integer ecoScoreScore;

    @JsonProperty("nutrient_levels")
    private NutrientLevels nutrientLevels;

    // === Bilder ===
    @JsonProperty("image_url")
    private String imageUrl;

    @JsonProperty("image_small_url")
    private String imageSmallUrl;

    @JsonProperty("image_front_url")
    private String imageFrontUrl;

    @JsonProperty("image_nutrition_url")
    private String imageNutritionUrl;

    @JsonProperty("image_ingredients_url")
    private String imageIngredientsUrl;

    // === Meta-Daten ===
    @JsonProperty("created_t")
    private Long createdTimestamp;

    @JsonProperty("last_modified_t")
    private Long lastModifiedTimestamp;

    @JsonProperty("creator")
    private String creator;

    @JsonProperty("editors_tags")
    private List<String> editorsTags;

    @JsonProperty("completeness")
    private Double completeness;

    // === Innere Klasse fuer Naehrstoff-Levels ===
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class NutrientLevels {
        @JsonProperty("fat")
        private String fat;

        @JsonProperty("salt")
        private String salt;

        @JsonProperty("sugars")
        private String sugars;

        @JsonProperty("saturated-fat")
        private String saturatedFat;

        public String getFat() { return fat; }
        public void setFat(String fat) { this.fat = fat; }
        public String getSalt() { return salt; }
        public void setSalt(String salt) { this.salt = salt; }
        public String getSugars() { return sugars; }
        public void setSugars(String sugars) { this.sugars = sugars; }
        public String getSaturatedFat() { return saturatedFat; }
        public void setSaturatedFat(String saturatedFat) { this.saturatedFat = saturatedFat; }

        @Override
        public String toString() {
            return "NutrientLevels{fat='" + fat + "', salt='" + salt +
                    "', sugars='" + sugars + "', saturatedFat='" + saturatedFat + "'}";
        }
    }

    // === Getter & Setter ===

    public String getBarcode() { return barcode; }
    public void setBarcode(String barcode) { this.barcode = barcode; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public String getProductNameDe() { return productNameDe; }
    public void setProductNameDe(String productNameDe) { this.productNameDe = productNameDe; }

    public String getBrands() { return brands; }
    public void setBrands(String brands) { this.brands = brands; }

    public String getQuantity() { return quantity; }
    public void setQuantity(String quantity) { this.quantity = quantity; }

    public String getPackaging() { return packaging; }
    public void setPackaging(String packaging) { this.packaging = packaging; }

    public String getCategories() { return categories; }
    public void setCategories(String categories) { this.categories = categories; }

    public List<String> getCategoriesTags() { return categoriesTags; }
    public void setCategoriesTags(List<String> categoriesTags) { this.categoriesTags = categoriesTags; }

    public String getLabels() { return labels; }
    public void setLabels(String labels) { this.labels = labels; }

    public List<String> getLabelsTags() { return labelsTags; }
    public void setLabelsTags(List<String> labelsTags) { this.labelsTags = labelsTags; }

    public String getStores() { return stores; }
    public void setStores(String stores) { this.stores = stores; }

    public String getCountries() { return countries; }
    public void setCountries(String countries) { this.countries = countries; }

    public List<String> getCountriesTags() { return countriesTags; }
    public void setCountriesTags(List<String> countriesTags) { this.countriesTags = countriesTags; }

    public Double getEnergyKcal100g() { return energyKcal100g; }
    public void setEnergyKcal100g(Double energyKcal100g) { this.energyKcal100g = energyKcal100g; }

    public Double getEnergy100g() { return energy100g; }
    public void setEnergy100g(Double energy100g) { this.energy100g = energy100g; }

    public Double getFat100g() { return fat100g; }
    public void setFat100g(Double fat100g) { this.fat100g = fat100g; }

    public Double getSaturatedFat100g() { return saturatedFat100g; }
    public void setSaturatedFat100g(Double saturatedFat100g) { this.saturatedFat100g = saturatedFat100g; }

    public Double getCarbohydrates100g() { return carbohydrates100g; }
    public void setCarbohydrates100g(Double carbohydrates100g) { this.carbohydrates100g = carbohydrates100g; }

    public Double getSugars100g() { return sugars100g; }
    public void setSugars100g(Double sugars100g) { this.sugars100g = sugars100g; }

    public Double getFiber100g() { return fiber100g; }
    public void setFiber100g(Double fiber100g) { this.fiber100g = fiber100g; }

    public Double getProteins100g() { return proteins100g; }
    public void setProteins100g(Double proteins100g) { this.proteins100g = proteins100g; }

    public Double getSalt100g() { return salt100g; }
    public void setSalt100g(Double salt100g) { this.salt100g = salt100g; }

    public Double getSodium100g() { return sodium100g; }
    public void setSodium100g(Double sodium100g) { this.sodium100g = sodium100g; }

    public String getAllergens() { return allergens; }
    public void setAllergens(String allergens) { this.allergens = allergens; }

    public List<String> getAllergensTags() { return allergensTags; }
    public void setAllergensTags(List<String> allergensTags) { this.allergensTags = allergensTags; }

    public String getTraces() { return traces; }
    public void setTraces(String traces) { this.traces = traces; }

    public List<String> getTracesTags() { return tracesTags; }
    public void setTracesTags(List<String> tracesTags) { this.tracesTags = tracesTags; }

    public String getIngredientsText() { return ingredientsText; }
    public void setIngredientsText(String ingredientsText) { this.ingredientsText = ingredientsText; }

    public String getIngredientsTextDe() { return ingredientsTextDe; }
    public void setIngredientsTextDe(String ingredientsTextDe) { this.ingredientsTextDe = ingredientsTextDe; }

    public String getNutriScoreGrade() { return nutriScoreGrade; }
    public void setNutriScoreGrade(String nutriScoreGrade) { this.nutriScoreGrade = nutriScoreGrade; }

    public Integer getNutriScoreScore() { return nutriScoreScore; }
    public void setNutriScoreScore(Integer nutriScoreScore) { this.nutriScoreScore = nutriScoreScore; }

    public Integer getNovaGroup() { return novaGroup; }
    public void setNovaGroup(Integer novaGroup) { this.novaGroup = novaGroup; }

    public String getEcoScoreGrade() { return ecoScoreGrade; }
    public void setEcoScoreGrade(String ecoScoreGrade) { this.ecoScoreGrade = ecoScoreGrade; }

    public Integer getEcoScoreScore() { return ecoScoreScore; }
    public void setEcoScoreScore(Integer ecoScoreScore) { this.ecoScoreScore = ecoScoreScore; }

    public NutrientLevels getNutrientLevels() { return nutrientLevels; }
    public void setNutrientLevels(NutrientLevels nutrientLevels) { this.nutrientLevels = nutrientLevels; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }

    public String getImageSmallUrl() { return imageSmallUrl; }
    public void setImageSmallUrl(String imageSmallUrl) { this.imageSmallUrl = imageSmallUrl; }

    public String getImageFrontUrl() { return imageFrontUrl; }
    public void setImageFrontUrl(String imageFrontUrl) { this.imageFrontUrl = imageFrontUrl; }

    public String getImageNutritionUrl() { return imageNutritionUrl; }
    public void setImageNutritionUrl(String imageNutritionUrl) { this.imageNutritionUrl = imageNutritionUrl; }

    public String getImageIngredientsUrl() { return imageIngredientsUrl; }
    public void setImageIngredientsUrl(String imageIngredientsUrl) { this.imageIngredientsUrl = imageIngredientsUrl; }

    public Long getCreatedTimestamp() { return createdTimestamp; }
    public void setCreatedTimestamp(Long createdTimestamp) { this.createdTimestamp = createdTimestamp; }

    public Long getLastModifiedTimestamp() { return lastModifiedTimestamp; }
    public void setLastModifiedTimestamp(Long lastModifiedTimestamp) { this.lastModifiedTimestamp = lastModifiedTimestamp; }

    public String getCreator() { return creator; }
    public void setCreator(String creator) { this.creator = creator; }

    public List<String> getEditorsTags() { return editorsTags; }
    public void setEditorsTags(List<String> editorsTags) { this.editorsTags = editorsTags; }

    public Double getCompleteness() { return completeness; }
    public void setCompleteness(Double completeness) { this.completeness = completeness; }

    @Override
    public String toString() {
        return "Product{" +
                "barcode='" + barcode + '\'' +
                ", productName='" + productName + '\'' +
                ", brands='" + brands + '\'' +
                ", nutriScoreGrade='" + nutriScoreGrade + '\'' +
                ", energyKcal100g=" + energyKcal100g +
                '}';
    }

    // === Statische Beispieldaten ===

    /**
     * Erstellt 5 bekannte deutsche Produkte mit realistischen Daten.
     * Wird vom MockClient verwendet, um die externe API zu ersetzen.
     */
    public static Map<String, Product> createSampleProducts() {
        Map<String, Product> products = new LinkedHashMap<>();

        // 1. Nutella
        Product nutella = new Product();
        nutella.setBarcode("3017620422003");
        nutella.setProductName("Nutella");
        nutella.setProductNameDe("Nutella Nuss-Nougat-Creme");
        nutella.setBrands("Ferrero");
        nutella.setQuantity("450 g");
        nutella.setPackaging("Glas");
        nutella.setCategories("Aufstriche, Schokoladenaufstriche, Haselnussaufstriche");
        nutella.setCategoriesTags(Arrays.asList("en:spreads", "en:chocolate-spreads", "en:hazelnut-spreads"));
        nutella.setLabels("Ohne Farbstoffe, Ohne kuenstliche Aromen");
        nutella.setLabelsTags(Arrays.asList("en:no-colorings", "en:no-artificial-flavors"));
        nutella.setStores("Rewe, Edeka, Aldi, Lidl");
        nutella.setCountries("Deutschland");
        nutella.setCountriesTags(Arrays.asList("en:germany"));
        nutella.setEnergyKcal100g(539.0);
        nutella.setEnergy100g(2255.0);
        nutella.setFat100g(30.9);
        nutella.setSaturatedFat100g(10.6);
        nutella.setCarbohydrates100g(57.5);
        nutella.setSugars100g(56.3);
        nutella.setFiber100g(0.0);
        nutella.setProteins100g(6.3);
        nutella.setSalt100g(0.107);
        nutella.setSodium100g(0.043);
        nutella.setAllergens("Haselnuesse, Milch, Soja");
        nutella.setAllergensTags(Arrays.asList("en:milk", "en:nuts", "en:soybeans"));
        nutella.setTraces("Weizen");
        nutella.setTracesTags(Arrays.asList("en:gluten"));
        nutella.setIngredientsText("Zucker, Palmoel, HASELNUESSE (13%), Magermilchpulver (8,7%), fettarmer Kakao (7,4%), Emulgator: Lecithine (Soja), Vanillin.");
        nutella.setIngredientsTextDe("Zucker, Palmoel, HASELNUESSE (13%), Magermilchpulver (8,7%), fettarmer Kakao (7,4%), Emulgator: Lecithine (Soja), Vanillin.");
        nutella.setNutriScoreGrade("e");
        nutella.setNutriScoreScore(26);
        nutella.setNovaGroup(4);
        nutella.setEcoScoreGrade("d");
        nutella.setEcoScoreScore(28);
        NutrientLevels nutellaLevels = new NutrientLevels();
        nutellaLevels.setFat("high");
        nutellaLevels.setSaturatedFat("high");
        nutellaLevels.setSugars("high");
        nutellaLevels.setSalt("low");
        nutella.setNutrientLevels(nutellaLevels);
        nutella.setImageUrl("https://images.openfoodfacts.org/images/products/301/762/042/2003/front_de.jpg");
        nutella.setCreatedTimestamp(1340209986L);
        nutella.setLastModifiedTimestamp(1710000000L);
        nutella.setCreator("workshop");
        nutella.setCompleteness(0.9);
        products.put(nutella.getBarcode(), nutella);

        // 2. Haribo Goldbaeren
        Product haribo = new Product();
        haribo.setBarcode("4001686301104");
        haribo.setProductName("Haribo Goldbaeren");
        haribo.setProductNameDe("Haribo Goldbaeren");
        haribo.setBrands("Haribo");
        haribo.setQuantity("200 g");
        haribo.setPackaging("Tuete");
        haribo.setCategories("Suessigkeiten, Fruchtgummis, Gummibaerchen");
        haribo.setCategoriesTags(Arrays.asList("en:sweets", "en:gummy-bears"));
        haribo.setStores("Rewe, Edeka, Aldi, Lidl, Rossmann");
        haribo.setCountries("Deutschland");
        haribo.setCountriesTags(Arrays.asList("en:germany"));
        haribo.setEnergyKcal100g(343.0);
        haribo.setEnergy100g(1436.0);
        haribo.setFat100g(0.5);
        haribo.setSaturatedFat100g(0.1);
        haribo.setCarbohydrates100g(77.0);
        haribo.setSugars100g(46.0);
        haribo.setFiber100g(0.0);
        haribo.setProteins100g(6.9);
        haribo.setSalt100g(0.02);
        haribo.setSodium100g(0.008);
        haribo.setAllergens("");
        haribo.setAllergensTags(Arrays.asList());
        haribo.setIngredientsText("Glukosesirup, Zucker, Gelatine, Saeuerungsmittel: Citronensaeure, Frucht- und Pflanzenkonzentrate, Aroma, Ueberzugsmittel: Bienenwachs, Carnaubawachs.");
        haribo.setIngredientsTextDe("Glukosesirup, Zucker, Gelatine, Saeuerungsmittel: Citronensaeure, Frucht- und Pflanzenkonzentrate, Aroma, Ueberzugsmittel: Bienenwachs, Carnaubawachs.");
        haribo.setNutriScoreGrade("d");
        haribo.setNutriScoreScore(18);
        haribo.setNovaGroup(4);
        haribo.setEcoScoreGrade("c");
        NutrientLevels hariboLevels = new NutrientLevels();
        hariboLevels.setFat("low");
        hariboLevels.setSaturatedFat("low");
        hariboLevels.setSugars("high");
        hariboLevels.setSalt("low");
        haribo.setNutrientLevels(hariboLevels);
        haribo.setCreatedTimestamp(1380000000L);
        haribo.setLastModifiedTimestamp(1710000000L);
        haribo.setCreator("workshop");
        haribo.setCompleteness(0.85);
        products.put(haribo.getBarcode(), haribo);

        // 3. Milka Alpenmilch Schokolade
        Product milka = new Product();
        milka.setBarcode("7622210100014");
        milka.setProductName("Milka Alpenmilch");
        milka.setProductNameDe("Milka Alpenmilch Schokolade");
        milka.setBrands("Milka");
        milka.setQuantity("100 g");
        milka.setPackaging("Plastik, Karton");
        milka.setCategories("Suessigkeiten, Schokolade, Milchschokolade");
        milka.setCategoriesTags(Arrays.asList("en:sweets", "en:chocolates", "en:milk-chocolates"));
        milka.setStores("Rewe, Edeka, Aldi, Lidl");
        milka.setCountries("Deutschland");
        milka.setCountriesTags(Arrays.asList("en:germany"));
        milka.setEnergyKcal100g(530.0);
        milka.setEnergy100g(2218.0);
        milka.setFat100g(29.5);
        milka.setSaturatedFat100g(18.0);
        milka.setCarbohydrates100g(59.0);
        milka.setSugars100g(58.0);
        milka.setFiber100g(1.0);
        milka.setProteins100g(6.3);
        milka.setSalt100g(0.3);
        milka.setSodium100g(0.12);
        milka.setAllergens("Milch, Haselnuesse, Soja");
        milka.setAllergensTags(Arrays.asList("en:milk", "en:nuts", "en:soybeans"));
        milka.setTraces("Weizen, Ei");
        milka.setTracesTags(Arrays.asList("en:gluten", "en:eggs"));
        milka.setIngredientsText("Zucker, Kakaobutter, MAGERMILCHPULVER, Kakaomasse, SUESSMOLKENPULVER, BUTTERREINFETT, Emulgator (SOJALECITHIN), HASELNUSSPASTE, Aroma.");
        milka.setIngredientsTextDe("Zucker, Kakaobutter, MAGERMILCHPULVER, Kakaomasse, SUESSMOLKENPULVER, BUTTERREINFETT, Emulgator (SOJALECITHIN), HASELNUSSPASTE, Aroma.");
        milka.setNutriScoreGrade("e");
        milka.setNutriScoreScore(27);
        milka.setNovaGroup(4);
        milka.setEcoScoreGrade("d");
        NutrientLevels milkaLevels = new NutrientLevels();
        milkaLevels.setFat("high");
        milkaLevels.setSaturatedFat("high");
        milkaLevels.setSugars("high");
        milkaLevels.setSalt("low");
        milka.setNutrientLevels(milkaLevels);
        milka.setCreatedTimestamp(1360000000L);
        milka.setLastModifiedTimestamp(1710000000L);
        milka.setCreator("workshop");
        milka.setCompleteness(0.92);
        products.put(milka.getBarcode(), milka);

        // 4. Fritz-Kola
        Product fritzKola = new Product();
        fritzKola.setBarcode("4260107220022");
        fritzKola.setProductName("Fritz-Kola");
        fritzKola.setProductNameDe("Fritz-Kola");
        fritzKola.setBrands("Fritz-Kulturgut");
        fritzKola.setQuantity("330 ml");
        fritzKola.setPackaging("Glasflasche");
        fritzKola.setCategories("Getraenke, Erfrischungsgetraenke, Cola");
        fritzKola.setCategoriesTags(Arrays.asList("en:beverages", "en:sodas", "en:colas"));
        fritzKola.setStores("Rewe, Edeka, Getraenkemarkt");
        fritzKola.setCountries("Deutschland");
        fritzKola.setCountriesTags(Arrays.asList("en:germany"));
        fritzKola.setEnergyKcal100g(42.0);
        fritzKola.setEnergy100g(176.0);
        fritzKola.setFat100g(0.0);
        fritzKola.setSaturatedFat100g(0.0);
        fritzKola.setCarbohydrates100g(10.1);
        fritzKola.setSugars100g(10.1);
        fritzKola.setFiber100g(0.0);
        fritzKola.setProteins100g(0.0);
        fritzKola.setSalt100g(0.01);
        fritzKola.setSodium100g(0.004);
        fritzKola.setAllergens("");
        fritzKola.setAllergensTags(Arrays.asList());
        fritzKola.setIngredientsText("Wasser, Zucker, Kohlensaeure, Farbstoff: Zuckerkuloer, natuerliches Koffein, Saeuerungsmittel: Phosphorsaeure, natuerliches Aroma.");
        fritzKola.setIngredientsTextDe("Wasser, Zucker, Kohlensaeure, Farbstoff: Zuckerkuloer, natuerliches Koffein, Saeuerungsmittel: Phosphorsaeure, natuerliches Aroma.");
        fritzKola.setNutriScoreGrade("e");
        fritzKola.setNutriScoreScore(14);
        fritzKola.setNovaGroup(4);
        fritzKola.setEcoScoreGrade("b");
        NutrientLevels fritzLevels = new NutrientLevels();
        fritzLevels.setFat("low");
        fritzLevels.setSaturatedFat("low");
        fritzLevels.setSugars("high");
        fritzLevels.setSalt("low");
        fritzKola.setNutrientLevels(fritzLevels);
        fritzKola.setCreatedTimestamp(1400000000L);
        fritzKola.setLastModifiedTimestamp(1710000000L);
        fritzKola.setCreator("workshop");
        fritzKola.setCompleteness(0.8);
        products.put(fritzKola.getBarcode(), fritzKola);

        // 5. Ritter Sport Voll-Nuss
        Product ritterSport = new Product();
        ritterSport.setBarcode("4000417025005");
        ritterSport.setProductName("Ritter Sport Voll-Nuss");
        ritterSport.setProductNameDe("Ritter Sport Voll-Nuss");
        ritterSport.setBrands("Ritter Sport");
        ritterSport.setQuantity("100 g");
        ritterSport.setPackaging("Plastik, Karton");
        ritterSport.setCategories("Suessigkeiten, Schokolade, Milchschokolade, Nussschokolade");
        ritterSport.setCategoriesTags(Arrays.asList("en:sweets", "en:chocolates", "en:milk-chocolates", "en:nut-chocolates"));
        ritterSport.setStores("Rewe, Edeka, Aldi, Lidl");
        ritterSport.setCountries("Deutschland");
        ritterSport.setCountriesTags(Arrays.asList("en:germany"));
        ritterSport.setEnergyKcal100g(545.0);
        ritterSport.setEnergy100g(2281.0);
        ritterSport.setFat100g(36.0);
        ritterSport.setSaturatedFat100g(14.0);
        ritterSport.setCarbohydrates100g(44.0);
        ritterSport.setSugars100g(42.0);
        ritterSport.setFiber100g(3.0);
        ritterSport.setProteins100g(9.5);
        ritterSport.setSalt100g(0.24);
        ritterSport.setSodium100g(0.096);
        ritterSport.setAllergens("Milch, Haselnuesse, Soja");
        ritterSport.setAllergensTags(Arrays.asList("en:milk", "en:nuts", "en:soybeans"));
        ritterSport.setTraces("Weizen, Ei, Mandeln");
        ritterSport.setTracesTags(Arrays.asList("en:gluten", "en:eggs", "en:almonds"));
        ritterSport.setIngredientsText("Zucker, HASELNUSSKERNE (20%), Kakaobutter, VOLLMILCHPULVER, Kakaomasse, SUESSMOLKENPULVER, BUTTERREINFETT, Emulgator: SOJALECITHIN, natuerliches Aroma.");
        ritterSport.setIngredientsTextDe("Zucker, HASELNUSSKERNE (20%), Kakaobutter, VOLLMILCHPULVER, Kakaomasse, SUESSMOLKENPULVER, BUTTERREINFETT, Emulgator: SOJALECITHIN, natuerliches Aroma.");
        ritterSport.setNutriScoreGrade("e");
        ritterSport.setNutriScoreScore(25);
        ritterSport.setNovaGroup(4);
        ritterSport.setEcoScoreGrade("c");
        NutrientLevels ritterLevels = new NutrientLevels();
        ritterLevels.setFat("high");
        ritterLevels.setSaturatedFat("high");
        ritterLevels.setSugars("high");
        ritterLevels.setSalt("low");
        ritterSport.setNutrientLevels(ritterLevels);
        ritterSport.setCreatedTimestamp(1370000000L);
        ritterSport.setLastModifiedTimestamp(1710000000L);
        ritterSport.setCreator("workshop");
        ritterSport.setCompleteness(0.88);
        products.put(ritterSport.getBarcode(), ritterSport);

        return products;
    }
}
