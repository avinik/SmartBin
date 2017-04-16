package com.mridul.smartbin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

public class ForgotPassword extends AppCompatActivity {

    EditText resetMail ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        Toolbar tbar = (Toolbar)findViewById(R.id.tbar_act_forgot_password_admin);
        tbar.setTitle("Reset Password");
        tbar.setTitleTextColor(getResources().getColor(R.color.WHITE));
        setSupportActionBar(tbar);



        resetMail = (EditText)findViewById(R.id.editText_resetPass);
    }


    public void resetPassword(View view){
        String s_email = resetMail.getText().toString();

        String type="resetpassword";

        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, s_email);
    }
}
