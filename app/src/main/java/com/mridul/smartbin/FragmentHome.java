package com.mridul.smartbin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import static com.mridul.smartbin.BackgroundWorkerLoginActivity.CURRENT_USER_EMAIL;


public class FragmentHome extends Fragment {

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        AfterLogin1.toolbar.setTitle("SmartBin Home");


        //Log.d("current email", ""+email_user);
        String type="accountInfo";
        BackgroundWorkerAccountInfo backgroundWorker = new BackgroundWorkerAccountInfo(getContext());
        backgroundWorker.execute(type, CURRENT_USER_EMAIL);

        return v;
    }
}
