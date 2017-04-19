package com.mridul.smartbin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import static com.mridul.smartbin.BackgroundWorkerLoginActivity.CURRENT_USER_EMAIL;


public class FragmentHome extends Fragment implements  View.OnClickListener{

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    ImageView filled_bins ;
    ImageView driver_mgmt ;
    ImageView path_maker ;
    ImageView notification ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        AfterLogin1.toolbar.setTitle("SmartBin Home");


        //Log.d("current email", ""+email_user);
        String type="accountInfo";
        BackgroundWorkerAccountInfo backgroundWorker = new BackgroundWorkerAccountInfo(getContext());
        backgroundWorker.execute(type, CURRENT_USER_EMAIL);

        filled_bins = (ImageView) v.findViewById(R.id.filled_bins_home);
        driver_mgmt = (ImageView) v.findViewById(R.id.driver_management_home);
        path_maker = (ImageView) v.findViewById(R.id.path_maker_home);
        notification = (ImageView) v.findViewById(R.id.notifiction_home);

        filled_bins.setOnClickListener(this);
        driver_mgmt.setOnClickListener(this);
        path_maker.setOnClickListener(this);
        notification.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {

        Fragment fragment = null;
        switch (v.getId()) {
            case R.id.filled_bins_home:
                fragment = new FragmentBinMarkersFilledBins();
                replaceFragment(fragment);
                break;

            case R.id.driver_management_home:
                fragment = new FragmentDriverManagement();
                replaceFragment(fragment);
                break;
            case R.id.path_maker_home:
                fragment = new FragmentPathMaker();
                replaceFragment(fragment);
                break;

            case R.id.notifiction_home:
                fragment = new FragmentNotifications();
                replaceFragment(fragment);
                break;
        }
    }


    public void replaceFragment(Fragment someFragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.content_after_login1, someFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }



}
