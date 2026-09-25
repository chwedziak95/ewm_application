# EWM Application

A warehouse management web app (*zarządzanie magazynem*): materials, categories, vendors, purchase orders and internal orders, with user registration and JWT-based login.

## Repository layout

| Path | What it is |
| --- | --- |
| `01-frontend/zarzadzanie-magazynem/` | Angular 15 single-page app |
| `02-backend/zarzadanie-magazynem/` | Spring Boot 3 REST API (Java 19, Maven) |
| `db.sql` | MySQL 8 dump of the `warehouse-application` database |

## Running locally

### 1. Database

Create a MySQL 8 database named `warehouse-application` and a user for the app, then import the dump:

```bash
mysql -u <user> -p warehouse-application < db.sql
```

Connection settings live in `02-backend/zarzadanie-magazynem/src/main/resources/application.properties`.

### 2. Backend

```bash
cd 02-backend/zarzadanie-magazynem
./mvnw spring-boot:run
```

The API serves HTTPS on `https://localhost:8443` and accepts requests from `https://localhost:4200`.

### 3. Frontend

```bash
cd 01-frontend/zarzadzanie-magazynem
npm install
npm start
```

Open `https://localhost:4200`. The dev server uses the self-signed certificate in `ssl-localhost/`, so your browser will show a warning the first time.
