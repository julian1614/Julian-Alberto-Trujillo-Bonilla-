package com.MediStock.MediStock.catalog.application.port;

import java.util.List;
import java.util.Optional;

import com.MediStock.MediStock.catalog.domain.model.Medication;

public interface MedicationRepositoryPort {

	Medication save(Medication medication);

	List<Medication> findAll();

	Optional<Medication> findById(Long id);

	boolean existsByCode(String code);
}
