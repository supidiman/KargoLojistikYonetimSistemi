package com.kargo.service;
import com.kargo.dao.IPersonelDAO;
import com.kargo.model.Personel;


public class AuthService {
	
	
		private final IPersonelDAO personeldao;//public classta bu veri private erişimi önlemek ve dğiştirilmesini önlemek amacıyla
		
		public AuthService(IPersonelDAO personeldao)//cosnstructor tanımlandı bağlantı kurmak için daoyla 
		{
			this.personeldao=personaldao;//parametremiz interface çünkü gelecekte yapılacak değişikliklere daha uyumlu ve görece daha az doğrudan bağlı artık veritabanındaki classlara
		}
		public boolean login(String kullaniciadi,String sifre)//hata kontrolünü bu metod ile yapıp uı kısmına cevap göndereceğim
		{
			if(kullaniciadi==null || sifre ==null|| kullaniciadi.trim().isEmpty())//nullpointerexception almamak için önden kontrol yapıyoz ayrıca boşluk tuşu isim olarak kabul görmemeli
			{
				return false;
			}
			/*if(kullaniciadi.equals("Admin") && sifre.equals("1234"))//equals koydum çünkü == adreslerden kontrolü yapıyor ama equals içeriklere bakıyor
			{
				return true;
			}*/
			try {
				boolean personeldogrula=personeldao.PersonelDogrula(kullaniciadi,sifre);// BU METODU EKLEYELİM YUNUSA DE !!!!!!***********
				return personeldogrula;
			}
			catch(Exception e) 
			{
				throw new RuntimeException("giriş işlemi sırasında hata",e);
			}
			
			
			
		}
		private  boolean gecerlimi (Personel p) //1 üsttekine göre çeşitlilik olsun istedim
		{
			return p!=null && p.getSifre()!=null  && p.getSifre().length()>=4 && p.getKullaniciAdi()!=null && !p.getKullaniciAdi().trim().isEmpty()  ;//ve ile bağlı olduklarından mütevellit hepsi true ysa ancak true döndürcek 
		}
		
		public boolean register(Personel person) //kullanıcı girişi kontrol eden metod
		{
			if(gecerlimi(person)!=true)
			{
				throw new IllegalArgumentException("hatalı deneme lütfen sadece boşluk kullanmadan,veri girme alanlarını boş bırakmadan ve şifreyi en az 4 hane olacak şekilde tekrar deneyiniz");
				
			}
			
			try {
				personeldao.ekle(person);
				System.out.println(person.getKullaniciAdi()+ "işlem gerçekleştirildi daoya iletiliyor");
				return true;
			}
			catch(Exception ex) 
			{
				throw new RuntimeException("kayıt hatası",ex );
			}
			
		}
		
		
		
		
		
		
		

	}



