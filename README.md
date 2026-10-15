# Courier-Delivery-Route-and-Tracking-
# SM Courier Service 📦

## Abstract

**SM Courier Service** is a console-based Courier Management System developed using **Java**. The project provides separate functionalities for customers and administrators to manage courier deliveries.

Customers can create a new courier, receive a unique courier ID, get the delivery charge calculated from the package weight, and track their package status. Administrators can view all courier records, update delivery statuses, view the total revenue, see how many couriers are in each status, and sort couriers by charge.

All courier data is saved to a text file, so records are still available the next time the program is run.

The project demonstrates **object-oriented programming (classes, constructors, encapsulation), exception handling, string handling, file I/O, the Collections Framework, lambda expressions and streams**, along with core concepts such as methods, loops and switch statements.

---

## Features

### 👤 Customer Portal

* Create a new courier
* Enter customer name, destination, package weight and delivery distance
* Delivery eligibility check (deliveries are accepted only up to 500 km)
* Generate a unique Courier ID
* Calculate delivery charges automatically
* Track courier using Courier ID
* Return to the main menu

### 👨‍💼 Admin Portal

* View all registered couriers with full details (ID, name, destination, weight, distance, status, charge)
* Update courier status
* View total revenue
* View a summary of how many couriers are in each status
* View couriers sorted by highest charge
* Return to the main menu

### 🚚 Courier Status

* PACKAGE CREATED
* IN TRANSIT
* OUT FOR DELIVERY
* DELIVERED

### 💾 Data Saving

* Courier records are saved to `couriers.txt` after every new courier or status update
* Records are loaded automatically when the program starts
* If no saved file exists, 5 sample couriers are loaded

### ✅ Input Validation

* Non-numeric input is handled with exception handling instead of crashing the program
* Empty names and destinations are not accepted
* Weight and distance must be greater than 0

---

## Technologies Used

* **Programming Language:** Java
* **IDE:** VS Code
* **Version Control:** Git & GitHub
* **Input Handling:** Java Scanner
* **Data Storage:** `ArrayList` of objects, saved to a text file using `java.nio.file`
* **Application Type:** Console-based application

---

## Java Concepts Used

| Concept | Where it is used |
| ------- | ---------------- |
| Classes and objects | `Courier` class, one object per courier |
| Constructors (overloading) | One constructor for new couriers, another for loaded or sample couriers |
| Encapsulation | Private fields in `Courier` with getters and a setter |
| Static members | `nextId` counter in `Courier` |
| `toString()` | Displays a courier's details |
| Exception handling | `try-catch` in `readInt()`, multi-catch while loading the file |
| String handling | `trim()`, `replace()`, `toUpperCase()`, `isEmpty()`, `split()` |
| Sorting with `Comparator` | Viewing couriers by highest charge |
| File I/O | `Files`, `Path`, `BufferedReader`, `BufferedWriter` with `try-with-resources` |
| Collections | `ArrayList<Courier>`, `Map` |
| Lambdas and method references | `findCourier()`, `TotalIncome()` |
| Streams | `filter`, `findFirst`, `mapToInt`, `groupingBy`, `counting` |

---

## Program Structure

### `Courier` class

Stores the ID, name, destination, weight, distance, charge and status of one courier.

### `FinalProject` class

| Method | Purpose |
| ------ | ------- |
| `readInt()` / `readText()` | Read and validate user input |
| `CreateDelivery()` | Takes customer and package details and creates a courier |
| `DeliveryCharge()` | Calculates courier charges |
| `DeliveryEligibility()` | Checks whether the distance is within the delivery limit |
| `findCourier()` | Searches for a courier by ID |
| `CourierTracking()` | Returns the status of a courier |
| `UpdateStatus()` | Updates the status of a courier |
| `ViewCouriers()` | Displays all courier records |
| `ViewByCharge()` | Displays couriers sorted by highest charge |
| `TotalIncome()` | Calculates total revenue |
| `StatusSummary()` | Counts couriers in each status |
| `saveData()` / `loadData()` | Save to and load from `couriers.txt` |
| `customerMenu()` / `adminMenu()` | Customer and Admin menus |

The `main()` method loads the saved data and controls the main menu.

---

## Working of the System

### Customer Flow

```text
START
  ↓
Main Menu
  ↓
Customer Portal
  ↓
Create Courier
  ↓
Enter Package Details
  ↓
Eligibility Check
  ↓
Generate Courier ID
  ↓
Calculate Delivery Charge
  ↓
Save to File
  ↓
Track Courier / Create Another Courier
```

### Admin Flow

```text
START
  ↓
Main Menu
  ↓
Admin Portal
  ↓
View Couriers
     OR
Update Courier Status
     OR
View Total Revenue
     OR
View Status Summary
     OR
View Couriers by Highest Charge
  ↓
Back to Main Menu
```

---

## Advantages

1. **Simple and Easy to Use**
   The menu-driven interface makes the system easy to operate.

2. **Unique Courier Identification**
   Each newly created courier receives a unique Courier ID.

3. **Courier Tracking**
   Customers can check the current status of their courier using its ID.

4. **Separate User Roles**
   Customer and Admin functionalities are separated, making the system more organized.

5. **Automatic Charge Calculation**
   The delivery charge is calculated automatically based on package weight.

6. **Status Management**
   Administrators can update the courier status as the delivery progresses.

7. **Revenue and Reports**
   The admin can see total income, a status summary and couriers sorted by charge.

8. **Data Is Saved**
   Records are stored in a file and are available again after the program is restarted.

9. **Safe Input Handling**
   Invalid input is handled without crashing the program.

10. **Demonstrates Core Java Concepts**
    The project applies object-oriented programming, exception handling, file I/O, collections and streams in a practical application.

---

## Limitations

* Data is stored in a plain text file, not in a database.
* There is no route system yet; only the destination and distance are stored.
* Deliveries are limited to a distance of 500 km.
* There is no graphical user interface.
* There is no login or authentication system, so anyone can use the admin portal or track any ID.
* The saved file is not encrypted.

---

## Future Upgrades

### 1. Database Integration

Use **MySQL** instead of a text file to store customer details, courier details, delivery status, charges, destination and distance.

### 2. Route Management

Add a route system that stores the source, destination, distance and possible delivery route.

### 3. Delivery Time Estimation

Calculate an estimated delivery time using distance and delivery speed.

### 4. Improved Charge Calculation

Charges can be calculated using multiple factors such as:

* Package weight
* Distance
* Delivery type
* Priority delivery

### 5. Customer Login System

Add customer accounts so users can view their own courier history.

### 6. Admin Authentication

Add an admin username and password to restrict access to administrative functions.

### 7. Graphical User Interface

Convert the console application into a GUI using **Java Swing** or **JavaFX**.

### 8. Real-Time Tracking

A future version could integrate GPS/location-based tracking to display the approximate location of a delivery.

### 9. Route Map

A map-based interface could be added to visually display the courier's route from source to destination.

### 10. Web Application

The system could eventually be converted into a web application using technologies such as **HTML, CSS, JavaScript, Java Spring Boot, and MySQL**.

---

## Conclusion

The **SM Courier Service** project provides a basic computerized solution for managing courier deliveries. It allows customers to create and track packages while providing administrators with tools to manage courier statuses, view reports and calculate revenue.

The project demonstrates how object-oriented design, exception handling, file handling, collections and streams can be combined to build a functional real-world application. Although the current version is console-based and uses a text file for storage, it provides a foundation for future improvements such as database integration, route mapping, authentication, GUI development and real-time tracking.
