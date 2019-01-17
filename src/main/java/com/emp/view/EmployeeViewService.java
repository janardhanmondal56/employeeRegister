package com.emp.view;

import java.util.List;

import com.emp.dto.EmployeeDTO;
import com.emp.rest.exception.customexception.ValidationException;

public interface EmployeeViewService {

	void validateNewEmployee(EmployeeDTO employeeDTO) throws ValidationException;

	EmployeeDTO saveEmployeeDetails(EmployeeDTO employeeDTO) throws ValidationException;

	List<EmployeeDTO> getAllEmployeeDetails(int page, int start, int limit, String dir, String sort) throws ValidationException;;

}
