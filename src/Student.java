import java.util.ArrayList;

public class Student {

    private final int studentId;
    private String studentName;
    private int studentAge;
    private final ArrayList<Course> enrolledCourses;

    Student(int id, String name, int age ){
        this.studentId = id;
        this.studentName = name;
        this.studentAge = age;
        this.enrolledCourses = new ArrayList<>();
    }

    int getStudentId(){
        return this.studentId;
    }

    String getStudentName(){
        return this.studentName;
    }

    int getStudentAge(){
        return this.studentAge;
    }

    void setStudentName(String studentName){
        this.studentName = studentName;
    }

    void setStudentAge(int studentAge){
        this.studentAge = studentAge;
    }

    void enrollCourse(Course course){
        for (Course enrolledcourse: enrolledCourses){
            if( course.getCourseCode().equals(enrolledcourse.getCourseCode())){
                IO.println("Already enrolled to this course");
                return;
            }
        }

        enrolledCourses.add(course);
    }

    void unenrollCourse(String courseCode){
        if(enrolledCourses.isEmpty()){
            IO.println("No Course Enrolled");
        } else {
            for(int i = 0; i < enrolledCourses.size(); i++){
                if(courseCode.equals(enrolledCourses.get(i).getCourseCode())){
                    enrolledCourses.remove(i);
                    IO.println(courseCode + " removed successfully");
                    return;
                }
            }
        }
    }

    ArrayList<Course> getEnrolledCourses(){
        return this.enrolledCourses;
    }
}
