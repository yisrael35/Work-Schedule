package yisraelbar.com.workschedule;

import java.util.List;

public class EmployeesOptions {

   private String UserName;
   private List<Boolean> schedule;

   public EmployeesOptions (){

   }

    public EmployeesOptions (String e1,List<Boolean> s1){

//    this.schedule=new Schedule(s1);
        this.UserName=e1;
        this.schedule=s1;

    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public List<Boolean> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<Boolean> schedule) {
        this.schedule = schedule;
    }

    public String toString(){
       Schedule s1=new Schedule();
        s1.convertTheListIntoArray(schedule);
       return UserName+" "+s1.toString();
    }

}
