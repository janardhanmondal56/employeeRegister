package com.emp.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.emp.dto.EmployeeDTO;
import com.emp.util.dto.SuccessResponseDTO;
import com.emp.util.response.SimpleResponse;
import com.emp.view.EmployeeViewService;

@Controller
public class EmployeeController {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);
	@Autowired
	private EmployeeViewService employeeViewService;
	
	@RequestMapping(value = "/api/v1/employee/register", method = RequestMethod.POST)
	@ResponseBody
	public HttpEntity<SimpleResponse<SuccessResponseDTO>> registerEmployee(@RequestBody EmployeeDTO employeeDTO) throws Exception {
		LOGGER.debug("Saving employee details!");
		this.employeeViewService.validateNewEmployee(employeeDTO);
		EmployeeDTO resultObj = this.employeeViewService.saveEmployeeDetails(employeeDTO);
		return new ResponseEntity<SimpleResponse<SuccessResponseDTO>>(
				new SimpleResponse<SuccessResponseDTO>(new SuccessResponseDTO(true, resultObj, "Employee successfully created!")),
				HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/api/v1/employee", method = RequestMethod.GET)
	@ResponseBody
	public HttpEntity<SimpleResponse<SuccessResponseDTO>> getAllEmployee(@RequestParam(required = false, defaultValue = "1") int page,
			@RequestParam(required = false, defaultValue = "1") int start,
			@RequestParam(required = false, defaultValue = "10") int limit,
			@RequestParam(required = false, defaultValue = "ASC") String dir,
			@RequestParam(required = false, defaultValue = "type") String sort) throws Exception {
		LOGGER.debug("Get all employee: Parameters {} ", new Object[] { page, start, limit, dir, sort });
		List<EmployeeDTO> resultObj = this.employeeViewService.getAllEmployeeDetails(page, start, limit, dir, sort);
		return new ResponseEntity<SimpleResponse<SuccessResponseDTO>>(
				new SimpleResponse<SuccessResponseDTO>(new SuccessResponseDTO(true, resultObj, "Success")),
				HttpStatus.CREATED);
	}
}
