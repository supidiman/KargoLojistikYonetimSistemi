package com.kargo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:sqlite:kargo.db";
    
    // Uygulama boyunca sadece bir tane bağlantı nesnesi tutacağız
    private static Connection tekBaglanti = null; 

    public static Connection connect() {
        try {
            // Eğer bağlantı daha önce hiç açılmadıysa veya kapandıysa, SADECE BİR KERE aç
            if (tekBaglanti == null || tekBaglanti.isClosed()) {
                tekBaglanti = DriverManager.getConnection(URL);
                System.out.println("Veritabanı bağlantısı başarılı.");
            }
        } catch (SQLException e) {
            System.out.println("Veritabanı bağlantı hatası: " + e.getMessage());
        }
        // Mevcut açık bağlantıyı geri döndür (herkes aynı bağlantıyı kullansın)
        return tekBaglanti;
    }
}