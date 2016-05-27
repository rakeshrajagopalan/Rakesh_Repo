package puyopack;

import java.util.*;
import javax.swing.*;

/**
 * Utility class containing utility methods
 * 
 * @author Rakesh (15-06-07)
 * 
 */

@SuppressWarnings(value = { "unused", "unchecked" })
public class PuyoUtilities {

	public static final int COLUMNS = 6;

	public static final int ROWS = 12;

	private static final int A = 1;

	private static final int B = 2;

	private static final int C = 3;

	private static final int D = 4;

	private static final int E = 5;

	private static final int F = 6;

	private static final int G = 7;

	private static final int H = 8;

	private static final int I = 9;

	private static final int J = 10;

	private static final int K = 11;

	private static final int L = 12;

	/**
	 * Static utility method to compare two puyos
	 * 
	 * @param firstBottom
	 * @param secondBottom
	 * @return
	 */
	public static String whoIsGreater(String firstBottom, String secondBottom) {
		String first = firstBottom.substring(0, 1);
		String second = secondBottom.substring(0, 1);
		int firstInt = 0;
		int secondInt = 0;
		if (first.equalsIgnoreCase(second)) {
			return "equal";
		} else {
			if (first.equalsIgnoreCase("A")) {
				firstInt = 1;
			} else if (first.equalsIgnoreCase("B")) {
				firstInt = 2;
			} else if (first.equalsIgnoreCase("C")) {
				firstInt = 3;
			} else if (first.equalsIgnoreCase("D")) {
				firstInt = 4;
			} else if (first.equalsIgnoreCase("E")) {
				firstInt = 5;
			} else if (first.equalsIgnoreCase("F")) {
				firstInt = 6;
			} else if (first.equalsIgnoreCase("G")) {
				firstInt = 7;
			} else if (first.equalsIgnoreCase("H")) {
				firstInt = 8;
			} else if (first.equalsIgnoreCase("I")) {
				firstInt = 9;
			} else if (first.equalsIgnoreCase("J")) {
				firstInt = 10;
			} else if (first.equalsIgnoreCase("K")) {
				firstInt = 11;
			} else if (first.equalsIgnoreCase("L")) {
				firstInt = 12;
			}
			if (second.equalsIgnoreCase("A")) {
				secondInt = 1;
			} else if (second.equalsIgnoreCase("B")) {
				secondInt = 2;
			} else if (second.equalsIgnoreCase("C")) {
				secondInt = 3;
			} else if (second.equalsIgnoreCase("D")) {
				secondInt = 4;
			} else if (second.equalsIgnoreCase("E")) {
				secondInt = 5;
			} else if (second.equalsIgnoreCase("F")) {
				secondInt = 6;
			} else if (second.equalsIgnoreCase("G")) {
				secondInt = 7;
			} else if (second.equalsIgnoreCase("H")) {
				secondInt = 8;
			} else if (second.equalsIgnoreCase("I")) {
				secondInt = 9;
			} else if (second.equalsIgnoreCase("J")) {
				secondInt = 10;
			} else if (second.equalsIgnoreCase("K")) {
				secondInt = 11;
			} else if (second.equalsIgnoreCase("L")) {
				secondInt = 12;
			}
			if (firstInt > secondInt) {
				return "first";
			} else {
				return "second";
			}
		}
	}

	/**
	 * Static utility method, which flips the coordinates
	 * 
	 * @param coordinate
	 * @return
	 */
	public static int flipCoordinates(int coordinate) {
		if (coordinate == 0) {
			return 5;
		} else if (coordinate == 1) {
			return 2;
		} else if (coordinate == 2) {
			return 3;
		} else if (coordinate == 3) {
			return 4;
		} else {
			return 0;
		}
	}

	/**
	 * Returns all the panel of the component
	 * 
	 * @param panel
	 * @return
	 */
	public static JLabel[] componentsOfThePanel(JPanel panel) {
		int compCount = panel.getComponentCount();
		JLabel[] column = new JLabel[compCount];
		for (int i = 0; i < compCount; i++) {
			column[i] = (JLabel) panel.getComponent(i);
		}
		return column;
	}

	/**
	 * Returns all the panel of the component arranged by column
	 * 
	 * @param panel
	 * @return
	 */
	public static JLabel[] columnArray(JPanel panel) {
		int componentCount = panel.getComponentCount();
		JLabel[] columnArray = new JLabel[componentCount];
		for (int i = 0; i < COLUMNS; i++) {
			int temp = 0;
			for (int j = 0; j < componentCount; j = j + COLUMNS) {
				columnArray[temp] = (JLabel) panel.getComponent(i + j);
				temp = temp + 1;
			}
		}
		return columnArray;
	}

	/**
	 * Method, which returns the next row element
	 * 
	 * @param panel
	 * @param name
	 * @return
	 */
	public static JLabel getNextRowElement(JPanel panel, String name) {
		JLabel[] temp = componentsOfThePanel(panel);
		JLabel toReturn = null;
		if (!name.substring(1, 2).equalsIgnoreCase(
				new Integer(COLUMNS - 1).toString())) {
			LOOP: for (int i = 0; i < temp.length; i++) {
				toReturn = (JLabel) panel.getComponent(i);
				if (toReturn.getName().equalsIgnoreCase(name)) {
					toReturn = (JLabel) panel.getComponent(i + 1);
					return toReturn;
				} else {
					continue LOOP;
				}
			}
			return toReturn;
		} else {
			return null;
		}
	}

	/**
	 * Method, which returns the next row element
	 * 
	 * @param panel
	 * @param name
	 * @return
	 */
	public static JLabel getPrevRowElement(JPanel panel, String name) {
		JLabel[] temp = componentsOfThePanel(panel);
		JLabel toReturn = null;
		if (!name.substring(1, 2).equalsIgnoreCase("0")) {
			LOOP: for (int i = 0; i < temp.length; i++) {
				toReturn = (JLabel) panel.getComponent(i);
				if (toReturn.getName().equalsIgnoreCase(name)) {
					toReturn = (JLabel) panel.getComponent(i - 1);
					return toReturn;
				} else {
					continue LOOP;
				}
			}
			return toReturn;
		} else {
			return null;
		}
	}

