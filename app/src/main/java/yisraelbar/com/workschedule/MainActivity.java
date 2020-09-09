package yisraelbar.com.workschedule;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


   private EditText edUser,edpass;
   private SharedPreferences myShare;
   private static  final  String USER_NAME="USER_NAME";
    private static  final  String USER_PASSWORD="USER_PASSWORD";
    public static String userName="";
    private  String userPass="";
    private FirebaseAuth mAuth;
   private FireBase fb1=new FireBase();
 private    FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
  static   List<EmployeesOptions> eo1=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edUser = findViewById(R.id.edUserName);
        edpass = findViewById((R.id.edPassword));
        mAuth = FirebaseAuth.getInstance();
        ///need to find another way
        ShowEmployees.employeeList=  fb1.getEmployeeList();
        EmployeeMenu.scheduleByDatesList=fb1.getAllSchedule();
        eo1=fb1.getAllEmployeesScheduleForNextWeek();
        firebaseAuth=FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        try {
//            Log.d("yisrael", "fuck 0");
            fb1.getAllSchedule();
//            Log.d("yisrael", "fuck end");
            myShare = getSharedPreferences("first", Context.MODE_PRIVATE);
            userName = myShare.getString(USER_NAME, null);
            userPass = myShare.getString(USER_PASSWORD, null);
            edUser.setText(userName);
            edpass.setText(userPass);

        } catch (Exception e) {
            Toast.makeText(this, "" + e, Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences.Editor edit1 = myShare.edit();
        edit1.putString(USER_NAME,userName);
        edit1.putString(USER_PASSWORD,userPass);
        edit1.commit();
    }

    public  void mainClick (View v){
    if (v.getId()==R.id.btLogin){

        try {
            if (!edUser.getText().toString().equals("") && !edpass.getText().toString().equals("")) {
                userName = edUser.getText().toString();
                userPass = edpass.getText().toString();
                Login(userName, userPass);
            }
        }catch (Exception e) {
            Toast.makeText(MainActivity.this,""+e,Toast.LENGTH_LONG).show();

        }

    }
       else if (v.getId()==R.id.btRegister){
        Intent intent= new Intent(getApplicationContext(),RegisterEmployee.class);
        startActivity(intent);

    }

    }

    private void Login(String userName, String userPass){
        userName+="@gmail.com";
        firebaseAuth.signInWithEmailAndPassword(userName,userPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                //checking if success
                if(task.isSuccessful()){
                    //display some message here
                    finish();
                    progressDialog.dismiss();
                    startActivity(new Intent(getApplicationContext(),EmployeeMenu.class));
                }else{
                    //display some message here
                    Toast.makeText(MainActivity.this,"sign Error",Toast.LENGTH_LONG).show();
                    progressDialog.dismiss();
                }

            }
        });

    }

//    public void sendToSql( ScheduleByDate sbd){
//        sql s1=new sql(getApplicationContext(),"yisrael", null,1);
//        s1.addSchedule(sbd);
//    }

}
