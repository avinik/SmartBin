package com.mridul.smartbin;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;


public class FragmentNotifications extends Fragment {

    ImageView imageView ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notifications, container, false);

        imageView = (ImageView)view.findViewById(R.id.iv_notification_bell_image);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // call background worker to parse notifications here...
                Toast.makeText(getContext(),"Yes! I am clicked",Toast.LENGTH_LONG).show();
                startActivity(new Intent(getContext(),ShowNotifications.class));
            }
        });

        return view;
    }
}
