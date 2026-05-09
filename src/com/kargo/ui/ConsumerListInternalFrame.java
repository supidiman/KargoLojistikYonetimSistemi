package com.kargo.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import com.kargo.service.MusteriService;
import com.kargo.model.Musteri;
import java.awt.event.*;
import java.util.List;

public class ConsumerListInternalFrame extends JInternalFrame 
{
    private static final long serialVersionUID = 1L;
    private MusteriService servis;
    private DefaultTableModel tabloformat;
    private JTable consumertable;

    public ConsumerListInternalFrame(MusteriService servis)
    {
        super("Kayıtlı Müşteri Listesi", false, true, false, true);
        this.servis = servis;
        this.setSize(750, 450);
        this.setLayout(new java.awt.BorderLayout());
        
        tabloformat = new DefaultTableModel();
        tabloformat.addColumn("Ad Soyad");
        tabloformat.addColumn("TC Kimlik No");
        tabloformat.addColumn("Telefon");
        tabloformat.addColumn("Adres");
        
        consumertable = new JTable(tabloformat);
        this.add(new JScrollPane(consumertable));
        
        tabloyulistele();
    }

    private void tabloyulistele() 
    {
        tabloformat.setRowCount(0);
        try 
        {
            List<Musteri> musteriler = servis.musterileriGetir();
            if(musteriler != null) 
            {
                for(Musteri m : musteriler)
                {
                    Object[] satir = {m.getAd(), m.getTc(), m.getTelefon(), m.getAdres()};
                    tabloformat.addRow(satir);
                }
            }
        }
        catch(Exception ex) 
        {
            JOptionPane.showMessageDialog(this, "Müşteri listesi yüklenemedi: " + ex.getMessage());
        }
    }
}
