package com.kargo.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.kargo.service.MusteriService;
import com.kargo.model.Musteri;

public class RegisterInternalFrame extends JInternalFrame 
{
private MusteriService servis;

private JTextField ad;
private JTextField adres;
private JTextField kimlik;
private JTextField tel;

private JButton kaydet;

private JLabel lad;
private JLabel ladres;
private JLabel lkimlik;
private JLabel ltel;
private JLabel lkaydet;

RegisterInternalFrame(MusteriService servis)
{
	super("yeni müsteri ekeleme ",false,true,false,true);//başlık-pencere köşeden büyütülmez küçlültülmez-kapatabilirsin-büyütmr yok-küçültme var ikon halinde
    this.servis=servis;
    this.setSize(350,350);
    this.setLayout(null);
    this.setLocation(50,80);
    
    lad=new JLabel("adınız:");
    lad.setBounds(30,30,100,25);
    this.add(lad);
    
    ad=new JTextField();
    ad.setBounds(130,30,150,25);
    this.add(ad);
    
    
    
    lkimlik=new JLabel("kimlik numaranız:");
    lkimlik.setBounds(30,70,100,25);
    this.add(lkimlik);
    
    kimlik=new JTextField();
    kimlik.setBounds(130,70,150,25);
    this.add(kimlik);
    
    ltel=new JLabel("telefon numaranız:");
    ltel.setBounds(30, 110, 100, 25);
    this.add(ltel);
    
    tel=new JTextField();
    tel.setBounds(130, 110, 150, 25);
    this.add(tel);
    
    ladres=new JLabel("adresiniz:");
    ladres.setBounds(30, 150, 100, 25);
    this.add(ladres);
    
    adres=new JTextField();
    adres.setBounds(130, 150, 150, 25);
    this.add(adres);
    
    kaydet=new JButton("KAYDET");
  kaydet.setBounds(130, 200, 150, 35);  
  this.add(kaydet);
  
  kaydet.addActionListener(new ActionListener() 
  {
	  public void actionPerformed(ActionEvent e)
	  {
		 String kimlik=RegisterInternalFrame.this.kimlik.getText();//kimlik kapsayıcı dış classtan burdaki kimliğe ata
		 String adres=RegisterInternalFrame.this.adres.getText();
		 String ad=RegisterInternalFrame.this.ad.getText();
		 String tel=RegisterInternalFrame.this.tel.getText();
		 Musteri m=new Musteri(ad,kimlik,tel,adres);
		 try {
			 servis.musteriKaydettim(m);//buradan servisteki metoda parametreyle attım
			 JOptionPane.showMessageDialog(null,"BASARIYLA KAYDEDILDI");//eğer yukarda patlamazsa zaten başarılı olmuş mesajı yazabilşrim 
			 //null çünkü herhangi sfesifik bi pencere yok açılmasını istediğim
			 JTextField[] alanlar= {RegisterInternalFrame.this.ad,RegisterInternalFrame.this.kimlik,RegisterInternalFrame.this.adres,RegisterInternalFrame.this.tel};
			 //bu dizi tanımı uzun oldu ama this.ad i görmediği için ana sınıfın adını yazdım hepsine değişkenleri yazmadım çünkü amaceım text metodunun içini 0 lamak
			 for(int i=0;i<alanlar.length;i++) //içleri tekrar 0 lıcam sonraki kullanım için 
			 {
				 alanlar[i].setText("");
				 
			 }
			 
			 
		 }
		 catch(Exception exe)
		 {
			 JOptionPane.showMessageDialog(null,"HAY AKSI BIR AKSAKLIK OLDU  HATANIZ: "+exe.getMessage());//throw yaptığım hataalr gözükücek diye umut ediyorum service katmanda
			 
		 }
		 finally
		 {
			 
		 }
		 
		 
		 
		 
		 
		  
		  
		  
	  }
  });
  
    
    
    
    
    
	
	
	
}


	
	
	
}
