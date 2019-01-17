package com.emp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.emp.entity.Employee;
import com.emp.repository.EmployeeRepository;
import com.emp.service.EmployeeService;

@Service("employeeService")
@Transactional
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private EmployeeRepository employeeRepository;
	@Override
	public Employee save(Employee employee) {
		return this.employeeRepository.save(employee);
	}
	@Override
	public List<Employee> getAllEmployee(Pageable pageable) {
		Page<Employee> page = employeeRepository.findAll(pageable);
		return page.getContent();
		//List<Employee> page = employeeRepository.findAll();
		//return page;
	}

}
