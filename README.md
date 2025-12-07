# 🧩 User-Microservice Application (with Kafka)

A simple microservices application built to understand how **Apache Kafka** works.
This project contains two services:

---

## 📌 1. User Service (Producer)

Responsible for:

* Creating new users using a **POST API**
* Producing a Kafka message every time a user is created

### 👉 API

```
POST /users
```

**Description:** Creates a user and publishes a Kafka event to **user-topic**.

### ✔ What It Does

* Accepts a JSON request
* Saves nothing to DB (stateless demo)
* Sends message to Kafka

---

## 📌 2. Notification Service (Consumer)

Responsible for:

* Listening to the Kafka **user-topic**
* Processing the message (sending email/notification OR logging)

### ✔ What It Does

* No database used
* Reads messages from Kafka
* Prints the notification in console (or extend for SMS/Email)

## ⚙ Technologies Used

* Java 17
* Spring Boot 4.x
* Spring Web MVC
* Spring Kafka
* Lombok
* Maven
* Apache Kafka (local setup)

---

## 📂 Project Structure

```
/user-service
    /controller
    /service
    /kafka
    /model (POJO only, no DB)
    application.properties
    pom.xml

/notification-service
    /listener
    /service
    /kafka
    /model (POJO only)
    application.properties
    pom.xml
```

---

## 🔗 Kafka Setup

Start Zookeeper & Kafka:

```
bin/zookeeper-server-start.sh config/zookeeper.properties
bin/kafka-server-start.sh config/server.properties
```

Create topic:

```
bin/kafka-topics.sh --create --topic user-topic --bootstrap-server localhost:9092
```

---

## ▶ Running the Microservices

### 1️⃣ Start Kafka

### 2️⃣ Run **User Service**

### 3️⃣ Run **Notification Service**

---

## 🚀 Flow of the Application

### Step 1: User sends POST request

```
POST /users
{
  "id": 1,
  "name": "Madhu"
}
```

### Step 2: User Service processes and publishes event

```text
Sent message to Kafka: { id: 1, name: "Madhu" }
```

### Step 3: Notification Service consumes event

```text
Notification Received: User created → Madhu
```

---

## 🛠 Common Issues Solved

### ❌ DataSourceAutoConfiguration Errors

Cause: JPA dependency existed but no DB configured
Fix: Removed JPA & excluded auto configuration.

### ❌ Serializer Errors

Cause: Wrong serializer like
`tools.jackson.databind.ser.jdk.StringSerializer`
Fix: Added correct Kafka serializer configs:

```
key.serializer=org.apache.kafka.common.serialization.StringSerializer
value.serializer=org.apache.kafka.common.serialization.StringSerializer
```

---

## 🎯 Final Result

A working microservices demo:

* **User Service** → produces Kafka message
* **Notification Service** → consumes message
* No database required
* Clean, simple, scalable architecture

---
