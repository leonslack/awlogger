
package com.awto.logger.data.models;

import java.beans.Transient;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.querydsl.core.annotations.PropertyType;
import com.querydsl.core.annotations.QueryType;

@MappedSuperclass
public abstract class CustomBaseEntity implements Serializable {

	@QueryType(PropertyType.NONE)
	@JsonIgnore
	private static final long serialVersionUID = 628959748387095558L;

	@QueryType(PropertyType.NONE)
	@JsonIgnore
	final static Logger log = LogManager.getLogger();

	private Long id = 0l;

	@JsonIgnore
	public static final String _id = "id";

	public CustomBaseEntity() {

	}

	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof CustomBaseEntity)) {
			return false;
		}
		if (this.idIsNullOrEmpty()) {
			return false;
		}
		CustomBaseEntity other = (CustomBaseEntity) obj;
		return getId().equals(other.getId());
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Transient
	public boolean idIsNullOrEmpty() {
		return (this.id == null || this.id.equals(0L));
	}

	@Override
	public String toString() {
		return "CustomBaseEntity [" + (id != null ? "id=" + id : "") + "]";
	}

}