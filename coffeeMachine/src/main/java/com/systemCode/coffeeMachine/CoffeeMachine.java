package com.systemCode.coffeeMachine;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.systemCode.coffeMachine.task.RecipeOrder;

import model.ExecutionResult;
import model.MachineProperties;
import model.Recipe;
import model.TemporaryResult;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.concurrent.*;

public class CoffeeMachine {

    private static CoffeeMachine coffeeMachine = null;
    public MachineProperties coffeeMachineConfiguration;
    public OrderManager orderManager;
    private static final int maximumThreads = 100;
    private ThreadPoolExecutor executor;

    public static CoffeeMachine getCoffeeMachine(final String recipeInput) throws IOException {
        if (coffeeMachine == null) {
            coffeeMachine = new CoffeeMachine(recipeInput);
        }
        return coffeeMachine;
    }

    private CoffeeMachine(String jsonInput) throws IOException {
        this.orderManager = OrderManager.getItemInvetoryManager();
        this.coffeeMachineConfiguration = new ObjectMapper().readValue(jsonInput, MachineProperties.class);
        int outlet =  coffeeMachineConfiguration.getOutletInfo().getCount();
        executor = new ThreadPoolExecutor(outlet, outlet, 9000L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(maximumThreads));
        initrepositories();
    }

    private void initrepositories() {

       initItemInventory();
       initRecipeRepository();
    }
    
    private void initItemInventory()
    {
    	LinkedHashMap<String, Integer> ingredients = this.coffeeMachineConfiguration.getIngredientQuantityMap();
        for (String key : ingredients.keySet()) {
            orderManager.addItemToInventory(key, ingredients.get(key));
        }
    }
    
    private void initRecipeRepository()
    {
    	 LinkedHashMap<String, LinkedHashMap<String, Integer>> recipes = coffeeMachineConfiguration.getBeverages();
         for (String key : recipes.keySet()) {
             Recipe recipe = new Recipe(key, recipes.get(key));
             orderManager.addRecipe(recipe);
         }
    }

    public void handleCustomerRequest(String recipe) {
        RecipeOrder task = new RecipeOrder(recipe);
        executor.execute(task);
    }

    public void stopMachine() {
    	TemporaryResult.clearResults();
    	coffeeMachine = null;
        executor.shutdown();
    }
    
    public LinkedList<ExecutionResult> getTemporrayTestResults()
	{
		return TemporaryResult.getExecutionResults();
	}

}