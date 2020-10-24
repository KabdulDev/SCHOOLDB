public class Student extends Person{

    //Student Variables
    private static int numStudents = 0;
    private int studentID;
    private Course[] coursesTaken;
    private int numCoursesTaken;
    private boolean isGraduate;
    private String major;
    private int numCredits;

    //Student Constructors
    public Student(){
        super();
        numStudents++;
        this.studentID = numStudents;
        this.coursesTaken = new Course[100];
        this.numCoursesTaken = 0;
        this.isGraduate = false;
        this.major = "undeclared";
        this.numCredits = 0;
    }

    public Student(boolean isGraduate){
        super();
        numStudents++;
        this.studentID = numStudents;
        this.coursesTaken = new Course[100];
        this.numCoursesTaken = 0;
        this.isGraduate = isGraduate;
        this.major = "undeclared";
        this.numCredits = 0;
    }

    public Student(String major, boolean isGraduate){
        super();
        numStudents++;
        this.studentID = numStudents;
        this.coursesTaken = new Course[100];
        this.numCoursesTaken = 0;
        this.isGraduate = isGraduate;
        this.major = major;
        this.numCredits = 0;
    }

    public Student(String name, int birthYear, String major, boolean isGraduate){
        super(name, birthYear);
        numStudents++;
        this.studentID = numStudents;
        this.coursesTaken = new Course[100];
        this.numCoursesTaken = 0;
        this.isGraduate = isGraduate;
        this.major = major;
        this.numCredits = 0;
    }

    //Student Getters

    public boolean isGraduate() {
        return isGraduate;
    }

    public static int getNumStudents() {
        return numStudents;
    }

    public int getNumCoursesTaken() {
        return numCoursesTaken;
    }

    public int getNumCredits() {
        return numCredits;
    }

    public int getStudentID() {
        return studentID;
    }

    public String getMajor() {
        return major;
    }

    public Course getCourseTaken(int index){
        if (index < 0 || index > this.numCoursesTaken){
            return null;
        }

        else{
            return coursesTaken[index] ;
        }
    }

    public Course [] getAllCoursesTaken(){
        return coursesTaken;
    }

    public String getCourseTakenAsString (int index){
        if (index < 0 || index > this.numCoursesTaken){
            return "courseDept-courseNum";
        }

        else{
            return coursesTaken[index].toString() ;
        }
    }

    public String getAllCoursesTakenAsString(){
        String allCourses=" ";

        if (numCoursesTaken <= 0){
            return allCourses;
        }

        for (int i = 0; i < numCoursesTaken - 1; i++){
            allCourses = allCourses.concat(coursesTaken[i].toString() + ",") ;
            }
            allCourses = allCourses.concat(coursesTaken[numCoursesTaken - 1 ].toString() );

        return allCourses;

    }

    //Student Setters
    public void setIsGraduate( boolean isGraduate) {
        this.isGraduate = isGraduate;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    //Student Public Methods

    public void addCourseTaken(Course course){
        this.coursesTaken[numCoursesTaken] = course;
        numCoursesTaken++;
        this.numCredits += course.getNumCredits();
    }

    public void addCoursesTaken(Course[] course){
        for(int i = 0; i < course.length; i++ ){
            this.coursesTaken[numCoursesTaken] = course[i];
            numCoursesTaken++;
            this.numCredits += course[i].getNumCredits();
        }
    }

    //Override Public Methods
    @Override
    public boolean equals(Object o) {
        if (o instanceof Student) {
            if( super.equals(o) ) {
                Student s = (Student) o;
                if( this.studentID == s.getStudentID()) {
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public String toString() {
        String graduate = (this.isGraduate)? "Graduate" : "Undergraduate";
        String student = String.format( super.toString() + " Student: studentID: %04d | Major %20s | %14s | Number of Courses Taken: %3d | Courses Taken: %s", studentID, major, graduate, numCoursesTaken, getAllCoursesTakenAsString() ) ;

        return student;
    }
    

    @Override
    public int compareTo(Person p) {
        if ( !(p instanceof Student) ){
            return -1;  
        }
        
        else {
            Student otherS = (Student) p;
            int comparisionVal = this.getNumCredits() - otherS.getNumCredits() ;

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
    public int compare(Person p1, Person p2) {
            Student s1 = (Student) p1;
            Student s2 = (Student) p2;

            int comparisionVal = s1.getNumCredits()- s2.getNumCredits() ;

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
