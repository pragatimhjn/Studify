package com.itvedant.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.itvedant.model.Student;
import com.itvedant.model.StudentWithoutEmail;

//import com.itvedant.model.Student;
@Repository
public interface StudentRepository 
       extends CrudRepository<Student, Integer>{

	List<StudentWithoutEmail> findAllProjectedBy();

//	Optional<Student> findProjectedById(Integer id);
    //return StudentWithoutEmail not Student
	
	Optional<StudentWithoutEmail> findProjectedById(Integer id);
	List<StudentWithoutEmail> findByName(String name);
    List<StudentWithoutEmail> findByAggregateGreaterThan(Double aggregate);	
	List<Student> findByEmailContaining(String pattern);	
	List<Student> findByEmailContainingOrEmailContaining(String pattern1, String pattern2);
	List<StudentWithoutEmail> findByAggregateBetween(Double low,Double high);
	//customized query for aggregate functions
	@Query(value = "select s from Student s order by s.name")
	List<StudentWithoutEmail> findAllOrderByName();
	//here we are returning list of objects as we dont have any mapping for fields returned in result
	@Query(value = "select s.name, count(s.name) from Student s group by s.name")
	List<Object[]> findSameNameStudent();

} 


/*   after projection
@Repository
public interface StudentRepository
	extends CrudRepository<Student, Integer>{
	List<StudentWithoutEmail> findAllProjectedBy();
}

*/