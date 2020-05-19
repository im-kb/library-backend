package com.demo.springboot.rest;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBController {

    public static void main(String[] args) {

        String url = "jdbc:postgresql://rogue.db.elephantsql.com:5432/cargbzfv";
        String user = "cargbzfv";
        String password = "wjhaujNlFknUAm2GQ3jz6HOh9UNLYRB8";
        System.out.println("DZIALA SQLLL\n");

        //////////////////////////ZAPYTANIE O WYDAWNICTWO
        System.out.println("Wydawnictwo::::::::::::::::::::::::");
        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement("SELECT * FROM wydawnictwo");
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
            Logger lgr = Logger.getLogger(DBController.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
        System.out.print("-------------------------------------\n\n");
        //////////////////////////ZAPYTANIE O WYDAWNICTWO

        //////////////////////////ZAPYTANIE O KLIENT
        System.out.println("Klient::::::::::::::::::::::::");
        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement("SELECT * FROM KLIENT");
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
            Logger lgr = Logger.getLogger(DBController.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
        System.out.print("-------------------------------------");
        //////////////////////////ZAPYTANIE O KLIENT

    }
}