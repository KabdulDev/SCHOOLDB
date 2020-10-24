
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

public class test {

    // private database object variables
    private static ArrayList<Course> Courses = new ArrayList<Course>();
    private static ArrayList<Faculty> Faculty = new ArrayList<Faculty>();
    private static ArrayList<GeneralStaff> GeneralStaff = new ArrayList<GeneralStaff>();
    private static ArrayList<Student> Students = new ArrayList<Student>();
    private static String schoolName;

    // private Object Gen helper methods
    private static void objectGen(Scanner inputLine) {

        String[] objTypes = { "Course:", "Faculty:", "GeneralStaff:", "Student:" };

        while (inputLine.hasNextLine()) {
            for (int i = 0; i < objTypes.length; i++) {
                if (inputLine.hasNext(objTypes[i])) {
                    inputLine.next(objTypes[i]);
                    String[] objParams = inputLine.nextLine().split(",");
                    switch (i) {
                        case 0:
                            genCourse(objParams);
                            break;
                        case 1:
                            genFaculty(objParams);
                            break;
                        case 2:
                            genGeneralStaff(objParams);
                            break;
                        case 3:
                            genStudent(objParams);
                            break;
                        default:
                            break;
                    }
                    ;
                }
            }
        }
    }

    private static Course genCourse(String[] objParams) {

        boolean isGraduate = (objParams[0].replace(" ", "").equalsIgnoreCase("true")) ? true : false;
        int courseNum = Integer.parseInt(objParams[1].replace(" ", ""));
        String courseDept = objParams[2].replace(" ", "");
        int numCredits = Integer.parseInt(objParams[3].replace(" ", ""));
        Course course = new Course(isGraduate, courseNum, courseDept, numCredits);
        Courses.add(course);

        return course;
    }

    private static Faculty genFaculty(String[] objParams) {

        int i = objParams.length - 1;
        if (i == 0) {
            if (objParams[0].isEmpty()) {
                Faculty f = new Faculty();
                Faculty.add(f);
                return f;
            } else {
                boolean isTenured = (objParams[0].replace(" ", "").equalsIgnoreCase("true")) ? true : false;
                Faculty f = new Faculty(isTenured);
                Faculty.add(f);
                return f;
            }
        }
        if (i == 1) {
            String deptName = objParams[0].replace(" ", "");
            boolean isTenured = (objParams[1].replace(" ", "").equalsIgnoreCase("true")) ? true : false;
            Faculty f = new Faculty(deptName, isTenured);
            Faculty.add(f);
            return f;
        }

        if (i == 3) {
            String name = objParams[0].replace(" ", "");
            int birthYear = Integer.parseInt(objParams[1].replace(" ", ""));
            String deptName = objParams[2].replace(" ", "");
            boolean isTenured = (objParams[3].replace(" ", "").equalsIgnoreCase("true")) ? true : false;
            Faculty f = new Faculty(name, birthYear, deptName, isTenured);
            Faculty.add(f);
            return f;
        } else {
            return null;
        }
    }

    private static GeneralStaff genGeneralStaff(String[] objParams) {

        int i = objParams.length - 1;
        if (i == 0) {
            if (objParams[0].isEmpty()) {
                GeneralStaff g = new GeneralStaff();
                GeneralStaff.add(g);
                return g;
            } else {
                String duty = objParams[0];
                GeneralStaff g = new GeneralStaff(duty);
                GeneralStaff.add(g);
                return g;
            }
        }
        if (i == 1) {
            String deptName = objParams[0].replace(" ", "");
            String duty = objParams[1];
            GeneralStaff g = new GeneralStaff(deptName, duty);
            GeneralStaff.add(g);
            return g;
        }

        if (i == 3) {
            String name = objParams[0].replace(" ", "");
            int birthYear = Integer.parseInt(objParams[1].replace(" ", ""));
            String deptName = objParams[2].replace(" ", "");
            String duty = objParams[3];
            GeneralStaff g = new GeneralStaff(name, birthYear, deptName, duty);
            GeneralStaff.add(g);
            return g;
        } else {
            return null;
        }
    }

    private static Student genStudent(String[] objParams) {

                int i = objParams.length - 1;
                    if(i == 0){
                        if(objParams[0].isEmpty() ){
                            Student s = new Student();
                            Students.add(s);
                            return s;
                        }
                        else{
                            boolean isGraduate = (objParams[0].replace(" ", "").equalsIgnoreCase("true")) ? true : false;
                            Student s = new Student(isGraduate);
                            Students.add(s);
                            return s;
                        }
                    }
                    else if( i==1){
                        String major = objParams[0].replace(" ", "");
                        boolean isGraduate = (objParams[1].replace(" ", "").equalsIgnoreCase("true")) ? true : false;
                        Student s = new Student(major, isGraduate);
                        Students.add(s);
                        return s;
                    }
                    
                    else if (i == 3) {
                        String name = objParams[0];
                        int birthYear = Integer.parseInt(objParams[1].replace(" ", ""));
                        String major = objParams[2].replace(" ", "");
                        boolean isGraduate = (objParams[3].replace(" ", "").equalsIgnoreCase("true")) ? true : false;
                        Student s = new Student(name, birthYear, major, isGraduate);
                        Students.add(s);
                        return s;
                    }
                    else{
                        return null;
                    }
                }

