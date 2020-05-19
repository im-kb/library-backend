package com.demo.springboot.service.impl;

import com.demo.springboot.domain.dto.Ksiazka;
import com.demo.springboot.domain.dto.Questions;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBCode {
    private static ArrayList<Ksiazka> ksiazkaList;
    private static String url = "jdbc:postgresql://rogue.db.elephantsql.com:5432/cargbzfv";
    private static String user = "cargbzfv";
    private static String password = "wjhaujNlFknUAm2GQ3jz6HOh9UNLYRB8";
    private static String SEPERATOR = ".";
    private static final String SPLIT_CHAR = ",";

    public static ArrayList<Ksiazka> readKsiazki() { //wczytywywanie ksiazek
        ksiazkaList = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement("SELECT * FROM KSIAZKA");
             ResultSet rs = pst.executeQuery()) {
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            String columnValue = "";
            while (rs.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    columnValue = columnValue+ rs.getString(i);
                    columnValue = columnValue + ",";

                    String[] tab = SEPERATOR.split(SPLIT_CHAR);
                    Ksiazka ks = new Ksiazka(tab[0], tab[1], tab[2], tab[3], tab[4], tab[5], tab[6], tab[7]);
                    ksiazkaList.add(ks);
                }
                columnValue = columnValue + ".";
                columnValue="";
            }

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(DBCode.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return ksiazkaList;
    }

    public static void main(String[] args) {
       //ksiazkaList=readKsiazki(); TODO:: CZEMU TO WYPIERDALA LOL

        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement("SELECT * FROM KSIAZKA");
             ResultSet rs = pst.executeQuery()) {
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            String columnValue = "";
            while (rs.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    columnValue = columnValue+ rs.getString(i);
                    columnValue = columnValue + ",";
                }
                columnValue = columnValue + ".";
                System.out.print(columnValue);
                System.out.println("");
                columnValue="";
            }
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(DBCode.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
        //////////////////////////ZAPYTANIE O ksiazka
    }











/* KOPIA
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
}