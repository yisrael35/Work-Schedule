package yisraelbar.com.workschedule;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class EmployeeMenu extends AppCompatActivity {
    static List<ScheduleByDate> scheduleByDatesList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_menu);
        Calendar c = Calendar.getInstance();
        int weekYear=c.get(Calendar.WEEK_OF_YEAR);

        sql s1=new sql(getApplicationContext(),"yisrael", null,1);
        //just for check
        for (ScheduleByDate sb:scheduleByDatesList ){
            try {
                if (sb.getWeekYear()==weekYear){
                    s1.deleteSchedule(weekYear);
               }
                if (sb.getWeekYear()==weekYear+1){
                    s1.deleteSchedule(weekYear+1);
                }
                s1.addSchedule(sb);
                Log.d("yisrael", "seccsess");
            }catch (Exception e){
                Log.d("yisrael", "fuck"+e);
            }

        }



    }

    public void mainClick(View v){
        switch (v.getId()){
            case R.id.btEmployeeInfo:
                Intent intent0= new Intent(getApplicationContext(),ShowEmployees.class);
                startActivity(intent0);
                break;
            case R.id.btSendSchedule:
                Intent intent= new Intent(getApplicationContext(),optionsForNextWeek.class);
                startActivity(intent);
                break;
            case R.id.btShowSchedule:
                Intent intent1= new Intent(getApplicationContext(),displayShifts.class);
                startActivity(intent1);
                break;
            case R.id.btmakeSchedule:
                Intent intent2= new Intent(getApplicationContext(),MakeSchdule.class);
                startActivity(intent2);

                break;


            default:
        }
    }

}