    // Private Menu Display Functions
    private static String maxBorder = "";
    private static String midBorder = "";
    private static String minBorder = "";
    private static int maxBorderLength = 100;
    private static int midBorderLength = 50;
    private static int minBorderLength = 25;

    private static void titleMenuDisplay() {
        for (int i = 0; i < maxBorderLength; i++) {
            maxBorder = maxBorder.concat("*");
        }
        for (int i = 0; i < midBorderLength; i++) {
            midBorder = midBorder.concat("*");
        }
        for (int i = 0; i < minBorderLength; i++) {
            minBorder = minBorder.concat("*");
        }
        System.out.println(maxBorder);
        System.out.println("SCHOOL DATABASE INFO:");
        System.out.println();
        courseMenuDisplay();
        personsMenuDisplay();
        System.out.println(maxBorder);
    }

    private static void topLevelDisplay(String schoolName) {
        for (int i = 0; i < maxBorderLength; i++) {
            maxBorder = maxBorder.concat("*");
        }
        for (int i = 0; i < midBorderLength; i++) {
            midBorder = midBorder.concat("*");
        }
        for (int i = 0; i < minBorderLength; i++) {
            minBorder = minBorder.concat("*");
        }
        System.out.println(maxBorder);
        System.out.println(schoolName + "DATABASE INFO:");
        System.out.println();
        courseMenuDisplay();
        personsMenuDisplay();
        System.out.println(maxBorder);

    }

    private static void courseMenuDisplay() {
        System.out.println(minBorder);
        System.out.println("COURSES:");
        for (int i = 0; i < Courses.size(); i++) {
            System.out.println(Courses.get(i).toString());
        }
        System.out.println(minBorder);
    }

    private static void personsMenuDisplay() {
        System.out.println(midBorder);
        System.out.println("PERSONS:");
        System.out.println();
        employeesMenuDisplay();
        studentsMenuDisplay();
        System.out.println(midBorder);

    }

    private static void employeesMenuDisplay() {
        System.out.println(midBorder);
        System.out.println("EMPLOYEES:");
        System.out.println();
        generalStaffMenuDisplay();
        facultyMenuDisplay();
        System.out.println(midBorder);

    }

    private static void generalStaffMenuDisplay() {
        System.out.println(minBorder);
        System.out.println("GENERAL STAFF:");
        for (int i = 0; i < GeneralStaff.size(); i++) {
            System.out.println(GeneralStaff.get(i).toString());
        }
        System.out.println(minBorder);

    }

    private static void facultyMenuDisplay() {
        System.out.println(minBorder);
        System.out.println("FACULTY:");
        for (int i = 0; i < Faculty.size(); i++) {
            System.out.println(Faculty.get(i).toString());
        }
        System.out.println(minBorder);

    }

    private static void studentsMenuDisplay() {
        System.out.println(minBorder);
        System.out.println("STUDENTS:");
        for (int i = 0; i < Students.size(); i++) {
            System.out.println(Students.get(i).toString());
        }
        System.out.println(minBorder);

    }

