package com.demo.springboot.service.impl;

import com.demo.springboot.domain.dto.Ksiazka;
import com.demo.springboot.domain.dto.LoginData;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DBManager {
    private static ArrayList<Ksiazka> ksiazkaList;
    private static ArrayList<LoginData> loginList;
    private static String url = "jdbc:postgresql://rogue.db.elephantsql.com:5432/cargbzfv";
    private static String user = "cargbzfv";
    private static String password = "wjhaujNlFknUAm2GQ3jz6HOh9UNLYRB8";
    private static String QUERY_RESULT_ROW = "";
    private static final String SPLIT_CHAR = ";;";

    public static ArrayList<Ksiazka> readKsiazki() { //wczytywywanie ksiazek
        ksiazkaList = new ArrayList<>();
        String queryGetBooksForClient = "SELECT k.id_ksiazki,k.tytul,\n" +
                "a.imie || ' ' || a.nazwisko as AUTOR,\n" +
                "w.nazwa || ' ' ||w.miasto as WYDAWNICTWO,\n" +
                "k.temat,k.jezyk_ksiazki,k.rok_wydania,k.dostepnosc,k.opis\n" +
                "from ksiazka k natural join autor a natural join wydawnictwo w";
        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(queryGetBooksForClient);
             ResultSet rs = pst.executeQuery()) {
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            String columnValue = "";
            while (rs.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    columnValue = columnValue + rs.getString(i);
                    columnValue = columnValue + ";;";
                }
                QUERY_RESULT_ROW = columnValue;
                String[] tab = QUERY_RESULT_ROW.split(SPLIT_CHAR);
                Ksiazka ks = new Ksiazka(tab[0], tab[1], tab[2], tab[3], tab[4], tab[5], tab[6], tab[7], tab[8]);
                ksiazkaList.add(ks);
                columnValue = "";
            }
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(DBManager.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return ksiazkaList;
    }

    public static Boolean isLoginAndPasswordRight(String userLogin, String userPassword) {
        String queryGetBooksForClient = "SELECT login, haslo from klient where login ='" + userLogin + "' and haslo = '" + userPassword + "'";
        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement(queryGetBooksForClient);
             ResultSet rs = pst.executeQuery()) {
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            String columnValue = "";
            while (rs.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    columnValue = columnValue + rs.getString(i);
                    columnValue = columnValue + ";;";
                }
                QUERY_RESULT_ROW = columnValue;
                if (QUERY_RESULT_ROW != null) {
                    return true;
                } else {
                    return false;
                }
            }
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(DBManager.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return false;
    }
    public static void registerData(String imie,String nazwisko,String login,String haslo,String kod_pocztowy,String telefon,String miejscowosc,String ulica,String nr_domu){
        String queryInsert = "INSERT INTO klient (nazwisko,imie,kod_pocztowy,miejscowosc,ulica,nr_domu,telefon,login,haslo)SELECT '"+nazwisko+"','"+imie+"','"+kod_pocztowy+"','"+miejscowosc+"','"+ulica+"','"+nr_domu+"','"+telefon+"','"+login+"','"+haslo+"' WHERE NOT EXISTS (SELECT login FROM klient WHERE login='"+login+"');";
        try {
            Connection con = DriverManager.getConnection(url, user, password);
            Statement stmt=con.createStatement();
            stmt.executeUpdate(queryInsert);
            System.out.println("zarejesestrowano");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        System.out.println("LOGIN TO::::::::::::::::::::::::::::::::::::::::");
        System.out.println(isLoginAndPasswordRight("kamilabudzik", "gabigabi")); // zworci true bo jest taki login i haslo
    }
}


/* KOPIA DO WYSWIETLANIA
    public static void main(String[] args) {
        //////////////////////////ZAPYTANIE O KSIAZKA
        System.out.println("KSIAZKA::::::::::::::::::::::::");
        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement("SELECT * FROM KSIAZKA");
             ResultSet rs = pst.executeQuery()) {
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            while (rs.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) System.out.print(",  ");
                    String columnValue = rs.getString(i);
                    System.out.print(rsmd.getColumnName(i) + ": " + columnValue);
                }
                System.out.println("");
            }

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(DBCode.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
        System.out.print("-------------------------------------");
        //////////////////////////ZAPYTANIE O ksiazka
 }*/