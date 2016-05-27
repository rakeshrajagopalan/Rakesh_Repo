package com.java8.chap01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortLambda {
	public static void main(String[] args) {
		List<Employee> dataList = new ArrayList<>();
		dataList.add(new Employee(4, "Dog"));
		dataList.add(new Employee(1, "Apple"));
		dataList.add(new Employee(3, "Cat"));
		dataList.add(new Employee(2, "Boy"));
		Collections.sort(dataList, (e1,e2) -> e1.getEmpId().compareTo(e2.getEmpId()));
		System.out.println(dataList);
	}

	private static class Employee {

		private Integer empId;

		private String empName;

		private Employee(Integer empId, String empName) {
			this.empId = empId;
			this.empName = empName;
		}

		public Integer getEmpId() {
			return empId;
		}

		public String getEmpName() {
			return empName;
		}

	}

}