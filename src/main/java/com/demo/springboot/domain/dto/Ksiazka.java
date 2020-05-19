package com.demo.springboot.domain.dto;

public class Ksiazka {
    private int idKsiazki;
    private String tytul;
    private int idAutora;
    private String wydawnictwo;
    private String temat;
    private String jezykKsiazki;
    private String rokWydania;
    private boolean dostepnosc;

    public Ksiazka(int idKsiazki, String tytul, int idAutora, String wydawnictwo, String temat,
                   String jezykKsiazki, String rokWydania, boolean dostepnosc) {
        this.idKsiazki = idKsiazki;
        this.tytul = tytul;
        this.idAutora = idAutora;
        this.wydawnictwo = wydawnictwo;
        this.temat = temat;
        this.jezykKsiazki = jezykKsiazki;
        this.rokWydania = rokWydania;
        this.dostepnosc = dostepnosc;
    }

    public int idKsiazki() {
        return idKsiazki;
    }

    public String tytul() {
        return tytul;
    }

    public int idAutora() {
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

    public boolean dostepnosc() {
        return dostepnosc;
    }
}
