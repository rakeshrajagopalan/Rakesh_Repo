package trickyquestions;

public class Rectangle {

	private final int height;

	private final int width;

	public Rectangle(int width, int height) {
		this.width = width;
		this.height = height;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		throw new UnmodifiableException();
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		throw new UnmodifiableException();
	}

	@Override
	public boolean equals(Object o) {
		boolean returnVal = false;
		if (o instanceof Rectangle && ((Rectangle) o).width == width
				&& ((Rectangle) o).height == height) {
			returnVal = true;
		}
		return returnVal;
	}

	@Override
	public int hashCode() {
		return (height * width) >>> 22;
	}

	public static void main(String[] args) {
		Rectangle r = new Rectangle(1329870, 123470);
		System.out.println(r.hashCode());
	}
}

class UnmodifiableException extends RuntimeException {

}