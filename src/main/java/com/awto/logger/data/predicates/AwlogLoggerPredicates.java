package com.awto.logger.data.predicates;

import com.awto.logger.data.models.QAwlogLogger;
import com.querydsl.core.types.Predicate;

public final class AwlogLoggerPredicates {

	private AwlogLoggerPredicates() {
	}

	private static QAwlogLogger awlogLogger = QAwlogLogger.awlogLogger;

	public static Predicate hashtagEq(String hashTag) {
		return awlogLogger.tags.any().description.eq(hashTag);
	}

	public static Predicate idNotNull() {
		return awlogLogger.id.isNotNull();
	}

}
