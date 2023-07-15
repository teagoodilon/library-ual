package br.com.compass.pb.libraryual.controller;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
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
    @DisplayName("Should return 400 when invalid request body")
    void shouldReturn400WhenInvalidRequestBody() throws Exception {
        String requestBody = "{\"name\": \"\"}"; // Invalid name

        mockMvc.perform(post("/api/publishingCompany/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest());
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
    @DisplayName("Should return 404 when publishingCompany not found")
    void shouldReturn404WhenAuthorNotFound() throws Exception {
        mockMvc.perform(get("/api/publishingCompany/100"))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Should update publishing company")
    @Transactional
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
    @DirtiesContext
    void shouldDeletePublishingCompany() throws Exception {
        mockMvc.perform(delete("/api/publishingCompany/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("Deleted successfully"));
    }
}

