package pack;

import java.awt.*;

import javax.swing.*;

public class Render extends JPanel {
	static final long serialVersionUID = 1;
	private final int RENDER_WIDTH; // width of window

	private StateValues data; // contains data
	private int numberOfPossibleStates; // example: 10 states from 0..9

	/**
	 * Creates a Window and initializes the data to be displayed
	 * 
	 * @param stateValues            object holding the current values of the matrix
	 * @param numberOfPossibleStates how many different values are possible, e.g. 10
	 */
	public Render(StateValues stateValues, int numberOfPossibleStates) {
		this.RENDER_WIDTH = 1600;
		final int RENDER_HEIGHT = RENDER_WIDTH + 30; // window header
		this.data = stateValues;
		this.numberOfPossibleStates = numberOfPossibleStates;

		JFrame windowFrame = new JFrame();
		windowFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		windowFrame.getContentPane().setLayout(new BorderLayout());
		windowFrame.setSize(RENDER_WIDTH, RENDER_HEIGHT);
		windowFrame.add(this);
		windowFrame.setVisible(true);
	}

	/**
	 * Called automatically if update is triggered via render.repaint()
	 * 
	 * @param graphics properties for drawing context like color/font
	 */
	@Override
	public void paintComponent(Graphics graphics) {
		final int MAX_COLOR_VALUE = 255; // RGB colors from 0...255
		final int DATA_SIZE = data.getSize(); // size of matrix

		// size of squares depends on window width and number of cells
		final int SQUARE_SIZE = RENDER_WIDTH / DATA_SIZE;

		// example: 10 states -> step size about 25
		final int COLOR_STEP_SIZE = MAX_COLOR_VALUE / numberOfPossibleStates;

		for (int row = 0; row < DATA_SIZE; row++) {
			for (int column = 0; column < DATA_SIZE; column++) {
				// example: data value 1 -> color value 25
				int colorValue = COLOR_STEP_SIZE * data.getValue(row, column);

				// blue-ish color
				graphics.setColor(new Color(colorValue, colorValue, MAX_COLOR_VALUE));

				// fillRect-Parameter: x, y, width, height
				graphics.fillRect(row * SQUARE_SIZE, column * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
			}
		}
	}
}