package com.MediStock.MediStock.suppliers.application.service;

import static com.MediStock.MediStock.shared.domain.DomainValidation.requireNonNull;

import org.springframework.stereotype.Service;

import com.MediStock.MediStock.suppliers.application.port.SupplierRepositoryPort;
import com.MediStock.MediStock.suppliers.domain.model.Supplier;

@Service
public class GetSupplierService {

	private final SupplierRepositoryPort repository;

	public GetSupplierService(SupplierRepositoryPort repository) {
		this.repository = requireNonNull(repository, "supplier repository");
	}

	public Supplier get(Long id) {
		return repository.findById(requireNonNull(id, "supplier id"))
			.orElseThrow(() -> new IllegalArgumentException("supplier not found"));
	}
}
