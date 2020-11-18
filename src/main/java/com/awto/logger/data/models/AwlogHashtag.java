package com.awto.logger.data.models;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import org.springframework.data.relational.core.mapping.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.querydsl.core.annotations.PropertyType;
import com.querydsl.core.annotations.QueryType;

@Entity
@Table(value = "[awlog_hashtag]")
public class AwlogHashtag extends CustomBaseEntity {

	@QueryType(PropertyType.NONE)
	@JsonIgnore
	private static final long serialVersionUID = -8930902037221753192L;

	private String description;

	private Set<AwlogLogger> loggers;

	@Column(name = "description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@ManyToMany(mappedBy = "tags")
	public Set<AwlogLogger> getLoggers() {
		return loggers;
	}

	public void setLoggers(Set<AwlogLogger> loggers) {
		this.loggers = loggers;
	}

}
