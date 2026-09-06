package com.MediStock.MediStock.suppliers.interfaces.rest;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record SupplierRequest(
		@NotBlank String documentNumber,
		@NotBlank String name,
		@NotBlank String phone,
		@NotBlank @Email String email,
		@NotBlank String address) {
}
