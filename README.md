# Markdown Note Taking App

## Açıklama

Markdown Note Taking App, kullanıcıların [Markdown](https://www.markdownguide.org/basic-syntax/) formatında notlar oluşturmasını ve bu notları HTML formatına dönüştürmesini sağlar. Uygulama ayrıca dosya yükleme ve notlara dosya ekleme gibi özellikler sunar. Bu uygulama, Spring Boot kullanılarak geliştirilmiştir ve PostgreSQL veritabanı ile entegre edilmiştir.

## Özellikler

- **Kullanıcı Yönetimi**: Kullanıcılar oluşturulabilir, görüntülenebilir ve silinebilir.
- **Not Yönetimi**: Notlar oluşturulabilir, görüntülenebilir ve silinebilir.
- **Markdown'dan HTML'ye Dönüştürme**: Markdown içeriğini HTML'ye dönüştürür.
- **Dosya Yükleme ve Yönetimi**: Notlara dosya ekleme ve dosyaları yönetme.

## Teknolojiler

- **Backend**: [Spring Boot](https://spring.io/projects/spring-boot/)
- **Veritabanı**: [PostgreSQL](https://www.postgresql.org/)
- **Markdown İşleme**: [CommonMark](https://commonmark.org/)
- **Dosya Yükleme**: MultipartFile

## Kurulum

### Gereksinimler

- [JDK 17](https://www.oracle.com/java/technologies/downloads/#java17) veya [üstü](https://www.oracle.com/java/technologies/downloads/#java22)
- PostgreSQL
- [Maven](https://maven.apache.org/)

### Adımlar

1. **Projeyi Klonlayın**:

   ```bash
   git clone https://github.com/kullanici_adiniz/markdown-note-taking-app.git
   cd markdown-note-taking-app

2. **Veritabanı Ayarları**:

PostgreSQL veritabanınızı oluşturun ve src/main/resources/application.properties dosyasını uygun şekilde düzenleyin:

  ```bash
  spring.datasource.url=jdbc:postgresql://localhost:5432/markdown_app
  spring.datasource.username=postgres
  spring.datasource.password=your_password
  ```
3. **Projeyi Yapılandırın ve Çalıştırın**:

  ```bash
  mvn clean install
  mvn spring-boot:run
 ```
## API Kullanımı

Bu bölümde, projenin API endpoint'lerinin nasıl kullanılacağı anlatılmaktadır.

### 1. Kullanıcı İşlemleri (UserController)

#### Kullanıcı Ekleme

- **URL:** `/user`
- **Method:** `POST`
- **Açıklama:** Yeni bir kullanıcı oluşturur.

- **Request Body:** `<UserDTO>`

- **Örnek İstek:**
  ```bash
  curl -X POST http://localhost:8080/user -H "Content-Type: application/json" -d "{\"username\":\"OmerF\", \"email\":\"omer@example.com\", \"password\":\"your_password\"}"
  ```
>- **Başarılı Yanıt:**
>   
   >> + Status: `200 OK`
   >> +  Body:
   >> +   ```json
>   >      {
>   >     "username": "OmerF",
>   >     "email": "omer@example.com",
>   >     "password": "your_password"
>   >      }
          
#### Kullanıcıları Getirme

- **URL:** `/user`
- **Method:** `GET`
- **Açıklama:** Sistemde kayıtlı tüm kullanıcıları getirir.
- **Örnek İstek:**
 ```bash
 curl -X GET "http://localhost:8080/user"
 ```
  
>- **Başarılı Yanıt:**
>   
   >> + Status: `200 OK`
   >> +  Body: `<List<ResponseUserDTO>>`
   >> +   ```json
>   >     [
>   >      {
>   >     "username": "OmerF",
>   >     "createdAt": "YYYY-MM-DDTHH:MM:SSZ"
>   >      }
>   >      {
>   >      "username": "user2",
>   >      "createdAt": "YYYY-MM-DDTHH:MM:SSZ"
>   >      } 
>   >     ]

#### Belirli Kullanıcı Getirme

- **URL:** `/user/{id}`
- **Method:** `GET`
- **URL Parametresi:'id'
- **Açıklama:** ID'ye göre bir kullanıcı getirir.
- **Örnek İstek:**
 ```bash
 curl -X GET "http://localhost:8080/user/1"
 ```
  
>- **Başarılı Yanıt:**
>   
   >> + Status: `200 OK`
   >> +  Body: `<ResponseUserDTO>`
   >> +   ```json
>   >      {
>   >     "username": "OmerF",
>   >     "createdAt": "YYYY-MM-DDTHH:MM:SSZ"
>   >      }

#### Kullanıcı Silme

- **URL:** `/user/{id}`
- **Method:** `DELETE`
- **URL Parametresi:'id'
- **Açıklama:** ID'ye göre bir kullanıcı siler.
- **URL Parametresi:'id'
- **Örnek İstek:**
 ```bash
curl -X DELETE http://localhost:8080/user/1
 ```
  
>- **Başarılı Yanıt:**
>   
   >> + Status: `200 OK`
   >> +  Body: `<ResponseUserDTO>`
   >> +   ```json
>   >     "User deleted!"

>- **Hata Durumu (Kullanıcı Bulunamadı)**
>
   >> + Status: `404 Not Found`
   >> +  ```json
>   >    "User not found!"

>- **Hata Durumu (Yanlış ID tipi)**
>
   >> + Status: `400 Bad Request`
   >> +  ```json
>   >    "There is no such user has this id!"

### 2. Not İşlemleri (NoteController)

#### Not Ekleme

- **URL:** `/notes`
- **Method:** `POST`
- **Açıklama:** Yeni bir not ekler.

- **Request Body:** `<NoteDTO>`

- **Örnek İstek:**
  ```bash
  curl -X POST http://localhost:8080/notes -H "Content-Type: application/json" -d '{"title":"Not Başlığı", "content":"Markdown içeriği burada yer alacak.", "userId":1}'
  ```
>- **Başarılı Yanıt:**
>   
   >> + Status: `200 OK`
   >> +  Body:
   >> +   ```json
>   >     {
>   >     "username" : "ID'si 1 olan user'ın username'i",
>   >     "title" : "Not Başlığı",
>   >     "createdAt" : "2024-08-30T19:34:51.3570971",
>   >     "updatedAt" : "2024-08-30T19:34:51.3570971"
>   >     }

>- **Hata Durumu:**
>
   >> + Status: `500 Internal Server Error`
   >> + Body: 
   >> + ```json
>   >   {
>   >   "timestamp" : "2024-08-30T16:39:07.205+00:00",
>   >   "status" : 500,
>   >   "error" : "Internal Server Error",
>   >   "path" : "/notes"
>   >   }

#### Not Getirme

- **URL:** `/notes/{id}`
- **Method:** `GET`
- **URL Parametresi:'id'
- **Açıklama:** ID'ye göre bir kullanıcı getirir.
- **Örnek İstek:**
 ```bash
 curl -X GET "http://localhost:8080/notes/1"
 ```
  
>- **Başarılı Yanıt:**
>   
   >> + Status: `200 OK`
   >> +  Body: `<Note>`
>   > +  Notun içerisine file eklenmemişse
   >> +   ```json
>   >    {
  "id" : 1,
  "title" : "Not Başlığı",
  "content" : "Markdown içeriği burada yer alacak.",
  "createdAt" : "2024-08-29T20:22:52.663821",
  "updatedAt" : "2024-08-29T20:22:52.663821",
  "file" : null
}

