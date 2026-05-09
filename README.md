🚚 Kargo Lojistik Yönetim Sistemi

Modern lojistik operasyonlarını dijital ortamda yönetmek amacıyla geliştirilen kapsamlı bir Kargo ve Lojistik Yönetim Sistemi.



Bu proje; kullanıcı yönetimi, operasyon süreçleri, veri tabanı yönetimi ve nesne yönelimli yazılım mimarisi prensipleri temel alınarak geliştirilmiştir.

🎯 Proje Amacı

Bu sistemin temel amacı:





Kargo operasyonlarını dijitalleştirmek



Kullanıcı ve personel yönetimini kolaylaştırmak



Sipariş ve sevkiyat süreçlerini merkezi şekilde yönetmek



Modüler ve sürdürülebilir bir yazılım mimarisi oluşturmak



OOP prensiplerine uygun ölçeklenebilir bir yapı sunmaktır

🛠️ Kullanılan Teknolojiler

Java

Java Swing

JDBC

MySQL / SQL

DAO Design Pattern

Object-Oriented Programming (OOP)

📂 Proje Mimarisi



KargoLojistikYonetimSistemi

│

├── src/

│ ├── database/

│ │ ├── DBConnection.java

│ │ └── DAO/

│ │

│ ├── model/

│ │ ├── Kullanici.java

│ │ ├── Kargo.java

│ │ ├── Personel.java

│ │ └── Musteri.java

│ │

│ ├── service/

│ │ ├── KullaniciService.java

│ │ ├── KargoService.java

│ │ └── OperasyonService.java

│ │

│ ├── ui/

│ │ ├── LoginPanel.java

│ │ ├── Dashboard.java

│ │ └── AdminPanel.java

│ │

│ └── Main.java

│

├── assets/

├── README.md

└── pom.xml



⚙️ Sistem Özellikleri

✅ Kullanıcı giriş ve doğrulama sistemi



✅ Kargo oluşturma ve takip işlemleri



✅ Personel ve müşteri yönetimi



✅ Veritabanı bağlantısı ve CRUD işlemleri



✅ DAO mimarisi ile veri erişim katmanı



✅ Modüler ve sürdürülebilir kod yapısı



✅ Nesne yönelimli programlama prensiplerine uygun tasarım

🧩 Yazılım Mimarisi

Projede katmanlı yazılım mimarisi kullanılmıştır:



UI Layer → Kullanıcı arayüzü işlemleri

Service Layer → İş mantığı yönetimi

DAO Layer → Veritabanı erişim işlemleri

Model Layer → Veri nesneleri

Bu yapı sayesinde:





Kod tekrarının azaltılması



Yönetilebilirlik



Test edilebilirlik



Ölçeklenebilirlik

hedeflenmiştir.

🚀 Kurulum

Projeyi çalıştırmak için:





git clone https://github.com/supidiman/KargoLojistikYonetimSistemi.git





Ardından IDE üzerinden açıp gerekli veritabanı bağlantı ayarlarını düzenleyebilirsiniz.

🗄️ Veritabanı

Sistem JDBC bağlantısı kullanarak SQL tabanlı bir veritabanı ile haberleşmektedir.

Örnek tablolar:





Kullanicilar



Kargolar



Personeller



Musteriler



Sevkiyatlar

📌 Gelecek Geliştirmeler



Gerçek zamanlı kargo takip sistemi



QR / Barkod entegrasyonu



REST API desteği



Yetkilendirme sistemi geliştirmeleri



Mobil uygulama desteği



Raporlama ve analiz modülleri

📖 Yazılım Tasarım Yaklaşımları

Projede aşağıdaki yazılım geliştirme yaklaşımları uygulanmıştır:





Object-Oriented Programming (OOP)



Encapsulation



Abstraction



Inheritance



Polymorphism



DAO Design Pattern



Layered Architecture

🤝 Katkıda Bulunma

Projeye katkıda bulunmak için:





Fork oluşturun



Yeni branch açın



Değişikliklerinizi commit edin



Pull Request gönderin

📄 Lisans

Bu proje eğitim ve akademik kullanım amacıyla geliştirilmiştir.

👥 Proje Ekibi (Grup 24)

Kasım Can Yıldırım

OOP Mimari ve Core Logic



Yunus Emre Sarıbacak

Veritabanı Mimari ve DAO



Yusuf Kağan Kızılpınar

Kullanıcı Yönetimi ve Arayüz Entegrasyonu



Furkan Tayyip Arfat

Operasyonel Modüller ve Backend Mantığı

🔗 GitHub Repository

https://github.com/supidiman/KargoLojistikYonetimSistemi
