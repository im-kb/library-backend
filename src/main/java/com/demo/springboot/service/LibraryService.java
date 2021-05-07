package com.demo.springboot.service;

import com.demo.springboot.model.*;
import com.demo.springboot.repository.*;
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
    private final AuthorRepository authorRepo;
    private final RentalRepository rentedBookRepo;
    private final AdminRepository adminRepo;

    @Autowired
    public LibraryService(BookRepository bookRepo, ClientRepository clientRepo, PublishingHouseRepository publHouseRepo, AuthorRepository authorRepo, RentalRepository rentedBookRepo, AdminRepository adminRepo) {
        this.bookRepo = bookRepo;
        this.clientRepo = clientRepo;
        this.publHouseRepo = publHouseRepo;
        this.authorRepo = authorRepo;
        this.rentedBookRepo = rentedBookRepo;
        this.adminRepo = adminRepo;
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
        return clientRepo.findClientByLoginAndPassword(login, password).orElseThrow(() -> new ClientNotFoundException("Wrong login or password."));
    }

    public Client deleteClientByLoginAndPassword(String login, String password) {
        if (clientRepo.existsByLoginAndPassword(login, password)) {
            Client removedClient = clientRepo.deleteClientByLoginAndPassword(login, password).get(0);
            return removedClient;
        } else {
            throw new ClientNotFoundException("Wrong login or password.");
        }
    }

    //END OF CLIENT SECTION_______________________________________

    //PUBLISHING HOUSE______________________________________
    public List<PublishingHouse> getAllPublishingHouses() {
        return publHouseRepo.findAll();
    }

    public PublishingHouse addPublishingHouse(PublishingHouse publishingHouse) {
        if (publishingHouse.getCity() != null && publishingHouse.getName() != null) {
            return publHouseRepo.save(publishingHouse);
        } else {
            throw new IllegalArgumentException("Illegal argument");
        }

    }

    //AUTHOR______________________________________
    public List<Author> getAllAuthors() {
        return authorRepo.findAll();
    }

    public Author addAuthor(Author author) {
        if (author.getName() != null && author.getNationality() != null && author.getSurname() != null) {
            return authorRepo.save(author);
        } else {
            throw new IllegalArgumentException("Illegal argument");
        }

    }

    //Rentals
    public List<Rental> getAllRentals() {
        return rentedBookRepo.findAll();
    }

    public List<Rental> getRentalByClientLoginAndPassword(String login, String password) {
        if (clientRepo.existsByLoginAndPassword(login, password)) {
            return rentedBookRepo.getRentalByClientLoginAndPassword(login, password);
        } else {
            throw new ClientNotFoundException("Wrong login or password.");
        }
    }

    //ADMIN
    public Boolean adminExistsByLoginAndPassword(String login, String password) {
        if (adminRepo.existsByLoginAndPassword(login, password) == true) {
            return true;
        } else {
            throw new ClientNotFoundException("Wrong login or password.");
        }
    }
}
