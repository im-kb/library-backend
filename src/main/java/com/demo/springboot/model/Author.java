package com.demo.springboot.model;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Author implements Serializable {

//    @OneToMany(targetEntity = Book.class, mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    public List<Book> book =new ArrayList<>();
//
//    public void setBook(List<Book> book) {
//        this.book = book;
//    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="author_id", nullable = false, updatable = false)
    private Long authorId;
    private String surname;
    private String name;
    private String nationality;

    @Column(name="publication_period")
    private String publicationPeriod;

    @Column(name="writing_language")
    private String writingLanguage;


    public Author() { }

    public Author(String surname, String name, String nationality, String publicationPeriod, String writingLanguage) {
        this.surname = surname;
        this.name = name;
        this.nationality = nationality;
        this.publicationPeriod = publicationPeriod;
        this.writingLanguage = writingLanguage;
    }
    public Long getAuthorId() { return authorId;}
    public String getSurname() {return surname;}
    public String getName() { return name;}
    public String getNationality() { return nationality;}
    public String getPublicationPeriod() { return publicationPeriod;}
    public String getWritingLanguage() { return writingLanguage;}


    @Override
    public String toString() {
        return "AuthorData{" +
                "author_id='" + authorId + '\'' +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", nationality='" + nationality + '\'' +
                ", creation_period='" + publicationPeriod + '\'' +
                ", language='" + writingLanguage + '\'' +
                '}';
    }
}
