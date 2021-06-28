package com.systemCode.coffeMachine.task;

import com.systemCode.coffeeMachine.OrderManager;

import model.ExecutionResult;
import model.TemporaryResult;

public class RecipeOrder implements Runnable {

	private String recipe;
	
	public RecipeOrder(String recipe)
	{
		this.recipe = recipe;
	}
	
	public void run()
	{
		ExecutionResult result =OrderManager.getItemInvetoryManager().serveRecip(recipe);
		TemporaryResult.addExecutionResult(result);
		
	}
}
