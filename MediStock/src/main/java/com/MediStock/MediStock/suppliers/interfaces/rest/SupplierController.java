package com.MediStock.MediStock.suppliers.interfaces.rest;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MediStock.MediStock.suppliers.application.service.CreateSupplierService;
import com.MediStock.MediStock.suppliers.application.service.GetSupplierService;
import com.MediStock.MediStock.suppliers.application.service.ListSuppliersService;
import com.MediStock.MediStock.suppliers.domain.model.Supplier;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

	private final CreateSupplierService createSupplierService;
	private final ListSuppliersService listSuppliersService;
	private final GetSupplierService getSupplierService;

	public SupplierController(CreateSupplierService createSupplierService, ListSuppliersService listSuppliersService,
			GetSupplierService getSupplierService) {
		this.createSupplierService = createSupplierService;
		this.listSuppliersService = listSuppliersService;
		this.getSupplierService = getSupplierService;
	}

	@PostMapping
	public ResponseEntity<SupplierResponse> create(@Valid @RequestBody SupplierRequest request) {
		Supplier supplier = createSupplierService.create(new Supplier(null, request.documentNumber(), request.name(),
				request.phone(), request.email(), request.address(), true));
		return ResponseEntity.created(URI.create("/api/suppliers/" + supplier.id()))
			.body(SupplierResponse.from(supplier));
	}

	@GetMapping
	public List<SupplierResponse> list() {
		return listSuppliersService.list().stream().map(SupplierResponse::from).toList();
	}

	@GetMapping("/{id}")
	public SupplierResponse get(@PathVariable Long id) {
		return SupplierResponse.from(getSupplierService.get(id));
	}
}
