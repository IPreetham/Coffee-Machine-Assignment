package com.systemCode.coffeeMachine;

import static org.junit.jupiter.api.Assertions.*;
import java.util.LinkedList;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import model.ExecutionResult;

class CoffeeMachineTest {

	private String readFileAsString(String file) throws Exception {
		return new String(Files.readAllBytes(Paths.get(file)));
	}

	@Test
	// to verify the positive scenario
	void verifyAllValidInput() throws Exception {
		String inventoryDetails = readFileAsString("src/test/java/com/systemCode/coffeeMachine/input.json");
		CoffeeMachine machine = CoffeeMachine.getCoffeeMachine(inventoryDetails);
		machine.handleCustomerRequest("hot_tea");
		machine.handleCustomerRequest("green_tea");
		Thread.sleep(3000);
		LinkedList<ExecutionResult> orderOutcomes = machine.getTemporrayTestResults();
		for (ExecutionResult result : orderOutcomes) {
			assertEquals(result, ExecutionResult.SuccessFul);
		}

		machine.stopMachine();
	}

	@Test
	// to verify if the order contains any item which is not present in input file
	void verifyOrderNotValid() throws Exception {
		String inventoryDetails = readFileAsString("src/test/java/com/systemCode/coffeeMachine/input.json");
		CoffeeMachine machine = CoffeeMachine.getCoffeeMachine(inventoryDetails);
		machine.handleCustomerRequest("hot_te");
		machine.handleCustomerRequest("hot_cof");
		machine.handleCustomerRequest("green_ta");
		Thread.sleep(1000);
		LinkedList<ExecutionResult> orderOutcomes = machine.getTemporrayTestResults();
		for (ExecutionResult result : orderOutcomes) {
			assertEquals(result, ExecutionResult.InvalidOrder);
		}

		machine.stopMachine();
	}

	@Test
	// to verify when the order exhaust the items in inventory
	void orderinsufficient() throws Exception {
		String inventoryDetails = readFileAsString("src/test/java/com/systemCode/coffeeMachine/input.json");
		CoffeeMachine machine = CoffeeMachine.getCoffeeMachine(inventoryDetails);
		machine.handleCustomerRequest("black_tea");
		machine.handleCustomerRequest("black_tea");
		machine.handleCustomerRequest("hot_tea");
		Thread.sleep(1000);
		LinkedList<ExecutionResult> orderOutcomes = machine.getTemporrayTestResults();
		assertTrue(orderOutcomes.contains(ExecutionResult.Insufficient));
		machine.stopMachine();
	}

}
