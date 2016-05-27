package chapter3;

public class InitBlocks {
	static {
		System.out.println("Static Init");
	}
	
	public InitBlocks() {
		super();
		System.out.println("Inside Constructor");
	}
	
	{System.out.println("Instance Init 1");}
	
	public static void main(String[] args) {
		System.out.println("Main");
		new InitBlocks();
		System.out.println("Object construction successful");
	}
	
	{System.out.println("Instance Init 2");}
}
