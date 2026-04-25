package com.kargo.ui;

import javax.swing.*;

public class MainFrame extends JFrame {

    public MainFrame() {
        setTitle("Savan Nakliyat - Kargo Otomasyonu");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Ekranın ortasında açılır

        JTabbedPane tabbedPane = new JTabbedPane();
        
        // Senin yazdığın Kargo Operasyonları paneli
        tabbedPane.addTab("Kargo İşlemleri", new KargoPanel());
        
        // Yusuf'un yazacağı panel (O yazana kadar yorum satırında tutuyoruz ki hata vermesin)
        // tabbedPane.addTab("Müşteri İşlemleri", new MusteriPanel()); 

        add(tabbedPane);
    }
}