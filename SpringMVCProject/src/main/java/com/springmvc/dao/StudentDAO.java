package com.springmvc.dao;

import java.util.List;

import com.springmvc.data.Student;

public interface StudentDAO {
	
	public void saveStudent(Student student);
	
	public List<Student> getStudents();
	
	public Student getStudent(Student student);
	
	public void updateStudent(Student student, String valueToUpdate);
	
	public void removeStudent(Student student);
	
}
