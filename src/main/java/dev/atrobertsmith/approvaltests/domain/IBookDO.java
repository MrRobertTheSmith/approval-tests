package dev.atrobertsmith.approvaltests.domain;

import dev.atrobertsmith.approvaltests.model.Book;

import java.util.List;

public interface IBookDO {

    List<Book> fetchBooks();

    Book saveBook(Book book);
}
