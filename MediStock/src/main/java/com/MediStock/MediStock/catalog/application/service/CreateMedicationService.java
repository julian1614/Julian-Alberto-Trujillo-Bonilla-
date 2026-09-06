package com.MediStock.MediStock.catalog.application.service;

import static com.MediStock.MediStock.shared.domain.DomainValidation.requireNonNull;

import org.springframework.stereotype.Service;

import com.MediStock.MediStock.catalog.application.port.MedicationRepositoryPort;
import com.MediStock.MediStock.catalog.domain.model.Medication;

@Service
public class CreateMedicationService {

	private final MedicationRepositoryPort repository;

	public CreateMedicationService(MedicationRepositoryPort repository) {
		this.repository = requireNonNull(repository, "medication repository");
	}

	public Medication create(Medication medication) {
		Medication requestedMedication = requireNonNull(medication, "medication");
		if (repository.existsByCode(requestedMedication.code())) {
			throw new IllegalArgumentException("medication code already exists");
		}
		return repository.save(requestedMedication);
	}
}
