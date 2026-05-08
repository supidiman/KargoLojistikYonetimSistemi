package com.kargo.dao;

import com.kargo.model.Musteri;
import java.sql.*;
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
        } catch (SQLException e) { System.out.println("Ekleme hatasi: " + e.getMessage()); }
    }

    @Override
    public void sil(String tc) { // DUZELTME: TC uzerinden silme
        String sql = "DELETE FROM musteri WHERE tc = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, tc);
            pstmt.executeUpdate();
        } catch (SQLException e) { System.out.println("Silme hatasi: " + e.getMessage()); }
    }

    @Override
    public void guncelle(Musteri m) { // DUZELTME: TC uzerinden bulup guncelleme
        String sql = "UPDATE musteri SET ad = ?, telefon = ?, adres = ? WHERE tc = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, m.getAd());
            pstmt.setString(2, m.getTelefon());
            pstmt.setString(3, m.getAdres());
            pstmt.setString(4, m.getTc());
            pstmt.executeUpdate();
        } catch (SQLException e) { System.out.println("Guncelleme hatasi: " + e.getMessage()); }
    }

    @Override
    public List<Musteri> listele() {
        List<Musteri> list = new ArrayList<>();
        String sql = "SELECT * FROM musteri";
        try (Connection conn = DatabaseConnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Musteri(rs.getInt("id"), rs.getString("ad"), rs.getString("tc"), rs.getString("telefon"), rs.getString("adres")));
            }
        } catch (SQLException e) { System.out.println("Listeleme hatasi: " + e.getMessage()); }
        return list;
    }
}