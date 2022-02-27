package br.com.renan.project;

import br.com.renan.project.domain.entity.Shoe;
import br.com.renan.project.domain.service.ShoeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ProjectApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private ShoeService service;

	@Test
	void case1() throws Exception {
		Shoe shoe = new Shoe(45, "Tenis", "Preto", 352.0, "Nike", 20L, "Muito confortavel e estiloso", LocalDate.now());

		mockMvc.perform(post("/v1/shoe/new")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(shoe)))
				.andExpect(status().isCreated());
	}

}
