package com.MediStock.MediStock.suppliers.infrastructure.persistence.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.MediStock.MediStock.suppliers.application.port.SupplierRepositoryPort;
import com.MediStock.MediStock.suppliers.domain.model.Supplier;

@Repository
public class JpaSupplierRepository implements SupplierRepositoryPort {

	private final SpringDataSupplierJpaRepository repository;

	public JpaSupplierRepository(SpringDataSupplierJpaRepository repository) {
		this.repository = repository;
	}

	@Override
	public Supplier save(Supplier supplier) {
		return repository.save(SupplierJpaEntity.fromDomain(supplier)).toDomain();
	}

	@Override
	public List<Supplier> findAll() {
		return repository.findAll().stream().map(SupplierJpaEntity::toDomain).toList();
	}

	@Override
	public Optional<Supplier> findById(Long id) {
		return repository.findById(id).map(SupplierJpaEntity::toDomain);
	}

	@Override
	public boolean existsByDocumentNumber(String documentNumber) {
		return repository.existsByDocumentNumberIgnoreCase(documentNumber);
	}
}
