package com.kargo.ui;

import javax.swing.*;

public class MainFrame extends JFrame {

    public MainFrame() {
        setTitle("Savan Lojistik A.Ş. - Kargo Otomasyon Sistemi");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 

        JTabbedPane tabbedPane = new JTabbedPane();
        
        tabbedPane.addTab("Kargo İşlemleri", new KargoPanel());
        
        // Yusuf Müşteri kısmını yazdığında bu yorumu kaldıracağız:
        // tabbedPane.addTab("Müşteri Yönetimi", new MusteriPanel()); 

        add(tabbedPane);
    }
}