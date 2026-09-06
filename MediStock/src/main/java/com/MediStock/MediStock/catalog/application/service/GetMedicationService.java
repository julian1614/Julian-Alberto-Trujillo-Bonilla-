package com.MediStock.MediStock.catalog.application.service;

import static com.MediStock.MediStock.shared.domain.DomainValidation.requireNonNull;

import org.springframework.stereotype.Service;

import com.MediStock.MediStock.catalog.application.port.MedicationRepositoryPort;
import com.MediStock.MediStock.catalog.domain.model.Medication;

@Service
public class GetMedicationService {

	private final MedicationRepositoryPort repository;

	public GetMedicationService(MedicationRepositoryPort repository) {
		this.repository = requireNonNull(repository, "medication repository");
	}

	public Medication get(Long id) {
		return repository.findById(requireNonNull(id, "medication id"))
			.orElseThrow(() -> new IllegalArgumentException("medication not found"));
	}
}
