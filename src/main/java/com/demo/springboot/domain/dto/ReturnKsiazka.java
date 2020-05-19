package com.demo.springboot.domain.dto;

import lombok.AllArgsConstructor;

//@AllArgsConstructor
public class ReturnKsiazka {
    String idKsiazki, tytul, idAutora, wydawnictwo, temat, jezykKsiazki, rokWydania, dostepnosc;




    public ReturnKsiazka(String idKsiazki, String tytul, String idAutora, String wydawnictwo, String temat,
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
}
