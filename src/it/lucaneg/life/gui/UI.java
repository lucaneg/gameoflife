package it.lucaneg.life.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.RepaintManager;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;

import it.lucaneg.life.Life;
import it.lucaneg.life.model.KnownRelativeStates;
import it.lucaneg.life.model.State;
import it.lucaneg.life.model.State.Coordinates;

public class UI extends JFrame {

	private static final String RANDOM_ACTION = "random";
	private static final String BLOCK_ACTION = "block";
	private static final String BEE_HIVE_ACTION = "bee-hive";
	private static final String LOAF_ACTION = "loaf";
	private static final String BOAT_ACTION = "boat";
	private static final String TUB_ACTION = "tub";
	private static final String BLINKER_ACTION = "blinker";
	private static final String TOAD_ACTION = "toad";
	private static final String BEACON_ACTION = "beacon";
	private static final String PULSAR_ACTION = "pulsar";
	private static final String PENTA_DECATHLON_ACTION = "penta-decathlon";
	private static final String GLIDER_ACTION = "glider";
	private static final String LWSS_ACTION = "lwss";
	private static final String MWSS_ACTION = "mwss";
	private static final String HWSS_ACTION = "hwss";
	private static final String R_PENTOMINO_ACTION = "r-pentomino";
	private static final String DIEHARD_ACTION = "diehard";
	private static final String ACORN_ACTION = "acorn";

	private static final long serialVersionUID = -6874281801421132358L;

	private final int automatonSize;
	private final Life game;
	
	private JPanel grid;
	private JLabel generationLabel;
	private JButton start, stop;
	private JSpinner livenessRatio;
	private JSlider speed;
	
	private ButtonGroup executionTypes;
	
	private final Collection<Coordinates> aliveCells;
	
	public UI(Life game, int automatonSize) throws HeadlessException {
		super("Conway's Game of Life");
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		this.automatonSize = automatonSize;
		this.game = game;
		
		this.aliveCells = new ArrayList<>();
		
		init();
	}

	private void init() {
		Container content = getContentPane();
		content.setLayout(new BorderLayout());
		
		grid = new JPanel();
		grid.setLayout(new GridLayout(automatonSize, automatonSize));
		for (int row = 0; row < automatonSize; row++)
			for (int column = 0; column < automatonSize; column++) 
				grid.add(new Cell(), index(row, column));
			
		
		content.add(grid, BorderLayout.CENTER);
		
		generationLabel = new JLabel(" ");
		content.add(generationLabel, BorderLayout.NORTH);
		
		JPanel settings = new JPanel();
		settings.setLayout(new BorderLayout());
		content.add(settings, BorderLayout.EAST);

		addExecutionTypeComponents(settings);
		addCommonComponents(settings);
		
		pack();
	}

	private void addExecutionTypeComponents(JPanel settings) {
		JPanel wrapper = new JPanel();
		JPanel types = new JPanel();
		types.setLayout(new BoxLayout(types, BoxLayout.Y_AXIS));
		types.setBorder(new TitledBorder("Execution types"));
		
		executionTypes = new ButtonGroup();
		
		mkRandomSection(types);
		mkStableFormsSection(types);
		mkOscillatorsSection(types);
		mkShipsSection(types);
		mkMethuselahsSection(types);
		
		wrapper.add(types);
		settings.add(wrapper, BorderLayout.CENTER);
	}

	private void mkMethuselahsSection(JPanel container) {
		JPanel meth = new JPanel(new GridLayout(3, 2)); 
		meth.add(new JLabel("Methuselahs:"));
		meth.add(new JLabel());
		
		mkRadio(meth, R_PENTOMINO_ACTION);
		mkRadio(meth, DIEHARD_ACTION);
		mkRadio(meth, ACORN_ACTION);
		
		container.add(meth);
		container.add(new JSeparator(JSeparator.HORIZONTAL));
	}

	private void mkShipsSection(JPanel container) {
		JPanel ships = new JPanel(new GridLayout(3, 2)); 
		ships.add(new JLabel("Ships:"));
		ships.add(new JLabel());
		
		mkRadio(ships, GLIDER_ACTION);
		mkRadio(ships, LWSS_ACTION);
		mkRadio(ships, MWSS_ACTION);
		mkRadio(ships, HWSS_ACTION);
		
		container.add(ships);
		container.add(new JSeparator(JSeparator.HORIZONTAL));
	}

