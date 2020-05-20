package com.demo.springboot.domain.dto;

//@AllArgsConstructor
public class ReturnKsiazka {
    String idKsiazki, tytul, autor, wydawnictwo, temat, jezykKsiazki, rokWydania, dostepnosc;

    public ReturnKsiazka(String idKsiazki, String tytul, String autor, String wydawnictwo, String temat,
                         String jezykKsiazki, String rokWydania, String dostepnosc) {
        this.idKsiazki = idKsiazki;
        this.tytul = tytul;
        this.autor = autor;
        this.wydawnictwo = wydawnictwo;
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


}


