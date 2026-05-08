package com.kargo.dao;

import com.kargo.model.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GonderiDAO implements IGonderiDAO {

    @Override
    public void ekle(Gonderi g) {
        String sql = "INSERT INTO gonderi (agirlik, mesafe, durum, kargo_tipi, gumruk_vergisi, musteri_id) VALUES (?, ?, ?, ?, ?, ?)";

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
            pstmt.setInt(6, g.getMusteri() != null ? g.getMusteri().getId() : 0);

            pstmt.executeUpdate();

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
            pstmt.executeUpdate();
        } catch (SQLException e) { }
    }

    @Override
    public void guncelle(Gonderi g) {
        String sql = "UPDATE gonderi SET durum = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, g.getDurum().name());
            pstmt.setInt(2, g.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) { }
    }

    @Override
    public List<Gonderi> listele() {
        List<Gonderi> list = new ArrayList<>();
        String sql = "SELECT g.*, m.ad as m_ad, m.tc as m_tc, m.telefon as m_tel, m.adres as m_adr " +
                     "FROM gonderi g LEFT JOIN musteri m ON g.musteri_id = m.id";
        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Gonderi g;
                String tip = rs.getString("kargo_tipi");
                if ("ULUSLARARASI".equals(tip)) g = new UluslararasiKargo();
                else if ("HIZLI".equals(tip)) g = new HizliKargo();
                else g = new StandartKargo();
                
                g.setId(rs.getInt("id"));
                g.setAgirlik(rs.getDouble("agirlik"));
                g.setMesafe(rs.getDouble("mesafe"));
                g.setDurum(Durum.valueOf(rs.getString("durum")));
                
                Musteri m = new Musteri(rs.getInt("musteri_id"), rs.getString("m_ad"), rs.getString("m_tc"), rs.getString("m_tel"), rs.getString("m_adr"));
                g.setMusteri(m);
                
                if (g instanceof UluslararasiKargo) ((UluslararasiKargo) g).setGumrukVergisi(rs.getDouble("gumruk_vergisi"));
                list.add(g);
            }
        } catch (SQLException e) { System.out.println("Liste hatasi: " + e.getMessage()); }
        return list;
    }
}