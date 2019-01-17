package com.emp.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.emp.entity.Employee;

public interface EmployeeService {

	Employee save(Employee employee);

	List<Employee> getAllEmployee(Pageable pageable);

}
