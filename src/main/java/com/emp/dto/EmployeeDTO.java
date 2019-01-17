package com.emp.dto;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.emp.util.dto.AuditableDTO;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EmployeeDTO extends AuditableDTO {

	private static final long serialVersionUID = -1347749921063755983L;
	
	private String firstName;

	private String lastName;

	public EmployeeDTO(String firstName, String lastName, String gender, String dob, String department) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.dob = dob;
		this.department = department;
	}

	public EmployeeDTO() {
	}

	private String gender;

	private String dob;
	
	private String department;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

}
