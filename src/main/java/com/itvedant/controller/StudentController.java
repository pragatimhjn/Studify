package com.itvedant.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itvedant.model.Student;
import com.itvedant.model.StudentWithoutEmail;
import com.itvedant.service.StudentService;
import com.itvedant.util.ResponseWrapper;

/*

@RestController
//@RequestMapping("/students") --> to remove redundant students from url part

// this is combination of response body and controller annotation
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
*/

@RestController
@RequestMapping("/students")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	private static ResponseWrapper wrapper;
	
	public StudentController() {
		wrapper = new ResponseWrapper();
	}

/*	
	//get all records
	@GetMapping("/students")
	public Iterable<Student> getAll(){
		return this.studentService.getAll();
	}
	
	//get one record with id
	@GetMapping("/students/{id}")
	public Student getById(@PathVariable Integer id) {
		return this.studentService.getById(id);
	}
	
	//create record with postman
	@PostMapping("/students")
	public Student addStudent(@RequestBody Student student) {
		return this.studentService.addStudent(student);
	}
	
	//update record, whole object needs to be passed
	@PutMapping("/students/{id}")
	public Student updateStudent(@PathVariable Integer id, @RequestBody Student student) {
		
		return this.studentService.updateStudent(id,student);
	}
	
	//delete record with postman api
	@DeleteMapping("/students/{id}")
	public String deleteStudent(@PathVariable Integer id) {
		return this.studentService.deleteStudent(id);
	}

/*
 * @PutMapping("/students/{id}")
	public Student updateStudent(@PathVariable Integer id,@RequestBody Student student) {
		return this.studentService.updateStudent(id, student);
	}
	
	@DeleteMapping("/students/{id}")
	public String deleteStudent(@PathVariable Integer id) {
		return this.studentService.deleteStudent(id);
	}

  */
	
	// following apis after adding common wrapped response
	
	@GetMapping("/")
	public ResponseEntity<?> getAll(){
		wrapper.setMessage("List of all the students");
		wrapper.setResponse(this.studentService.getAll());
//		return new ResponseEntity<>(wrapper, HttpStatus.OK);
		return new ResponseEntity<>(wrapper, HttpStatus.OK);

	}
@GetMapping("/{id}")
//	public ResponseEntity<?> getById(@PathVariable Integer id) {
//		Student found = this.studentService.getById(id);
public ResponseEntity<?> getById(@PathVariable Integer id) {
	StudentWithoutEmail found = this.studentService.getByIdProjected(id);
		if(found != null) {
			wrapper.setMessage("Details of the student with ID: " + id);
			wrapper.setResponse(found);
			return new ResponseEntity<>(wrapper,HttpStatus.OK);
		}
		else {
			wrapper.setMessage("Student with ID " + id + " not found");
			wrapper.setResponse(null);
			return new ResponseEntity<>(wrapper,HttpStatus.NOT_FOUND);
		}
	}
//find record by name

@GetMapping("/byname")
public ResponseEntity<?> getByName(@RequestParam String name){
	wrapper.setMessage("Students with the name: " + name);
	wrapper.setResponse(this.studentService.getByName(name));
	return new ResponseEntity<>(wrapper, HttpStatus.OK);
}

@GetMapping("/byscore")
public ResponseEntity<?> getByScore(@RequestParam Double score){
	wrapper.setMessage("Students who scored more than: " + score);
	wrapper.setResponse(this.studentService.getByScore(score));
	return new ResponseEntity<>(wrapper, HttpStatus.OK);
}
@GetMapping("/byemail")
public ResponseEntity<?> getByEmail(@RequestParam String pattern){
	wrapper.setMessage("Students email containing the pattern: " + pattern);
	wrapper.setResponse(this.studentService.getByEmail(pattern));
	return new ResponseEntity<>(wrapper, HttpStatus.OK);
}
@GetMapping("byemails")
public ResponseEntity<?> getByEmail(@RequestParam String pattern1,@RequestParam String pattern2){
	wrapper.setMessage("Students email containing the pattern: " + pattern1 + ", " + pattern2);
	wrapper.setResponse(this.studentService.getByEmails(pattern1, pattern2));
	return new ResponseEntity<>(wrapper, HttpStatus.OK);
}
@GetMapping("byrange")
public ResponseEntity<?> getByRange(@RequestParam Double low,@RequestParam Double high){
	wrapper.setMessage("Students scoring between " + low + " and " + high);
	wrapper.setResponse(this.studentService.getByRange(low, high));
	return new ResponseEntity<>(wrapper, HttpStatus.OK);
}

//aggregate functions

@GetMapping("ordered")
public ResponseEntity<?> getAllOrdered(){
	wrapper.setMessage("Students Ordered By Name");
	wrapper.setResponse(this.studentService.getAllOrdered());
	return new ResponseEntity<>(wrapper, HttpStatus.OK);
}
@GetMapping("count")
public ResponseEntity<?> getStudentWithSameName(){
	wrapper.setMessage("Student Count with same name");
	wrapper.setResponse(this.studentService.studentCountByName());
	return new ResponseEntity<>(wrapper, HttpStatus.OK);
}

@PostMapping("/")
	public ResponseEntity<?> addStudent(@RequestBody Student student) {
		wrapper.setMessage("Student Details Added");
		wrapper.setResponse(this.studentService.addStudent(student));
		return new ResponseEntity<>(wrapper, HttpStatus.CREATED);
	}
@PutMapping("/{id}")
	public ResponseEntity<?> updateStudent(@PathVariable Integer id,@RequestBody Student student) {
		Student found = this.studentService.getById(id);
		if(found != null) {
			wrapper.setMessage("Details of the student with ID " + id + " updated");
			wrapper.setResponse(this.studentService.updateStudent(id, student));
			return new ResponseEntity<>(wrapper, HttpStatus.OK);
		}
		else {
			wrapper.setMessage("Student with ID " + id + " not found");
			wrapper.setResponse(null);
			return new ResponseEntity<>(wrapper,HttpStatus.NOT_FOUND);
		}
}
@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteStudent(@PathVariable Integer id) {
		Student found = this.studentService.getById(id);
		if(found != null) {
			wrapper.setMessage("Details of the student with ID " + id + " updated");
			wrapper.setResponse(this.studentService.deleteStudent(id));
			return new ResponseEntity<>(wrapper, HttpStatus.OK);
		}
		else {
			wrapper.setMessage("Student with ID " + id + " not found");
			wrapper.setResponse(null);
            return new ResponseEntity<>(wrapper,HttpStatus.NOT_FOUND);
		}
	}

	
}