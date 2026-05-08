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

public class LoginFrame extends JFrame {
	private JButton  buttongir;
	private JButton buttonkayit;
	private JTextField ad;
	private JPasswordField sifre;
	private JLabel etiket;
	private JLabel etiket2;
	private AuthService authservice = new AuthService(new PersonelDAO());
	
	public LoginFrame()
	{
		this.setTitle("GİRİS EKRANI");
		this.setSize(400,300);
		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);

		etiket = new JLabel("adı :");
		etiket.setBounds(50,50,100,30);
		this.add(etiket);

		ad = new JTextField();
		ad.setBounds(150,50,150,30);
		this.add(ad);

		etiket2 = new JLabel("sifre :");
		etiket2.setBounds(50,100,100,30);
		this.add(etiket2);

		sifre = new JPasswordField();
		sifre.setBounds(150,100,150,30);
		this.add(sifre);

		buttongir = new JButton("GİRİS");
		buttongir.setBounds(150,150,100,30);
		this.add(buttongir);

		buttonkayit = new JButton("KAYIT OL");
		buttonkayit.setBounds(150,190,100,30);
		this.add(buttonkayit);

		buttongir.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				String isim = ad.getText();
				String sifreText = new String(LoginFrame.this.sifre.getPassword());
				boolean BasardikMi = authservice.login(isim, sifreText);
				if(BasardikMi == true) 
				{
					JOptionPane.showMessageDialog(LoginFrame.this, "Basarılı Giris");
					dispose();
					MainFrame anaEkran = new MainFrame();
					anaEkran.setVisible(true);
				}
				else {
					JOptionPane.showMessageDialog(LoginFrame.this, "HAY AKSİ BASARISIZ TEŞEBBÜS");
				}
			}
		});

		buttonkayit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				RegisterFrame kayitekrani = new RegisterFrame();
				kayitekrani.setVisible(true);
			}
		});
	}
}