	/**
	 * Method to obtain next column element
	 * 
	 * @param prev
	 * @param panel
	 * @return
	 */
	public static JLabel getNextColumnElement(JLabel prev, JPanel panel) {
		String name = prev.getName();
		JLabel toReturn = null;
		int compCount = panel.getComponentCount();
		LOOP: for (int i = 0; i < compCount; i++) {
			toReturn = (JLabel) panel.getComponent(i);
			if (toReturn.getName().equalsIgnoreCase(name)) {
				toReturn = (JLabel) panel.getComponent(i + COLUMNS);
				break;
			} else {
				continue LOOP;
			}
		}
		return toReturn;
	}

	/**
	 * Method to obtain prev column element
	 * 
	 * @param prev
	 * @param panel
	 * @return
	 */
	public static JLabel getPrevColumnElement(String name, JPanel panel) {
		// String name = prev.getName();
		JLabel toReturn = null;
		int compCount = panel.getComponentCount();
		LOOP: for (int i = compCount - 1; i > 0; i--) {
			toReturn = (JLabel) panel.getComponent(i);
			if (toReturn.getName().equalsIgnoreCase(name)) {
				toReturn = (JLabel) panel.getComponent(i - COLUMNS);
				break;
			} else {
				continue LOOP;
			}
		}
		return toReturn;
	}

	/**
	 * This method specifies which row has been cleared
	 * 
	 * @param labelName
	 * @param panel
	 * @return
	 */
	public static int componentsToLookFor(String labelName, JPanel panel) {
		int totalComponents = panel.getComponentCount();
		String start = labelName.substring(0, 1);
		if (start.equalsIgnoreCase("L")) {
			return totalComponents - (COLUMNS * A);
		} else if (start.equalsIgnoreCase("K")) {
			return totalComponents - (COLUMNS * B);
		} else if (start.equalsIgnoreCase("J")) {
			return totalComponents - (COLUMNS * C);
		} else if (start.equalsIgnoreCase("I")) {
			return totalComponents - (COLUMNS * D);
		} else if (start.equalsIgnoreCase("H")) {
			return totalComponents - (COLUMNS * E);
		} else if (start.equalsIgnoreCase("G")) {
			return totalComponents - (COLUMNS * F);
		} else if (start.equalsIgnoreCase("F")) {
			return totalComponents - (COLUMNS * G);
		} else if (start.equalsIgnoreCase("E")) {
			return totalComponents - (COLUMNS * H);
		} else if (start.equalsIgnoreCase("D")) {
			return totalComponents - (COLUMNS * I);
		} else if (start.equalsIgnoreCase("C")) {
			return totalComponents - (COLUMNS * J);
		} else {
			return totalComponents - (COLUMNS * K);
		}
	}

	/**
	 * Method which returns the components of a column
	 * 
	 * @param panel
	 * @param coordinate
	 * @return
	 */
	public static ArrayList getElementsOfCoordinate(JPanel panel, int coordinate) {
		ArrayList compList = new ArrayList();
		for (int i = coordinate; i < panel.getComponentCount(); i = i + COLUMNS) {
			compList.add(panel.getComponent(i));
		}
		return compList;
	}

	/**
	 * Method to get bottom of the column
	 * 
	 * @param components
	 * @return
	 */
	public static ArrayList calculateBottomForVerticalPuyo(ArrayList components) {
		String name1 = null;
		String name2 = null;
		LOOP: for (int i = (components.size()) - 1; i > 0; i--) {
			JLabel dummy = (JLabel) components.get(i);
			if (dummy.getIcon() == null) {
				name1 = dummy.getName();
				name2 = ((JLabel) components.get(i - 1)).getName();
				break;
			} else {
				continue LOOP;
			}
		}
		components.clear();
		components.add(name1);
		components.add(name2);
		return components;
	}

	/**
	 * Method that tells where the top most puyo is, in that particular column.
	 * Difference between this and the calculateBottom() method is that, this
	 * returns a JLabel, while the latter returns a String. Method written as a
	 * bug-fix.
	 * 
	 * @param panel
	 * @param coordinate
	 * @param key
	 * @return
	 */
	public static JLabel getTopMostColumnElement(JPanel panel, int coordinate,
			String key) {
		JLabel topLabel = null;
		LOOP: for (int i = coordinate; i < panel.getComponentCount(); i = (i + COLUMNS)) {
			topLabel = (JLabel) panel.getComponent(i);
			if (topLabel.getIcon() != null) {
				return topLabel;
			} else {
				continue LOOP;
			}
		}
		return topLabel;
	}

	/**
	 * Returns all the panel of the component arranged by column. Method added
	 * as a requirement update.
	 * 
	 * @param panel
	 * @return
	 */
	public static ArrayList columnArrayList(JPanel panel) {
		ArrayList toReturn = new ArrayList();
		for (int i = 0; i < COLUMNS; i++) {
			for (int j = 0; j < panel.getComponentCount(); j += COLUMNS) {
				toReturn.add(panel.getComponent(i + j));
			}
		}
		return toReturn;
	}

