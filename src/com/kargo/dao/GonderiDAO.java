package com.kargo.dao;

import com.kargo.model.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GonderiDAO implements IGonderiDAO {
    private Connection connection;

    public GonderiDAO() {
        this.connection = DatabaseConnection.connect();
        tabloOlustur();
    }

    private void tabloOlustur() {
        // OOP Polymorphism'i veritabanında tutabilmek için kargoTipi sütunu ekledik.
        String sql = "CREATE TABLE IF NOT EXISTS Gonderi ("
                   + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                   + "agirlik REAL, mesafe REAL, durum TEXT, "
                   + "kargoTipi TEXT, gumrukVergisi REAL, musteriId INTEGER)";
        try {
            connection.createStatement().execute(sql);
        } catch (SQLException e) {
            System.out.println("Gönderi tablosu hatası: " + e.getMessage());
        }
    }

    @Override
    public void ekle(Gonderi g) {
        String sql = "INSERT INTO Gonderi(agirlik, mesafe, durum, kargoTipi, gumrukVergisi, musteriId) VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setDouble(1, g.getAgirlik());
            pstmt.setDouble(2, g.getMesafe());
            pstmt.setString(3, g.getDurum().name());
            
            // Hangi kargo nesnesi geldiyse onun tipini kaydediyoruz (Polymorphism Database Binding)
            if (g instanceof StandartKargo) {
                pstmt.setString(4, "Standart");
                pstmt.setDouble(5, 0.0);
            } else if (g instanceof HizliKargo) {
                pstmt.setString(4, "Hizli");
                pstmt.setDouble(5, 0.0);
            } else if (g instanceof UluslararasiKargo) {
                pstmt.setString(4, "Uluslararasi");
                pstmt.setDouble(5, ((UluslararasiKargo) g).getGumrukVergisi());
            }

            pstmt.setInt(6, g.getMusteri().getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Gönderi ekleme hatası: " + e.getMessage());
        }
    }

    @Override
    public void sil(int id) {
        String sql = "DELETE FROM Gonderi WHERE id = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Gönderi silme hatası: " + e.getMessage());
        }
    }

    @Override
    public void guncelle(Gonderi g) {
        String sql = "UPDATE Gonderi SET durum = ? WHERE id = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, g.getDurum().name());
            pstmt.setInt(2, g.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Gönderi güncelleme hatası: " + e.getMessage());
        }
    }

    @Override
    public List<Gonderi> listele() {
        List<Gonderi> liste = new ArrayList<>();
        // SQL JOIN ile Kargo ve Müşteri tablolarını birleştirip müşteri adını da alıyoruz
        String sql = "SELECT Gonderi.*, Musteri.ad as musteriAd FROM Gonderi " +
                     "LEFT JOIN Musteri ON Gonderi.musteriId = Musteri.id";
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String tip = rs.getString("kargoTipi");
                Durum durum = Durum.valueOf(rs.getString("durum"));
                
                // Müşteri nesnesini ID ve isimle oluşturuyoruz [cite: 25-28, 128-133]
                Musteri m = new Musteri();
                m.setId(rs.getInt("musteriId"));
                m.setAd(rs.getString("musteriAd"));

                Gonderi g = null;
                // Polymorphism kullanarak nesneleri oluşturuyoruz [cite: 112, 113]
                if (tip.equals("Standart")) {
                    g = new StandartKargo(rs.getInt("id"), rs.getDouble("agirlik"), rs.getDouble("mesafe"), durum, m);
                } else if (tip.equals("Hizli")) {
                    g = new HizliKargo(rs.getInt("id"), rs.getDouble("agirlik"), rs.getDouble("mesafe"), durum, m);
                } else if (tip.equals("Uluslararasi")) {
                    g = new UluslararasiKargo(rs.getInt("id"), rs.getDouble("agirlik"), rs.getDouble("mesafe"), durum, m, rs.getDouble("gumrukVergisi"));
                }
                
                if (g != null) liste.add(g);
            }
        } catch (SQLException e) {
            System.out.println("Gönderi listeleme hatası: " + e.getMessage());
        }
        return liste;
    }
}