    private static void facultyMenuOptions() {

        Scanner scnr = new Scanner (System.in);

        String [] facultyCommands = {"Create new faculty", "Create multiple new faculty", "Add course to faculty member", "Add courses to faculty member", "Check if faculty contains specific course", "Check course at index of faculty member", "Determine max and min courses taught", "See current faculty", "Exit"};

        System.out.println(minBorder);
        System.out.println("Faculty Menu:");
        System.out.println(minBorder);
        for (int i = 0; i < facultyCommands.length; i++) {
            System.out.printf("%d. %s%n", (i+1),facultyCommands[i]);
        }

        try{
            int selection = scnr.nextInt();
            if ( ! (selection < facultyCommands.length && selection > 0) ){
                throw new Exception("Not a Valid Menu option");
            }
            else if (selection == 1 ) { //Create New Faculty Single
                facultyAdd(1);
                facultyMenuOptions();
            }
            else if (selection == 2 ) { //Create New Faculty Multiple
                facultyAdd();
                facultyMenuOptions();
            }
            else if (selection == 3) { //Add Course to Faculty object
                System.out.println("Please select a faculty member");
                Faculty f = facultySelector();
                System.out.println("Please select a course to add to faculty member");
                Course c = courseSelector();
                f.addCourseTaught(c);
                facultyMenuOptions();
            }

            else if (selection == 4) { //Add Courses to Faculty Object

                try{
                    System.out.print("Enter how many courses to add to faculty: ");
                    int coursesToAdd = scnr.nextInt();

                    if( coursesToAdd <=0 ){
                        throw new Exception ("Can only add positive number of courses.");
                    }

                    Course [] courseArray = new Course [coursesToAdd];

                    System.out.printf("Please select a faculty member");
                    Faculty f = facultySelector();

                    for (int i=0; i < coursesToAdd; i++){
                        System.out.println("Please select a course to add to faculty member");
                        courseArray[i] = courseSelector();
                    }
                    
                    f.addCoursesTaught(courseArray);
                    facultyMenuOptions();

                }
                catch (Exception e){
                    System.out.println("Invalid entry: " + e.getMessage() );
                    System.out.println("Exiting course adder");
                    facultyMenuOptions();
                }
            }
            else if (selection == 5) {//Check if faculty member teaches specifc course
                System.out.println("Please select a faculty member");
                Faculty f = facultySelector();
                System.out.println("Please select a course to verify");
                Course c = courseSelector();
                boolean doesTeach = false;


                for (Course i : f.getAllCoursesTaught() ){
                    if(c.equals(i)){
                        doesTeach = true;
                        break;
                    }
                }
                String queryResult = doesTeach ? String.format("Selected faculty does teach %s", c.getCourseName() ) : String.format("Selected faculty does not teach %s", c.getCourseName() );
                System.out.println(queryResult);
                facultyMenuOptions();
            }

            else if (selection == 6) { //Determine course at index
                
                System.out.println("Please select a faculty member:");
                Faculty f = facultySelector();

                try{
                    System.out.print("Please enter index to check:");
                    int index = scnr.nextInt();

                    System.out.println(" The course listed is " + getCourseAt(f,index)) ;
                }

                catch (Exception e){
                    System.out.println("There has been an error. Your entry is not a valid Index");
                }



                facultyMenuOptions();

            }

            else if (selection == 7) { //Determine max and min course load
                
                System.out.printf("Faculty with max course load is%n%s%n",  Collections.max(Faculty) );
                System.out.printf("Faculty with min course load is%n%s%n",  Collections.min(Faculty) );
                facultyMenuOptions();

            }

            else if (selection == 8) { //Display all current faculty}
                facultyMenuDisplay();
                facultyMenuOptions();
            }

            else if (selection == 9) { //Just Exits Nothing special
                System.out.println("Exiting faculty menu");
            }

        }
        catch (Exception e){
            System.out.println("Invalid entry: " + e.getMessage() );
            System.out.println("Exiting faculty menu");
        }
        
    }
    
    private static void studentMenuOptions() {

        Scanner scnr = new Scanner (System.in);

        String [] studentCommands = {"Create new student", "Create multiple new students", "Add course to student", "Add courses to student", "Check if student has taken specific course", "Check course at index of student", "Determine max and min credit hours of students", "See current student roster", "Exit"};

        System.out.println(minBorder);
        System.out.println("Student Menu:");
        System.out.println(minBorder);
        for (int i = 0; i < studentCommands.length; i++) {
            System.out.printf("%d. %s%n", (i+1),studentCommands[i]);
        }

        try{
            int selection = scnr.nextInt();
            if ( ! (selection <= studentCommands.length && selection > 0) ){
                throw new Exception("Not a Valid Menu option");
            }
            else if (selection == 1 ) { //Create New student Single
                studentAdd(1);
                studentMenuOptions();
            }
            else if (selection == 2 ) { //Create New student Multiple
                studentAdd();
                studentMenuOptions();
            }
            else if (selection == 3) { //Add Course to student object
                System.out.println("Please select a student member");
                Student s = studentSelector();
                System.out.println("Please select a course to add to student member");
                Course c = courseSelector();
                s.addCourseTaken(c);
                studentMenuOptions();
            }

            else if (selection == 4) { //Add Courses to Student Object

                try{
                    System.out.print("Enter how many courses to add to student: ");
                    int coursesToAdd = scnr.nextInt();

                    if( coursesToAdd <=0 ){
                        throw new Exception ("Can only add positive number of courses.");
                    }

                    Course [] courseArray = new Course [coursesToAdd];

                    System.out.printf("Please select a student member");
                    Student s = studentSelector();

                    for (int i=0; i < coursesToAdd; i++){
                        System.out.println("Please select a course to add to student member");
                        courseArray[i] = courseSelector();
                    }
                    
                    s.addCoursesTaken(courseArray);
                    studentMenuOptions();

                }
                catch (Exception e){
                    System.out.println("Invalid entry: " + e.getMessage() );
                    System.out.println("Exiting course adder");
                    studentMenuOptions();
                }
            }
            else if (selection == 5) {//Check if student member teaches specifc course
                System.out.println("Please select a student member");
                Student s = studentSelector();
                System.out.println("Please select a course to verify");
                Course c = courseSelector();
                boolean hasTaken = false;


                for (Course i : s.getAllCoursesTaken() ){
                    if(c.equals(i)){
                        hasTaken = true;
                        break;
                    }
                }
                String queryResult = hasTaken ? String.format("Selected student has taken %s", c.getCourseName() ) : String.format("Selected student has not taken %s", c.getCourseName() );
                System.out.println(queryResult);
                studentMenuOptions();
            }

            else if (selection == 6) { //Determine course at index
                
                System.out.println("Please select a student member:");
                Student s = studentSelector();

                try{
                    System.out.print("Please enter index to check:");
                    int index = scnr.nextInt();

                    System.out.println(" The course listed is " + getCourseAt(s,index)) ;
                }

                catch (Exception e){
                    System.out.println("There has been an error. Your entry is not a valid Index");
                }



                studentMenuOptions();

            }

            else if (selection == 7) { //Determine max and min course load
                
                System.out.printf("Student with most credits is %n%s%n",  Collections.max(Students) );
                System.out.printf("Student with least credits is%n%s%n",  Collections.min(Students) );
                studentMenuOptions();

            }

            else if (selection == 8) { //Display all current student}
                studentsMenuDisplay();
                studentMenuOptions();
            }

            else if (selection == 9) { //Just Exits Nothing special
                System.out.println("Exiting student menu");
            }

        }
        catch (Exception e){
            System.out.println("Invalid entry: " + e.getMessage() );
            System.out.println("Exiting student menu");
        }
        
    }
    
