package com.kargo.ui;

import com.kargo.model.Durum;
import com.kargo.model.Gonderi;
import com.kargo.model.StandartKargo; 
import com.kargo.service.KargoService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class KargoPanel extends JPanel {

    private KargoService kargoService;
    private JTable table;
    private DefaultTableModel tableModel;
    private JComboBox<Durum> durumComboBox;
    
    private JTextField txtAgirlik, txtMesafe;
    private JComboBox<String> kargoTipiComboBox;
    private JButton btnKargoEkle, btnDurumGuncelle;

    public KargoPanel() {
        kargoService = new KargoService();
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // --- ÜST: KARGO EKLEME FORMU ---
        JPanel formPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        formPanel.setBorder(BorderFactory.createTitledBorder("Yeni Kargo Girişi"));

        formPanel.add(new JLabel("Ağırlık (kg):"));
        txtAgirlik = new JTextField(5);
        formPanel.add(txtAgirlik);
        
        formPanel.add(new JLabel("Mesafe (km):"));
        txtMesafe = new JTextField(5);
        formPanel.add(txtMesafe);
        
        formPanel.add(new JLabel("Tip:"));
        String[] tipler = {"Standart", "Hızlı", "Uluslararası"};
        kargoTipiComboBox = new JComboBox<>(tipler);
        formPanel.add(kargoTipiComboBox);
        
        btnKargoEkle = new JButton("Kaydet");
        btnKargoEkle.setBackground(new Color(46, 204, 113));
        btnKargoEkle.setForeground(Color.WHITE);
        formPanel.add(btnKargoEkle);
        
        add(formPanel, BorderLayout.NORTH);

        // --- ORTA: VERİ TABLOSU ---
        String[] kolonlar = {"Kargo ID", "Ağırlık", "Mesafe", "Ücret (Hesaplanan)", "Kargo Tipi", "Durum"};
        tableModel = new DefaultTableModel(kolonlar, 0) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        table = new JTable(tableModel);
        table.setRowHeight(25);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // --- ALT: DURUM GÜNCELLEME ---
        JPanel altPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        altPanel.add(new JLabel("Yeni Durum:"));
        
        durumComboBox = new JComboBox<>(Durum.values());
        altPanel.add(durumComboBox);

        btnDurumGuncelle = new JButton("Seçili Kargonun Durumunu Güncelle");
        btnDurumGuncelle.setBackground(new Color(52, 152, 219)); 
        btnDurumGuncelle.setForeground(Color.WHITE);
        altPanel.add(btnDurumGuncelle);

        add(altPanel, BorderLayout.SOUTH);

        btnKargoEkle.addActionListener(e -> kargoEkleAction());
        btnDurumGuncelle.addActionListener(e -> durumGuncelleAction());

        tabloyuYenile();
    }

    private void tabloyuYenile() {
        tableModel.setRowCount(0); 
        try {
            List<Gonderi> kargolar = kargoService.kargoListele();
            if(kargolar != null) {
                for (Gonderi g : kargolar) {
                    Object[] satir = {
                        g.getId(),
                        g.getAgirlik(),
                        g.getMesafe(),
                        g.ucretHesapla() + " TL", // Can'ın yazdığı interface metodunu kullanıyoruz
                        g.getClass().getSimpleName(), 
                        g.getDurum()
                    };
                    tableModel.addRow(satir);
                }
            }
        } catch (Exception ex) {
            System.err.println("Veri çekilirken hata oluştu: " + ex.getMessage());
        }
    }

    private void kargoEkleAction() {
        try {
            double agirlik = Double.parseDouble(txtAgirlik.getText());
            double mesafe = Double.parseDouble(txtMesafe.getText());
            
            // Şimdilik sadece StandartKargo üretiyoruz. Can diğerlerini ekleyince burayı if-else ile açarız.
            Gonderi yeniKargo = new StandartKargo(agirlik, mesafe); 
            yeniKargo.setDurum(Durum.SUBEDE); 

            kargoService.kargoEkle(yeniKargo); 
            JOptionPane.showMessageDialog(this, "Kargo başarıyla kaydedildi!");
            tabloyuYenile(); 
            
            txtAgirlik.setText(""); txtMesafe.setText("");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Ağırlık ve mesafe sayısal olmalıdır!", "Girdi Hatası", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void durumGuncelleAction() {
        int seciliSatir = table.getSelectedRow();
        if (seciliSatir == -1) {
            JOptionPane.showMessageDialog(this, "Lütfen tablodan bir kargo seçin!", "Uyarı", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int kargoId = (int) tableModel.getValueAt(seciliSatir, 0);
        Durum secilenDurum = (Durum) durumComboBox.getSelectedItem();

        try {
            List<Gonderi> kargolar = kargoService.kargoListele();
            Gonderi guncellenecekKargo = null;
            for(Gonderi g : kargolar) {
                if(g.getId() == kargoId) {
                    guncellenecekKargo = g;
                    break;
                }
            }

            if (guncellenecekKargo != null) {
                guncellenecekKargo.setDurum(secilenDurum);
                kargoService.kargoGuncelle(guncellenecekKargo);
                
                JOptionPane.showMessageDialog(this, "Durum başarıyla güncellendi!");
                tabloyuYenile(); 
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Güncelleme hatası: " + ex.getMessage());
        }
    }
}