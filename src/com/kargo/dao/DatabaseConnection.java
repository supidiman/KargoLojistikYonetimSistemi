package com.kargo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    
    private static final String URL = "jdbc:sqlite:kargo.db";

    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL);
        } catch (SQLException e) {
            System.out.println("Veritabanına bağlanırken hata oluştu: " + e.getMessage());
        }
        return conn;
    }

    public static void tablolariOlustur() {
        String sqlMusteri = "CREATE TABLE IF NOT EXISTS musteri (\n"
                + " id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                + " ad TEXT NOT NULL,\n"
                + " tc TEXT NOT NULL UNIQUE,\n"
                + " telefon TEXT,\n"
                + " adres TEXT\n"
                + ");";

        String sqlGonderi = "CREATE TABLE IF NOT EXISTS gonderi (\n"
                + " id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                + " agirlik REAL NOT NULL,\n"
                + " mesafe REAL NOT NULL,\n"
                + " durum TEXT NOT NULL,\n"
                + " kargo_tipi TEXT NOT NULL,\n"
                + " gumruk_vergisi REAL,\n"
                + " musteri_id INTEGER,\n"
                + " FOREIGN KEY (musteri_id) REFERENCES musteri (id)\n"
                + ");";

        String sqlPersonel = "CREATE TABLE IF NOT EXISTS personel (\n"
                + " id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                + " kullaniciAdi TEXT NOT NULL UNIQUE,\n"
                + " sifre TEXT NOT NULL\n"
                + ");";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            
            stmt.execute(sqlMusteri);
            stmt.execute(sqlGonderi);
            stmt.execute(sqlPersonel);
            
            System.out.println("Veritabanı tabloları hazır.");
            
        } catch (SQLException e) {
            System.out.println("Tablo oluşturma hatası: " + e.getMessage());
        }
    }
}