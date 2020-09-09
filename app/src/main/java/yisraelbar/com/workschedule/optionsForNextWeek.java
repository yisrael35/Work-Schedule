package yisraelbar.com.workschedule;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Calendar;

public class optionsForNextWeek extends AppCompatActivity {
   private CheckBox cbSundayMorning,cbSundayNoon,cbSundayNight,cbSunday7_19,cbSunday19_7,
            cbMondayMorning,cbMondayNoon,cbMondayNight,cbMonday7_19,cbMonday19_7,
            cbTuesdayMorning,cbTuesdayNoon,cbTuesdayNight,cbTuesday7_19,cbTuesday19_7,
            cbWednesdayMorning,cbWednesdayNoon,cbWednesdayNight,cbWednesday7_19,cbWednesday19_7,
            cbThursdayMorning,cbThursdayNoon,cbThursdayNight,cbThursday7_19,cbThursday19_7,
            cbFridayMorning,cbFridayNoon,cbFridayNight,cbFriday7_19,cbFriday19_7,
            cbSaturdayMorning,cbSaturdayNoon,cbSaturdayNight,cbSaturday7_19,cbSaturday19_7;
    private TextView tvScheduleSent;
    private Schedule s1;
    private FirebaseAuth firebaseAuth;
    private String userMail="00";
    private FireBase fb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options_for_next_week);
        firebaseAuth=FirebaseAuth.getInstance();
        tvScheduleSent=findViewById(R.id.tvScheduleSent);
        //sunday
        cbSundayMorning=findViewById(R.id.cbSundayMorning);
        cbSundayNoon=findViewById(R.id.cbSundayNoon);
        cbSundayNight=findViewById(R.id.cbSundayNight);
        cbSunday7_19=findViewById(R.id.cbSunday7_19);
        cbSunday19_7=findViewById(R.id.cbSunday19_7);
        //monday
        cbMondayMorning=findViewById(R.id.cbMondayMorning);
        cbMondayNoon=findViewById(R.id.cbMondayNoon);
        cbMondayNight=findViewById(R.id.cbMondayNight);
        cbMonday7_19=findViewById(R.id.cbMonday7_19);
        cbMonday19_7=findViewById(R.id.cbMonday19_7);
        //tuesday
        cbTuesdayMorning=findViewById(R.id.cbTuesdayMorning);
        cbTuesdayNoon=findViewById(R.id.cbTuesdayNoon);
        cbTuesdayNight=findViewById(R.id.cbTuesdayNight);
        cbTuesday7_19=findViewById(R.id.cbTuesday7_19);
        cbTuesday19_7=findViewById(R.id.cbTuesday19_7);
        //Wednesday
        cbWednesdayMorning=findViewById(R.id.cbWednesdayMorning);
        cbWednesdayNoon=findViewById(R.id.cbWednesdayNoon);
        cbWednesdayNight=findViewById(R.id.cbWednesdayNight);
        cbWednesday7_19=findViewById(R.id.cbWednesday7_19);
        cbWednesday19_7=findViewById(R.id.cbWednesday19_7);
        //Thursday
        cbThursdayMorning=findViewById(R.id.cbThursdayMorning);
        cbThursdayNoon=findViewById(R.id.cbThursdayNoon);
        cbThursdayNight=findViewById(R.id.cbThursdayNight);
        cbThursday7_19=findViewById(R.id.cbThursday7_19);
        cbThursday19_7=findViewById(R.id.cbThursday19_7);
        //Friday
        cbFridayMorning=findViewById(R.id.cbFridayMorning);
        cbFridayNoon=findViewById(R.id.cbFridayNoon);
        cbFridayNight=findViewById(R.id.cbFridayNight);
        cbFriday7_19=findViewById(R.id.cbFriday7_19);
        cbFriday19_7=findViewById(R.id.cbFriday19_7);
        //Saturday
        cbSaturdayMorning=findViewById(R.id.cbSaturdayMorning);
        cbSaturdayNoon=findViewById(R.id.cbSaturdayNoon);
        cbSaturdayNight=findViewById(R.id.cbSaturdayNight);
        cbSaturday7_19=findViewById(R.id.cbSaturday7_19);
        cbSaturday19_7=findViewById(R.id.cbSaturday19_7);
         fb=new FireBase();
        s1=new Schedule();
        if (firebaseAuth.getCurrentUser()!=null){
          userMail=firebaseAuth.getCurrentUser().getEmail();
          userMail=userMail.substring(0,userMail.length()-10);
        }
         findViewById(R.id.btsend).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fillTheSchedule();
                try{
//                    Calendar c=Calendar.getInstance();
//                    int weekYear=c.get(Calendar.WEEK_OF_YEAR);

                    EmployeesOptions eo1=new EmployeesOptions(userMail,s1.createListOfOption());
                    fb.sendEmployeeScheduleInfoFb(eo1);
                    tvScheduleSent.setText("האופציות לשבוע הבא שנשלחו: "+ "\n"+s1.toString());
                }catch (Exception e){
                    Log.d("yisraels", "opt "+e);
                }
            }
        });
        findViewById(R.id.btSendToWhatsApp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              //check what boxes been checked
              fillTheSchedule();
                String temp=s1.toString();
               //send the text to share
                Intent intent=new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT, temp);
                intent.setType("text/plain");
                Intent chooser=Intent.createChooser(intent,"share");
                if (intent.resolveActivity(getPackageManager())!=null){
                    startActivity(chooser);
                }
            }
        });
    }
    //check what boxes been checked
    private void fillTheSchedule(){
        //sunday
        if (cbSundayMorning.isChecked()){
            s1.setSchedule(0,0,true);
        }
        if (cbSundayNoon.isChecked()){
            s1.setSchedule(0,1,true);
        }
        if (cbSundayNight.isChecked()){
            s1.setSchedule(0,2,true);
        }
        if (cbSunday7_19.isChecked()){
            s1.setSchedule(0,3,true);
        }
        if (cbSunday19_7.isChecked()){
            s1.setSchedule(0,4,true);
        }
        //monday
        if (cbMondayMorning.isChecked()){
            s1.setSchedule(1,0,true);
        }
        if (cbMondayNoon.isChecked()){
            s1.setSchedule(1,1,true);
        }
        if (cbMondayNight.isChecked()){
            s1.setSchedule(1,2,true);
        }
        if (cbMonday7_19.isChecked()){
            s1.setSchedule(1,3,true);
        }
        if (cbMonday19_7.isChecked()){
            s1.setSchedule(1,4,true);
        }
        //Tuesday
        if (cbTuesdayMorning.isChecked()){
            s1.setSchedule(2,0,true);
        }
        if (cbTuesdayNoon.isChecked()){
            s1.setSchedule(2,1,true);
        }
        if (cbTuesdayNight.isChecked()){
            s1.setSchedule(2,2,true);
        }
        if (cbTuesday7_19.isChecked()){
            s1.setSchedule(2,3,true);
        }
        if (cbTuesday19_7.isChecked()){
            s1.setSchedule(2,4,true);
        }

        //Wednesday
        if (cbWednesdayMorning.isChecked()){
            s1.setSchedule(3,0,true);
        }
        if (cbWednesdayNoon.isChecked()){
            s1.setSchedule(3,1,true);
        }
        if (cbWednesdayNight.isChecked()){
            s1.setSchedule(3,2,true);
        }
        if (cbWednesday7_19.isChecked()){
            s1.setSchedule(3,3,true);
        }
        if (cbWednesday19_7.isChecked()){
            s1.setSchedule(3,4,true);
        }
        //Thursday
        if (cbThursdayMorning.isChecked()){
            s1.setSchedule(4,0,true);
        }
        if (cbThursdayNoon.isChecked()){
            s1.setSchedule(4,1,true);
        }
        if (cbThursdayNight.isChecked()){
            s1.setSchedule(4,2,true);
        }
        if (cbThursday7_19.isChecked()){
            s1.setSchedule(4,3,true);
        }
        if (cbThursday19_7.isChecked()){
            s1.setSchedule(4,4,true);
        }
        //Friday
        if (cbFridayMorning.isChecked()){
            s1.setSchedule(5,0,true);
        }
        if (cbFridayNoon.isChecked()){
            s1.setSchedule(5,1,true);
        }
        if (cbFridayNight.isChecked()){
            s1.setSchedule(5,2,true);
        }
        if (cbFriday7_19.isChecked()){
            s1.setSchedule(5,3,true);
        }
        if (cbFriday19_7.isChecked()){
            s1.setSchedule(5,4,true);
        }
        //Saturday
        if (cbSaturdayMorning.isChecked()){
            s1.setSchedule(6,0,true);
        }
        if (cbSaturdayNoon.isChecked()){
            s1.setSchedule(6,1,true);
        }
        if (cbSaturdayNight.isChecked()){
            s1.setSchedule(6,2,true);
        }
        if (cbSaturday7_19.isChecked()){
            s1.setSchedule(6,3,true);
        }
        if (cbSaturday19_7.isChecked()){
            s1.setSchedule(6,4,true);
        }
    }

}
