package yisraelbar.com.workschedule;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class FireBase {
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    private List <Employee> employeeList=new ArrayList<Employee>();
    private List<EmployeesOptions> employeesOptionsList =new ArrayList<>();
    private List<ScheduleByDate> scheduleByDateList =new ArrayList<>();
    private Employee e1=new Employee();
    private Calendar c=Calendar.getInstance();
    private EmployeesOptions eo2;
    private int weekYear=c.get(Calendar.WEEK_OF_YEAR);
    private ScheduleByDate sbd=new ScheduleByDate();


    public void sendEmployeeScheduleInfoFb(EmployeesOptions eo1){
        DatabaseReference sendSchedule =
                database.getReference("Schedules").child("Option").child(eo1.getUserName());
        sendSchedule.child("weekYear"+(weekYear+1)).setValue(eo1);

    }
    public List<EmployeesOptions> getAllEmployeesScheduleForNextWeek(){

        DatabaseReference getSchedule = database.getReference("Schedules").child("Option");
        getSchedule.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//                employeeList.clear();
                for (DataSnapshot ds:dataSnapshot.getChildren()){
//                        Log.d("yisrael", "weekYear"+(weekYear+1));
                        eo2=ds.getValue(EmployeesOptions.class);
                        if (ds.getKey().equals("weekYear"+(weekYear+1)))
                        employeesOptionsList.add(eo2);
                }

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    return employeesOptionsList;
    }

    public Boolean sendEmployeeInfoFb( Employee e1){
       boolean flag=false;
        DatabaseReference employeeInfo = database.getReference("Employees").child("Employee");
        employeeInfo.child(e1.getUserName()).setValue(e1);
        flag=true;
        return flag;
    }
    public  List<Employee> getEmployeeList(){

        DatabaseReference getUserInfo=database.getReference("Employees");
        getUserInfo.addChildEventListener(new ChildEventListener() {
            @Override
            public  void onChildAdded (@NonNull DataSnapshot dataSnapshot, @Nullable String s)  {
                employeeList.clear();
                for (DataSnapshot ds:dataSnapshot.getChildren()){
                    e1=ds.getValue(Employee.class);
                    employeeList.add(e1);
                }

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                employeeList.clear();
                for (DataSnapshot ds:dataSnapshot.getChildren()){
                    e1=ds.getValue(Employee.class);
                    employeeList.add(e1);
                }
//                Log.d("yisrael", "yaa "+e1);
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

//        Log.d("yisrael", "its "+employeeList.isEmpty());
        return employeeList;
    }

    public  void sendToFbNextSchedule(String [][] s){
//        Log.d("yisrael", "fuck me");
        sbd=new ScheduleByDate(weekYear+1,s);
        DatabaseReference sendSchedule =
                database.getReference("Schedules").child("Schedule").child("weekYear"+(weekYear+1));
        sendSchedule.child("weekYear"+(weekYear+1)).setValue(sbd);

    }



    public List<ScheduleByDate> getAllSchedule(){

        DatabaseReference getSchedule = database.getReference("Schedules").child("Schedule");
        getSchedule.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                for (DataSnapshot ds:dataSnapshot.getChildren()){
                    sbd=ds.getValue(ScheduleByDate.class);
//                  if (ds.getKey().equals("weekYear"+(weekYear+1)))
                    scheduleByDateList.add(sbd);
//                    Log.d("yisrael", " 3");

                }

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return scheduleByDateList;
    }

}
