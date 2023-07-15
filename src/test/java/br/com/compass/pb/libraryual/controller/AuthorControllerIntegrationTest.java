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
class AuthorControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    @Test
    @DisplayName("Should insert author")
    void shouldInsertAuthor() throws Exception {
        String requestBody = "{\"name\": \"Jane Austen\"}";
        mockMvc.perform(post("/api/author/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Jane Austen"));
    }

    @Test
    @DisplayName("Should return 400 when invalid request body")
    void shouldReturn400WhenInvalidRequestBody() throws Exception {
        String requestBody = "{\"name\": \"\"}"; // Invalid name
        mockMvc.perform(post("/api/author/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("Should return all authors")
    void shouldReturnAllAuthors() throws Exception {
        mockMvc.perform(get("/api/author/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Jane Austen"));
    }

    @Test
    @DisplayName("Should return author by id")
    void shouldReturnAuthorById() throws Exception {
        mockMvc.perform(get("/api/author/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Jane Austen"));
    }

    @Test
    @DisplayName("Should return 404 when author not found")
    void shouldReturn404WhenAuthorNotFound() throws Exception {
        mockMvc.perform(get("/api/author/100"))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Should update author")
    @Transactional
    void shouldUpdateAuthor() throws Exception {
        String requestBody = "{\"name\": \"Updated\"}";
        mockMvc.perform(put("/api/author/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Updated"));
    }

    @Test
    @DisplayName("Should delete author")
    @DirtiesContext
    void shouldDeleteAuthor() throws Exception {
        mockMvc.perform(delete("/api/author/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("Deleted successfully"));
    }
}

