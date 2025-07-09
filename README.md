# 📋 Electronic Health Record (EHR) System  
**Final Project for CC103 - Object-Oriented Programming**  

*Developed by Davedev*  

![Java](https://img.shields.io/badge/Java-17-blue)  
![License](https://img.shields.io/badge/License-MIT-green)  

A **console-based Electronic Health Record (EHR) System** built with Java, designed for healthcare professionals to manage patient and doctor records efficiently.  

---

## ✨ Features  

### **Patient Management**  
- ✅ **CRUD Operations**: Add, view, update, and archive patient records  
- 📂 **Archival System**: Safely archive/unarchive records without deletion  
- 🔍 **Search**: Find patients by ID  
- 📝 **Detailed Records**:  
  - Personal info, medical history, allergies, treatment plans  
  - Emergency contacts, assigned doctors  

### **Doctor Management**  
- 👨‍⚕️ **Add/View Doctors**: Track doctor specializations  
- 🏷️ **Assign Doctors**: Link patients to doctors  

### **User Experience**  
- 🎨 **Color-Coded Console**: ANSI colors for better readability  
- ✔️ **Input Validation**: Ensures correct formats (dates, phone numbers, etc.)  
- 📅 **Auto Age Calculation**: From date of birth  

### **Security & Compliance**  
- 🔒 **Privacy Notice**: Prompts users about data confidentiality  
- ♻️ **Safe Archiving**: Prevents accidental data loss  

---

## 🛠️ Technical Implementation  

### **OOP Principles Used**  
- **Encapsulation**: Private fields with getters/setters  
- **Abstraction**: Separate UI (`*View` classes) from logic (`*Service` classes)  
- **Composition**: `PatientService` depends on `DoctorService`  

### **Key Classes**  
| Class | Purpose |  
|-------|---------|  
| `Patient` | Stores patient data (ID, name, medical history, etc.) |  
| `Doctor` | Manages doctor details (ID, name, specialization) |  
| `PatientService` | Handles patient operations (CRUD, archiving) |  
| `InputValidator` | Validates user inputs (numbers, dates, text) |  
| `MenuUtil` | Manages console UI (menus, screen clearing) |  

### **Error Handling**  
- Try-catch blocks with colored error messages  
- Prevents crashes on invalid inputs  

---

## 🚀 How to Run  

1. **Requirements**:  
   - Java 17+  
   - Terminal/CMD (for console output)  

2. **Steps**:  
   ```bash
   git clone 
   cd EHR-System  
   javac Main.java  
   java Main  
   ```

---

## 📜 License  
MIT License - Free for academic/learning use.  

---

## 👥 Contributors  
- Anggit, John Robert
- Balonzo, John Venedick
- Gascon, Jerell
- Quidan, Excequel
- Rentoria, Mark Rainier
  
**Submitted for CC103 - AISAT DASMARIÑAS COLLEGE**  

--- 

🔹 *For a detailed code walkthrough, check the source files!*  

---
