package com.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springmvc.dao.StudentDAO;
import com.springmvc.data.Student;

@Service
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	StudentDAO studentDao;

	public void saveStudent(Student student) {
		studentDao.saveStudent(student);
	}

	public List<Student> getStudents() {
		return studentDao.getStudents();
	}

	public Student getStudent(Student student) {
		return studentDao.getStudent(student);
	}

	public void updateStudent(Student student, String valueToUpdate) {
		studentDao.updateStudent(student, valueToUpdate);
	}

	public void removeStudent(Student student) {
		studentDao.removeStudent(student);
	}
	
}
