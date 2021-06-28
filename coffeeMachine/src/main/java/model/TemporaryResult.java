package model;

import java.util.LinkedList;

// used to manage the results of orders
public class TemporaryResult {

	private static LinkedList<ExecutionResult>  executionresult = new LinkedList<ExecutionResult>();
	
	public static void addExecutionResult(ExecutionResult result)
	{
		executionresult.add(result);
	}
	
	public static void clearResults()
	{
		executionresult.clear();
	}
	
	public static LinkedList<ExecutionResult> getExecutionResults()
	{
		return executionresult;
	}
}
