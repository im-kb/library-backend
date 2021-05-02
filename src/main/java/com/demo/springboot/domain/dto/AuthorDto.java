package com.demo.springboot.domain.dto;


public class AuthorDto {
    private String authorId;
    private String surname;
    private String name;
    private String nationality;
    private String creationPeriod;
    private String language;

    public AuthorDto() { }

    public AuthorDto(String authorId, String surname, String name, String nationality, String creationPeriod, String language) {
        this.authorId = authorId;
        this.surname = surname;
        this.name = name;
        this.nationality = nationality;
        this.creationPeriod = creationPeriod;
        this.language = language;
    }
    public String getAuthorId() { return authorId;}
    public String getSurname() {return surname;}
    public String getName() { return name;}
    public String getNationality() { return nationality;}
    public String getCreationPeriod() { return creationPeriod;}
    public String getLanguage() { return language;}


    @Override
    public String toString() {
        return "AuthorData{" +
                "author_id='" + authorId + '\'' +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", nationality='" + nationality + '\'' +
                ", creation_period='" + creationPeriod + '\'' +
                ", language='" + language + '\'' +
                '}';
    }


}