    private static void courseMenuOptions() {

        Scanner scnr = new Scanner (System.in);

        String [] courseCommands = {"Create new course", "Create multiple new courses", "Determine minimum Course", "Determine maximum course", "See all courses", "Exit"};

        System.out.println(minBorder);
        System.out.println("Course Menu:");
        System.out.println(minBorder);
        for (int i = 0; i < courseCommands.length; i++) {
            System.out.printf("%d. %s%n", (i+1),courseCommands[i]);
        }

        try{
            int selection = scnr.nextInt();
            if ( ! (selection <= courseCommands.length && selection > 0) ){
                throw new Exception("Not a Valid Menu option");
            }
            else if (selection == 1 ) { //Create New course Single
                courseAdd(1);
                courseMenuOptions();
            }
            else if (selection == 2 ) { //Create New course Multiple
                courseAdd();
                courseMenuOptions();
            }
            
            else if (selection == 3) { //Determine min course load
                
                System.out.printf("The smallest course is%n%s%n",  Collections.min(Courses) );
                courseMenuOptions();

            }

            else if (selection == 4) { //Determine max course load
                
                System.out.printf("The largest course is %n%s%n",  Collections.max(Courses) );
                courseMenuOptions();

            }

            else if (selection == 5) { //Display all current course}
                courseMenuDisplay();
                courseMenuOptions();
            }

            else if (selection == 6) { //Just Exits Nothing special
                System.out.println("Exiting course menu");
            }

        }
        catch (Exception e){
            System.out.println("Invalid entry: " + e.getMessage() );
            System.out.println("Exiting course menu");
        }
        

    }

    private static void interactiveMenuOptions() {

    }

    // Menu Interactive Helper Functions

    private static String interactiveBoolean(int Mode) {

        // String[] modeOptions = { "Course", "Faculty", "Student" };
        Scanner scnr = new Scanner(System.in);
        String[] questionDisply = { "Is this a graduate course?", "Is this a tenured professor",
                "Is this a graduate student" };

        if ( !( (Mode - 1) == 0 || (Mode - 1) == 1 || (Mode - 1) == 2) ){
            // scnr.close();
            return "false";

        } 
        else {

            System.out.printf("%s:%n1. Yes%n2. No%nSelection:", questionDisply[Mode-1]);

            try {
                int selection = scnr.nextInt();
                while ( !(selection == 1 || selection == 2) ){
                    System.out.println("Please enter a valid selection");
                    System.out.printf("%s:%n1. Yes%n2. No%nSelection:", questionDisply[Mode-1]);
                    selection = scnr.nextInt();
                }
                if (selection == 1) {
                    // scnr.close();
                    return "true";
                } 
                else {
                    // scnr.close();
                    return "false";
                }
            }
            catch (Exception e){
                // scnr.close();
                System.out.println("Invalid entry: " + e.getMessage());
                System.out.println("Defaulting to false");
                return "false";
            }
        }

    }

    private static String interactiveInteger(int Mode) {

        // String[] modeOptions = { "Course Number", "Course Credits", "Person's Birth
        // Year" };
        Scanner scnr = new Scanner(System.in);
        String[] questionDisply = { "What is the course num?", "How many credits is this course?",
                "What year was this person born" };

        if ( !( (Mode - 1) == 0 || (Mode - 1) == 1 || (Mode - 1) == 2) ){
            // scnr.close();
            return "0";

        } 
        else {

            System.out.printf("%s:", questionDisply[Mode-1]);
            try {
                int selection = scnr.nextInt();
                // scnr.close();

                if (selection < 0) {
                    throw new Exception("Invalid entry");
                }
                return String.valueOf(selection);
            }

            catch (Exception exception) {
                System.out.println(exception.getMessage());
                System.out.println("Defaulting to 0");
                return "0";
            }
        }

    }

