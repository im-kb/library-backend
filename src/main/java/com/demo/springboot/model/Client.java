package com.demo.springboot.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Client implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id", nullable = false, updatable = false)
    private Long clientId;

    private String login;
    private String password;
    private String name;
    private String surname;

    @Column(name = "house_number")
    private String houseNumber;

    @Column(name = "phone_number")
    private String phoneNumber;
    private String street;
    private String city;

    @Column(name = "zip_code")
    private String zipCode;

    public Client(String name, String surname, String city, String street, String houseNumber,
                  String zipCode, String phoneNumber, String login, String password) {
        this.name = name;
        this.surname = surname;
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
        this.zipCode = zipCode;
        this.phoneNumber = phoneNumber;
        this.login = login;
        this.password = password;

    }

    public Client() {
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

    public String getZipCode() {
        return zipCode;
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
                ", postalCode='" + zipCode + '\'' +
                '}';
    }
}
