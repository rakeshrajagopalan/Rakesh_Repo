package rovermoverpack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * This is a class to send information to the imaginary Rover deployed on Mars.
 * The Rover robot is imagined to have landed in a rectangular terrain on Mars.
 * The robot owners will send the instructions from Earth to it as to how it has
 * to move about in the terrain. This class leverages the powerful API of
 * InputVerifer to obtain and verify the inputs entered by the user, and uses
 * the CommandProcessor to process the command.
 */
public class RoverMover {

	private static final String GRID_SIZE_REGEX = "(^\\d \\d\\z)";

	private static final String GRID_SIZE_MESSAGE = "Please enter the x & y"
			+ " coordiantes of the grid in the format x co-ordinate "
			+ "followed by space followed by y-co-ordinate (example:5 5):";

	private static final String INIT_COORDINATES_REGEX = "^(\\d \\d [NEWS])\\z";

	private static final String INIT_COORDINATES_MESSAGE = "Please enter the initial Rover position's x & y "
			+ "coordiantes of the grid followed by the direction in the format x co-ordinate "
			+ "followed by space followed by y-co-ordinate followed by space followed by "
			+ "direction character(N or E or W or S)(example:1 3 N):";

	private static final String COMMAND_REGEX = "[^LRM]";

	private static final String COMMAND_MESSAGE = "Please enter the command. Command can contain only direction constants(L or R) and move(M) symbol";

	private static final String MORE_ROVERS_MESSAGE = "Enter inputs for next Rover?(Y/N):";

	private enum UserInputStage {
		GRID_SIZE, INITIAL_POSITION, ROVER_COMMAND, MORE_ROVERS
	}

	private UserInputStage currentStage;

	private boolean allInputsCollected;

	private List<List<String>> commandList;

	/**
	 * This constructs a RoverMover object.
	 * 
	 * @throws Exception
	 */
	public RoverMover() throws Exception {
		commandList = new ArrayList<List<String>>();
		getAndVerifyInput();
	}

	/**
	 * This creates a InputVerifier object, whcih asks the user to enter various
	 * inputs, and then collects all the inputs in a coillection and returns it
	 * back to RoverMover through the getCommandList method.
	 * 
	 * @throws Exception
	 */
	private void getAndVerifyInput() throws Exception {
		InputVerifier verifier = new InputVerifier();
		currentStage = UserInputStage.GRID_SIZE;
		while (!allInputsCollected) {
			switch (currentStage) {
			case GRID_SIZE:
				System.out.println(GRID_SIZE_MESSAGE);
				BufferedReader inputReader = new BufferedReader(
						new InputStreamReader(System.in));
				String gridInput = inputReader.readLine();
				boolean b = verifier.validateGridSizeInput(gridInput,
						GRID_SIZE_REGEX);
				if (b) {
					currentStage = UserInputStage.INITIAL_POSITION;
					verifier.setGridDimenion(gridInput);
				} else {
					System.out.println(" ----------- ");
					System.out.println("|WRONG INPUT|");
					System.out.println(" ----------- ");
				}
				break;
			case INITIAL_POSITION:
				System.out.println(INIT_COORDINATES_MESSAGE);
				inputReader = new BufferedReader(new InputStreamReader(
						System.in));
				gridInput = inputReader.readLine();
				b = verifier.validateInitialCoordinates(gridInput,
						INIT_COORDINATES_REGEX);
				if (b) {
					currentStage = UserInputStage.ROVER_COMMAND;
					verifier.setInitCoordinates(gridInput);
				} else {
					System.out.println(" ----------- ");
					System.out.println("|WRONG INPUT|");
					System.out.println(" ----------- ");
				}
				break;
			case ROVER_COMMAND:
				System.out.println(COMMAND_MESSAGE);
				inputReader = new BufferedReader(new InputStreamReader(
						System.in));
				gridInput = inputReader.readLine();
				b = verifier.validateCommand(gridInput, COMMAND_REGEX);
				if (b) {
					currentStage = UserInputStage.MORE_ROVERS;
					verifier.setCommand(gridInput);
				} else {
					System.out.println(" ----------- ");
					System.out.println("|WRONG INPUT|");
					System.out.println(" ----------- ");
				}
				break;
			case MORE_ROVERS:
				System.out.println(MORE_ROVERS_MESSAGE);
				inputReader = new BufferedReader(new InputStreamReader(
						System.in));
				gridInput = inputReader.readLine();
				b = verifier.moreRovers(gridInput);
				if (b) {
					currentStage = UserInputStage.INITIAL_POSITION;
					List<String> temp = new ArrayList<String>();
					temp.add(verifier.getInitCoordinates());
					temp.add(verifier.getCommand());
					commandList.add(temp);
				} else {
					allInputsCollected = true;
					List<String> temp = new ArrayList<String>();
					temp.add(verifier.getInitCoordinates());
					temp.add(verifier.getCommand());
					commandList.add(temp);
				}
				break;
			}
		}
		String gridSize = verifier.getGridDimenion();
		processInputs(gridSize, commandList);
	}

	/**
	 * This method creates a CommandProcessor object, which does processing of
	 * the commands and returns the output back here.
	 * 
	 * @param gridSize
	 * @param commandList
	 * @throws Exception
	 */
	private void processInputs(String gridSize, List<List<String>> commandList)
			throws Exception {
		displayResults(new CommandProcessor(gridSize, commandList)
				.processCommands());
	}

	/**
	 * This method displays the processed output.
	 * 
	 * @param outputList
	 * @throws Exception
	 */
	private void displayResults(List<List<String>> outputList) throws Exception {
		System.out.println("The output is: ");
		for (List<String> intList : outputList) {
			for (String output : intList) {
				System.out.println(output);
			}
			System.out.println();
		}
	}

	/**
	 * This is the main method of the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			new RoverMover();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
