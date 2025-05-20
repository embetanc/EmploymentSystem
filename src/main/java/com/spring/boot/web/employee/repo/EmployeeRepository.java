package com.spring.boot.web.employee.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.spring.boot.web.employee.entity.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Long>{

	/*int create(Employee employee);
	
	List<Employee> findEmployees();
	
	Employee findEmployee(Integer id);*/
	
	@Query(value = "SELECT * FROM employee WHERE uid = ?1", nativeQuery = true)
	Employee findByUid(String uid);
}