package it.lucaneg.life.model;

import it.lucaneg.life.model.State.Coordinates;

public class Configuration {
	
	private final boolean[][] matrix;
	
	private Configuration(int size) {
		matrix = new boolean[size][size];
	}
	
	private Configuration(Configuration other) {
		this(other.matrix.length);
		updateAllCells((r, c, b) -> other.matrix[r][c]);
	}
	
	public Configuration(int size, double aliveProbability) {
		this(size);
		updateAllCells((r, c, b) -> Math.random() < aliveProbability);
	}
	
	public Configuration(int size, Coordinates... alive) {
		this(size);
		for (Coordinates c : alive)
			matrix[c.row][c.column] = true;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		for (int row = 0; row < matrix.length; row++) {
			sb.append("| ");
			for (int column = 0; column < matrix[row].length; column++)
				sb.append(matrix[row][column] ? "*" : "_");
			sb.append(" |\n");
		}
		
		return sb.toString();
	}

	public Configuration evolve() {
		Configuration result = new Configuration(this);
		queryAllCells((r, c, b) -> result.matrix[r][c] = b ? evolveLiveCell(r, c) : evolveDeadCell(r, c));
		return result;
	}

	private boolean evolveDeadCell(int row, int column) { 
		int aliveNeighbors = aliveNeighbors(row, column);
		return aliveNeighbors == 3;
	}

	private boolean evolveLiveCell(int row, int column) {
		int aliveNeighbors = aliveNeighbors(row, column); 
		return aliveNeighbors == 2 || aliveNeighbors == 3;
	}

	private int aliveNeighbors(int row, int column) {
		int aliveNeighbors = 0;
		
		for (int r = Math.max(0, row - 1); r < Math.min(matrix.length, row + 2); r++)
			for (int c = Math.max(0, column - 1); c < Math.min(matrix[row].length, column + 2); c++)
				if ((r != row || c != column) && matrix[r][c])
					aliveNeighbors++;
		
		return aliveNeighbors;
	}
	
	private interface CellUpdater {
		boolean update(int row, int column, boolean currentValue);
	}
	
	private void updateAllCells(CellUpdater updater) {
		queryAllCells((r, c, b) -> matrix[r][c] = updater.update(r, c, b));
	}
	
	public interface CellQuerier {
		void query(int row, int column, boolean currentValue);
	}
	
	public void queryAllCells(CellQuerier querier) {
		for (int row = 0; row < matrix.length; row++)
			for (int column = 0; column < matrix[row].length; column++)
				querier.query(row, column, matrix[row][column]);
	}
}
