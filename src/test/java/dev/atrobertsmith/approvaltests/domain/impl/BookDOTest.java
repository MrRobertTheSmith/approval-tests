package dev.atrobertsmith.approvaltests.domain.impl;

import dev.atrobertsmith.approvaltests.model.Book;
import org.approvaltests.Approvals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

// This test tries out the
class BookDOTest {

    private BookDO testTarget;

    @BeforeEach
    void setUp() {
        testTarget = new BookDO();
    }

    @Test
    void fetchHappyPath() {
        //GIVEN two books have been saved
        testTarget.saveBook(new Book("Cat's Cradle", "K Vonnegut"));
        testTarget.saveBook(new Book("Julian", "G Vidal"));

        //WHEN
        var result = testTarget.fetchBooks();

        //THEN
        Approvals.verify(result);
    }

    @Test
    void saveHappyPath() {
        //GIVEN two books have been saved
        //WHEN
        var result = testTarget.saveBook(new Book("Cat's Cradle", "K Vonnegut"));

        //THEN
        Approvals.verify(result);
    }

}