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
    private JTextField ad, adres, kimlik, tel;

    public RegisterInternalFrame(MusteriService servis)
    {
        super("Yeni Müşteri Kaydı",false,true,false,true);
        this.servis=servis;
        this.setSize(350,350);
        this.setLayout(null);
        this.setLocation(50,80);
        
        JLabel l1=new JLabel("Ad Soyad:"); l1.setBounds(30,30,100,25); this.add(l1);
        ad=new JTextField(); ad.setBounds(130,30,150,25); this.add(ad);
        
        JLabel l2=new JLabel("TC Kimlik No:"); l2.setBounds(30,70,100,25); this.add(l2);
        kimlik=new JTextField(); kimlik.setBounds(130,70,150,25); this.add(kimlik);
        
        JLabel l3=new JLabel("Telefon:"); l3.setBounds(30, 110, 100, 25); this.add(l3);
        tel=new JTextField(); tel.setBounds(130, 110, 150, 25); this.add(tel);
        
        JLabel l4=new JLabel("Adres:"); l4.setBounds(30, 150, 100, 25); this.add(l4);
        adres=new JTextField(); adres.setBounds(130, 150, 150, 25); this.add(adres);
        
        JButton btn = new JButton("Müşteriyi Kaydet"); btn.setBounds(130, 200, 150, 35); this.add(btn);
        
        btn.addActionListener(new ActionListener() 
        {
            public void actionPerformed(ActionEvent e)
            {
                Musteri m = new Musteri(ad.getText(), kimlik.getText(), tel.getText(), adres.getText());
                try {
                    servis.musteriKaydet(m);
                    JOptionPane.showMessageDialog(null,"Müşteri başarıyla sisteme kaydedildi.");
                    ad.setText(""); kimlik.setText(""); tel.setText(""); adres.setText("");
                }
                catch(Exception exe) {
                    JOptionPane.showMessageDialog(null,"Kayıt başarısız: " + exe.getMessage());
                }
            }
        });
    }
}
