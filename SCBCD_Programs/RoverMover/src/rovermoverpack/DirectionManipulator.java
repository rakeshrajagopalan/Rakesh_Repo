package rovermoverpack;

/**
 * This is an utility enum to easily obtain the next direction, when a flipping
 * takes place either clockwise, or anti-clockwise.
 * 
 * @author Rakesh
 * 
 */
public enum DirectionManipulator {

	NORTH() {
		public String toString() {
			return "N";
		}
	},
	EAST() {
		public String toString() {
			return "E";
		}
	},
	WEST() {
		public String toString() {
			return "W";
		}
	},
	SOUTH() {
		public String toString() {
			return "S";
		}
	};

	private DirectionManipulator nextDir;

	/**
	 * This is an utility method to obtain the next direction, when a flipping
	 * takes place clockwise. If the current direction of the Rover is North and
	 * the user wants to turn it 90 degrees clock-wise, then this method is
	 * called and it turns the Rover to East direction.
	 * 
	 * @param currentDir
	 * @return
	 * @throws Exception
	 */
	public DirectionManipulator getNextClockwise(DirectionManipulator currentDir)
			throws Exception {
		if (currentDir.equals(NORTH)) {
			nextDir = EAST;
		} else if (currentDir.equals(EAST)) {
			nextDir = SOUTH;
		} else if (currentDir.equals(SOUTH)) {
			nextDir = WEST;
		} else {
			nextDir = NORTH;
		}
		return nextDir;
	}

	/**
	 * This is an utility method to obtain the next direction, when a flipping
	 * takes place anti clock-wise.If the current direction of the Rover is
	 * North and the user wants to turn it 90 degrees anti clock-wise, then this
	 * method is called and it turns the Rover to West direction.
	 * 
	 * @param currentDir
	 * @return
	 * @throws Exception
	 */
	public DirectionManipulator getNextAntiClockwise(
			DirectionManipulator currentDir) throws Exception {
		if (currentDir.equals(NORTH)) {
			nextDir = WEST;
		} else if (currentDir.equals(WEST)) {
			nextDir = SOUTH;
		} else if (currentDir.equals(SOUTH)) {
			nextDir = EAST;
		} else {
			nextDir = NORTH;
		}
		return nextDir;
	}

}
