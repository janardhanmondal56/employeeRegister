package com.emp.view.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.emp.dto.EmployeeDTO;
import com.emp.entity.Employee;
import com.emp.rest.exception.customexception.ValidationException;
import com.emp.service.EmployeeService;
import com.emp.util.pagination.PaginationUtil;
import com.emp.view.EmployeeViewService;
@Service
public class EmployeeViewServiceImpl implements EmployeeViewService{

	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private PaginationUtil paginationUtil;
	
	@Override
	public void validateNewEmployee(EmployeeDTO employeeDTO) throws ValidationException {
		if(StringUtils.isEmpty(employeeDTO.getFirstName())) {
			throw new ValidationException("Employee first name is mendatory!");
		}
		if(StringUtils.isEmpty(employeeDTO.getLastName())) {
			throw new ValidationException("Employee Last name is mendatory!");
		}
		if(StringUtils.isEmpty(employeeDTO.getDob())) {
			throw new ValidationException("Employee DOB is mendatory!");
		}
		
	}

	@Override
	public EmployeeDTO saveEmployeeDetails(EmployeeDTO employeeDTO) throws ValidationException {
		
		Employee employee = new Employee();
		employee.setFirstName(employeeDTO.getFirstName());
		employee.setLastName(employeeDTO.getLastName());
		employee.setGender(employeeDTO.getGender());
		employee.setDob(employeeDTO.getDob());
		employee.setDepartment(employeeDTO.getDepartment());
		Employee savedEmployee = employeeService.save(employee);
		employeeDTO.setId(savedEmployee.getId());
		return employeeDTO;
	}

	@Override
	public List<EmployeeDTO> getAllEmployeeDetails(int page, int start, int limit, String dir, String sort)
			throws ValidationException {

		//Pageable pageable = paginationUtil.getPageableObject(page, limit, dir, sort);
		Pageable pageable = this.paginationUtil.getPageableObject(page, limit, dir);
		
		List<Employee> employeeList = employeeService.getAllEmployee(pageable);
		List<EmployeeDTO> employeeDtoList = new ArrayList<>();
		for(Employee employee : employeeList ) {
			EmployeeDTO employeeDTO = new EmployeeDTO(employee.getFirstName(), employee.getLastName(),
					employee.getGender(), employee.getDob(), employee.getDepartment());
			employeeDtoList.add(employeeDTO);
		}
		return employeeDtoList;
	}

}
