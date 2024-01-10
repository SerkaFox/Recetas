package com.recetaweb;

public class Recipe {
    private int id;
    private String recipeName;
    private int difficulty;

    public Recipe(String recipeName, int difficulty) {
        this.recipeName = recipeName;
        this.difficulty = difficulty;
    }

    public Recipe(int id, String recipeName, int difficulty) {
        this.id = id;
        this.recipeName = recipeName;
        this.difficulty = difficulty;
    }

    public int getId() {
        return id;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }
}
