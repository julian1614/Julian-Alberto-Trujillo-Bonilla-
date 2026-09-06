package com.MediStock.MediStock.catalog.interfaces.rest;

import com.MediStock.MediStock.catalog.domain.model.Medication;

public record MedicationResponse(Long id, String code, String name, String activeIngredient, String pharmaceuticalForm,
		String concentration, String unitOfMeasure, boolean active) {

	public static MedicationResponse from(Medication medication) {
		return new MedicationResponse(medication.id(), medication.code(), medication.name(),
				medication.activeIngredient(), medication.pharmaceuticalForm(), medication.concentration(),
				medication.unitOfMeasure(), medication.active());
	}
}
