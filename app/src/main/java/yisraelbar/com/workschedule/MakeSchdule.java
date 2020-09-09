package yisraelbar.com.workschedule;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MakeSchdule extends AppCompatActivity {
    private EditText [][]edArray=new EditText[7][5];
    private String [][] strings=new String[7][5];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_schdule);

        edArray[0][0]=findViewById(R.id.ed00);
        edArray[0][1]=findViewById(R.id.ed01);
        edArray[0][2]=findViewById(R.id.ed02);
        edArray[0][3]=findViewById(R.id.ed03);
        edArray[0][4]=findViewById(R.id.ed04);
        //monday
        edArray[1][0]=findViewById(R.id.ed10);
        edArray[1][1]=findViewById(R.id.ed11);
        edArray[1][2]=findViewById(R.id.ed12);
        edArray[1][3]=findViewById(R.id.ed13);
        edArray[1][4]=findViewById(R.id.ed14);
        //tuesday
        edArray[2][0]=findViewById(R.id.ed20);
        edArray[2][1]=findViewById(R.id.ed21);
        edArray[2][2]=findViewById(R.id.ed22);
        edArray[2][3]=findViewById(R.id.ed23);
        edArray[2][4]=findViewById(R.id.ed24);
        //Wednesday
        edArray[3][0]=findViewById(R.id.ed30);
        edArray[3][1]=findViewById(R.id.ed31);
        edArray[3][2]=findViewById(R.id.ed32);
        edArray[3][3]=findViewById(R.id.ed33);
        edArray[3][4]=findViewById(R.id.ed34);
        //Thursday
        edArray[4][0]=findViewById(R.id.ed40);
        edArray[4][1]=findViewById(R.id.ed41);
        edArray[4][2]=findViewById(R.id.ed42);
        edArray[4][3]=findViewById(R.id.ed43);
        edArray[4][4]=findViewById(R.id.ed44);
        //Friday
        edArray[5][0]=findViewById(R.id.ed50);
        edArray[5][1]=findViewById(R.id.ed51);
        edArray[5][2]=findViewById(R.id.ed52);
        edArray[5][3]=findViewById(R.id.ed53);
        edArray[5][4]=findViewById(R.id.ed54);
        //Saturday
        edArray[6][0]=findViewById(R.id.ed60);
        edArray[6][1]=findViewById(R.id.ed61);
        edArray[6][2]=findViewById(R.id.ed62);
        edArray[6][3]=findViewById(R.id.ed63);
        edArray[6][4]=findViewById(R.id.ed64);

        findViewById(R.id.btMakeSchedule).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i=0; i<7;i++) {
                    for (int j = 0; j < 5; j++) {
                        if (edArray[i][j].getText().toString().equals("")){
                            strings[i][j]="";
                        }else strings[i][j]=edArray[i][j].getText().toString();
                    }
                }
                FireBase fb1=new FireBase();
                fb1.sendToFbNextSchedule(strings);
            }
        });

        findViewById(R.id.btGenerate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generate();
            }
        });

    }
    private void generate(){
        Schedule s1 = new Schedule();
        String[][] allOptions = new String[7][5];
        int to=3;
        try {
            for (EmployeesOptions eo : MainActivity.eo1) {
                boolean[][] eo2 = s1.convertTheListIntoArray1(eo.getSchedule());
                for (int i = 0; i < 7; i++) {
                    for (int j = 0; j < to; j++) {
                        if (eo2[i][j] == true && allOptions[i][j]==null) {
                            for (Employee se1:ShowEmployees.employeeList ){
                                if (se1.getUserName().equals(eo.getUserName()))
                                    if (j>=1 && allOptions[i][j-1]!=null && allOptions[i][j-1].trim().equals(se1.getName())){
                                    }else if (i>=1 && allOptions[i-1][3]!=null && allOptions[i-1][3].trim().equals(se1.getName())){
                                    }else
                                    allOptions[i][j]=se1.getName()+" ";
                            }
                            if (j==2){
                                boolean flag=true;
                                for (int k=0 ;k<3 ;k++){
                                  if( allOptions[i][k]==null|| allOptions[i][k].equals("")){
                                      flag=false;
                                  }
                                }
                                if (!flag)
                                    to=5;
                            }
                        }
                    }
                    to=3;
                }
            }
            for (int i = 0; i < 7; i++) {
                for (int j = 0; j < 5; j++) {
                    if (allOptions[i][j] != null)
                        edArray[i][j].setText(allOptions[i][j]);
                }
            }
        }catch (Exception e){
            Toast.makeText(MakeSchdule.this,""+e,Toast.LENGTH_LONG).show();

        }
    }


}
