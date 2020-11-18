package com.awto.logger.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.awto.logger.commons.exceptions.CustomBaseException;
import com.awto.logger.data.models.AwlogHashtag;

public interface IAwlogHashtagService {

	@Transactional(rollbackFor = Exception.class)
	List<AwlogHashtag> findOrCreateTags(List<String> tags) throws CustomBaseException;

	@Transactional(rollbackFor = Exception.class)
	AwlogHashtag updateHashtag(AwlogHashtag toUpdate) throws CustomBaseException;

}
