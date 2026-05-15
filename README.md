# Student Management System

This is a simple Java-based Student Management System application. It allows users to manage student records, courses, and enrollments via a command-line interface.

## Features

- Add new students and courses
- Enroll students in courses
- Update student details and course information
- Delete students and courses (only if no students are enrolled)
- Search for students by name
- Display all students and their enrolled courses
- All data (students, courses, enrollments) are persisted in plain text files for simplicity

## Main Implementations

- **Student, Course, and Enrollment management**: Core Java classes are used to handle basic object-oriented design for students and courses.
- **Data persistence**: Student and course information, as well as enrollments, are saved and loaded from local text files (`students.txt`, `courses.txt`, `enrollements.txt`).
- **Input validation**: All user inputs for names, IDs, and ages are validated for correctness.
- **Console-based menu**: Users interact with the system using a clear, menu-driven command-line interface.

## How To Use

1. Run the `Main.java` application in your Java-capable IDE or terminal.
2. Follow the menu prompts to add students, add courses, enroll students, search, update, and delete records.
3. All changes are automatically saved to the corresponding text files.

## File Structure

- `src/Student.java` - Student model and logic
- `src/Course.java` - Course model and logic
- `src/StudentManagementSystem.java` - Main business logic for managing the application
- `src/Main.java` - Entry point with menu and UI logic
- `students.txt`, `courses.txt`, `enrollements.txt` - Data files for persistence

## Requirements

- Java 8 or above (the code uses Java standard features)
- Any Java IDE or command-line Java setup

## Note

- This is a simple educational project and does not use databases or graphical user interfaces.
- All persistent data is stored in plain text files in the project directory.
