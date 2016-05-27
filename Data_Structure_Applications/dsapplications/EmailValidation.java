package dsapplications;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author Rakesh
 * 
 */

public class EmailValidation {
	public static void main(String[] args) {
		boolean valid = false;
		String input = "rakesh.r@siemens.com";
		String regex = "([\\w|\\.|-]+)@([\\w&&[^_]]+)\\.[c|o|m|r|g|v]{2,3}([\\.a-z]{0,3})$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		valid = matcher.matches();
		// or
		// valid = Pattern.matches(regex,input);
		if (!valid) {
			System.out.println("The e-mail ID is invalid");
		} else {
			System.out.println("The e-mail ID is valid");
		}
	}
}
