package com.emp.entity.util;

import java.io.Serializable;

public interface Persistable<ID extends Serializable> extends Serializable
{
	ID getId();

	boolean isNew();
}
