/*
 * 
 * 
 * Youtube Interactive Run Through -
 * Youtube Architecture Review and BreakDown -  
 * GitHub Link - 
 * 
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

public class Driver_SchoolDB {

    // private database object variables
    private static ArrayList<Course> Courses = new ArrayList<Course>();
    private static ArrayList<Faculty> Faculty = new ArrayList<Faculty>();
    private static ArrayList<GeneralStaff> GeneralStaff = new ArrayList<GeneralStaff>();
    private static ArrayList<Student> Students = new ArrayList<Student>();
    private static String schoolName = "SCHOOL";

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
            String deptName = objParams[0].replace(" ","");
            boolean isTenured = (objParams[1].replace(" ", "").equalsIgnoreCase("true")) ? true : false;
            Faculty f = new Faculty(deptName, isTenured);
            Faculty.add(f);
            return f;
        }

        if (i == 3) {
            String name = objParams[0].trim();
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
                String duty = objParams[0].trim();
                GeneralStaff g = new GeneralStaff(duty);
                GeneralStaff.add(g);
                return g;
            }
        }
        if (i == 1) {
            String deptName = objParams[0].replace(" ", "");
            String duty = objParams[1].trim();
            GeneralStaff g = new GeneralStaff(deptName, duty);
            GeneralStaff.add(g);
            return g;
        }

        if (i == 3) {
            String name = objParams[0].trim();
            int birthYear = Integer.parseInt(objParams[1].replace(" ", ""));
            String deptName = objParams[2].replace(" ", "");
            String duty = objParams[3].trim();
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
                        String name = objParams[0].trim();
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

    // Private Database and File functions
    private static void loadDatabaseFromFile() throws IOException{

        FileInputStream fileByteStream = null;
        Scanner inFS = null;
        Scanner scnr = new Scanner (System.in);
        String filePath = "src/SchoolDB_Initial.txt";

        System.out.printf("The default database file is %s. If you wish to use this file, press y to confirm. Press any other key to enter different database file", filePath);

        char confirm = Character.toLowerCase(scnr.next().charAt(0));
        if ( confirm == 'y') {
            
            try{
                fileByteStream = new FileInputStream(filePath);
                inFS = new Scanner(fileByteStream);
    
                objectGen(inFS);
                fileByteStream.close();
                }
                catch (IOException e){
                    System.out.println("Caught IOException: " + e.getMessage());
                }
        }
        else{
            System.out.println("Please enter the full path to the database file");
            filePath = scnr.next();

            try{
            fileByteStream = new FileInputStream(filePath);
            inFS = new Scanner(fileByteStream);

            objectGen(inFS);
            fileByteStream.close();
            }
            catch (IOException e){
                System.out.println("Caught IOException: " + e.getMessage());
            }

        }

        

    }
    
    private static void clearDatabase() {
        Courses.clear();
        Faculty.clear();
        GeneralStaff.clear();
        Students.clear();
        schoolName = "SCHOOL";

    }

    private static void writeDatabaseToFile () throws IOException {

        FileOutputStream fileByteStream = null;
        PrintWriter outFS = null;
        Scanner scnr = new Scanner (System.in);
        String filePath = "src/";

        System.out.println("Enter filename for database:");
        filePath = filePath + scnr.next();
        filePath = filePath + ".txt";
            
        try{
            fileByteStream = new FileOutputStream(filePath);
            outFS = new PrintWriter(fileByteStream);

    
            for(Course c : Courses){
                outFS.printf("Course: %s,%s,%s,%s%n", c.isGraduateCourse(),c.getCourseNum(),c.getCourseDept(), c.getNumCredits() );
                outFS.flush();
            }

            ArrayList <Employee> e = new ArrayList <Employee>();
            e.addAll(Faculty);
            e.addAll(GeneralStaff);
            Collections.sort(e);
            for( Employee elem : e){
                if(elem instanceof Faculty ) {
                    Faculty f = (Faculty) elem;
                    if( !(f.isTenured() ) && f.getDeptName().isEmpty() && f.getBirthYear() == 0 && f.getName().isEmpty() ) {
                        outFS.printf("Faculty: %n");
                    }

                    else if ( (f.isTenured() ) && f.getDeptName().isEmpty() && f.getBirthYear() == 0 && f.getName().isEmpty() ){
                        outFS.printf("Faculty: %s%n",f.isTenured());
                    }

                    else if ( !(f.getDeptName().isEmpty() ) && f.getBirthYear() == 0 && f.getName().isEmpty() ) {
                        outFS.printf("Faculty: %s,%s%n",f.getDeptName(), f.isTenured());
                    }

                    else {
                        outFS.printf("Faculty: %s,%s,%s,%s%n",f.getName(), f.getBirthYear(), f.getDeptName(), f.isTenured());
                    }
                    outFS.flush();
                }

                if(elem instanceof GeneralStaff ) {
                    GeneralStaff g = (GeneralStaff) elem;
                    if(  g.getDeptName().isEmpty() && g.getBirthYear() == 0 && g.getName().isEmpty() &&g.getDuty().isEmpty() ) {
                        outFS.printf("GeneralStaff: %n");
                    }

                    else if ( !(g.getDuty().isEmpty() ) && g.getDeptName().isEmpty() && g.getBirthYear() == 0 && g.getName().isEmpty() ){
                        outFS.printf("GeneralStaff: %s%n",g.getDuty());
                    }

                    else if ( !(g.getDeptName().isEmpty() ) && g.getBirthYear() == 0 && g.getName().isEmpty() ) {
                        outFS.printf("GeneralStaff: %s,%s%n",g.getDeptName(), g.getDuty());
                    }

                    else {
                        outFS.printf("GeneralStaff: %s,%s,%s,%s%n",g.getName(), g.getBirthYear(), g.getDeptName(), g.getDuty() );
                    }
                    outFS.flush();
                }
            }

            for (Student s : Students) {
                if( !(s.isGraduate() ) && s.getMajor().isEmpty() && s.getBirthYear() == 0 && s.getName().isEmpty() ) {
                    outFS.printf("Student: %n");
                }

                else if ( (s.isGraduate() ) && s.getMajor().isEmpty() && s.getBirthYear() == 0 && s.getName().isEmpty() ){
                    outFS.printf("Student: %s%n",s.isGraduate());
                }

                else if ( !(s.getMajor().isEmpty() ) && s.getBirthYear() == 0 && s.getName().isEmpty() ) {
                    outFS.printf("Student: %s,%s%n",s.getMajor(), s.isGraduate());
                }

                else {
                    outFS.printf("Student: %s,%s,%s,%s%n",s.getName(), s.getBirthYear(), s.getMajor(), s.isGraduate());
                }
                outFS.flush();

            }

            fileByteStream.close();
        }

        catch (IOException e){
            System.out.println("Caught IOException: " + e.getMessage());
        }
    }
    

    // Private Menu Display Variables
    private static String maxBorder = "";
    private static String midBorder = "";
    private static String minBorder = "";
    private static int maxBorderLength = 100;
    private static int midBorderLength = 50;
    private static int minBorderLength = 25;

    //Private Menu Display Functions

    private static void borderInit(){
        if (maxBorder.isEmpty() ) {
            for (int i = 0; i < maxBorderLength; i++) {
                maxBorder = maxBorder.concat("*");
            }
            for (int i = 0; i < midBorderLength; i++) {
                midBorder = midBorder.concat("*");
            }
            for (int i = 0; i < minBorderLength; i++) {
                minBorder = minBorder.concat("*");
            }
        }
    }

    private static void titleMenuDisplay() {

        System.out.println(maxBorder);
        System.out.println(schoolName + " DATABASE INFO:");
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

    // Private Interactive Menu's with options

    private static void facultyMenuOptions() {

        Scanner scnr = new Scanner (System.in);

        String [] facultyCommands = {"Create new faculty", "Create multiple new faculty", "Add course to faculty member", "Add courses to faculty member", "Check if faculty contains specific course", "Check course at index of faculty member", "Determine max and min courses taught", "See current faculty", "Exit"};

        System.out.println(minBorder);
        System.out.println("Faculty Menu:");
        System.out.println(minBorder);
        for (int i = 0; i < facultyCommands.length; i++) {
            System.out.printf("%d. %s%n", (i+1),facultyCommands[i]);
        }
        System.out.print("Selection: ");

        try{
            int selection = scnr.nextInt();
            if ( ! (selection <= facultyCommands.length && selection > 0) ){
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
                System.out.println("Please select a course to add to faculty member");
                Course c = courseSelector();
                
                System.out.println("Please select a faculty member");
                Faculty f = facultySelector();
                
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
                    for (int i=0; i < coursesToAdd; i++){
                        System.out.println("Please select a course to add to faculty member");
                        courseArray[i] = courseSelector();
                    }


                    System.out.printf("Please select a faculty member");
                    Faculty f = facultySelector();

                    
                    
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

                try{
                    if (Faculty.isEmpty()){
                        throw new Exception("Error: No faculty objects available");
                    }
                        System.out.printf("Faculty with max course load is%n%s%n",  Collections.max(Faculty) );
                        System.out.printf("Faculty with min course load is%n%s%n",  Collections.min(Faculty) );
                    
                }
                
                catch (Exception e){
                    System.out.println(e.getMessage() );
                }
                finally{
                    facultyMenuOptions();
                }
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

    private static void generalStaffMenuOptions() {

        Scanner scnr = new Scanner (System.in);

        String [] generalStaffCommands = {"Create new general staff", "Create multiple new general staff",  "See all current general staff", "Exit"}; //To - Add: "Add duty to general staff member",

        System.out.println(minBorder);
        System.out.println("General Staff Menu:");
        System.out.println(minBorder);
        for (int i = 0; i < generalStaffCommands.length; i++) {
            System.out.printf("%d. %s%n", (i+1),generalStaffCommands[i]);
        }
        System.out.print("Selection: ");

        try{
            int selection = scnr.nextInt();
            if ( ! (selection <= generalStaffCommands.length && selection > 0) ){
                throw new Exception("Not a Valid Menu option");
            }
            else if (selection == 1 ) { //Create New generalStaff Single
                generalStaffAdd(1);
                generalStaffMenuOptions();
            }
            else if (selection == 2 ) { //Create multiple new General Staff 
                generalStaffAdd();
                generalStaffMenuOptions();
            }
            // else if (selection == 3) { //Add Duty to generalStaff object

            //     GeneralStaff g = generalStaffSelector();
            //     System.out.print("Enter general staff member's duties:");
                    
            //     String duty = scnr.nextLine();
            //     duty = duty.trim();

            //     g.setDuty(duty);
            //     generalStaffMenuOptions();
            // }


            else if (selection == 3) { //Display all current General Staff}
                generalStaffMenuDisplay();
                generalStaffMenuOptions();
            }

            else if (selection == 4) { //Just Exits Nothing special
                System.out.println("Exiting General Staff menu");
            }

        }
        catch (Exception e){
            System.out.println("Invalid entry: " + e.getMessage() );
            System.out.println("Exiting General Staff menu");
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
        System.out.print("Selection: ");

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
                System.out.println("Please select a course to add to student member");
                Course c = courseSelector();
                
                System.out.println("Please select a student member");
                Student s = studentSelector();
                
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

                    for (int i=0; i < coursesToAdd; i++){
                        System.out.println("Please select a course to add to student member");
                        courseArray[i] = courseSelector();
                    }

                    System.out.printf("Please select a student member");
                    Student s = studentSelector();

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
                try{

                    if (Students.isEmpty()){
                        throw new Exception("Error: No student objects available");
                    }

                
                    Student sMax = Collections.max(Students);
                    Student sMin = Collections.min(Students);
                    
                    System.out.printf("Student with most number of credits at %d is %n%s%n", sMax.getNumCredits(), sMax );
                    System.out.printf("Student with least number of credits at %d is%n%s%n", sMin.getNumCredits(), sMin);
            }

            catch (Exception e){
                System.out.println(e.getMessage() );
            }
            finally{
                studentMenuOptions();
            }
                

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
        System.out.print("Selection: ");

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
                
                try{
                    if (Courses.isEmpty()){
                        throw new Exception("Error: No course objects available");
                    }

                
                System.out.printf("The smallest course is %n%s%n",  Collections.min(Courses) );
                }
                catch (Exception e){
                    System.out.println(e.getMessage());

                }

                finally{
                    courseMenuOptions();
                }

            }

            else if (selection == 4) { //Determine max course load
                try{
                    if (Courses.isEmpty()){
                        throw new Exception("Error: No course objects available");
                    }

                
                System.out.printf("The largest course is %n%s%n",  Collections.max(Courses) );
                }
                catch (Exception e){
                    System.out.println(e.getMessage());

                }

                finally{
                    courseMenuOptions();
                }
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

    private static void databaseAdminOptions() throws IOException{

        Scanner scnr = new Scanner (System.in);

        String [] adminCommands = {"Load database from file", "Append new entries from file", "Clear database entries", "Save database state to file", "See full database", "Change school name", "Exit"};

        System.out.println(maxBorder);
        System.out.println("Admin Menu:");
        System.out.println(maxBorder);
        for (int i = 0; i < adminCommands.length; i++) {
            System.out.printf("%d. %s%n", (i+1),adminCommands[i]);
        }
        System.out.print("Selection: ");

        try{
            int selection = scnr.nextInt();
            if ( ! (selection <= adminCommands.length && selection > 0) ){
                throw new Exception("Not a Valid Menu option");
            }
            else if (selection == 1 ) { //Load database from file
                clearDatabase();
                loadDatabaseFromFile();
                databaseAdminOptions();
            }
            else if (selection == 2 ) { //Append file entries to database
                loadDatabaseFromFile();
                databaseAdminOptions();
            }
            
            else if (selection == 3) { //Clear running database
                clearDatabase();
                databaseAdminOptions();

            }

            else if (selection == 4) { //Save database state to file
                writeDatabaseToFile();
                databaseAdminOptions();

            }

            else if (selection == 5) { //See full database
                titleMenuDisplay();
                databaseAdminOptions();
            }

            else if (selection == 6) { //Rename School
                System.out.println("Enter New School Name:");
                schoolName = scnr.nextLine();
                databaseAdminOptions();
            }

            else if (selection == 7) { //Just Exits Nothing special
                System.out.println("Exiting admin menu");
            }

        }
        catch (Exception e){
            System.out.println("Invalid entry: " + e.getMessage() );
            System.out.println("Exiting admin menu");
        }

    }

    private static void databaseAuthenticator () {
        Scanner scnr = new Scanner (System.in);

        System.out.printf("Only authorized personal allowed access to %s database under penalty of law. Do you have permission to access?(y/n)%n", schoolName);

        try{
            char confirm = Character.toLowerCase(scnr.next().charAt(0));
            if ( confirm == 'y') {
                interactiveMenuOptions();
            }

            else if ( confirm == 'n') {
                System.out.println("Closing database");
                            
            }
            else {
                // scnr.close();
                throw new Exception ("Invalid Entry");
            }  
    
        }
    
        catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Closing database");
    
        }
    

    }
    
    private static void interactiveMenuOptions() {

        Scanner scnr = new Scanner (System.in);


        String [] menuOptions = {"Database Admin Menu", "Course Menu", "Faculty Menu", "General Staff Menu", "Student Menu", "Exit"};

        

        System.out.println(maxBorder);
        System.out.println("Menu Options:");
        System.out.println(maxBorder);
        for (int i = 0; i < menuOptions.length; i++) {
            System.out.printf("%d. %s%n", (i+1),menuOptions[i]);
        }
        System.out.print("Selection: ");

        try{
            int selection = scnr.nextInt();

            if ( ! (selection <= menuOptions.length && selection > 0) ){
                throw new Exception("Not a Valid Menu option");
            }

            else if ( selection == 1) { //Database Admin Menu
                databaseAdminOptions();
                interactiveMenuOptions();

            }

            else if ( selection == 2) { //Course Menu
                courseMenuOptions();
                interactiveMenuOptions();

            }

            else if ( selection == 3) { //Faculty Menu
                facultyMenuOptions();
                interactiveMenuOptions();

            }

            else if ( selection == 4) { //General Staff Menu
                generalStaffMenuOptions();
                interactiveMenuOptions();

            }

            else if ( selection == 5 ){ //Student Menu
                studentMenuOptions();
                interactiveMenuOptions();

            }

            else if ( selection == 6 ) { //Exit
                System.out.println("Exiting and Closing database");
            }
                    
        }

        catch (Exception e){
            System.out.println("Invalid Entry: " + e.getMessage());
            System.out.println("Please try again");
            interactiveMenuOptions();
        }
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
        String[] questionDisply = { "Please enter course's department: ","Please enter person's name: ", "Please enter employee's department: ", "Please enter employee's duty: ", "Please enter student's major: " };

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
            return courseSelector();
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
            return studentSelector();
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
            return facultySelector();
        }
        
    }

    private static GeneralStaff generalStaffSelector() {

        Scanner scnr = new Scanner (System.in);

        System.out.println(minBorder);
        System.out.println("General Staff:");
        System.out.println(minBorder);
        for (int i = 0; i < GeneralStaff.size(); i++) {
            System.out.printf("%d. %s%n", (i+1), GeneralStaff.get(i).toString());
        }
        System.out.printf("%d. New general staff%n", GeneralStaff.size() + 1 );
        System.out.println("Please select a general staff member:");

        try{
            int selection = scnr.nextInt();
            if (selection <= GeneralStaff.size() && selection > 0){
                return getGeneralStaffAt(selection - 1);
            }
            else if (selection == GeneralStaff.size() + 1) {
                return returnGeneralStaffAdd();
            }
            else {
                throw new Exception("Out of index");
            }

        }
        catch (Exception e){
            System.out.println("Invalid entry: " + e.getMessage() );
            return generalStaffSelector();
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
        return getFacultyAt(Faculty.size()-1);
    }

    private static GeneralStaff returnGeneralStaffAdd(){
        generalStaffAdd(1);
        return getGeneralStaffAt(GeneralStaff.size()-1) ;
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
        
        borderInit();
        databaseAuthenticator();
    }
}