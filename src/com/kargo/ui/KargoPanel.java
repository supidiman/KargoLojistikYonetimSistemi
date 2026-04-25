package com.kargo.ui;

import com.kargo.model.Durum;
import com.kargo.service.KargoService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class KargoPanel extends JPanel {

    private KargoService kargoService;
    private JTable table;
    private DefaultTableModel tableModel;
    private JComboBox<Durum> durumComboBox;
    private JButton btnDurumGuncelle;

    public KargoPanel() {
        kargoService = new KargoService();
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // --- ÜST PANEL: İŞLEMLER ---
        JPanel islemPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        islemPanel.add(new JLabel("Yeni Durum:"));
        
        // Durumları ComboBox'a yüklüyoruz
        durumComboBox = new JComboBox<>(Durum.values());
        islemPanel.add(durumComboBox);

        btnDurumGuncelle = new JButton("Seçili Kargonun Durumunu Güncelle");
        btnDurumGuncelle.setBackground(new Color(70, 130, 180));
        btnDurumGuncelle.setForeground(Color.WHITE);
        islemPanel.add(btnDurumGuncelle);

        add(islemPanel, BorderLayout.NORTH);

        // --- ORTA PANEL: TABLO ---
        String[] kolonlar = {"Kargo ID", "Müşteri Adı", "Ağırlık (kg)", "Mesafe (km)", "Kargo Tipi", "Durum"};
        tableModel = new DefaultTableModel(kolonlar, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Hücreler elle değiştirilmesin
            }
        };
        table = new JTable(tableModel);
        table.setRowHeight(25);
        add(new JScrollPane(table), BorderLayout.CENTER);

        // --- BUTON AKSİYONLARI ---
        btnDurumGuncelle.addActionListener(e -> durumGuncelleAction());

        // Arayüz açılır açılmaz sahte verileri yükle
        tabloyuYenile();
    }

    private void tabloyuYenile() {
        // Yunus veritabanını bağlayana kadar geçici verileri kullanıyoruz
        kargoService.sahteVeriYukle(tableModel);
    }

    private void durumGuncelleAction() {
        int seciliSatir = table.getSelectedRow();
        if (seciliSatir == -1) {
            JOptionPane.showMessageDialog(this, "Lütfen tablodan güncellenecek bir kargo seçin!", "Uyarı", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Durum secilenDurum = (Durum) durumComboBox.getSelectedItem();
        
        // Sadece arayüzde görselliği test etmek için anlık güncelleme yapıyoruz
        tableModel.setValueAt(secilenDurum.name(), seciliSatir, 5);
        JOptionPane.showMessageDialog(this, "Kargo durumu başarıyla " + secilenDurum.name() + " olarak güncellendi!\n(Veritabanı bağlantısı bekleniyor)");
    }
}