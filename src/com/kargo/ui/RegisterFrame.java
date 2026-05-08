package com.kargo.ui;

import com.kargo.model.Personel;
import com.kargo.service.AuthService;
import javax.swing.*;
import java.awt.*;

public class RegisterFrame extends JFrame {
    private JTextField txtKullaniciAdi;
    private JPasswordField txtSifre;
    private AuthService authService;

    public RegisterFrame() {
        authService = new AuthService();

        setTitle("Kargo Lojistik - Personel Kayıt");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Sadece bu pencereyi kapat
        setLocationRelativeTo(null); // Ekranın tam ortasında açılır
        setLayout(new GridLayout(3, 2, 10, 10));

        add(new JLabel("  Yeni Kullanıcı Adı:"));
        txtKullaniciAdi = new JTextField();
        add(txtKullaniciAdi);

        add(new JLabel("  Yeni Şifre:"));
        txtSifre = new JPasswordField();
        add(txtSifre);

        JButton btnIptal = new JButton("İptal");
        JButton btnKaydet = new JButton("Kaydet");

        add(btnIptal);
        add(btnKaydet);

        // Kaydet butonuna tıklanınca olacaklar:
        btnKaydet.addActionListener(e -> {
            Personel p = new Personel(0, txtKullaniciAdi.getText(), new String(txtSifre.getPassword()));
            if (authService.register(p)) {
                JOptionPane.showMessageDialog(this, "Kayıt Başarılı! Şimdi giriş yapabilirsiniz.");
                dispose(); // Kayıt ekranını kapat
            } else {
                JOptionPane.showMessageDialog(this, "Kayıt Başarısız! En az 4 haneli şifre girin.");
            }
        });

        // İptal butonuna tıklanınca:
        btnIptal.addActionListener(e -> dispose());
    }
}