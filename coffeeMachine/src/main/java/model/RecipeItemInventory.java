package model;

import java.util.LinkedHashMap;

// used to manage the list of items in the inventory
public class RecipeItemInventory {
     
	private LinkedHashMap<String, Integer> recipeInventoryMap = new LinkedHashMap<String, Integer>();
	private int maxCapacity;
	
	synchronized public Integer getRecipeBalance(String item)
	{
		return  recipeInventoryMap.getOrDefault(item, -1);
	}
	
	synchronized public void setMaxCapacity(final int maxCapacity)
	{
		this.maxCapacity = maxCapacity;
	}
	
	public int getMaxCapacity()
	{
		return maxCapacity;
	}
	
	synchronized public Boolean updateRecipeContent(String recipe, int quantity )
	{
		int currentCapacity = recipeInventoryMap.getOrDefault(recipe, -1);
		if(currentCapacity<quantity)
			return false;
	    recipeInventoryMap.put(recipe, currentCapacity-quantity);
	    return true;
	}
	
	synchronized public void addItems(String recipe, int quantity )
	{
	    recipeInventoryMap.put(recipe,quantity);
	}
	
	synchronized public void refillRepository()
	{
	    for(final String recipeItem :recipeInventoryMap.keySet())
	    	recipeInventoryMap.put(recipeItem, maxCapacity);
	}
}
