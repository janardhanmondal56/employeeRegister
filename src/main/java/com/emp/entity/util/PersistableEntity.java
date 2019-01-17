package com.emp.entity.util;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.proxy.HibernateProxy;
import org.pojomatic.Pojomatic;
import org.pojomatic.annotations.PojomaticPolicy;
import org.pojomatic.annotations.Property;
import org.springframework.data.domain.Persistable;

@MappedSuperclass
public abstract class PersistableEntity implements java.io.Serializable, Persistable<String> {

	private static final long serialVersionUID = 2535090450811888936L;

	@Id
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	@Column(name = "ID", columnDefinition = "CHAR(36)", length = 36, nullable = false)
	@Property(policy = PojomaticPolicy.TO_STRING)
	private String id;

	@Version
	@Column(name = "VERSION_NUMBER")
	@NotNull
	@Property(policy = PojomaticPolicy.TO_STRING)
	private long versionNumber = 1;
	
	@Column(name = "INACTIVE", columnDefinition = "TINYINT(1) DEFAULT 0", nullable = false)
	private boolean inactive;

	@Override
	public String getId() {
		return id;
	}

	public void setId(final String id) {
		this.id = id;
	}

	public long getVersionNumber() {
		return versionNumber;
	}

	public void setVersionNumber(long versionNumber) {
		this.versionNumber = versionNumber;
	}

	@Override
	public boolean isNew() {
		return null == getId();
	}

	public boolean isInactive() {
		return inactive;
	}

	public void setInactive(boolean inactive) {
		this.inactive = inactive;
	}

	@Override
	public final String toString() {
		return Pojomatic.toString(getEntity());
	}

	@Override
	public final boolean equals(Object obj) {
		return Pojomatic.equals(getEntity(), obj);
	}

	@Override
	public final int hashCode() {
		return Pojomatic.hashCode(getEntity());
	}

	private Object getEntity() {
		if (this instanceof HibernateProxy) {
			return HibernateProxy.class.cast(this).getHibernateLazyInitializer().getImplementation();
		} else {
			return this;
		}
	}
}