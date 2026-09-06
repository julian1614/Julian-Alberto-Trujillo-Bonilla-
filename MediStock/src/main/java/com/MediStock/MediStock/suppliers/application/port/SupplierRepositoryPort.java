package com.MediStock.MediStock.suppliers.application.port;

import java.util.List;
import java.util.Optional;

import com.MediStock.MediStock.suppliers.domain.model.Supplier;

public interface SupplierRepositoryPort {

	Supplier save(Supplier supplier);

	List<Supplier> findAll();

	Optional<Supplier> findById(Long id);

	boolean existsByDocumentNumber(String documentNumber);
}