	/**
	 * Method which checks for occurance of square shape. Method added as a
	 * requirement update.
	 * 
	 * @param panel
	 * @return
	 */
	public static ArrayList clearSquareShape(JPanel panel) {
		ArrayList toReturnList = new ArrayList();
		ArrayList columnList = columnArrayList(panel);
		OUTER: for (int i = 0; i < (columnList.size() - 1); i += 1) {
			int redCount = 0;
			int blueCount = 0;
			int greenCount = 0;
			int yellowCount = 0;
			INNER: for (int j = 0; j < 2; j++) {
				JLabel label = (JLabel) columnList.get(i + j);
				ImageIcon icon = (ImageIcon) label.getIcon();
				if (icon != null && icon.equals(PuyoGame.redPuyo)) {
					redCount += 1;
				} else if (icon != null && icon.equals(PuyoGame.bluePuyo)) {
					blueCount += 1;
				} else if (icon != null && icon.equals(PuyoGame.greenPuyo)) {
					greenCount += 1;
				} else if (icon != null && icon.equals(PuyoGame.yellowPuyo)) {
					yellowCount += 1;
				}
				if (redCount == 2 || blueCount == 2 || greenCount == 2
						|| yellowCount == 2) {
					toReturnList.add(columnList.get(i));
					toReturnList.add(label);
					// break OUTER;
					// } else {
					// continue OUTER;
				}
			}
		}
		return toReturnList;
	}

	/**
	 * Method which checks for occurance of a shape. Method added as a
	 * requirement update.
	 * 
	 * @param panel
	 * @return
	 */
	public static ArrayList detectColumnShapes(JPanel panel) {
		ArrayList toReturnList = new ArrayList();
		ArrayList columnList = columnArrayList(panel);
		OUTER: for (int i = 0; i < (columnList.size() - 2); i += 1) {
			int redCount = 0;
			int blueCount = 0;
			int greenCount = 0;
			int yellowCount = 0;
			INNER: for (int j = 0; j < 3; j++) {
				JLabel label3 = (JLabel) columnList.get(i + j);
				ImageIcon icon = (ImageIcon) label3.getIcon();
				if (icon != null && icon.equals(PuyoGame.redPuyo)) {
					redCount += 1;
				} else if (icon != null && icon.equals(PuyoGame.bluePuyo)) {
					blueCount += 1;
				} else if (icon != null && icon.equals(PuyoGame.greenPuyo)) {
					greenCount += 1;
				} else if (icon != null && icon.equals(PuyoGame.yellowPuyo)) {
					yellowCount += 1;
				}
				if (redCount == 3 || blueCount == 3 || greenCount == 3
						|| yellowCount == 3) {
					JLabel label2 = getPrevColumnElement(label3.getName(),
							panel);
					JLabel label1 = getPrevColumnElement(label2.getName(),
							panel);
					toReturnList.add(label1);
					toReturnList.add(label2);
					toReturnList.add(label3);
					// break OUTER;
					// } else {
					// continue OUTER;
				}
			}
		}
		return toReturnList;
	}

	/**
	 * This method returns all the puyos with icons above the given label.
	 * Method added as a requirement update.
	 * 
	 * @param label
	 * @param panel
	 * @return
	 */
	public static ArrayList liveElementsAboveTheLabelInTheColumn(JLabel label,
			JPanel panel) {
		ArrayList toReturnList = new ArrayList();
		String element = label.getName().substring(1, 2);
		ArrayList columnList = getElementsOfCoordinate(panel, Integer
				.parseInt(element));
		int index = 0;
		for (int i = (columnList.size() - 1); i >= 0; i--) {
			JLabel intlabel = (JLabel) columnList.get(i);
			if (intlabel.getName().equalsIgnoreCase(label.getName())) {
				index = i - 1;
				break;
			}
		}
		for (int i = index; i >= 0; i--) {
			JLabel dummyLabel = (JLabel) columnList.get(i);
			if (dummyLabel.getIcon() == null) {
				break;
			} else {
				toReturnList.add(dummyLabel);
				continue;
			}
		}
		return toReturnList;
	}

	/**
	 * This method calculates and returns the bottommost label in the column
	 * (Label without icon). Method added as a requirement update.
	 * 
	 * @param label
	 * @param panel
	 * @return
	 */
	public static JLabel bottommostColumnElement(JLabel label, JPanel panel) {
		JLabel toReturnLabel = null;
		String element = label.getName().substring(1, 2);
		ArrayList columnList = getElementsOfCoordinate(panel, Integer
				.parseInt(element));
		for (int i = (columnList.size() - 1); i >= 0; i--) {
			toReturnLabel = (JLabel) columnList.get(i);
			if (toReturnLabel.getIcon() == null) {
				break;
			} else {
				continue;
			}
		}
		return toReturnLabel;
	}

	/**
	 * This method detects the following pattern. Method added as a requirement
	 * update.
	 * 
	 * @param label1
	 * @param panel
	 * @return
	 */
	// O O
	// O
	// O
	//
	public static JLabel checkAShape(JLabel label1, JPanel panel) {
		JLabel label = getNextRowElement(panel, label1.getName());
		return label;
	}

	/**
	 * This method detects the following pattern. Method added as a requirement
	 * update.
	 * 
	 * @param label2
	 * @param panel
	 * @return
	 */
	// O
	// O O
	// O
	//
	public static JLabel checkBShape(JLabel label2, JPanel panel) {
		JLabel label = getNextRowElement(panel, label2.getName());
		return label;
	}

	/**
	 * This method detects the following pattern. Method added as a requirement
	 * update.
	 * 
	 * @param label3
	 * @param panel
	 * @return
	 */
	// O
	// O
	// O O
	//
	public static JLabel checkCShape(JLabel label3, JPanel panel) {
		JLabel label = getNextRowElement(panel, label3.getName());
		return label;
	}

	/**
	 * This method detects the following pattern. Method added as a requirement
	 * update.
	 * 
	 * @param label1
	 * @param panel
	 * @return
	 */
	// O O
	// O
	// O
	//
	public static JLabel checkDShape(JLabel label1, JPanel panel) {
		JLabel label = getPrevRowElement(panel, label1.getName());
		return label;
	}

