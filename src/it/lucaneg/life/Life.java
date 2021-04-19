package it.lucaneg.life;

import javax.swing.SwingUtilities;

import it.lucaneg.life.gui.UI;
import it.lucaneg.life.model.State;
import it.lucaneg.life.model.State.Coordinates;

public class Life {
	
	private static final int AUTOMATON_SIZE = 200;
	
	private UI ui;
	private Runner runner;
	
	public void setUi(UI ui) {
		this.ui = ui;
	}
	
	public interface UITask {
		public void process(UI ui);
	}

	public void performOnUI(UITask task) {
		SwingUtilities.invokeLater(() -> task.process(ui));
	}

	public void startRandom(double livenessRatio, int speed) {
		State state = new State(AUTOMATON_SIZE, livenessRatio);
		updateUiAndExec(speed, state);
	}

	public void startWithPattern(int speed, Coordinates[] pattern) {
		startWithPattern(speed, pattern, 37, 38);
	}
	
	public void startWithPattern(int speed, Coordinates[] pattern, int xOffset, int yOffset) {
		State state = new State(AUTOMATON_SIZE, offset(pattern, xOffset, yOffset));
		updateUiAndExec(speed, state);
	}

	public void startSpaceships(int speed) {
		State state = new State(AUTOMATON_SIZE);
		updateUiAndExec(speed, state);
	}
	
	private Coordinates[] offset(Coordinates[] relative, int xorig, int yorig) {
		Coordinates[] result = new Coordinates[relative.length];
		
		for (int i = 0; i < result.length; i++) 
			result[i] = new Coordinates(relative[i].row + xorig, relative[i].column + yorig);
		
		return result;
	}

	private void updateUiAndExec(int speed, State state) {
		performOnUI(gui -> gui.show(state));
		
		if (isRunning())
			stop();
		
		runner = new Runner(this, state, speed);
		runner.start();
	}

	public void setSpeed(int value) {
		if (isRunning())
			runner.setSpeed(value);
	}
	
	public void stop() {
		if (isRunning()) 
			runner.interrupt();
		
		runner = null;
	}
	
	public boolean isRunning() {
		return runner != null;
	}
	
	public static void main(String[] args) throws InterruptedException {
		Life game = new Life();
		UI ui = new UI(game, AUTOMATON_SIZE);
		game.setUi(ui);
		ui.setVisible(true);
	}
}
