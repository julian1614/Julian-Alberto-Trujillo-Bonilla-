package com.MediStock.MediStock.suppliers.interfaces.rest;

import com.MediStock.MediStock.suppliers.domain.model.Supplier;

public record SupplierResponse(Long id, String documentNumber, String name, String phone, String email, String address,
		boolean active) {

	public static SupplierResponse from(Supplier supplier) {
		return new SupplierResponse(supplier.id(), supplier.documentNumber(), supplier.name(), supplier.phone(),
				supplier.email(), supplier.address(), supplier.active());
	}
}
