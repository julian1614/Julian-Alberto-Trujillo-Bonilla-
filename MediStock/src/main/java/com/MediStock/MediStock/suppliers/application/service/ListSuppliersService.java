package com.MediStock.MediStock.suppliers.application.service;

import static com.MediStock.MediStock.shared.domain.DomainValidation.requireNonNull;

import java.util.List;

import org.springframework.stereotype.Service;

import com.MediStock.MediStock.suppliers.application.port.SupplierRepositoryPort;
import com.MediStock.MediStock.suppliers.domain.model.Supplier;

@Service
public class ListSuppliersService {

	private final SupplierRepositoryPort repository;

	public ListSuppliersService(SupplierRepositoryPort repository) {
		this.repository = requireNonNull(repository, "supplier repository");
	}

	public List<Supplier> list() {
		return repository.findAll();
	}
}
