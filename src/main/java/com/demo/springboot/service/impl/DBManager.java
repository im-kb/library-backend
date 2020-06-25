package com.demo.springboot.service.impl;

import com.demo.springboot.domain.dto.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBManager {
    private static ArrayList<Ksiazka> ksiazkaList;
    private static ArrayList<KlientData> klientList;
    private static ArrayList<WydawnictwoData> wydawnictwoList;
    private static ArrayList<AutorData> autorList;

    private static String url = "jdbc:postgresql://rogue.db.elephantsql.com:5432/cargbzfv";
    private static String user = "cargbzfv";
    private static String password = "wjhaujNlFknUAm2GQ3jz6HOh9UNLYRB8";
    private static String QUERY_RESULT_ROW = "";
    private static final String SPLIT_CHAR = ";;";
    private static Connection con;

    static {
        try {
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public DBManager() throws SQLException {
    }

    public static ArrayList<Ksiazka> getBooks() { //wczytywywanie ksiazek
        ksiazkaList = new ArrayList<>();
        String queryGetBooksForClient = "SELECT k.id_ksiazki,k.tytul,\n" +
                "a.imie || ' ' || a.nazwisko as AUTOR,\n" +
                "w.nazwa || ' ' ||w.miasto as WYDAWNICTWO,\n" +
                "k.temat,k.jezyk_ksiazki,k.rok_wydania,k.dostepnosc,k.opis\n" +
                "from ksiazka k natural join autor a natural join wydawnictwo w";
        try (
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

    public static ArrayList<WypozyczeniaKlienta> getWypozyczeniaKlienta(String login, String password) {//TODO::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
        ArrayList<WypozyczeniaKlienta> klientWypozyczeniaList;
        klientWypozyczeniaList = new ArrayList<>();
        String queryGetWypozyczeniaKlienta = "select id_ksiazki, tytul, data_wypozyczenia, data_zwrotu  from wypozyczone natural join ksiazka\n" +
                "where id_klienta=(SELECT id_klienta from klient where login = '" + login + "'  and haslo = '" + password + "')";
        try (
                PreparedStatement pst = con.prepareStatement(queryGetWypozyczeniaKlienta);
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
                WypozyczeniaKlienta wyp = new WypozyczeniaKlienta(tab[0], tab[1], tab[2], tab[3]);
                klientWypozyczeniaList.add(wyp);
                columnValue = "";
            }
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(DBManager.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return klientWypozyczeniaList;
    }

    public static ArrayList<KlientData> getKlient(String klientLogin, String klientPassword) { //wczytywywanie klienta
        klientList = new ArrayList<>();
        String queryGetBooksForClient = "select imie, nazwisko, miejscowosc, ulica, nr_domu, kod_pocztowy, telefon, login, haslo from klient where login = '" + klientLogin + "'\n" +
                "and haslo = '" + klientPassword + "'";
        try (
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
                KlientData daneKlienta = new KlientData(tab[0], tab[1], tab[2], tab[3], tab[4], tab[5], tab[6], tab[7], tab[8]);
                klientList.add(daneKlienta);
                columnValue = "";
            }
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(DBManager.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
        if (klientList.isEmpty()) {
            return null;
        } else {
            return klientList;
        }
    }

    public static Boolean isLoginAndPasswordRightClient(String userLogin, String userPassword) {
        String queryGetBooksForClient = "SELECT login, haslo from klient where login ='" + userLogin + "' and haslo = '" + userPassword + "'";
        try (
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

    public static Boolean isLoginAndPasswordRightAdmin(String userLogin, String userPassword) {
        String queryGetBooksForClient = "SELECT login, haslo from administrator where login ='" + userLogin + "' and haslo = '" + userPassword + "'";
        try (
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

    public static int deleteClient(String userLogin, String userPassword) {
        String queryClientToRemove = "delete from klient where login ='" + userLogin + "' and haslo = '" + userPassword + "'";
        try {
            Statement stmt = con.createStatement();
            int i = stmt.executeUpdate(queryClientToRemove);
            System.out.println(i);
            return i;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int wypozyczKsiazke(String userLogin, String userPassword, Integer idKsiazki) {
        String queryWypozycz = "INSERT INTO wypozyczone(id_ksiazki,id_klienta,data_wypozyczenia,data_zwrotu)\n" +
                "VALUES (" + idKsiazki + ",(SELECT id_klienta from klient  where login ='" + userLogin + "' and haslo = '" + userPassword + "'),current_date,current_date+30) ON CONFLICT DO NOTHING";
        try {
            Statement stmt = con.createStatement();
            int i = stmt.executeUpdate(queryWypozycz);
            System.out.println(i);
            if (i == 1) {
                String queryUpdate = "update ksiazka set dostepnosc=false where id_ksiazki=" + idKsiazki;
                Statement stmt2 = con.createStatement();
                int i2 = stmt2.executeUpdate(queryUpdate);
                System.out.println(i2);
            }
            return i;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static Boolean jestJuzWypozyczona(String userLogin, String userPassword, Integer idKsiazki) {
        String queryCheck = "select id_ksiazki,id_klienta from wypozyczone where id_klienta=(SELECT id_klienta from klient" +
                "  where login ='" + userLogin + "' and haslo = '" + userPassword + "') and id_ksiazki=" + idKsiazki;
        try (
                PreparedStatement pst = con.prepareStatement(queryCheck);
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


    public static int registerData(String imie, String nazwisko, String login, String haslo, String kod_pocztowy, String telefon, String miejscowosc, String ulica, String nr_domu) {
        String queryInsert = "INSERT INTO klient (nazwisko,imie,kod_pocztowy,miejscowosc,ulica,nr_domu,telefon,login,haslo)" +
                "SELECT '" + nazwisko + "','" + imie + "','" + kod_pocztowy + "','" + miejscowosc + "','" + ulica + "','" + nr_domu + "','" + telefon + "','" + login + "','" + haslo +
                "' WHERE NOT EXISTS (SELECT login FROM klient WHERE login='" + login + "');";
        try {
            Statement stmt = con.createStatement();
            int i = stmt.executeUpdate(queryInsert);
            System.out.println(i);
            return i;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int updateData(String imie, String nazwisko, String login, String haslo, String kod_pocztowy, String telefon, String miejscowosc, String ulica, String nr_domu) {
        String queryUpdate = "UPDATE klient SET nazwisko='" + nazwisko + "',imie='" + imie + "',kod_pocztowy='" + kod_pocztowy + "',miejscowosc='" + miejscowosc + "',ulica='" + ulica + "',nr_domu='" + nr_domu + "',telefon='" + telefon + "',login='" + login + "',haslo='" + haslo + "' WHERE login='" + login + "' AND haslo='" + haslo + "'";
        try {
            Statement stmt = con.createStatement();
            int i = stmt.executeUpdate(queryUpdate);
            System.out.println(i);
            return i;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int addWydawnictwo(String nazwa, String miasto) {
        String queryInsert = "INSERT INTO wydawnictwo (nazwa,miasto)" +
                "SELECT '" + nazwa + "','" + miasto +
                "' WHERE NOT EXISTS (SELECT nazwa FROM wydawnictwo WHERE nazwa='" + nazwa + "');";
        try {
            Statement stmt = con.createStatement();
            int i = stmt.executeUpdate(queryInsert);
            System.out.println(i);
            return i;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    public static int addAutor(String nazwisko, String imie,String narodowosc,String okres_tworzenia,String jezyk) {
        String queryInsert = "INSERT INTO autor (nazwisko,imie,narodowosc,okres_tworzenia,jezyk)" +
                "SELECT '" + nazwisko + "','" + imie + "','" + narodowosc + "','" + okres_tworzenia + "','" +jezyk+
                "' WHERE NOT EXISTS (SELECT nazwisko FROM autor WHERE nazwisko='" + nazwisko + "' AND imie='"+imie+"');";
        try {
            Statement stmt = con.createStatement();
            int i = stmt.executeUpdate(queryInsert);
            System.out.println(i);
            return i;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static ArrayList<WydawnictwoData> getWydawnictwo() { //wczytywywanie wydawnictw
        wydawnictwoList = new ArrayList<>();
        String queryGetWydawnictwa = "SELECT * FROM wydawnictwo";
        try (
                PreparedStatement pst = con.prepareStatement(queryGetWydawnictwa);
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
                WydawnictwoData ks = new WydawnictwoData(tab[0], tab[1], tab[2]);
                wydawnictwoList.add(ks);
                columnValue = "";
            }
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(DBManager.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return wydawnictwoList;
    }
    public static ArrayList<AutorData> getAutors() { //wczytywywanie autorow
        autorList = new ArrayList<>();
        String queryGetAutors = "SELECT * FROM autor";
        try (
                PreparedStatement pst = con.prepareStatement(queryGetAutors);
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
                AutorData ks = new AutorData(tab[0], tab[1], tab[2],tab[3],tab[4],tab[5]);
                autorList.add(ks);
                columnValue = "";
            }
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(DBManager.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return autorList;
    }
    public static int addBook(String tytul, String temat, String jezyk_ksiazki, String rok_wydania, String dostepnosc,String opis, String autor) {
        String queryInsert = "INSERT INTO ksiazka (tytul,temat, autor,jezyk_ksiazki,rok_wydania,dostepnosc,opis)" +
                "SELECT '" + tytul + "','" + temat + "','" + autor+"','" +jezyk_ksiazki + "','" + rok_wydania + "','" + dostepnosc +
                "' WHERE NOT EXISTS (SELECT tytul FROM ksiazka WHERE tytul='" + tytul + "');";
        try {
            Statement stmt = con.createStatement();
            int i = stmt.executeUpdate(queryInsert);
            System.out.println(i);
            return i;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static void main(String[] args) {
       // wypozyczKsiazke("Zxcasd", "zxcasd", 1);
      /* klientList = new ArrayList<>();
        String queryGetBooksForClient = "select id_klienta, imie,nazwisko, miejscowosc, ulica, nr_domu, kod_pocztowy, telefon, login, haslo from klient where login = '" + "kamilabudzik" + "'\n" +
                "and haslo = '" + "gabigabi" + "'";
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
                KlientData daneKlienta = new KlientData(tab[0], tab[1], tab[2], tab[3], tab[4], tab[5], tab[6], tab[7], tab[8]);
                klientList.add(daneKlienta);
                columnValue = "";
            }
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(DBManager.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
        System.out.print(Arrays.toString(klientList.toArray()));
    }*/
    }
}
