package com.MediStock.MediStock.suppliers.infrastructure.persistence.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataSupplierJpaRepository extends JpaRepository<SupplierJpaEntity, Long> {

	boolean existsByDocumentNumberIgnoreCase(String documentNumber);
}
