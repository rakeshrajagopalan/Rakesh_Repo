package puyopack;

import javax.swing.*;
import javax.sound.midi.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * Game similar to tetris. Two similar faces moves down. If 4 similar colors
 * matches, they are cleared.
 * 
 * @author Rakesh (13-06-07)
 * 
 */

@SuppressWarnings(value = { "unused", "unchecked", "deprecation" })
public class PuyoGame extends JFrame implements KeyListener {

	// /////////////DECLARATION SECTION STARTS/////////////

	private static final long serialVersionUID = 1777092124504094749L;

	private JPanel mainPanel = new JPanel();

	private static final int RED_PUYO = 0;

	private static final int GREEN_PUYO = 1;

	private static final int BLUE_PUYO = 2;

	private static final int YELLOW_PUYO = 3;

	public static ImageIcon redPuyo;

	public static ImageIcon greenPuyo;

	public static ImageIcon bluePuyo;

	public static ImageIcon yellowPuyo;

	private JLabel a0Label = new JLabel();

	private JLabel a1Label = new JLabel();

	private JLabel a2Label = new JLabel();

	private JLabel a3Label = new JLabel();

	private JLabel b0Label = new JLabel();

	private JLabel b1Label = new JLabel();

	private JLabel b2Label = new JLabel();

	private JLabel b3Label = new JLabel();

	private JLabel c0Label = new JLabel();

	private JLabel c1Label = new JLabel();

	private JLabel c2Label = new JLabel();

	private JLabel c3Label = new JLabel();

	private JLabel d0Label = new JLabel();

	private JLabel d1Label = new JLabel();

	private JLabel d2Label = new JLabel();

	private JLabel d3Label = new JLabel();

	private JLabel e0Label = new JLabel();

	private JLabel e1Label = new JLabel();

	private JLabel e2Label = new JLabel();

	private JLabel e3Label = new JLabel();

	private JLabel f0Label = new JLabel();

	private JLabel f1Label = new JLabel();

	private JLabel f2Label = new JLabel();

	private JLabel f3Label = new JLabel();

	private JLabel g0Label = new JLabel();

	private JLabel g1Label = new JLabel();

	private JLabel g2Label = new JLabel();

	private JLabel g3Label = new JLabel();

	private JLabel h0Label = new JLabel();

	private JLabel h1Label = new JLabel();

	private JLabel h2Label = new JLabel();

	private JLabel h3Label = new JLabel();

	private JLabel i0Label = new JLabel();

	private JLabel i1Label = new JLabel();

	private JLabel i2Label = new JLabel();

	private JLabel i3Label = new JLabel();

	private JLabel j0Label = new JLabel();

	private JLabel j1Label = new JLabel();

	private JLabel j2Label = new JLabel();

	private JLabel j3Label = new JLabel();

	private JLabel k0Label = new JLabel();

	private JLabel k1Label = new JLabel();

	private JLabel k2Label = new JLabel();

	private JLabel k3Label = new JLabel();

	private JLabel l0Label = new JLabel();

	private JLabel l1Label = new JLabel();

	private JLabel l2Label = new JLabel();

	private JLabel l3Label = new JLabel();

	private int chosenPuyo;

	private int gridCoordinate;

	private JLabel firstLiveLabel;

	private JLabel secondLiveLabel;

	private ImageIcon firstLiveIcon;

	private ImageIcon secondLiveIcon;

	private String bottom_of_first_puyo;

	private String bottom_of_second_puyo;

	private String whereIsFirstPuyo;

	private String whereIsSecondPuyo;

	private int score;

	private JLabel scoreLabel = new JLabel();

	private String scoreText = "Your Score is: ";

	private int componentCount;

	private int firstBottom;

	private int secondBottom;

	private JLabel[] rowArray;

	private JLabel[] columnArray;

	private long timeOut;

	private int flag = 0;

	private JLabel bottomOneLabel;

	private JLabel bottomTwoLabel;

	private Sequencer player;

	private Sequence seq;

	private Track track;

	private boolean chainPuyoChecker;

	private static final int ZERO_DEGREE = 0;

	private static final int NINTY_DEGREE = 1;

	private static final int ONE_EIGHTY_DEGREE = 2;

	private static final int TWO_SEVENTY_DEGREE = 3;

	private int rotationState = 0;

	private boolean bottomReached = false;

	private static final String LEFT_KEY = "Left";

	private static final String RIGHT_KEY = "Right";

	private JLabel a4Label = new JLabel();

	private JLabel a5Label = new JLabel();

	private JLabel b4Label = new JLabel();

	private JLabel b5Label = new JLabel();

	private JLabel c4Label = new JLabel();

	private JLabel c5Label = new JLabel();

	private JLabel d4Label = new JLabel();

	private JLabel d5Label = new JLabel();

	private JLabel e4Label = new JLabel();

	private JLabel e5Label = new JLabel();

	private JLabel f4Label = new JLabel();

	private JLabel f5Label = new JLabel();

	private JLabel g4Label = new JLabel();

	private JLabel g5Label = new JLabel();

	private JLabel h4Label = new JLabel();

	private JLabel h5Label = new JLabel();

	private JLabel i4Label = new JLabel();

	private JLabel i5Label = new JLabel();

	private JLabel j4Label = new JLabel();

	private JLabel j5Label = new JLabel();

	private JLabel k4Label = new JLabel();

	private JLabel k5Label = new JLabel();

	private JLabel l4Label = new JLabel();

	private JLabel l5Label = new JLabel();

	private JPanel previewPanel = new JPanel();

	private JPanel hugePanel = null;

	private ImageIcon globalNextIcon1 = null;

	private ImageIcon globalNextIcon2 = null;

	private ArrayList iconList = new ArrayList();

	private JLabel previewLabel1 = new JLabel();

	private JLabel previewLabel2 = new JLabel();

	public static final int COMMON_COUNT = 4;

	private long level;

	private static final int levelProgressor = 500;

	private JLabel levelLabel = new JLabel();

	private static final String levelText = "LEVEL: ";

	private int point = 1;

	private int bonusPoint = 5;

	private static boolean paused;

	private Thread movePuyoThread;

	private JLabel pauseLabel = new JLabel();

	// ////////////DECLARATION SECTION ENDS///////////////

	/**
	 * Constructor
	 * 
	 */
	public PuyoGame(long level) {
		try {
			if (level == 1) {
				timeOut = 1000;
			} else if (level == 2) {
				timeOut = 500;
			} else {
				timeOut = 100;
			}
			this.level = level;
			player = MidiSystem.getSequencer();
			player.open();
			seq = new Sequence(Sequence.PPQ, 4);
			track = seq.createTrack();
			getContentPane().setLayout(new BorderLayout());
			setResizable(false);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setTitle("Puyo Game");
			setSize(300, 450);
			constructGUI();
		} catch (Exception ex) {

		}
	}

	/**
	 * Constructs the GUI(12X6 Frame)
	 * 
	 */
	private void constructGUI() {
		mainPanel.setLayout(new GridLayout(PuyoUtilities.ROWS,
				PuyoUtilities.COLUMNS));
		previewPanel.setBackground(Color.BLUE);
		previewPanel.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridx = 0;
		constraints.gridy = 0;
		previewPanel.add(new JLabel("Sneak Peek"), constraints);
		constraints.gridx = 0;
		constraints.gridy = 1;
		previewPanel.add(previewLabel1, constraints);
		constraints.gridx = 1;
		constraints.gridy = 1;
		previewPanel.add(previewLabel2, constraints);
		constraints.gridx = 0;
		constraints.gridy = 2;
		previewPanel.add(pauseLabel, constraints);
		hugePanel = new JPanel(new BorderLayout());
		mainPanel.setBackground(Color.BLACK);
		redPuyo = new ImageIcon("puyo_red.png");
		redPuyo.setDescription("Red Puyo");
		greenPuyo = new ImageIcon("puyo_green.png");
		greenPuyo.setDescription("Green Puyo");
		bluePuyo = new ImageIcon("puyo_blue.png");
		bluePuyo.setDescription("Blue Puyo");
		yellowPuyo = new ImageIcon("puyo_yellow.png");
		yellowPuyo.setDescription("Yellow Puyo");
		populateGrid();
		JPanel trackPanel = new JPanel();
		trackPanel.setLayout(new BorderLayout());
		trackPanel.add(scoreLabel, BorderLayout.WEST);
		trackPanel.add(levelLabel, BorderLayout.EAST);
		levelLabel.setText(levelText + level);
		scoreLabel.setText(scoreText + score);
		hugePanel.add(mainPanel, BorderLayout.CENTER);
		hugePanel.add(trackPanel, BorderLayout.SOUTH);
		this.getContentPane().add(hugePanel, BorderLayout.EAST);
		this.getContentPane().add(previewPanel, BorderLayout.WEST);
		componentCount = mainPanel.getComponentCount();
		firstBottom = secondBottom = componentCount;
		rowArray = PuyoUtilities.componentsOfThePanel(mainPanel);
		columnArray = PuyoUtilities.columnArray(mainPanel);
		this.addKeyListener(this);
		choosePuyo();
	}

	/**
	 * 12X6 Labels are added to the grid
	 * 
	 */
	private void populateGrid() {
		// //// CONSTRUCTING FIRST ROW//////////////
		mainPanel.add(a0Label);
		a0Label.setName("A0Label");
		mainPanel.add(a1Label);
		a1Label.setName("A1Label");
		mainPanel.add(a2Label);
		a2Label.setName("A2Label");
		mainPanel.add(a3Label);
		a3Label.setName("A3Label");
		mainPanel.add(a4Label);
		a4Label.setName("A4Label");
		mainPanel.add(a5Label);
		a5Label.setName("A5Label");
		// //// CONSTRUCTING SECOND ROW//////////////
		mainPanel.add(b0Label);
		b0Label.setName("B0Label");
		mainPanel.add(b1Label);
		b1Label.setName("B1Label");
		mainPanel.add(b2Label);
		b2Label.setName("B2Label");
		mainPanel.add(b3Label);
		b3Label.setName("B3Label");
		mainPanel.add(b4Label);
		b4Label.setName("B4Label");
		mainPanel.add(b5Label);
		b5Label.setName("B5Label");
		// //// CONSTRUCTING THIRD ROW//////////////
		mainPanel.add(c0Label);
		c0Label.setName("C0Label");
		mainPanel.add(c1Label);
		c1Label.setName("C1Label");
		mainPanel.add(c2Label);
		c2Label.setName("C2Label");
		mainPanel.add(c3Label);
		c3Label.setName("C3Label");
		mainPanel.add(c4Label);
		c4Label.setName("C4Label");
		mainPanel.add(c5Label);
		c5Label.setName("C5Label");
		// //// CONSTRUCTING FOURTH ROW//////////////
		mainPanel.add(d0Label);
		d0Label.setName("D0Label");
		mainPanel.add(d1Label);
		d1Label.setName("D1Label");
		mainPanel.add(d2Label);
		d2Label.setName("D2Label");
		mainPanel.add(d3Label);
		d3Label.setName("D3Label");
		mainPanel.add(d4Label);
		d4Label.setName("D4Label");
		mainPanel.add(d5Label);
		d5Label.setName("D5Label");
		// //// CONSTRUCTING FIFTH ROW//////////////
		mainPanel.add(e0Label);
		e0Label.setName("E0Label");
		mainPanel.add(e1Label);
		e1Label.setName("E1Label");
		mainPanel.add(e2Label);
		e2Label.setName("E2Label");
		mainPanel.add(e3Label);
		e3Label.setName("E3Label");
		mainPanel.add(e4Label);
		e4Label.setName("E4Label");
		mainPanel.add(e5Label);
		e5Label.setName("E5Label");
		// ////CONSTRUCTING SIXTH ROW//////////////
		mainPanel.add(f0Label);
		f0Label.setName("F0Label");
		mainPanel.add(f1Label);
		f1Label.setName("F1Label");
		mainPanel.add(f2Label);
		f2Label.setName("F2Label");
		mainPanel.add(f3Label);
		f3Label.setName("F3Label");
		mainPanel.add(f4Label);
		f4Label.setName("F4Label");
		mainPanel.add(f5Label);
		f5Label.setName("F5Label");
		// ////CONSTRUCTING SEVENTH ROW//////////////
		mainPanel.add(g0Label);
		g0Label.setName("G0Label");
		mainPanel.add(g1Label);
		g1Label.setName("G1Label");
		mainPanel.add(g2Label);
		g2Label.setName("G2Label");
		mainPanel.add(g3Label);
		g3Label.setName("G3Label");
		mainPanel.add(g4Label);
		g4Label.setName("G4Label");
		mainPanel.add(g5Label);
		g5Label.setName("G5Label");
		// ////CONSTRUCTING EIGHT ROW//////////////
		mainPanel.add(h0Label);
		h0Label.setName("H0Label");
		mainPanel.add(h1Label);
		h1Label.setName("H1Label");
		mainPanel.add(h2Label);
		h2Label.setName("H2Label");
		mainPanel.add(h3Label);
		h3Label.setName("H3Label");
		mainPanel.add(h4Label);
		h4Label.setName("H4Label");
		mainPanel.add(h5Label);
		h5Label.setName("H5Label");
		// ////CONSTRUCTING NINTH ROW//////////////
		mainPanel.add(i0Label);
		i0Label.setName("I0Label");
		mainPanel.add(i1Label);
		i1Label.setName("I1Label");
		mainPanel.add(i2Label);
		i2Label.setName("I2Label");
		mainPanel.add(i3Label);
		i3Label.setName("I3Label");
		mainPanel.add(i4Label);
		i4Label.setName("I4Label");
		mainPanel.add(i5Label);
		i5Label.setName("I5Label");
		// ////CONSTRUCTING TENTH ROW//////////////
		mainPanel.add(j0Label);
		j0Label.setName("J0Label");
		mainPanel.add(j1Label);
		j1Label.setName("J1Label");
		mainPanel.add(j2Label);
		j2Label.setName("J2Label");
		mainPanel.add(j3Label);
		j3Label.setName("J3Label");
		mainPanel.add(j4Label);
		j4Label.setName("J4Label");
		mainPanel.add(j5Label);
		j5Label.setName("J5Label");
		// ////CONSTRUCTING ELEVENTH ROW//////////////
		mainPanel.add(k0Label);
		k0Label.setName("K0Label");
		mainPanel.add(k1Label);
		k1Label.setName("K1Label");
		mainPanel.add(k2Label);
		k2Label.setName("K2Label");
		mainPanel.add(k3Label);
		k3Label.setName("K3Label");
		mainPanel.add(k4Label);
		k4Label.setName("K4Label");
		mainPanel.add(k5Label);
		k5Label.setName("K5Label");
		// ////CONSTRUCTING TWELTH ROW//////////////
		mainPanel.add(l0Label);
		l0Label.setName("L0Label");
		mainPanel.add(l1Label);
		l1Label.setName("L1Label");
		mainPanel.add(l2Label);
		l2Label.setName("L2Label");
		mainPanel.add(l3Label);
		l3Label.setName("L3Label");
		mainPanel.add(l4Label);
		l4Label.setName("L4Label");
		mainPanel.add(l5Label);
		l5Label.setName("L5Label");
		setPoints();
	}

	private void setPoints() {
		point = point * (int) level;
		bonusPoint = bonusPoint * (int) level;
	}

	/**
	 * Method that resets level and timeout after a particular level has been
	 * reached.
	 * 
	 * @param timeOut
	 * @param level
	 */
	private void resetTimeOut(long timeOut, long level) {
		this.timeOut = timeOut;
		this.level = level;
		levelLabel.setText(levelText + level);
		setPoints();
	}

