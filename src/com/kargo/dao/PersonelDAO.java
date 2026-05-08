package com.kargo.dao;

import com.kargo.model.Personel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonelDAO implements IPersonelDAO {
    private Connection connection;

    public PersonelDAO() {
        this.connection = DatabaseConnection.connect();
        tabloOlustur();
    }

    private void tabloOlustur() {
        String sql = "CREATE TABLE IF NOT EXISTS Personel ("
                   + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                   + "kullaniciAdi TEXT NOT NULL, "
                   + "sifre TEXT NOT NULL)";
        try {
            connection.createStatement().execute(sql);
        } catch (SQLException e) {
            System.out.println("Personel tablosu oluşturulamadı: " + e.getMessage());
        }
    }

    @Override
    public boolean personelDogrula(String kadi, String sifre) {
        String sql = "SELECT * FROM Personel WHERE kullaniciAdi = ? AND sifre = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, kadi);
            pstmt.setString(2, sifre);
            ResultSet rs = pstmt.executeQuery();
            return rs.next(); // Eğer kayıt eşleşiyorsa true döner
        } catch (SQLException e) {
            System.out.println("Giriş doğrulama hatası: " + e.getMessage());
            return false;
        }
    }

    @Override
    public void ekle(Personel p) {
        String sql = "INSERT INTO Personel(kullaniciAdi, sifre) VALUES(?, ?)";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, p.getKullaniciAdi());
            pstmt.setString(2, p.getSifre());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Personel ekleme hatası: " + e.getMessage());
        }
    }
}