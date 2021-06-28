package model;

import java.util.LinkedHashMap;

public class Recipe {

	private String name;
	private LinkedHashMap<String,Integer> recipe;
	
	public Recipe(String name, LinkedHashMap<String,Integer> recipe)
	{
		this.name = name;
		this.recipe = recipe;
	}

	public LinkedHashMap<String,Integer> getRecipeItems() {
		return this.recipe;
	}

	public String getName() {
		return this.name;
	}
	
	
}
