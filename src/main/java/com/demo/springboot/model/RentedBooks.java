package com.demo.springboot.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class RentedBooks implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "auto_gen")
    @SequenceGenerator(name = "auto_gen", sequenceName = "A")
    @Column(nullable = false, updatable = false)
    private String bookId;

    private String title;
    private String rentalDate;
    private String returnDate;


    public RentedBooks(String bookId, String title, String rentalDate, String returnDate) {
        this.bookId = bookId;
        this.title = title;
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
    }

    public RentedBooks() {

    }


    public String getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getdataWypozyczenia() {
        return rentalDate;
    }

    public String getReturnDate() {
        return returnDate;
    }
}