	/**
	 * Method that returns time out associated for a particular level.
	 * 
	 * @param level
	 * @return
	 */
	private long getTimeOut(long level) {
		if (level == 1) {
			return 1000;
		} else if (level == 2) {
			return 500;
		} else {
			return 100;
		}
	}

	/**
	 * Method that resets certain values after puyo set reaches bottom.
	 * 
	 */
	private void resetValues() {
		if (scoreChecker()) {
			long lev = level;
			if (level != 3) {
				long temp = getTimeOut(lev + 1);
				resetTimeOut(temp, lev + 1);
			}
		}
		flag = 0;
		rotationState = 0;
		bottomReached = false;
		setCurrentPuyos();
	}

	/**
	 * The two puyos are chosen at random
	 * 
	 */
	private void choosePuyo() {
		/**
		 * This method chooses two icons in a random manner. This is done to
		 * make the game more lively.
		 */
		chosenPuyo = (int) Math.round(Math.random() * 3);
		switch (chosenPuyo) {
		case RED_PUYO:
			firstLiveIcon = redPuyo;
			break;
		case GREEN_PUYO:
			firstLiveIcon = greenPuyo;
			break;
		case BLUE_PUYO:
			firstLiveIcon = bluePuyo;
			break;
		case YELLOW_PUYO:
			firstLiveIcon = yellowPuyo;
			break;
		}
		int secondPuyo = (int) Math.round(Math.random() * 3);
		switch (secondPuyo) {
		case RED_PUYO:
			secondLiveIcon = redPuyo;
			break;
		case GREEN_PUYO:
			secondLiveIcon = greenPuyo;
			break;
		case BLUE_PUYO:
			secondLiveIcon = bluePuyo;
			break;
		case YELLOW_PUYO:
			secondLiveIcon = yellowPuyo;
			break;
		}
		chooseCoordinates();
		sneakPeek();
	}

	/**
	 * Method that chooses next set of puyos to show preview to the user.
	 * 
	 * @return
	 */
	private ArrayList nextPuyoSet() {
		iconList.clear();
		int puyoOne = (int) Math.round(Math.random() * 3);
		switch (puyoOne) {
		case RED_PUYO:
			globalNextIcon1 = redPuyo;
			break;
		case GREEN_PUYO:
			globalNextIcon1 = greenPuyo;
			break;
		case BLUE_PUYO:
			globalNextIcon1 = bluePuyo;
			break;
		case YELLOW_PUYO:
			globalNextIcon1 = yellowPuyo;
			break;
		}
		puyoOne = (int) Math.round(Math.random() * 3);
		switch (puyoOne) {
		case RED_PUYO:
			globalNextIcon2 = redPuyo;
			break;
		case GREEN_PUYO:
			globalNextIcon2 = greenPuyo;
			break;
		case BLUE_PUYO:
			globalNextIcon2 = bluePuyo;
			break;
		case YELLOW_PUYO:
			globalNextIcon2 = yellowPuyo;
			break;
		}
		iconList.add(globalNextIcon1);
		iconList.add(globalNextIcon2);
		return iconList;
	}

	/**
	 * Method that shows the next set of puyos to the user.
	 * 
	 */
	private void sneakPeek() {
		ArrayList list = nextPuyoSet();
		previewLabel1.setIcon((ImageIcon) list.get(0));
		previewLabel2.setIcon((ImageIcon) list.get(1));
		previewPanel.repaint();
	}

	/**
	 * Method that picks up puyos from the already chosen puyos.
	 * 
	 */
	private void setCurrentPuyos() {
		try {
			firstLiveIcon = (ImageIcon) iconList.get(0);
			secondLiveIcon = (ImageIcon) iconList.get(1);
			chooseCoordinates();
			sneakPeek();
		} catch (Exception ex) {

		}
	}

	/**
	 * Method to determine the starting point of the puyos
	 * 
	 */
	private void chooseCoordinates() {
		/**
		 * This method determines from where the puyos will start to fall. Here,
		 * Im selecting random coordinates to make the game more lively.
		 */
		gridCoordinate = (int) Math.round(Math.random()
				* (PuyoUtilities.COLUMNS - 2));
		switch (gridCoordinate) {
		case 0:
			firstLiveLabel = (JLabel) mainPanel.getComponent(gridCoordinate);
			secondLiveLabel = (JLabel) mainPanel
					.getComponent(gridCoordinate + 1);
			break;
		case 1:
			firstLiveLabel = (JLabel) mainPanel.getComponent(gridCoordinate);
			secondLiveLabel = (JLabel) mainPanel
					.getComponent(gridCoordinate + 1);
			break;
		case 2:
			firstLiveLabel = (JLabel) mainPanel.getComponent(gridCoordinate);
			secondLiveLabel = (JLabel) mainPanel
					.getComponent(gridCoordinate + 1);
			break;
		case 3:
			firstLiveLabel = (JLabel) mainPanel.getComponent(gridCoordinate);
			secondLiveLabel = (JLabel) mainPanel
					.getComponent(gridCoordinate + 1);
			break;
		case 4:
			firstLiveLabel = (JLabel) mainPanel.getComponent(gridCoordinate);
			secondLiveLabel = (JLabel) mainPanel
					.getComponent(gridCoordinate + 1);
			break;
		}
		bottom_of_first_puyo = calculateBottomOfFirstPuyo(gridCoordinate);
		bottom_of_second_puyo = calculateBottomOfSecondPuyo(gridCoordinate);
		assignPuyoToLabel();
	}

	/**
	 * Assign selected puyos to selected labels
	 */
	private void assignPuyoToLabel() {
		/**
		 * Here, I have assigned the selected icons to the selected labels.
		 */
		firstLiveLabel.setIcon(firstLiveIcon);
		secondLiveLabel.setIcon(secondLiveIcon);
		movePuyoThread = new Thread(new Runnable() {
			public void run() {
				moveDownFirstPuyo();
			}
		});
		movePuyoThread.start();
	}

	/**
	 * Method to move down puyo
	 * 
	 */
	private void moveDownFirstPuyo() {
		/**
		 * This is the core method of the application. In this method, a Thread
		 * is started and for every time out, it clears the icon present in the
		 * current label and resets it in the next label down the row. If it
		 * happens so that one of the puyos has landed on top of something else
		 * while the other puyo still has to reach bottom, this code will be
		 * called. Greater of the two puyoa will be calculated and the greater
		 * puyo alone will be sent, while the lesser puyo is made stationary
		 */
		String other = null;
		try {
			/**
			 * Checking whether the puyo has reached the bottom. If true, then,
			 * next set of puyos starts to fall.
			 */
			while (!hasFirstPuyoReachedBottom()
					&& !hasSecondPuyoReachedBottom()) {
				Thread.sleep(timeOut);
				clearPreviousAndSetNext();
			}
			other = PuyoUtilities.whoIsGreater(bottom_of_first_puyo,
					bottom_of_second_puyo);
			if (!other.equalsIgnoreCase("equal")) {
				bottomReached = true;
				if (other.equalsIgnoreCase("first")) {
					moveDownOtherPuyo(firstLiveLabel, bottom_of_first_puyo,
							other);
				} else {
					moveDownOtherPuyo(secondLiveLabel, bottom_of_second_puyo,
							other);
				}
			}
			clearAllSimilarPuyos();
			if (chainPuyoChecker) {
				miscCheck();
			}
			if (!isTopReached()) {
				ShortMessage a = new ShortMessage();
				a.setMessage(144, 5, 100, 100);
				MidiEvent one = new MidiEvent(a, 3);
				track.add(one);
				player.setSequence(seq);
				player.start();
				resetValues();
			} else {
				ShortMessage a = new ShortMessage();
				a.setMessage(128, 1, 44, 100);
				MidiEvent one = new MidiEvent(a, 1);
				track.add(one);
				player.setSequence(seq);
				player.start();
				// JOptionPane.showMessageDialog(this, "Game Over!");
				// System.exit(0);
				askForInput();
			}
			System.gc();
		} catch (Exception ex) {

			if (!isTopReached()) {
				resetValues();
			} else {
				// JOptionPane.showMessageDialog(this, "Game Over!");
				// System.exit(0);
				askForInput();
			}
		}
	}

	/**
	 * Method that clear all shapes
	 * 
	 */
	private void clearAllSimilarPuyos() {
		removeSquarePuyos();
		detectColumnShapes();
		detectRowShapes();
		clearSimilarColumnPuyos();
		clearSimilarRowPuyos();
	}

	/**
	 * Method to move down the lesser puyo
	 * 
	 * @param label
	 * @param bottom
	 * @param other
	 */
	private void moveDownOtherPuyo(JLabel label, String bottom, String other) {
		/**
		 * If it happens so that one of the puyos has landed on top of something
		 * else while the other puyo still has to reach bottom, this code will
		 * be called. Greater of the two puyos will be calculated and the
		 * greater puyo alone will be sent, while the lesser puyo is made
		 * stationary. Here, I have synchronized the code, so that next set of
		 * puyos will be called only after this Thread has released its lock
		 */
		synchronized (this) {
			try {
				if (other.equalsIgnoreCase("first")) {
					while (!firstLiveLabel.getName().equalsIgnoreCase(bottom)) {
						Thread.sleep(timeOut);
						clearPreviousAndSetNext(other);
					}
				} else {
					while (!secondLiveLabel.getName().equalsIgnoreCase(bottom)) {
						Thread.sleep(timeOut);
						clearPreviousAndSetNext(other);
					}
				}
			} catch (InterruptedException e) {
				JOptionPane.showMessageDialog(this, "Sorry! Error occured");
				System.exit(0);
			}
		}
	}

	/**
	 * Method clears previous and sets next
	 * 
	 * @param name
	 */
	private void clearPreviousAndSetNext(String name) {
		/**
		 * This is one of the core methods of the game. When the puyo(s) have
		 * not reached their bottom and the Thread has slept for the timeout, it
		 * wakes up and call this method. This method clears the icon in the
		 * current label and set the same icon to the next label down the lane.
		 * As this happens after some timeout, this gives an illusion to the
		 * user that the icons are falling down.
		 */
		if (name.equalsIgnoreCase("first")) {
			updateFirstPuyo();
			for (int i = 0; i < componentCount; i++) {
				JLabel label = (JLabel) mainPanel.getComponent(i);
				JLabel nextLabel = (JLabel) mainPanel.getComponent(i
						+ PuyoUtilities.COLUMNS);
				String nameLabel = label.getName();
				if (whereIsFirstPuyo.equalsIgnoreCase(nameLabel)) {
					label.setIcon(null);
					nextLabel.setIcon(firstLiveIcon);
					firstLiveLabel = nextLabel;
					break;
				}
			}
		} else {
			updateSecondPuyo();
			for (int i = 0; i < componentCount; i++) {
				JLabel label = (JLabel) mainPanel.getComponent(i);
				JLabel nextLabel = (JLabel) mainPanel.getComponent(i
						+ PuyoUtilities.COLUMNS);
				String nameLabel = label.getName();
				if (whereIsSecondPuyo.equalsIgnoreCase(nameLabel)) {
					label.setIcon(null);
					nextLabel.setIcon(secondLiveIcon);
					secondLiveLabel = nextLabel;
					break;
				}
			}
		}
	}

	/**
	 * This method clears similar row puyos
	 * 
	 */
	private void clearSimilarRowPuyos() {
		/**
		 * This method again is a core method in the game. This methid clears
		 * icons of the same colour in a row. Here, Im iterating through all the
		 * components of the panel. Then, im collecting the icons in them. If a
		 * label contains icons of similar colors, it will increase the
		 * corresponding count and if the count is four, Im clearing the 4
		 * icons.
		 * 
		 */
		try {
			JLabel[] componentOfPanel = PuyoUtilities
					.componentsOfThePanel(mainPanel);
			OUTER: for (int i = 0; i < componentOfPanel.length; i += PuyoUtilities.COLUMNS) {
				INNER: for (int j = 0; j < PuyoUtilities.COLUMNS; j++) {
					int redCount = 0;
					int greenCount = 0;
					int blueCount = 0;
					int yellowCount = 0;
					int temp = i + j;
					SUBINNER: for (int k = 0; k < COMMON_COUNT; k++) {
						JLabel label4 = componentOfPanel[temp];
						ImageIcon icon = (ImageIcon) label4.getIcon();
						if (icon != null && icon.equals(redPuyo)) {
							redCount = redCount + 1;
						} else if (icon != null && icon.equals(greenPuyo)) {
							blueCount = blueCount + 1;
						} else if (icon != null && icon.equals(bluePuyo)) {
							greenCount = greenCount + 1;
						} else if (icon != null && icon.equals(yellowPuyo)) {
							yellowCount = yellowCount + 1;
						}
						if (redCount == COMMON_COUNT
								|| blueCount == COMMON_COUNT
								|| greenCount == COMMON_COUNT
								|| yellowCount == COMMON_COUNT) {
							JLabel label3 = PuyoUtilities.getPrevRowElement(
									mainPanel, label4.getName());
							JLabel label2 = PuyoUtilities.getPrevRowElement(
									mainPanel, label3.getName());
							JLabel label1 = PuyoUtilities.getPrevRowElement(
									mainPanel, label2.getName());
							ArrayList labelList = new ArrayList();
							labelList.add(label1);
							labelList.add(label2);
							labelList.add(label3);
							labelList.add(label4);
							processRowLabels(labelList);
						}
						if (label4.getName().substring(1, 2).equalsIgnoreCase(
								"5")) {
							continue OUTER;
						}
						temp += 1;
					}
				}
			}
		} catch (Exception ex) {

		}
	}

	/**
	 * Method that clears off accumulated row labels and awards points to the
	 * user.
	 * 
	 * @param list
	 */
	private void processRowLabels(ArrayList list) {
		JLabel label1 = (JLabel) list.get(0);
		ImageIcon commonIcon = (ImageIcon) label1.getIcon();
		JLabel label2 = (JLabel) list.get(1);
		JLabel label3 = (JLabel) list.get(2);
		JLabel label4 = (JLabel) list.get(3);
		JLabel label5 = null;
		JLabel label6 = null;
		String name = label1.getName().substring(1, 2);
		if (name.equalsIgnoreCase("0")) {
			label5 = PuyoUtilities.getNextRowElement(mainPanel, label4
					.getName());
			label6 = PuyoUtilities.getNextRowElement(mainPanel, label5
					.getName());
		} else if (name.equalsIgnoreCase("1")) {
			label5 = PuyoUtilities.getPrevRowElement(mainPanel, label1
					.getName());
			label6 = PuyoUtilities.getNextRowElement(mainPanel, label4
					.getName());
		} else if (name.equalsIgnoreCase("2")) {
			label5 = PuyoUtilities.getPrevRowElement(mainPanel, label1
					.getName());
			label6 = PuyoUtilities.getPrevRowElement(mainPanel, label5
					.getName());
		}
		if (label5 != null && label5.getIcon() != null
				&& label5.getIcon().equals(commonIcon)) {
			label5.setIcon(null);
			score = score + bonusPoint;
			if (label6 != null && label6.getIcon() != null
					&& label6.getIcon().equals(commonIcon)) {
				label6.setIcon(null);
				score = score + bonusPoint;
			}
		}
		label1.setIcon(null);
		label2.setIcon(null);
		label3.setIcon(null);
		label4.setIcon(null);
		score = score + (point * 4);
		scoreLabel.setText(scoreText + score);
		fillVoid();
	}

