package com.MediStock.MediStock.catalog.interfaces.rest;

import jakarta.validation.constraints.NotBlank;

public record MedicationRequest(
		@NotBlank String code,
		@NotBlank String name,
		@NotBlank String activeIngredient,
		@NotBlank String pharmaceuticalForm,
		@NotBlank String concentration,
		@NotBlank String unitOfMeasure) {
}
