package com.kargo.dao;

import com.kargo.model.Personel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonelDAO implements IPersonelDAO {

    @Override
    public boolean personelDogrula(String kullaniciAdi, String sifre) {
        // Veritabanında bu kullanıcı adı ve şifreye sahip biri var mı diye bakıyoruz
        String sql = "SELECT * FROM personel WHERE kullaniciAdi = ? AND sifre = ?";
        
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, kullaniciAdi);
            pstmt.setString(2, sifre);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                // Eğer ResultSet'te bir kayıt varsa (rs.next() true dönerse) giriş başarılıdır
                if (rs.next()) {
                    System.out.println("Giriş Başarılı. Hoşgeldin: " + kullaniciAdi);
                    return true;
                }
            }
        } catch (SQLException e) {
            System.out.println("Personel doğrulanırken veritabanı hatası: " + e.getMessage());
        }
        
        // Kayıt bulunamadıysa veya hata olduysa false dön
        System.out.println("Hatalı kullanıcı adı veya şifre!");
        return false;
    }

    @Override
    public void ekle(Personel p) {
        String sql = "INSERT INTO personel (kullaniciAdi, sifre) VALUES (?, ?)";
        
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            // Not: getKullaniciAdi ve getSifre metotlarını Can Personel sınıfına ekleyecek
            pstmt.setString(1, p.getKullaniciAdi());
            pstmt.setString(2, p.getSifre());
            pstmt.executeUpdate();
            
            System.out.println("Yeni personel kaydedildi: " + p.getKullaniciAdi());
            
        } catch (SQLException e) {
            System.out.println("Personel eklenirken veritabanı hatası: " + e.getMessage());
        }
    }
}
