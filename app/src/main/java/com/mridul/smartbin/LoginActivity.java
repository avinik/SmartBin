package com.mridul.smartbin;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * This class is for opening first activity just after you opens the app.
 * it will open registration activity if you want to register or Forgot Password if you forgot your password.
 */

public class LoginActivity extends AppCompatActivity {

    EditText email, password ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = (EditText)findViewById(R.id.login_email);
        password = (EditText)findViewById(R.id.login_password);
    }

    public void activity_afterlogin(View view){
        String s_email = email.getText().toString();
        String s_password = password.getText().toString();
        String type="login";

        BackgroundWorker backgroundWorker = new BackgroundWorker(this);
        backgroundWorker.execute(type, s_email, s_password);
    }

    public void registerLayout(View view){
        startActivity(new Intent(this,Register.class));
    }

    public void forgotPasswordLayout(View view){
        startActivity(new Intent(this,ForgotPassword.class));
    }



}
