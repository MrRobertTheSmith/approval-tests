package dev.atrobertsmith.approvaltests.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.atrobertsmith.approvaltests.domain.IBookDO;
import dev.atrobertsmith.approvaltests.model.Book;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.slf4j.LoggerFactory.getLogger;

@RestController
public class BookController {
    private static final Logger LOGGER = getLogger(BookController.class);

    private final ObjectMapper mapper;
    private final IBookDO iBookDO;

    public BookController(IBookDO iBookDO) {
        this.mapper = new ObjectMapper();
        this.iBookDO = iBookDO;
    }

    @PostMapping("/api/v1/book")
    ResponseEntity<String> post(@RequestBody Book book) {
        LOGGER.info("Received: {}", book);

        var saved = iBookDO.saveBook(book);

        try {
            return new ResponseEntity<>(mapper.writeValueAsString(saved), HttpStatus.OK);
        } catch (JsonProcessingException e) {
            LOGGER.warn("{}", e.getMessage());
            return new ResponseEntity<>("Internal Server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/api/v1/books")
    ResponseEntity<String> get() {
        var books = iBookDO.fetchBooks();

        LOGGER.info("Fetching: {}", books);

        try {
            return new ResponseEntity<>(mapper.writeValueAsString(books), HttpStatus.OK);
        } catch (JsonProcessingException e) {
            LOGGER.warn("{}", e.getMessage());
            return new ResponseEntity<>("Internal Server error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
