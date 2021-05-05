package com.demo.springboot.service;

import com.demo.springboot.model.Book;
import com.demo.springboot.model.Client;
import com.demo.springboot.repository.BookRepository;
import com.demo.springboot.repository.ClientRepository;
import exception.BookNotFoundException;
import exception.ClientNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibraryService {
    private final BookRepository bookRepo;

    @Autowired
    public LibraryService(BookRepository bookRepo, ClientRepository clientRepo) {
        this.bookRepo = bookRepo;
        this.clientRepo = clientRepo;
    }

    private final ClientRepository clientRepo;

//    public Book addBook(Book book) {
//        return bookRepo.save(book);
//    }

    public Client addClient(Client client) {
        return clientRepo.save(client);
    }

    public Client updateClient(Client client) {
        return clientRepo.save(client);
    }

    public Book findBookByBookId(Long bookId) {
        return bookRepo.findBookByBookId(bookId).orElseThrow(() -> new BookNotFoundException("Book with id: " + bookId + "not found"));
    }

    public List<Book> getAllBooks() {
        return bookRepo.findAll();
    }

    public boolean existsByLoginAndPassword(String login, String password) {
        return clientRepo.existsByLoginAndPassword(login, password);
    }

    public Client findClientByLoginAndPassword(String login, String password) {
        return clientRepo.findClientByLoginAndPassword(login, password).orElseThrow(() -> new ClientNotFoundException("User with login: " + login + "not found"));
    }
}
