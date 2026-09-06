package com.MediStock.MediStock.suppliers.infrastructure.persistence.inmemory;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import com.MediStock.MediStock.suppliers.application.port.SupplierRepositoryPort;
import com.MediStock.MediStock.suppliers.domain.model.Supplier;

@Repository
@Profile("memory")
public class InMemorySupplierRepository implements SupplierRepositoryPort {

	private final ConcurrentMap<Long, Supplier> suppliers = new ConcurrentHashMap<>();
	private final AtomicLong sequence = new AtomicLong(0);

	@Override
	public Supplier save(Supplier supplier) {
		Long id = supplier.id() == null ? sequence.incrementAndGet() : supplier.id();
		Supplier storedSupplier = new Supplier(id, supplier.documentNumber(), supplier.name(), supplier.phone(),
				supplier.email(), supplier.address(), supplier.active());
		suppliers.put(id, storedSupplier);
		return storedSupplier;
	}

	@Override
	public List<Supplier> findAll() {
		return suppliers.values()
			.stream()
			.sorted(Comparator.comparing(Supplier::id))
			.toList();
	}

	@Override
	public Optional<Supplier> findById(Long id) {
		return Optional.ofNullable(suppliers.get(id));
	}

	@Override
	public boolean existsByDocumentNumber(String documentNumber) {
		return suppliers.values()
			.stream()
			.anyMatch(supplier -> supplier.documentNumber().equalsIgnoreCase(documentNumber));
	}
}
