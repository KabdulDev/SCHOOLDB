import java.util.Comparator;

public class Course implements Comparable<Course>, Comparator<Course>{

    //Course Variables
    private boolean isGraduateCourse;
    private int courseNum;
    private String courseDept;
    private int numCredits;


    //Course Constructor
    public Course(boolean isGraduateCourse, int courseNum, String courseDept, int numCredits){
        this.isGraduateCourse = isGraduateCourse;
        this.courseNum = (courseNum > 0) ? courseNum : 0;
        this.courseDept = courseDept;
        this.numCredits = (numCredits > 0) ? numCredits : 0;
    }

    //Getters
    public boolean isGraduateCourse() {
        return isGraduateCourse;
    }

    public int getCourseNum() {
        return courseNum;
    }

    public String getCourseDept() {
        return courseDept;
    }

    public int getNumCredits() {
        return numCredits;
    }

    public String getCourseName(){
        String courseName = (this.isGraduateCourse) ? "G" + this.courseDept + Integer.toString(this.courseNum) : "U" + this.courseDept + Integer.toString(this.courseNum);
        return courseName;
    }

    //Overrided public Methods
    @Override
    public boolean equals(Object obj){
        if (obj instanceof Course){
            Course otherCourse = (Course) obj;
            if (this.isGraduateCourse == otherCourse.isGraduateCourse()){
                if (this.courseNum == otherCourse.getCourseNum()){
                    if (this.numCredits == otherCourse.getNumCredits()){
                        if (this.courseDept.equalsIgnoreCase(otherCourse.getCourseDept())){
                            return true;
                        }
                    }
                }
            }
        }
        return false;

    }

    @Override
    public String toString(){
        String course = String.format("Course: %3s-%3d | Number of Credits: %02d | ", courseDept, courseNum, numCredits);
        if(this.isGraduateCourse){
            course = course.concat("Graduate");
        }
        else{
            course = course.concat("Undergraduate");
        }

        return course;
    }

    

    @Override
    public int compareTo(Course c){
       
        int comparisionVal = this.courseNum - c.getCourseNum();

        if(comparisionVal == 0){  
            return 0; 
        }
        else if(comparisionVal > 0)  {
            return 1;  
        }
        else  {
            return -1;  
        }
    }
    @Override
    public int compare(Course c1, Course c2){

        int comparisionVal = c1.getCourseNum() - c2.getCourseNum();

        if(comparisionVal == 0){  
            return 0; 
        }
        else if(comparisionVal > 0)  {
            return 1;  
        }
        else  {
            return -1;  
        }
    }


    
}
