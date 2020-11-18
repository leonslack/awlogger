package com.awto.logger.services;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.awto.logger.commons.exceptions.CustomBaseException;
import com.awto.logger.commons.exceptions.CustomInvalidAttributeException;
import com.awto.logger.commons.exceptions.CustomMissingAttributeException;
import com.awto.logger.data.models.AwlogHashtag;
import com.awto.logger.data.predicates.AwlogHashtagPredicates;
import com.awto.logger.data.repositories.AwlogHashtagRepository;

@Service("IAwlogHashtagService")
public class AwlogHashtagService implements IAwlogHashtagService {

	private AwlogHashtagRepository repository;

	@Autowired
	public AwlogHashtagService(AwlogHashtagRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public List<AwlogHashtag> findOrCreateTags(List<String> tags) throws CustomBaseException {
		List<AwlogHashtag> tagsFounds = repository.findByDescriptionIn(tags);
		if (tags.size() == tagsFounds.size()) {
			return tagsFounds;
		}

		List<AwlogHashtag> newTagsToPersist = new LinkedList<AwlogHashtag>();

		if (tags.size() > tagsFounds.size() && !tagsFounds.isEmpty()) {
			newTagsToPersist = getTagsNotFound(tags, tagsFounds);

		} else if (tagsFounds.isEmpty()) {
			newTagsToPersist = createEntitiesToPersist(tags);

		}
		tagsFounds.addAll(persistInBUlk(newTagsToPersist));

		return tagsFounds;
	}

	private List<AwlogHashtag> persistInBUlk(List<AwlogHashtag> newTagsToPersist) {
		return repository.saveAll(newTagsToPersist);
	}

	private List<AwlogHashtag> getTagsNotFound(List<String> tags, List<AwlogHashtag> tagsFounds) {
		List<String> tagsToCreate = tags.stream()
				.filter(tag -> tagsFounds.stream().anyMatch(tagFound -> !tagFound.getDescription().equals(tag)))
				.collect(Collectors.toList());
		return createEntitiesToPersist(tagsToCreate);
	}

	private List<AwlogHashtag> createEntitiesToPersist(List<String> tagsToCreate) {
		List<AwlogHashtag> entitiesToCreate = new LinkedList<AwlogHashtag>();
		for (String newTag : tagsToCreate) {
			AwlogHashtag entity = new AwlogHashtag();
			entity.setDescription(newTag);
			entitiesToCreate.add(entity);
		}

		return entitiesToCreate;
	}

	@Override
	public AwlogHashtag updateHashtag(AwlogHashtag toUpdate) throws CustomBaseException {
		AwlogHashtag entity = repository.getOne(toUpdate.getId());
		validateBeforeUpdate(toUpdate);
		entity.setDescription(toUpdate.getDescription());
		return repository.save(entity);
	}

	private void validateBeforeUpdate(AwlogHashtag toUpdate) throws CustomBaseException {

		if (repository.exists(AwlogHashtagPredicates.hashtagEquals(toUpdate.getDescription()))) {
			throw new CustomInvalidAttributeException(
					"another hashtag exist with description: " + toUpdate.getDescription());
		}
		if (toUpdate.getDescription().isEmpty()) {
			throw new CustomMissingAttributeException("attribute description its empty or null");
		}
	}
}
