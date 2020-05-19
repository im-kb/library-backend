package com.demo.springboot.domain.dto;

public class Ksiazka {
    private String idKsiazki;
    private String tytul;
    private String idAutora;
    private String wydawnictwo;
    private String temat;
    private String jezykKsiazki;
    private String rokWydania;
    private String  dostepnosc;

    public Ksiazka(String  idKsiazki, String tytul, String  idAutora, String wydawnictwo, String temat,
                   String jezykKsiazki, String rokWydania, String dostepnosc) {
        this.idKsiazki = idKsiazki;
        this.tytul = tytul;
        this.idAutora = idAutora;
        this.wydawnictwo = wydawnictwo;
        this.temat = temat;
        this.jezykKsiazki = jezykKsiazki;
        this.rokWydania = rokWydania;
        this.dostepnosc = dostepnosc;
    }

    public String idKsiazki() {
        return idKsiazki;
    }

    public String tytul() {
        return tytul;
    }

    public String idAutora() {
        return idAutora;
    }

    public String wydawnictwo() {
        return wydawnictwo;
    }

    public String temat() {
        return temat;
    }

    public String jezykKsiazki() {
        return jezykKsiazki;
    }

    public String rokWydania() {
        return rokWydania;
    }

    public String dostepnosc() {
        return dostepnosc;
    }
}
