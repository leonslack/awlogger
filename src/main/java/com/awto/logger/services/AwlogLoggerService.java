package com.awto.logger.services;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.awto.logger.commons.exceptions.CustomBaseException;
import com.awto.logger.data.models.AwlogLogger;
import com.awto.logger.data.repositories.AwlogLoggerRepository;
import com.awto.logger.webservices.dtos.request.AwlogLoggerRequest;
import com.querydsl.core.types.Predicate;

@Service("IAwlogLoggerService")
public class AwlogLoggerService implements IAwlogLoggerService {

	private AwlogLoggerRepository repository;

	private IAwlogHashtagService tagsService;

	@Autowired
	public AwlogLoggerService(AwlogLoggerRepository repository, IAwlogHashtagService tagsService) {
		super();
		this.repository = repository;
		this.tagsService = tagsService;
	}

	@Override
	public AwlogLogger createLog(AwlogLoggerRequest request) throws CustomBaseException {
		AwlogLogger toPersist = AwlogLoggerRequest.toEntity(request);
		toPersist.setTags(new HashSet<>(tagsService.findOrCreateTags(request.getHashtags())));
		return repository.save(toPersist);
	}

	@Override
	public List<AwlogLogger> getLogs(Predicate predicate) throws CustomBaseException {
		return repository.findAll();
	}

	@Override
	public AwlogLogger getLogById(Long id) throws CustomBaseException {
		return repository.findById(id).orElse(null);
	}

}
