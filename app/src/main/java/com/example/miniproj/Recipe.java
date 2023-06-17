package com.example.miniproj;

public class Recipe {
    private String name;
    private String description;
    private String ingredients;
    private String image;
    private boolean expanded;

    public Recipe(String name, String description, String ingredients, String image) {
        this.name = name;
        this.description = description;
        this.ingredients = ingredients;
        this.image = image;
        this.expanded = false;
    }

    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }

    public String getIngredients() {
        return ingredients;
    }

    public String getImage() {
        return image;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

}
