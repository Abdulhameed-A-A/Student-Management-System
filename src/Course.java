public class Course {

    private String courseCode;
    private String courseName;

    Course (String courseCode, String courseName){
        this.courseCode = courseCode;
        this.courseName = courseName;
    }

    String getCourseCode(){
        return this.courseCode;
    }

    String getCourseName(){
        return this.courseName;
    }

    void setCourseCode(String courseCode){
        this.courseCode = courseCode;
    }

    void setCourseName(String courseName){
        this.courseName = courseName;
    }

}
