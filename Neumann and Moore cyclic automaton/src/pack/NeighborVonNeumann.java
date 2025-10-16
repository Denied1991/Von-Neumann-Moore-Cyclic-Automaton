package pack;

import java.awt.Point;
import java.util.*;

public class NeighborVonNeumann implements NeighborService{
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
	
	private final static Integer calculateRightNeighbor(Point coordinate, StateValues values) {
		Integer rightNeighbor;
		if(coordinate.x != (values.getSize() - 1)) {
			rightNeighbor = values.getValue(coordinate.y, (coordinate.x + 1));
		}
		else {
			rightNeighbor = values.getValue(coordinate.y, 0);
		}
		return rightNeighbor;
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
	
	@Override
	public ArrayList<Integer> getValuesFromNeighbors(Point coordinate, StateValues values) {
		ArrayList<Integer> neighbors = new ArrayList<>();
		neighbors.add(calculateTopNeighbor(coordinate, values));
		neighbors.add(calculateRightNeighbor(coordinate, values));
		neighbors.add(calculateBottomNeighbor(coordinate, values));
		neighbors.add(calculateLeftNeighbor(coordinate, values));
		return neighbors;
	}
}