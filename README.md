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

### 1. Kullanıcı İşlemleri ([UserController](https://github.com/omerfeyzioglu/markdown-note-taking-app/blob/main/src/main/java/com/example/demo/controllers/UserController.java))

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
- **URL Parametresi:**'id'
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
- **URL Parametresi:**'id'
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

### 2. Not İşlemleri ([NoteController](https://github.com/omerfeyzioglu/markdown-note-taking-app/blob/main/src/main/java/com/example/demo/controllers/NoteController.java))

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
- **URL Parametresi:**'id'
- **Açıklama:** ID'ye göre bir kullanıcı getirir.
- **Örnek İstek:**
 ```bash
 curl -X GET "http://localhost:8080/notes/1"
 ```
  
>- **Başarılı Yanıt:** (Notun içerisine file eklenmediği durum.)
>   
   >> + Status: `200 OK`
   >> +  Body: `<Note>`
   >> +   ```json
>   >     {
>   >     "id" : 1,
>   >     "title" : "Not Başlığı",
>   >     "content" : "Markdown içeriği burada yer alacak.",
>   >     "createdAt" : "2024-08-29T20:22:52.663821",
>   >     "updatedAt" : "2024-08-29T20:22:52.663821",
>   >     "file" : null
>   >     }

>- **Başarılı Yanıt:** (Notun içerisine file eklendiği durum.)
>
   >> + Status: `200 OK`
   >> + Body: `<Note>`
   >> +  ```json
>   >     {
>   >     "id" : 1,
>   >     "title" : "Not Başlığı",
>   >     "content" : "Markdown içeriği burada yer alacak.",
>   >     "createdAt" : "2024-08-29T20:22:52.663821",
>   >     "updatedAt" : "2024-08-29T20:22:52.663821",
>   >     "file" : {
>   >      "id" : 1,
>   >      "fileName" : "dailytasks.txt",
>   >      "filePath" : "C:\\..\\dailytasks.txt",
>   >      "fileType" : "text/plain",
>   >    "uploadedAt" : "2024-08-29T20:22:52.649713"
>   >              }
>   >     }

>- **Hata Durumu (Not Bulunamadı)**
>
   >> + Status: `404 Not Found`
   >> + Body: 
   >> +  ```json
>   >    {
>   >     "timestamp" : "2024-08-30T22:09:10.117+00:00",
>   >    "status" : 500,
>   >    "error" : "Internal Server Error",
>   >    "path" : "/notes/1"
>   >    }

>- **Hata Durumu (Yanlış ID tipi)**
>
   >> + Status: `400 Bad Request`
   >> +  ```json
>   >    {
>   >    "timestamp" : "2024-08-30T22:09:25.017+00:00",
>   >    "status" : 400,
>   >    "error" : "Bad Request",
>   >    "path" : "/notes/1a"
>   >    }

#### Belirli Bir Kullanıcının Notlarını Getirme

- **URL:** `/notes/user/{userId}`
- **Method:** `GET`
- **URL Parametresi:**'userId'
- **Açıklama:** ID'ye göre bir kullanıcı getirir.
- **Örnek İstek:**
 ```bash
 curl -X GET "http://localhost:8080/notes/user/1"
 ```
