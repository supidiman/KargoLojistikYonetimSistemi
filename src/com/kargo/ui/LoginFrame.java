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

public class LoginFrame extends JFrame {
	private JButton  buttongir;
	private JButton buttonkayit;
	private JTextField ad;
	private JPasswordField sifre;//text field değil çünkü *** şifre görünmemeli girererken 
	private JLabel etiket;
	private JLabel etiket2;
	private AuthService authservice=new AuthService(new PersonelDAO());//constructorıyla beraber oluşturduk nesnesini ve private olarak aldık bu ekrana
	
	public LoginFrame()
	{
		this.setTitle("GİRİS EKRANI");
		this.setSize(400,300);
		this.setLayout(null);//ekrana manuel olarak yerleştirecez buton text şifre alanı vb
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//ekranı kapatınca program kapansın arkada çalışmasıyn
		etiket =new JLabel("adı :");
		etiket.setBounds(50,50,100,30);//boyutunu ayarladık 
		this.add(etiket);//şimdi ekrana yerleştirelim etiketimizi
		ad=new JTextField();//parantez boş dışardan kullancıdan veriyi alacağız ilk hali boş görüncek
		ad.setBounds(150,50,150,30);
		this.add(ad);
		etiket2=new JLabel("sifre :");
		etiket2.setBounds(50,100,100,30);
		this.add(etiket2);
		sifre=new JPasswordField();
		sifre.setBounds(150,100,150,30);
		this.add(sifre);
		
		
		buttongir=new JButton("GİRİS");//butonun üsütünde gir yazsın
		buttongir.setBounds(150,150,100,30);
		this.add(buttongir);
		
		buttonkayit=new JButton("KAYIT OL");
		buttonkayit.setBounds(150,190,100,30);
		this.add(buttonkayit);
		
		buttongir.addActionListener(    // butonun iç mekaniği burda ona bi can veriyoz
				new ActionListener() //action listener interface i açtık içine bazı özelliklerini kullancaz 
		{
			public void actionPerformed(ActionEvent e)
			{
				String isim=ad.getText();
				String sifre=new String(LoginFrame.this.sifre.getPassword());//this diyince artık Logine değil actionlidstener clşassında arıyor değişkeni o yüzden mecbur class adınıda verecem kolay bulsun
				boolean BasardikMi=authservice.login(isim,sifre);
				if(BasardikMi ==true) 
				{
					JOptionPane.showMessageDialog(LoginFrame.this,"Basrılı Giris");//this loginframe tam login ekranının ortasında yazsın,giris basarılı yazsın
						dispose();
						MusteriPanel panel =new MusteriPanel();
						panel.setVisible(true);
						
						
				}
				
				else {
					JOptionPane.showMessageDialog(LoginFrame.this,"HAY AKSİ BASARIZ TEŞEBBÜS");
					
				}
				
			}
			
			
			
		}
		
		
		
		);
		
		
		buttonkayit.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				dispose();//KAPAT EKRANI EKRAN NE LOGİN EKRANI KAPAT
				RegisterFrame kayitekrani =new RegisterFrame();
				kayitekrani.setVisible(true);//şimdi bunu kapattık kayıt ekranı yeni pencereyi göster bize
			}
		});
		
		
		
		
		
		
		
		
		
		
	}
	

}
