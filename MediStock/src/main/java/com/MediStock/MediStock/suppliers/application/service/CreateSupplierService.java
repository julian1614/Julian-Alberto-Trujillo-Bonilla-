package com.MediStock.MediStock.suppliers.application.service;

import static com.MediStock.MediStock.shared.domain.DomainValidation.requireNonNull;

import org.springframework.stereotype.Service;

import com.MediStock.MediStock.suppliers.application.port.SupplierRepositoryPort;
import com.MediStock.MediStock.suppliers.domain.model.Supplier;

@Service
public class CreateSupplierService {

	private final SupplierRepositoryPort repository;

	public CreateSupplierService(SupplierRepositoryPort repository) {
		this.repository = requireNonNull(repository, "supplier repository");
	}

	public Supplier create(Supplier supplier) {
		Supplier requestedSupplier = requireNonNull(supplier, "supplier");
		if (repository.existsByDocumentNumber(requestedSupplier.documentNumber())) {
			throw new IllegalArgumentException("supplier document number already exists");
		}
		return repository.save(requestedSupplier);
	}
}
