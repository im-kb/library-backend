package com.demo.springboot.domain.dto;

public class Ksiazka {
    private String idKsiazki;
    private String tytul;
    private String autor;
    private String wydawnictwo;
    private String temat;
    private String jezykKsiazki;
    private String rokWydania;
    private String dostepnosc;
    private String opis;

    private String imieAutora;
    private String nazwiskoAutora;

    public Ksiazka(String idKsiazki, String tytul, String autor, String wydawnictwo, String temat,
                   String jezykKsiazki, String rokWydania, String dostepnosc, String opis) {
        this.idKsiazki = idKsiazki;
        this.tytul = tytul;
        this.autor = autor;
        this.wydawnictwo = wydawnictwo;
        this.temat = temat;
        this.jezykKsiazki = jezykKsiazki;
        this.rokWydania = rokWydania;
        this.dostepnosc = dostepnosc;
        this.opis = opis;
    }

    public Ksiazka() {
    }


    public String getIdKsiazki() {
        return idKsiazki;
    }

    public String getTytul() {
        return tytul;
    }

    public String getAutor() {
        return autor;
    }

    public String getWydawnictwo() {
        return wydawnictwo;
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

    public String getOpis() {
        return opis;
    }

    public String getImieAutora() {
        return imieAutora;
    }

    public String getNazwiskoAutora() {
        return nazwiskoAutora;
    }
}