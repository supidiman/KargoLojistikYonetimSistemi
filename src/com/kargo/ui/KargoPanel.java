package com.kargo.ui;

import com.kargo.model.*;
import com.kargo.service.KargoService;
import com.kargo.service.MusteriService;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class KargoPanel extends JPanel {
    private static final long serialVersionUID = 1L;
	private KargoService kargoService;
    private MusteriService musteriService;
    private JTable tablo;
    private DefaultTableModel model;
    private JComboBox<Musteri> comboMusteri;
    private JComboBox<String> comboTip;
    private JTextField txtAgirlik, txtMesafe;

    public KargoPanel() {
        kargoService = new KargoService();
        musteriService = new MusteriService(new com.kargo.dao.MusteriDAO());
        setLayout(new BorderLayout(10, 10));

        JPanel formPanel = new JPanel(new GridLayout(3, 4, 10, 10));
        formPanel.setBorder(BorderFactory.createTitledBorder("Kargo Operasyon Yönetimi"));

        formPanel.add(new JLabel("  Ağırlık (kg):"));
        txtAgirlik = new JTextField();
        formPanel.add(txtAgirlik);

        formPanel.add(new JLabel("  Mesafe (km):"));
        txtMesafe = new JTextField();
        formPanel.add(txtMesafe);

        formPanel.add(new JLabel("  Kargo Tipi:"));
        comboTip = new JComboBox<>(new String[]{"Standart", "Hizli", "Uluslararasi"});
        formPanel.add(comboTip);

        formPanel.add(new JLabel("  Gönderen Müşteri:"));
        comboMusteri = new JComboBox<>();
        musteriListesiniYukle();
        formPanel.add(comboMusteri);

        JButton btnEkle = new JButton("Yeni Kargo Kaydet");
        btnEkle.setBackground(new Color(144, 238, 144));
        formPanel.add(btnEkle);

        JButton btnGuncelle = new JButton("Durumu İlerlet");
        btnGuncelle.setBackground(new Color(173, 216, 230));
        formPanel.add(btnGuncelle);

        add(formPanel, BorderLayout.NORTH);

        String[] kolonlar = {"ID", "Müşteri", "Tip", "Ağırlık", "Mesafe", "Durum", "Toplam Ücret"};
        model = new DefaultTableModel(kolonlar, 0);
        tablo = new JTable(model);
        add(new JScrollPane(tablo), BorderLayout.CENTER);

        btnEkle.addActionListener(e -> {
            try {
                double agirlik = Double.parseDouble(txtAgirlik.getText());
                double mesafe = Double.parseDouble(txtMesafe.getText());
                String tip = (String) comboTip.getSelectedItem();
                Musteri secilenMusteri = (Musteri) comboMusteri.getSelectedItem();

                if (secilenMusteri == null) {
                    JOptionPane.showMessageDialog(this, "Önce müşteri eklemelisiniz!");
                    return;
                }

                Gonderi g;
                if (tip.equals("Hizli")) g = new HizliKargo(0, agirlik, mesafe, Durum.SUBEDE, secilenMusteri);
                else if (tip.equals("Uluslararasi")) g = new UluslararasiKargo(0, agirlik, mesafe, Durum.SUBEDE, secilenMusteri, 50.0);
                else g = new StandartKargo(0, agirlik, mesafe, Durum.SUBEDE, secilenMusteri);

                kargoService.kargoEkle(g);
                tabloyuGuncelle();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Giriş değerlerini kontrol edin!");
            }
        });

        btnGuncelle.addActionListener(e -> {
            int seciliSatir = tablo.getSelectedRow();
            if (seciliSatir == -1) {
                JOptionPane.showMessageDialog(this, "Lütfen tablodan bir kargo seçin!");
                return;
            }
            int kargoId = (int) tablo.getValueAt(seciliSatir, 0);
            List<Gonderi> kargolar = kargoService.kargoListele();
            for (Gonderi g : kargolar) {
                if (g.getId() == kargoId) {
                    if (g.getDurum() == Durum.SUBEDE) g.setDurum(Durum.DAGITIMDA);
                    else if (g.getDurum() == Durum.DAGITIMDA) g.setDurum(Durum.TESLIM_EDILDI);
                    else if (g.getDurum() == Durum.TESLIM_EDILDI) g.setDurum(Durum.IADE);
                    else g.setDurum(Durum.SUBEDE);
                    kargoService.kargoDurumGuncelle(g);
                    tabloyuGuncelle();
                    break;
                }
            }
        });

        tabloyuGuncelle();
    }

    public void tabloyuGuncelle() {
        model.setRowCount(0);
        List<Gonderi> liste = kargoService.kargoListele();
        for (Gonderi g : liste) {
            Object[] satir = {
                g.getId(), 
                g.getMusteri() != null ? g.getMusteri().getAd() : "Bilinmiyor",
                g.getClass().getSimpleName(), g.getAgirlik(), g.getMesafe(), g.getDurum(), g.ucretHesapla() + " TL"
            };
            model.addRow(satir);
        }
    }

    public void musteriListesiniYukle() {
        comboMusteri.removeAllItems();
        List<Musteri> musteriler = musteriService.musterileriGetir();
        for (Musteri m : musteriler) {
            comboMusteri.addItem(m);
        }
    }
}