package com.MediStock.MediStock.persistence;

import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class ApiPersistenceIntegrationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void persistsMedicationThroughJpaRepository() throws Exception {
		String requestBody = """
				{
				  "code": "MED-JPA-001",
				  "name": "Ibuprofeno 400 mg",
				  "activeIngredient": "Ibuprofeno",
				  "pharmaceuticalForm": "Tableta",
				  "concentration": "400 mg",
				  "unitOfMeasure": "Caja"
				}
				""";

		mockMvc.perform(post("/api/medications")
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestBody))
			.andExpect(status().isCreated())
			.andExpect(header().exists("Location"))
			.andExpect(jsonPath("$.code").value("MED-JPA-001"));

		mockMvc.perform(get("/api/medications"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$[*].code", hasItem("MED-JPA-001")));
	}

	@Test
	void persistsSupplierThroughJpaRepository() throws Exception {
		String requestBody = """
				{
				  "documentNumber": "JPA-900123456",
				  "name": "Proveedor Clinico Central",
				  "phone": "3011234567",
				  "email": "ventas@clinicocentral.com",
				  "address": "Carrera 12 # 20-15"
				}
				""";

		mockMvc.perform(post("/api/suppliers")
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestBody))
			.andExpect(status().isCreated())
			.andExpect(header().exists("Location"))
			.andExpect(jsonPath("$.documentNumber").value("JPA-900123456"));

		mockMvc.perform(get("/api/suppliers"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$[*].documentNumber", hasItem("JPA-900123456")));
	}
}
