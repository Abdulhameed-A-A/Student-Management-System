static Scanner scanner = new Scanner(System.in);
static StudentManagementSystem studManage = new StudentManagementSystem();

void main() {
    studManage.loadStudentsFromFile();
    studManage.loadCoursesFromFile();
    studManage.loadEnrollmentsFromFile();
    IO.println("-----Student Management System-----");
    int option;
    boolean running = true;

    while(running){
        displayMenu();

        while(true){
            option = getValidInputInt("Enter your option: ");
            switch(option) {
                case 1, 2, 3, 4, 5, 6, 7, 8 -> {}
                default -> {
                    IO.println("Please enter a number between (1-6)");
                    continue;
                }
            }
            break;
        }

        switch (option) {
            case 1 -> addStudent();
            case 2 -> addCourse();
            case 3 -> enrollStudent();
            case 4 -> searchStudent();
            case 5 -> displayStudent();
            case 6 -> updateStudentCourse();
            case 7 -> deleteStudentCourse();
            case 8 -> running = false;
        }
    }
}

void displayMenu() {
    IO.println("1. Add Student");
    IO.println("2. Add Course");
    IO.println("3. Enroll Student");
    IO.println("4. Search Student");
    IO.println("5. Display Students");
    IO.println("6. Update");
    IO.println("7. Delete");
    IO.println("8. Exit");
}

void addStudent(){
    String inputName = capitalizeFirstLetter(getValidInputString("Enter your name: "));
    int inputAge = getValidInputInt("Enter your age: ");
    if (studManage.addStudent(inputName, inputAge)){
        IO.println("*Student Added Successfully*");
    }
}

void addCourse(){
    String inputCourseCode;
    while(true){
        inputCourseCode = getValidInputString("Enter Course Code: ").toUpperCase();

        if(studManage.searchCourseByCourseCode(inputCourseCode) != null){
            IO.println("Course Duplicate Exists");
            continue;
        }

        break;
    }
    String inputCourseName = capitalizeFirstLetter(getValidInputString("Enter Course Name: "));

    if(studManage.addCourse(inputCourseCode, inputCourseName)){
        IO.println("Course added successfully");
    }
}

void enrollStudent(){
    int inputId = getValidInputInt("Enter Student Id: ");
    String inputCourseCode = getValidInputString("Enter Course Code: ").toUpperCase();

    if(studManage.enrollStudentInCourse(inputId, inputCourseCode)){
        IO.println("Course enrolled successfully");
    } else {
        IO.println("Error Enrolling Course");
    }
}

void searchStudent(){
    ArrayList<Student> searchStudents;

    while(true){
        String inputName = capitalizeFirstLetter(getValidInputString("Enter Your Name: "));
        searchStudents = studManage.searchStudentByName(inputName);

        if(searchStudents.isEmpty()){
            IO.println("No match Found");
            continue;
        }

        break;
    }

    for (Student student: searchStudents){
        IO.println("Id: " + student.getStudentId() + " | Name: " + student.getStudentName() + " | Age: "+  student.getStudentAge());
    }

    scanner.close();
}

void displayStudent(){
    studManage.displayStudents();
}

void updateStudentCourse(){
    boolean running = true;

    while(running){
        IO.println("1. Update Student Name");
        IO.println("2. Update Student Age");
        IO.println("3. Update Course Name");
        IO.println("4. Back");
        int option = getValidInputInt("Enter your option: ");

        switch (option) {
            case 1 -> updateStudentName();
            case 2 -> updateStudentAge();
            case 3 -> updateCourseName();
            case 4 -> running = false;
            default -> IO.println("Enter a valid option");
        }
    }
}

void deleteStudentCourse(){
    boolean running = true;

    while(running){
        IO.println("1. Delete Student");
        IO.println("2. Delete Course");
        IO.println("3. Back");
        int option = getValidInputInt("Enter option(1-2): ");

        switch (option) {
            case 1 -> deleteStudent();
            case 2 -> deleteCourse();
            case 3 -> running = false;
            default -> IO.println("Enter a valid option");
        }
    }
}

//delete Methods

void deleteStudent(){
    int inputId = getValidInputInt("Enter student Id: ");

    if(!studManage.deleteStudent(inputId)){
        IO.println("Student Id entered does not exist");
    } else {
        IO.println("Student Successfully deleted");
    }
}

void deleteCourse(){
    String inputCourseCode =  getValidInputString("Enter Course Code: ").toUpperCase();
    if(!studManage.deleteCourse(inputCourseCode)){
        IO.println("Unable to delete Course. Student Still Enrolled");
    } else {
        IO.println("Course Deleted Successfully");
    }
}

//updates Methods

void updateStudentName(){
    int inputId = getValidInputInt("Enter Student id: ");
    String inputNewName = capitalizeFirstLetter(getValidInputString("Enter new Student name: "));

    if(studManage.updateStudentName(inputId, inputNewName)){
        IO.println("Student Name Updated Successfully");
    }
}

void updateStudentAge(){
    int inputId = getValidInputInt("Enter Student id: ");
    int inputNewAge = getValidInputInt("Enter new Student Age: ");

    if(studManage.updateStudentAge(inputId, inputNewAge)){
        IO.println("Student Age Updated Successfully");
    }
}

void updateCourseName(){
    String inputCourseCode = getValidInputString("Enter the course code: ").toUpperCase();
    String inputNewCourseName = capitalizeFirstLetter(getValidInputString("Enter new Course Name: "));

    if(studManage.updateCourseName(inputCourseCode, inputNewCourseName)){
        IO.println("Student Name Updated Successfully");
    }
}

//Input Validation for Types String and Int
String getValidInputString(String message){
    while(true){
        IO.print(message);
        String input = scanner.nextLine();

        if(!input.trim().isEmpty()){
            return input;
        }
        IO.println("This field cannot be empty");
    }
}

int getValidInputInt(String message){

    while(true){
        String inputString = getValidInputString(message);
        try {
            return Integer.parseInt(inputString);
        } catch (NumberFormatException e) {
            IO.println("Enter a valid Number");
        }
    }
}

//Utility Method
String capitalizeFirstLetter(String str){

    if(str == null || str.isEmpty()){
        return str;
    }

    return str.substring(0,1).toUpperCase() + str.substring(1).toLowerCase();
}