package trickyquestions;

import java.util.*;

public class GenericsTest {
	public static <T> List<T> backwards(List<T> input) {
		// public static <T> List<T> backwards(List<? extends T> input) { //Will Compile
		// public static <T> List<T> backwards(List<? super T> input) { //Wont Compile
		// public static <T> List<? extends T> backwards(List<T> input) { //OK
		// public static <T> List<? super T> backwards(List<T> input) { //OK
		// public static <? extends T> List<T> backwards(List<T> input) { //Wrong Syntax
		// public static <? super T> List<T> backwards(List<T> input) { //Wrong Syntax

		List<T> output = new LinkedList<T>();
		for (T t : input) {
			output.add(t);
		}
		return output;
	}
}
