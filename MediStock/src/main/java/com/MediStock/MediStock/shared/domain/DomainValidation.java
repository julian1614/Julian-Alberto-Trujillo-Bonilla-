package com.MediStock.MediStock.shared.domain;

import java.util.Collection;

public final class DomainValidation {

	private DomainValidation() {
	}

	public static String requireText(String value, String fieldName) {
		if (value == null || value.isBlank()) {
			throw new IllegalArgumentException(fieldName + " is required");
		}
		return value.trim();
	}

	public static <T> T requireNonNull(T value, String fieldName) {
		if (value == null) {
			throw new IllegalArgumentException(fieldName + " is required");
		}
		return value;
	}

	public static <T extends Collection<?>> T requireNotEmpty(T value, String fieldName) {
		requireNonNull(value, fieldName);
		if (value.isEmpty()) {
			throw new IllegalArgumentException(fieldName + " cannot be empty");
		}
		return value;
	}
}
