package com.awto.logger.data.models;

import java.time.Instant;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.relational.core.mapping.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.querydsl.core.annotations.PropertyType;
import com.querydsl.core.annotations.QueryType;

@Entity
@Table(value = "[awlog_logger]")
public class AwlogLogger extends CustomBaseEntity {

	@QueryType(PropertyType.NONE)
	@JsonIgnore
	private static final long serialVersionUID = -665120036581119982L;

	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private Instant creationDate;

	@JsonIgnore
	public static final String _creationDate = "creationDate";

	@NotNull
	private String host;

	@NotNull
	private String origin;

	@NotNull
	private String details;

	@NotNull
	private String stackTrace;

	private Set<AwlogHashtag> tags;

	@CreatedDate
	@Column(name = "creation_date", columnDefinition = "DATETIME", updatable = false)
	public Instant getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Instant creationDate) {
		this.creationDate = creationDate;
	}

	@PrePersist
	void onCreate() {
		Instant currentDate = Instant.now();

		setCreationDate(currentDate);

	}

	@Column(name = "host")
	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	@Column(name = "origin")
	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	@Column(name = "details")
	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	@Column(name = "stacktrace")
	public String getStackTrace() {
		return stackTrace;
	}

	public void setStackTrace(String stackTrace) {
		this.stackTrace = stackTrace;
	}

	@ManyToMany
	@JoinTable(name = "awlog_logger_hashtag", joinColumns = @JoinColumn(name = "log_id"), inverseJoinColumns = @JoinColumn(name = "hastag_id"))
	public Set<AwlogHashtag> getTags() {
		return tags;
	}

	public void setTags(Set<AwlogHashtag> tags) {
		this.tags = tags;
	}

	@Override
	public String toString() {
		return "AwtoLogger [" + (creationDate != null ? "creationDate=" + creationDate + ", " : "")
				+ (host != null ? "host=" + host + ", " : "") + (origin != null ? "origin=" + origin + ", " : "")
				+ (details != null ? "details=" + details + ", " : "")
				+ (stackTrace != null ? "stackTrace=" + stackTrace : "") + "]";
	}

}