	private void mkOscillatorsSection(JPanel container) {
		JPanel oscillaltors = new JPanel(new GridLayout(4, 2)); 
		oscillaltors.add(new JLabel("Oscillators:"));
		oscillaltors.add(new JLabel());
		
		mkRadio(oscillaltors, BLINKER_ACTION);
		mkRadio(oscillaltors, TOAD_ACTION);
		mkRadio(oscillaltors, BEACON_ACTION);
		mkRadio(oscillaltors, PULSAR_ACTION);
		mkRadio(oscillaltors, PENTA_DECATHLON_ACTION);
		
		container.add(oscillaltors);
		container.add(new JSeparator(JSeparator.HORIZONTAL));
	}

	private void mkStableFormsSection(JPanel container) {
		JPanel stable = new JPanel(new GridLayout(4, 2)); 
		stable.add(new JLabel("Stable forms:"));
		stable.add(new JLabel());
		
		mkRadio(stable, BLOCK_ACTION);
		mkRadio(stable, BEE_HIVE_ACTION);
		mkRadio(stable, LOAF_ACTION);
		mkRadio(stable, BOAT_ACTION);
		mkRadio(stable, TUB_ACTION);
		
		container.add(stable);
		container.add(new JSeparator(JSeparator.HORIZONTAL));
	}

	private void mkRandomSection(JPanel container) {
		JPanel random = new JPanel(new GridLayout(1, 2));
		
		mkRadio(random, RANDOM_ACTION, true);
		
		JPanel inner = new JPanel(new GridLayout(1, 2));
		JLabel label = new JLabel("Liveness:");
		inner.add(label);
		
		livenessRatio = new JSpinner(new SpinnerNumberModel(0.1, 0.0, 1.0, 0.1));
		inner.add(livenessRatio);
		
		random.add(inner);
		
		container.add(random);
		container.add(new JSeparator(JSeparator.HORIZONTAL));
	}

	private void mkRadio(JPanel root, String action) {
		mkRadio(root, action, false);
	}
	
	private void mkRadio(JPanel root, String action, boolean selected) {
		JRadioButton radio = new JRadioButton(action);
		radio.setSelected(selected); 
		executionTypes.add(radio);
		root.add(radio);
		
		radio.addActionListener(this::start);
	}

	private void addCommonComponents(JPanel settings) {
		JPanel common = new JPanel(new GridLayout(3, 1));
		common.setBorder(new TitledBorder("Common settings"));
		
		JLabel label = new JLabel("Update interval (ms):");
		common.add(label);
		
		speed = new JSlider(JSlider.HORIZONTAL, 0, 2000, 500);
		speed.addChangeListener(this::speedChanged);
		speed.setMinorTickSpacing(100);  
		speed.setMajorTickSpacing(500); 
		speed.setPaintTicks(true);
		speed.setPaintLabels(true);
		
		common.add(speed);
		
		JPanel wrapper = new JPanel();
		JPanel exec = new JPanel();
		exec.setLayout(new GridLayout(1, 2, 20, 0));
		
		start = new JButton("Start");
		start.addActionListener(this::start);
		exec.add(start);
		
		stop = new JButton("Stop");
		stop.setEnabled(false);
		stop.addActionListener(this::stop);
		exec.add(stop);
		
		wrapper.add(exec);
		common.add(wrapper);
		
		settings.add(common, BorderLayout.SOUTH);
	}
	
	private void speedChanged(ChangeEvent event) {
		game.setSpeed(speed.getValue());
	}

	private void stop(ActionEvent event) {
		stop.setEnabled(false); 
		start.setEnabled(true); 
		game.stop();
	}

