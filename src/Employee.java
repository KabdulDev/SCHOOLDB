public class Employee extends Person {

    //Employee Variables
    private String deptName;
    private int employeeID;
    private static int numEmployees = 0;


    //Employee Constructors
    public Employee(){
        super();
        numEmployees++;
        this.deptName = "";
        this.employeeID = numEmployees;
    }

    public Employee(String deptName){
        super();
        numEmployees++;
        this.deptName = deptName;
        this.employeeID = numEmployees;
    }

    public Employee(String name, int birthYear, String deptName){
        super(name, birthYear);
        numEmployees++;
        this.deptName = deptName;
        this.employeeID = numEmployees;
    }

    //Employee Getters
    public String getDeptName() {
        return deptName;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public static int getNumEmployees() {
        return numEmployees;
    }

    //Employee Setter
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }


    //Employee Override functions

    @Override
    public boolean equals(Object o) {
        if( o instanceof Employee) {
            if(super.equals(o) ) {
                Employee otherE = (Employee) o;
                if( this.employeeID == otherE.getEmployeeID() ){
                    if (this.deptName.equalsIgnoreCase(otherE.getDeptName() ) ) {
                        return true;
                    }
                }

            }
        }

        return false;
    }

    @Override
    public String toString() {
        String employee = String.format(super.toString() + " Employee: Department: %20s | Employee Number: %3d", deptName, employeeID) ;
        return employee;
    }

    @Override
    public int compareTo(Person p){
        if ( !(p instanceof Employee) ){
            return -1;  
        }
        
        else {
            Employee otherE = (Employee) p;
            int comparisionVal = this.employeeID - otherE.getEmployeeID() ;

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
        Employee e1 = (Employee) p1;
        Employee e2 = (Employee) p2;
        int comparisionVal = e1.getEmployeeID() - e2.getEmployeeID() ;
        
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