	/**
	 * This method detects the following pattern. Method added as a requirement
	 * update.
	 * 
	 * @param label2
	 * @param panel
	 * @return
	 */
	// O
	// O O
	// O
	//
	public static JLabel checkEShape(JLabel label2, JPanel panel) {
		JLabel label = getPrevRowElement(panel, label2.getName());
		return label;
	}

	/**
	 * This method detects the following pattern. Method added as a requirement
	 * update.
	 * 
	 * @param label3
	 * @param panel
	 * @return
	 */
	// O
	// O
	// O O
	//
	public static JLabel checkFShape(JLabel label3, JPanel panel) {
		JLabel label = getPrevRowElement(panel, label3.getName());
		return label;
	}

	/**
	 * This method detects the following pattern. Method added as a requirement
	 * update.
	 * 
	 * @param label1
	 * @param panel
	 * @return
	 */
	// O O O
	// O
	//
	public static ArrayList checkGShape(JLabel label1, JPanel panel) {
		ArrayList toReturnList = new ArrayList();
		JLabel toReturn = getNextRowElement(panel, label1.getName());
		toReturnList.add(toReturn);
		toReturn = getNextRowElement(panel, toReturn.getName());
		toReturnList.add(toReturn);
		return toReturnList;
	}

	/**
	 * This method detects the following pattern. Method added as a requirement
	 * update.
	 * 
	 * @param label1
	 * @param panel
	 * @return
	 */
	// O O O
	// O
	//
	public static ArrayList checkHShape(JLabel label1, JPanel panel) {
		ArrayList toReturnList = new ArrayList();
		JLabel toReturn = getNextRowElement(panel, label1.getName());
		toReturnList.add(toReturn);
		toReturn = getPrevRowElement(panel, label1.getName());
		toReturnList.add(toReturn);
		return toReturnList;
	}

	/**
	 * This method detects the following pattern. Method added as a requirement
	 * update.
	 * 
	 * @param label1
	 * @param panel
	 * @return
	 */
	// O O O
	// O
	//
	public static ArrayList checkIShape(JLabel label1, JPanel panel) {
		ArrayList toReturnList = new ArrayList();
		JLabel toReturn = getPrevRowElement(panel, label1.getName());
		toReturnList.add(toReturn);
		toReturn = getPrevRowElement(panel, toReturn.getName());
		toReturnList.add(toReturn);
		return toReturnList;
	}

	/**
	 * This method detects the following pattern. Method added as a requirement
	 * update.
	 * 
	 * @param label2
	 * @param panel
	 * @return
	 */
	// O
	// O O O
	// 
	public static ArrayList checkJShape(JLabel label2, JPanel panel) {
		ArrayList toReturnList = new ArrayList();
		JLabel toReturn = getNextRowElement(panel, label2.getName());
		toReturnList.add(toReturn);
		toReturn = getNextRowElement(panel, toReturn.getName());
		toReturnList.add(toReturn);
		return toReturnList;
	}

	/**
	 * This method detects the following pattern. Method added as a requirement
	 * update.
	 * 
	 * @param label2
	 * @param panel
	 * @return
	 */
	// O
	// O O O
	//
	public static ArrayList checkKShape(JLabel label2, JPanel panel) {
		ArrayList toReturnList = new ArrayList();
		JLabel toReturn = getNextRowElement(panel, label2.getName());
		toReturnList.add(toReturn);
		toReturn = getPrevRowElement(panel, label2.getName());
		toReturnList.add(toReturn);
		return toReturnList;
	}

	/**
	 * This method detects the following pattern. Method added as a requirement
	 * update.
	 * 
	 * @param label2
	 * @param panel
	 * @return
	 */
	// O
	// O O O
	//
	public static ArrayList checkLShape(JLabel label2, JPanel panel) {
		ArrayList toReturnList = new ArrayList();
		JLabel toReturn = getPrevRowElement(panel, label2.getName());
		toReturnList.add(toReturn);
		toReturn = getPrevRowElement(panel, toReturn.getName());
		toReturnList.add(toReturn);
		return toReturnList;
	}

	/**
	 * This method detects the following pattern. Method added as a requirement
	 * update.
	 * 
	 * @param label2
	 * @param label2
	 * @param panel
	 * @return
	 */
	// O O
	// O O
	// 
	public static ArrayList checkMShape(JLabel label1, JLabel label2,
			JPanel panel) {
		ArrayList toReturnList = new ArrayList();
		JLabel toReturn = getPrevRowElement(panel, label1.getName());
		toReturnList.add(toReturn);
		toReturn = getNextRowElement(panel, label2.getName());
		toReturnList.add(toReturn);
		return toReturnList;
	}

	/**
	 * This method detects the following pattern. Method added as a requirement
	 * update.
	 * 
	 * @param label2
	 * @param label2
	 * @param panel
	 * @return
	 */
	// O O
	// O O
	// 
	public static ArrayList checkNShape(JLabel label1, JLabel label2,
			JPanel panel) {
		ArrayList toReturnList = new ArrayList();
		JLabel toReturn = getNextRowElement(panel, label1.getName());
		toReturnList.add(toReturn);
		toReturn = getPrevRowElement(panel, label2.getName());
		toReturnList.add(toReturn);
		return toReturnList;
	}

	/**
	 * This method detects the following pattern. Method added as a requirement
	 * update.
	 * 
	 * @param label1
	 * @param panel
	 * @return
	 */
	// O
	// O O
	// O
	public static ArrayList checkOShape(JLabel label1, JPanel panel) {
		ArrayList toReturnList = new ArrayList();
		JLabel toReturn = getNextRowElement(panel, label1.getName());
		toReturnList.add(toReturn);
		toReturn = getPrevColumnElement(toReturn.getName(), panel);
		toReturnList.add(toReturn);
		return toReturnList;
	}

