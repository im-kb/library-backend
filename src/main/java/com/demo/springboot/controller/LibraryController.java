package com.demo.springboot.controller;

import com.demo.springboot.model.Book;
import com.demo.springboot.model.Client;
import com.demo.springboot.service.LibraryService;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/library")

public class LibraryController {
    private final LibraryService libraryService;

    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @GetMapping("/books/all")
    public ResponseEntity<List<Book>> getAllUsers() {
        List<Book> books = libraryService.getAllBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping("/books/{bookId}")
    public ResponseEntity<Book> getBookById(@PathVariable("bookId") Long bookId) {
        Book book = libraryService.findBookByBookId(bookId);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @GetMapping(
            value = "/image/{id}",
            produces = MediaType.IMAGE_JPEG_VALUE

    )
    public @ResponseBody
    byte[] getImageWithMediaType(@PathVariable Integer id) throws IOException {
        InputStream in = getClass()
                .getResourceAsStream("/imgs/" + id + ".jpg");
        return IOUtils.toByteArray(in);
    }

    //CLIENT SECTION_______________________________________
    @GetMapping("/client")
    public ResponseEntity<Client> getClientData(@RequestParam(value = "login", required = true) String login, @RequestParam(value = "password", required = true) String password) {
        Client client = libraryService.findClientByLoginAndPassword(login, password);
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @PostMapping("/client/register")
    public ResponseEntity<Client> addClient(@RequestBody Client client) {
        Client newClient = libraryService.addClient(client);
        return new ResponseEntity<>(newClient, HttpStatus.CREATED);
    }

    @PutMapping("/client/update")
    public ResponseEntity<Client> updateClient(@RequestBody Client client) {
        Client updatedClient = libraryService.updateClient(client);
            return new ResponseEntity<>(updatedClient, HttpStatus.OK);
    }

    @PostMapping(value = "/client/login")
    public ResponseEntity<HttpStatus> login(@RequestParam(value = "login", required = true) String login, @RequestParam(value = "password", required = true) String password) {
        if (libraryService.existsByLoginAndPassword(login, password) == false) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } else {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
    //End of CLIENT__________________________________________
}
