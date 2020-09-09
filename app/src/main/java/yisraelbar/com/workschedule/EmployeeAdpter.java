package yisraelbar.com.workschedule;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class EmployeeAdpter extends ArrayAdapter <Employee> {
   private TextView tvEmployeeName,tvEmployeeFacility;
   private Button btEmployeeDeatels;
  private   List<Employee> myObjects;
   private View myView;
   private Employee e1;
  private   ShowEmployees se1=new ShowEmployees();
    public EmployeeAdpter(@NonNull Context context, @NonNull List<Employee> emp) {
        super(context, 0, emp);
        this.myObjects = emp;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        myView=LayoutInflater.from(getContext()).inflate(R.layout.adpter_employee,parent,false);
       try {
            e1=getItem(position);
       }catch (Exception e){

       }


        tvEmployeeName=myView.findViewById(R.id.tvEmployeeName);
        tvEmployeeFacility=myView.findViewById(R.id.tvEmployeeFacility);
        btEmployeeDeatels= myView.findViewById(R.id.btEmployeeDeatels);

//        tvName.setTextColor(Color.BLACK);
//        tvCompany.setTextColor(Color.BLACK);

        try {
            tvEmployeeName.setText("name: "+e1.getName());
            tvEmployeeFacility.setText("Facility name: "+e1.getFacility());
            btEmployeeDeatels.setText(""+e1.getEmployeeNumber());
        }catch (Exception e){

        }




        myView.findViewById(R.id.btEmployeeDeatels).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                try {
                    Button b1 = (Button)v;
                    int EmployeeNumber = Integer.parseInt(b1.getText().toString());
                    for(Employee e2 : myObjects)
                    {
                        if(e2.getEmployeeNumber()==EmployeeNumber) {
                            ShowEmployees.e1 = e2;

                        }
                    }
                    se1.showEmployee();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        return myView;
    }

}
