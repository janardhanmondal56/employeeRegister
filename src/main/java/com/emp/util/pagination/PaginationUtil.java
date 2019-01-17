package com.emp.util.pagination;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.emp.rest.exception.customexception.ValidationException;

@Component
public class PaginationUtil {
	public Pageable getPageableObject(int pageNum, int pageSize, String direction, String properties) throws ValidationException {
		this.validatePaginationParameters(pageNum, pageSize, direction, properties);
		Sort sort = new Sort(Sort.Direction.fromString(direction), properties);
		return new PageRequest(pageNum - 1, pageSize, sort);
	}

	public Pageable getPageableObject(int pageNum, int pageSize, String direction) throws ValidationException {
		this.validatePaginationParameters(pageNum, pageSize, direction, null);
		return new PageRequest(pageNum - 1, pageSize);
	}

	public void validatePaginationParameters(int pageNum, int pageSize, String direction, String properties)
			throws ValidationException {
		try {
			Sort.Direction.fromString(direction);
		} catch (IllegalArgumentException exception) {
			throw new ValidationException("Sort direction is invalid");
		}
		if (StringUtils.isNotBlank(properties) && properties.contains("%")) {
			throw new ValidationException("Invalid sort property");
		}
		if (pageNum <= 0 || pageNum > 999) {
			throw new ValidationException("Invalid page number value");
		}
		if (pageSize <= 0 || pageSize > 999) {
			throw new ValidationException("Invalid page size(limit) value");
		}
	}
}