	/**
	 * This method clears similar column puyos
	 * 
	 */
	private void clearSimilarColumnPuyos() {
		/**
		 * This method again is a core method in the game. This methid clears
		 * icons of the same colour in a row.
		 */
		JLabel[] labels = PuyoUtilities.componentsOfThePanel(mainPanel);
		ImageIcon icon = null;
		JLabel[] dummy = null;
		ArrayList dummyList = new ArrayList();
		JLabel dummyLabel = new JLabel();
		OUTER: for (int i = 0; i < PuyoUtilities.COLUMNS; i++) {
			/**
			 * Here, Im iterating through all the labels in a column. Then, im
			 * collecting the icons in them. If a label contains icons of
			 * similar colors, it will increase the corresponding count and if
			 * the count is four, Im clearing the 4 icons.
			 */
			JLabel[] toClear = new JLabel[PuyoUtilities.ROWS];
			int var = 0;
			INNER: for (int j = 0; j < componentCount; j = j
					+ PuyoUtilities.COLUMNS) {
				toClear[var] = labels[i + j];
				var = var + 1;
			}
			BEGIN: for (int x = 0; x < (toClear.length - 3); x++) {
				int start = x;
				int redCount = 0;
				int greenCount = 0;
				int blueCount = 0;
				int yellowCount = 0;
				IN: for (int y = 0; y < 4; y++) {
					icon = (ImageIcon) toClear[x + y].getIcon();
					if (icon != null && icon.equals(redPuyo)) {
						redCount = redCount + 1;
					} else if (icon != null && icon.equals(greenPuyo)) {
						greenCount = greenCount + 1;
					} else if (icon != null && icon.equals(bluePuyo)) {
						blueCount = blueCount + 1;
					} else if (icon != null && icon.equals(yellowPuyo)) {
						yellowCount = yellowCount + 1;
					}
					if (redCount == 4 || blueCount == 4 || greenCount == 4
							|| yellowCount == 4) {
						dummy = new JLabel[4];
						ImageIcon dummyIcon = null;
						int cnt = 0;
						for (int z = start; z < (start + 4); z++) {
							dummy[cnt] = toClear[z];
							ImageIcon testicon = (ImageIcon) ((JLabel) toClear[z])
									.getIcon();
							if (testicon != null) {
								dummyIcon = testicon;
							}
							toClear[z].setIcon(null);
							score = score + point;
							scoreLabel.setText(scoreText + score);
							cnt = cnt + 1;
						}
						for (int j = 0; j < dummy.length; j++) {
							dummyList.add(dummy[j]);
						}
						dummyLabel = PuyoUtilities.checkForMorePuyos(dummyList,
								mainPanel, dummyIcon);
						if (dummyLabel != null && dummyLabel.getIcon() != null
								&& dummyLabel.getIcon().equals(dummyIcon)) {
							dummyLabel.setIcon(null);
							score = score + bonusPoint;
							scoreLabel.setText(scoreText + score);
						}
						fillVoid();
						chainPuyoChecker = true;
						moveDownHangingColumnPuyos(dummy, gridCoordinate);
						break;
					} else {
						continue IN;
					}
				}
			}
		}
	}

	/**
	 * Method move down hanging column puyos
	 * 
	 */
	private void moveDownHangingColumnPuyos(JLabel[] array, int coordinate) {
		/**
		 * This method remembers the top and bottom of the cleared puyos. Then,
		 * if there are puyos that are on top of the cleared puyo, it moves it
		 * down.
		 */
		JLabel topLabel = array[0];
		JLabel bottomLabel = array[3];
		ArrayList elementsOfColumn = PuyoUtilities
				.getElementsOfCoordinate(mainPanel, Integer.parseInt(topLabel
						.getName().substring(1, 2)));
		JLabel dummy = null;
		LOOP: for (int i = 0; i < elementsOfColumn.size(); i++) {
			dummy = (JLabel) elementsOfColumn.get(i);
			if (dummy.equals(topLabel)) {
				dummy = (JLabel) elementsOfColumn.get(i - 1);
				if (dummy.getIcon() != null) {
					bottomLabel.setIcon(dummy.getIcon());
					dummy.setIcon(null);
					break;
				}
			} else {
				continue LOOP;
			}
		}
	}

	/**
	 * Method to clear previous label and set next label
	 * 
	 */
	private void clearPreviousAndSetNext() {
		/**
		 * This is one of the core methods of the game. When the puyo(s) have
		 * not reached their bottom and the Thread has slept for the timeout, it
		 * wakes up and call this method. This method clears the icon in the
		 * current label and set the same icon to the next label down the lane.
		 * As this happens after some timeout, this gives an illusion to the
		 * user that the icons are falling down.
		 */
		try {
			if (rotationState != TWO_SEVENTY_DEGREE) {
				String firstName = firstLiveLabel.getName();
				String secondName = secondLiveLabel.getName();
				for (int i = 0; i < componentCount; i++) {
					JLabel label = (JLabel) mainPanel.getComponent(i);
					JLabel nextLabel = (JLabel) mainPanel.getComponent(i
							+ PuyoUtilities.COLUMNS);
					String name = label.getName();
					if (firstName.equalsIgnoreCase(name)) {
						label.setIcon(null);
						nextLabel.setIcon(firstLiveIcon);
						firstLiveLabel = nextLabel;
						break;
					}
				}
				for (int i = 0; i < componentCount; i++) {
					JLabel label = (JLabel) mainPanel.getComponent(i);
					JLabel nextLabel = (JLabel) mainPanel.getComponent(i
							+ PuyoUtilities.COLUMNS);
					String name = label.getName();
					if (secondName.equalsIgnoreCase(name)) {
						label.setIcon(null);
						nextLabel.setIcon(secondLiveIcon);
						secondLiveLabel = nextLabel;
						break;
					}
				}
			} else {
				String firstName = firstLiveLabel.getName();
				String secondName = secondLiveLabel.getName();
				for (int i = 0; i < componentCount; i++) {
					JLabel label = (JLabel) mainPanel.getComponent(i);
					JLabel nextLabel = (JLabel) mainPanel.getComponent(i
							+ PuyoUtilities.COLUMNS);
					String name = label.getName();
					if (secondName.equalsIgnoreCase(name)) {
						label.setIcon(null);
						nextLabel.setIcon(secondLiveIcon);
						secondLiveLabel = nextLabel;
						break;
					}
				}
				for (int i = 0; i < componentCount; i++) {
					JLabel label = (JLabel) mainPanel.getComponent(i);
					JLabel nextLabel = (JLabel) mainPanel.getComponent(i
							+ PuyoUtilities.COLUMNS);
					String name = label.getName();
					if (firstName.equalsIgnoreCase(name)) {
						label.setIcon(null);
						nextLabel.setIcon(firstLiveIcon);
						firstLiveLabel = nextLabel;
						break;
					}
				}
			}
		} catch (Exception ex) {

		}
	}

