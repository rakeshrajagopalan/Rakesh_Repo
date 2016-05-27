package com.springmvc.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.springmvc.config.SpringMongoConfig;
import com.springmvc.data.Student;

@Import(SpringMongoConfig.class)
@Repository
public class StudentDAOImpl implements StudentDAO {
	
	@Autowired
	MongoTemplate mongoTemplate;

	public void saveStudent(Student student) {
		mongoTemplate.save(student);
	}

	public List<Student> getStudents() {
		return mongoTemplate.findAll(Student.class);
	}

	public Student getStudent(Student student) {
		Query searchUserQuery = new Query(Criteria.where("name").is(student.getName()));
		return mongoTemplate.findOne(searchUserQuery, Student.class);
	}

	public void updateStudent(Student student, String valueToUpdate) {
		Query searchUserQuery = new Query(Criteria.where("name").is(student.getName()));
		mongoTemplate.updateFirst(searchUserQuery, Update.update("name", valueToUpdate), Student.class);
	}

	public void removeStudent(Student student) {
		mongoTemplate.remove(student);
	}

}
