package pack;

public class Main {
	public static void main(String[] args) {
		int matrixSize = 800;
		CyclicAutomaton ca = new CyclicAutomaton(matrixSize, new NeighborVonNeumann());
		ca.simulate();
//		CyclicAutomaton ce = new CyclicAutomaton(matrixSize, new NeighborMoore());
//		ce.simulate();
	}
}