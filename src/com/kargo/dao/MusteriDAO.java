package com.kargo.dao;

import com.kargo.model.Musteri;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MusteriDAO implements IMusteriDAO {

    @Override
    public void ekle(Musteri m) {
        String sql = "INSERT INTO musteri (ad, tc, telefon, adres) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, m.getAd());
            pstmt.setString(2, m.getTc());
            pstmt.setString(3, m.getTelefon());
            pstmt.setString(4, m.getAdres());

            pstmt.executeUpdate();
            System.out.println("Müşteri başarıyla kaydedildi: " + m.getAd());

        } catch (SQLException e) {
            System.out.println("Müşteri eklenirken veritabanı hatası: " + e.getMessage());
        }
    }

    @Override
    public void sil(int id) {
        String sql = "DELETE FROM musteri WHERE id = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            int etkilenenSatir = pstmt.executeUpdate();

            if (etkilenenSatir > 0) {
                System.out.println("Müşteri başarıyla silindi. ID: " + id);
            } else {
                System.out.println("Silinecek müşteri bulunamadı.");
            }

        } catch (SQLException e) {
            System.out.println("Müşteri silinirken veritabanı hatası: " + e.getMessage());
        }
    }

    @Override
    public void guncelle(Musteri m) {
        String sql = "UPDATE musteri SET ad = ?, tc = ?, telefon = ?, adres = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, m.getAd());
            pstmt.setString(2, m.getTc());
            pstmt.setString(3, m.getTelefon());
            pstmt.setString(4, m.getAdres());
            pstmt.setInt(5, m.getId());

            pstmt.executeUpdate();
            System.out.println("Müşteri bilgileri güncellendi: " + m.getAd());

        } catch (SQLException e) {
            System.out.println("Müşteri güncellenirken veritabanı hatası: " + e.getMessage());
        }
    }

    @Override
    public List<Musteri> listele() {
        List<Musteri> musteriListesi = new ArrayList<>();
        String sql = "SELECT * FROM musteri";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                // 1. Veritabanından satırı oku ve Can'ın modeline uygun nesne yarat
                Musteri m = new Musteri();
                
                // 2. Verileri nesneye (setter ile) yerleştir
                m.setId(rs.getInt("id"));
                m.setAd(rs.getString("ad"));
                m.setTc(rs.getString("tc"));
                m.setTelefon(rs.getString("telefon"));
                m.setAdres(rs.getString("adres"));
                
                // 3. Dolu nesneyi listeye ekle (Yusuf bunu JTable'da kullanacak)
                musteriListesi.add(m);
            }

        } catch (SQLException e) {
            System.out.println("Müşteriler listelenirken veritabanı hatası: " + e.getMessage());
        }
        return musteriListesi;
    }
}
