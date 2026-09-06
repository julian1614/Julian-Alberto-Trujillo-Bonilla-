package com.MediStock.MediStock.catalog.domain.model;

import static com.MediStock.MediStock.shared.domain.DomainValidation.requireText;

public class Medication {

	private final Long id;
	private final String code;
	private final String name;
	private final String activeIngredient;
	private final String pharmaceuticalForm;
	private final String concentration;
	private final String unitOfMeasure;
	private final boolean active;

	public Medication(Long id, String code, String name, String activeIngredient, String pharmaceuticalForm,
			String concentration, String unitOfMeasure, boolean active) {
		this.id = id;
		this.code = requireText(code, "medication code");
		this.name = requireText(name, "medication name");
		this.activeIngredient = requireText(activeIngredient, "active ingredient");
		this.pharmaceuticalForm = requireText(pharmaceuticalForm, "pharmaceutical form");
		this.concentration = requireText(concentration, "concentration");
		this.unitOfMeasure = requireText(unitOfMeasure, "unit of measure");
		this.active = active;
	}

	public Long id() {
		return id;
	}

	public String code() {
		return code;
	}

	public String name() {
		return name;
	}

	public String activeIngredient() {
		return activeIngredient;
	}

	public String pharmaceuticalForm() {
		return pharmaceuticalForm;
	}

	public String concentration() {
		return concentration;
	}

	public String unitOfMeasure() {
		return unitOfMeasure;
	}

	public boolean active() {
		return active;
	}
}
