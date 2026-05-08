package com.kargo.dao;

import com.kargo.model.Musteri;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MusteriDAO implements IMusteriDAO {
    private Connection connection;

    public MusteriDAO() {
        this.connection = DatabaseConnection.connect();
        tabloOlustur();
    }

    private void tabloOlustur() {
        String sql = "CREATE TABLE IF NOT EXISTS Musteri ("
                   + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                   + "ad TEXT, tc TEXT, telefon TEXT, adres TEXT)";
        try {
            connection.createStatement().execute(sql);
        } catch (SQLException e) {
            System.out.println("Müşteri tablosu hatası: " + e.getMessage());
        }
    }

    @Override
    public void ekle(Musteri m) {
        String sql = "INSERT INTO Musteri(ad, tc, telefon, adres) VALUES(?,?,?,?)";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, m.getAd());
            pstmt.setString(2, m.getTc());
            pstmt.setString(3, m.getTelefon());
            pstmt.setString(4, m.getAdres());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Müşteri ekleme hatası: " + e.getMessage());
        }
    }

    @Override
    public void sil(int id) {
        String sql = "DELETE FROM Musteri WHERE id = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Müşteri silme hatası: " + e.getMessage());
        }
    }

    @Override
    public void guncelle(Musteri m) {
        String sql = "UPDATE Musteri SET ad=?, tc=?, telefon=?, adres=? WHERE id=?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, m.getAd());
            pstmt.setString(2, m.getTc());
            pstmt.setString(3, m.getTelefon());
            pstmt.setString(4, m.getAdres());
            pstmt.setInt(5, m.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Müşteri güncelleme hatası: " + e.getMessage());
        }
    }

    @Override
    public List<Musteri> listele() {
        List<Musteri> liste = new ArrayList<>();
        String sql = "SELECT * FROM Musteri";
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Musteri m = new Musteri(
                    rs.getInt("id"),
                    rs.getString("ad"),
                    rs.getString("tc"),
                    rs.getString("telefon"),
                    rs.getString("adres")
                );
                liste.add(m);
            }
        } catch (SQLException e) {
            System.out.println("Müşteri listeleme hatası: " + e.getMessage());
        }
        return liste;
    }
}