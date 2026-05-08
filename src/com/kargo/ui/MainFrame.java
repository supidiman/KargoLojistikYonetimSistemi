package com.kargo.ui;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

/**
 * Uygulamanın ana yönetim merkezi olan çerçeve sınıfı. [cite: 5, 41, 103, 164]
 * Kargo ve Müşteri panellerini sekmeli bir yapıda barındırır. [cite: 78-108, 161-165]
 */
public class MainFrame extends JFrame {

    public MainFrame() {
        // Pencere genel ayarları [cite: 41, 103]
        setTitle("Kargo Lojistik Yönetim Sistemi - Ana Panel");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Ekranın ortasında açılması için

        // Sekmeli yapı bileşeni (Tabbed Pane)
        JTabbedPane sekmeler = new JTabbedPane();

        // Panelleri birer kez oluşturuyoruz ki Listener içinde erişebilelim [cite: 40, 102, 104]
        KargoPanel kargoPanel = new KargoPanel();
        MusteriPanel musteriPanel = new MusteriPanel();

        // Sekmeleri ana iskelete ekliyoruz [cite: 9, 165]
        sekmeler.addTab("Kargo İşlemleri", kargoPanel);
        sekmeler.addTab("Müşteri İşlemleri", musteriPanel);

        /**
         * Sekme değişim dinleyicisi (ChangeListener):
         * Kullanıcı sekmeler arasında geçiş yaptığında verilerin güncel kalmasını sağlar.
         */
        sekmeler.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                // Eğer Kargo İşlemleri sekmesine (0. index) tıklandıysa
                if (sekmeler.getSelectedIndex() == 0) {
                    // Müşteri sekmesinde eklenen yeni isimlerin listeye gelmesi için [cite: 51, 142]
                    kargoPanel.musteriListesiniYukle(); 
                    // Tablonun en güncel haliyle listelenmesi için [cite: 48, 141]
                    kargoPanel.tabloyuGuncelle();
                }
            }
        });

        // Sekmeli yapıyı ana pencereye ekle
        add(sekmeler);
    }
}