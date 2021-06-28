package model;

import java.util.LinkedList;
import java.util.ListIterator;

// used to store all the recipes that can be ordered
public class RecipeRepository {
   
	LinkedList<Recipe> listOfRecipe = new LinkedList<Recipe>();
	
	public Recipe getRecipe(String name)
	{
	   ListIterator<Recipe> recipeIterator= listOfRecipe.listIterator();
	   while(recipeIterator.hasNext())
	   {
		    Recipe recipe = (Recipe)recipeIterator.next();
		    if(recipe.getName().equals(name))
		    	return recipe;    	
	   }
	   return null;
	}
	
	public void addRecipe(Recipe recipe)
	{
		listOfRecipe.add(recipe);
	}
	
}
