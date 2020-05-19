package com.demo.springboot.domain.dto;

public class Klient {
    private int idKlienta;
    private String nazwisko;
    private String imie;
    private String kodPocztowy;
    private String miejscowosc;
    private String ulica;
    private String nrDomu;
    private String telefon;
    private String login;
    private String haslo;

    public Klient(int idKlienta, String nazwisko, String imie, String kodPocztowy, String miejscowosc,
                  String ulica, String nrDomu, String telefon, String login, String haslo) {
        this.idKlienta = idKlienta;
        this.nazwisko = nazwisko;
        this.imie = imie;
        this.kodPocztowy = kodPocztowy;
        this.miejscowosc = miejscowosc;
        this.ulica = ulica;
        this.nrDomu = nrDomu;
        this.telefon = telefon;
        this.login = login;
        this.haslo = haslo;
    }

    public int getIdKlienta() {
        return idKlienta;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public String getImie() {
        return imie;
    }

    public String getKod_pocztowy() {
        return kodPocztowy;
    }

    public String getMiejscowosc() {
        return miejscowosc;
    }

    public String getUlica() {
        return ulica;
    }

    public String getNr_domu() {
        return nrDomu;
    }

    public String getTelefon() {
        return telefon;
    }

    public String getLogin() {
        return login;
    }

    public String getHaslo() {
        return haslo;
    }
}

