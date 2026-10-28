package com.pixnetics.wallet_backend;

import com.pixnetics.wallet_backend.payment.client.ClientRepository;
import com.pixnetics.wallet_backend.payment.client.dto.ClientCreationRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.cockroachdb.CockroachContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import tools.jackson.databind.ObjectMapper;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class ClientEndpointTests {

	@Container
	static CockroachContainer container = new CockroachContainer("cockroachdb/cockroach:latest-v26.2");

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private ClientRepository clientRepository;

	@DynamicPropertySource
	static void configureProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", container::getJdbcUrl);
		registry.add("spring.datasource.username", container::getUsername);
		registry.add("spring.datasource.password", container::getPassword);
	}

	@Test
	@Order(1)
	void testCreateClient() throws Exception {
        ClientCreationRequest request = new ClientCreationRequest("test1", "hello12345", "PUBKEY8921IE2BQHIWDQ8E");

        String json = objectMapper.writeValueAsString(request);

		var x = mockMvc.perform(MockMvcRequestBuilders
				.post("/api/v1/clients")
				.with(httpBasic("user", "apple"))
				.contentType(MediaType.APPLICATION_JSON)
				.content(json));

		x.andExpect(status().isCreated());

		Assertions.assertEquals(1, clientRepository.findAll().size());
	}

	@Test
	@Order(2)
	void testGetClient() throws Exception {
		//Path variable: .get("/clients/{id}", id)
		//Query parameter: .get("/clients").param("status", "ACTIVE")
		mockMvc.perform(MockMvcRequestBuilders
						.get("/api/v1/clients/{id}", "f745ec3c-a77a-4b1c-8bcb-1ee363d26844")
						.with(httpBasic("user", "apple")))
				.andExpect(status().isOk());
	}
}
