package com.awto.logger.webservices.dtos.request;

import java.util.List;

import com.awto.logger.data.models.AwlogLogger;

import lombok.Data;

@Data
public class AwlogLoggerRequest {

	private String host;

	private String origin;

	private String details;

	private String stackTrace;

	private List<String> hashtags;

	public static AwlogLogger toEntity(AwlogLoggerRequest dto) {
		AwlogLogger entity = new AwlogLogger();
		entity.setHost(dto.getHost());
		entity.setOrigin(dto.getOrigin());
		entity.setDetails(dto.getDetails());
		entity.setStackTrace(dto.getStackTrace());
		return entity;
	}

}
