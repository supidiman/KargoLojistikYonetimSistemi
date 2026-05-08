package com.kargo.ui;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.kargo.service.MusteriService;
import com.kargo.model.Musteri;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class ConsumerInternalList extends JInternalFrame 
{
private MusteriService servis;

private DefaultTableModel tabloformat;
private JTable consumertable;
private JScrollPane scrollpane;


private JTextField ad;
private JTextField adres;
private JTextField kimlik;
private JTextField tel;

private JLabel label1;
private JLabel label2;
private JLabel label3;
private JLabel label4;


private JButton güncelle;
private JButton sil;

ConsumerInternalList(MusteriService servis)
{
	
	super("kayıtı musteri listesi",false,true,false,true);//en üste yazılmak zorunda super registerinternalla aynı ayarları yaptım
	this.servis=servis;
	this.setSize(750,450);
	this.setLayout(null);
	this.setLocation(100,50);
	
	tabloformat=new DefaultTableModel();
	tabloformat.addColumn("ISIM");//tablo sütunları
	tabloformat.addColumn("KIMLIK");
	tabloformat.addColumn("TELEFON");
	tabloformat.addColumn("ADRES");
	
	consumertable=new JTable(tabloformat);
	scrollpane=new JScrollPane(consumertable);//tablonun kaydırma (scroll) özelliği kazandırmak için scrollpane konteynıra koyduk
	scrollpane.setBounds(20,20,450,370);
	this.add(scrollpane);//ekrana kondur
	int sagx =490;
	
	label1=new JLabel("AD");
	label1.setBounds(sagx,200,100,25);
    this.add(label1);
    
    ad=new JTextField();
    ad.setBounds(sagx,45,200,25);
    this.add(ad);
    
    label2=new JLabel("KİMLİK NUM");
    label2.setBounds(sagx,80,100,25);
    this.add(label2);
    
    kimlik=new JTextField();
    kimlik.setBounds(sagx,105,200,25);
    kimlik.setEditable(false);//******tc değişmez değiştirilemez ********
    this.add(kimlik);
    
    label3=new JLabel("TELEFON NO");
	label3.setBounds(sagx,140,100,25);
    this.add(label3);
    
   tel=new JTextField();
    tel.setBounds(sagx,165,200,25);
    this.add(tel);
    
    label4=new JLabel("ADRES");
	label4.setBounds(sagx,200,100,25);
    this.add(label4);
    
    adres=new JTextField();
    adres.setBounds(sagx,225,200,25);
    this.add(adres);
    
    güncelle=new JButton("GUNCELLE");
    güncelle.setBounds(sagx, 280, 200, 35);
    this.add(güncelle);
    
    sil=new JButton("SIL");
    sil.setBounds(sagx,330,200,35);
    this.add(sil);
    
    consumertable.addMouseListener(new MouseAdapter()
    {//listener varsa direkt iç class çağrıyoz zaten şimdiye kadar her seferinde
    	
    	public void mouseClicked(MouseEvent e ) 
    	{//actionPerformed(ActionEvent e ydi buton için olan da benziye
    		
    		
    		int satir=consumertable.getSelectedRow();
    		
    		ad.setText(tabloformat.getValueAt(satir,0).toString());
    		kimlik.setText(tabloformat.getValueAt(satir,1).toString());
    		
    		tel.setText(tabloformat.getValueAt(satir,2).toString());
    		adres.setText(tabloformat.getValueAt(satir,3).toString());
    		
    		
    		
	
    	}
    	
    	
    });
    
    güncelle.addActionListener(new ActionListener() 
    {
    	public void actionPerformed( ActionEvent e) 
    	{
    		String ad=ConsumerInternalList.this.ad.getText();
    		String kimlik=ConsumerInternalList.this.kimlik.getText();
    		String tel=ConsumerInternalList.this.tel.getText();
    		String adres=ConsumerInternalList.this.adres.getText();
    	
    		if(kimlik.isEmpty()==true) {
    			
    			JOptionPane.showMessageDialog(null,"HATA:müsteri sec");
    			
    			
    			
    			return; //işlemi kes direkt
    			
    			
    			
    			
    			
    			}
    			else 
    			
    			{
    				
    				try {
    					Musteri yenimusteri=new Musteri(ad,kimlik,tel,adres);
    					servis.MusteriGuncelle(yenimusteri);
    					tabloyulistele();
    					JTextField [] dizi= {ConsumerInternalList.this.ad,ConsumerInternalList.this.kimlik,ConsumerInternalList.this.tel,ConsumerInternalList.this.adres};
    					int i=0;
    					while(i< dizi.length)
    					{
    						dizi[i].setText(""); //text boxları 0 la
    						i++;
    					}
    					
    				}catch(Exception ex)
    				{
    					JOptionPane.showMessageDialog(null, "Güncelleme Hatası: " + ex.getMessage());
    				}
    				
    				
    				
    			}
    			
    		
    	}
    	
    });
    
    sil.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent e) 
    	{
    		String kimlik=ConsumerInternalList.this.kimlik.getText();
    		if(kimlik.isEmpty()) {
    			JOptionPane.showMessageDialog(null,"tıkla seçtiğinden emin ol");
    			return; //bıçak gibi kes işlemi
    		}
    		
    		try{
    			
    			servis.MusteriSil(kimlik);
    			tabloyulistele();//yeni halini görelim
    			JTextField [] dizi= {ConsumerInternalList.this.ad,ConsumerInternalList.this.kimlik,ConsumerInternalList.this.tel,ConsumerInternalList.this.adres};
				int i=0;
				while(i< dizi.length)
				{
					dizi[i].setText(""); //text boxları 0 la
					i++;
				}
				
    			
    		}catch(Exception ex) {
    			JOptionPane.showMessageDialog(null,"HATA"+ex.getMessage());
    		}
    		
    	}
    });
    
    
    tabloyulistele();
	
}


private void tabloyulistele() 
{
	for(int i=tabloformat.getRowCount()-1;i>=0;i--)//tabloda veriler birikmesin diye temizledik
	{
		tabloformat.removeRow(i);
	}
	try 
	{
		List<Musteri>musteriler=servis.Musterilerigetir();//servisteki metodla bağlantı kur oda müsteri ile bağıntılı
		if(musteriler !=null) 
		{
			for(int i=0;i<musteriler.size();i++)
			{
				Musteri m=musteriler.get(i);
				Object[] satir= {m.getad(),m.getkimlik(),m.gettel(),m.getadres()};
				tabloformat.addRow(satir);
				
				
				
			}
		}
	}
	catch(Exception ex) 
	{
		JOptionPane.showMessageDialog(this, "Kayıtlar yüklenemedi:" + ex.getMessage());
		
	}
	finally 
	{
		
	}
}





}

