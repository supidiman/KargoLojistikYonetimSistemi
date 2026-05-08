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
import com.kargo.dao.MusteriDAO;
import com.kargo.model.Personel;

public class RegisterFrame extends JFrame {
	
	
	private JButton iptal;
	private JTextField ad;
	private JPasswordField sifre;
	private JLabel label1;
	private JLabel label2;
	private JButton kaydet;
	
	
	private AuthService authservice =new AuthService(new PersonelDAO());
	
	public RegisterFrame() {//constructora yine loginde açıkladığım durumlar
		this.setTitle("KAYIT EKRANI");
		this.setSize(400,400);
		this.setLayout(null);
		this.setLocationRelativeTo(null);//pencere direkt ekran ortasında açılsın
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//yine bu sayfadada istediğin an programı kapatabilirsin
		
				label1=new JLabel("Adınız:");
				label1.setBounds(30,30,100,30);
				this.add(label1);
				ad =new JTextField();
				ad.setBounds(150, 30, 100, 50);
				this.add(ad);
				label2=new JLabel("sifreniz:");
				label2.setBounds(60, 60, 100,30);
				this.add(label2);
				sifre=new JPasswordField();
				sifre.setBounds(160,60,100,30);
				this.add(sifre);
				iptal=new JButton();//private değişkene yeni nesne oluşturduk
				iptal.setBounds(60,210,100,50);//nereye yerleşceğini yazdım
				this.add(iptal);//ekrana ekle
				
				iptal.addActionListener(new ActionListener() {//Action listener interfaceinden zorunlu actionperformed i implement ettik
					public void actionPerformed(ActionEvent e) {
						dispose();//işlem bitti kaydı aldı kapat 
						new LoginFrame().setVisible(true);//import grerekmez çünkü ui paketinde ikiside
						//login ekranını yine aç
						
						
						
						
					}
				});
				
				kaydet=new JButton("KAYDET");
				kaydet.setBounds(180,210,100,50);
				this.add(kaydet);
				
				kaydet.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						String ad=RegisterFrame.this.ad.getText();
						String sifre=new String(RegisterFrame.this.sifre.getPassword());
						Personel personel=new Personel();//registerımpersonal parmetresi istiyo paketleyip şutluyacağım
						personel.setkullaniciAdi(ad);
						personel.setsifre(sifre);
						try {
							
							authservice.register(personel);
							
							
							
							//authservice.register(personel);bu olmuyor çünkü parametresiz personal set le direkt içine atama yapcaz 
							JOptionPane.showMessageDialog(null, "KAYIT BASARILI");
							dispose();
							new LoginFrame().setVisible(true);
							
						}
						catch(Exception ex) {
							JOptionPane.showMessageDialog(null,"AAA Bİ HATA MEYDANA GELDİ"+ex.getMessage());//frontendde hata fırlatılmaz mimari olarsk mesaj verdim zaten backenddeki kontrolu geçemicek ordada patlıcak 
						}
						
						
					}
				});
				
				
		
		
	}

}
