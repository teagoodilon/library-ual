package br.com.compass.pb.libraryual;

import static org.mockito.Mockito.*;

import br.com.compass.pb.libraryual.domain.dto.BookDTO;
import br.com.compass.pb.libraryual.domain.entity.Author;
import br.com.compass.pb.libraryual.domain.entity.Genre;
import br.com.compass.pb.libraryual.domain.entity.PublishingCompany;
import br.com.compass.pb.libraryual.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class BookControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Test
    public void createBook_ReturnsCreated() throws Exception {

        Author author = new Author();
        author.setId(12L);
        author.setName("John Doe");

        Genre genre = new Genre();
        genre.setId(1L);
        genre.setName("Fiction");

        PublishingCompany publishingCompany = new PublishingCompany();
        publishingCompany.setId(1L);
        publishingCompany.setName("ABC Publishing");

        String requestPayload = "{\n" +
                "    \"title\": \"ABC Example\",\n" +
                "    \"numPages\": 200,\n" +
                "    \"year\": 2006,\n" +
                "    \"edition\": \"A\",\n" +
                "    \"author\": {\"id\": 1},\n" +
                "    \"genre\": {\"id\": 1},\n" +
                "    \"publishingCompany\": {\"id\": 1}\n" +
                "}";

        // Mock service behavior
        when(bookService.insert(any(BookDTO.class))).thenReturn(new BookDTO());

        // Perform the POST request
        mockMvc.perform(MockMvcRequestBuilders.post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestPayload))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }
}

