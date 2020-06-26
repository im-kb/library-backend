package com.demo.springboot.domain.dto;



public class KlientData {
    private String login;
    private String haslo;
    private String imie;
    private String nazwisko;
    private String nrDomu;
    private String telefon;
    private String ulica;
    private String miejscowosc;
    private String kodPocztowy;

    public KlientData(String imie, String nazwisko, String miejscowosc, String ulica, String nrDomu,
                      String kodPocztowy, String telefon, String login, String haslo) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.miejscowosc = miejscowosc;
        this.ulica = ulica;
        this.nrDomu = nrDomu;
        this.kodPocztowy = kodPocztowy;
        this.telefon = telefon;
        this.login = login;
        this.haslo = haslo;

    }

    public KlientData() {
    }

    public String getLogin() {
        return login;
    }

    public String getHaslo() {
        return haslo;
    }

    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public String getNrDomu() {
        return nrDomu;
    }

    public String getTelefon() {
        return telefon;
    }

    public String getUlica() {
        return ulica;
    }

    public String getMiejscowosc() {
        return miejscowosc;
    }

    public String getKodPocztowy() {
        return kodPocztowy;
    }

    @Override
    public String toString() {
        return "{\"KlientData\":{"
                + "\"login\":\"" + login + ""
                + ", \"haslo\":\"" + haslo + "\""
                + ", \"imie:\":\"" + imie + "\""
                + ", \"nazwisko:\":\"" + nazwisko + "\""
                + ", \"nrDomu:\":\"" + nrDomu + "\""
                + ", \"telefon:\":\"" + telefon + "\""
                + ", \"ulica:\":\"" + ulica + "\""
                + ", \"miejscowosc:\":\"" + miejscowosc + "\""
                + ", \"kodPocztowy:\":\"" + kodPocztowy + "\""
                + "}}";
    }
}
