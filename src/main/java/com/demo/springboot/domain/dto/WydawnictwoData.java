package com.demo.springboot.domain.dto;

public class WydawnictwoData {
    private String id_wydawnictwa;
    private String nazwa;
    private String miasto;

    public WydawnictwoData() { }
    public WydawnictwoData(String nazwa, String miasto) {
        this.nazwa=nazwa;
        this.miasto=miasto;
    }

    public WydawnictwoData(String id_wydawnictwa, String nazwa, String miasto) {
        this.id_wydawnictwa=id_wydawnictwa;
        this.nazwa=nazwa;
        this.miasto=miasto;
    }

    public String getNazwa() {return nazwa;}
    public String getId_wydawnictwa() {return id_wydawnictwa;}
    public String getMiasto() { return miasto;}
    @Override
    public String toString() {
        return "{\"WydawnictwoData\":{"
                + "\"ID Wydawnictwa\":\"" + id_wydawnictwa + ""
                + "\"Nazwa\":\"" + nazwa + ""
                + ", \"Miasto\":\"" + miasto + "\""
                + "}}";
    }
}
