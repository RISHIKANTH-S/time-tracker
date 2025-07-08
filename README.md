# ⏱️ TimeTracker — Employee Work Hour Management System

**TimeTracker** is a Spring Boot–based backend system designed for efficient tracking and management of employee work hours. The application supports **punch-in/out**, **admin reporting with date filters and pagination**, and **automated email reminders** for missed punches. Data persistence is handled using **PostgreSQL**, and the system is structured for clean role-based access.

## ✨ Features

### 👨‍💼 Employee & Admin Roles
- Employees can punch in/out and view their own data.
- Admins can access, edit , and manage all user punch records.

### 🕐 Punch Tracking
- Employees punch in and out.
- Prevents duplicate punches.
- Records timestamps and calculates worked hours.

### 📅 Date-Based Reporting for Admins
- Filter punch records by specific **date** or **date range**.
- Useful for generating reports on daily, weekly, or custom periods.

### 📃 Paginated Results
- Admin punch record queries support **pagination**.
- Reduces load and improves performance for large datasets.

### 📧 Email Reminder System
- Sends daily reminders (e.g., 10:15 PM) to employees who forgot to punch out.

FYI: This Project is Abstract level Design Of what i worked as a Project Associate
- Uses Spring Scheduler + Gmail SMTP.
  
### 🔐 Role-Based Access Control (RBAC)
- Routes and operations are restricted based on role (`EMPLOYEE`, `ADMIN`).
- Prevents unauthorized access to sensitive records.

