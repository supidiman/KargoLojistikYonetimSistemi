package com.kargo.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;

import com.kargo.service.MusteriService;

public class MusteriPanel extends JFrame {
	
	private MusteriService servis=new MusteriService(new MusteriDAO());
	private JDesktopPane ana;//internalframe bu tip konteynırın içinde çalışabilir
	private JButton butonekle;
	private JButton butonlistele;
	
	
	public MusteriPanel()
	{
		this.setTitle("MUSTERI PANELI");
		this.setSize(800,700);
		this.setLocationRelativeTo(null);//ekranın ortasında açılsın bir butona göre hizalanmasın
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//programı kapat tuşu burda da olsun
		ana =new JDesktopPane();
		this.setContentPane(ana);//şimdi ana yı ekrana yerleştirdik artık ekranı ana kapsıyor zemini ana.ana artık content panin yerine geçti
		ana.setLayout(null);//yerleşme konumunu bena atyacağım
		
		butonekle =new JButton("MUSTERI EKLE");//Musteri ekle isimli buton tipini ata butonekleye
		butonekle.setBounds(20,20,150,40);//sol üst köşe oriji kabul edersek konumu
		this.add(butonekle);//ekrana koy 
		
		butonekle.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) 
			{
				RegisterInternalFrame eklepenceresi =new RegisterInternalFrame(servis);//BURDA TANIMLADIM CONSTRURTOR DIŞINDA DEĞİL ÇÜNKÜ ŞUAN BAŞKA SINIFIN İÇİNDEYİZ GÖREMEYEBİLİR
				ana.add(eklepenceresi);
				eklepenceresi.setVisible(true);//bunu aç diğerini kapatmadım zaten fikir bu ekran üstünde hemen bi iç pencere açılcak
				
				
			}
		});
		
		butonlistele=new JButton("LISTELE");//ismi listele olan butonu ata buton listeleye
		butonlistele.setBounds(180,20,150,40);//bu konumda sol üst köşe orij,in olarak
		this.add(butonlistele);//ekrana ekle
		
		butonlistele.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				ConsumerInternalList listelepenceresi=new ConsumerInternalList(servis);
				ana.add(listelepenceresi);
				listelepenceresi.setVisible(true);
				
				
				
			}
		});
		
		
		
		
	
		
		
		
	}
	public MusteriService getServis() 
	{
		return this.servis;
	}
	
	

}
