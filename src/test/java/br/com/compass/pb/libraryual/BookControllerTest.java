package br.com.compass.pb.libraryual;
import br.com.compass.pb.libraryual.domain.entity.Author;
import br.com.compass.pb.libraryual.domain.entity.Genre;
import br.com.compass.pb.libraryual.domain.entity.PublishingCompany;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@AutoConfigureWebClient
public class BookControllerTest {

        @Autowired
        MockMvc mockMvc;

        @Test
        @DisplayName("Should return status code 200 when a book is created")
        void shouldReturnStatusCode200WhenCreateBook() throws Exception {
            Author author = new Author();
            author.setId(1L);
            author.setName("teste");

            Genre genre = new Genre();
            genre.setId(1L);
            genre.setName("teste");

            PublishingCompany publishingCompany = new PublishingCompany();
            publishingCompany.setId(1L);
            publishingCompany.setName("teste");

            String requestBody = "{  \"title\": \"test2\",\n" +
                    "    \"numPages\": 300,\n" +
                    "    \"year\": 2000,\n" +
                    "    \"edition\": \"Standard\",\n" +
                    "    \"author\" : {\"id\":1},\n" +
                    "    \"genre\" : {\"id\":1},\n" +
                    "    \"publishingCompany\" : {\"id\":1}}";


            String responseExpected = "{    \"title\": \"test2\",\n" +
                    "    \"numPages\": 300,\n" +
                    "    \"year\": 2000,\n" +
                    "    \"edition\": \"Standard\",\n" +
                    "    \"author\" : {\"id\":1},\n" +
                    "    \"genre\" : {\"id\":1},\n" +
                    "    \"publishingCompany\" : {\"id\":1}}";

            mockMvc.perform(
                            post("/api/book/")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(requestBody)
                    )
                    .andExpect(status().isCreated())
                    .andExpect(content().json(responseExpected));
        }

}
