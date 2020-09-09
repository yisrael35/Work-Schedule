package yisraelbar.com.workschedule;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class ShowEmployees extends AppCompatActivity {
      private static   TextView tvName,tvUserName,tvCellphone,tvEmployeeNumber,tvFacility,tvAddress;
      static List<Employee> employeeList=new ArrayList<Employee>();
//      private List<Employee> employeeList1=new ArrayList<Employee>();

      private FireBase fb1=new FireBase();
      static Employee e1;
//    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_employees);


      /*
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading Please Wait...");
        progressDialog.show();

        employeeList1=fb1.getEmployeeList();

        progressDialog.dismiss();
        */
//        if (employeeList.isEmpty())
//            Toast.makeText(this, "is empty",Toast.LENGTH_SHORT).show();
        tvName=findViewById(R.id.tvName);
        tvUserName=findViewById(R.id.tvUserName);
        tvCellphone=findViewById(R.id.tvCellphone);
        tvEmployeeNumber=findViewById(R.id.tvEmployeeNumber);
        tvFacility=findViewById(R.id.tvFacility);
        tvAddress=findViewById(R.id.tvAddress);
        ListView lv=findViewById(R.id.lvEmployee);
        lv.setAdapter(new EmployeeAdpter(getApplicationContext(),employeeList));


    }

    public  static  void showEmployee() {

        tvName.setText("Name: "+e1.getName());
        tvUserName.setText("User Name: "+e1.getUserName());
        tvCellphone.setText("Cellphone: "+e1.getCellphone());
        tvEmployeeNumber.setText("Employee Number: "+e1.getEmployeeNumber());
        tvFacility.setText("Facility: "+e1.getFacility());
        tvAddress.setText("Address: "+e1.getAddress());
    }




}
