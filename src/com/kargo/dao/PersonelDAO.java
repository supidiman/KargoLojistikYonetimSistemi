package com.kargo.dao;

import com.kargo.model.Personel;
import java.sql.*;

public class PersonelDAO implements IPersonelDAO {

    @Override
    public boolean personelDogrula(String kullaniciAdi, String sifre) {
        String sql = "SELECT * FROM personel WHERE kullaniciAdi = ? AND sifre = ?";
        
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, kullaniciAdi);
            pstmt.setString(2, sifre);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return true;
                }
            }
        } catch (SQLException e) {
            System.out.println("Personel doğrulanırken veritabanı hatası: " + e.getMessage());
        }
        return false;
    }

    @Override
    public void ekle(Personel p) {
        String sql = "INSERT INTO personel (kullaniciAdi, sifre) VALUES (?, ?)";
        
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, p.getKullaniciAdi());
            pstmt.setString(2, p.getSifre());
            pstmt.executeUpdate();
            
        } catch (SQLException e) {
            System.out.println("Personel eklenirken veritabanı hatası: " + e.getMessage());
        }
    }
}