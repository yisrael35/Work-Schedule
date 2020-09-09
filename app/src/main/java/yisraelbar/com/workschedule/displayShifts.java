package yisraelbar.com.workschedule;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.text.DateFormat;
import java.util.Calendar;

public class displayShifts extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

   private TextView [][] tvArray=new TextView[7][5];
   private TextView tvdate;
   private TableLayout table;
    private Calendar c;
   private int weekYear;
   private  sql s1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_shifts);
        table=findViewById(R.id.table);
        c = Calendar.getInstance();
        s1=new sql(getApplicationContext(),"yisrael",null,1);

        //sunday
        tvArray[0][0]=findViewById(R.id.tvSundayMorning);
        tvArray[0][1]=findViewById(R.id.tvSundayNoon);
        tvArray[0][2]=findViewById(R.id.tvSundayNight);
        tvArray[0][3]=findViewById(R.id.tvSunday7_19);
        tvArray[0][4]=findViewById(R.id.tvSunday19_7);
        //monday
        tvArray[1][0]=findViewById(R.id.tvMondayMorning);
        tvArray[1][1]=findViewById(R.id.tvMondayNoon);
        tvArray[1][2]=findViewById(R.id.tvMondayNight);
        tvArray[1][3]=findViewById(R.id.tvMonday7_19);
        tvArray[1][4]=findViewById(R.id.tvMonday19_7);
        //tuesday
        tvArray[2][0]=findViewById(R.id.tvTuesdayMorning);
        tvArray[2][1]=findViewById(R.id.tvTuesdayNoon);
        tvArray[2][2]=findViewById(R.id.tvTuesdayNight);
        tvArray[2][3]=findViewById(R.id.tvTuesday7_19);
        tvArray[2][4]=findViewById(R.id.tvTuesday19_7);
        //Wednesday
        tvArray[3][0]=findViewById(R.id.tvWednesdayMorning);
        tvArray[3][1]=findViewById(R.id.tvWednesdayNoon);
        tvArray[3][2]=findViewById(R.id.tvWednesdayNight);
        tvArray[3][3]=findViewById(R.id.tvWednesday7_19);
        tvArray[3][4]=findViewById(R.id.tvWednesday19_7);
        //Thursday
        tvArray[4][0]=findViewById(R.id.tvThursdayMorning);
        tvArray[4][1]=findViewById(R.id.tvThursdayNoon);
        tvArray[4][2]=findViewById(R.id.tvThursdayNight);
        tvArray[4][3]=findViewById(R.id.tvThursday7_19);
        tvArray[4][4]=findViewById(R.id.tvThursday19_7);
        //Friday
        tvArray[5][0]=findViewById(R.id.tvFridayMorning);
        tvArray[5][1]=findViewById(R.id.tvFridayNoon);
        tvArray[5][2]=findViewById(R.id.tvFridayNight);
        tvArray[5][3]=findViewById(R.id.tvFriday7_19);
        tvArray[5][4]=findViewById(R.id.tvFriday19_7);
        //Saturday
        tvArray[6][0]=findViewById(R.id.tvSaturdayMorning);
        tvArray[6][1]=findViewById(R.id.tvSaturdayNoon);
        tvArray[6][2]=findViewById(R.id.tvSaturdayNight);
        tvArray[6][3]=findViewById(R.id.tvSaturday7_19);
        tvArray[6][4]=findViewById(R.id.tvSaturday19_7);
        tvdate=findViewById(R.id.tvdate);

        String currentDateString =
                DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
         weekYear=c.get(Calendar.WEEK_OF_YEAR);
        displayOnScreen(s1.search(weekYear),currentDateString);

    }
    private void displayOnScreen (String [][] s, String date){
       tvdate.setText(date);
       //reset
        table.setColumnCollapsed(4,false);
        table.setColumnCollapsed(5,false);
//
        for (int i=0; i<7;i++) {
            for (int j = 0; j < 5; j++) {
                tvArray[i][j].setBackgroundColor(Color.WHITE);
                tvArray[i][j].setText(s[i][j]);
               try {
                   if ( tvArray[i][j].getText().toString().trim().equals(MainActivity.userName)){
                       tvArray[i][j].setBackgroundColor(Color.GREEN);
                   }

                   if (s[i][j]==null || s[i][j].equals("") ){
                       tvArray[i][j].setBackgroundColor(Color.RED);
                   }
               }catch (Exception e){
                   Toast.makeText(this, ""+e,Toast.LENGTH_SHORT).show();
               }
            }
        }
        boolean flag=true;
        int j=3;
        for (int i=0; i<7; i++){
                if (tvArray[i][j].getText().toString().equals("")){
                }else flag=false;
        }
        if (flag){
            table.setColumnCollapsed(4,true);
        }
         flag=true;
         j=4;
        for (int i=0; i<7; i++){
            if (tvArray[i][j].getText().toString().equals("")){
            }else flag=false;
        }
        if (flag){
            table.setColumnCollapsed(5,true);
        }

         findViewById(R.id.btChooseDate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });

    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString =
                DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        tvdate.setText(currentDateString);
         weekYear=c.get(Calendar.WEEK_OF_YEAR);
        displayOnScreen(s1.search(weekYear),currentDateString);

     }
    }

