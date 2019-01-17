package com.emp.util.dto;

import java.io.Serializable;

import org.pojomatic.Pojomatic;
import org.pojomatic.annotations.Property;

public class BaseDTO implements Serializable {
	private static final long serialVersionUID = 1335106222930457712L;

	private String id;

	private boolean inactive;
	
	@Property
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Property
	public boolean isInactive() {
		return inactive;
	}

	public void setInactive(boolean inactive) {
		this.inactive = inactive;
	}

	@Override
	public final boolean equals(Object obj) {
		return Pojomatic.equals(this, obj);
	}

	@Override
	public final int hashCode() {
		return Pojomatic.hashCode(this);
	}
}
