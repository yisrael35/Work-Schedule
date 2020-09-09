package yisraelbar.com.workschedule;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterEmployee extends AppCompatActivity {
    private String Name,UserName,UserPass,Facility,cellphone,Address;
    private  Button btReg;
    private int employeeNumber;
    private  EditText edName,edUserName,edUserPass,edFacilty,edCellphone,edEmployeeNumber,edAddress;
    private FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
    private ProgressDialog progressDialog;
   private FireBase fb1;
    private Employee e1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_employee);
        edName=findViewById(R.id.edName);
        edUserName=findViewById(R.id.edUserName);
        edUserPass=findViewById(R.id.edUserPass);
        edCellphone=findViewById(R.id.edCellphone);
        edFacilty=findViewById(R.id.edFacility);
        edEmployeeNumber=findViewById(R.id.edEmployeeNumber);
        edAddress=findViewById(R.id.edAddress);
        btReg=findViewById(R.id.btregister);
        progressDialog = new ProgressDialog(this);
        fb1=new FireBase();
        findViewById(R.id.btregister).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            try {
            if (edName.getText().toString().equals("") || edUserName.getText().toString().equals("")
                    || edUserPass.getText().toString().equals("") ||
                    edFacilty.getText().toString().equals("")||edCellphone.getText().toString().equals("")
                    ||edEmployeeNumber.getText().toString().equals("")||edAddress.getText().toString().equals("") ){
               btReg.setText("Fill the blank");
            }else{
                Name=edName.getText().toString();
                UserName=edUserName.getText().toString();
                UserPass=edUserPass.getText().toString();
                Facility=edFacilty.getText().toString();
                cellphone=edCellphone.getText().toString();
                employeeNumber=Integer.parseInt(edEmployeeNumber.getText().toString());
                Address=edAddress.getText().toString();
                registerUser(UserName,UserPass);
                e1=new Employee(UserName,UserPass,Name,cellphone,employeeNumber,Facility,Address);

//                fb1.sendEmployeeInfoFb(e1);

            }
            }catch (Exception e) {
                btReg.setText("Fill the blank"+ e);
            }
            }
        });
    }

    private void registerUser(String userName,String userPass){

        userName+="@gmail.com";

        progressDialog.setMessage("Registering Please Wait...");
        progressDialog.show();
        //creating a new user
        firebaseAuth.createUserWithEmailAndPassword(userName, userPass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //checking if success
                        if(task.isSuccessful()){
                            //display some message here
                            Toast.makeText(RegisterEmployee.this,"Successfully registered",Toast.LENGTH_LONG).show();
                            if (fb1.sendEmployeeInfoFb(e1)){
                                btReg.setText("User added");
                            }
                        }else{
                            //display some message here
                            Toast.makeText(RegisterEmployee.this,"Registration Error",Toast.LENGTH_LONG).show();

                        }
                        progressDialog.dismiss();
                    }
                });

    }
}
