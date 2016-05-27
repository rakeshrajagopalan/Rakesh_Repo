package chapter3;

class Animal{}
class Dog extends Animal{}

public class MultiDimenionalArray {
	public static void main(String[] args) {
		int[][][] i = {{{1,2},{35,3,4},{11}},{{33,44,55}}};
		System.out.println("[0][0][0]" +i[0][0][0]);
		System.out.println("[0][0][1]" +i[0][0][1]);
		System.out.println("[0][1][0]" +i[0][1][0]);
		System.out.println("[0][1][1]" +i[0][1][1]);
		System.out.println("[0][1][2]" +i[0][1][2]);
		System.out.println("[0][2][0]" +i[0][2][0]);
		System.out.println("[1][0][0]" +i[1][0][0]);
		System.out.println("[1][0][1]" +i[1][0][1]);
		System.out.println("[1][0][2]" +i[1][0][2]);
		Animal[] a = new Animal[3];
		Dog[] d = new Dog[5];
		a = d;
		d = (Dog[])a;
	}
}
