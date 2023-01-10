package com.itvedant.model;

import org.springframework.beans.factory.annotation.Value;

public interface StudentWithoutEmail {
	String getName();
	Double getAggregate();
	//creating projection (custom result like view in sql) 
	@Value("#{target.name}" +"has scored "  + "#{target.aggregate}")
	String getResult();

}
