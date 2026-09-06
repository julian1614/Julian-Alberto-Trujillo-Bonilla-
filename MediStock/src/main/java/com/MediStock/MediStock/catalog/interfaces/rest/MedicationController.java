package com.MediStock.MediStock.catalog.interfaces.rest;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MediStock.MediStock.catalog.application.service.CreateMedicationService;
import com.MediStock.MediStock.catalog.application.service.GetMedicationService;
import com.MediStock.MediStock.catalog.application.service.ListMedicationsService;
import com.MediStock.MediStock.catalog.domain.model.Medication;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/medications")
public class MedicationController {

	private final CreateMedicationService createMedicationService;
	private final ListMedicationsService listMedicationsService;
	private final GetMedicationService getMedicationService;

	public MedicationController(CreateMedicationService createMedicationService,
			ListMedicationsService listMedicationsService, GetMedicationService getMedicationService) {
		this.createMedicationService = createMedicationService;
		this.listMedicationsService = listMedicationsService;
		this.getMedicationService = getMedicationService;
	}

	@PostMapping
	public ResponseEntity<MedicationResponse> create(@Valid @RequestBody MedicationRequest request) {
		Medication medication = createMedicationService.create(new Medication(null, request.code(), request.name(),
				request.activeIngredient(), request.pharmaceuticalForm(), request.concentration(),
				request.unitOfMeasure(), true));
		return ResponseEntity.created(URI.create("/api/medications/" + medication.id()))
			.body(MedicationResponse.from(medication));
	}

	@GetMapping
	public List<MedicationResponse> list() {
		return listMedicationsService.list().stream().map(MedicationResponse::from).toList();
	}

	@GetMapping("/{id}")
	public MedicationResponse get(@PathVariable Long id) {
		return MedicationResponse.from(getMedicationService.get(id));
	}
}
