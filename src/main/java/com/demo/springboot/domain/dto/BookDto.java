package com.demo.springboot.domain.dto;

public class BookDto {
    private String bookId;
    private String title;
    private String author;
    private String publishingHouse;
    private String topic;
    private String bookLanguage;
    private String publicationDate;
    private String availability;
    private String description;

    private String authorName;
    private String authorSurname;

    public BookDto(String bookId, String title, String author, String publishingHouse, String topic,
                   String bookLanguage, String publicationDate, String availability, String description) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.publishingHouse = publishingHouse;
        this.topic = topic;
        this.bookLanguage = bookLanguage;
        this.publicationDate = publicationDate;
        this.availability = availability;
        this.description = description;
    }

    public BookDto() {
    }


    public String getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublishingHouse() {
        return publishingHouse;
    }

    public String getTopic() {
        return topic;
    }

    public String getBookLanguage() {
        return bookLanguage;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public String getAvailability() {
        return availability;
    }

    public String getDescription() {
        return description;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getAuthorSurname() {
        return authorSurname;
    }
}
