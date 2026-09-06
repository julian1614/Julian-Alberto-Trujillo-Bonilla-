package com.MediStock.MediStock.catalog.infrastructure.persistence.inmemory;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Repository;

import com.MediStock.MediStock.catalog.application.port.MedicationRepositoryPort;
import com.MediStock.MediStock.catalog.domain.model.Medication;

@Repository
public class InMemoryMedicationRepository implements MedicationRepositoryPort {

	private final ConcurrentMap<Long, Medication> medications = new ConcurrentHashMap<>();
	private final AtomicLong sequence = new AtomicLong(0);

	@Override
	public Medication save(Medication medication) {
		Long id = medication.id() == null ? sequence.incrementAndGet() : medication.id();
		Medication storedMedication = new Medication(id, medication.code(), medication.name(),
				medication.activeIngredient(), medication.pharmaceuticalForm(), medication.concentration(),
				medication.unitOfMeasure(), medication.active());
		medications.put(id, storedMedication);
		return storedMedication;
	}

	@Override
	public List<Medication> findAll() {
		return medications.values()
			.stream()
			.sorted(Comparator.comparing(Medication::id))
			.toList();
	}

	@Override
	public Optional<Medication> findById(Long id) {
		return Optional.ofNullable(medications.get(id));
	}

	@Override
	public boolean existsByCode(String code) {
		return medications.values()
			.stream()
			.anyMatch(medication -> medication.code().equalsIgnoreCase(code));
	}
}
