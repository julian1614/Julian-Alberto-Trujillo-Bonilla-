package com.MediStock.MediStock.suppliers.infrastructure.persistence.jpa;

import com.MediStock.MediStock.suppliers.domain.model.Supplier;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "suppliers")
public class SupplierJpaEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true, length = 40)
	private String documentNumber;

	@Column(nullable = false, length = 120)
	private String name;

	@Column(nullable = false, length = 30)
	private String phone;

	@Column(nullable = false, length = 120)
	private String email;

	@Column(nullable = false, length = 160)
	private String address;

	@Column(nullable = false)
	private boolean active;

	protected SupplierJpaEntity() {
	}

	private SupplierJpaEntity(Long id, String documentNumber, String name, String phone, String email,
			String address, boolean active) {
		this.id = id;
		this.documentNumber = documentNumber;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.active = active;
	}

	public static SupplierJpaEntity fromDomain(Supplier supplier) {
		return new SupplierJpaEntity(supplier.id(), supplier.documentNumber(), supplier.name(), supplier.phone(),
				supplier.email(), supplier.address(), supplier.active());
	}

	public Supplier toDomain() {
		return new Supplier(id, documentNumber, name, phone, email, address, active);
	}
}
