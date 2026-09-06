package com.MediStock.MediStock.catalog.interfaces;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.MediStock.MediStock.catalog.application.service.CreateMedicationService;
import com.MediStock.MediStock.catalog.application.service.GetMedicationService;
import com.MediStock.MediStock.catalog.application.service.ListMedicationsService;
import com.MediStock.MediStock.catalog.infrastructure.persistence.inmemory.InMemoryMedicationRepository;
import com.MediStock.MediStock.catalog.interfaces.rest.MedicationController;
import com.MediStock.MediStock.shared.interfaces.rest.RestExceptionHandler;

class MedicationControllerTests {

	private MockMvc mockMvc;

	@BeforeEach
	void setUp() {
		InMemoryMedicationRepository repository = new InMemoryMedicationRepository();
		MedicationController controller = new MedicationController(new CreateMedicationService(repository),
				new ListMedicationsService(repository), new GetMedicationService(repository));
		mockMvc = MockMvcBuilders.standaloneSetup(controller)
			.setControllerAdvice(new RestExceptionHandler())
			.build();
	}

	@Test
	void createsAndRetrievesMedicationThroughHttp() throws Exception {
		String requestBody = """
				{
				  "code": "MED-001",
				  "name": "Acetaminofen 500 mg",
				  "activeIngredient": "Acetaminofen",
				  "pharmaceuticalForm": "Tableta",
				  "concentration": "500 mg",
				  "unitOfMeasure": "Caja"
				}
				""";

		mockMvc.perform(post("/api/medications")
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestBody))
			.andExpect(status().isCreated())
			.andExpect(header().string("Location", "/api/medications/1"))
			.andExpect(jsonPath("$.id").value(1))
			.andExpect(jsonPath("$.code").value("MED-001"))
			.andExpect(jsonPath("$.name").value("Acetaminofen 500 mg"));

		mockMvc.perform(get("/api/medications"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$", hasSize(1)))
			.andExpect(jsonPath("$[0].activeIngredient").value("Acetaminofen"));

		mockMvc.perform(get("/api/medications/1"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.concentration").value("500 mg"));
	}

	@Test
	void rejectsInvalidMedicationRequest() throws Exception {
		String requestBody = """
				{
				  "code": "",
				  "name": "Acetaminofen 500 mg",
				  "activeIngredient": "Acetaminofen",
				  "pharmaceuticalForm": "Tableta",
				  "concentration": "500 mg",
				  "unitOfMeasure": "Caja"
				}
				""";

		mockMvc.perform(post("/api/medications")
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestBody))
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.error").value("invalid request body"));
	}
}