    private static String interactiveString(int Mode){
        // String[] modeOptions = { "Course Dept", "Person name", "Employee Dept", "Gen Staff duty", "Student Major" };
        Scanner scnr = new Scanner(System.in);
        String[] questionDisply = { "Please enter course's department: ","Please enter person's name: ", "Please enter employee's department: ", "Please enter emploee's duty: ", "Please enter student's major: " };

        if ( Mode > questionDisply.length || Mode <= 0 ){
            // scnr.close();
            return "";

        } 
        else {

            System.out.printf("%s", questionDisply[Mode-1]);
            try {
                String selection = scnr.nextLine();
                // scnr.close();

                return selection;
            }

            catch (Exception exception) {
                System.out.println(exception.getMessage());
                System.out.println("Defaulting to blank");
                return "";
            }
        }

    }


    //Menu Interactive Object Returns

    private static Course courseSelector() {

        Scanner scnr = new Scanner (System.in);

        System.out.println(minBorder);
        System.out.println("Courses:");
        System.out.println(minBorder);
        for (int i = 0; i < Courses.size(); i++) {
            System.out.printf("%d. %s%n", (i+1), Courses.get(i).toString());
        }
        System.out.printf("%d. New course%n", Courses.size() + 1 );
        System.out.println("Please select a course:");

        try{
            int selection = scnr.nextInt();
            if (selection <= Courses.size() && selection > 0){
                return getCourseAt(selection-1);
            }
            else if (selection == Courses.size() + 1) {
                return returnCourseAdd();

            }
            else {
                throw new Exception("Out of index");
            }

        }
        catch (Exception e){
            System.out.println("Invalid entry: " + e.getMessage() );
            System.out.println("Exiting course selection menu");
            return null;
        }
        
    }

    private static Student studentSelector() {

        Scanner scnr = new Scanner (System.in);

        System.out.println(minBorder);
        System.out.println("Student:");
        System.out.println(minBorder);
        for (int i = 0; i < Students.size(); i++) {
            System.out.printf("%d. %s%n", (i+1), Students.get(i).toString());
        }
        System.out.printf("%d. New student%n", Students.size()+1 );
        System.out.println("Please select a student:");

        try{
            int selection = scnr.nextInt();
            if (selection <= Students.size() && selection > 0){
                return getStudentAt(selection -1);
            }
            else if (selection == Students.size() + 1) {
                return returnStudentAdd();

            }
            else {
                throw new Exception("Out of index");
            }

        }
        catch (Exception e){
            System.out.println("Invalid entry: " + e.getMessage() );
            System.out.println("Exiting student selection menu");
            return null;
        }
        
    }

    private static Faculty facultySelector() {

        Scanner scnr = new Scanner (System.in);

        System.out.println(minBorder);
        System.out.println("Faculty:");
        System.out.println(minBorder);
        for (int i = 0; i < Faculty.size(); i++) {
            System.out.printf("%d. %s%n", (i+1), Faculty.get(i).toString());
        }
        System.out.printf("%d. New faculty%n", Faculty.size() + 1 );
        System.out.println("Please select a faculty member:");

        try{
            int selection = scnr.nextInt();
            if (selection <= Faculty.size() && selection > 0){
                return getFacultyAt(selection - 1);
            }
            else if (selection == Faculty.size() + 1) {
                return returnFacultyAdd();
            }
            else {
                throw new Exception("Out of index");
            }

        }
        catch (Exception e){
            System.out.println("Invalid entry: " + e.getMessage() );
            System.out.println("Exiting raculty selection menu");
            return null;
        }
        
    }

