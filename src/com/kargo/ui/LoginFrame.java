package com.kargo.ui;

import com.kargo.service.AuthService;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {
    private JTextField txtKullaniciAdi;
    private JPasswordField txtSifre;
    private AuthService authService;

    public LoginFrame() {
        // Servis katmanını başlatıyoruz
        authService = new AuthService();

        // Pencere Ayarları
        setTitle("Kargo Lojistik - Personel Girişi");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Ekranın ortasında açılır
        setLayout(new BorderLayout(10, 10));

        // Başlık Paneli
        JPanel pnlBaslik = new JPanel();
        pnlBaslik.add(new JLabel("Sisteme Giriş Yapın"));
        add(pnlBaslik, BorderLayout.NORTH);

        // Giriş Formu Paneli
        JPanel pnlForm = new JPanel(new GridLayout(2, 2, 10, 10));
        pnlForm.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        pnlForm.add(new JLabel("Kullanıcı Adı:"));
        txtKullaniciAdi = new JTextField();
        pnlForm.add(txtKullaniciAdi);

        pnlForm.add(new JLabel("Şifre:"));
        txtSifre = new JPasswordField();
        pnlForm.add(txtSifre);

        add(pnlForm, BorderLayout.CENTER);

        // Butonlar Paneli
        JPanel pnlButonlar = new JPanel();
        JButton btnKayit = new JButton("Kayıt Ol");
        JButton btnGiris = new JButton("Giriş Yap");
        
        pnlButonlar.add(btnKayit);
        pnlButonlar.add(btnGiris);
        add(pnlButonlar, BorderLayout.SOUTH);

        // --- BUTON AKSİYONLARI ---

        // Giriş Yap Butonu
        btnGiris.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String kadi = txtKullaniciAdi.getText();
                String sifre = new String(txtSifre.getPassword());

                // AuthService üzerinden veritabanı kontrolü yapılıyor
                if (authService.login(kadi, sifre)) {
                    JOptionPane.showMessageDialog(null, "Giriş Başarılı! Ana sayfaya yönlendiriliyorsunuz.");
                    
                    // Giriş başarılıysa MainFrame'i (Ana Sayfa) aç
                    new MainFrame().setVisible(true); 
                    
                    // Mevcut Login penceresini kapat
                    dispose(); 
                } else {
                    JOptionPane.showMessageDialog(null, "Hatalı kullanıcı adı veya şifre!", "Giriş Hatası", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Kayıt Ol Butonu
        btnKayit.addActionListener(e -> {
            // Kayıt penceresini açar
            new RegisterFrame().setVisible(true);
        });
    }
}