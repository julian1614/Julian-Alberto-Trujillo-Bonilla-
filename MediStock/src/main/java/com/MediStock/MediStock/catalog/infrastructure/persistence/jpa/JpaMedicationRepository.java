package com.MediStock.MediStock.catalog.infrastructure.persistence.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.MediStock.MediStock.catalog.application.port.MedicationRepositoryPort;
import com.MediStock.MediStock.catalog.domain.model.Medication;

@Repository
public class JpaMedicationRepository implements MedicationRepositoryPort {

	private final SpringDataMedicationJpaRepository repository;

	public JpaMedicationRepository(SpringDataMedicationJpaRepository repository) {
		this.repository = repository;
	}

	@Override
	public Medication save(Medication medication) {
		return repository.save(MedicationJpaEntity.fromDomain(medication)).toDomain();
	}

	@Override
	public List<Medication> findAll() {
		return repository.findAll().stream().map(MedicationJpaEntity::toDomain).toList();
	}

	@Override
	public Optional<Medication> findById(Long id) {
		return repository.findById(id).map(MedicationJpaEntity::toDomain);
	}

	@Override
	public boolean existsByCode(String code) {
		return repository.existsByCodeIgnoreCase(code);
	}
}
