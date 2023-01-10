package com.itvedant.util;

import com.itvedant.model.Student;

import lombok.Data;

//wrapping response in common format with message
//fields show message and response
@Data

public class ResponseWrapper {
	private String message;
	private Object response;
//	public void setMessage(String string) {
//		// TODO Auto-generated method stub
//		
//	}
//	public void setResponse(Iterable<Student> all) {
//		// TODO Auto-generated method stub
//		
//	}
//	public void setResponse(Student found) {
//		// TODO Auto-generated method stub
//		
//	}

}