>- **Başarılı Yanıt:** (Notun içerisine file eklendiği durum.)
>
   >> + Status: `200 OK`
   >> + Body: `<List<Note>>`
   >> +  ```json
>   >     [ {
>   >     "id" : 9,
>   >     "title" : "routine",
>   >     "content" : "Markdown note 1",
>   >    "createdAt" : "2024-08-28T17:09:39.106826",
>   >     "updatedAt" : "2024-08-28T17:38:52.354705",
>   >    "file" : {
>   >      "id" : 14,
>   >     "fileName" : "dailytasks.txt",
>   >     "filePath" : "C:\\..\\dailytasks.txt",
>   >        "fileType" : "text/plain",
>   >        "uploadedAt" : "2024-08-28T17:38:52.286481"
>   >      }
>   >    },
>   >    {
>   >      "id" : 13,
>   >      "title" : "meeting",
>   >    "content" : "Markdown note 2",
>   >      "createdAt" : "2024-08-29T19:44:15.221622",
>   >    "updatedAt" : "2024-08-29T19:44:15.221622",
>   >    "file" : null
>   >    },
>   >    {
>   >      "id" : 10,
>   >     "title" : "project",
>   >    "content" : "Markdown note 3",
>   >     "createdAt" : "2024-08-28T17:38:52.343281",
>   >     "updatedAt" : "2024-08-29T19:44:15.234142",
>   >     "file" : {
>   >       "id" : 17,
>   >     "fileName" : "dailytasks.txt",
>   >     "filePath" : "C:\\..\\codeexample.txt",
>   >     "fileType" : "text/plain",
>   >    "uploadedAt" : "2024-08-29T19:44:15.129561"
>   >      }
>   >    } ]

>- **Hata Durumu (Not Bulunamadı)**
>
   >> + Status: `200 OK`
   >> + Body: 
   >> +  ```json
>   >    []

>- **Hata Durumu (Yanlış ID tipi)**
>
   >> + Status: `400 Bad Request`
   >> +  ```json
>   >    {
>   >    "timestamp" : "2024-08-30T22:09:25.017+00:00",
>   >    "status" : 400,
>   >    "error" : "Bad Request",
>   >    "path" : "/notes/user/1a"
>   >    }

#### Not Silme

- **URL:** `/notes/{id}`
- **Method:** `DELETE`
- **URL Parametresi:**'id'
- **Açıklama:** ID'ye göre bir not siler.
- **URL Parametresi:'id'
- **Örnek İstek:**
 ```bash
curl -X DELETE http://localhost:8080/notes/1
 ```

>- **Başarılı Yanıt:**
>   
   >> + Status: `200 OK`
   >> +   ```json
>   >     "Note deleted"

>- **Hata Durumu (Not Bulunamadı)**
>
   >> + Status: `404 Not Found`
   >> +  ```json
>   >    "Note not found"

>- **Hata Durumu (Yanlış ID tipi)**
>
   >> + Status: `400 Bad Request`
   >> +  ```json
>   >    {
>   >     "timestamp" : "2024-08-30T22:44:10.670+00:00",
>   >    "status" : 400,
>   >    "error" : "Bad Request",
>   >    "path" : "/notes/1a"
>   >    }
   
#### Belirli Notun Contentini Html Olarak Alma

- **URL:** `/notes/content/{id}`
- **Method:** `GET`
- **URL Parametresi:**'id'
- **Açıklama:** Notun markdown dilinde ki içeriğini Html'e çevirir ve döndürür.
- **Örnek İstek:**
 ```bash
 curl -X GET http://localhost:8080/notes/content/1
 ```

>- **Başarılı Yanıt:** 
>   
   >> + Status: `200 OK`
   >> +  Body: `Html içeriği`
   >> +   ```json
>   >     <p>Markdown işaretleme dilinde içerik</p>

>- **Hata Durumu (Not İçeriği Boş Olma Durumu)**
>
   >> + Status: `200 OK`
   >> +  ```json
>   >    ""

>- **Hata Durumu (Not Olmama Durumu)**
>
   >> + Status: `500 Internal Server Error`
   >> +  ```json
>   >    {
>   >      "timestamp" : "2024-08-30T22:54:25.807+00:00",
>   >    "status" : 500,
>   >    "error" : "Internal Server Error",
>   >    "path" : "/notes/content/130"
>   >    } 

>- **Hata Durumu (Yanlış ID tipi)**
>
   >> + Status: `400 Bad Request`
   >> +  ```json
>   >    {
>   >     "timestamp" : "2024-08-30T22:44:10.670+00:00",
>   >    "status" : 400,
>   >    "error" : "Bad Request",
>   >    "path" : "/notes/content/1a"
>   >    }

