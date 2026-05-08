# 📦 Kargo Lojistik Yönetim Sistemi (V1.0)

Gazi Üniversitesi Mühendislik Fakültesi bünyesinde, **Nesne Yönelimli Programlama** prensiplerine sadık kalınarak geliştirilmiş kurumsal bir kargo yönetim otomasyonudur.

---

## 🏗️ Mimari Yapı (N-Tier Architecture)
Proje, bakımı kolay ve ölçeklenebilir olması için **Çok Katmanlı Mimari** kullanılarak tasarlanmıştır:

1.  **UI (User Interface) Katmanı:** `javax.swing` kullanılarak oluşturulan, kullanıcı etkileşimini yöneten katman.
2.  **Service (Business) Katmanı:** İş kurallarının (ücret hesaplama, durum kontrolü) işlendiği katman.
3.  **DAO (Data Access Object) Katmanı:** SQLite veritabanı ile uygulama arasındaki veri köprüsü.
4.  **Model Katmanı:** Kargo, Müşteri ve Gönderi gibi temel nesne yapıları.

---

## 🛠️ Teknik Derinlik ve OOP Uygulamaları

### 1. Polimorfizm (Çok Biçimlilik)
Sistemde kargo türlerine göre ücret hesaplama mantığı dinamiktir. `Gonderi` sınıfındaki `ucretHesapla()` metodu, alt sınıflarda (Hizli, Standart, Uluslararasi) ezilerek (**Override**) her tip için farklı katsayılarla çalışır.

### 2. Kalıtım ve Soyutlama (Inheritance & Abstraction)
- `Gonderi` sınıfı `abstract` olarak tanımlanarak temel kargo özelliklerini alt sınıflara aktarır.
- Ortak özellikler (ağırlık, mesafe, müşteri) tek merkezden yönetilir.

### 3. Veritabanı Yönetimi
- **SQLite JDBC:** Hafif ve taşınabilir bir veritabanı çözümü entegre edilmiştir.
- **İlişkisel Veri:** Müşteri ve Kargo verileri `Foreign Key` mantığıyla birbirine bağlıdır.

---

## 📑 Ekran Görüntüleri ve İşlevler
* **Müşteri Yönetimi:** CRUD (Ekle, Sil, Güncelle, Listele) işlemleri.
* **Kargo Operasyonları:** Otomatik ücret hesaplama ve kargo durum simülasyonu.
* **Durum Takibi:** `SUBEDE` -> `DAGITIMDA` -> `TESLIM_EDILDI` -> `IADE` döngüsünün yönetimi.

---

## 🚀 Kurulum Adımları
1.  **Depoyu Klonlayın:** `git clone https://github.com/supidiman/KargoLojistikYonetimSistemi.git`
2.  **Kütüphaneyi Bağlayın:** `sqlite-jdbc-3.53.1.0.jar` dosyasını `Referenced Libraries` altına ekleyin.
3.  **Başlatın:** `com.kargo.main.Main.java` dosyasını `Run as Java Application` diyerek çalıştırın.

---

## 👥 Proje Ekibi
* **Furkan Tayyip Arfat** (21118080742) - Lead Developer & Backend Architect
* **Yusuf Kağan** - UI Designer & Database Management

---

> **Not:** Bu proje eğitim amaçlı geliştirilmiş olup, Gazi Üniversitesi Bilgisayar Mühendisliği müfredatına uygundur.
