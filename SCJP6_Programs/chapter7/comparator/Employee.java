package chapter7.comparator;

public class Employee implements Comparable<Employee> {
	private String name;

	private String empID;

	public String getEmpID() {
		return empID;
	}

	public void setEmpID(String empID) {
		this.empID = empID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int compareTo(Employee employee) {
		return this.empID.compareTo(employee.getEmpID());
	}

}
