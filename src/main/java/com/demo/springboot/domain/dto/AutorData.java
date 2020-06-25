package com.demo.springboot.domain.dto;

//import lombok.AllArgsConstructor;

//@AllArgsConstructor
public class AutorData {
    private String id_autora;
    private String nazwisko;
    private String imie;
    private String narodowosc;
    private String okres_tworzenia;
    private String jezyk;

    public AutorData() { }
    public AutorData(String nazwisko, String imie,String narodowosc,String okres_tworzenia,String jezyk) {
        this.nazwisko=nazwisko;
        this.imie=imie;
        this.narodowosc=narodowosc;
        this.okres_tworzenia=okres_tworzenia;
        this.jezyk=jezyk;
    }
    public AutorData(String id_autora,String nazwisko, String imie,String narodowosc,String okres_tworzenia,String jezyk) {
        this.id_autora=id_autora;
        this.nazwisko=nazwisko;
        this.imie=imie;
        this.narodowosc=narodowosc;
        this.okres_tworzenia=okres_tworzenia;
        this.jezyk=jezyk;
    }
    public String getIdAutora() { return id_autora;}
    public String getNazwisko() {return nazwisko;}
    public String getImie() { return imie;}
    public String getNarodowosc() { return narodowosc;}
    public String getOkres_tworzenia() { return okres_tworzenia;}
    public String getJezyk() { return jezyk;}
    @Override
    public String toString() {
        return "{\"AutorData\":{"
                + "\"ID_Autora\":\"" + id_autora + ""
                + "\"Nazwisko\":\"" + nazwisko + ""
                + ", \"Imie\":\"" + imie + "\""
                + "\"Narodowosc\":\"" + narodowosc + ""
                + "\"Okres tworzenia\":\"" + okres_tworzenia + ""
                + "\"Język\":\"" + jezyk + ""
                + "}}";
    }
}
