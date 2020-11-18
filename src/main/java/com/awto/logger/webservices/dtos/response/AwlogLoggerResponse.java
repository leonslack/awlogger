package com.awto.logger.webservices.dtos.response;

import java.util.LinkedList;
import java.util.List;

import com.awto.logger.data.models.AwlogHashtag;
import com.awto.logger.data.models.AwlogLogger;

import lombok.Data;

@Data
public class AwlogLoggerResponse {

	private String host;

	private String origin;

	private String details;

	private String stackTrace;

	private List<String> hashtags;

	public static AwlogLoggerResponse fromEntity(AwlogLogger entity) {
		AwlogLoggerResponse dto = new AwlogLoggerResponse();
		dto.setHost(entity.getHost());
		dto.setOrigin(entity.getOrigin());
		dto.setDetails(entity.getDetails());
		dto.setStackTrace(entity.getStackTrace());
		List<String> tags = new LinkedList<String>();
		for (AwlogHashtag tag : entity.getTags()) {
			tags.add(tag.getDescription());
		}
		dto.setHashtags(tags);
		return dto;
	}
}
