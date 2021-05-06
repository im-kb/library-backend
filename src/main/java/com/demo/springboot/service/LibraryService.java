package com.demo.springboot.service;

import com.demo.springboot.model.Book;
import com.demo.springboot.model.Client;
import com.demo.springboot.model.PublishingHouse;
import com.demo.springboot.repository.BookRepository;
import com.demo.springboot.repository.ClientRepository;
import com.demo.springboot.repository.PublishingHouseRepository;
import exception.BookNotFoundException;
import exception.ClientNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibraryService {
    private final BookRepository bookRepo;
    private final ClientRepository clientRepo;
    private final PublishingHouseRepository publHouseRepo;

    @Autowired
    public LibraryService(BookRepository bookRepo, ClientRepository clientRepo, PublishingHouseRepository publHouseRepo) {
        this.bookRepo = bookRepo;
        this.clientRepo = clientRepo;
        this.publHouseRepo = publHouseRepo;
    }

    //BOOK SECTION_______________________________________
    public Book findBookByBookId(Long bookId) {
        return bookRepo.findBookByBookId(bookId).orElseThrow(() -> new BookNotFoundException("Book with id: " + bookId + " not found"));
    }

    public List<Book> getAllBooks() {
        return bookRepo.findAll();
    }


    public Book addBook(Book book) {
        return bookRepo.save(book);
    }


    public Book updateBook(Book book) {
        return bookRepo.save(book);
    }

    //END OF BOOK SECTION_______________________________________


    //CLIENT SECTION_______________________________________
    public Client addClient(Client client) {
        return clientRepo.save(client);
    }

    public Client updateClient(Client client) {
        return clientRepo.save(client);
    }


    public boolean existsByLoginAndPassword(String login, String password) {
        return clientRepo.existsByLoginAndPassword(login, password);
    }

    public Client findClientByLoginAndPassword(String login, String password) {
        return clientRepo.findClientByLoginAndPassword(login, password).orElseThrow(() -> new ClientNotFoundException("Client with login: " + login + " not found"));
    }

    public Client deleteClientByLoginAndPassword(String login, String password) {
        if (clientRepo.existsByLoginAndPassword(login, password)) {
            Client removedClient = clientRepo.deleteClientByLoginAndPassword(login, password).get(0);
            return removedClient;
        } else {
            throw new ClientNotFoundException("Client with login: " + login + " not found");
        }
    }

    //END OF CLIENT SECTION_______________________________________

    //PUBLISHING HOUSE______________________________________
    public List<PublishingHouse> getAllPublishingHouses() {
        return publHouseRepo.findAll();
    }

    public PublishingHouse addPublishingHouse(PublishingHouse publishingHouse) {
        if (publishingHouse.getCity() != null &&publishingHouse.getName()!=null) {
            return publHouseRepo.save(publishingHouse);
        } else {
            throw new IllegalArgumentException("Illegal argument");
        }

    }
    //END OF PUBLISHING HOUSE SECTION_______________________________________

}
