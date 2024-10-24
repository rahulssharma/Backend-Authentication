
# **Spring Boot Authentication Backend**

## **Overview**

A Spring Boot backend with **Spring Security** for email-based user authentication, registration, and email validation, with users stored in a database. Passwords are encrypted, and email verification is required for account activation.

---

## **Features**

- **User Registration** with email & password
- **Email Validation** for account activation
- **BCrypt Password Encryption**
- **Form-Based Login** using Spring Security
- **Database Integration** (PostgreSQL)
- **Role-based Authorization**
- **Email Verification** via JavaMailSender

---

## **Technologies**

- **Spring Boot**  |  **Spring Security**
- **Spring Data JPA** |  **Hibernate**
- **BCrypt**  |  **JavaMailSender**
- **PostgreSQL**

---

## **Setup Instructions**

### **1. Prerequisites**

- **JDK 17** or later
- **Maven** or **Gradle**
- **MySQL/PostgreSQL** for database
- **SMTP** setup for email verification

### **2. Clone the Repository**

```bash
git clone https://github.com/rahulssharma/Backend-Authentication.git
```

### **3. Configure Database**

#### MySQL in `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/your_db_name
spring.datasource.username=your_db_user
spring.datasource.password=your_db_password
spring.jpa.hibernate.ddl-auto=update
```

### **4. Configure Email Service**

Configure **SMTP** for email verification:

```properties
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=your-email@gmail.com
spring.mail.password=your-app-password
spring.mail.properties.mail.smtp.starttls.enable=true
```

### **5. Build & Run the Project**

```bash
# Using Maven
mvn clean install
mvn spring-boot:run
```

---

## **API Endpoints**

### **1. User Registration**
- **POST** `/register`
```json
{
  "email": "example@example.com",
  "password": "password123"
}
```

### **2. Email Confirmation**
- **GET** `/confirm?token=<token>`

### **3. Login**
- **POST** `/login`
```json
{
  "email": "example@example.com",
  "password": "password123"
}
```

---

## **Security Configuration**

Spring Security is configured to handle **form login**, user authentication, and database-backed user details:

```java
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests(auth -> auth
            .antMatchers("/register", "/confirm", "/login").permitAll()
            .anyRequest().authenticated()
    ).formLogin(form -> form.loginPage("/login").permitAll());
    return http.build();
}

@Bean
public UserDetailsService userDetailsService(AppUserRepository repo) {
    return email -> repo.findByEmail(email).orElseThrow(
        () -> new UsernameNotFoundException("User not found"));
}
```

---

## **Database Schema**

### **User Table**
```sql
CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    roles VARCHAR(255) NOT NULL,
    enabled BOOLEAN DEFAULT FALSE
);
```

---

## **License**

Licensed under the MIT License. See the [LICENSE](LICENSE) file for more details.

---

## **Author**

- [Rahul Sharma](https://github.com/rahulssharma)

---

This README provides a concise guide for setting up, configuring, and running the Spring Boot authentication backend. It covers essential project features, setup instructions, and API documentation. Let me know if any further refinements are needed!
