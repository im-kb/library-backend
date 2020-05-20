package com.demo.springboot.domain.dto;

public class Ksiazka {
    private String idKsiazki;
    private String tytul;
    private String idAutora;
    private String idWydawnictwa;
    private String temat;
    private String jezykKsiazki;
    private String rokWydania;
    private String dostepnosc;

    public Ksiazka(String idKsiazki, String tytul, String idAutora, String idWydawnictwa, String temat,
                   String jezykKsiazki, String rokWydania, String dostepnosc) {
        this.idKsiazki = idKsiazki;
        this.tytul = tytul;
        this.idAutora = idAutora;
        this.idWydawnictwa = idWydawnictwa;
        this.temat = temat;
        this.jezykKsiazki = jezykKsiazki;
        this.rokWydania = rokWydania;
        this.dostepnosc = dostepnosc;
    }

    public String getIdKsiazki() {
        return idKsiazki;
    }

    public String getTytul() {
        return tytul;
    }

    public String getIdAutora() {
        return idAutora;
    }

    public String getIdWydawnictwa() {
        return idWydawnictwa;
    }

    public String getTemat() {
        return temat;
    }

    public String getJezykKsiazki() {
        return jezykKsiazki;
    }

    public String getRokWydania() {
        return rokWydania;
    }

    public String getDostepnosc() {
        return dostepnosc;
    }

    public String toString() {
        return "{\"KSIAZKA::\":{"
                + "\"tytul\":\"" + tytul + ""
                + ", \"autor\":\"" + idAutora + "\""
                + ", \"temat\":\"" + temat + "\""
                + "}}" + "\n";
    }
}
