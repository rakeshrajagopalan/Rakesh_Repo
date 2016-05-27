package rovermoverpack;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This is a self-contained class used to collect various inputs from the user
 * and verify them on the fly. It offers two public accessor methods, one
 * returning the GridSize entered by the user and another returning a collection
 * of commands for the rovers.
 * 
 * @author Rakesh
 * 
 */
public class InputVerifier {

	private String gridDimenion;

	private String initCoordinates;

	private String command;

	private List<List<String>> commandDetails;

	/**
	 * Constructs a InputVerifier object.
	 * 
	 * @throws Exception
	 */
	public InputVerifier() throws Exception {

	}

	/**
	 * This method validates the grid co-ordinates entered by the user. This
	 * method comprehensively verifies whether the input entered by the user is
	 * legal. This method first tokenizes the input string using space as
	 * delimiter. Then it checks whether the user has entered too few or too
	 * many arguments than needed. Then, using the regular expressions, it
	 * checks whether any character(apart from the allowed character set(space,
	 * 0-9 or N,E,W,S,M,L,R) is entered by the user. If all these scenarios
	 * passes, the method call returns successfully. Else, it calls its caller
	 * once again recursively until the user enters the correct input.
	 * 
	 * @param gridInput
	 * @return
	 * @throws Exception
	 */
	public boolean validateGridSizeInput(String gridInput, String regex)
			throws Exception {
		boolean validated = false;
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(gridInput);
		if (matcher.find()) {
			validated = true;
		}
		return validated;
	}

	/**
	 * This method validates the initial co-ordinates entered by the user. This
	 * method comprehensively verifies whether the input entered by the user is
	 * legal. This method first tokenizes the input string using space as
	 * delimiter. Then it checks whether the user has entered too few or too
	 * many arguments than needed. Then, using the regular expressions, it
	 * checks whether any character(apart from the allowed character set(space,
	 * 0-9 or N,E,W,S,M,L,R) is entered by the user. If all these scenarios
	 * passes, the method call returns successfully. Else, it calls its caller
	 * once again recursively until the user enters the correct input.
	 * 
	 * @param gridInput
	 * @return
	 * @throws Exception
	 */
	public boolean validateInitialCoordinates(String message, String regex)
			throws Exception {
		boolean validated = false;
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(message);
		if (matcher.find()) {
			validated = true;
		}
		return validated;
	}

	/**
	 * This method validates the command entered by the user. This
	 * method comprehensively verifies whether the input entered by the user is
	 * legal. This method first tokenizes the input string using space as
	 * delimiter. Then it checks whether the user has entered too few or too
	 * many arguments than needed. Then, using the regular expressions, it
	 * checks whether any character(apart from the allowed character set(space,
	 * 0-9 or N,E,W,S,M,L,R) is entered by the user. If all these scenarios
	 * passes, the method call returns successfully. Else, it calls its caller
	 * once again recursively until the user enters the correct input.
	 * 
	 * @param gridInput
	 * @return
	 * @throws Exception
	 */
	public boolean validateCommand(String message, String regex)
			throws Exception {
		boolean validated = false;
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(message);
		if (!matcher.find()) {
			validated = true;
		}
		return validated;
	}

	/**
	 * Method to check whether the userwants to enter command for more Rovers or not.
	 * @param message
	 * @return
	 * @throws Exception
	 */
	public boolean moreRovers(String message) throws Exception {
		boolean moreRovers = false;
		if (message.contains("Y")) {
			moreRovers = true;
		}
		return moreRovers;
	}

	/**
	 * This is a getter method which the CommandProcessor class will use to
	 * obtain the Command input.
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<List<String>> getCommandList() throws Exception {
		return this.commandDetails;
	}

	/**
	 * Setter method for Grid Dimension.
	 * @param gridDimenion
	 */
	public void setGridDimenion(String gridDimenion) throws Exception  {
		this.gridDimenion = gridDimenion;
	}

	/**
	 * Getter method for initial co-ordinates.
	 * @return
	 */
	public String getInitCoordinates() throws Exception  {
		return initCoordinates;
	}

	/**
	 * Setter method for initial co-ordinates.
	 * @return
	 */
	public void setInitCoordinates(String initCoordinates) throws Exception  {
		this.initCoordinates = initCoordinates;
	}

	/**
	 * Getter method for command.
	 * @return
	 */
	public String getCommand() throws Exception  {
		return command;
	}

	/**
	 * Setter method for command.
	 * @return
	 */
	public void setCommand(String command) throws Exception  {
		this.command = command;
	}
	
	/**
	 * Getter method for grid co-ordinates.
	 * @return
	 */
	public String getGridDimenion() throws Exception {
		return gridDimenion;
	}

}
