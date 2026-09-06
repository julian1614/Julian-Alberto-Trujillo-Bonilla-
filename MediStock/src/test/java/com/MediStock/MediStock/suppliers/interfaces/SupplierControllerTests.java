package com.MediStock.MediStock.suppliers.interfaces;

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

import com.MediStock.MediStock.shared.interfaces.rest.RestExceptionHandler;
import com.MediStock.MediStock.suppliers.application.service.CreateSupplierService;
import com.MediStock.MediStock.suppliers.application.service.GetSupplierService;
import com.MediStock.MediStock.suppliers.application.service.ListSuppliersService;
import com.MediStock.MediStock.suppliers.infrastructure.persistence.inmemory.InMemorySupplierRepository;
import com.MediStock.MediStock.suppliers.interfaces.rest.SupplierController;

class SupplierControllerTests {

	private MockMvc mockMvc;

	@BeforeEach
	void setUp() {
		InMemorySupplierRepository repository = new InMemorySupplierRepository();
		SupplierController controller = new SupplierController(new CreateSupplierService(repository),
				new ListSuppliersService(repository), new GetSupplierService(repository));
		mockMvc = MockMvcBuilders.standaloneSetup(controller)
			.setControllerAdvice(new RestExceptionHandler())
			.build();
	}

	@Test
	void createsAndRetrievesSupplierThroughHttp() throws Exception {
		String requestBody = """
				{
				  "documentNumber": "900123456",
				  "name": "Distribuidora Salud Total",
				  "phone": "3001234567",
				  "email": "ventas@saludtotal.com",
				  "address": "Calle 10 # 15-20"
				}
				""";

		mockMvc.perform(post("/api/suppliers")
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestBody))
			.andExpect(status().isCreated())
			.andExpect(header().string("Location", "/api/suppliers/1"))
			.andExpect(jsonPath("$.id").value(1))
			.andExpect(jsonPath("$.documentNumber").value("900123456"))
			.andExpect(jsonPath("$.name").value("Distribuidora Salud Total"));

		mockMvc.perform(get("/api/suppliers"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$", hasSize(1)))
			.andExpect(jsonPath("$[0].email").value("ventas@saludtotal.com"));

		mockMvc.perform(get("/api/suppliers/1"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.phone").value("3001234567"));
	}

	@Test
	void rejectsInvalidSupplierRequest() throws Exception {
		String requestBody = """
				{
				  "documentNumber": "900123456",
				  "name": "",
				  "phone": "3001234567",
				  "email": "not-an-email",
				  "address": "Calle 10 # 15-20"
				}
				""";

		mockMvc.perform(post("/api/suppliers")
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestBody))
			.andExpect(status().isBadRequest())
			.andExpect(jsonPath("$.error").value("invalid request body"));
	}
}
