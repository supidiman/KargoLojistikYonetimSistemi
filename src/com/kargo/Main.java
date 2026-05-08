package com.kargo;

import com.kargo.ui.LoginFrame;
import com.kargo.dao.DatabaseConnection;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // Veritabanı tablolarını ilk açılışta kontrol et/oluştur
        DatabaseConnection.tablolariOlustur();
        
        SwingUtilities.invokeLater(() -> {
            // Uygulamayı giriş ekranı ile başlat
            LoginFrame girisEkrani = new LoginFrame();
            girisEkrani.setVisible(true);
        });
    }
}