package com.kargo.dao;

import com.kargo.model.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GonderiDAO implements IGonderiDAO {

    @Override
    public void ekle(Gonderi g) {
        String sql = "INSERT INTO gonderi (agirlik, mesafe, durum, kargo_tipi, gumruk_vergisi) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setDouble(1, g.getAgirlik());
            pstmt.setDouble(2, g.getMesafe());
            pstmt.setString(3, g.getDurum().name());

            if (g instanceof UluslararasiKargo) {
                pstmt.setString(4, "ULUSLARARASI");
                pstmt.setDouble(5, ((UluslararasiKargo) g).getGumrukVergisi());
            } else if (g instanceof HizliKargo) {
                pstmt.setString(4, "HIZLI");
                pstmt.setDouble(5, 0.0);
            } else {
                pstmt.setString(4, "STANDART");
                pstmt.setDouble(5, 0.0);
            }

            pstmt.executeUpdate();
            System.out.println("Kargo başarıyla veritabanına eklendi.");

        } catch (SQLException e) {
            System.out.println("Kargo eklenirken veritabanı hatası: " + e.getMessage());
        }
    }

    @Override
    public void sil(int id) {
        String sql = "DELETE FROM gonderi WHERE id = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            int etkilenenSatir = pstmt.executeUpdate();

            if (etkilenenSatir > 0) {
                System.out.println("Kargo başarıyla silindi. ID: " + id);
            } else {
                System.out.println("Silinecek kargo bulunamadı.");
            }

        } catch (SQLException e) {
            System.out.println("Kargo silinirken veritabanı hatası: " + e.getMessage());
        }
    }

    @Override
    public void guncelle(Gonderi g) {
        String sql = "UPDATE gonderi SET durum = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, g.getDurum().name());
            pstmt.setInt(2, g.getId());
            pstmt.executeUpdate();
            System.out.println("Kargo durumu güncellendi. Yeni durum: " + g.getDurum().name());

        } catch (SQLException e) {
            System.out.println("Kargo güncellenirken veritabanı hatası: " + e.getMessage());
        }
    }

    @Override
    public List<Gonderi> listele() {
        List<Gonderi> kargoListesi = new ArrayList<>();
        String sql = "SELECT * FROM gonderi";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                // 1. Gelen verileri alıyoruz
                String kargoTipi = rs.getString("kargo_tipi");
                Durum durum = Durum.valueOf(rs.getString("durum")); // String'den Enum'a çeviriyoruz
                
                Gonderi g = null;

                // 2. Kargo tipine göre doğru alt sınıfı oluşturuyoruz (Polymorphism)
                if ("ULUSLARARASI".equals(kargoTipi)) {
                    UluslararasiKargo uk = new UluslararasiKargo();
                    uk.setGumrukVergisi(rs.getDouble("gumruk_vergisi"));
                    g = uk;
                } else if ("HIZLI".equals(kargoTipi)) {
                    g = new HizliKargo();
                } else {
                    g = new StandartKargo();
                }

                // 3. Ortak bilgileri Gonderi nesnesine set ediyoruz
                g.setId(rs.getInt("id"));
                g.setAgirlik(rs.getDouble("agirlik"));
                g.setMesafe(rs.getDouble("mesafe"));
                g.setDurum(durum);

                // 4. Dolu kargo nesnesini listeye ekliyoruz (Furkan bunu UI'da JTable'a basacak)
                kargoListesi.add(g);
            }

        } catch (SQLException e) {
            System.out.println("Kargolar listelenirken veritabanı hatası: " + e.getMessage());
        }
        return kargoListesi;
    }
}