	/**
	 * This method detects the following pattern. Method added as a requirement
	 * update.
	 * 
	 * @param label2
	 * @param panel
	 * @return
	 */
	// O
	// O O
	// O
	public static ArrayList checkPShape(JLabel label2, JPanel panel) {
		ArrayList toReturnList = new ArrayList();
		JLabel toReturn = getNextRowElement(panel, label2.getName());
		toReturnList.add(toReturn);
		toReturn = getNextColumnElement(toReturn, panel);
		toReturnList.add(toReturn);
		return toReturnList;
	}

	/**
	 * Method returns the previous alphabet of the String passed as parameter.
	 * 
	 * @param alphabet
	 * @return
	 */
	private static String returnPreviousAlphabet(String alphabet) {
		if (alphabet.equalsIgnoreCase("L")) {
			return "K";
		} else if (alphabet.equalsIgnoreCase("K")) {
			return "J";
		} else if (alphabet.equalsIgnoreCase("J")) {
			return "I";
		} else if (alphabet.equalsIgnoreCase("I")) {
			return "H";
		} else if (alphabet.equalsIgnoreCase("H")) {
			return "G";
		} else if (alphabet.equalsIgnoreCase("G")) {
			return "F";
		} else if (alphabet.equalsIgnoreCase("F")) {
			return "E";
		} else if (alphabet.equalsIgnoreCase("E")) {
			return "D";
		} else if (alphabet.equalsIgnoreCase("D")) {
			return "C";
		} else if (alphabet.equalsIgnoreCase("C")) {
			return "B";
		} else {
			return "A";
		}
	}

	/**
	 * Method returns the next alphabet of the String passed as parameter.
	 * 
	 * @param alphabet
	 * @return
	 */
	private static String returnNextAlphabet(String alphabet) {
		if (alphabet.equalsIgnoreCase("A")) {
			return "B";
		} else if (alphabet.equalsIgnoreCase("B")) {
			return "C";
		} else if (alphabet.equalsIgnoreCase("C")) {
			return "D";
		} else if (alphabet.equalsIgnoreCase("D")) {
			return "E";
		} else if (alphabet.equalsIgnoreCase("E")) {
			return "F";
		} else if (alphabet.equalsIgnoreCase("F")) {
			return "G";
		} else if (alphabet.equalsIgnoreCase("G")) {
			return "H";
		} else if (alphabet.equalsIgnoreCase("H")) {
			return "I";
		} else if (alphabet.equalsIgnoreCase("I")) {
			return "J";
		} else if (alphabet.equalsIgnoreCase("J")) {
			return "K";
		} else {
			return "L";
		}
	}

	/**
	 * Method returns the row elements of the String passed as parameter.
	 * 
	 * @param name
	 * @param panel
	 * @return
	 */
	private static ArrayList getRowByRowName(String name, JPanel panel) {
		ArrayList rowList = new ArrayList();
		int components = panel.getComponentCount();
		for (int i = 0; i < components; i++) {
			JLabel label = (JLabel) panel.getComponent(i);
			if (label.getName().substring(0, 1).equalsIgnoreCase(name)) {
				rowList.add(label);
			}
		}
		return rowList;
	}

	/**
	 * Method gets the previous two rows of the shape forming-labels.
	 * 
	 * @param name
	 * @param panel
	 * @return
	 */
	private static ArrayList getAdjacentPreviousRows(String name, JPanel panel) {
		ArrayList bigList = new ArrayList();
		try {
			ArrayList nameList = new ArrayList();
			if (name.equalsIgnoreCase("A")) {

			} else if (name.equalsIgnoreCase("B")) {
				nameList.add(returnPreviousAlphabet(name));
			} else {
				String dummy1 = returnPreviousAlphabet(name);
				String dummy2 = returnPreviousAlphabet(dummy1);
				nameList.add(dummy2);
				nameList.add(dummy1);
			}
			for (int i = 0; i < nameList.size(); i++) {
				ArrayList dummyList = getRowByRowName((String) nameList.get(i),
						panel);
				bigList.add(dummyList);
			}
		} catch (Exception ex) {

		}
		return bigList;
	}

	/**
	 * Method gets the next two rows of the shape forming-labels.
	 * 
	 * @param name
	 * @param panel
	 * @return
	 */
	private static ArrayList getAdjacentNextRows(String name, JPanel panel) {
		ArrayList nameList = new ArrayList();
		ArrayList bigList = new ArrayList();
		try {
			if (name.equalsIgnoreCase("L")) {

			} else if (name.equalsIgnoreCase("K")) {
				nameList.add(returnNextAlphabet(name));
			} else {
				String dummy = returnNextAlphabet(name);
				nameList.add(dummy);
				dummy = returnNextAlphabet(dummy);
				nameList.add(dummy);
			}
			for (int i = 0; i < nameList.size(); i++) {
				ArrayList dummyList = getRowByRowName((String) nameList.get(i),
						panel);
				bigList.add(dummyList);
			}
		} catch (Exception ex) {

		}
		return bigList;
	}

	/**
	 * Method to get current rows.
	 * 
	 * @param nameList
	 * @param panel
	 * @return
	 */
	private static ArrayList getCurrentRows(ArrayList nameList, JPanel panel) {
		/**
		 * Method which gets all the elements of the rows which formed the
		 * shape.
		 */
		ArrayList bigList = new ArrayList();
		try {
			for (int i = 0; i < nameList.size(); i++) {
				ArrayList dummyList = getRowByRowName((String) nameList.get(i),
						panel);
				bigList.add(dummyList);
			}
		} catch (Exception ex) {

		}
		return bigList;
	}

