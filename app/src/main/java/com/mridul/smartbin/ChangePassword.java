package com.mridul.smartbin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ChangePassword extends AppCompatActivity {

    private EditText et_old;
    private EditText et_new;
    private EditText et_confNew;
    private Button btn_changePasswd;

    private String EMAIL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        Toolbar tbar = (Toolbar)findViewById(R.id.tbar_act_change_password_admin);
        tbar.setTitle("Change Password");
        tbar.setTitleTextColor(getResources().getColor(R.color.WHITE));
        setSupportActionBar(tbar);

        Intent intent = getIntent();
        EMAIL = intent.getStringExtra("email");


        et_old = (EditText)findViewById(R.id.et_oldPasswd);
        et_new = (EditText)findViewById(R.id.et_newPasswd);
        et_confNew = (EditText)findViewById(R.id.et_confNewPasswd);
        btn_changePasswd = (Button)findViewById(R.id.btn_changePasswd);

        btn_changePasswd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changePassword(et_old.getText().toString(), et_new.getText().toString(), et_confNew.getText().toString() );
            }
        });
    }

    private void changePassword(String et_o, String et_n, String et_cN){
        if(et_o.isEmpty() || et_n.isEmpty() || et_cN.isEmpty()){
            Toast.makeText(this, "All fields are Required !", Toast.LENGTH_LONG).show();
        }else{
            if(et_n.equals(et_cN)){
                if(et_n.length()>=8 ){
                    // necessary work here...
                    String type = "changePassword";
                    BackgroundWorker backgroundWorker = new BackgroundWorker(this);
                    backgroundWorker.execute(type, EMAIL, et_o, et_n);
                }else{
                    Toast.makeText(this, "NEW Password length must be more than 7", Toast.LENGTH_LONG).show();
                }
            }else{
                Toast.makeText(this, "NEW Passwords do not MATCH.", Toast.LENGTH_LONG).show();
            }
        }
    }


}
