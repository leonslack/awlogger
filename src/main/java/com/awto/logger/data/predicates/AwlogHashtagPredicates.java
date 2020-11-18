package com.awto.logger.data.predicates;

import com.awto.logger.data.models.QAwlogHashtag;
import com.querydsl.core.types.Predicate;

public final class AwlogHashtagPredicates {

	private AwlogHashtagPredicates() {
	}

	private static QAwlogHashtag awlogHashtag = QAwlogHashtag.awlogHashtag;

	public static Predicate hashtagEquals(String hashtag) {
		return awlogHashtag.description.eq(hashtag);
	}

}
