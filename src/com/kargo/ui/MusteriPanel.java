package com.kargo.ui;

import com.kargo.model.Musteri;
import com.kargo.service.MusteriService;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class MusteriPanel extends JPanel {
    private MusteriService musteriService;
    private JTable tablo;
    private DefaultTableModel model;

    public MusteriPanel() {
        musteriService = new MusteriService();
        setLayout(new BorderLayout());

        // Üst Kısım: Müşteri Ekleme Formu
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createTitledBorder("Yeni Müşteri Ekle"));

        formPanel.add(new JLabel("  Ad Soyad:"));
        JTextField txtAd = new JTextField();
        formPanel.add(txtAd);

        formPanel.add(new JLabel("  TC Kimlik (11 Hane):"));
        JTextField txtTc = new JTextField();
        formPanel.add(txtTc);

        formPanel.add(new JLabel("  Telefon:"));
        JTextField txtTelefon = new JTextField();
        formPanel.add(txtTelefon);

        formPanel.add(new JLabel("  Adres:"));
        JTextField txtAdres = new JTextField();
        formPanel.add(txtAdres);

        JButton btnEkle = new JButton("Müşteri Kaydet");
        formPanel.add(new JLabel("")); // Boşluk dolsun diye
        formPanel.add(btnEkle);

        add(formPanel, BorderLayout.NORTH);

        // Orta Kısım: Müşteri Listesi Tablosu
        String[] kolonlar = {"ID", "Ad Soyad", "TC Kimlik", "Telefon", "Adres"};
        model = new DefaultTableModel(kolonlar, 0);
        tablo = new JTable(model);
        add(new JScrollPane(tablo), BorderLayout.CENTER);

        // Buton İşlemi
        btnEkle.addActionListener(e -> {
            Musteri m = new Musteri(0, txtAd.getText(), txtTc.getText(), txtTelefon.getText(), txtAdres.getText());
            
            try {
                // Servis katmanındaki validasyon (doğrulama) kuralları çalışır
                musteriService.musteriKaydet(m);
                
                // Formu temizle
                txtAd.setText("");
                txtTc.setText("");
                txtTelefon.setText("");
                txtAdres.setText("");
                
                // Tabloyu yenile
                tabloyuGuncelle();
                JOptionPane.showMessageDialog(this, "Müşteri başarıyla eklendi!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Hata: Müşteri eklenemedi. Bilgileri kontrol edin.");
            }
        });

        // Sayfa açıldığında tabloyu doldur
        tabloyuGuncelle();
    }

    public void tabloyuGuncelle() {
        model.setRowCount(0); // Tabloyu temizle
        List<Musteri> liste = musteriService.Musterilerigetir();
        for (Musteri m : liste) {
            Object[] satir = {m.getId(), m.getAd(), m.getTc(), m.getTelefon(), m.getAdres()};
            model.addRow(satir);
        }
    }
}