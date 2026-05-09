package com.kargo.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.kargo.service.AuthService;
import com.kargo.dao.PersonelDAO;
import com.kargo.model.Personel;

public class RegisterFrame extends JFrame {
    private static final long serialVersionUID = 1L;
	
	private JButton iptal;
	private JTextField ad;
	private JPasswordField sifre;
	private JLabel label1;
	private JLabel label2;
	private JButton kaydet;
	
	private AuthService authservice = new AuthService(new PersonelDAO());
	
	public RegisterFrame() {
		this.setTitle("Personel Kayıt Ekranı");
		this.setSize(400,400);
		this.setLayout(null);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		label1=new JLabel("Kullanıcı Adı:");
		label1.setBounds(30,30,100,30);
		this.add(label1);
		ad =new JTextField();
		ad.setBounds(150, 30, 100, 50);
		this.add(ad);
		label2=new JLabel("Şifre:");
		label2.setBounds(60, 60, 100,30);
		this.add(label2);
		sifre=new JPasswordField();
		sifre.setBounds(160,60,100,30);
		this.add(sifre);
		
		iptal=new JButton("İPTAL");
		iptal.setBounds(60,210,100,50);
		this.add(iptal);
		
		iptal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new LoginFrame().setVisible(true);
			}
		});
		
		kaydet=new JButton("Kaydet");
		kaydet.setBounds(180,210,100,50);
		this.add(kaydet);
		
		kaydet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String adText=RegisterFrame.this.ad.getText();
				String sifreText=new String(RegisterFrame.this.sifre.getPassword());
				Personel personel=new Personel();
				personel.setKullaniciAdi(adText);
				personel.setSifre(sifreText);
				try {
					authservice.register(personel);
					JOptionPane.showMessageDialog(null, "Kayıt başarıyla tamamlandı. Giriş yapabilirsiniz.");
					dispose();
					new LoginFrame().setVisible(true);
				}
				catch(Exception ex) {
					JOptionPane.showMessageDialog(null,"Kayıt sırasında bir hata oluştu: " + ex.getMessage());
				}
			}
		});
	}
}
