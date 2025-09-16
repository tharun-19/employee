# Employee CRUD API 

A simple **Spring Boot REST API** project that performs **CRUD operations** (Create, Read, Update, Delete) on an Employee entity.  
The project uses **Spring Data JPA** with an in-memory **H2 Database** for persistence and validation with **Jakarta Bean Validation**.

---

## 🔧 Tech Stack
- **Java**: 22  
- **Spring Boot**: 3.5.5  
- **Build Tool**: Gradle  
- **Database**: H2 (in-memory, with console enabled)  

---

## 📌 Features
- Add a new employee  
- Get all employees  
- Get employee by ID  
- Update employee details  
- Delete employee  
- Validation on input fields (e.g., not null, valid email)  
- Global exception handling  

---

## ▶️ Run the Application
```bash
./gradlew bootRun
