package chapter7.comparator;

import java.util.*;

public class EmpComparator implements Comparator<Employee> {
	public int compare(Employee emp1, Employee emp2) {
		return (emp1.getName().compareTo(emp2.getName()));
	}
}
