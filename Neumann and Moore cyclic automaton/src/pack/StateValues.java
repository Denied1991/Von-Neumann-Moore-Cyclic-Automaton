package pack;

import java.util.*;

public class StateValues {
	/*
	** Determines the amount of possible states for each cell
	** in the matrix.
	*/
	private final static int NUMBER_OF_POSSIBLE_STATES = 10;
	
	/*
	** The highest possible value for any cell will always be
	** lower than the amount of possible states by one due to
	** the way that 
	*/
	private final static int HIGHEST_POSSIBLE_VALUE = NUMBER_OF_POSSIBLE_STATES - 1;
	
	/*
	** The lowest possible value any cell can have.
	*/
	private final static int LOWEST_POSSIBLE_VALUE = 0;
	
	/*
	** Determines the overall size of matrix.
	**
	** Example: making size 3 would create a 3x3 
	**			matrix (3 rows and 3 columns).
	*/
	private int size;
	
	/*
	** The ArrayList which makes up the matrix.
	*/
	private ArrayList<ArrayList<Integer>> matrix = new ArrayList<>();
	
	/**
	 * Constructor for the class "StateValues".
	 * 
	 * @param size - determines the size of the matrix
	 * @param fillMatrixWithValues - (true) creates a matrix with random values,
	 * 								 (false) creates an empty matrix
	 */
	public StateValues(int size, boolean fillMatrixWithValues) {
		if(fillMatrixWithValues) {
			createMatrixWithRandomValues(size);
		}
		else {
			createEmptyMatrix(size);
		}
	}
	
	/**
	 * Creates and fills the entire matrix with randomly generated values,
	 * ranging from 0 to (NUMBER_OF_POSSIBLE_STATES - 1).
	 * 
	 * @param size - determines the size of the matrix
	 */
	private void createMatrixWithRandomValues(int size) {
		setSize(size);
		Random random = new Random();
		for(int row=0; row<size; row++) {
			ArrayList<Integer> myRow = new ArrayList<>();
			for(int column=0; column<this.size; column++) {
				myRow.add(random.nextInt(NUMBER_OF_POSSIBLE_STATES));
			}
			this.matrix.add(myRow);
		}
	}
	
	/**
	 * Creates an empty matrix.
	 * 
	 * @param size - determines the size of the matrix
	 */
	private void createEmptyMatrix(int size) {
		setSize(size);
		for(int row=0; row<size; row++) {
			ArrayList<Integer> myRow = new ArrayList<>();
			this.matrix.add(myRow);
		}
	}
	
	/*
	public StateValues() {
		System.out.println (
			"The possible values that each cell in the matrix can have is: "
		);
		int[] possibleStateValues = new int[10];
		for(int i=0; i<NUMBER_OF_POSSIBLE_STATES; i++) {
			possibleStateValues[i] = i;
		}
		System.out.print(possibleStateValues);
	}
	*/
	
	@Override
	/** 
	 * @param value
	 * @return the values of the matrix in the form
	 * 		   of an ArrayList.
	 */
	public String toString() {
		return this.matrix.toString();
	}
	
	@Override
	/*
	** REMINDER: Need to finish.
	*/
	public int hashCode() {
		//Arrays.deepHashCode(this.matrix.get(0));
		return 3;
	}
	
	/**
	 * @return The number of possible states that any cell in the matrix can have.
	 */
	public final static int getNumberOfPossibleValues() {
		return NUMBER_OF_POSSIBLE_STATES;
	}
	
	/**
	 * @return The highest possible value that any cell can have.
	 */
	public final static int getHighestPossibleValue() {
		return HIGHEST_POSSIBLE_VALUE;
	}
	
	/**
	 * @return The lowest possible value that any cell can have.
	 */
	public final static int getLowestPossibleValue() {
		return LOWEST_POSSIBLE_VALUE;
	}
	
	/**
	 * @return The matrix that holds the values.
	 */
	public final ArrayList<ArrayList<Integer>> getMatrix() {
		return this.matrix;
	}
	
	/**
	 * Replaces the curent matrix with the matrix that was passed as the argument.
	 * 
	 * @param newMatrix - The matrix that is to be set
	 */
	public final void setMatrix(ArrayList<ArrayList<Integer>> newMatrix) {
		this.matrix = newMatrix;
	}
	
	/**
	 * @return The size of the matrix.
	 */
	public final int getSize() {
		return this.size;
	}
	
	/**
	 * Example: Passing 3 as the argument of the call would
	 * 			create a matrix with 3 rows and 3 columns. 
	 * 
	 * @param size
	 */
	private final void setSize(int size) {
		this.size = size;
	}
	
	/**
	 * @param row - integer value that determines which row will be accessed
	 * @param column - integer value that determines which column will be accessed
	 * @return An Integer value that is stored at the specified row and column.
	 */
	public Integer getValue(int row, int column) {
		return this.matrix.get(row).get(column);
	}
	
	/**
	 * This method is used to overwrite an existing value at the specified row
	 * and column.
	 * 
	 * @param row - integer value that determines which row will be accessed
	 * @param column - integer value that determines which column will be accessed
	 * @param newValue - the new Integer value to be set
	 */
	public void setValue(int row, int column, Integer newValue) {
		this.matrix.get(row).set(column, newValue);
	}
}