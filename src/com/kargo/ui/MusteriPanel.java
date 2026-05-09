package com.kargo.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JPanel;
import com.kargo.service.MusteriService;
import com.kargo.dao.MusteriDAO;

public class MusteriPanel extends JPanel {
	
	private MusteriService servis = new MusteriService(new MusteriDAO());
	private JDesktopPane ana;
	private JButton butonekle;
	private JButton butonlistele;
	
	public MusteriPanel()
	{
		this.setLayout(new BorderLayout());
		ana = new JDesktopPane();
		this.add(ana, BorderLayout.CENTER);
		ana.setLayout(null);
		
		butonekle = new JButton("Yeni Müşteri Ekle");
		butonekle.setBounds(20,20,150,40);
		ana.add(butonekle);
		
		butonekle.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) 
			{
				RegisterInternalFrame eklepenceresi = new RegisterInternalFrame(servis);
				ana.add(eklepenceresi);
				eklepenceresi.setVisible(true);
			}
		});
		
		butonlistele=new JButton("Müşterileri Listele");
		butonlistele.setBounds(180,20,150,40);
		ana.add(butonlistele);
		
		butonlistele.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				ConsumerListInternalFrame listelepenceresi = new ConsumerListInternalFrame(servis);
				ana.add(listelepenceresi);
				listelepenceresi.setVisible(true);
			}
		});
	}

	public MusteriService getServis() {
		return this.servis;
	}
}
