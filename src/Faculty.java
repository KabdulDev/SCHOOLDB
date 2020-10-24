public class Faculty extends Employee{

    //Faculty Variables
    private Course [] coursesTaught;
    private int numCoursesTaught;
    private boolean isTenured;

    //Faculty Constructor
    public Faculty(){
        super();
        coursesTaught = new Course[100];
        numCoursesTaught = 0;
        this.isTenured = false;
    }

    public Faculty(boolean isTenured){
        super();
        coursesTaught = new Course[100];
        numCoursesTaught = 0;
        this.isTenured = isTenured;
    }

    public Faculty(String deptName, boolean isTenured){
        super(deptName);
        coursesTaught = new Course[100];
        numCoursesTaught = 0;
        this.isTenured = isTenured;
    }

    public Faculty(String name, int birthYear, String deptName, boolean isTenured){
        super(name, birthYear, deptName);
        coursesTaught = new Course[100];
        numCoursesTaught = 0;
        this.isTenured = isTenured;
    }

    //Faculty Getters

    public boolean isTenured(){
        return isTenured;
    }

    public int getNumCoursesTaught() {
        return numCoursesTaught;
    }

    public Course getCourseTaught(int index){
        if (index < 0 || index > this.numCoursesTaught){
            return null;
        }

        else{
            return coursesTaught[index] ;
        }
    }

    public Course [] getAllCoursesTaught() {

        return coursesTaught;

    }

    public String getAllCoursesTaughtAsString(){
        String allCourses=" ";

        if (numCoursesTaught <= 0){
            return allCourses;
        }

        for (int i = 0; i < numCoursesTaught -1 ; i++){
            allCourses = allCourses.concat(coursesTaught[i].toString() + ",") ;
            }
            allCourses = allCourses.concat(coursesTaught[numCoursesTaught -1 ].toString() );

        return allCourses;

    }

    //Faculty Setters

    public void setIsTenured (boolean isTenured){
        this.isTenured = isTenured;
    }

    //Faculty Public Methods

    public void addCourseTaught(Course course){
        this.coursesTaught[numCoursesTaught] = course;
        numCoursesTaught++;
    }

    public void addCoursesTaught(Course[] course){
        for(int i = 0; i < course.length; i++ ){
            this.coursesTaught[numCoursesTaught] = course[i];
            numCoursesTaught++;
        }
    }

    //Override Public Methods

    @Override
    public boolean equals(Object o){
        if( o instanceof Faculty) {
            if(super.equals(o) ) {
                Faculty otherF = (Faculty) o;
                if( this.isTenured == otherF.isTenured() ){
                    if(this.numCoursesTaught == otherF.getNumCoursesTaught() ){
                        if (this.getAllCoursesTaughtAsString().equalsIgnoreCase(otherF.getAllCoursesTaughtAsString() ) ) {
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    @Override
    public String toString() {
        String tenured = (isTenured() )? "Is Tenured" : "Not Tenured";
        String faculty = (String.format(super.toString() + " Faculty: %11s | Number of Courses Taught: %3d | Courses Taught: %s", tenured, numCoursesTaught, this.getAllCoursesTaughtAsString() ) ) ;
        return faculty;

    }

    @Override
    public int compareTo(Person p){
        if ( !(p instanceof Faculty) ){
            return -1;  
        }
        
        else {
            Faculty otherF = (Faculty) p;
            int comparisionVal = this.numCoursesTaught - otherF.getNumCoursesTaught() ;

            if(comparisionVal == 0){  
                return 0; 
            }
            else if(comparisionVal > 0)  {
                return 1;  
            }
            else {
                return -1;  
            }  
        }
    }

    @Override
    public int compare(Person p1, Person p2){
        
        Faculty f1 = (Faculty) p1;
        Faculty f2 = (Faculty) p2;
        int comparisionVal = f1.getNumCoursesTaught() - f2.getNumCoursesTaught() ;
        if(comparisionVal == 0){  
            return 0; 
        }
        else if(comparisionVal > 0)  {
            return 1;  
        }
        else {
            return -1;  
        }  
    }
    
}
