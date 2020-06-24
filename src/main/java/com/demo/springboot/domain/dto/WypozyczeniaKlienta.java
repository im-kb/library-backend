package com.demo.springboot.domain.dto;

public class WypozyczeniaKlienta {
    private String idKsiazki;
    private String tytulKsiazki;
    private String dataWypozyczenia;
    private String dataZwrotu;


    public WypozyczeniaKlienta(String idKsiazki, String tytulKsiazki, String dataWypozyczenia, String dataZwrotu) {
        this.idKsiazki = idKsiazki;
        this.tytulKsiazki = tytulKsiazki;
        this.dataWypozyczenia = dataWypozyczenia;
        this.dataZwrotu = dataZwrotu;
    }

    public String getIdKsiazki() {
        return idKsiazki;
    }

    public String getTytulKsiazki() {
        return tytulKsiazki;
    }

    public String getdataWypozyczenia() {
        return dataWypozyczenia;
    }

    public String getDataZwrotu() {
        return dataZwrotu;
    }
}