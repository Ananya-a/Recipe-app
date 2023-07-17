package com.example.miniproj;

public class Recipe {
    private String name;
    private String ingredients;
    private String image;
    private String instructions;
    private String videoLink;
    private boolean expanded;

    public Recipe(String name, String ingredients, String instructions, String image, String videoLink) {
        this.name = name;
        this.ingredients = ingredients;
        this.image = image;
        this.instructions = instructions;
        this.videoLink = videoLink;
        this.expanded = false;
    }

    public String getName() {
        return name;
    }

    public String getIngredients() {
        return ingredients;
    }

    public String getInstructions() {
        return instructions;
    }

    public String getImage() {
        return image;
    }

    public String getVideoLink() {
        return videoLink;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }
}