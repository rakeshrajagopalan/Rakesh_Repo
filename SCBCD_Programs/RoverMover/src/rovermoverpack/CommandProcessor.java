package rovermoverpack;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to process the command entered by the user. The input to
 * this class contains the grid size, input co-ordinates and command for Rovers.
 * The class processes the command using the processCommand method.
 * 
 * @author Rakesh
 * 
 */
public class CommandProcessor {

	private int xMin = 0;

	private int yMin = 0;

	private int xMax;

	private int yMax;

	private int xCurrent;

	private int yCurrent;

	private DirectionManipulator currentDirectionConstant;

	private List<List<String>> commandList;

	private List<List<String>> outputList;

	private enum CommandConstant {
		LEFT() {
			public String toString() {
				return "L";
			}
		},
		RIGHT() {
			public String toString() {
				return "R";
			}
		},
		MOVE() {
			public String toString() {
				return "M";
			}
		}
	}

	/**
	 * This is the constructor of the CommandProcessor object.
	 * 
	 * @param gridSize
	 * @param commandList
	 * @throws Exception
	 */
	public CommandProcessor(String gridSize, List<List<String>> commandList)
			throws Exception {
		outputList = new ArrayList<List<String>>();
		this.commandList = commandList;
		String[] tokens = gridSize.split(" ");
		xMax = Integer.parseInt(tokens[0]);
		yMax = Integer.parseInt(tokens[1]);
	}

	/**
	 * This is the accessor method which returns the caller a list of processed
	 * commands.
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<List<String>> processCommands() throws Exception {
		int tempCount = 1;
		for (List<String> internalList : commandList) {
			String initialCoordinates = internalList.get(0);
			String command = internalList.get(1);
			process(initialCoordinates, command);
			List<String> details = new ArrayList<String>();
			details.add("Details of Rover " + tempCount);
			details.add("-------------------------------");
			details.add("InitialCoordinates: " + initialCoordinates);
			details.add("Command issued: " + command);
			details.add("Output: " + xCurrent + " " + yCurrent + " "
					+ currentDirectionConstant);
			outputList.add(details);
			tempCount++;
		}
		return outputList;
	}

	/**
	 * This method is the heart of the class. This takes two input parameters,
	 * initial co-ordinates and command. Then, it iterates through the command
	 * String, and extracts character by character. Based on the character that
	 * comes out, it chooses flip or move method.
	 * 
	 * @param initialCoordinates
	 * @param command
	 * @throws Exception
	 */
	private void process(String initialCoordinates, String command)
			throws Exception {
		String[] tokens = initialCoordinates.split(" ");
		xCurrent = Integer.parseInt(tokens[0]);
		yCurrent = Integer.parseInt(tokens[1]);
		String dir = tokens[2];
		if (dir.equals(DirectionManipulator.EAST.toString())) {
			currentDirectionConstant = DirectionManipulator.EAST;
		} else if (dir.equals(DirectionManipulator.WEST.toString())) {
			currentDirectionConstant = DirectionManipulator.WEST;
		} else if (dir.equals(DirectionManipulator.NORTH.toString())) {
			currentDirectionConstant = DirectionManipulator.NORTH;
		} else {
			currentDirectionConstant = DirectionManipulator.SOUTH;
		}
		for (int i = 0; i < command.length(); i++) {
			String nextToken = command.substring(i, i + 1);
			if (nextToken.equals(CommandConstant.LEFT.toString())) {
				flipAnticlockwise();
			} else if (nextToken.equals(CommandConstant.RIGHT.toString())) {
				flipClockwise();
			} else {
				if (currentDirectionConstant.equals(DirectionManipulator.EAST)
						|| currentDirectionConstant
								.equals(DirectionManipulator.WEST)) {
					moveInXAxis();
				} else {
					moveInYAxis();
				}
			}
		}
	}

	/**
	 * This method is called for the 'R' command entered by the user. If the
	 * current direction is North and the user has entered 'R', then this method
	 * flips it 90 degrees clock-wise. Hence, the direction of the Rover after
	 * this method-call returns will have been changed from North to East.
	 * 
	 * @throws Exception
	 */
	private void flipClockwise() throws Exception {
		currentDirectionConstant = currentDirectionConstant
				.getNextClockwise(currentDirectionConstant);
	}

	/**
	 * This method is called for the 'L' command entered by the user. If the
	 * current direction is North and the user has entered 'L', then this method
	 * flips it 90 degrees anti clock-wise. Hence, the direction of the Rover
	 * after this method-call returns will have been changed from North to West.
	 * 
	 * @throws Exception
	 */
	private void flipAnticlockwise() throws Exception {
		currentDirectionConstant = currentDirectionConstant
				.getNextAntiClockwise(currentDirectionConstant);
	}

	/**
	 * This method is called whenever a Move command is issued by the user and
	 * the Rover is facing either Eastwards or Westwards.This method advances
	 * the Rover by 1 grid position in x-axis. If the current direction of Rover
	 * is West and the Rover is sitting at x=2, then this method moves the Rover
	 * by 1 position Westwards. Therefore the current x co-ordinate of the Rover
	 * will have been changed to 1 when this method returns. Now, if the Rover
	 * is facing Eastwards and is itting at x=2, then this method moves the
	 * Rover by 1 position Eastwards. Therefore the current x co-ordinate of the
	 * Rover will have been changed to 3 when this method returns. Note: When a
	 * Move command is issued when a Rover is sitting at x=0 and is facing
	 * Westwards, this method simply ignores that Move Command. Same is true
	 * when the Rover is sitting at x=MAX_X and is facing Eastwards.
	 * 
	 * @throws Exception
	 */
	private void moveInXAxis() throws Exception {
		if (!((xCurrent == xMin && currentDirectionConstant
				.equals(DirectionManipulator.WEST)) || (xCurrent == xMax && currentDirectionConstant
				.equals(DirectionManipulator.EAST)))) {
			if (currentDirectionConstant.equals(DirectionManipulator.EAST)) {
				xCurrent += 1;
			} else if (currentDirectionConstant
					.equals(DirectionManipulator.WEST)) {
				xCurrent -= 1;
			}
		}
	}

	/**
	 * This method is called whenever a Move command is issued by the user and
	 * the Rover is facing either Northwards or Southwards.This method advances
	 * the Rover by 1 grid position in y-axis. If the current direction of Rover
	 * is South and the Rover is sitting at y=2, then this method moves the
	 * Rover by 1 position Southwards. Therefore the current y co-ordinate of
	 * the Rover will have been changed to 1 when this method returns. Now, if
	 * the Rover is facing Northwards and is itting at y=2, then this method
	 * moves the Rover by 1 position Northwards. Therefore the current y
	 * co-ordinate of the Rover will have been changed to 3 when this method
	 * returns. Note: When a Move command is issued when a Rover is sitting at
	 * y=0 and is facing Southwards, this method simply ignores that Move
	 * Command. Same is true when the Rover is sitting at y=MAX_Y and is facing
	 * Northwards.
	 * 
	 * @throws Exception
	 */
	private void moveInYAxis() throws Exception {
		if (!((yCurrent == yMin && currentDirectionConstant
				.equals(DirectionManipulator.SOUTH)) || (yCurrent == yMax && currentDirectionConstant
				.equals(DirectionManipulator.NORTH)))) {
			if (currentDirectionConstant.equals(DirectionManipulator.NORTH)) {
				yCurrent += 1;
			} else if (currentDirectionConstant
					.equals(DirectionManipulator.SOUTH)) {
				yCurrent -= 1;
			}
		}
	}

}
