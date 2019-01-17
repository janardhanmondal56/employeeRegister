package com.emp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.emp.entity.util.AuditableEntity;

@Entity
@Table(name = "EMPLOYEE")
public class Employee extends AuditableEntity{

	private static final long serialVersionUID = -2368360904334310818L;

	@Column(name = "FIRST_NAME", columnDefinition = "CHAR(128)", nullable = false)
	private String firstName;

	@Column(name = "LAST_NAME", columnDefinition = "CHAR(36)", nullable = false)
	private String lastName;

	@Column(name = "GENDER", columnDefinition = "CHAR(36)", nullable = true)
	private String gender;
	
	@Column(name = "DOB", columnDefinition = "CHAR(36)", nullable = false)
	private String dob;

	@Column(name = "DEPARTMENT", columnDefinition = "CHAR(36)", nullable = true)
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
