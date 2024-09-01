# Markdown Note Taking App

## Description

Markdown Note Taking App allows users to create notes in [Markdown](https://www.markdownguide.org/basic-syntax/) format and convert these notes to HTML format. The application also offers features such as file uploading and attaching files to notes. This application is developed using Spring Boot and integrated with PostgreSQL database.

## Features

- **User Management**: Users can be created, viewed, and deleted.
- **Note Management**: Notes can be created, viewed, and deleted.
- **Markdown to HTML Conversion**: Converts Markdown content to HTML.
- **File Upload and Management**: Add files to notes and manage files.

## Technologies

- **Backend**: [Spring Boot](https://spring.io/projects/spring-boot/)
    - For building the RESTful API and handling backend logic.
- **Database**: [PostgreSQL](https://www.postgresql.org/)
    - To store and manage data securely and efficiently.
- **Markdown Processing**: [CommonMark](https://commonmark.org/)
    - For converting Markdown content into HTML format.
- **File Upload**: MultipartFile
    - To handle file uploads in your Spring Boot application.
- **Object Mapping & Simplification**: [Lombok](https://projectlombok.org/)
    - To reduce boilerplate code with annotations.
- **Object-DTO Conversion**: [ModelMapper](https://modelmapper.org/)
    - For mapping between your entity classes and DTOs efficiently.


## UML Diagram

![Untitled Diagram drawio](https://github.com/user-attachments/assets/81efbd41-64ed-42eb-9776-9f190a7e1cb1)

## Installation

### Requirements

- [JDK 17](https://www.oracle.com/java/technologies/downloads/#java17) or [higher](https://www.oracle.com/java/technologies/downloads/#java22)
- PostgreSQL
- [Maven](https://maven.apache.org/)

### Steps

1. **Clone the Project**:

   ```bash
   git clone https://github.com/your_username/markdown-note-taking-app.git
   cd markdown-note-taking-app
   ```

2. **Database Settings**:

Create your PostgreSQL database and edit the src/main/resources/application.properties file accordingly:

  ```bash
  spring.datasource.url=jdbc:postgresql://localhost:5432/markdown_app
  spring.datasource.username=postgres
  spring.datasource.password=your_password
  ```

3. **Configure and Run the Project**:

  ```bash
  mvn clean install
  mvn spring-boot:run
  ```

## API Usage

This section explains how to use the project's API endpoints.

### 1. User Operations ([UserController](https://github.com/omerfeyzioglu/markdown-note-taking-app/blob/main/src/main/java/com/example/demo/controllers/UserController.java))

#### Add User

- **URL:** `/user`
- **Method:** `POST`
- **Description:** Creates a new user.

- **Request Body:** `<UserDTO>`

- **Example Request:**
  ```bash
  curl -X POST http://localhost:8080/user -H "Content-Type: application/json" -d "{\"username\":\"OmerF\", \"email\":\"omer@example.com\", \"password\":\"your_password\"}"
  ```
>- **Successful Response:**
>   
   >> + Status: `200 OK`
   >> +  Body:
   >> +   ```json
>   >      {
>   >     "username": "OmerF",
>   >     "email": "omer@example.com",
>   >     "password": "your_password"
>   >      }
          
#### Get Users

- **URL:** `/user`
- **Method:** `GET`
- **Description:** Retrieves all users registered in the system.
- **Example Request:**
 ```bash
 curl -X GET "http://localhost:8080/user"
 ```
  
>- **Successful Response:**
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

#### Get Specific User

- **URL:** `/user/{id}`
- **Method:** `GET`
- **URL Parameter:** 'id'
- **Description:** Retrieves a user by ID.
- **Example Request:**
 ```bash
 curl -X GET "http://localhost:8080/user/1"
 ```
  
>- **Successful Response:**
>   
   >> + Status: `200 OK`
   >> +  Body: `<ResponseUserDTO>`
   >> +   ```json
>   >      {
>   >     "username": "OmerF",
>   >     "createdAt": "YYYY-MM-DDTHH:MM:SSZ"
>   >      }

#### Delete User

- **URL:** `/user/{id}`
- **Method:** `DELETE`
- **URL Parameter:** 'id'
- **Description:** Deletes a user by ID.
- **Example Request:**
 ```bash
curl -X DELETE http://localhost:8080/user/1
 ```
  
>- **Successful Response:**
>   
   >> + Status: `200 OK`
   >> +  Body: `<ResponseUserDTO>`
   >> +   ```json
>   >     "User deleted!"

>- **Error State (User Not Found)**
>
   >> + Status: `404 Not Found`
   >> +  ```json
>   >    "User not found!"

>- **Error State (Wrong ID type)**
>
   >> + Status: `400 Bad Request`
   >> +  ```json
>   >    "There is no such user has this id!"

### 2. Note Operations ([NoteController](https://github.com/omerfeyzioglu/markdown-note-taking-app/blob/main/src/main/java/com/example/demo/controllers/NoteController.java))

#### Add Note

- **URL:** `/notes`
- **Method:** `POST`
- **Description:** Adds a new note.

- **Request Body:** `<NoteDTO>`

- **Example Request:**
  ```bash
  curl -X POST http://localhost:8080/notes -H "Content-Type: application/json" -d '{"title":"Note Title", "content":"Markdown content will be here.", "userId":1}'
  ```
>- **Successful Response:**
>   
   >> + Status: `200 OK`
   >> +  Body:
   >> +   ```json
>   >     {
>   >     "username" : "Username of the user with ID 1",
>   >     "title" : "Note Title",
>   >     "createdAt" : "2024-08-30T19:34:51.3570971",
>   >     "updatedAt" : "2024-08-30T19:34:51.3570971"
>   >     }

>- **Error State:**
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

#### Get Note

- **URL:** `/notes/{id}`
- **Method:** `GET`
- **URL Parameter:** 'id'
- **Description:** Retrieves a note by ID.
- **Example Request:**
 ```bash
 curl -X GET "http://localhost:8080/notes/1"
 ```
  
>- **Successful Response:** (When no file is attached to the note.)
>   
   >> + Status: `200 OK`
   >> +  Body: `<Note>`
   >> +   ```json
>   >     {
>   >     "id" : 1,
>   >     "title" : "Note Title",
>   >     "content" : "Markdown content will be here.",
>   >     "createdAt" : "2024-08-29T20:22:52.663821",
>   >     "updatedAt" : "2024-08-29T20:22:52.663821",
>   >     "file" : null
>   >     }

>- **Successful Response:** (When a file is attached to the note.)
>
   >> + Status: `200 OK`
   >> + Body: `<Note>`
   >> +  ```json
>   >     {
>   >     "id" : 1,
>   >     "title" : "Note Title",
>   >     "content" : "Markdown content will be here.",
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

>- **Error State (Note Not Found)**
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

>- **Error State (Wrong ID type)**
>
   >> + Status: `400 Bad Request`
   >> +  ```json
>   >    {
>   >    "timestamp" : "2024-08-30T22:09:25.017+00:00",
>   >    "status" : 400,
>   >    "error" : "Bad Request",
>   >    "path" : "/notes/1a"
>   >    }

#### Get Notes of a Specific User

- **URL:** `/notes/user/{userId}`
- **Method:** `GET`
- **URL Parameter:** 'userId'
- **Description:** Retrieves all notes of a user by user ID.
- **Example Request:**
 ```bash
 curl -X GET "http://localhost:8080/notes/user/1"
 ```
>- **Successful Response:** (When files are attached to notes.)
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

>- **Error State (Note Not Found)**
>
   >> + Status: `200 OK`
   >> + Body: 
   >> +  ```json
>   >    []

>- **Error State (Wrong ID type)**
>
   >> + Status: `400 Bad Request`
   >> +  ```json
>   >    {
>   >    "timestamp" : "2024-08-30T22:09:25.017+00:00",
>   >    "status" : 400,
>   >    "error" : "Bad Request",
>   >    "path" : "/notes/user/1a"
>   >    }

#### Delete Note

- **URL:** `/notes/{id}`
- **Method:** `DELETE`
- **URL Parameter:** 'id'
- **Description:** Deletes a note by ID.
- **Example Request:**
 ```bash
curl -X DELETE http://localhost:8080/notes/1
 ```

>- **Successful Response:**
>   
   >> + Status: `200 OK`
   >> +   ```json
>   >     "Note deleted"

>- **Error State (Note Not Found)**
>
   >> + Status: `404 Not Found`
   >> +  ```json
>   >    "Note not found"

>- **Error State (Wrong ID type)**
>
   >> + Status: `400 Bad Request`
   >> +  ```json
>   >    {
>   >     "timestamp" : "2024-08-30T22:44:10.670+00:00",
>   >    "status" : 400,
>   >    "error" : "Bad Request",
>   >    "path" : "/notes/1a"
>   >    }
   
#### Get Note Content as HTML

- **URL:** `/notes/content/{id}`
- **Method:** `GET`
- **URL Parameter:** 'id'
- **Description:** Converts the markdown content of the note to HTML and returns it.
- **Example Request:**
 ```bash
 curl -X GET http://localhost:8080/notes/content/1
 ```

>- **Successful Response:** 
>   
   >> + Status: `200 OK`
   >> +  Body: `HTML content`
   >> +   ```json
>   >     <p>Content in Markdown markup language</p>

>- **Error State (Empty Note Content)**
>
   >> + Status: `200 OK`
   >> +  ```json
>   >    ""

>- **Error State (Note Not Found)**
>
   >> + Status: `500 Internal Server Error`
   >> +  ```json
>   >    {
>   >      "timestamp" : "2024-08-30T22:54:25.807+00:00",
>   >    "status" : 500,
>   >    "error" : "Internal Server Error",
>   >    "path" : "/notes/content/130"
>   >    }

### File Operations ([FileController](https://github.com/omerfeyzioglu/markdown-note-taking-app/blob/main/src/main/java/com/example/demo/controllers/FileController.java))

#### File Upload

- **URL:** `/files`
- **Method:** `POST`
- **Request Parameters:** `file` `filePath` `noteId`
- **Description:** Allows uploading a file to a specified path and note ID. Only `txt` or `word` files must be used.
- **Example Request:**
 ```bash
 curl -X POST http://localhost:8080/files -H "Content-Type: multipart/form-data" -F "file=@/path/to/your/meeting.txt" -F "filePath=path/to/your/meeting.txt" -F "noteId=14"
 ```
>- **Successful Response:** 
>   
   >> + Status: `200 OK`
   >> +  Body: 'FileDTO'
   >> +   ```json
>   >     {
>   >      "fileName" : "meeting.txt",
>   >      "filePath" : "path/to/your/meeting.txt",
>   >      "noteId" : 14
>   >     }

>- **Error State**
>
   >> + Status: `415 Unsupported Media Type`
   >> +  ```json
>   >    ""

#### Get File

- **URL:** `/files/{id}`
- **Method:** `GET`
- **URL Parameter:** 'id'
- **Description:** Retrieves the relevant file according to the ID.
- **Example Request:**
 ```bash
 curl -X GET "http://localhost:8080/files/1"
 ```
>- **Successful Response:** 
>
   >> + Status: `200 OK`
   >> + Body: `<FileDTO>`
   >> +  ```json
>   >     {
>   >     "fileName" : "meeting.txt",
>   >    "filePath" : "path/to/your/meeting.txt",
>   >    "noteId" : 14
>   >    }

>- **Error State (File Not Found)**
>
   >> + Status: `500 Internal Server Error`
   >> +  ```json
>   >    {
>   >      "timestamp" : "2024-08-30T22:54:25.807+00:00",
>   >    "status" : 500,
>   >    "error" : "Internal Server Error",
>   >    "path" : "/files/230"
>   >    } 

>- **Error State (Wrong ID type)**
>
   >> + Status: `400 Bad Request`
   >> +  ```json
>   >    {
>   >     "timestamp" : "2024-08-30T22:44:10.670+00:00",
>   >    "status" : 400,
>   >    "error" : "Bad Request",
>   >    "path" : "/files/1a"
>   >    }

#### Delete File

- **URL:** `/files/{id}`
- **Method:** `DELETE`
- **URL Parameter:** 'id'
- **Description:** Deletes the relevant file according to the ID.
- **Example Request:**
 ```bash
curl -X DELETE http://localhost:8080/files/1
 ```

>- **Successful Response:**
>   
   >> + Status: `200 OK`
   >> +   ```json
>   >     "File successfully deleted"

>- **Error State (File Not Found)**
>
   >> + Status: `500 Internal Server Error`
   >> +  ```json
>   >    {
>   >      "timestamp" : "2024-08-30T22:54:25.807+00:00",
>   >    "status" : 500,
>   >    "error" : "Internal Server Error",
>   >    "path" : "/files/230"
>   >    } 

>- **Error State (Wrong ID type)**
>
   >> + Status: `400 Bad Request`
   >> +  ```json
>   >    {
>   >     "timestamp" : "2024-08-30T22:44:10.670+00:00",
>   >    "status" : 400,
>   >    "error" : "Bad Request",
>   >    "path" : "/files/1a"
>   >    }
