package com.kargo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    
    // SQLite veritabanı dosyamızın adı ve konumu (Projenin ana dizininde oluşacak)
    private static final String URL = "jdbc:sqlite:kargo.db";

    /**
     * Veritabanına bağlantı sağlayan ana metot.
     * UML Diyagramındaki + connect(): Connection metodunun karşılığıdır.
     */
    public static Connection connect() {
        Connection conn = null;
        try {
            // SQLite sürücüsü ile bağlantıyı kuruyoruz
            conn = DriverManager.getConnection(URL);
        } catch (SQLException e) {
            System.out.println("Veritabanına bağlanırken hata oluştu: " + e.getMessage());
        }
        return conn;
    }

    /**
     * Proje ilk çalıştığında tabloları otomatik oluşturmak için yazdığımız metot.
     * (Yunus'un gizli silahı!)
     */
    public static void tablolariOlustur() {
        // Müşteri tablosu için SQL sorgusu
        String sqlMusteri = "CREATE TABLE IF NOT EXISTS musteri (\n"
                + " id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                + " ad TEXT NOT NULL,\n"
                + " tc TEXT NOT NULL UNIQUE,\n"
                + " telefon TEXT,\n"
                + " adres TEXT\n"
                + ");";

        // Kargo (Gönderi) tablosu için SQL sorgusu.
        // UML'deki Standart, Hızlı, Uluslararası kargoları tek tabloda tutacağız.
        String sqlGonderi = "CREATE TABLE IF NOT EXISTS gonderi (\n"
                + " id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                + " agirlik REAL NOT NULL,\n"
                + " mesafe REAL NOT NULL,\n"
                + " durum TEXT NOT NULL,\n"        // SUBEDE, DAGITIMDA vb.
                + " kargo_tipi TEXT NOT NULL,\n"    // STANDART, HIZLI, ULUSLARARASI
                + " gumruk_vergisi REAL,\n"         // Sadece uluslararası kargo için dolacak
                + " musteri_id INTEGER,\n"          // Hangi müşteriye ait olduğunu bağlayan sütun
                + " FOREIGN KEY (musteri_id) REFERENCES musteri (id)\n"
                + ");";

        // Personel girişi için Tablo
        String sqlPersonel = "CREATE TABLE IF NOT EXISTS personel (\n"
                + " id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                + " kullaniciAdi TEXT NOT NULL UNIQUE,\n"
                + " sifre TEXT NOT NULL\n"
                + ");";

        // try-with-resources kullanarak bağlantıyı otomatik kapatıyoruz
        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            
            // Tabloları veritabanına işliyoruz
            stmt.execute(sqlMusteri);
            stmt.execute(sqlGonderi);
            stmt.execute(sqlPersonel);
            
            System.out.println("Veritabanı tabloları hazır (veya zaten mevcut).");
            
        } catch (SQLException e) {
            System.out.println("Tablo oluşturma hatası: " + e.getMessage());
        }
    }
}