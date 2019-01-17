package com.emp.entity.util;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.joda.time.DateTime;
import org.pojomatic.annotations.PojomaticPolicy;
import org.pojomatic.annotations.Property;
import org.springframework.data.domain.Auditable;

@MappedSuperclass
public abstract class AuditableEntity extends PersistableEntity implements Auditable<String, String> {
	private static final long serialVersionUID = -6615842299925446677L;

	@Basic
	@Column(name = "CREATED_BY", nullable = false, columnDefinition = "CHAR(36)", length = 36, insertable = true)
	private String createdBy = "ANONYMOUS";

	@Basic
	@Column(name = "CREATED_ON", nullable = false, columnDefinition = "BIGINT(20)", insertable = true)
	private Long createdDate = System.currentTimeMillis();

	@Basic
	@Column(name = "UPDATED_BY", nullable = true, columnDefinition = "CHAR(36)", length = 36, insertable = true)
	private String lastModifiedBy;

	@Basic
	@Column(name = "UPDATED_ON", nullable = true, columnDefinition = "BIGINT(20)", insertable = true)
	private Long lastModifiedDate = System.currentTimeMillis();

	@Override
	@Property(policy = PojomaticPolicy.TO_STRING)
	public String getCreatedBy() {
		return createdBy;
	}

	@Override
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy == null ? this.createdBy : createdBy;
	}

	@Override
	@Property(policy = PojomaticPolicy.TO_STRING)
	public DateTime getCreatedDate() {
		return createdDate == null ? null : new DateTime(createdDate.longValue());
	}

	@Override
	public void setCreatedDate(DateTime createdDate) {
		this.createdDate = createdDate == null ? null : createdDate.getMillis();
	}

	@Override
	@Property(policy = PojomaticPolicy.TO_STRING)
	public String getLastModifiedBy() {
		return lastModifiedBy;
	}

	@Override
	public void setLastModifiedBy(final String lastModifiedBy) {
		this.lastModifiedBy = lastModifiedBy;
	}

	@Override
	@Property(policy = PojomaticPolicy.TO_STRING)
	public DateTime getLastModifiedDate() {
		return lastModifiedDate == null ? null : new DateTime(lastModifiedDate.longValue());
	}

	@Override
	public void setLastModifiedDate(final DateTime lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate == null ? null : lastModifiedDate.getMillis();
	}
}