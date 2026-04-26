package com.kargo.service;


import java.util.List;

import com.kargo.dao.IMusteriDAO;
import com.kargo.model.Musteri;

public class MusteriService
{
	private final IMusteriDAO musteridao;//erişilmesini önlemek için private değiştirlmesi önlemek için final
	public MusteriService(IMusteriDAO musteridao)//constructor daodan gelen arayüzü atamak için
	{
		this.musteridao=musteridao;
	}
	
	public void musteriKaydettim(Musteri m)
	{
		
		this.validation(m);
		try {
			musteridao.ekle(m);
			System.out.println("basarıyla kaydedildi");
		}
		catch(Exception ex) //genel exception dan sql exception yakaladık mesela onu runtime paketine koyup fırlattık böylece yarın kullandığımız veritabanı uygulaması değiştiğinde ,burda güncelleme yapmamaıza gerek olmayacak
		{
			throw new RuntimeException("veri tabanına kayıt sırasında bir hata oluştu"+ex.getMessage());
		}
			
	}
	public void validation(Musteri guncelmusteri) 
	{
		if (guncelmusteri==null) {//AuthService registerda işlem devam etsin istiyoduk ama burda bu nesne işlmei durdursun ve hata fırlatsın istiyoruz daha büyük faciaları engellemek adına
			throw new IllegalArgumentException("güncelleme yapılan nesne boş olmamalı");
			
		}
			String tc=guncelmusteri.getTc();//getTcc görece daha mahrem olduğu için tc ye attım
			String tel=guncelmusteri.getTelefon();//""
			String adres=guncelmusteri.getAdres();//""
			String ad=guncelmusteri.getAd();//""
			if(ad==null ||  ad.trim().isEmpty()) {
				throw new IllegalArgumentException("ad null olmamalı ve sadece boşluk kullanılmamalı");
			}
			if( tc==null|| !tc.matches("\\d{11}"))//desenlerle istediğimiz kalıbı daha az kod yazarak kontrol ediyoruz
			{
		throw new IllegalArgumentException("tc kimlik 11 haneden olmalı ve tamamı rakamlardan oluşmalıdır!!");
			}
	if(tel==null|| !tel.matches("05\\d{9}"))
	{
		throw new IllegalArgumentException("telefon numarası 05 le başlamalı toplam 11 rakam içermelidir");
	}
	if(adres ==null|| adres.isBlank())
	{
	throw new IllegalArgumentException("adresi boş bırakmamalısınız");	
	}
	}
	
	
	
	
	
	
	public void MusteriGuncelle(Musteri guncelmusteri) 
	{  
		this.validation(guncelmusteri);

try {
	musteridao.guncelle(guncelmusteri);
	System.out.println("basarıyla güncellendi");
}
catch(Exception ex) 
{
	throw new RuntimeException("veri tabanına güncelleme sırasında bir hata oluştu"+ex.getMessage());
}
		
		
		
	}
	
	public List<Musteri> Musterilerigetir()
	{ try
	{
		return musteridao.Listele() ;
	}
	catch(Exception e) 
	{
		throw new RuntimeException(e);
	}
		
	}

}
