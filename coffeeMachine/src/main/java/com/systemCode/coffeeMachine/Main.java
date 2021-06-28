package com.systemCode.coffeeMachine;

import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Hello world!
 *
 */
public class Main
{
	  public static void main(String[] args) throws Exception {
	        if (args.length < 1) {
	            System.out.print("Input file name required");
	        }
	        
	        String path = CoffeeMachine.class.getClassLoader().getResource(args[0]).getFile();
	        String jsonInput = Files.readString(Paths.get(path));
	        CoffeeMachine coffeeMachine = CoffeeMachine.getCoffeeMachine(jsonInput);
	        coffeeMachine.handleCustomerRequest("hot_tea");
	        coffeeMachine.handleCustomerRequest("hot_coffee");
	    }
}
