package com.MediStock.MediStock.catalog.infrastructure.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataMedicationJpaRepository extends JpaRepository<MedicationJpaEntity, Long> {

	boolean existsByCodeIgnoreCase(String code);
}