	private void start(ActionEvent event) {
		if (game.isRunning())
			stop(null);
		
		start.setEnabled(false); 
		stop.setEnabled(true); 
		
		switch (getExecutionAction()) {
		case RANDOM_ACTION:
			game.startRandom((double) livenessRatio.getValue(), speed.getValue());
			break;
		case BLOCK_ACTION:
			game.startWithPattern(speed.getValue(), KnownRelativeStates.BLOCK);
			break;
		case BEE_HIVE_ACTION:
			game.startWithPattern(speed.getValue(), KnownRelativeStates.BEE_HIVE);
			break;
		case LOAF_ACTION:
			game.startWithPattern(speed.getValue(), KnownRelativeStates.LOAF);
			break;
		case BOAT_ACTION:
			game.startWithPattern(speed.getValue(), KnownRelativeStates.BOAT);
			break;
		case TUB_ACTION:
			game.startWithPattern(speed.getValue(), KnownRelativeStates.TUB);
			break;
		case BLINKER_ACTION:
			game.startWithPattern(speed.getValue(), KnownRelativeStates.BLINKER);
			break;
		case TOAD_ACTION:
			game.startWithPattern(speed.getValue(), KnownRelativeStates.TOAD);
			break;
		case BEACON_ACTION:
			game.startWithPattern(speed.getValue(), KnownRelativeStates.BEACON);
			break;
		case PULSAR_ACTION:
			game.startWithPattern(speed.getValue(), KnownRelativeStates.PULSAR);
			break;
		case PENTA_DECATHLON_ACTION:
			game.startWithPattern(speed.getValue(), KnownRelativeStates.PENTA_DECATHLON);
			break;
		case GLIDER_ACTION:
			game.startWithPattern(speed.getValue(), KnownRelativeStates.GLIDER, 5, 5);
			break;
		case LWSS_ACTION:
			game.startWithPattern(speed.getValue(), KnownRelativeStates.LWSS, 5, 5);
			break;
		case MWSS_ACTION:
			game.startWithPattern(speed.getValue(), KnownRelativeStates.MWSS, 5, 5);
			break;
		case HWSS_ACTION:
			game.startWithPattern(speed.getValue(), KnownRelativeStates.HWSS, 5, 5);
			break;
		case R_PENTOMINO_ACTION:
			game.startWithPattern(speed.getValue(), KnownRelativeStates.R_PENTOMINO);
			break;
		case DIEHARD_ACTION:
			game.startWithPattern(speed.getValue(), KnownRelativeStates.DIEHARD);
			break;
		case ACORN_ACTION:
			game.startWithPattern(speed.getValue(), KnownRelativeStates.ACORN, 50, 50);
			break;
		default:
			start.setEnabled(true);
			stop.setEnabled(false);
		}
	}
	
	private String getExecutionAction() {
        for (Enumeration<AbstractButton> buttons = executionTypes.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) 
                return button.getText();
        }

        return "";
    }

	private int index(int row, int column) {
		return row * automatonSize + column;
	}
	
	private int coordinate(int row, int column) {
		// graphical coordinates are swapped wrt the matrix row/column
		return column * automatonSize + row;
	}

	public void show(State state) {
		generationLabel.setText("Generation " + state.getGeneration()); 
		
		Cell cell;
		
		for (Coordinates coord : aliveCells) {
			cell = (Cell) grid.getComponent(coordinate(coord.row, coord.column));
			cell.kill();
			RepaintManager.currentManager(this).markCompletelyClean(cell);
		}
		
		aliveCells.clear();
		
		for (Coordinates coord : state.getAliveCells()) {
			aliveCells.add(coord);
			cell = (Cell) grid.getComponent(coordinate(coord.row, coord.column));
			if (!cell.isAlive())
				cell.summon();
			RepaintManager.currentManager(this).markCompletelyClean(cell);
		}
		
		repaint();
	}
	
	private static class Cell extends JButton {
		private static final long serialVersionUID = -439293389545926307L;

		private static final int SIZE = 5;
		
		private boolean alive;

		private Cell() {
			super();
			
			setEnabled(false);
			setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.LIGHT_GRAY));
			setPreferredSize(new Dimension(SIZE, SIZE));
			
			kill();
		}
		
		public boolean isAlive() {
			return alive;
		}
		
		private void kill() {
			alive = false;
			setBackground(Color.WHITE);
		}
		
		private void summon() {
			alive = true;
			setBackground(Color.BLACK);
		}
	}
}
