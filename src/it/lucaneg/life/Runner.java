package it.lucaneg.life;

public class Runner extends Thread {
	
	private static final int REFRESH = 500;

	private final Life game;
	private final it.lucaneg.life.model.State init;
	
	private int speed;
	
	public Runner(Life game, it.lucaneg.life.model.State init, int speed) {
		this.game = game;
		this.init = init;
		this.speed = speed;
	}

	public void run() {
		it.lucaneg.life.model.State state = this.init;
		while (true) {
			if (isInterrupted())
				break;
			
			updateUi(state);
			//state.dumpToConsole();
			
			state = state.evolve();
			
			int sleep = REFRESH;
			synchronized (this) {
				sleep = speed;
			}
			
			try {
				Thread.sleep(sleep > 0 ? sleep : 10);
			} catch (InterruptedException e) {
				break;
			}
		}
	}
	
	public void setSpeed(int speed) {
		synchronized (this) {
			this.speed = speed;
		}
	}

	private void updateUi(it.lucaneg.life.model.State state) {
		game.performOnUI(ui -> ui.show(state));
	}
}
