package com.library.management.service;

import com.library.management.model.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class BookService {
    private final List<Book> books = new ArrayList<>();
    private final AtomicInteger idGenerator = new AtomicInteger(1);

    public BookService() {
        // Add some sample books
        books.add(new Book(idGenerator.getAndIncrement(), "The Great Gatsby", "F. Scott Fitzgerald", "Fiction", 5));
        books.add(new Book(idGenerator.getAndIncrement(), "To Kill a Mockingbird", "Harper Lee", "Fiction", 3));
        books.add(new Book(idGenerator.getAndIncrement(), "1984", "George Orwell", "Dystopian", 4));
        books.add(new Book(idGenerator.getAndIncrement(), "Clean Code", "Robert C. Martin", "Programming", 2));
        books.add(new Book(idGenerator.getAndIncrement(), "Design Patterns", "Gang of Four", "Programming", 1));
    }

    public List<Book> getAllBooks() {
        return new ArrayList<>(books);
    }

    public Optional<Book> getBookById(Integer id) {
        return books.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst();
    }

    public Book addBook(Book book) {
        book.setId(idGenerator.getAndIncrement());
        books.add(book);
        return book;
    }

    public Optional<Book> updateBook(Integer id, Book updatedBook) {
        return books.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst()
                .map(existingBook -> {
                    existingBook.setTitle(updatedBook.getTitle());
                    existingBook.setAuthor(updatedBook.getAuthor());
                    existingBook.setCategory(updatedBook.getCategory());
                    existingBook.setAvailableCopies(updatedBook.getAvailableCopies());
                    return existingBook;
                });
    }

    public boolean deleteBook(Integer id) {
        return books.removeIf(book -> book.getId().equals(id));
    }
}