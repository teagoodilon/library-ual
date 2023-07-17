package br.com.compass.pb.libraryual.controller;
import br.com.compass.pb.libraryual.domain.dto.BookDTO;
import br.com.compass.pb.libraryual.domain.entity.Author;
import br.com.compass.pb.libraryual.domain.entity.Genre;
import br.com.compass.pb.libraryual.domain.entity.PublishingCompany;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc

public class BookControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    private Long bookId;

    @Autowired
    private ObjectMapper objectMapper;

    private BookDTO createBookDTO(){
        Author author = new Author();
        author.setId(1L);

        Genre genre = new Genre();
        genre.setId(1L);

        PublishingCompany publishingCompany = new PublishingCompany();
        publishingCompany.setId(1L);

        BookDTO bookDTO = new BookDTO();

        bookDTO.setId(1L);
        bookDTO.setTitle("TestBook");
        bookDTO.setNumPages(250);
        bookDTO.setYear(2003);
        bookDTO.setEdition("Standard");
        bookDTO.setPublishingCompany(publishingCompany);
        bookDTO.setGenre(genre);
        bookDTO.setAuthor(author);

        return bookDTO;
    }

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

    @DisplayName("Should insert genre")
    void shouldInsertGenre() throws Exception {
        String requestBody = "{\"name\": \"Fiction\"}";
        mockMvc.perform(post("/api/genre/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Fiction"));
    }

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

    @BeforeEach
    @Test
    @DisplayName("Should insert book")
    void insertShouldReturnBookDTOCreated() throws Exception{
        this.shouldInsertAuthor();
        this.shouldInsertGenre();
        this.shouldInsertPublishingCompany();
        BookDTO bookDTO = createBookDTO();
        String jsonBody = objectMapper.writeValueAsString(bookDTO);
        ResultActions result = mockMvc.perform(post("/api/book/").content(jsonBody).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.id").value(1));
        result.andExpect(jsonPath("$.title").value("TestBook"));
        result.andExpect(jsonPath("$.numPages").value(250));
        result.andExpect(jsonPath("$.year").value(2003));
        result.andExpect(jsonPath("$.edition").value("Standard"));
        result.andExpect(jsonPath("$.publishingCompany.id").value(1));
        result.andExpect(jsonPath("$.genre.id").value(1));
        result.andExpect(jsonPath("$.author.id").value(1));
    }

    @Test
    @DisplayName("Should return the book result with ID")
    void findBookShouldReturnWhenParamIsNotEmpty() throws Exception {
        ResultActions result = mockMvc.perform(get("/api/book/{bookId}", bookId).accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.[0].id").value(1L));
        result.andExpect(jsonPath("$.[0].title").value("TestBook"));
        result.andExpect(jsonPath("$.[0].numPages").value(250));
        result.andExpect(jsonPath("$.[0].year").value(2003));
        result.andExpect(jsonPath("$.[0].edition").value("Standard"));
        result.andExpect(jsonPath("$.[0].author.id").value(1L));
        result.andExpect(jsonPath("$.[0].genre.id").value(1L));
        result.andExpect(jsonPath("$.[0].publishingCompany.id").value(1L));
    }

    @Test
    @DisplayName("Should return the book result without parameter")
    void findBookShouldReturnWhenParamIsEmpty() throws Exception {
        ResultActions result = mockMvc.perform(get("/api/book/").accept(MediaType.APPLICATION_JSON));
        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.[0].id").value(1L));
        result.andExpect(jsonPath("$.[0].title").value("TestBook"));
        result.andExpect(jsonPath("$.[0].numPages").value(250));
        result.andExpect(jsonPath("$.[0].year").value(2003));
        result.andExpect(jsonPath("$.[0].edition").value("Standard"));
        result.andExpect(jsonPath("$.[0].author.id").value(1L));
        result.andExpect(jsonPath("$.[0].genre.id").value(1L));
        result.andExpect(jsonPath("$.[0].publishingCompany.id").value(1L));
    }

    @Test
    @Transactional
    @DisplayName("Should update book with the ID")
    void updateShouldReturnBookDTOUpdated() throws Exception{
        BookDTO bookDTO = createBookDTO();
        bookDTO.setTitle("TestBookUpdated");

        String jsonBody = objectMapper.writeValueAsString(bookDTO);
        ResultActions result = mockMvc.perform(put("/api/book/1").content(jsonBody).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andDo(MockMvcResultHandlers.print());
        result.andExpect(status().isOk());
        result.andExpect(jsonPath("$.id").value(1));
        result.andExpect(jsonPath("$.title").value("TestBookUpdated"));
        result.andExpect(jsonPath("$.numPages").value(250));
        result.andExpect(jsonPath("$.year").value(2003));
        result.andExpect(jsonPath("$.edition").value("Standard"));
        result.andExpect(jsonPath("$.publishingCompany.id").value(1));
        result.andExpect(jsonPath("$.genre.id").value(1));
        result.andExpect(jsonPath("$.author.id").value(1));

    }

    @Test
    @DisplayName("Should return status Method Not Allowed 405 when update is without parameter")
    @DirtiesContext
    void shouldReturnStatus405WhenUpdateIsWithoutParameter() throws Exception {
        mockMvc.perform(put("/api/book/"))
                .andExpect(status().isMethodNotAllowed())
                .andExpect(jsonPath("$.detail").value("The requested HTTP method 'PUT' is not supported. Supported methods are: GET, POST"));
    }

    @Test
    @DisplayName("Should delete book")
    @DirtiesContext
    void shouldDeleteBook() throws Exception {
        this.insertShouldReturnBookDTOCreated();
        mockMvc.perform(delete("/api/book/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("Deleted successfully"));
    }

    @Test
    @DisplayName("Should return status Method Not Allowed 405 when delete is without parameter")
    @DirtiesContext
    void shouldReturnStatus405WhenDeleteIsWithoutParameter() throws Exception {
        mockMvc.perform(delete("/api/book/"))
                .andExpect(status().isMethodNotAllowed())
        .andExpect(jsonPath("$.detail").value("The requested HTTP method 'DELETE' is not supported. Supported methods are: GET, POST"));
    }

    @Test
    @DisplayName("Should return Status Bad Request 400 when Publishing Company foreign key are nonexistent")
    void insertShouldReturnBook404StatusWhenConstraintPublishingCompanyIdAreNonexistent() throws Exception{
        this.shouldInsertAuthor();
        this.shouldInsertGenre();
        this.shouldInsertPublishingCompany();
        BookDTO bookDTO = createBookDTO();
        bookDTO.getPublishingCompany().setId(10L);
        String jsonBody = objectMapper.writeValueAsString(bookDTO);
        ResultActions result = mockMvc.perform(post("/api/book/").content(jsonBody).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andDo(MockMvcResultHandlers.print());
        result.andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.detail").value("publishingCompany : Invalid publishing company ID"));
    }

    @Test
    @DisplayName("Should return Status Bad Request 400 when Genre foreign key are nonexistent")
    void insertShouldReturnBook404StatusWhenConstraintGenreIdAreNonexistent() throws Exception{
        this.shouldInsertAuthor();
        this.shouldInsertGenre();
        this.shouldInsertPublishingCompany();
        BookDTO bookDTO = createBookDTO();
        bookDTO.getGenre().setId(10L);
        String jsonBody = objectMapper.writeValueAsString(bookDTO);
        ResultActions result = mockMvc.perform(post("/api/book/").content(jsonBody).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andDo(MockMvcResultHandlers.print());
        result.andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.detail").value("genre : Invalid genre ID"));
    }

    @Test
    @DisplayName("Should return Status Bad Request 400 when Author foreign key are nonexistent")
    void insertShouldReturnBook404StatusWhenConstraintAuthorIdAreNonexistent() throws Exception{
        this.shouldInsertAuthor();
        this.shouldInsertGenre();
        this.shouldInsertPublishingCompany();
        BookDTO bookDTO = createBookDTO();
        bookDTO.getAuthor().setId(10L);
        String jsonBody = objectMapper.writeValueAsString(bookDTO);
        ResultActions result = mockMvc.perform(post("/api/book/").content(jsonBody).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andDo(MockMvcResultHandlers.print());
        result.andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.detail").value("author : Invalid author ID"));
    }

    @Test
    @DisplayName("Should return Status Bad Request 400 when Title parameter is empty")
    void insertShouldReturnBook404StatusWhenTitleParameterIsEmpty() throws Exception{
        this.shouldInsertAuthor();
        this.shouldInsertGenre();
        this.shouldInsertPublishingCompany();
        BookDTO bookDTO = createBookDTO();
        bookDTO.setTitle("");
        String jsonBody = objectMapper.writeValueAsString(bookDTO);
        ResultActions result = mockMvc.perform(post("/api/book/").content(jsonBody).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andDo(MockMvcResultHandlers.print());
        result.andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.detail").value("title : must not be blank"));
    }

    @Test
    @DisplayName("Should return Status Bad Request 400 when N°Pages paramater is 0 or less")
    void insertShouldReturnBook404StatusWhenNumPagesParameterIsOLess() throws Exception{
        this.shouldInsertAuthor();
        this.shouldInsertGenre();
        this.shouldInsertPublishingCompany();
        BookDTO bookDTO = createBookDTO();
        bookDTO.setNumPages(0);
        String jsonBody = objectMapper.writeValueAsString(bookDTO);
        ResultActions result = mockMvc.perform(post("/api/book/").content(jsonBody).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andDo(MockMvcResultHandlers.print());
        result.andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.detail").value("numPages : must be greater than or equal to 1"));
    }

    @Test
    @DisplayName("Should return Status Bad Request 400 when Year paramater is 0 or less")
    void insertShouldReturnBook404StatusWhenYearParameterIsOLess() throws Exception{
        this.shouldInsertAuthor();
        this.shouldInsertGenre();
        this.shouldInsertPublishingCompany();
        BookDTO bookDTO = createBookDTO();
        bookDTO.setYear(0);
        String jsonBody = objectMapper.writeValueAsString(bookDTO);
        ResultActions result = mockMvc.perform(post("/api/book/").content(jsonBody).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andDo(MockMvcResultHandlers.print());
        result.andExpect(status().isBadRequest())
            .andExpect(MockMvcResultMatchers.jsonPath("$.detail")
            .value(Matchers.either(Matchers.equalTo("year : must be greater than or equal to 1"))
            .or(Matchers.equalTo("year : must be greater than 0")))
        );
    }

    @Test
    @DisplayName("Should return Status Bad Request 400 when Edition parameter is empty")
    void insertShouldReturnBook404StatusWhenEditionParameterIsEmpty() throws Exception{
        this.shouldInsertAuthor();
        this.shouldInsertGenre();
        this.shouldInsertPublishingCompany();
        BookDTO bookDTO = createBookDTO();
        bookDTO.setEdition("");
        String jsonBody = objectMapper.writeValueAsString(bookDTO);
        ResultActions result = mockMvc.perform(post("/api/book/").content(jsonBody).contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andDo(MockMvcResultHandlers.print());
        result.andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.detail").value("edition : must not be blank"));
    }

}
