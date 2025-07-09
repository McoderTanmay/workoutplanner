# 🏋️‍♂️ Workout Tracker API

A RESTful API for a workout tracking application. This backend system allows users to register, log in, manage their workout plans, schedule workout sessions, and track fitness progress — all securely authenticated via JWT.

---

## 🚀 Features

- ✅ User Registration and Login with JWT Authentication
- 🔐 Secure endpoints – only authenticated users can access and manage their data
- 🏃‍♀️ Manage Exercises (name, description, category/muscle group)
- 📋 Create, Update, and Delete Workout Plans
- 📅 Schedule workouts on specific dates and times
- 📈 Generate reports on past workouts and performance
- 🧪 Unit Testing for reliability
- 📄 API documented using OpenAPI/Swagger

---

## 🧰 Tech Stack

- **Backend:** Java 17+ (Tested on Java 21 / Java 24)
- **Framework:** Spring Boot
- **Build Tool:** Maven
- **Authentication:** JWT (JSON Web Token)
- **Database:** MySQL
- **ORM:** Hibernate / JPA

---

## 🗃️ Database Schema Overview

- **Users**
  - `id`, `username`, `email`, `passwordHash`
- **Exercises**
  - `id`, `name`, `description`, `category` (e.g., cardio, strength, etc.)
- **Workout Plans**
  - `id`, `user_id`, `title`, `dateTime`, `comments`
- **Workout Exercises**
  - `id`, `workout_id`, `exercise_id`, `sets`, `reps`, `weight`

Seeder class populates initial `Exercise` data.

---
## ⚙️ How to Run
1. **Clone the repository**
2. **Configure database**
   -Edit src/main/resources/application.properties
   ```bash
   spring.datasource.url=jdbc:mysql://localhost:3306/workout_tracker
   spring.datasource.username=your_db_username
   spring.datasource.password=your_db_password
   jwt.secret=your_jwt_secret_key
   ```
3. **Build and run**
   ```bash
   mvn clean install
   mvn spring-boot:run
  ```