### File İşlemleri ([FileController](https://github.com/omerfeyzioglu/markdown-note-taking-app/blob/main/src/main/java/com/example/demo/controllers/FileController.java))

#### Dosya Yükleme

- **URL:** `/files`
- **Method:** `POST`
- **İstek Parametreleri:** `file` `filePath` `noteId`
- **Açıklama:** Bir dosyanın belirtilen bir yola ve not ID'sine göre yüklenmesini sağlar. `txt` veya `word` dosyası kullanılmak zorunda
- **Örnek İstek:**
 ```bash
 curl -X POST http://localhost:8080/files -H "Content-Type: multipart/form-data" -F "file=@/path/to/your/toplantı.txt" -F "filePath=path/to/your/toplantı.txt" -F "noteId=14"
 ```

>- **Başarılı Yanıt:** 
>   
   >> + Status: `200 OK`
   >> +  Body: 'FileDTO'
   >> +   ```json
>   >     {
>   >      "fileName" : "toplantı.txt",
>   >      "filePath" : "path/to/your/toplantı.txt",
>   >      "noteId" : 14
>   >     }

>- **Hata Durumu**
>
   >> + Status: `415 Unsupported Media Type`
   >> +  ```json
>   >    ""

#### Dosya Getirme

- **URL:** `/files/{id}`
- **Method:** `GET`
- **URL Parametresi:**'id'
- **Açıklama:** ID'ye göre ilgili dosya getirir.
- **Örnek İstek:**
 ```bash
 curl -X GET "http://localhost:8080/files/1"
 ```
>- **Başarılı Yanıt:** 
>
   >> + Status: `200 OK`
   >> + Body: `<FileDTO>`
   >> +  ```json
>   >     {
>   >     "fileName" : "toplantı.txt",
>   >    "filePath" : "path/to/your/toplantı.txt",
>   >    "noteId" : 14
>   >    }

>- **Hata Durumu (Dosya Olmama Durumu)**
>
   >> + Status: `500 Internal Server Error`
   >> +  ```json
>   >    {
>   >      "timestamp" : "2024-08-30T22:54:25.807+00:00",
>   >    "status" : 500,
>   >    "error" : "Internal Server Error",
>   >    "path" : "/files/230"
>   >    } 

>- **Hata Durumu (Yanlış ID tipi)**
>
   >> + Status: `400 Bad Request`
   >> +  ```json
>   >    {
>   >     "timestamp" : "2024-08-30T22:44:10.670+00:00",
>   >    "status" : 400,
>   >    "error" : "Bad Request",
>   >    "path" : "/files/1a"
>   >    }

#### Dosya Silme

- **URL:** `/files/{id}`
- **Method:** `DELETE`
- **URL Parametresi:**'id'
- **Açıklama:** ID'ye göre ilgil dosyayı siler.
- **URL Parametresi:'id'
- **Örnek İstek:**
 ```bash
curl -X DELETE http://localhost:8080/files/1
 ```

>- **Başarılı Yanıt:**
>   
   >> + Status: `200 OK`
   >> +   ```json
>   >     "File successfully  deleted"

>- **Hata Durumu (Dosya Olmama Durumu)**
>
   >> + Status: `500 Internal Server Error`
   >> +  ```json
>   >    {
>   >      "timestamp" : "2024-08-30T22:54:25.807+00:00",
>   >    "status" : 500,
>   >    "error" : "Internal Server Error",
>   >    "path" : "/files/230"
>   >    } 

>- **Hata Durumu (Yanlış ID tipi)**
>
   >> + Status: `400 Bad Request`
   >> +  ```json
>   >    {
>   >     "timestamp" : "2024-08-30T22:44:10.670+00:00",
>   >    "status" : 400,
>   >    "error" : "Bad Request",
>   >    "path" : "/files/1a"
>   >    }

