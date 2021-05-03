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

    @GetMapping("/book/{bookId}")
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

    @PostMapping("/register")
    public ResponseEntity<Client> addClient(@RequestBody Client client){
        Client newClient =  libraryService.addClient(client);
        return new ResponseEntity<>(newClient, HttpStatus.CREATED);
    }

}
