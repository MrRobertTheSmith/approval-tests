package dev.atrobertsmith.approvaltests.rest;

import dev.atrobertsmith.approvaltests.model.Book;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.slf4j.LoggerFactory.getLogger;

@RestController
public class BookController {
    private static final Logger LOGGER = getLogger(BookController.class);

    @PostMapping("/api/v1/book")
    ResponseEntity<String> post(@RequestBody Book book) {
        LOGGER.info("{}", book);

        return ResponseEntity.ok().build();
    }
}
