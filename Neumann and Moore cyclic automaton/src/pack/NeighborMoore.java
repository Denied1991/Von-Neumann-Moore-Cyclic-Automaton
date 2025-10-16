package pack;

import java.awt.Point;
import java.util.ArrayList;

public class NeighborMoore implements NeighborService{
	private final static Integer calculateTopNeighbor(Point coordinate, StateValues values) {
		Integer topNeighbor;
		if(coordinate.y != 0) {
			topNeighbor = values.getValue((coordinate.y - 1), coordinate.x);
		}
		else {
			topNeighbor = values.getValue((values.getSize() - 1), coordinate.x);
		}
		return topNeighbor;
	}
	
	private final static Integer calculateTopRightNeighbor(Point coordinate, StateValues values) {
		Integer topRightNeighbor;
		/*
		** If the selected cell is located in the top-right corner of the matrix.
		*/
		if(coordinate.y == 0 && (coordinate.x == (values.getSize() - 1))) {
			topRightNeighbor = values.getValue((values.getSize() - 1), 0);
		}
		/*
		** If the selected cell is located at the top of the matrix, but not completely
		** to the right.
		*/
		else if(coordinate.y == 0 && (coordinate.x != (values.getSize() - 1))) {
			topRightNeighbor = values.getValue((values.getSize() - 1), (coordinate.x + 1));
		}
		/*
		** If the selected cell is located at the right border of the matrix, but not
		** at the top.
		*/
		else if((coordinate.y != 0) && (coordinate.x == (values.getSize() - 1))) {
			topRightNeighbor = values.getValue((coordinate.y - 1), 0);
		}
		/*
		** If the selected cell is located anywhere else in the matrix.
		*/
		else {
			topRightNeighbor = values.getValue((coordinate.y - 1), (coordinate.x + 1));
		}
		return topRightNeighbor;
	}
	
	private final static Integer calculateRightNeighbor(Point coordinate, StateValues values) {
		Integer rightNeighbor;
		// tocke pocinju na 0/0
		if(coordinate.x != (values.getSize() - 1)) {
			rightNeighbor = values.getValue(coordinate.y, (coordinate.x + 1));
		}
		else {
			rightNeighbor = values.getValue(coordinate.y, 0);
		}
		return rightNeighbor;
	}
	
	private final static Integer calculateBottomRightNeighbor(Point coordinate, StateValues values) {
		Integer bottomRightNeighbor;
		/*
		** If the selected cell is located in the bottom-right corner of the matrix.
		*/
		if((coordinate.y == (values.getSize() - 1)) && (coordinate.x == (values.getSize() - 1))) {
			bottomRightNeighbor = values.getValue(0, 0);
		}
		/*
		** If the selected cell is located at the bottom of the matrix, but not completely
		** to the right.
		*/
		else if((coordinate.y == (values.getSize() - 1)) && (coordinate.x != (values.getSize() - 1))) {
			bottomRightNeighbor = values.getValue(0, (coordinate.x + 1));
		}
		/*
		** If the selected cell is located at the right border of the matrix, but not
		** at the bottom.
		*/
		else if((coordinate.y != (values.getSize() - 1)) && (coordinate.x == (values.getSize() - 1))) {
			bottomRightNeighbor = values.getValue((coordinate.y + 1), 0);
		}
		/*
		** If the selected cell is located anywhere else in the matrix.
		*/
		else {
			bottomRightNeighbor = values.getValue((coordinate.y + 1), (coordinate.x + 1));
		}
		return bottomRightNeighbor;
	}
	
	private final static Integer calculateBottomNeighbor(Point coordinate, StateValues values) {
		Integer bottomNeighbor;
		if(coordinate.y != (values.getSize() - 1)) {
			bottomNeighbor = values.getValue((coordinate.y + 1), coordinate.x);
		}
		else {
			bottomNeighbor = values.getValue(0, coordinate.x);
		}
		return bottomNeighbor;
	}
	
	private final static Integer calculateBottomLeftNeighbor(Point coordinate, StateValues values) {
		Integer bottomLeftNeighbor;
		/*
		** If the selected cell is located in the bottom-left corner of the matrix.
		*/
		if((coordinate.y == (values.getSize() - 1)) && (coordinate.x == 0)) {
			bottomLeftNeighbor = values.getValue(0, (values.getSize() - 1));
		}
		/*
		** If the selected cell is located at the bottom of the matrix, but not completely
		** to the left.
		*/
		else if((coordinate.y == (values.getSize() - 1)) && (coordinate.x != 0)) {
			bottomLeftNeighbor = values.getValue(0, (coordinate.x - 1));
		}
		/*
		** If the selected cell is located at the left border of the matrix, but not
		** at the bottom.
		*/
		else if((coordinate.y != (values.getSize() - 1)) && (coordinate.x == 0)) {
			bottomLeftNeighbor = values.getValue((coordinate.y + 1), (values.getSize() - 1));
		}
		/*
		** If the selected cell is located anywhere else in the matrix.
		*/
		else {
			bottomLeftNeighbor = values.getValue((coordinate.y + 1), (coordinate.x - 1));
		}
		return bottomLeftNeighbor;
	}
	
	private final static Integer calculateLeftNeighbor(Point coordinate, StateValues values) {
		Integer leftNeighbor;
		if(coordinate.x != 0) {
			leftNeighbor = values.getValue(coordinate.y, (coordinate.x - 1));
		}
		else {
			leftNeighbor = values.getValue(coordinate.y, (values.getSize() - 1));
		}
		return leftNeighbor;
	}
	
	private final static Integer calculateTopLeftNeighbor(Point coordinate, StateValues values) {
		Integer topLeftNeighbor;
		/*
		** If the selected cell is located in the top-left corner of the matrix.
		*/
		if((coordinate.y == 0) && (coordinate.x == 0)) {
			topLeftNeighbor = values.getValue((values.getSize() - 1), (values.getSize() - 1));
		}
		/*
		** If the selected cell is located at the top of the matrix, but not completely
		** to the left.
		*/
		else if((coordinate.y == 0) && (coordinate.x != 0)) {
			topLeftNeighbor = values.getValue((values.getSize() - 1), (coordinate.x - 1));
		}
		/*
		** If the selected cell is located at the left border of the matrix, but not
		** at the top.
		*/
		else if((coordinate.y != 0) && (coordinate.x == 0)) {
			topLeftNeighbor = values.getValue((coordinate.y - 1), (values.getSize() - 1));
		}
		/*
		** If the selected cell is located anywhere else in the matrix.
		*/
		else {
			topLeftNeighbor = values.getValue((coordinate.y - 1), (coordinate.x - 1));
		}
		return topLeftNeighbor;
	}
	
	@Override
	public ArrayList<Integer> getValuesFromNeighbors(Point coordinate, StateValues values) {
		ArrayList<Integer> neighbors = new ArrayList<>();
		neighbors.add(calculateTopNeighbor(coordinate, values));
		neighbors.add(calculateTopRightNeighbor(coordinate, values));
		neighbors.add(calculateRightNeighbor(coordinate, values));
		neighbors.add(calculateBottomRightNeighbor(coordinate, values));
		neighbors.add(calculateBottomNeighbor(coordinate, values));
		neighbors.add(calculateBottomLeftNeighbor(coordinate, values));
		neighbors.add(calculateLeftNeighbor(coordinate, values));
		neighbors.add(calculateTopLeftNeighbor(coordinate, values));
		return neighbors;
	}
	
}