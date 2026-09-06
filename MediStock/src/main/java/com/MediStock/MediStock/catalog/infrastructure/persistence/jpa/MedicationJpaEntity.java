package com.MediStock.MediStock.catalog.infrastructure.persistence.jpa;

import com.MediStock.MediStock.catalog.domain.model.Medication;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "medications")
public class MedicationJpaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true, length = 40)
	private String code;

	@Column(nullable = false, length = 120)
	private String name;

	@Column(nullable = false, length = 120)
	private String activeIngredient;

	@Column(nullable = false, length = 80)
	private String pharmaceuticalForm;

	@Column(nullable = false, length = 60)
	private String concentration;

	@Column(nullable = false, length = 40)
	private String unitOfMeasure;

	@Column(nullable = false)
	private boolean active;

	protected MedicationJpaEntity() {
	}

	private MedicationJpaEntity(Long id, String code, String name, String activeIngredient,
			String pharmaceuticalForm, String concentration, String unitOfMeasure, boolean active) {
		this.id = id;
		this.code = code;
		this.name = name;
		this.activeIngredient = activeIngredient;
		this.pharmaceuticalForm = pharmaceuticalForm;
		this.concentration = concentration;
		this.unitOfMeasure = unitOfMeasure;
		this.active = active;
	}

	public static MedicationJpaEntity fromDomain(Medication medication) {
		return new MedicationJpaEntity(medication.id(), medication.code(), medication.name(),
				medication.activeIngredient(), medication.pharmaceuticalForm(), medication.concentration(),
				medication.unitOfMeasure(), medication.active());
	}

	public Medication toDomain() {
		return new Medication(id, code, name, activeIngredient, pharmaceuticalForm, concentration, unitOfMeasure,
				active);
	}
}
