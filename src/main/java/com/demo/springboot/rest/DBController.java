package com.demo.springboot.rest;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBController {

    public static void main(String[] args) {

        String url = "jdbc:postgresql://rogue.db.elephantsql.com:5432/cargbzfv";
        String user = "cargbzfv";
        String password = "wjhaujNlFknUAm2GQ3jz6HOh9UNLYRB8";
        System.out.println("\n \n \nDZIALA SQLLL ");

        try (Connection con = DriverManager.getConnection(url, user, password);
             PreparedStatement pst = con.prepareStatement("SELECT * FROM wydawnictwo");
             ResultSet rs = pst.executeQuery()) {
            while (rs.next() != false) {
                System.out.print(rs.getInt(1));
                System.out.print(": ");
                System.out.print(rs.getString(2));
                System.out.print(": ");
                System.out.print(rs.getString(3));
                System.out.print("\n");
            }

        } catch (SQLException ex) {

            Logger lgr = Logger.getLogger(DBController.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
}