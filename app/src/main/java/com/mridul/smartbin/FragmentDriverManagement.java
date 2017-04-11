package com.mridul.smartbin;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.mridul.smartbin.Drivers.AddDriver;
import com.mridul.smartbin.Drivers.BackgroundWorkerDriver;
import com.mridul.smartbin.Drivers.ViewDrivers;

public class FragmentDriverManagement extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_driver_management, container, false);
        AfterLogin1.toolbar.setTitle("Bin Collectors");


        Button btn_add_driver = (Button)view.findViewById(R.id.btn_add_driver);
        btn_add_driver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), AddDriver.class));

            }
        });

        Button btn_view_driver = (Button)view.findViewById(R.id.btn_view_driver);
        btn_view_driver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String type = "viewDriver";
                BackgroundWorkerDriver backgroundWorkerDriver = new BackgroundWorkerDriver(getContext());
                backgroundWorkerDriver.execute(type);
            }
        });


        return view;
    }
}
