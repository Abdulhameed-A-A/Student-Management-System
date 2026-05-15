import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class StudentManagementSystem {

    private final HashMap<Integer, Student> students;
    private final HashMap<String, Course> courses;
    private int nextStudentId = 1;
    private final String FILE_PATH_STUDENT = "students.txt";
    private final String FILE_PATH_COURSE = "courses.txt";
    private final String FILE_PATH_ENROLLMENT = "enrollements.txt";

    StudentManagementSystem(){
        this.students = new HashMap<>();
        this.courses = new HashMap<>();
    }

    void loadStudentsFromFile(){
        try{
            BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH_STUDENT));
            String line;
            int id = 0;
            int age = 0;

            while((line = reader.readLine()) != null){
                String[] parts = line.split(",");

                if (parts.length != 3){
                    IO.println("Bad line Structure");
                    continue;
                }

                try {
                     id = Integer.parseInt(parts[0]);
                     age = Integer.parseInt(parts[2]);
                } catch (NumberFormatException e){
                    IO.println("Error Parsing id or age. NAN");
                }

                Student student = new Student(id, parts[1], age);

                students.put(student.getStudentId(), student);

                if(id >= nextStudentId) {
                    nextStudentId = id + 1;
                }
            }
            reader.close();
        } catch (FileNotFoundException e){
            IO.println("Error Loading File");
        } catch (IOException e){
            IO.println("Something Went Wrong");
        }
    }

    void saveStudentToFile(){
        try{
            FileWriter writer = new FileWriter(FILE_PATH_STUDENT);

            for (Student student : students.values()){
                String line = student.getStudentId() + "," + student.getStudentName() + "," + student.getStudentAge();
                writer.write(line + "\n");
            }
            writer.close();
        }
        catch (IOException e){
            IO.println("File path error");
        }
    }

    void saveCourseToFile(){
        try{
            FileWriter writer = new FileWriter(FILE_PATH_COURSE);

            for(Course course : courses.values()){
                String line = course.getCourseCode() + "," + course.getCourseName();

                writer.write(line + "\n");
            }
            writer.close();
        } catch(IOException e){
            IO.println("Something went wrong");
        }
    }

    void loadCoursesFromFile(){
        try{
            BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH_COURSE));
            String line;

            while((line = reader.readLine()) != null){
                String[] parts = line.split(",");

                Course course = new Course(parts[0], parts[1]);
                courses.put(course.getCourseCode(), course);
            }
        } catch (FileNotFoundException e){
            IO.println("File Not Found");
        } catch (IOException e){
            IO.println("Something Went Wrong");
        }
    }

    void saveEnrollmentsToFile(){
        try{
            FileWriter writer = new FileWriter(FILE_PATH_ENROLLMENT);

            for (Student student : students.values()){
                for(Course enrolledCourse : student.getEnrolledCourses()){
                    String line = student.getStudentId() + "," + enrolledCourse.getCourseCode();

                    writer.write(line + "\n");
                }
            }
            writer.close();
        } catch (IOException e) {
            IO.println("Something went wrong");
        }
    }

    void loadEnrollmentsFromFile(){
        try{
            BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH_ENROLLMENT));
            String line;
            int id = 0;

            while((line = reader.readLine()) != null){
                String[] parts = line.split(",");

                try{
                    id = Integer.parseInt(parts[0]);
                } catch (NumberFormatException e) {
                    IO.println("Error parsing. Id NAN");
                    continue;
                }

                Student student = students.get(id);
                Course course = courses.get(parts[1]);

                if(student != null && course != null){
                    student.enrollCourse(course);
                }
            }
            reader.close();
        } catch(FileNotFoundException e){
            IO.println("File not found");
        } catch (IOException e){
            IO.println("Something went wrong");
        }
    }

    void displayStudents(){
        int index = 1;
        for (Student student: students.values()){
            IO.println("Id: " + student.getStudentId() + " | Name: " + student.getStudentName() + " | Age: "+  student.getStudentAge());
            IO.println("--------------------");
            IO.println("Courses:");
            if(student.getEnrolledCourses().isEmpty()){
                IO.println("No Courses Enrolled");
            } else {
                for(Course enrolledCourse: student.getEnrolledCourses()){
                    IO.println(index++ + " " + enrolledCourse.getCourseCode() + " Name: " + enrolledCourse.getCourseName() );
                }
            }
        }
    }

    Boolean addStudent(String inputName, int inputAge){

        Student student = new Student(nextStudentId, inputName, inputAge);
        nextStudentId++;
        students.put(student.getStudentId(), student);
        saveStudentToFile();
        return true;
    }

    Boolean addCourse(String inputCourseCode, String inputCourseName){

        Course course = new Course(inputCourseCode, inputCourseName);
        courses.put(course.getCourseCode(), course);
        saveCourseToFile();
        return true;
    }

    Student searchStudentById(int inputId) {
        return students.get(inputId);
    }

    ArrayList<Student> searchStudentByName(String studentName){
        ArrayList<Student> searchResult = new ArrayList<>();

        if(students.isEmpty()){
            return searchResult;
        }

        String inputName = studentName.toLowerCase().trim();

        for (Student student: students.values()){
            String name = student.getStudentName().toLowerCase().trim();

            if(name.equals(inputName) || name.contains(inputName)) {
                searchResult.add(student);
            }
        }
        return searchResult;
    }

    ArrayList<Course> searchCourseByName(String courseName){
        ArrayList<Course> searchResult = new ArrayList<>();
        if(courses.isEmpty()){
            return searchResult;
        }

        String inputName = courseName.toLowerCase().trim();

        for (Course course: courses.values()){
            String name = course.getCourseName().toLowerCase().trim();

            if(name.equals(inputName) || name.contains(inputName)){
                searchResult.add(course);
            }
        }
        return searchResult;
    }

    Course searchCourseByCourseCode(String inputCourseCode){
        return  courses.get(inputCourseCode);
    }

    boolean enrollStudentInCourse(int studentId, String courseCode){

        Student student = students.get(studentId);
        Course course = courses.get(courseCode);

        if(student != null && course != null){
            if(student.enrollCourse(course)){

                saveEnrollmentsToFile();
                return true;
            }
        }

        return false;
    }

    Boolean updateStudentName(int inputStudentId, String inputNewName){
        Student student = searchStudentById(inputStudentId);
        if(student != null){
            student.setStudentName(inputNewName);
            saveStudentToFile();
            return true;
        }

        return false;
    }

    Boolean updateStudentAge(int inputStudentId, int inputNewAge){
        Student student = searchStudentById(inputStudentId);
        if(student != null){
            student.setStudentAge(inputNewAge);
            return true;
        }

        return false;
    }

    Boolean updateCourseName(String inputCourseCode, String inputNewName){
        Course course = searchCourseByCourseCode(inputCourseCode);
        if(course != null){
            course.setCourseName(inputNewName);
            saveCourseToFile();
        }
        return false;
    }

    boolean deleteStudent(int inputId){
        if(searchStudentById(inputId) != null ) {
            students.remove(inputId);
            saveStudentToFile();
            return true;
        }

        return false;
    }

    boolean deleteCourse(String inputCourseCode){

        if(searchCourseByCourseCode(inputCourseCode) != null){
            for (Student student: students.values()){
                for(Course enrolledCourse: student.getEnrolledCourses()){
                    if(enrolledCourse.getCourseCode().equalsIgnoreCase(inputCourseCode)){
                        return false;
                    }
                }
            }

            courses.remove(inputCourseCode);
            saveCourseToFile();

            return true;
        }
        return false;
    }
}