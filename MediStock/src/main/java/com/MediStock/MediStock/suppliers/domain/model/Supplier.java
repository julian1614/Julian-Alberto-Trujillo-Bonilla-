package com.MediStock.MediStock.suppliers.domain.model;

import static com.MediStock.MediStock.shared.domain.DomainValidation.requireText;

public class Supplier {

	private final Long id;
	private final String documentNumber;
	private final String name;
	private final String phone;
	private final String email;
	private final String address;
	private final boolean active;

	public Supplier(Long id, String documentNumber, String name, String phone, String email, String address,
			boolean active) {
		this.id = id;
		this.documentNumber = requireText(documentNumber, "supplier document number");
		this.name = requireText(name, "supplier name");
		this.phone = requireText(phone, "supplier phone");
		this.email = requireText(email, "supplier email");
		this.address = requireText(address, "supplier address");
		this.active = active;
	}

	public Long id() {
		return id;
	}

	public String documentNumber() {
		return documentNumber;
	}

	public String name() {
		return name;
	}

	public String phone() {
		return phone;
	}

	public String email() {
		return email;
	}

	public String address() {
		return address;
	}

	public boolean active() {
		return active;
	}
}