	/**
	 * Method which detects for more than 4 colors
	 * 
	 * @param labelList
	 * @param panel
	 * @return
	 */
	public static ArrayList checkForMorePuyos(ArrayList labelList, JPanel panel) {
		/**
		 * This method detects whether more than 4 colors are present in the
		 * current chain. First of all, the 4 about to be cleared labels are
		 * sent. The method gets the predecessor and successor rows to check
		 * similar icons exists. Then, the method validates whether they form
		 * legitimate chains. If they form, the lables are returned back to its
		 * caller, which then clears those labels as well, while clearing the
		 * initial four labels, which formed the chain.
		 */
		ArrayList rowList = new ArrayList();
		ArrayList chunkList = new ArrayList();
		try {
			JLabel label1 = (JLabel) labelList.get(0);
			JLabel label2 = (JLabel) labelList.get(1);
			JLabel label3 = (JLabel) labelList.get(2);
			JLabel label4 = (JLabel) labelList.get(3);
			ImageIcon commonIcon = (ImageIcon) label1.getIcon();
			ArrayList nameList = new ArrayList();
			ArrayList filteredList = new ArrayList();
			for (int i = 0; i < labelList.size(); i++) {
				JLabel label = (JLabel) labelList.get(i);
				String tempname = label.getName().substring(0, 1);
				if (!nameList.contains(tempname)) {
					nameList.add(tempname);
				}
			}
			ArrayList previousRowList = getAdjacentPreviousRows(
					(String) nameList.get(0), panel);
			ArrayList currentRowList = getCurrentRows(nameList, panel);
			ArrayList nextRowList = null;
			if (nameList.size() == 3) {
				nextRowList = getAdjacentNextRows((String) nameList.get(2),
						panel);
			} else if (nameList.size() == 2) {
				nextRowList = getAdjacentNextRows((String) nameList.get(1),
						panel);
			}
			for (int i = 0; i < previousRowList.size(); i++) {
				ArrayList dummyList = (ArrayList) previousRowList.get(i);
				for (int j = 0; j < dummyList.size(); j++) {
					JLabel label = (JLabel) dummyList.get(j);
					if (!label.equals(label1) && !label.equals(label2)
							&& !label.equals(label3) && !label.equals(label4)
							&& label.getIcon() != null
							&& label.getIcon().equals(commonIcon)) {
						filteredList.add(label);
					}
				}
			}
			for (int i = 0; i < currentRowList.size(); i++) {
				ArrayList dummyList = (ArrayList) currentRowList.get(i);
				for (int j = 0; j < dummyList.size(); j++) {
					JLabel label = (JLabel) dummyList.get(j);
					if (!label.equals(label1) && !label.equals(label2)
							&& !label.equals(label3) && !label.equals(label4)
							&& label.getIcon() != null
							&& label.getIcon().equals(commonIcon)) {
						filteredList.add(label);
						rowList.add(label);
					}
				}
			}
			for (int i = 0; i < nextRowList.size(); i++) {
				ArrayList dummyList = (ArrayList) nextRowList.get(i);
				for (int j = 0; j < dummyList.size(); j++) {
					JLabel label = (JLabel) dummyList.get(j);
					if (!label.equals(label1) && !label.equals(label2)
							&& !label.equals(label3) && !label.equals(label4)
							&& label.getIcon() != null
							&& label.getIcon().equals(commonIcon)) {
						filteredList.add(label);
					}
				}
			}
			for (int i = 0; i < rowList.size(); i++) {
				JLabel rowLabel = (JLabel) rowList.get(i);
				for (int j = 0; j < labelList.size(); j++) {
					JLabel label = (JLabel) labelList.get(j);
					if (checkForRelationShips(label, rowLabel, panel)) {
						chunkList.add(rowLabel);
						labelList.add(rowLabel);
					}
				}
			}
			// if (chunkList.size() != 0) {
			for (int i = 0; i < filteredList.size(); i++) {
				JLabel labelToCheck = (JLabel) filteredList.get(i);
				for (int j = 0; j < labelList.size(); j++) {
					JLabel existingLabel = (JLabel) labelList.get(j);
					if (!chunkList.contains(labelToCheck)) {
						if (checkForRelationShips(existingLabel, labelToCheck,
								panel)) {
							chunkList.add(labelToCheck);
							labelList.add(labelToCheck);
						}
					}
				}
			}
			// }
		} catch (Exception ex) {

		}
		return chunkList;
	}

	/**
	 * Method which detects for more than 4 colors
	 * 
	 * @param labelList
	 * @param panel
	 * @param dummy
	 * @return
	 */
	public static JLabel checkForMorePuyos(ArrayList labelList, JPanel panel,
			ImageIcon commonIcon) {
		JLabel label5 = null;
		try {
			JLabel label1 = (JLabel) labelList.get(0);
			JLabel label4 = (JLabel) labelList.get(3);
			if (!(label1.getName().subSequence(0, 1)).equals("A")) {
				label5 = getPrevColumnElement(label1.getName(), panel);
				if (label5 != null && label5.getIcon() != null
						&& label5.getIcon().equals(commonIcon)) {
					return label5;
				}
			}
			if (!(label4.getName().subSequence(0, 1)).equals("L")) {
				label5 = getNextColumnElement(label4, panel);
				if (label5 != null && label5.getIcon() != null
						&& label5.getIcon().equals(commonIcon)) {
					return label5;
				}
			}
		} catch (Exception ex) {

		}
		return label5;
	}

