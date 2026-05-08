package com.kargo.ui;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

/**
 * Uygulamanın ana yönetim merkezi olan çerçeve sınıfı.
 * Kargo ve Müşteri panellerini sekmeli bir yapıda barındırır.
 */
public class MainFrame extends JFrame {

    public MainFrame() {
        setTitle("Kargo Lojistik Yönetim Sistemi - Ana Panel");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 

        JTabbedPane sekmeler = new JTabbedPane();

        // Panelleri birer kez oluşturuyoruz
        KargoPanel kargoPanel = new KargoPanel();
        MusteriPanel musteriPanel = new MusteriPanel();

        // Sekmeleri ana iskelete ekliyoruz
        sekmeler.addTab("Kargo İşlemleri", kargoPanel);
        sekmeler.addTab("Müşteri İşlemleri", musteriPanel);

        // Sekme değişim dinleyicisi
        sekmeler.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (sekmeler.getSelectedIndex() == 0) {
                    kargoPanel.musteriListesiniYukle(); 
                    kargoPanel.tabloyuGuncelle();
                }
            }
        });

        add(sekmeler);
    }
}