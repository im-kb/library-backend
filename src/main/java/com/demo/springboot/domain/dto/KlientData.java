package com.demo.springboot.domain.dto;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class KlientData {
    private int idKlienta;
    private String login;
    private String haslo;
    private String imie;
    private String nazwisko;
    private String nrDomu;
    private String telefon;
    private String ulica;
    private String miejscowosc;
    private String kodPocztowy;

    public KlientData() { }

    public int getIdKlienta() {return idKlienta;}
    public String getLogin() {return login;}
    public String getHaslo() { return haslo;}
    public String getImie() { return imie;}
    public String getNazwisko() { return nazwisko;}
    public String getNrDomu() { return nrDomu;}
    public String getTelefon() { return telefon;}
    public String getUlica() { return ulica;}
    public String getMiejscowosc() { return miejscowosc;}
    public String getKodPocztowy() { return kodPocztowy;}
    @Override
    public String toString() {
        return "{\"KlientData\":{"
                + "\"idKlienta\":\"" + idKlienta + ""
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
