import java.util.Comparator;

public class Person implements Comparable <Person> , Comparator <Person> {

    //Person Variables
    private String name;
    private int birthYear;

    //Person Constructors
    public Person(){
        this.name = "";
        this.birthYear = 0;
    }

    public Person(String name, int birthYear){
        this.name = name;
        this.birthYear = (birthYear > 0) ? birthYear : 0;
    }

    //Person Getters
    public String getName() {
        return name;
    }
    public int getBirthYear() {
        return birthYear;
    }

    //Person Setters
    public void setName(String name){
        this.name = name;
    }

    public void setBirthYear(int birthYear){
        this.birthYear = (birthYear > 0) ? birthYear : 0;
    }

    @Override
    public boolean equals(Object obj){
        if(obj instanceof Person){
            Person otherP = (Person) obj;
            if (this.birthYear == otherP.getBirthYear()) {
                if (this.name.equalsIgnoreCase(otherP.getName())){
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public String toString(){
        return String.format("Person: Name: %30s | Birth Year: %4d", name, birthYear);
    }

    @Override
    public int compareTo(Person p){

        int comparisionVal = this.birthYear- p.getBirthYear();

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
    public int compare(Person p1, Person p2){

        int comparisionVal = p1.getBirthYear()- p2.getBirthYear();

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
