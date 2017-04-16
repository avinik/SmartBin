package com.mridul.smartbin.Drivers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.mridul.smartbin.R;

public class RemoveDriver extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_driver);

        Toolbar tbar = (Toolbar)findViewById(R.id.tbar_act_remove_driver);
        tbar.setTitle("Remove Driver");
        tbar.setTitleTextColor(getResources().getColor(R.color.WHITE));
        setSupportActionBar(tbar);


        final EditText et_rmDriver = (EditText)findViewById(R.id.et_driver_remove_uName);

        Button btn_rmDriver = (Button) findViewById(R.id.btn_driver_remove_finally);
        btn_rmDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String removeDriver = et_rmDriver.getText().toString();

                String type2 = "removeDriver";
                BackgroundWorkerDriverRemove bg1 = new BackgroundWorkerDriverRemove(RemoveDriver.this);
                bg1.execute(type2, removeDriver);

                et_rmDriver.getText().clear();
            }
        });


    }
}
