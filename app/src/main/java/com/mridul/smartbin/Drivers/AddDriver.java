package com.mridul.smartbin.Drivers;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.mridul.smartbin.R;

public class AddDriver extends AppCompatActivity {

    private EditText et_uName;
    private EditText et_name;
    private EditText et_mob;
    private EditText et_vehicle;
    private EditText et_pass;
    private EditText et_rePass;

    private Button btn_addDriver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_driver);


        Toolbar tbar = (Toolbar)findViewById(R.id.tbar_act_add_driver);
        tbar.setTitle("Add Driver");
        tbar.setTitleTextColor(getResources().getColor(R.color.WHITE));
        setSupportActionBar(tbar);



        et_uName = (EditText)findViewById(R.id.et_driver_user_name);
        et_name = (EditText)findViewById(R.id.et_driver_name);
        et_mob = (EditText)findViewById(R.id.et_driver_mobNo);
        et_vehicle = (EditText)findViewById(R.id.et_driver_vehicleNo);
        et_pass = (EditText)findViewById(R.id.et_driver_password);
        et_rePass = (EditText)findViewById(R.id.et_driver_retype_password);

        btn_addDriver = (Button)findViewById(R.id.btn_insert_driver);
        btn_addDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkingEmpty(et_uName.getText().toString(), et_name.getText().toString(), et_mob.getText().toString(), et_vehicle.getText().toString(), et_pass.getText().toString(), et_rePass.getText().toString()) == 1) {
                    if (et_rePass.getText().toString().equals(et_pass.getText().toString())) {
                        if (et_rePass.getText().length() < 6) {

                            et_rePass.getText().clear();
                            et_pass.getText().clear();
                            AlertDialog.Builder alert = new AlertDialog.Builder(AddDriver.this);
                            alert.setTitle("Password too Short...");
                            alert.setMessage("Password must be atleast six characters long.");
                            alert.setPositiveButton("OK", null);
                            alert.show();

                        } else {

                            String type = "addDriver";
                            BackgroundWorkerDriver backgroundWorkerDriver = new BackgroundWorkerDriver(getApplicationContext());
                            backgroundWorkerDriver.execute(type, et_uName.getText().toString(), et_name.getText().toString(), et_mob.getText().toString(), et_vehicle.getText().toString(), et_pass.getText().toString());

                            et_rePass.getText().clear();
                            et_pass.getText().clear();
                            et_uName.getText().clear();
                            et_mob.getText().clear();
                            et_vehicle.getText().clear();
                            et_name.getText().clear();
                        }
                    } else {
                        et_rePass.getText().clear();
                        et_pass.getText().clear();
                        AlertDialog.Builder alert = new AlertDialog.Builder(AddDriver.this);
                        alert.setTitle("Notification...");
                        alert.setMessage("Your entered Passwords do not matched.\nPlease Re-Enter passwords and try again.");
                        alert.setPositiveButton("OK", null);
                        alert.show();
                    }
                }
                else{
                    AlertDialog.Builder alert = new AlertDialog.Builder(AddDriver.this);
                    alert.setTitle("Error...");
                    alert.setMessage("All Fields are Required. Please fill all the Fields.");
                    alert.setPositiveButton("OK", null);
                    alert.show();
                }
            }
        });


    }

    private int checkingEmpty(String s1, String s2, String s3, String s4, String s5, String s6){
        if(s1.isEmpty() || s2.isEmpty() || s3.isEmpty() || s4.isEmpty() || s5.isEmpty() || s6.isEmpty()){
             return -1;
        }
        else return 1;
    }

}