	/**
	 * Method which checks for existance of relationship among similar labels.
	 * 
	 * @param existingLabel
	 * @param labelToCheck
	 * @param panel
	 * @return
	 */
	private static boolean checkForRelationShips(JLabel existingLabel,
			JLabel labelToCheck, JPanel panel) {
		/**
		 * This method checks each and every label which has same icon with the
		 * initial set of 4 icons which are to be cleared. If they form a
		 * legitimate chain, they are cleared off.
		 */
		boolean yes = false;
		JLabel nextRowLabel = null;
		JLabel nextColumnLabel = null;
		JLabel prevRowLabel = null;
		JLabel prevColLabel = null;
		String rowName = existingLabel.getName().substring(0, 1);
		String colName = existingLabel.getName().substring(1, 2);
		if (rowName.equalsIgnoreCase("A") && colName.equalsIgnoreCase("0")) {
			nextRowLabel = getNextRowElement(panel, existingLabel.getName());
			nextColumnLabel = getNextColumnElement(existingLabel, panel);
			if (nextRowLabel != null && nextRowLabel.equals(labelToCheck)) {
				yes = true;
				return yes;
			} else if (nextColumnLabel != null
					&& nextColumnLabel.equals(labelToCheck)) {
				yes = true;
				return yes;
			}
		} else if (rowName.equalsIgnoreCase("A")
				&& colName.equalsIgnoreCase("5")) {
			prevRowLabel = getPrevRowElement(panel, existingLabel.getName());
			nextColumnLabel = getNextColumnElement(existingLabel, panel);
			if (prevRowLabel != null && prevRowLabel.equals(labelToCheck)) {
				yes = true;
				return yes;
			} else if (nextColumnLabel != null
					&& nextColumnLabel.equals(labelToCheck)) {
				yes = true;
				return yes;
			}
		} else if (rowName.equalsIgnoreCase("L")
				&& colName.equalsIgnoreCase("0")) {
			nextRowLabel = getNextRowElement(panel, existingLabel.getName());
			prevColLabel = getPrevColumnElement(existingLabel.getName(), panel);
			if (nextRowLabel != null && nextRowLabel.equals(labelToCheck)) {
				yes = true;
				return yes;
			} else if (prevColLabel != null
					&& prevColLabel.equals(labelToCheck)) {
				yes = true;
				return yes;
			}
		} else if (rowName.equalsIgnoreCase("L")
				&& colName.equalsIgnoreCase("5")) {
			prevRowLabel = getPrevRowElement(panel, existingLabel.getName());
			prevColLabel = getPrevColumnElement(existingLabel.getName(), panel);
			if (prevRowLabel != null && prevRowLabel.equals(labelToCheck)) {
				yes = true;
				return yes;
			} else if (prevColLabel != null
					&& prevColLabel.equals(labelToCheck)) {
				yes = true;
				return yes;
			}
		} else if (rowName.equalsIgnoreCase("A")) {
			nextRowLabel = getNextRowElement(panel, existingLabel.getName());
			nextColumnLabel = getNextColumnElement(existingLabel, panel);
			prevRowLabel = getPrevRowElement(panel, existingLabel.getName());
			if (nextRowLabel != null && nextRowLabel.equals(labelToCheck)) {
				yes = true;
				return yes;
			} else if (nextColumnLabel != null
					&& nextColumnLabel.equals(labelToCheck)) {
				yes = true;
				return yes;
			} else if (prevRowLabel != null
					&& prevRowLabel.equals(labelToCheck)) {
				yes = true;
				return yes;
			}
		} else if (rowName.equalsIgnoreCase("L")) {
			nextRowLabel = getNextRowElement(panel, existingLabel.getName());
			prevColLabel = getPrevColumnElement(existingLabel.getName(), panel);
			prevRowLabel = getPrevRowElement(panel, existingLabel.getName());
			if (nextRowLabel != null && nextRowLabel.equals(labelToCheck)) {
				yes = true;
				return yes;
			} else if (prevColLabel != null
					&& prevColLabel.equals(labelToCheck)) {
				yes = true;
				return yes;
			} else if (prevRowLabel != null
					&& prevRowLabel.equals(labelToCheck)) {
				yes = true;
				return yes;
			}
		} else if (rowName.equalsIgnoreCase("0")) {
			nextRowLabel = getNextRowElement(panel, existingLabel.getName());
			nextColumnLabel = getNextColumnElement(existingLabel, panel);
			prevColLabel = getPrevColumnElement(existingLabel.getName(), panel);
			if (nextRowLabel != null && nextRowLabel.equals(labelToCheck)) {
				yes = true;
				return yes;
			} else if (nextColumnLabel != null
					&& nextColumnLabel.equals(labelToCheck)) {
				yes = true;
				return yes;
			} else if (prevColLabel != null
					&& prevColLabel.equals(labelToCheck)) {
				yes = true;
				return yes;
			}
		} else if (rowName.equalsIgnoreCase("5")) {
			prevRowLabel = getPrevRowElement(panel, existingLabel.getName());
			nextColumnLabel = getNextColumnElement(existingLabel, panel);
			prevColLabel = getPrevColumnElement(existingLabel.getName(), panel);
			if (prevRowLabel != null && prevRowLabel.equals(labelToCheck)) {
				yes = true;
				return yes;
			} else if (nextColumnLabel != null
					&& nextColumnLabel.equals(labelToCheck)) {
				yes = true;
				return yes;
			} else if (prevColLabel != null
					&& prevColLabel.equals(labelToCheck)) {
				yes = true;
				return yes;
			}
		} else {
			prevRowLabel = getPrevRowElement(panel, existingLabel.getName());
			nextColumnLabel = getNextColumnElement(existingLabel, panel);
			prevColLabel = getPrevColumnElement(existingLabel.getName(), panel);
			nextRowLabel = getNextRowElement(panel, existingLabel.getName());
			if (prevRowLabel != null && prevRowLabel.equals(labelToCheck)) {
				yes = true;
				return yes;
			} else if (nextColumnLabel != null
					&& nextColumnLabel.equals(labelToCheck)) {
				yes = true;
				return yes;
			} else if (prevColLabel != null
					&& prevColLabel.equals(labelToCheck)) {
				yes = true;
				return yes;
			} else if (nextRowLabel != null
					&& nextRowLabel.equals(labelToCheck)) {
				yes = true;
				return yes;
			}
		}
		return yes;
	}

	/**
	 * This method calculates and returns the bottommost label in the column
	 * (Label without icon). Method added as a requirement update.
	 * 
	 * @param label
	 * @param panel
	 * @return
	 */
	public static JLabel bottommostColumnElement(ArrayList coordinateList) {
		JLabel toReturn = null;
		for (int i = (coordinateList.size() - 1); i >= 0; i--) {
			toReturn = (JLabel) coordinateList.get(i);
			if (toReturn.getIcon() == null) {
				break;
			}
		}
		return toReturn;
	}

