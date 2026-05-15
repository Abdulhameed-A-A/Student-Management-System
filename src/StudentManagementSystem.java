import java.util.ArrayList;
import java.util.HashMap;

public class StudentManagementSystem {

    private final HashMap<Integer, Student> students;
    private final HashMap<String, Course> courses;
    private int nextStudentId = 1;

    StudentManagementSystem(){
        this.students = new HashMap<>();
        this.courses = new HashMap<>();
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
        return true;
    }

    void addCourse(String inputCourseCode, String inputCourseName){

        Course course = new Course(inputCourseCode, inputCourseName);
        courses.put(course.getCourseCode(), course);
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

    Course searchCourseByCourseCode(String inputCourCode){
        return  courses.get(inputCourCode);
    }

    Boolean enrollStudentInCourse(int studentId, String courseCode){
        Student student = students.get(studentId);
        Course course = courses.get(courseCode);

        if(student != null && course != null){
            student.enrollCourse(course);
            return true;
        } else {
            return false;
        }
    }

    Boolean updateStudentName(int inputStudentId, String inputNewName){
        Student student = searchStudentById(inputStudentId);
        if(student != null){
            student.setStudentName(inputNewName);
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
        }

        return false;
    }
}