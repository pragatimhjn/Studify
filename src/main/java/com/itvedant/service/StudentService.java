package com.itvedant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itvedant.model.Student;
import com.itvedant.model.StudentWithoutEmail;
import com.itvedant.repository.StudentRepository;
/*  ----before wrapper 
@Service
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;
		
	public Iterable<Student> getAll(){
		Student s = new Student();
//		s.setName("mike");
//		s.setEmail("mike@gmail.com");
//		s.setAggregate(70.90);
//		this.studentRepository.save(s);
		return this.studentRepository.findAll();
	}


	public Student getById(Integer id) {
		return this.studentRepository.findById(id).orElse(null);
	}


	public Student addStudent(Student student) {
		return this.studentRepository.save(student);
	}


	public Student updateStudent(Integer id, Student student) {
		Student findStudent = this.getById(id);
		if(findStudent != null) {
			student.setId(id);
			return this.studentRepository.save(student);
		}
		else {
			return null;
		}
	}
public String deleteStudent(Integer id) {
		Student findStudent = this.getById(id);
		if(findStudent != null) {
			this.studentRepository.deleteById(id);
			return "Student Deleted";
		}
		else {
			return "Student Does Not Exists";
		}
	}}
*/

@Service
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;
	/*
	public Iterable<Student> getAll(){
		return this.studentRepository.findAll();
	}
	 after projection
	 */

	public List<StudentWithoutEmail> getAll(){
		return this.studentRepository.findAllProjectedBy();
	}
	
	public StudentWithoutEmail getByIdProjected(Integer id) {
		return this.studentRepository.findProjectedById(id).orElse(null);
	}
	
public Student getById(Integer id) {
		return this.studentRepository.findById(id).orElse(null);
	}

public List<StudentWithoutEmail> getByName(String name) {
	return this.studentRepository.findByName(name);
}
public List<StudentWithoutEmail> getByScore(Double score) {
	return this.studentRepository.findByAggregateGreaterThan(score);
}

public List<Student> getByEmail(String pattern){
	return this.studentRepository.findByEmailContaining(pattern);
}
public List<Student> getByEmails(String pattern1, String pattern2) {
	return this.studentRepository.findByEmailContainingOrEmailContaining(pattern1, pattern2);
}

public List<StudentWithoutEmail> getByRange(Double low, Double high) {
	return this.studentRepository.findByAggregateBetween(low,high);
}

public List<StudentWithoutEmail> getAllOrdered(){
	return this.studentRepository.findAllOrderByName();
}

public List<Object[]> studentCountByName(){
	return this.studentRepository.findSameNameStudent();
}




	public Student addStudent(Student student) {
		return this.studentRepository.save(student);
	}


	public Student updateStudent(Integer id, Student student) {
			student.setId(id);
			return this.studentRepository.save(student);
	}
public String deleteStudent(Integer id) {
			this.studentRepository.deleteById(id);
			return "Student Deleted";
	}
}