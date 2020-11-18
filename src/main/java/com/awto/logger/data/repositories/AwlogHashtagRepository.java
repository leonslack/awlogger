package com.awto.logger.data.repositories;

import java.util.Collection;
import java.util.List;

import com.awto.logger.data.models.AwlogHashtag;

public interface AwlogHashtagRepository extends CustomBaseJpaRepository<AwlogHashtag, Long> {

	List<AwlogHashtag> findByDescriptionIn(Collection<String> tags);

}
