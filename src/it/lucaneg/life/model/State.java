package it.lucaneg.life.model;

import java.util.ArrayList;
import java.util.Collection;

public class State {
	
	public static class Coordinates {
		public final int row;
		public final int column;
		
		public Coordinates(int row, int column) {
			this.row = row;
			this.column = column;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + column;
			result = prime * result + row;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Coordinates other = (Coordinates) obj;
			if (column != other.column)
				return false;
			if (row != other.row)
				return false;
			return true;
		}
	}

	private final Configuration currentConfig;
	
	private final int generation;
	
	private State(State other) {
		currentConfig = other.currentConfig.evolve();
		generation = other.generation + 1;
	}
	
	public State(int automatonSize) {
		this(automatonSize, 0.2);
	}
	
	public State(int automatonSize, double livenessRatio) {
		currentConfig = new Configuration(automatonSize, livenessRatio);
		generation = 0;
	}
	
	public State(int automatonSize, Coordinates... alive) {
		currentConfig = new Configuration(automatonSize, alive);
		generation = 0;
	}
	
	public State evolve() {
		return new State(this);
	}

	public void dumpToConsole() {
		System.out.println("Generation " + generation + ":"); 
		System.out.println(currentConfig);
	}
	
	public int getGeneration() {
		return generation;
	}
	
	public Configuration getCurrentConfig() {
		return currentConfig;
	}
	
	public Collection<Coordinates> getAliveCells() {
		Collection<Coordinates> alive = new ArrayList<>();
		
		currentConfig.queryAllCells((r, c, b) -> {
			if (b)
				alive.add(new Coordinates(r, c));
		});
		
		return alive;
	}
}
