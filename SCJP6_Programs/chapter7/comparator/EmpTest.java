package chapter7.comparator;

import java.util.*;

public class EmpTest {
	public static void main(String[] args) {
		EmpTest empTest = new EmpTest();
		empTest.comparatorTest();
	}

	private void comparatorTest() {
		ArrayList<Employee> empList = new ArrayList<Employee>();
		Employee emp1 = new Employee();
		emp1.setEmpID("102");
		emp1.setName("Cat");
		empList.add(emp1);
		emp1 = new Employee();
		emp1.setEmpID("104");
		emp1.setName("Eagle");
		empList.add(emp1);
		emp1 = new Employee();
		emp1.setEmpID("100");
		emp1.setName("Apple");
		empList.add(emp1);
		emp1 = new Employee();
		emp1.setEmpID("101");
		emp1.setName("Dog");
		empList.add(emp1);
		emp1 = new Employee();
		emp1.setEmpID("103");
		emp1.setName("Boy");
		empList.add(emp1);
		System.out.println("Before Sorting: ");
		for (Employee emp : empList) {
			System.out.println(emp.getEmpID()+ " " + emp.getName());
		}
		Collections.sort(empList);
		System.out.println("\nAfter Sorting by ID: ");
		for (Employee emp : empList) {
			System.out.println(emp.getEmpID()+ " " + emp.getName());
		}
		Collections.sort(empList, new EmpComparator());
		System.out.println("\nAfter Sorting by name: ");
		for (Employee emp : empList) {
			System.out.println(emp.getEmpID()+ " " + emp.getName());
		}
	}
}