	/**
	 * Method to find out whether bottom is reached
	 */
	private boolean hasFirstPuyoReachedBottom() {
		whereIsFirstPuyo = whereamiFirstPuyo();
		if (whereIsFirstPuyo.equalsIgnoreCase(bottom_of_first_puyo)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Method to find out whether bottom is reached
	 * 
	 * @return
	 */
	private boolean hasSecondPuyoReachedBottom() {
		whereIsSecondPuyo = whereamiSecondPuyo();
		if (whereIsSecondPuyo.equalsIgnoreCase(bottom_of_second_puyo)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Method that indicates the whereabouts of puyo
	 * 
	 * @return
	 */
	private String whereamiFirstPuyo() {
		return firstLiveLabel.getName();
	}

	/**
	 * Method that indicates the whereabouts of puyo
	 * 
	 * @return
	 */
	private String whereamiSecondPuyo() {
		return secondLiveLabel.getName();
	}

	/**
	 * Method to determine whether top is reached
	 */
	private boolean isTopReached() {
		/**
		 * This method checks for settled icons in labels in a row-wise manner.
		 * If even one label in each row is not null, it increments the count of
		 * the puyoCountOfRow. If puyoCountOfRow reaches 8, which means all the
		 * rows have puyos and no space for further puyos to fall and hence
		 * terminates the game.
		 */
		int puyoCountOfRow = 0;
		JLabel tester = null;
		OUTER: for (int i = 0; i < componentCount; i = i
				+ PuyoUtilities.COLUMNS) {
			INNER: for (int j = 0; j < PuyoUtilities.COLUMNS; j++) {
				tester = (JLabel) mainPanel.getComponent(i + j);
				if (tester.getIcon() != null) {
					puyoCountOfRow = puyoCountOfRow + 1;
					break INNER;
				} else {
					continue INNER;
				}
			}
		}
		if (puyoCountOfRow == PuyoUtilities.ROWS) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Method to calculate the bottom for the given icon
	 * 
	 */
	private String calculateBottomOfFirstPuyo(int firstCoordinate) {
		/**
		 * This method calculates the bottommost label to which icon has to
		 * travel before the next set of icons fall.
		 */
		String name = null;
		name = firstLiveLabel.getName().substring(1, 2);
		JLabel label = PuyoUtilities.bottommostColumnElement(firstLiveLabel,
				mainPanel);
		name = label.getName();
		return name;
	}

	/**
	 * Method to calculate bottom of puyo two.
	 * 
	 * @return
	 */
	private String calculateBottomOfSecondPuyo(int firstCoordinate) {
		/**
		 * This method calculates the bottommost label to which icon has to
		 * travel before the next set of icons fall.
		 */
		String name = null;
		name = secondLiveLabel.getName().substring(1, 2);
		JLabel label = PuyoUtilities.bottommostColumnElement(secondLiveLabel,
				mainPanel);
		name = label.getName();
		return name;
	}

	/**
	 * Method, which alters the coordinates based on the user selection
	 * 
	 * @param firstCoordinate
	 * @param key
	 */
	private void refreshCoordinates(int firstCoordinate, String key) {
		if (key.equalsIgnoreCase("Left")) {
			if (gridCoordinate != 0) {
				gridCoordinate = gridCoordinate - 1;
			}
		} else if (key.equalsIgnoreCase("Right")) {
			if (gridCoordinate != (PuyoUtilities.COLUMNS - 1)) {
				gridCoordinate = gridCoordinate + 1;
			}
		} else if (key.equalsIgnoreCase("Downhorizontal")) {
			gridCoordinate = gridCoordinate - 1;
		} else if (key.equalsIgnoreCase("Downvertical")) {
			gridCoordinate = gridCoordinate + 1;
		}
	}

	/**
	 * Method to keep track of first puyo
	 * 
	 */
	private void updateFirstPuyo() {
		whereIsFirstPuyo = firstLiveLabel.getName();
	}

	/**
	 * Method to keep track of second puyo
	 */
	private void updateSecondPuyo() {
		whereIsSecondPuyo = secondLiveLabel.getName();
	}

	public void keyTyped(KeyEvent ke) {

	}

	/**
	 * This method listens to the user movements and toggles the Puyo.
	 * 
	 */
	public void keyPressed(KeyEvent ke) {
		if (ke.getKeyCode() == KeyEvent.VK_UP) {
			if (goAhead(rotationState)) {
				upMethod();
			}
		} else if (ke.getKeyCode() == KeyEvent.VK_LEFT) {
			if (validateMovement(firstLiveLabel, secondLiveLabel, rotationState)) {
				leftMethod();
			}
		} else if (ke.getKeyCode() == KeyEvent.VK_RIGHT) {
			if (validateMovement(firstLiveLabel, secondLiveLabel, rotationState)) {
				rightMethod();
			}
		} else if (ke.getKeyCode() == KeyEvent.VK_DOWN) {
			if (goAhead(rotationState)) {
				downMethod();
			}
		} else if (ke.getKeyCode() == KeyEvent.VK_P) {
			try {
				paused = !paused;
				if (paused) {
					pauseGame();
				} else {
					releaseGame();
				}
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
		}
	}

	public void keyReleased(KeyEvent ke) {

	}

	private void releaseGame() throws InterruptedException {
		synchronized (movePuyoThread) {
			movePuyoThread.resume();
			pauseLabel.setText("");
			this.repaint();
		}
	}

	private void pauseGame() throws InterruptedException {
		synchronized (movePuyoThread) {
			movePuyoThread.suspend();
			pauseLabel.setText("PAUSED");
			this.repaint();
		}
	}

	/**
	 * Method to validate conversion
	 * 
	 * @param state_of_rotation
	 * @return
	 */
	private boolean goAhead(int state_of_rotation) {
		boolean yes = true;
		if (state_of_rotation == ZERO_DEGREE) {
			JLabel nextLabel = PuyoUtilities.getNextColumnElement(
					secondLiveLabel, mainPanel);
			if (nextLabel != null && nextLabel.getIcon() == null) {
				JLabel bottomLabel = PuyoUtilities.bottommostColumnElement(
						nextLabel, mainPanel);
				JLabel compare1 = PuyoUtilities.getPrevColumnElement(
						bottomLabel.getName(), mainPanel);
				if (compare1 != null
						&& (nextLabel.equals(bottomLabel) || nextLabel
								.equals(compare1))) {
					yes = false;
				}
			}
		}
		return yes;
	}

	/**
	 * This method occurs when the user hits the UP ARROW KEY.
	 * 
	 */
	private void upMethod() {
		/**
		 * This method occurs when the user hits the UP ARROW KEY. This just
		 * flips the icons.
		 */
		if (!hasFirstPuyoReachedBottom() && !hasSecondPuyoReachedBottom()) {
			ImageIcon dummy = firstLiveIcon;
			firstLiveIcon = secondLiveIcon;
			secondLiveIcon = dummy;
			dummy = null;
			firstLiveLabel.setIcon(firstLiveIcon);
			secondLiveLabel.setIcon(secondLiveIcon);
		}
		// if (!bottomReached) {
		// completeRotation(gridCoordinate);
		// }
	}

	/**
	 * This method occurs when the user hits the LEFT ARROW KEY.
	 * 
	 */
	private void leftMethod() {
		/**
		 * This method occurs when the user hits the LEFT ARROW KEY. This method
		 * then pushes the puyo to the left, provided there is no blocking puyo
		 * there. If there is blocking puyo to its left, then the puyo is not
		 * moved to the left. Please note that if one puyo has settled on top of
		 * another and second puyo is still moving, it cannot be moved to its
		 * left alone.
		 */
		// if (flag == 0) {
		JLabel dummy1 = PuyoUtilities.getPrevRowElement(mainPanel,
				firstLiveLabel.getName());
		JLabel dummy2 = PuyoUtilities.getPrevRowElement(mainPanel,
				secondLiveLabel.getName());
		// if (restrictHorizontalMovement(dummy1, dummy2, gridCoordinate,
		// "Left")) {
		if (!hasFirstPuyoReachedBottom() && !hasSecondPuyoReachedBottom()) {
			// refreshCoordinates(gridCoordinate, "Left");
			// bottom_of_first_puyo =
			// calculateBottomOfFirstPuyo(gridCoordinate);
			// bottom_of_second_puyo =
			// calculateBottomOfSecondPuyo(gridCoordinate);
			if (goAhead(LEFT_KEY, rotationState)
					&& updateBottom(LEFT_KEY, rotationState)) {
				firstLiveLabel.setIcon(null);
				secondLiveLabel.setIcon(null);
				firstLiveLabel = dummy1;
				secondLiveLabel = dummy2;
				firstLiveLabel.setIcon(firstLiveIcon);
				secondLiveLabel.setIcon(secondLiveIcon);
			}
		}
		// } else {
		// if (gridCoordinate > 0) {
		// JLabel dummy1 = PuyoUtilities.getPrevRowElement(mainPanel,
		// firstLiveLabel.getName());
		// JLabel dummy2 = PuyoUtilities.getPrevRowElement(mainPanel,
		// secondLiveLabel.getName());
		// JLabel dummy3 = PuyoUtilities.getNextColumnElement(dummy1,
		// mainPanel);
		// if (dummy1 != null && dummy2 != null && dummy3 != null
		// && dummy1.getIcon() == null && dummy2.getIcon() == null
		// && dummy3.getIcon() == null) {
		// refreshCoordinates(gridCoordinate, "Left");
		// ArrayList componentList = PuyoUtilities
		// .getElementsOfCoordinate(mainPanel, gridCoordinate);
		// ArrayList list = PuyoUtilities
		// .calculateBottomForVerticalPuyo(componentList);
		// bottom_of_first_puyo = (String) list.get(0);
		// bottom_of_second_puyo = (String) list.get(1);
		// if (!bottom_of_first_puyo.equalsIgnoreCase(firstLiveLabel
		// .getName())) {
		// ImageIcon i = (ImageIcon) firstLiveLabel.getIcon();
		// firstLiveLabel.setIcon(null);
		// firstLiveLabel = dummy1;
		// firstLiveLabel.setIcon(i);
		// i = (ImageIcon) secondLiveLabel.getIcon();
		// secondLiveLabel.setIcon(null);
		// secondLiveLabel = dummy2;
		// secondLiveLabel.setIcon(i);
		// }
		// }
		// }
		// }
	}

	private boolean goAhead(String key, int state_of_rotation) {
		boolean yes = true;
		try {
			if (key.equalsIgnoreCase(LEFT_KEY)) {
				if (rotationState == ZERO_DEGREE) {
					JLabel nextLabel = PuyoUtilities.getPrevRowElement(
							mainPanel, firstLiveLabel.getName());
					if (nextLabel != null && nextLabel.getIcon() == null) {
						JLabel dummyBottom = PuyoUtilities
								.bottommostColumnElement(nextLabel, mainPanel);
						JLabel compare1 = PuyoUtilities.getPrevColumnElement(
								dummyBottom.getName(), mainPanel);
						if (compare1 != null
								&& (nextLabel.equals(compare1) || nextLabel
										.equals(dummyBottom))) {
							yes = false;
						}
					}
				} else if (rotationState == NINTY_DEGREE) {
					JLabel nextLabel = PuyoUtilities.getPrevRowElement(
							mainPanel, firstLiveLabel.getName());
					if (nextLabel != null && nextLabel.getIcon() == null) {
						JLabel dummyBottom = PuyoUtilities
								.bottommostColumnElement(nextLabel, mainPanel);
						JLabel compare1 = PuyoUtilities.getPrevColumnElement(
								dummyBottom.getName(), mainPanel);
						if (compare1 != null
								&& (nextLabel.equals(compare1) || nextLabel
										.equals(dummyBottom))) {
							yes = false;
						}
					}
				} else if (rotationState == ONE_EIGHTY_DEGREE) {
					JLabel nextLabel = PuyoUtilities.getPrevRowElement(
							mainPanel, secondLiveLabel.getName());
					if (nextLabel != null && nextLabel.getIcon() == null) {
						JLabel dummyBottom = PuyoUtilities
								.bottommostColumnElement(nextLabel, mainPanel);
						JLabel compare1 = PuyoUtilities.getPrevColumnElement(
								dummyBottom.getName(), mainPanel);
						if (compare1 != null
								&& (nextLabel.equals(compare1) || nextLabel
										.equals(dummyBottom))) {
							yes = false;
						}
					}
				} else {
					JLabel nextLabel = PuyoUtilities.getPrevRowElement(
							mainPanel, secondLiveLabel.getName());
					if (nextLabel != null && nextLabel.getIcon() == null) {
						JLabel dummyBottom = PuyoUtilities
								.bottommostColumnElement(nextLabel, mainPanel);
						JLabel compare1 = PuyoUtilities.getPrevColumnElement(
								dummyBottom.getName(), mainPanel);
						if (compare1 != null
								&& (nextLabel.equals(compare1) || nextLabel
										.equals(dummyBottom))) {
							yes = false;
						}
					}
				}
			} else if (key.equalsIgnoreCase(RIGHT_KEY)) {
				if (rotationState == ZERO_DEGREE) {
					JLabel nextLabel = PuyoUtilities.getNextRowElement(
							mainPanel, secondLiveLabel.getName());
					if (nextLabel != null && nextLabel.getIcon() == null) {
						JLabel dummyBottom = PuyoUtilities
								.bottommostColumnElement(nextLabel, mainPanel);
						JLabel compare1 = PuyoUtilities.getPrevColumnElement(
								dummyBottom.getName(), mainPanel);
						if (compare1 != null
								&& (nextLabel.equals(compare1) || nextLabel
										.equals(dummyBottom))) {
							yes = false;
						}
					}
				} else if (rotationState == NINTY_DEGREE) {
					JLabel nextLabel = PuyoUtilities.getNextRowElement(
							mainPanel, firstLiveLabel.getName());
					if (nextLabel != null && nextLabel.getIcon() == null) {
						JLabel dummyBottom = PuyoUtilities
								.bottommostColumnElement(nextLabel, mainPanel);
						JLabel compare1 = PuyoUtilities.getPrevColumnElement(
								dummyBottom.getName(), mainPanel);
						if (compare1 != null
								&& (nextLabel.equals(compare1) || nextLabel
										.equals(dummyBottom))) {
							yes = false;
						}
					}
				} else if (rotationState == ONE_EIGHTY_DEGREE) {
					JLabel nextLabel = PuyoUtilities.getNextRowElement(
							mainPanel, firstLiveLabel.getName());
					if (nextLabel != null && nextLabel.getIcon() == null) {
						JLabel dummyBottom = PuyoUtilities
								.bottommostColumnElement(nextLabel, mainPanel);
						JLabel compare1 = PuyoUtilities.getPrevColumnElement(
								dummyBottom.getName(), mainPanel);
						if (compare1 != null
								&& (nextLabel.equals(compare1) || nextLabel
										.equals(dummyBottom))) {
							yes = false;
						}
					}
				} else {
					JLabel nextLabel = PuyoUtilities.getNextRowElement(
							mainPanel, secondLiveLabel.getName());
					if (nextLabel != null && nextLabel.getIcon() == null) {
						JLabel dummyBottom = PuyoUtilities
								.bottommostColumnElement(nextLabel, mainPanel);
						JLabel compare1 = PuyoUtilities.getPrevColumnElement(
								dummyBottom.getName(), mainPanel);
						if (compare1 != null
								&& (nextLabel.equals(compare1) || nextLabel
										.equals(dummyBottom))) {
							yes = false;
						}
					}
				}
			}
		} catch (Exception ex) {

		}
		return yes;
	}

	/**
	 * This method occurs when the user hits the RIGHT ARROW KEY.
	 * 
	 */
	private void rightMethod() {
		/**
		 * This method occurs when the user hits the RIGHT ARROW KEY. This
		 * method then pushes the puyo to the right, provided there is no
		 * blocking puyo there. If there is blocking puyo to its right, then the
		 * puyo is not moved to the right. Please note that if one puyo has
		 * settled on top of another and second puyo is still moving, it cannot
		 * be moved to its right alone.
		 */
		// if (flag == 0) {
		JLabel dummy1 = PuyoUtilities.getNextRowElement(mainPanel,
				firstLiveLabel.getName());
		JLabel dummy2 = PuyoUtilities.getNextRowElement(mainPanel,
				secondLiveLabel.getName());
		// if (restrictHorizontalMovement(dummy1, dummy2, gridCoordinate,
		// "Right")) {
		if (!hasFirstPuyoReachedBottom() && !hasSecondPuyoReachedBottom()) {
			// refreshCoordinates(gridCoordinate, "Right");
			if (goAhead(RIGHT_KEY, rotationState)
					&& updateBottom(RIGHT_KEY, rotationState)) {
				firstLiveLabel.setIcon(null);
				secondLiveLabel.setIcon(null);
				firstLiveLabel = dummy1;
				secondLiveLabel = dummy2;
				firstLiveLabel.setIcon(firstLiveIcon);
				secondLiveLabel.setIcon(secondLiveIcon);
			}
			// bottom_of_first_puyo =
			// calculateBottomOfFirstPuyo(gridCoordinate);
			// bottom_of_second_puyo =
			// calculateBottomOfSecondPuyo(gridCoordinate);
		}
		// }
		// } else {
		// if (gridCoordinate < (PuyoUtilities.COLUMNS - 1)) {
		// JLabel dummy1 = PuyoUtilities.getNextRowElement(mainPanel,
		// firstLiveLabel.getName());
		// JLabel dummy2 = PuyoUtilities.getNextRowElement(mainPanel,
		// secondLiveLabel.getName());
		// JLabel dummy3 = PuyoUtilities.getNextColumnElement(dummy1,
		// mainPanel);
		// if (dummy1 != null && dummy2 != null && dummy3 != null
		// && dummy1.getIcon() == null && dummy2.getIcon() == null
		// && dummy3.getIcon() == null) {
		// refreshCoordinates(gridCoordinate, "Right");
		// ArrayList componentList = PuyoUtilities
		// .getElementsOfCoordinate(mainPanel, gridCoordinate);
		// ArrayList list = PuyoUtilities
		// .calculateBottomForVerticalPuyo(componentList);
		// bottom_of_first_puyo = (String) list.get(0);
		// bottom_of_second_puyo = (String) list.get(1);
		// if (!bottom_of_first_puyo.equalsIgnoreCase(firstLiveLabel
		// .getName())) {
		// ImageIcon i = (ImageIcon) firstLiveLabel.getIcon();
		// firstLiveLabel.setIcon(null);
		// firstLiveLabel = dummy1;
		// firstLiveLabel.setIcon(i);
		// i = (ImageIcon) secondLiveLabel.getIcon();
		// secondLiveLabel.setIcon(null);
		// secondLiveLabel = dummy2;
		// secondLiveLabel.setIcon(i);
		// }
		// }
		// }
		// }
	}

	/**
	 * This method occurs when the user hits the DOWN ARROW KEY.
	 * 
	 */
	private void downMethod() {
		/**
		 * This method occurs when the user hits the DOWN ARROW KEY. This just
		 * flips the icons.
		 */
		// if (flag == 0) {
		// flipToVertical(gridCoordinate);
		// } else {
		// flipToHorizontal(gridCoordinate);
		// }
		if (!bottomReached) {
			completeRotation(gridCoordinate);
		}
	}

	/**
	 * This method rotates the puyos by 90 degrees.
	 * 
	 * @param coordinate
	 */
	private void flipToVertical(int coordinate) {
		/**
		 * In this method, Im getting the next component of the present JLabel
		 * and am assigning it to the component immediately below the present
		 * JLabel.
		 */
		if (!restrictHorizontalToVerticalFlipMovement(secondLiveLabel)) {
			JLabel dummy = PuyoUtilities.getNextColumnElement(secondLiveLabel,
					mainPanel);
			refreshCoordinates(coordinate, "Downvertical");
			ArrayList componentList = PuyoUtilities.getElementsOfCoordinate(
					mainPanel, gridCoordinate);
			ArrayList list = PuyoUtilities
					.calculateBottomForVerticalPuyo(componentList);
			bottom_of_first_puyo = (String) list.get(0);
			bottom_of_second_puyo = (String) list.get(1);
			if (!bottom_of_first_puyo
					.equalsIgnoreCase(firstLiveLabel.getName())) {
				ImageIcon i = (ImageIcon) firstLiveLabel.getIcon();
				firstLiveLabel.setIcon(null);
				firstLiveLabel = dummy;
				firstLiveLabel.setIcon(i);
			}
			flag = flag + 1;
		}

	}

	/**
	 * This method rotates the puyos by another 90 degrees.
	 * 
	 * @param coordinate
	 */
	private void flipToHorizontal(int firstCoordinate) {
		/**
		 * In this method, Im getting the position of the two puyos, and am
		 * getting the next row-wise component of the higher label. Then am
		 * re-assigning the icon to that component.
		 */
		try {
			if (!restrictVerticalToHorizontalFlipMovement(firstLiveLabel)) {
				if (gridCoordinate != 0) {
					JLabel dummy = PuyoUtilities.getPrevRowElement(mainPanel,
							secondLiveLabel.getName());
					refreshCoordinates(firstCoordinate, "Downhorizontal");
					bottom_of_first_puyo = calculateBottomOfFirstPuyo(gridCoordinate);
					bottom_of_second_puyo = calculateBottomOfSecondPuyo(gridCoordinate);
					if (!dummy.getName().equalsIgnoreCase(bottom_of_first_puyo)
							&& dummy.getIcon() == null) {
						ImageIcon i = (ImageIcon) firstLiveLabel.getIcon();
						firstLiveLabel.setIcon(null);
						firstLiveLabel = dummy;
						firstLiveLabel.setIcon(i);
						flag = 0;
					}
				} else if (gridCoordinate != (PuyoUtilities.COLUMNS - 1)) {
					JLabel dummy = PuyoUtilities.getNextRowElement(mainPanel,
							secondLiveLabel.getName());
					refreshCoordinates(firstCoordinate, "Downhorizontal");
					bottom_of_first_puyo = calculateBottomOfFirstPuyo(gridCoordinate);
					bottom_of_second_puyo = calculateBottomOfSecondPuyo(gridCoordinate);
					if (!dummy.getName().equalsIgnoreCase(bottom_of_first_puyo)
							&& dummy.getIcon() == null) {
						ImageIcon i = (ImageIcon) firstLiveLabel.getIcon();
						firstLiveLabel.setIcon(null);
						firstLiveLabel = dummy;
						firstLiveLabel.setIcon(i);
						flag = 0;
					}
				}
			}
		} catch (Exception ex) {

		}
	}

	/**
	 * Method to fall down hanging puyos, after row has been cleared
	 * 
	 * @param clearedElements
	 */
	private void fallDownHangingPuyos(JLabel[] clearedElements) {
		/**
		 * If a row is cleared and there are puyos hanging above that, then they
		 * are brought down to fill the vacant spaces through this method. Im
		 * iterating throughout the panel and am collecting those labels which
		 * needs to be brought down. Then, Im assigning the icons to them
		 */
		ArrayList labelList = getStaticPuyos(clearedElements[clearedElements.length - 1]);
		for (int i = 0; i < labelList.size(); i++) {
			JLabel label = (JLabel) labelList.get(i);
			ImageIcon icon = (ImageIcon) label.getIcon();
			JLabel label2 = PuyoUtilities
					.getNextColumnElement(label, mainPanel);
			label.setIcon(null);
			label2.setIcon(icon);
		}
	}

	/**
	 * Method that returns those puyos which are hanging in air
	 * 
	 * @param lastClearedComponent
	 * @return
	 */
	private ArrayList getStaticPuyos(JLabel lastClearedComponent) {
		/**
		 * This method gives a list of components above the cleared row, which
		 * needs to be brought down.
		 */
		ArrayList staticLabels = new ArrayList();
		int count = PuyoUtilities.componentsToLookFor(lastClearedComponent
				.getName(), mainPanel);
		for (int i = (count - 1); i > 0; i--) {
			JLabel label = (JLabel) mainPanel.getComponent(i);
			if (label.getIcon() != null) {
				staticLabels.add(label);
			}
		}
		return staticLabels;
	}

	/**
	 * Method to restrict Horizontal-Vertical rotation.
	 * 
	 * @param secondLabel
	 * @return
	 */
	private boolean restrictHorizontalToVerticalFlipMovement(JLabel secondLabel) {
		/**
		 * This method doesnt respond to user's call to convert from horizontal
		 * to vertical movement, if it traces out that there is already a puyo
		 * in that place. Method written as a bug-fix.
		 */
		boolean b = false;
		try {
			JLabel dummy = PuyoUtilities.getNextColumnElement(secondLabel,
					mainPanel);
			JLabel dummyTwo = PuyoUtilities.getNextColumnElement(
					firstLiveLabel, mainPanel);
			if (dummy.getIcon() != null || dummyTwo.getIcon() != null
					|| dummy.getName().equalsIgnoreCase(bottom_of_second_puyo)) {
				b = true;
			} else {
				b = false;
			}
		} catch (Exception ex) {

		}
		return b;
	}

	/**
	 * Method to restrict Vertical-Horizontal rotation.
	 * 
	 * @param firstLabel
	 * @return
	 */
	private boolean restrictVerticalToHorizontalFlipMovement(JLabel firstLabel) {
		/**
		 * This method doesnt respond to user's call to convert from vertical to
		 * horizontal movement, if it traces out that there is already a puyo in
		 * that place. Method written as a bug-fix.
		 */
		boolean b = false;
		try {
			int firstCoordinate = gridCoordinate;
			if (gridCoordinate != 0) {
				firstCoordinate = firstCoordinate - 1;
				JLabel dummy1 = PuyoUtilities.getTopMostColumnElement(
						mainPanel, firstCoordinate, "");
				JLabel dummyIconTest = PuyoUtilities.getPrevRowElement(
						mainPanel, firstLiveLabel.getName());
				if (PuyoUtilities.getPrevRowElement(mainPanel,
						firstLiveLabel.getName()).equals(dummy1)
						|| dummyIconTest.getIcon() != null) {
					b = true;
				} else {
					b = false;
				}
			} else {
				firstCoordinate = firstCoordinate + 1;
				JLabel dummy1 = PuyoUtilities.getTopMostColumnElement(
						mainPanel, firstCoordinate, "");
				JLabel dummy2 = PuyoUtilities.getPrevColumnElement(dummy1
						.getName(), mainPanel);
				JLabel dummyIconTest = PuyoUtilities.getNextRowElement(
						mainPanel, firstLiveLabel.getName());
				if (PuyoUtilities.getNextRowElement(mainPanel,
						firstLiveLabel.getName()).equals(dummy1)
						|| dummyIconTest.getIcon() != null
						|| PuyoUtilities.getNextRowElement(mainPanel,
								firstLiveLabel.getName()).equals(dummy2)) {
					b = true;
				} else {
					b = false;
				}
			}
		} catch (Exception ex) {

		}
		return b;
	}

	/**
	 * Method that tracks where the puyo is and then restricts movement as
	 * applicable
	 * 
	 * @param one
	 * @param two
	 * @param coordinate
	 * @param key
	 * @return
	 */
	private boolean restrictHorizontalMovement(JLabel one, JLabel two,
			int coordinate, String key) {
		/**
		 * This method tracks where the puyo is. This method restricts the user
		 * from moving the puyo to left or right, if it knows that the
		 * movePuyo() method has been called and the bottom has been calculated
		 * and the thread has gone to sleep, knowing that puyo has not reached
		 * bottom. This method was added as a bug-fix.
		 */
		boolean b = false;
		try {
			JLabel test = null;
			if (key.equalsIgnoreCase("Left")) {
				if (coordinate != 0) {
					coordinate = coordinate - 1;
					test = PuyoUtilities.getTopMostColumnElement(mainPanel,
							coordinate, "Left");
					if (PuyoUtilities.getPrevRowElement(mainPanel,
							firstLiveLabel.getName()).equals(
							PuyoUtilities.getPrevColumnElement(test.getName(),
									mainPanel))
							|| (PuyoUtilities.getPrevRowElement(mainPanel,
									firstLiveLabel.getName()).getIcon() != null)) {
						return false;
					} else {
						return true;
					}
				}
			} else if (key.equalsIgnoreCase("Right")) {
				if (coordinate != (PuyoUtilities.COLUMNS - 1)) {
					coordinate = Integer.parseInt(secondLiveLabel.getName()
							.substring(1, 2));
					coordinate = coordinate + 1;
					test = PuyoUtilities.getTopMostColumnElement(mainPanel,
							coordinate, "Right");
					if (PuyoUtilities.getNextRowElement(mainPanel,
							secondLiveLabel.getName()).equals(
							PuyoUtilities.getPrevColumnElement(test.getName(),
									mainPanel))
							|| (PuyoUtilities.getNextRowElement(mainPanel,
									secondLiveLabel.getName()).getIcon() != null)) {
						return false;
					} else {
						return true;
					}
				}
			}
			String dummyOne = calculateBottomOfFirstPuyo(coordinate);
			String dummyTwo = calculateBottomOfSecondPuyo(coordinate);
			JLabel dummyOfOne = PuyoUtilities.getPrevColumnElement(dummyOne,
					mainPanel);
			JLabel dummyOfTwo = PuyoUtilities.getPrevColumnElement(dummyTwo,
					mainPanel);
			if (!one.equals(dummyOfOne) && !two.equals(dummyOfTwo)) {
				b = true;
			} else {
				b = false;
			}
		} catch (Exception ex) {

		}
		return b;
	}

	/**
	 * Method, which listens for square shaped puyos.
	 * 
	 */
	private void removeSquarePuyos() {
		/**
		 * This method listens for square shaped puyos. If 2 successive puyos of
		 * the same columns have same icon and their row counterparts also has
		 * same icons, they will be cleared. Method added as a requirement
		 * update.
		 */
		try {
			ArrayList commonLabels = PuyoUtilities.clearSquareShape(mainPanel);
			if (commonLabels != null && commonLabels.size() != 0) {
				for (int i = 0; i < (commonLabels.size() - 1); i += 2) {
					JLabel label1 = (JLabel) commonLabels.get(i);
					JLabel label2 = (JLabel) commonLabels.get(i + 1);
					process(label1, label2);
				}
			}
		} catch (Exception ex) {

		}
	}

	/**
	 * This method checks various shapes occurs in a row. Method added as a
	 * requirement update.
	 * 
	 */
	private void detectRowShapes() {
		/**
		 * This method checks for various shapes like T, inverted T etc.
		 */
		try {
			ArrayList commonLabels = PuyoUtilities.clearSquareShape(mainPanel);
			if (commonLabels != null && commonLabels.size() != 0) {
				for (int i = 0; i < (commonLabels.size() - 1); i += 2) {
					JLabel label1 = (JLabel) commonLabels.get(i);
					JLabel label2 = (JLabel) commonLabels.get(i + 1);
					process(label1, label2, false);
					checkForOAndPShapes(label1, label2);
				}
			}
		} catch (Exception ex) {

		}
	}

	/**
	 * This method checks for shapes G to L. Method added as a requirement
	 * update.
	 * 
	 * @param label1
	 * @param label2
	 * @param label3
	 * @param dummy
	 */
	private void process(JLabel label1, JLabel label2, boolean dummy) {
		/**
		 * This method checks for various shapes from Shape G to Shape L. If 3
		 * puyos of a same column has same icons, this method is called, which
		 * further investigates by checking the puyo to its immediate right/left
		 * depending om the position. If the puyo on the right/left matches it,
		 * it checks for shape and clears it.
		 */
		int coordinate = Integer.parseInt(label1.getName().substring(1, 2));
		ArrayList labelList = null;
		JLabel checker1 = null;
		JLabel checker2 = null;
		if (coordinate == 0) {
			labelList = PuyoUtilities.checkGShape(label1, mainPanel);
			if (labelList.size() != 0) {
				checker1 = (JLabel) labelList.get(0);
				checker2 = (JLabel) labelList.get(1);
				if (checker1 != null && checker2 != null
						&& checker1.getIcon() != null
						&& checker2.getIcon() != null
						&& label1.getIcon().equals(checker1.getIcon())
						&& label1.getIcon().equals(checker2.getIcon())) {
					ArrayList dummyList = new ArrayList();
					dummyList.add(label1);
					dummyList.add(label2);
					dummyList.add(checker1);
					dummyList.add(checker2);
					dummyList = PuyoUtilities.checkForMorePuyos(dummyList,
							mainPanel);
					for (int i = 0; i < dummyList.size(); i++) {
						JLabel label = (JLabel) dummyList.get(i);
						if (label != null && label.getIcon() != null) {
							label.setIcon(null);
							score = score + bonusPoint;
						}
					}
					JLabel dummy1 = label1;
					JLabel dummy2 = checker1;
					JLabel dummy3 = checker2;
					label1.setIcon(null);
					label2.setIcon(null);
					checker1.setIcon(null);
					checker2.setIcon(null);
					score = score + (point * 4);
					scoreLabel.setText(scoreText + score);
					chainPuyoChecker = true;
					fillVoid(dummy1, dummy2, dummy3);
				}
			}
			labelList = PuyoUtilities.checkJShape(label2, mainPanel);
			if (labelList.size() != 0) {
				checker1 = (JLabel) labelList.get(0);
				checker2 = (JLabel) labelList.get(1);
				if (checker1 != null && checker2 != null
						&& checker1.getIcon() != null
						&& checker2.getIcon() != null
						&& label1.getIcon().equals(checker1.getIcon())
						&& label1.getIcon().equals(checker2.getIcon())) {
					ArrayList dummyList = new ArrayList();
					dummyList.add(label1);
					dummyList.add(label2);
					dummyList.add(checker1);
					dummyList.add(checker2);
					dummyList = PuyoUtilities.checkForMorePuyos(dummyList,
							mainPanel);
					for (int i = 0; i < dummyList.size(); i++) {
						JLabel label = (JLabel) dummyList.get(i);
						if (label != null && label.getIcon() != null) {
							label.setIcon(null);
							score = score + bonusPoint;
						}
					}
					JLabel dummy1 = label1;
					JLabel dummy2 = checker1;
					JLabel dummy3 = checker2;
					label1.setIcon(null);
					label2.setIcon(null);
					checker1.setIcon(null);
					checker2.setIcon(null);
					score = score + (point * 4);
					scoreLabel.setText(scoreText + score);
					chainPuyoChecker = true;
					fillVoid(dummy1, dummy2, dummy3);
				}
			}
		} else if (coordinate == 5) {
			labelList = PuyoUtilities.checkIShape(label1, mainPanel);
			if (labelList.size() != 0) {
				checker1 = (JLabel) labelList.get(0);
				checker2 = (JLabel) labelList.get(1);
				if (checker1 != null && checker2 != null
						&& checker1.getIcon() != null
						&& checker2.getIcon() != null
						&& label1.getIcon().equals(checker1.getIcon())
						&& label1.getIcon().equals(checker2.getIcon())) {
					ArrayList dummyList = new ArrayList();
					dummyList.add(label1);
					dummyList.add(label2);
					dummyList.add(checker1);
					dummyList.add(checker2);
					dummyList = PuyoUtilities.checkForMorePuyos(dummyList,
							mainPanel);
					for (int i = 0; i < dummyList.size(); i++) {
						JLabel label = (JLabel) dummyList.get(i);
						if (label != null && label.getIcon() != null) {
							label.setIcon(null);
							score = score + bonusPoint;
						}
					}
					JLabel dummy1 = label1;
					JLabel dummy2 = checker1;
					JLabel dummy3 = checker2;
					label1.setIcon(null);
					label2.setIcon(null);
					checker1.setIcon(null);
					checker2.setIcon(null);
					score = score + (point * 4);
					scoreLabel.setText(scoreText + score);
					chainPuyoChecker = true;
					fillVoid(dummy1, dummy2, dummy3);
				}
			}
			labelList = PuyoUtilities.checkLShape(label2, mainPanel);
			if (labelList.size() != 0) {
				checker1 = (JLabel) labelList.get(0);
				checker2 = (JLabel) labelList.get(1);
				if (checker1 != null && checker2 != null
						&& checker1.getIcon() != null
						&& checker2.getIcon() != null
						&& label1.getIcon().equals(checker1.getIcon())
						&& label1.getIcon().equals(checker2.getIcon())) {
					ArrayList dummyList = new ArrayList();
					dummyList.add(label1);
					dummyList.add(label2);
					dummyList.add(checker1);
					dummyList.add(checker2);
					dummyList = PuyoUtilities.checkForMorePuyos(dummyList,
							mainPanel);
					for (int i = 0; i < dummyList.size(); i++) {
						JLabel label = (JLabel) dummyList.get(i);
						if (label != null && label.getIcon() != null) {
							label.setIcon(null);
							score = score + bonusPoint;
						}
					}
					JLabel dummy1 = label1;
					JLabel dummy2 = checker1;
					JLabel dummy3 = checker2;
					label1.setIcon(null);
					label2.setIcon(null);
					checker1.setIcon(null);
					checker2.setIcon(null);
					score = score + (point * 4);
					scoreLabel.setText(scoreText + score);
					chainPuyoChecker = true;
					fillVoid(dummy1, dummy2, dummy3);
				}
			}
		} else {
			labelList = PuyoUtilities.checkGShape(label1, mainPanel);
			if (labelList.size() != 0) {
				checker1 = (JLabel) labelList.get(0);
				checker2 = (JLabel) labelList.get(1);
				if (checker1 != null && checker2 != null
						&& checker1.getIcon() != null
						&& checker2.getIcon() != null
						&& label1.getIcon().equals(checker1.getIcon())
						&& label1.getIcon().equals(checker2.getIcon())) {
					ArrayList dummyList = new ArrayList();
					dummyList.add(label1);
					dummyList.add(label2);
					dummyList.add(checker1);
					dummyList.add(checker2);
					dummyList = PuyoUtilities.checkForMorePuyos(dummyList,
							mainPanel);
					for (int i = 0; i < dummyList.size(); i++) {
						JLabel label = (JLabel) dummyList.get(i);
						if (label != null && label.getIcon() != null) {
							label.setIcon(null);
							score = score + bonusPoint;
						}
					}
					JLabel dummy1 = label1;
					JLabel dummy2 = checker1;
					JLabel dummy3 = checker2;
					label1.setIcon(null);
					label2.setIcon(null);
					checker1.setIcon(null);
					checker2.setIcon(null);
					score = score + (point * 4);
					scoreLabel.setText(scoreText + score);
					chainPuyoChecker = true;
					fillVoid(dummy1, dummy2, dummy3);
				}
			}
			labelList = PuyoUtilities.checkHShape(label1, mainPanel);
			if (labelList.size() != 0) {
				checker1 = (JLabel) labelList.get(0);
				checker2 = (JLabel) labelList.get(1);
				if (checker1 != null && checker2 != null
						&& checker1.getIcon() != null
						&& checker2.getIcon() != null
						&& label1.getIcon().equals(checker1.getIcon())
						&& label1.getIcon().equals(checker2.getIcon())) {
					ArrayList dummyList = new ArrayList();
					dummyList.add(label1);
					dummyList.add(label2);
					dummyList.add(checker1);
					dummyList.add(checker2);
					dummyList = PuyoUtilities.checkForMorePuyos(dummyList,
							mainPanel);
					for (int i = 0; i < dummyList.size(); i++) {
						JLabel label = (JLabel) dummyList.get(i);
						if (label != null && label.getIcon() != null) {
							label.setIcon(null);
							score = score + bonusPoint;
						}
					}
					JLabel dummy1 = label1;
					JLabel dummy2 = checker1;
					JLabel dummy3 = checker2;
					label1.setIcon(null);
					label2.setIcon(null);
					checker1.setIcon(null);
					checker2.setIcon(null);
					score = score + (point * 4);
					scoreLabel.setText(scoreText + score);
					chainPuyoChecker = true;
					fillVoid(dummy1, dummy2, dummy3);
				}
			}
			labelList = PuyoUtilities.checkIShape(label1, mainPanel);
			if (labelList.size() != 0) {
				checker1 = (JLabel) labelList.get(0);
				checker2 = (JLabel) labelList.get(1);
				if (checker1 != null && checker2 != null
						&& checker1.getIcon() != null
						&& checker2.getIcon() != null
						&& label1.getIcon().equals(checker1.getIcon())
						&& label1.getIcon().equals(checker2.getIcon())) {
					ArrayList dummyList = new ArrayList();
					dummyList.add(label1);
					dummyList.add(label2);
					dummyList.add(checker1);
					dummyList.add(checker2);
					dummyList = PuyoUtilities.checkForMorePuyos(dummyList,
							mainPanel);
					for (int i = 0; i < dummyList.size(); i++) {
						JLabel label = (JLabel) dummyList.get(i);
						if (label != null && label.getIcon() != null) {
							label.setIcon(null);
							score = score + bonusPoint;
						}
					}
					JLabel dummy1 = label1;
					JLabel dummy2 = checker1;
					JLabel dummy3 = checker2;
					label1.setIcon(null);
					label2.setIcon(null);
					checker1.setIcon(null);
					checker2.setIcon(null);
					score = score + (point * 4);
					scoreLabel.setText(scoreText + score);
					chainPuyoChecker = true;
					fillVoid(dummy1, dummy2, dummy3);
				}
			}
			labelList = PuyoUtilities.checkJShape(label2, mainPanel);
			if (labelList.size() != 0) {
				checker1 = (JLabel) labelList.get(0);
				checker2 = (JLabel) labelList.get(1);
				if (checker1 != null && checker2 != null
						&& checker1.getIcon() != null
						&& checker2.getIcon() != null
						&& label1.getIcon().equals(checker1.getIcon())
						&& label1.getIcon().equals(checker2.getIcon())) {
					ArrayList dummyList = new ArrayList();
					dummyList.add(label1);
					dummyList.add(label2);
					dummyList.add(checker1);
					dummyList.add(checker2);
					dummyList = PuyoUtilities.checkForMorePuyos(dummyList,
							mainPanel);
					for (int i = 0; i < dummyList.size(); i++) {
						JLabel label = (JLabel) dummyList.get(i);
						if (label != null && label.getIcon() != null) {
							label.setIcon(null);
							score = score + bonusPoint;
						}
					}
					JLabel dummy1 = label1;
					JLabel dummy2 = checker1;
					JLabel dummy3 = checker2;
					label1.setIcon(null);
					label2.setIcon(null);
					checker1.setIcon(null);
					checker2.setIcon(null);
					score = score + (point * 4);
					scoreLabel.setText(scoreText + score);
					chainPuyoChecker = true;
					fillVoid(dummy1, dummy2, dummy3);
				}
			}
			labelList = PuyoUtilities.checkKShape(label2, mainPanel);
			if (labelList.size() != 0) {
				checker1 = (JLabel) labelList.get(0);
				checker2 = (JLabel) labelList.get(1);
				if (checker1 != null && checker2 != null
						&& checker1.getIcon() != null
						&& checker2.getIcon() != null
						&& label2.getIcon().equals(checker1.getIcon())
						&& label2.getIcon().equals(checker2.getIcon())) {
					ArrayList dummyList = new ArrayList();
					dummyList.add(label1);
					dummyList.add(label2);
					dummyList.add(checker1);
					dummyList.add(checker2);
					dummyList = PuyoUtilities.checkForMorePuyos(dummyList,
							mainPanel);
					for (int i = 0; i < dummyList.size(); i++) {
						JLabel label = (JLabel) dummyList.get(i);
						if (label != null && label.getIcon() != null) {
							label.setIcon(null);
							score = score + bonusPoint;
						}
					}
					JLabel dummy1 = label1;
					JLabel dummy2 = checker1;
					JLabel dummy3 = checker2;
					label1.setIcon(null);
					label2.setIcon(null);
					checker1.setIcon(null);
					checker2.setIcon(null);
					score = score + (point * 4);
					scoreLabel.setText(scoreText + score);
					chainPuyoChecker = true;
					fillVoid(dummy1, dummy2, dummy3);
				}
			}
			labelList = PuyoUtilities.checkLShape(label2, mainPanel);
			if (labelList.size() != 0) {
				checker1 = (JLabel) labelList.get(0);
				checker2 = (JLabel) labelList.get(1);
				if (checker1 != null && checker2 != null
						&& checker1.getIcon() != null
						&& checker2.getIcon() != null
						&& label1.getIcon().equals(checker1.getIcon())
						&& label1.getIcon().equals(checker2.getIcon())) {
					ArrayList dummyList = new ArrayList();
					dummyList.add(label1);
					dummyList.add(label2);
					dummyList.add(checker1);
					dummyList.add(checker2);
					dummyList = PuyoUtilities.checkForMorePuyos(dummyList,
							mainPanel);
					for (int i = 0; i < dummyList.size(); i++) {
						JLabel label = (JLabel) dummyList.get(i);
						if (label != null && label.getIcon() != null) {
							label.setIcon(null);
							score = score + bonusPoint;
						}
					}
					JLabel dummy1 = label1;
					JLabel dummy2 = checker1;
					JLabel dummy3 = checker2;
					label1.setIcon(null);
					label2.setIcon(null);
					checker1.setIcon(null);
					checker2.setIcon(null);
					score = score + (point * 4);
					scoreLabel.setText(scoreText + score);
					chainPuyoChecker = true;
					fillVoid(dummy1, dummy2, dummy3);
				}
			}
			labelList = PuyoUtilities.checkMShape(label1, label2, mainPanel);
			if (labelList.size() != 0) {
				checker1 = (JLabel) labelList.get(0);
				checker2 = (JLabel) labelList.get(1);
				if (checker1 != null && checker2 != null
						&& checker1.getIcon() != null
						&& checker2.getIcon() != null
						&& label1.getIcon().equals(checker1.getIcon())
						&& label2.getIcon().equals(checker2.getIcon())) {
					ArrayList dummyList = new ArrayList();
					dummyList.add(label1);
					dummyList.add(label2);
					dummyList.add(checker1);
					dummyList.add(checker2);
					dummyList = PuyoUtilities.checkForMorePuyos(dummyList,
							mainPanel);
					for (int i = 0; i < dummyList.size(); i++) {
						JLabel label = (JLabel) dummyList.get(i);
						if (label != null && label.getIcon() != null) {
							label.setIcon(null);
							score = score + bonusPoint;
						}
					}
					JLabel dummy1 = label1;
					JLabel dummy2 = checker1;
					JLabel dummy3 = checker2;
					label1.setIcon(null);
					label2.setIcon(null);
					checker1.setIcon(null);
					checker2.setIcon(null);
					score = score + (point * 4);
					scoreLabel.setText(scoreText + score);
					chainPuyoChecker = true;
					fillVoid(dummy1, dummy2, dummy3);
				}
			}
			labelList = PuyoUtilities.checkNShape(label1, label2, mainPanel);
			if (labelList.size() != 0) {
				checker1 = (JLabel) labelList.get(0);
				checker2 = (JLabel) labelList.get(1);
				if (checker1 != null && checker2 != null
						&& checker1.getIcon() != null
						&& checker2.getIcon() != null
						&& label1.getIcon().equals(checker1.getIcon())
						&& label2.getIcon().equals(checker2.getIcon())) {
					ArrayList dummyList = new ArrayList();
					dummyList.add(label1);
					dummyList.add(label2);
					dummyList.add(checker1);
					dummyList.add(checker2);
					dummyList = PuyoUtilities.checkForMorePuyos(dummyList,
							mainPanel);
					for (int i = 0; i < dummyList.size(); i++) {
						JLabel label = (JLabel) dummyList.get(i);
						if (label != null && label.getIcon() != null) {
							label.setIcon(null);
							score = score + bonusPoint;
						}
					}
					JLabel dummy1 = label1;
					JLabel dummy2 = checker1;
					JLabel dummy3 = checker2;
					label1.setIcon(null);
					label2.setIcon(null);
					checker1.setIcon(null);
					checker2.setIcon(null);
					score = score + (point * 4);
					scoreLabel.setText(scoreText + score);
					chainPuyoChecker = true;
					fillVoid(dummy1, dummy2, dummy3);
				}
			}
		}
	}

	/**
	 * Method fills the void left after square puyos are cleared. Method added
	 * as a requirement update.
	 * 
	 * @param columnA
	 * @param columnB
	 * @param columnC
	 */
	private void fillVoid(JLabel columnA, JLabel columnB, JLabel columnC) {
		/**
		 * This method fills the gap created by clearence of shape puyos. It
		 * takes two labels as parameters. It gets all the labels above the
		 * given two columns, and then calculates the gaps and puts the puyos in
		 * their respective places.
		 */
		try {
			ArrayList columnAList = PuyoUtilities
					.liveElementsAboveTheLabelInTheColumn(columnA, mainPanel);
			while (columnAList.size() != 0) {
				JLabel label = PuyoUtilities.bottommostColumnElement(columnA,
						mainPanel);
				JLabel intLabel = (JLabel) columnAList.get(0);
				ImageIcon i = (ImageIcon) intLabel.getIcon();
				label.setIcon(i);
				intLabel.setIcon(null);
				columnAList.remove(0);
			}
			ArrayList columnBList = PuyoUtilities
					.liveElementsAboveTheLabelInTheColumn(columnB, mainPanel);
			while (columnBList.size() != 0) {
				JLabel label = PuyoUtilities.bottommostColumnElement(columnB,
						mainPanel);
				JLabel intLabel = (JLabel) columnBList.get(0);
				ImageIcon i = (ImageIcon) intLabel.getIcon();
				label.setIcon(i);
				intLabel.setIcon(null);
				columnBList.remove(0);
			}
			ArrayList columnCList = PuyoUtilities
					.liveElementsAboveTheLabelInTheColumn(columnC, mainPanel);
			while (columnCList.size() != 0) {
				JLabel label = PuyoUtilities.bottommostColumnElement(columnC,
						mainPanel);
				JLabel intLabel = (JLabel) columnCList.get(0);
				ImageIcon i = (ImageIcon) intLabel.getIcon();
				label.setIcon(i);
				intLabel.setIcon(null);
				columnCList.remove(0);
			}
			fillVoid();
		} catch (Exception ex) {

		}
	}

	/**
	 * This method checks for shapes O to P. Method added as a requirement
	 * update.
	 * 
	 * @param label1
	 * @param label2
	 * 
	 */
	private void checkForOAndPShapes(JLabel label1, JLabel label2) {
		/**
		 * This method checks for various shapes from Shape O and Shape P. If 3
		 * puyos of a same column has same icons, this method is called, which
		 * further investigates by checking the puyo to its immediate right/left
		 * depending om the position. If the puyo on the right/left matches it,
		 * it checks for shape and clears it.
		 */
		try {
			ArrayList labelList = PuyoUtilities.checkOShape(label1, mainPanel);
			JLabel checker1 = null;
			JLabel checker2 = null;
			if (labelList.size() != 0) {
				checker1 = (JLabel) labelList.get(0);
				checker2 = (JLabel) labelList.get(1);
				if (checker1 != null && checker2 != null
						&& checker1.getIcon() != null
						&& checker2.getIcon() != null
						&& label1.getIcon().equals(checker1.getIcon())
						&& label1.getIcon().equals(checker2.getIcon())) {
					ArrayList dummyList = new ArrayList();
					dummyList.add(label1);
					dummyList.add(label2);
					dummyList.add(checker1);
					dummyList.add(checker2);
					dummyList = PuyoUtilities.checkForMorePuyos(dummyList,
							mainPanel);
					for (int i = 0; i < dummyList.size(); i++) {
						JLabel label = (JLabel) dummyList.get(i);
						if (label != null && label.getIcon() != null) {
							label.setIcon(null);
							score = score + bonusPoint;
						}
					}
					JLabel dummy1 = label1;
					JLabel dummy2 = checker2;
					label1.setIcon(null);
					label2.setIcon(null);
					checker1.setIcon(null);
					checker2.setIcon(null);
					score = score + (point * 4);
					scoreLabel.setText(scoreText + score);
					chainPuyoChecker = true;
					fillVoid(dummy1, dummy2);
				}
			}
			labelList = PuyoUtilities.checkPShape(label2, mainPanel);
			checker1 = null;
			checker2 = null;
			if (labelList.size() != 0) {
				checker1 = (JLabel) labelList.get(0);
				checker2 = (JLabel) labelList.get(1);
				if (checker1 != null && checker2 != null
						&& checker1.getIcon() != null
						&& checker2.getIcon() != null
						&& label1.getIcon().equals(checker1.getIcon())
						&& label1.getIcon().equals(checker2.getIcon())) {
					ArrayList dummyList = new ArrayList();
					dummyList.add(label1);
					dummyList.add(label2);
					dummyList.add(checker1);
					dummyList.add(checker2);
					dummyList = PuyoUtilities.checkForMorePuyos(dummyList,
							mainPanel);
					for (int i = 0; i < dummyList.size(); i++) {
						JLabel label = (JLabel) dummyList.get(i);
						if (label != null && label.getIcon() != null) {
							label.setIcon(null);
							score = score + bonusPoint;
						}
					}
					JLabel dummy1 = label1;
					JLabel dummy2 = checker1;
					label1.setIcon(null);
					label2.setIcon(null);
					checker1.setIcon(null);
					checker2.setIcon(null);
					score = score + (point * 4);
					scoreLabel.setText(scoreText + score);
					chainPuyoChecker = true;
					fillVoid(dummy1, dummy2);
				}
			}
		} catch (Exception ex) {

		}
	}

	/**
	 * This method checks for the occurance of a chain puyo. Method added as a
	 * requirement update.
	 * 
	 */
	private void miscCheck() {
		/**
		 * This method checks the occurance of a combi puyo by checking the
		 * chainPuyoChecker flag. Whenever a clearance occurs, the flag is
		 * updated to true, forcing the compiler to check for combi-puyos.
		 */
		chainPuyoChecker = false;
		clearAllSimilarPuyos();
	}

	/**
	 * This method checks various shapes occurs in a column. Method added as a
	 * requirement update.
	 * 
	 */
	private void detectColumnShapes() {
		/**
		 * This method checks for various shapes like L, inverted L etc.
		 */
		try {
			ArrayList similarLabelList = PuyoUtilities
					.detectColumnShapes(mainPanel);
			if (similarLabelList != null && similarLabelList.size() != 0) {
				for (int i = 0; i < (similarLabelList.size() - 2); i += 3) {
					JLabel label1 = (JLabel) similarLabelList.get(i);
					JLabel label2 = (JLabel) similarLabelList.get(i + 1);
					JLabel label3 = (JLabel) similarLabelList.get(i + 2);
					process(label1, label2, label3);
				}
			}
		} catch (Exception ex) {

		}
	}

	/**
	 * This method checks for shapes A to F. Method added as a requirement
	 * update.
	 * 
	 * @param label1
	 * @param label2
	 * @param label3
	 */
	private void process(JLabel label1, JLabel label2, JLabel label3) {
		/**
		 * This method checks for various shapes from Shape A to Shape F. If 3
		 * puyos of a same column has same icons, this method is called, which
		 * further investigates by checking the puyo to its immediate right/left
		 * depending om the position. If the puyo on the right/left matches it,
		 * it checks for shape and clears it.
		 */
		int coordinate = Integer.parseInt(label1.getName().substring(1, 2));
		JLabel label4 = null;
		if (coordinate == 0) {
			label4 = PuyoUtilities.checkAShape(label1, mainPanel);
			if (label4 != null && label4.getIcon() != null) {
				if (label1.getIcon().equals(label4.getIcon())) {
					ArrayList dummyList = new ArrayList();
					dummyList.add(label1);
					dummyList.add(label2);
					dummyList.add(label3);
					dummyList.add(label4);
					dummyList = PuyoUtilities.checkForMorePuyos(dummyList,
							mainPanel);
					for (int i = 0; i < dummyList.size(); i++) {
						JLabel label = (JLabel) dummyList.get(i);
						if (label != null && label.getIcon() != null) {
							label.setIcon(null);
							score = score + bonusPoint;
						}
					}
					JLabel dummy1 = label1;
					JLabel dummy2 = label4;
					label1.setIcon(null);
					label2.setIcon(null);
					label3.setIcon(null);
					label4.setIcon(null);
					score = score + (point * 4);
					scoreLabel.setText(scoreText + score);
					chainPuyoChecker = true;
					fillVoid(dummy1, dummy2);
				}
			}
			label4 = PuyoUtilities.checkBShape(label2, mainPanel);
			if (label4 != null && label4.getIcon() != null) {
				if (label2.getIcon().equals(label4.getIcon())) {
					ArrayList dummyList = new ArrayList();
					dummyList.add(label1);
					dummyList.add(label2);
					dummyList.add(label3);
					dummyList.add(label4);
					dummyList = PuyoUtilities.checkForMorePuyos(dummyList,
							mainPanel);
					for (int i = 0; i < dummyList.size(); i++) {
						JLabel label = (JLabel) dummyList.get(i);
						if (label != null && label.getIcon() != null) {
							label.setIcon(null);
							score = score + bonusPoint;
						}
					}
					JLabel dummy1 = label1;
					JLabel dummy2 = label4;
					label1.setIcon(null);
					label2.setIcon(null);
					label3.setIcon(null);
					label4.setIcon(null);
					score = score + (point * 4);
					scoreLabel.setText(scoreText + score);
					chainPuyoChecker = true;
					fillVoid(dummy1, dummy2);
				}
			}
			label4 = PuyoUtilities.checkCShape(label3, mainPanel);
			if (label4 != null && label4.getIcon() != null) {
				if (label3.getIcon().equals(label4.getIcon())) {
					ArrayList dummyList = new ArrayList();
					dummyList.add(label1);
					dummyList.add(label2);
					dummyList.add(label3);
					dummyList.add(label4);
					dummyList = PuyoUtilities.checkForMorePuyos(dummyList,
							mainPanel);
					for (int i = 0; i < dummyList.size(); i++) {
						JLabel label = (JLabel) dummyList.get(i);
						if (label != null && label.getIcon() != null) {
							label.setIcon(null);
							score = score + bonusPoint;
						}
					}
					JLabel dummy1 = label1;
					JLabel dummy2 = label4;
					label1.setIcon(null);
					label2.setIcon(null);
					label3.setIcon(null);
					label4.setIcon(null);
					score = score + (point * 4);
					scoreLabel.setText(scoreText + score);
					chainPuyoChecker = true;
					fillVoid(dummy1, dummy2);
				}
			}
		} else if (coordinate == 5) {
			label4 = PuyoUtilities.checkDShape(label1, mainPanel);
			if (label4 != null && label4.getIcon() != null) {
				if (label1.getIcon().equals(label4.getIcon())) {
					ArrayList dummyList = new ArrayList();
					dummyList.add(label1);
					dummyList.add(label2);
					dummyList.add(label3);
					dummyList.add(label4);
					dummyList = PuyoUtilities.checkForMorePuyos(dummyList,
							mainPanel);
					for (int i = 0; i < dummyList.size(); i++) {
						JLabel label = (JLabel) dummyList.get(i);
						if (label != null && label.getIcon() != null) {
							label.setIcon(null);
							score = score + bonusPoint;
						}
					}
					JLabel dummy1 = label1;
					JLabel dummy2 = label4;
					label1.setIcon(null);
					label2.setIcon(null);
					label3.setIcon(null);
					label4.setIcon(null);
					score = score + (point * 4);
					scoreLabel.setText(scoreText + score);
					chainPuyoChecker = true;
					fillVoid(dummy1, dummy2);
				}
			}
			label4 = PuyoUtilities.checkEShape(label2, mainPanel);
			if (label4 != null && label4.getIcon() != null) {
				if (label2.getIcon().equals(label4.getIcon())) {
					ArrayList dummyList = new ArrayList();
					dummyList.add(label1);
					dummyList.add(label2);
					dummyList.add(label3);
					dummyList.add(label4);
					dummyList = PuyoUtilities.checkForMorePuyos(dummyList,
							mainPanel);
					for (int i = 0; i < dummyList.size(); i++) {
						JLabel label = (JLabel) dummyList.get(i);
						if (label != null && label.getIcon() != null) {
							label.setIcon(null);
							score = score + bonusPoint;
						}
					}
					JLabel dummy1 = label1;
					JLabel dummy2 = label4;
					label1.setIcon(null);
					label2.setIcon(null);
					label3.setIcon(null);
					label4.setIcon(null);
					score = score + (point * 4);
					scoreLabel.setText(scoreText + score);
					chainPuyoChecker = true;
					fillVoid(dummy1, dummy2);
				}
			}
			label4 = PuyoUtilities.checkFShape(label3, mainPanel);
			if (label4 != null && label4.getIcon() != null) {
				if (label3.getIcon().equals(label4.getIcon())) {
					ArrayList dummyList = new ArrayList();
					dummyList.add(label1);
					dummyList.add(label2);
					dummyList.add(label3);
					dummyList.add(label4);
					dummyList = PuyoUtilities.checkForMorePuyos(dummyList,
							mainPanel);
					for (int i = 0; i < dummyList.size(); i++) {
						JLabel label = (JLabel) dummyList.get(i);
						if (label != null && label.getIcon() != null) {
							label.setIcon(null);
							score = score + bonusPoint;
						}
					}
					JLabel dummy1 = label1;
					JLabel dummy2 = label4;
					label1.setIcon(null);
					label2.setIcon(null);
					label3.setIcon(null);
					label4.setIcon(null);
					score = score + (point * 4);
					scoreLabel.setText(scoreText + score);
					chainPuyoChecker = true;
					fillVoid(dummy1, dummy2);
				}
			}
		} else {
			label4 = PuyoUtilities.checkAShape(label1, mainPanel);
			if (label4 != null && label4.getIcon() != null) {
				if (label1.getIcon().equals(label4.getIcon())) {
					ArrayList dummyList = new ArrayList();
					dummyList.add(label1);
					dummyList.add(label2);
					dummyList.add(label3);
					dummyList.add(label4);
					dummyList = PuyoUtilities.checkForMorePuyos(dummyList,
							mainPanel);
					for (int i = 0; i < dummyList.size(); i++) {
						JLabel label = (JLabel) dummyList.get(i);
						if (label != null && label.getIcon() != null) {
							label.setIcon(null);
							score = score + bonusPoint;
						}
					}
					JLabel dummy1 = label1;
					JLabel dummy2 = label4;
					label1.setIcon(null);
					label2.setIcon(null);
					label3.setIcon(null);
					label4.setIcon(null);
					score = score + (point * 4);
					scoreLabel.setText(scoreText + score);
					chainPuyoChecker = true;
					fillVoid(dummy1, dummy2);
				}
			}
			label4 = PuyoUtilities.checkBShape(label2, mainPanel);
			if (label4 != null && label4.getIcon() != null) {
				if (label2.getIcon().equals(label4.getIcon())) {
					ArrayList dummyList = new ArrayList();
					dummyList.add(label1);
					dummyList.add(label2);
					dummyList.add(label3);
					dummyList.add(label4);
					dummyList = PuyoUtilities.checkForMorePuyos(dummyList,
							mainPanel);
					for (int i = 0; i < dummyList.size(); i++) {
						JLabel label = (JLabel) dummyList.get(i);
						if (label != null && label.getIcon() != null) {
							label.setIcon(null);
							score = score + bonusPoint;
						}
					}
					JLabel dummy1 = label1;
					JLabel dummy2 = label4;
					label1.setIcon(null);
					label2.setIcon(null);
					label3.setIcon(null);
					label4.setIcon(null);
					score = score + (point * 4);
					scoreLabel.setText(scoreText + score);
					chainPuyoChecker = true;
					fillVoid(dummy1, dummy2);
				}
			}
			label4 = PuyoUtilities.checkCShape(label3, mainPanel);
			if (label4 != null && label4.getIcon() != null) {
				if (label3.getIcon().equals(label4.getIcon())) {
					ArrayList dummyList = new ArrayList();
					dummyList.add(label1);
					dummyList.add(label2);
					dummyList.add(label3);
					dummyList.add(label4);
					dummyList = PuyoUtilities.checkForMorePuyos(dummyList,
							mainPanel);
					for (int i = 0; i < dummyList.size(); i++) {
						JLabel label = (JLabel) dummyList.get(i);
						if (label != null && label.getIcon() != null) {
							label.setIcon(null);
							score = score + bonusPoint;
						}
					}
					JLabel dummy1 = label1;
					JLabel dummy2 = label4;
					label1.setIcon(null);
					label2.setIcon(null);
					label3.setIcon(null);
					label4.setIcon(null);
					score = score + (point * 4);
					scoreLabel.setText(scoreText + score);
					chainPuyoChecker = true;
					fillVoid(dummy1, dummy2);
				}
			}
			label4 = PuyoUtilities.checkDShape(label1, mainPanel);
			if (label4 != null && label4.getIcon() != null) {
				if (label1.getIcon().equals(label4.getIcon())) {
					ArrayList dummyList = new ArrayList();
					dummyList.add(label1);
					dummyList.add(label2);
					dummyList.add(label3);
					dummyList.add(label4);
					dummyList = PuyoUtilities.checkForMorePuyos(dummyList,
							mainPanel);
					for (int i = 0; i < dummyList.size(); i++) {
						JLabel label = (JLabel) dummyList.get(i);
						if (label != null && label.getIcon() != null) {
							label.setIcon(null);
							score = score + bonusPoint;
						}
					}
					JLabel dummy1 = label1;
					JLabel dummy2 = label4;
					label1.setIcon(null);
					label2.setIcon(null);
					label3.setIcon(null);
					label4.setIcon(null);
					score = score + (point * 4);
					scoreLabel.setText(scoreText + score);
					chainPuyoChecker = true;
					fillVoid(dummy1, dummy2);
				}
			}
			label4 = PuyoUtilities.checkEShape(label2, mainPanel);
			if (label4 != null && label4.getIcon() != null) {
				if (label2.getIcon().equals(label4.getIcon())) {
					ArrayList dummyList = new ArrayList();
					dummyList.add(label1);
					dummyList.add(label2);
					dummyList.add(label3);
					dummyList.add(label4);
					dummyList = PuyoUtilities.checkForMorePuyos(dummyList,
							mainPanel);
					for (int i = 0; i < dummyList.size(); i++) {
						JLabel label = (JLabel) dummyList.get(i);
						if (label != null && label.getIcon() != null) {
							label.setIcon(null);
							score = score + bonusPoint;
						}
					}
					JLabel dummy1 = label1;
					JLabel dummy2 = label4;
					label1.setIcon(null);
					label2.setIcon(null);
					label3.setIcon(null);
					label4.setIcon(null);
					score = score + (point * 4);
					scoreLabel.setText(scoreText + score);
					chainPuyoChecker = true;
					fillVoid(dummy1, dummy2);
				}
			}
			label4 = PuyoUtilities.checkFShape(label3, mainPanel);
			if (label4 != null && label4.getIcon() != null) {
				if (label3.getIcon().equals(label4.getIcon())) {
					ArrayList dummyList = new ArrayList();
					dummyList.add(label1);
					dummyList.add(label2);
					dummyList.add(label3);
					dummyList.add(label4);
					dummyList = PuyoUtilities.checkForMorePuyos(dummyList,
							mainPanel);
					for (int i = 0; i < dummyList.size(); i++) {
						JLabel label = (JLabel) dummyList.get(i);
						if (label != null && label.getIcon() != null) {
							label.setIcon(null);
							score = score + bonusPoint;
						}
					}
					JLabel dummy1 = label1;
					JLabel dummy2 = label4;
					label1.setIcon(null);
					label2.setIcon(null);
					label3.setIcon(null);
					label4.setIcon(null);
					score = score + (point * 4);
					scoreLabel.setText(scoreText + score);
					chainPuyoChecker = true;
					fillVoid(dummy1, dummy2);
				}
			}
		}
	}

	/**
	 * Method which process square puyos and clears them. Method added as a
	 * requirement update.
	 * 
	 * @param label1
	 * @param label2
	 */
	private void process(JLabel label1, JLabel label2) {
		/**
		 * This method checks for square shape. If two puyos of the same column
		 * has same colurs, this method further investigates by checking the
		 * right/left of those two puyos. If those puyos too has same colors,
		 * then it clears them.
		 */
		try {
			JLabel label3 = PuyoUtilities.getNextRowElement(mainPanel, label1
					.getName());
			JLabel label4 = PuyoUtilities.getNextRowElement(mainPanel, label2
					.getName());
			if (label3 != null && label4 != null) {
				ImageIcon icon1 = (ImageIcon) label1.getIcon();
				ImageIcon icon2 = (ImageIcon) label2.getIcon();
				ImageIcon icon3 = (ImageIcon) label3.getIcon();
				ImageIcon icon4 = (ImageIcon) label4.getIcon();
				if (icon1 != null && icon2 != null && icon3 != null
						&& icon4 != null) {
					if (icon1.equals(icon3) && icon2.equals(icon4)) {
						ArrayList dummyList = new ArrayList();
						dummyList.add(label1);
						dummyList.add(label2);
						dummyList.add(label3);
						dummyList.add(label4);
						dummyList = PuyoUtilities.checkForMorePuyos(dummyList,
								mainPanel);
						for (int i = 0; i < dummyList.size(); i++) {
							JLabel label = (JLabel) dummyList.get(i);
							if (label != null && label.getIcon() != null) {
								label.setIcon(null);
								score = score + bonusPoint;
							}
						}
						JLabel dummy1 = label1;
						JLabel dummy2 = label3;
						label1.setIcon(null);
						label2.setIcon(null);
						label3.setIcon(null);
						label4.setIcon(null);
						score = score + (point * 4);
						scoreLabel.setText(scoreText + score);
						chainPuyoChecker = true;
						fillVoid(dummy1, dummy2);
					}
				}
			}
		} catch (Exception ex) {

		}
	}

	/**
	 * Method fills the void left after square puyos are cleared. Method added
	 * as a requirement update.
	 * 
	 * @param columnA
	 * @param columnB
	 */
	private void fillVoid(JLabel columnA, JLabel columnB) {
		/**
		 * This method fills the gap created by clearence of shape puyos. It
		 * takes two labels as parameters. It gets all the labels above the
		 * given two columns, and then calculates the gaps and puts the puyos in
		 * their respective places.
		 */
		try {
			ArrayList columnAList = PuyoUtilities
					.liveElementsAboveTheLabelInTheColumn(columnA, mainPanel);
			while (columnAList.size() != 0) {
				JLabel label = PuyoUtilities.bottommostColumnElement(columnA,
						mainPanel);
				JLabel intLabel = (JLabel) columnAList.get(0);
				ImageIcon i = (ImageIcon) intLabel.getIcon();
				label.setIcon(i);
				intLabel.setIcon(null);
				columnAList.remove(0);
			}
			ArrayList columnBList = PuyoUtilities
					.liveElementsAboveTheLabelInTheColumn(columnB, mainPanel);
			while (columnBList.size() != 0) {
				JLabel label = PuyoUtilities.bottommostColumnElement(columnB,
						mainPanel);
				JLabel intLabel = (JLabel) columnBList.get(0);
				ImageIcon i = (ImageIcon) intLabel.getIcon();
				label.setIcon(i);
				intLabel.setIcon(null);
				columnBList.remove(0);
			}
			fillVoid();
		} catch (Exception ex) {

		}
	}

	/**
	 * Method which fills the gaps left by chain combos.
	 * 
	 */
	private void fillVoid() {
		try {
			ArrayList columnZeroList = PuyoUtilities.getElementsOfCoordinate(
					mainPanel, 0);
			JLabel bottomMostZeroElement = PuyoUtilities
					.bottommostColumnElement(columnZeroList);
			ArrayList liveZeroList = PuyoUtilities
					.liveElementsAboveTheLabelInTheColumn(columnZeroList,
							bottomMostZeroElement);
			while (liveZeroList.size() != 0) {
				bottomMostZeroElement = PuyoUtilities
						.bottommostColumnElement(columnZeroList);
				JLabel label = (JLabel) liveZeroList.get(0);
				bottomMostZeroElement.setIcon(label.getIcon());
				label.setIcon(null);
				liveZeroList.remove(0);
			}

			columnZeroList = PuyoUtilities
					.getElementsOfCoordinate(mainPanel, 1);
			bottomMostZeroElement = PuyoUtilities
					.bottommostColumnElement(columnZeroList);
			liveZeroList = PuyoUtilities.liveElementsAboveTheLabelInTheColumn(
					columnZeroList, bottomMostZeroElement);
			while (liveZeroList.size() != 0) {
				bottomMostZeroElement = PuyoUtilities
						.bottommostColumnElement(columnZeroList);
				JLabel label = (JLabel) liveZeroList.get(0);
				bottomMostZeroElement.setIcon(label.getIcon());
				label.setIcon(null);
				liveZeroList.remove(0);
			}

			columnZeroList = PuyoUtilities
					.getElementsOfCoordinate(mainPanel, 2);
			bottomMostZeroElement = PuyoUtilities
					.bottommostColumnElement(columnZeroList);
			liveZeroList = PuyoUtilities.liveElementsAboveTheLabelInTheColumn(
					columnZeroList, bottomMostZeroElement);
			while (liveZeroList.size() != 0) {
				bottomMostZeroElement = PuyoUtilities
						.bottommostColumnElement(columnZeroList);
				JLabel label = (JLabel) liveZeroList.get(0);
				bottomMostZeroElement.setIcon(label.getIcon());
				label.setIcon(null);
				liveZeroList.remove(0);
			}

			columnZeroList = PuyoUtilities
					.getElementsOfCoordinate(mainPanel, 3);
			bottomMostZeroElement = PuyoUtilities
					.bottommostColumnElement(columnZeroList);
			liveZeroList = PuyoUtilities.liveElementsAboveTheLabelInTheColumn(
					columnZeroList, bottomMostZeroElement);
			while (liveZeroList.size() != 0) {
				bottomMostZeroElement = PuyoUtilities
						.bottommostColumnElement(columnZeroList);
				JLabel label = (JLabel) liveZeroList.get(0);
				bottomMostZeroElement.setIcon(label.getIcon());
				label.setIcon(null);
				liveZeroList.remove(0);
			}

			columnZeroList = PuyoUtilities
					.getElementsOfCoordinate(mainPanel, 4);
			bottomMostZeroElement = PuyoUtilities
					.bottommostColumnElement(columnZeroList);
			liveZeroList = PuyoUtilities.liveElementsAboveTheLabelInTheColumn(
					columnZeroList, bottomMostZeroElement);
			while (liveZeroList.size() != 0) {
				bottomMostZeroElement = PuyoUtilities
						.bottommostColumnElement(columnZeroList);
				JLabel label = (JLabel) liveZeroList.get(0);
				bottomMostZeroElement.setIcon(label.getIcon());
				label.setIcon(null);
				liveZeroList.remove(0);
			}

			columnZeroList = PuyoUtilities
					.getElementsOfCoordinate(mainPanel, 5);
			bottomMostZeroElement = PuyoUtilities
					.bottommostColumnElement(columnZeroList);
			liveZeroList = PuyoUtilities.liveElementsAboveTheLabelInTheColumn(
					columnZeroList, bottomMostZeroElement);
			while (liveZeroList.size() != 0) {
				bottomMostZeroElement = PuyoUtilities
						.bottommostColumnElement(columnZeroList);
				JLabel label = (JLabel) liveZeroList.get(0);
				bottomMostZeroElement.setIcon(label.getIcon());
				label.setIcon(null);
				liveZeroList.remove(0);
			}
		} catch (Exception ex) {

		}
	}

	/**
	 * Method to validate movement.
	 * 
	 * @param label1
	 * @param label2
	 * @param checker
	 * @return
	 */
	private boolean validateMovement(JLabel label1, JLabel label2, int checker) {
		boolean yes = false;
		try {
			if (checker == 0 || checker == 1 || checker == 2) {
				JLabel dummy1 = PuyoUtilities.getPrevRowElement(mainPanel,
						label1.getName());
				if (dummy1 != null) {
					String letter1 = dummy1.getName().substring(0, 1);
					if (!letter1.equalsIgnoreCase("K")) {
						yes = true;
					}
				}
				JLabel dummy2 = PuyoUtilities.getNextRowElement(mainPanel,
						label1.getName());
				if (dummy2 != null) {
					String letter1 = dummy2.getName().substring(0, 1);
					if (!letter1.equalsIgnoreCase("K")) {
						yes = true;
					}
				}
			} else if (checker == 3) {
				JLabel dummy1 = PuyoUtilities.getPrevRowElement(mainPanel,
						label2.getName());
				if (dummy1 != null) {
					String letter1 = dummy1.getName().substring(0, 1);
					if (!letter1.equalsIgnoreCase("K")) {
						yes = true;
					}
				}
				JLabel dummy2 = PuyoUtilities.getNextRowElement(mainPanel,
						label2.getName());
				if (dummy2 != null) {
					String letter1 = dummy2.getName().substring(0, 1);
					if (!letter1.equalsIgnoreCase("K")) {
						yes = true;
					}
				}
			}
		} catch (Exception ex) {

		}
		return yes;
	}

	/**
	 * Method that takes the puyo through a 360 degree rotation cycle.
	 * 
	 * @param coordinate
	 */
	private void completeRotation(int coordinate) {
		try {
			ArrayList dummyList = new ArrayList();
			if (rotationState == ZERO_DEGREE) {
				JLabel dummyLabel = PuyoUtilities.getNextColumnElement(
						secondLiveLabel, mainPanel);
				if (dummyLabel != null
						&& (!dummyLabel.getName().substring(0, 1)
								.equalsIgnoreCase("L"))
						&& dummyLabel.getIcon() == null) {
					dummyList = PuyoUtilities.calculateBottomOfNinetyPuyo(
							firstLiveLabel, secondLiveLabel, mainPanel);
					bottom_of_first_puyo = (String) dummyList.get(0);
					bottom_of_second_puyo = (String) dummyList.get(1);
					ImageIcon icon = (ImageIcon) firstLiveLabel.getIcon();
					firstLiveLabel.setIcon(null);
					firstLiveLabel = dummyLabel;
					firstLiveLabel.setIcon(icon);
					rotationState = NINTY_DEGREE;
				}
			} else if (rotationState == NINTY_DEGREE) {
				JLabel dummyLabel = PuyoUtilities.getNextRowElement(mainPanel,
						secondLiveLabel.getName());
				JLabel prevOfPrev = PuyoUtilities.getNextColumnElement(
						dummyLabel, mainPanel);
				if (dummyLabel != null && dummyLabel.getIcon() == null
						&& prevOfPrev != null && prevOfPrev.getIcon() == null) {
					dummyList = PuyoUtilities.calculateBottomOfOneEightyPuyo(
							firstLiveLabel, secondLiveLabel, mainPanel);
					bottom_of_first_puyo = (String) dummyList.get(0);
					bottom_of_second_puyo = (String) dummyList.get(1);
					ImageIcon icon = (ImageIcon) firstLiveLabel.getIcon();
					firstLiveLabel.setIcon(null);
					firstLiveLabel = dummyLabel;
					firstLiveLabel.setIcon(icon);
					rotationState = ONE_EIGHTY_DEGREE;
				}
			} else if (rotationState == ONE_EIGHTY_DEGREE) {
				JLabel dummyLabel = PuyoUtilities.getPrevColumnElement(
						secondLiveLabel.getName(), mainPanel);
				if (dummyLabel != null && dummyLabel.getIcon() == null) {
					dummyList = PuyoUtilities.calculateBottomOfTwoSeventyPuyo(
							firstLiveLabel, secondLiveLabel, mainPanel);
					bottom_of_first_puyo = (String) dummyList.get(1);
					bottom_of_second_puyo = (String) dummyList.get(0);
					ImageIcon i = (ImageIcon) firstLiveLabel.getIcon();
					firstLiveLabel.setIcon(null);
					firstLiveLabel = dummyLabel;
					firstLiveLabel.setIcon(i);
					rotationState = TWO_SEVENTY_DEGREE;
				}
			} else if (rotationState == TWO_SEVENTY_DEGREE) {
				JLabel dummyLabel = PuyoUtilities.getPrevRowElement(mainPanel,
						secondLiveLabel.getName());
				JLabel prevOfPrev = PuyoUtilities.getNextColumnElement(
						dummyLabel, mainPanel);
				if (dummyLabel != null && dummyLabel.getIcon() == null
						&& prevOfPrev != null && prevOfPrev.getIcon() == null) {
					dummyList = PuyoUtilities.calculateBottomOfThreeSixtyPuyo(
							firstLiveLabel, secondLiveLabel, mainPanel);
					bottom_of_first_puyo = (String) dummyList.get(0);
					bottom_of_second_puyo = (String) dummyList.get(1);
					ImageIcon icon = (ImageIcon) firstLiveLabel.getIcon();
					firstLiveLabel.setIcon(null);
					firstLiveLabel = dummyLabel;
					firstLiveLabel.setIcon(icon);
					rotationState = ZERO_DEGREE;
				}
			}
		} catch (Exception ex) {

		}
	}

	/**
	 * Method to update bottom of puyo, when the user selects left or right
	 * arrow keys.
	 * 
	 * @param key
	 * @param state_of_rotation
	 * @return
	 */
	private boolean updateBottom(String key, int state_of_rotation) {
		boolean yes = false;
		try {
			if (key.equalsIgnoreCase(LEFT_KEY)) {
				if (state_of_rotation == ZERO_DEGREE) {
					JLabel dummy = PuyoUtilities.getPrevRowElement(mainPanel,
							firstLiveLabel.getName());
					if (dummy != null && dummy.getIcon() == null) {
						bottom_of_first_puyo = PuyoUtilities
								.bottommostColumnElement(dummy, mainPanel)
								.getName();
						bottom_of_second_puyo = PuyoUtilities
								.bottommostColumnElement(firstLiveLabel,
										mainPanel).getName();
						yes = true;
					}
				} else if (state_of_rotation == NINTY_DEGREE) {
					JLabel dummy = PuyoUtilities.getPrevRowElement(mainPanel,
							firstLiveLabel.getName());
					JLabel dummy1 = PuyoUtilities.getPrevRowElement(mainPanel,
							secondLiveLabel.getName());
					if (dummy != null && dummy.getIcon() == null
							&& dummy1 != null && dummy1.getIcon() == null) {
						bottom_of_first_puyo = PuyoUtilities
								.bottommostColumnElement(dummy, mainPanel)
								.getName();
						bottom_of_second_puyo = PuyoUtilities
								.getPrevColumnElement(bottom_of_first_puyo,
										mainPanel).getName();
						yes = true;
					}
				} else if (state_of_rotation == ONE_EIGHTY_DEGREE) {
					JLabel dummy = PuyoUtilities.getPrevRowElement(mainPanel,
							secondLiveLabel.getName());
					if (dummy != null && dummy.getIcon() == null) {
						bottom_of_second_puyo = PuyoUtilities
								.bottommostColumnElement(dummy, mainPanel)
								.getName();
						bottom_of_first_puyo = PuyoUtilities
								.bottommostColumnElement(secondLiveLabel,
										mainPanel).getName();
						yes = true;
					}
				} else {
					JLabel dummy = PuyoUtilities.getPrevRowElement(mainPanel,
							secondLiveLabel.getName());
					if (dummy != null && dummy.getIcon() == null) {
						bottom_of_second_puyo = PuyoUtilities
								.bottommostColumnElement(dummy, mainPanel)
								.getName();
						bottom_of_first_puyo = PuyoUtilities
								.getPrevColumnElement(bottom_of_second_puyo,
										mainPanel).getName();
						yes = true;
					}
				}
			} else if (key.equalsIgnoreCase(RIGHT_KEY)) {
				if (state_of_rotation == ZERO_DEGREE) {
					JLabel dummy = PuyoUtilities.getNextRowElement(mainPanel,
							secondLiveLabel.getName());
					if (dummy != null && dummy.getIcon() == null) {
						bottom_of_second_puyo = PuyoUtilities
								.bottommostColumnElement(dummy, mainPanel)
								.getName();
						bottom_of_first_puyo = PuyoUtilities
								.bottommostColumnElement(secondLiveLabel,
										mainPanel).getName();
						yes = true;
					}
				} else if (state_of_rotation == NINTY_DEGREE) {
					JLabel dummy = PuyoUtilities.getNextRowElement(mainPanel,
							firstLiveLabel.getName());
					JLabel dummy1 = PuyoUtilities.getNextRowElement(mainPanel,
							secondLiveLabel.getName());
					if (dummy != null && dummy.getIcon() == null
							&& dummy1 != null && dummy1.getIcon() == null) {
						bottom_of_first_puyo = PuyoUtilities
								.bottommostColumnElement(dummy, mainPanel)
								.getName();
						bottom_of_second_puyo = PuyoUtilities
								.getPrevColumnElement(bottom_of_first_puyo,
										mainPanel).getName();
						yes = true;
					}
				} else if (state_of_rotation == ONE_EIGHTY_DEGREE) {
					JLabel dummy = PuyoUtilities.getNextRowElement(mainPanel,
							firstLiveLabel.getName());
					if (dummy != null && dummy.getIcon() == null) {
						bottom_of_first_puyo = PuyoUtilities
								.bottommostColumnElement(dummy, mainPanel)
								.getName();
						bottom_of_second_puyo = PuyoUtilities
								.bottommostColumnElement(firstLiveLabel,
										mainPanel).getName();
						yes = true;
					}
				} else {
					JLabel dummy = PuyoUtilities.getNextRowElement(mainPanel,
							secondLiveLabel.getName());
					if (dummy != null && dummy.getIcon() == null) {
						bottom_of_second_puyo = PuyoUtilities
								.bottommostColumnElement(dummy, mainPanel)
								.getName();
						bottom_of_first_puyo = PuyoUtilities
								.getPrevColumnElement(bottom_of_second_puyo,
										mainPanel).getName();
						yes = true;
					}
				}
			}
		} catch (Exception ex) {

		}
		return yes;
	}

	/**
	 * Method that asks the user to input their names after getting a high
	 * score.
	 * 
	 */
	private void askForInput() {
		final JFrame frame = new JFrame();
		frame.setUndecorated(true);
		frame.getContentPane().setLayout(new BorderLayout());
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		frame.setSize(150, 75);
		JLabel label = new JLabel("Enter Your Name");
		final JTextField field = new JTextField();
		JButton button = new JButton("OK");
		panel.add(label);
		panel.add(field);
		frame.add(panel, BorderLayout.CENTER);
		frame.add(button, BorderLayout.SOUTH);
		frame.getRootPane().setDefaultButton(button);
		frame.setVisible(true);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertScore(field.getText());
				frame.dispose();
				new ScoreViewer().setVisible(true);
				PuyoGame.this.dispose();
			}
		});
	}

	/**
	 * Method that inserts the statistics into the DB.
	 * 
	 * @param name
	 */
	private void insertScore(String name) {
		DBHandler dbHandler = new DBHandler();
		dbHandler.insertRecords(name, score);
	}

	/**
	 * Method that takes the user automatically onto the next level, if a
	 * particular score has been reached.
	 * 
	 * @return
	 */
	private boolean scoreChecker() {
		boolean yes = false;
		if (level == 1 && score >= (levelProgressor * level)) {
			yes = true;
		} else if (level == 2 && score >= (levelProgressor * level)) {
			yes = true;
		}
		return yes;
	}
}
