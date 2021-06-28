package com.systemCode.coffeeMachine;

import java.util.LinkedHashMap;
import model.ExecutionResult;
import model.Recipe;
import model.RecipeItemInventory;
import model.RecipeRepository;

//Used to manage the order received by the machine
public class OrderManager {

	private RecipeItemInventory itemInventory;
	private RecipeRepository recipeRepository;
	private OrderManager() {
		
		itemInventory = new RecipeItemInventory();
		itemInventory.setMaxCapacity(1000);
		recipeRepository = new RecipeRepository();
	}
	
	private static class InventoryHolder 
	{
		public static final OrderManager orderManager  = new OrderManager();
	}
	
	synchronized public ExecutionResult serveRecip(final String recipe)
	{
		ExecutionResult result = canServeRecipe(recipe);
		if(result==ExecutionResult.SuccessFul)
		{
			updateRecipeRepository(recipe);
		}
		
		return result ;
	}
	
	synchronized private ExecutionResult canServeRecipe(final String recipe)
	{
		if(recipeRepository.getRecipe(recipe)!=null)
		{
		   final Recipe servedRecipe = recipeRepository.getRecipe(recipe);
		   LinkedHashMap<String,Integer> recipeItems =  servedRecipe.getRecipeItems();
		   for(String recipeItem: recipeItems.keySet())
		   {
			   int availableItemCapacity= itemInventory.getRecipeBalance(recipeItem);
			   if(recipeItems.get(recipeItem)>availableItemCapacity)
			   {
				   if(availableItemCapacity>0)
				   {
					   System.out.println("Recipe Item content is low");
					   return ExecutionResult.Insufficient;
					   
				   }
				   if(availableItemCapacity==-1)
				   {
					   System.out.println("Recipe Item is not available");
					   return ExecutionResult.InvalidOrder;
				   }
			   }
		   }  
		}
		
		return ExecutionResult.SuccessFul;
	}
	
	private void refillItemInventory()
	{
		itemInventory.refillRepository();
	}
	
	synchronized private Boolean updateRecipeRepository(final String recipe)
	{
		final Recipe servedRecipe = recipeRepository.getRecipe(recipe);
		LinkedHashMap<String,Integer> recipeItems =  servedRecipe.getRecipeItems();
		for(String recipeItem: recipeItems.keySet())
		{
			if(!itemInventory.updateRecipeContent(recipeItem, recipeItems.get(recipeItem)))
				return false;
				
		}
		return true;
	}
	
	public static OrderManager getItemInvetoryManager()
	{
		return InventoryHolder.orderManager;
	}
	
	public void addRecipe(final Recipe recipe)
	{
		recipeRepository.addRecipe(recipe);
	}
	
	public void addItemToInventory(final String item, final int capacity)
	{
		itemInventory.addItems(item, capacity);
	}
	
}
