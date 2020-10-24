public class GeneralStaff extends Employee{

    //GeneralStaff Variables
    private String duty;

    //GeneralStaff Constructors
    public GeneralStaff(){
        super();
        this.duty = "";
    }

    public GeneralStaff(String duty){
        super();
        this.duty = duty;
    }

    public GeneralStaff(String deptName, String duty){
        super(deptName);
        this.duty = duty;
    }

    public GeneralStaff(String name, int birthYear, String deptName, String duty){
        super(name, birthYear, deptName);
        this.duty = duty;
    }

    //GeneralStaff Getters
    public String getDuty() {
        return duty;
    }

    //GeneralStaff Setter
    public void setDuty(String duty) {
        this.duty = duty;
    }

   //Override Public Methods

   @Override
   public boolean equals(Object o){
       if( o instanceof GeneralStaff) {
           if(super.equals(o) ) {
                GeneralStaff otherG = (GeneralStaff) o;
                if (this.duty.equalsIgnoreCase(otherG.getDuty() ) ) {
                    return true;
                }
            }
        }
       return false;
   }

   @Override
   public String toString(){
        String generalStaff = String.format( super.toString() + " GeneralStaff: Duty: %10s", duty) ;

        return generalStaff;

   }
    
}