    //Object Return Helper Functions (Indexers and adds with returns)
    private static Course getCourseAt (int index){
        
        try{
            if(index<0 || index >= Courses.size() ){
                throw new Exception  ("Invalid Index");
            }
            return Courses.get(index);
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage() ) ;
            return null;
        }
    }

    private static Course getCourseAt (Faculty f, int index){
        
        try{
            if(index<0 || index >= f.getNumCoursesTaught() ){
                throw new Exception  ("Invalid Index");
            }
            return f.getCourseTaught(index);
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage() ) ;
            return null;
        }
    }

    private static Course getCourseAt (Student s, int index){
        
        try{
            if(index<0 || index >= s.getNumCoursesTaken() ){
                throw new Exception  ("Invalid Index");
            }
            return s.getCourseTaken(index);
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage() ) ;
            return null;
        }
    }

    private static Faculty getFacultyAt (int index){

        try{
            if(index<0 || index >= Faculty.size() ){
                throw new Exception  ("Invalid Index");
            }
            return Faculty.get(index);
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage() ) ;
            return null;
        }

    }

    private static GeneralStaff getGeneralStaffAt (int index){ 

        try{
            if(index<0 || index >= GeneralStaff.size() ){
                throw new Exception  ("Invalid Index");
            }
            return GeneralStaff.get(index);
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage() ) ;
            return null;
        }
    }

    private static Student getStudentAt( int index){
        try{
            if(index<0 || index >= Students.size() ){
                throw new Exception  ("Invalid Index");
            }
            return Students.get(index);
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage() ) ;
            return null;
        }

    }

    private static Course returnCourseAdd() {
        
        courseAdd(1);
        return Courses.get(Courses.size()-1);
        
    }

    private static Student returnStudentAdd(){
        studentAdd(1);
        return getStudentAt(Students.size()-1);
    }

    private static Faculty returnFacultyAdd(){
        facultyAdd(1);
        return getFacultyAt(Students.size()-1);
    }
    
    private static Course [] returnCourseArrayAdd(int Val) {
        Course [] courseArray = new Course [Val];
        for (int i = 0; i < Val; i++) {
            courseArray[i] = returnCourseAdd();
        }
        return courseArray;
    }



    // Menu Interactive Object Add
    private static void courseAdd() {
        int defaultVal = 3;

        Scanner scnr = new Scanner(System.in);
        System.out.printf(
                "Create %d(Default) new courses? Press y to confirm or n to enter an alternate number. ", defaultVal);
        try {
            char confirm = Character.toLowerCase(scnr.next().charAt(0));
            if ( confirm == 'y') {
                courseAdd(defaultVal);
            }
            else if ( confirm == 'n') {
                System.out.print("Enter amount of additional new courses: ");
                try{
                    int coursesToAdd = scnr.nextInt();

                    // scnr.close();
                    courseAdd(coursesToAdd);
                }

                catch (Exception e){
                    System.out.println("Invalid Entry: " + e.getMessage());
                    System.out.println("Exiting course creation");

                }
                
            }
            else {
                // scnr.close();
                throw new Exception ("Invalid Entry");
            }  
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Exiting course creation");
            
        }
    }

    private static void courseAdd(int Val){

        for (int i = 0; i < Val; i++) {

            String[] objParams = new String[4];

            objParams[0] = interactiveBoolean(1); // Mode 1 is is Graduate Course
            objParams[1] = interactiveInteger(1); // Mode 1 is Course num
            objParams[2] = interactiveString(1); //Mode is Course Dept
            objParams[3] = interactiveInteger(2); // Mode 2 is Number of Credits

            System.out.println("Added course: " + genCourse(objParams) );
        }

    }
      
    private static void facultyAdd(){
        int defaultVal = 3;
        Scanner scnr = new Scanner(System.in);
        System.out.printf(
                "Create %d(Default) new faculty members? Press y to confirm or n to enter an alternate number. ", defaultVal);
        try {
            char confirm = Character.toLowerCase(scnr.next().charAt(0));
            if ( confirm == 'y') {
                facultyAdd(defaultVal);
            }
            else if ( confirm == 'n') {
                System.out.print("Enter amount of additional new faculty members: ");
                try{
                    int facultyToAdd = scnr.nextInt();
                    facultyAdd(facultyToAdd);
                    }
                catch (Exception e){
                    System.out.println("Invalid entry: " + e.getMessage());
                }
            }
            else {
                // scnr.close();
                throw new Exception ("Invalid Entry");
            }  
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Exiting faculty creation");
        }

    }

    private static void facultyAdd(int Val){
        String[] facultyCreationOptions = {"1. Enter all defaults","2. Enter tenure status","3. Enter department and tenure status", "4. Enter name, birth year, department, and tenure status" };
        

        Scanner scnr = new Scanner(System.in);
        for (int i = 0; i < Val; i++) {
            // scnr.close();

            for (int j = 0; j<facultyCreationOptions.length; j++){
                System.out.println(facultyCreationOptions[j]);
                }
                System.out.print("Selection: ");
                try {
                    int selection = scnr.nextInt();
                    if (selection == 1) {
                        String [] defaultParams = {" "};
                        System.out.println("Added faculty: " + genFaculty(defaultParams));
                    } 
                    else if (selection == 2) {
                        String [] objParams = new String [1];
                        objParams[0] = interactiveBoolean(2); // Tenured Mode
                        System.out.println("Added faculty: " + genFaculty(objParams));
                    } 
                    else if (selection == 3) {
                        String [] objParams = new String [2];
                        objParams[0] = interactiveString(3); //Mode is Employee Dept
                        objParams[1] = interactiveBoolean(2); //Tenured Mode
                            
                        System.out.println("Added faculty: " + genFaculty(objParams));

                    } 
                        
                    else if (selection == 4) {
                        String [] objParams = new String [4];
                        objParams[0] = interactiveString(2); //Mode is Person Name
                        objParams[1] = interactiveInteger(3); // Mode 1 is Birth Year
                        objParams[2] = interactiveString(3); // Mode is Employee Dept
                        objParams[3] = interactiveBoolean(2); //Tenured Mode
                            
                        System.out.println("Added faculty: " + genFaculty(objParams));

                    } 
                    else {
                        throw new Exception("Not a menu option");
                    }
                }
                catch (Exception e){
                    // scnr.close();
                    System.out.println("Invalid entry: " + e.getMessage());
                    System.out.println("Creating default faculty member");
                    String [] defaultParams = {" "};
                    System.out.println("Added faculty: " + genFaculty(defaultParams));
                }
        }
    }

    private static void generalStaffAdd(){
        int defaultVal = 3;
               

        Scanner scnr = new Scanner(System.in);
        System.out.printf(
                "Create %d(Default) new general staff? Press y to confirm or n to enter an alternate number. ", defaultVal);
        try {
            char confirm = Character.toLowerCase(scnr.next().charAt(0));
            if ( confirm == 'y') {
                generalStaffAdd(defaultVal);
            }

            else if ( confirm == 'n') {
                System.out.print("Enter amount of additional new general staff members: ");
                try{
                    int generalStaffToAdd = scnr.nextInt();
                    generalStaffAdd(generalStaffToAdd);

                }
                catch (Exception e){
                    System.out.println("Invalid entry: " + e.getMessage());
                        System.out.println("Creating default general staff member");
                        String [] defaultParams = {" "};
                        System.out.println("Added general staff: " + genFaculty(defaultParams));
                }
            }
            else {
                // scnr.close();
                throw new Exception ("Invalid Entry");
            }  
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Exiting general staff creation");
        }

    }

    private static void generalStaffAdd(int Val){

        String[] generalStaffCreationOptions = {"1. Enter all defaults","2. Enter general staff duty","3. Enter department and duty", "4. Enter name, birth year, department, and duty" };
        

        Scanner scnr = new Scanner(System.in) ;
        for (int i = 0; i < Val; i++) {
            // scnr.close();

            for (int j = 0; j<generalStaffCreationOptions.length; j++){
                System.out.println(generalStaffCreationOptions[j]);
            }
                System.out.print("Selection: ");
                try {
                    int selection = scnr.nextInt();
                    if (selection == 1) {
                        String [] defaultParams = {" "};
                        System.out.println("Added general staff: " + genGeneralStaff(defaultParams));
                    } 
                    else if (selection == 2) {
                        String [] objParams = new String [1];
                        objParams[0] = interactiveString(4); // Duty Mode
                        System.out.println("Added general staff: " + genGeneralStaff(objParams));
                    } 
                    else if (selection == 3) {
                        String [] objParams = new String [2];
                        objParams[0] = interactiveString(3); //Mode is Employee Dept
                        objParams[1] = interactiveString(4); // Duty Mode
                        System.out.println("Added general staff: " + genGeneralStaff(objParams));
                        } 
                        
                    else if (selection == 4) {
                        String [] objParams = new String [4];
                        objParams[0] = interactiveString(2); //Mode is Person Name
                        objParams[1] = interactiveInteger(3); // Mode 1 is Birth Year
                        objParams[2] = interactiveString(3); // Mode is Employee Dept
                        objParams[3] = interactiveString(4); // Duty Mode
                            
                        System.out.println("Added general staff: " + genGeneralStaff(objParams));

                    } 
                    else {
                        throw new Exception("Not a menu option");
                    }
                }
                    
                catch (Exception e){
                    // scnr.close();
                    System.out.println("Invalid entry: " + e.getMessage());
                    System.out.println("Creating default general staff member");
                    String [] defaultParams = {" "};
                    System.out.println("Added general staff: " + genFaculty(defaultParams));
                }
        }
    }
        
    private static void studentAdd(){
        int defaultVal = 3;
        
        Scanner scnr = new Scanner(System.in);
        System.out.printf(
                "Create %d(Default) new student members? Press y to confirm or n to enter an alternate number. ", defaultVal);
        try {
            char confirm = Character.toLowerCase(scnr.next().charAt(0));
            if ( confirm == 'y') {
                studentAdd(defaultVal);}
            else if ( confirm == 'n') {
                System.out.print("Enter amount of additional new student members: ");
                try{
                    int studentToAdd = scnr.nextInt();
                    // scnr.close();
                    studentAdd(studentToAdd);
                }
                catch (Exception e){
                    System.out.println("Invalid entry: " + e.getMessage());

                }
            }
            else {
                // scnr.close();
                throw new Exception ("Invalid Entry");
            }  
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Exiting student creation");
        }
    }
    
    private static void studentAdd(int Val){

        String[] studentCreationOptions = {"1. Enter all defaults","2. Enter graduate status","3. Enter major and graduate status", "4. Enter name, birth year, major, and graduate status" };
        

        Scanner scnr = new Scanner(System.in);

        for (int i = 0; i < Val; i++) {
            // scnr.close();

            for (int j = 0; j<studentCreationOptions.length; j++){
                System.out.println(studentCreationOptions[j]);
            }
            System.out.print("Selection: ");
            try {
                int selection = scnr.nextInt();
                if (selection == 1) {
                    String [] defaultParams = {" "};
                    System.out.println("Added student: " + genStudent(defaultParams));
                } 
                else if (selection == 2) {
                    String [] objParams = new String [1];
                    objParams[0] = interactiveBoolean(3); // Graduate Mode
                    System.out.println("Added student: " + genStudent(objParams));
                } 
                else if (selection == 3) {
                    String [] objParams = new String [2];
                    objParams[0] = interactiveString(5); // Major Mode
                    objParams[1] = interactiveBoolean(3); // Graduate Mode
                    
                    System.out.println("Added student: " + genStudent(objParams));

                } 
                
                else if (selection == 4) {
                    String [] objParams = new String [4];
                    objParams[0] = interactiveString(2); //Mode is Person Name
                    objParams[1] = interactiveInteger(3); // Mode 1 is Birth Year
                    objParams[2] = interactiveString(5); // Major Mode
                    objParams[3] = interactiveBoolean(3); // Graduate Mode
                    
                    System.out.println("Added student: " + genStudent(objParams));

                } 
                else {
                    throw new Exception("Not a menu option");
                }
            }
            catch (Exception e){
                // scnr.close();
                System.out.println("Invalid entry: " + e.getMessage());
                System.out.println("Creating default student member");
                String [] defaultParams = {" "};
                System.out.println("Added student: " + genStudent(defaultParams));
            }
        }
    }

    public static void main(String[] args) throws IOException {
        FileInputStream fileByteStream = null;
        Scanner inFS = null;

        fileByteStream = new FileInputStream("src/SchoolDB_Initial.txt");
        inFS = new Scanner(fileByteStream);

        objectGen(inFS);
        //fileByteStream.close();

        FileOutputStream fileStream = null;
        PrintWriter outFS = null;
        // Scanner scnr = new Scanner (System.in);
        String filePath = "src/test3.txt";

        fileStream = new FileOutputStream(filePath);
        outFS = new PrintWriter(fileStream);

        outFS.println("test");
        outFS.flush();

        // System.out.println("Enter filename for database:");
        // filePath = filePath + scnr.next();
        // filePath = filePath + ".txt";
            
        // try{
        //     fileStream = new FileOutputStream(filePath);
        //     outFS = new PrintWriter(fileStream);

        //     outFS.println("test");
    
        //     for(Course c : Courses){
        //         outFS.printf("Course: %s,%s,%s,%s%n", c.isGraduateCourse(),c.getCourseNum(),c.getCourseDept(), c.getNumCredits() );
        //     }

        //     for (Faculty f : Faculty) {
        //         if( !(f.isTenured() ) && f.getDeptName().isEmpty() && f.getBirthYear() == 0 && f.getName().isEmpty() ) {
        //             outFS.printf("Faculty: %n");
        //         }

        //         else if ( (f.isTenured() ) && f.getDeptName().isEmpty() && f.getBirthYear() == 0 && f.getName().isEmpty() ){
        //             outFS.printf("Faculty: %s%n",f.isTenured());
        //         }

        //         else if ( !(f.getDeptName().isEmpty() ) && f.getBirthYear() == 0 && f.getName().isEmpty() ) {
        //             outFS.printf("Faculty: %s,%s%n",f.getDeptName(), f.isTenured());
        //         }

        //         else {
        //             outFS.printf("Faculty: %s,%s,%s,%s%n",f.getDeptName(), f.getBirthYear(), f.getDeptName(), f.isTenured());
        //         }
        //     }

        //     for (GeneralStaff g : GeneralStaff) {
        //         if(  g.getDeptName().isEmpty() && g.getBirthYear() == 0 && g.getName().isEmpty() &&g.getDuty().isEmpty() ) {
        //             outFS.printf("GeneralStaff: %n");
        //         }

        //         else if ( !(g.getDuty().isEmpty() ) && g.getDeptName().isEmpty() && g.getBirthYear() == 0 && g.getName().isEmpty() ){
        //             outFS.printf("GeneralStaff: %s%n",g.getDuty());
        //         }

        //         else if ( !(g.getDeptName().isEmpty() ) && g.getBirthYear() == 0 && g.getName().isEmpty() ) {
        //             outFS.printf("GeneralStaff: %s,%s%n",g.getDeptName(), g.getDuty());
        //         }

        //         else {
        //             outFS.printf("GeneralStaff: %s,%s,%s,%s%n",g.getDeptName(), g.getBirthYear(), g.getDeptName(), g.getDuty() );
        //         }

        //     }
        
        //     for (Student s : Students) {
        //         if( !(s.isGraduate() ) && s.getMajor().isEmpty() && s.getBirthYear() == 0 && s.getName().isEmpty() ) {
        //             outFS.printf("Student: %n");
        //         }

        //         else if ( (s.isGraduate() ) && s.getMajor().isEmpty() && s.getBirthYear() == 0 && s.getName().isEmpty() ){
        //             outFS.printf("Student: %s%n",s.isGraduate());
        //         }

        //         else if ( !(s.getMajor().isEmpty() ) && s.getBirthYear() == 0 && s.getName().isEmpty() ) {
        //             outFS.printf("Student: %s,%s%n",s.getMajor(), s.isGraduate());
        //         }

        //         else {
        //             outFS.printf("Student: %s,%s,%s,%s%n",s.getMajor(), s.getBirthYear(), s.getMajor(), s.isGraduate());
        //         }
        //     }

        //     fileByteStream.close();
        // }

        // catch (IOException e){
        //     System.out.println("Caught IOException: " + e.getMessage());
        // }
    
        

        //titleMenuDisplay();
        // Scanner test = new Scanner (System.in);
        // System.out.println("Iterate x times");
        // int x = test.nextInt();

        // for (int i =0; i < x; i++){
        //     System.out.print(interactiveBoolean(1));
        // }
        //test.close();
        // titleMenuDisplay();
        // courseAdd();
        // facultyAdd();
        // generalStaffAdd();
       //facultyMenuOptions();
        
    }

}
