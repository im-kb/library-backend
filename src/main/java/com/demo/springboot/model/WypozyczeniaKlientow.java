package com.demo.springboot.model;

public class WypozyczeniaKlientow {
    private String idKsiazki;
    private String idKlienta;
    private String tytulKsiazki;
    private String dataWypozyczenia;
    private String dataZwrotu;

    private String imieWypozyczajacego;
    private String nazwiskoWypozyczajacego;

    public WypozyczeniaKlientow(String idKsiazki, String idKlienta, String tytulKsiazki, String dataWypozyczenia, String dataZwrotu, String imieWypozyczajacego, String nazwiskoWypozyczajacego) {
        this.idKsiazki = idKsiazki;
        this.idKlienta=idKlienta;
        this.tytulKsiazki = tytulKsiazki;
        this.dataWypozyczenia = dataWypozyczenia;
        this.dataZwrotu = dataZwrotu;
        this.imieWypozyczajacego = imieWypozyczajacego;
        this.nazwiskoWypozyczajacego = nazwiskoWypozyczajacego;
    }

    public String getIdKsiazki() {
        return idKsiazki;
    }
    public String getIdKlienta() {
        return idKlienta;
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

    public String getImieWypozyczajacego() {
        return imieWypozyczajacego;
    }

    public String getNazwiskoWypozyczajacego() {
        return nazwiskoWypozyczajacego;
    }


}
