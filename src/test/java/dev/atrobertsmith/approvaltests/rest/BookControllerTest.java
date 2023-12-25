package dev.atrobertsmith.approvaltests.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.atrobertsmith.approvaltests.model.Book;
import org.approvaltests.Approvals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BookControllerTest {

    private ObjectMapper mapper;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mapper = new ObjectMapper();
    }

    @Test
    void saveHappyPath() throws Exception {
        //GIVEN a book
        var book = mapper.writeValueAsString(new Book("Cat's Cradle", "K V"));

        //WHEN we save the book
        var saveResponse = mockMvc.perform(post("/api/v1/book")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(book))
                .andDo(print())
                .andExpect(status().is(200))
                .andReturn();

        //THEN
        Approvals.verify(saveResponse.getResponse().getContentAsString());
    }



    @Test
    void happyPathEndToEnd() throws Exception {
        //GIVEN a book is saved
        var book = mapper.writeValueAsString(new Book("Cat's Cradle", "K V"));

        mockMvc.perform(post("/api/v1/book")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(book))
                .andDo(print())
                .andExpect(status().is(200))
                .andReturn();

        //WHEN we fetch books
        var fetchResponse = mockMvc.perform(get("/api/v1/books"))
                .andDo(print())
                .andExpect(status().is(200))
                .andReturn();

        //THEN
        Approvals.verify(fetchResponse.getResponse().getContentAsString());
    }

}