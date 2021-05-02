package com.demo.springboot.domain.dto;

public class ClientDto {
    private String login;
    private String password;
    private String name;
    private String surname;
    private String houseNumber;
    private String phoneNumber;
    private String street;
    private String city;
    private String postalCode;

    public ClientDto(String name, String surname, String city, String street, String houseNumber,
                     String postalCode, String phoneNumber, String login, String password) {
        this.name = name;
        this.surname = surname;
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
        this.postalCode = postalCode;
        this.phoneNumber = phoneNumber;
        this.login = login;
        this.password = password;

    }

    public ClientDto() {
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getPostalCode() {
        return postalCode;
    }


    @Override
    public String toString() {
        return "ClientDto{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", houseNumber='" + houseNumber + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", postalCode='" + postalCode + '\'' +
                '}';
    }


}