	/**
	 * This method returns all the puyos with icons above the given label.
	 * Method added as a requirement update.
	 * 
	 * @param label
	 * @param panel
	 * @return
	 */
	public static ArrayList liveElementsAboveTheLabelInTheColumn(
			ArrayList coordinateList, JLabel label) {
		ArrayList toReturnList = new ArrayList();
		int index = 0;
		for (int i = (coordinateList.size() - 1); i >= 0; i--) {
			JLabel dummy = (JLabel) coordinateList.get(i);
			if (dummy.getName().equalsIgnoreCase(label.getName())) {
				index = i;
				break;
			}
		}
		for (int i = index; i >= 0; i--) {
			JLabel dummylabel = (JLabel) coordinateList.get(i);
			if (dummylabel.getIcon() != null) {
				toReturnList.add(dummylabel);
			}
		}
		return toReturnList;
	}

	/**
	 * Method that calculates the bottom during 0-90 degree rotation of puyos.
	 * 
	 * @param label1
	 * @param label2
	 * @param panel
	 * @return
	 */
	public static ArrayList calculateBottomOfNinetyPuyo(JLabel label1,
			JLabel label2, JPanel panel) {
		ArrayList returnList = new ArrayList();
		int coordinate = Integer.parseInt(label2.getName().substring(1, 2));
		ArrayList elementList = getElementsOfCoordinate(panel, coordinate);
		LOOP: for (int i = (elementList.size() - 1); i >= 0; i--) {
			JLabel label = (JLabel) elementList.get(i);
			if (label.getIcon() == null) {
				returnList.add(label.getName());
				returnList.add((getPrevColumnElement(label.getName(), panel))
						.getName());
				break;
			} else {
				continue LOOP;
			}
		}
		return returnList;
	}

	/**
	 * Method that calculates the bottom during 90-180 degree rotation of puyos.
	 * 
	 * @param label1
	 * @param label2
	 * @param panel
	 * @return
	 */
	public static ArrayList calculateBottomOfOneEightyPuyo(JLabel label1,
			JLabel label2, JPanel panel) {
		ArrayList returnList = new ArrayList();
		int secondCoordinate = Integer.parseInt(label2.getName()
				.substring(1, 2));
		int firstCoordinate = 0;
		if (secondCoordinate != (PuyoUtilities.COLUMNS - 1)) {
			JLabel label = getNextRowElement(panel, label2.getName());
			firstCoordinate = Integer.parseInt(label.getName().substring(1, 2));
			ArrayList firstSet = getElementsOfCoordinate(panel, firstCoordinate);
			LOOP: for (int i = (firstSet.size() - 1); i >= 0; i--) {
				JLabel dummylabel = (JLabel) firstSet.get(i);
				if (dummylabel.getIcon() == null) {
					returnList.add(dummylabel.getName());
					break;
				} else {
					continue LOOP;
				}
			}
			firstSet = getElementsOfCoordinate(panel, secondCoordinate);
			LOOP: for (int i = (firstSet.size() - 1); i >= 0; i--) {
				JLabel dummylabel = (JLabel) firstSet.get(i);
				if (dummylabel.getIcon() == null) {
					returnList.add(dummylabel.getName());
					break;
				} else {
					continue LOOP;
				}
			}
		}
		return returnList;
	}

	/**
	 * Method that calculates the bottom during 180-270 degree rotation of
	 * puyos.
	 * 
	 * @param label1
	 * @param label2
	 * @param panel
	 * @return
	 */
	public static ArrayList calculateBottomOfTwoSeventyPuyo(JLabel label1,
			JLabel label2, JPanel panel) {
		ArrayList returnList = new ArrayList();
		int coordinate = Integer.parseInt(label2.getName().substring(1, 2));
		ArrayList elementList = getElementsOfCoordinate(panel, coordinate);
		LOOP: for (int i = (elementList.size() - 1); i >= 0; i--) {
			JLabel label = (JLabel) elementList.get(i);
			if (label.getIcon() == null) {
				returnList.add(label.getName());
				returnList.add((getPrevColumnElement(label.getName(), panel))
						.getName());
				break;
			} else {
				continue LOOP;
			}
		}
		return returnList;
	}

	/**
	 * Method that calculates the bottom during 270-360 degree rotation of
	 * puyos.
	 * 
	 * @param label1
	 * @param label2
	 * @param panel
	 * @return
	 */
	public static ArrayList calculateBottomOfThreeSixtyPuyo(JLabel label1,
			JLabel label2, JPanel panel) {
		ArrayList returnList = new ArrayList();
		int secondCoordinate = Integer.parseInt(label2.getName()
				.substring(1, 2));
		int firstCoordinate = 0;
		if (secondCoordinate != 0) {
			JLabel label = getPrevRowElement(panel, label2.getName());
			firstCoordinate = Integer.parseInt(label.getName().substring(1, 2));
			ArrayList firstSet = getElementsOfCoordinate(panel, firstCoordinate);
			LOOP: for (int i = (firstSet.size() - 1); i >= 0; i--) {
				JLabel dummylabel = (JLabel) firstSet.get(i);
				if (dummylabel.getIcon() == null) {
					returnList.add(dummylabel.getName());
					break;
				} else {
					continue LOOP;
				}
			}
			firstSet = getElementsOfCoordinate(panel, secondCoordinate);
			LOOP: for (int i = (firstSet.size() - 1); i >= 0; i--) {
				JLabel dummylabel = (JLabel) firstSet.get(i);
				if (dummylabel.getIcon() == null) {
					returnList.add(dummylabel.getName());
					break;
				} else {
					continue LOOP;
				}
			}
		}
		return returnList;
	}
}
