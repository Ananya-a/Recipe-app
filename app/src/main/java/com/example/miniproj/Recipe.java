package com.example.miniproj;

public class Recipe {
    private String name;
    private String ingredients;
    private String image;
    private String instructions;
    private String youtube;
    private boolean expanded;

    public Recipe(String name, String ingredients,String instructions, String image) {
        this.name = name;
        this.ingredients = ingredients;
        this.image = image;
        this.instructions = instructions;
        this.youtube = youtube;
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
    public String getYoutube() {
        return youtube;
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