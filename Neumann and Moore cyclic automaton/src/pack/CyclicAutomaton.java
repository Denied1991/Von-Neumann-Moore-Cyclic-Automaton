package pack;

import java.awt.Point;
import java.util.*;

public class CyclicAutomaton {
	private StateValues currentStateValues;
	private StateValues nextStateValues;
	private Render render;
	private NeighborService neighborService;
	private int MAX_ITERATIONS;
	
	/**
	 * Constructor for the class "CyclicAutomaton".
	 * 
	 * @param matrixSize - an integer value which determines the size of the matrix
	 * @param neighbors - an object from a class that implements the interface "NeighborService"
	 */
	public CyclicAutomaton(int matrixSize, NeighborService neighbors) {
		this.currentStateValues = new StateValues(matrixSize, true);
		this.render = new Render(this.currentStateValues, StateValues.getNumberOfPossibleValues());
		this.neighborService = neighbors;
		this.MAX_ITERATIONS = 10 * this.currentStateValues.getSize();
	}
	
	/**
	 * This method is called when the matrix is smaller than 100 rows
	 * and columns. It is used to create the effect of a smoother and
	 * better observable simulation.
	 * 
	 * @param sleepTime - the amount of time that the program will sleep for (in milliseconds)
	 */
	private final void pauseBetweenIterations(int sleepTime) {
		try {
			Thread.sleep(sleepTime);
		}
		catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Calling this will determine the suitable amount of sleep time
	 * for the program, based on the size of the matrix.
	 * 
	 * @return The amount of time that the program will sleep for (in milliseconds).
	 */
	private final int calculateSleepTime() {
		if(this.currentStateValues.getSize() < 100) {
			return ((20 / this.currentStateValues.getSize()) * 1000);
		}
		return 0;
	}
	
	private final boolean determineEnvironment(ListIterator<Integer> myIterator) {
		int listLength = 0;
		while(myIterator.hasNext()) {
			myIterator.next();
			listLength++;
		}
		if(listLength == 4) {
			return true;
		}
		return false;
	}
	
	/**
	 * @param myIterator
	 * @return (true) if at least one neighbor in the list has the lowest possible values
	 */
	/*
	private final boolean checkNeighborForLowestPossibleValue(ListIterator<Integer> myIterator) {
		if(myIterator.hasNext()) {
			while(myIterator.hasNext()) {
				if(myIterator.next() == StateValues.getLowestPossibleValue()) {
					return true;
				}
			}
		}
		else {
			while(myIterator.hasPrevious()) {
				if(myIterator.previous() == StateValues.getLowestPossibleValue()) {
					return true;
				}
			}
		}
		return false;
	}
	*/
	
	private final boolean checkNeighborForHigherValue(ListIterator<Integer> myIterator, int higherValue) {
		if(myIterator.hasNext()) {	
			while(myIterator.hasNext()) {
				if(myIterator.next() == higherValue) {
					return true;
				}
			}
		}
		else {
			while(myIterator.hasPrevious()) {
				if(myIterator.previous() == higherValue) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * This method is called when simulating a Moore-neighborhood CCA.
	 * 
	 * @param myIterator
	 * @param higherValue - value which is higher than the currently selected value by one
	 * @return
	 */
	private final boolean increaseValueMoore(ListIterator<Integer> myIterator, int higherValue) {
		int tau = 0;
		if(myIterator.hasNext())
			while(myIterator.hasNext()) {
				if(myIterator.next() == higherValue) {
					tau++;
				}
			}
		else {
			while(myIterator.hasPrevious()) {
				if(myIterator.previous() == higherValue) {
					tau++;
				}
			}
		}
		if(tau >= 2) {
			return true;
		}
		return false;
	}
	
	/**
	 * This funktion calculates the next value to be stored in the cell
	 * 
	 * @param myIterator - list iterator for going through the list of neighbor values
	 * @param currentValue - the value which is stored in the cell that is currently selected
	 * @param row - which row the value is going to be added to
	 */
	private final Integer calculateNextValue(ListIterator<Integer> myIterator, int currentValue) {
		Integer higherValue;
		if(currentValue == StateValues.getHighestPossibleValue()) {
			higherValue = 0;
		}
		else {
			higherValue = currentValue + 1;
		}
		if(determineEnvironment(myIterator)) {
			if(checkNeighborForHigherValue(myIterator, higherValue) && (currentValue == StateValues.getHighestPossibleValue())) {
				return StateValues.getLowestPossibleValue();
			}
			if(checkNeighborForHigherValue(myIterator, higherValue)) {
				return higherValue;
			}
		}
		else {
			if(increaseValueMoore(myIterator, higherValue) && (currentValue == StateValues.getHighestPossibleValue())) {
				/*
				** Check if this is correctly implemented.
				** I dont know how the resetting works with Moore.
				*/
				return StateValues.getLowestPossibleValue();
			}
			if(increaseValueMoore(myIterator, higherValue)) {
				return higherValue;
			}
		}
		return currentValue;
	}
	
	/**
	 * Adds the next calculated value for a cell into nextStateValues. 
	 * 
	 * @param nextValue - next calculated value
	 * @param row - which row the value is going to be added to
	 */
	private final void addNextValue(Integer nextValue, int row) {
		this.nextStateValues.getMatrix().get(row).add(nextValue);
	}
	
	/**
	 * Calculates the next generation.
	 */
	private final void calculateNextGeneration() {
		this.nextStateValues = new StateValues(this.currentStateValues.getSize(), false);
		ListIterator<Integer> myIterator;
		Point matrixCell = new Point();
		
		// Calculate the next generation of values and stores them in nextStateValues.
		for(matrixCell.y=0; matrixCell.y<this.currentStateValues.getSize(); matrixCell.y++) {
			for(matrixCell.x=0; matrixCell.x<this.currentStateValues.getSize(); matrixCell.x++) {
				myIterator = this.neighborService.getValuesFromNeighbors(matrixCell, this.currentStateValues).listIterator();
				addNextValue(calculateNextValue(myIterator, this.currentStateValues.getValue(matrixCell.y, matrixCell.x)), matrixCell.y);
			}
		}
		
		// Replaces the current matrix with the next one.
		this.currentStateValues.setMatrix(this.nextStateValues.getMatrix());
	}
	
	public void simulate() {
		/*
		** I initializied sleepTime here so that
		** the function determineSleepTime() does
		** not get called every time the for loop
		** iterates, thereby reducing unecessary
		** computation.
		*/
		int sleepTime = calculateSleepTime();
		for(int t=0; t<this.MAX_ITERATIONS; t++) {
			this.render.repaint();
			pauseBetweenIterations(1);
			calculateNextGeneration();
			
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}