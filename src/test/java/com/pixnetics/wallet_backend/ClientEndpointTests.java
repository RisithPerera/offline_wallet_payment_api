package com.pixnetics.wallet_backend;

import com.pixnetics.wallet_backend.payment.client.dto.ClientCreationRequest;
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

	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry registry){
		registry.add("spring.datasource.url", container::getJdbcUrl);
	}

	@Test
	void shouldCreateClient() throws Exception {
        ClientCreationRequest request = new ClientCreationRequest("test1", "hello12345", "PUBKEY8921IE2BQHIWDQ8E");

        String json = objectMapper.writeValueAsString(request);

		mockMvc.perform(MockMvcRequestBuilders
				.post("/api/v1/clients")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json)).andExpect(status().isCreated());
	}
}
