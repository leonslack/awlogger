package com.awto.logger.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.awto.logger.commons.exceptions.CustomBaseException;
import com.awto.logger.data.models.AwlogLogger;
import com.awto.logger.webservices.dtos.request.AwlogLoggerRequest;
import com.querydsl.core.types.Predicate;

public interface IAwlogLoggerService {

	@Transactional(rollbackFor = Exception.class)
	AwlogLogger createLog(AwlogLoggerRequest request) throws CustomBaseException;

	@Transactional(readOnly = true)
	List<AwlogLogger> getLogs(Predicate predicate) throws CustomBaseException;

	@Transactional(readOnly = true)
	AwlogLogger getLogById(Long id) throws CustomBaseException;
}
