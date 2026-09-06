package com.MediStock.MediStock.catalog.application.service;

import static com.MediStock.MediStock.shared.domain.DomainValidation.requireNonNull;

import java.util.List;

import org.springframework.stereotype.Service;

import com.MediStock.MediStock.catalog.application.port.MedicationRepositoryPort;
import com.MediStock.MediStock.catalog.domain.model.Medication;

@Service
public class ListMedicationsService {

	private final MedicationRepositoryPort repository;

	public ListMedicationsService(MedicationRepositoryPort repository) {
		this.repository = requireNonNull(repository, "medication repository");
	}

	public List<Medication> list() {
		return repository.findAll();
	}
}
