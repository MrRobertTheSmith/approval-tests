package dev.atrobertsmith.approvaltests.domain.impl;

import dev.atrobertsmith.approvaltests.domain.IBookDO;
import dev.atrobertsmith.approvaltests.model.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookDO implements IBookDO {

    private final List<Book> inMemoryStorage;

    public BookDO() {
        this.inMemoryStorage = new ArrayList<>();
    }

    @Override
    public List<Book> fetchBooks() {
        return inMemoryStorage;
    }

    @Override
    public Book saveBook(Book book) {
        inMemoryStorage.add(book);
        return book;
    }
}
