package br.com.compass.pb.libraryual.controller;

import br.com.compass.pb.libraryual.controller.PublishingCompanyController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PublishingCompanyControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    @Test
    @DisplayName("Should insert publishing company")
    void shouldInsertPublishingCompany() throws Exception {
        String requestBody = "{\"name\": \"Pearson Education\"}";

        mockMvc.perform(post("/api/publishingCompany/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Pearson Education"));
    }

    @Test
    @DisplayName("Should return all publishing companies")
    void shouldReturnAllPublishingCompanies() throws Exception {
        mockMvc.perform(get("/api/publishingCompany/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Pearson Education"));
    }

    @Test
    @DisplayName("Should return publishing company by id")
    void shouldReturnPublishingCompanyById() throws Exception {
        mockMvc.perform(get("/api/publishingCompany/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Pearson Education"));
    }

    @Test
    @DisplayName("Should update publishing company")
    void shouldUpdatePublishingCompany() throws Exception {
        String requestBody = "{\"name\": \"Updated\"}";

        mockMvc.perform(put("/api/publishingCompany/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Updated"));
    }

    @Test
    @DisplayName("Should delete publishing company")
    void shouldDeletePublishingCompany() throws Exception {
        mockMvc.perform(delete("/api/publishingCompany/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("Deleted successfully"));
    }
}

