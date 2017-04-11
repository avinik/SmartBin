package com.mridul.smartbin;


import android.*;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.model.LatLng;

import static android.app.Activity.RESULT_OK;

public class FragmentInstallBins extends Fragment {

    private static final int FINE_LOCATION_REQUEST_CODE_INSTALL_BIN = 101 ;
    private static final int PLACE_PICKER_REQUEST_CODE_INSTALL_BIN = 201 ;



    Button btnInstall ;
    TextView tvPlaceName ;
    TextView tvPlaceAddress ;
   // WebView webView ;
    TextView tvDetails ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_install_bins,container,false);
        AfterLogin1.toolbar.setTitle("Install Bins");

        btnInstall = (Button) v.findViewById(R.id.btn_install_bins);
        tvPlaceName = (TextView)v.findViewById(R.id.tv_place_name_install_bins);
        tvPlaceAddress = (TextView) v.findViewById(R.id.tv_place_address_install_bins);
        //webView = (WebView) v.findViewById(R.id.webview_install_bins);
        tvDetails = (TextView) v.findViewById(R.id.tv_detailText_install_bins);

        permissionCheck();


        btnInstall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnInstall.setClickable(false);
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                try {
                    Intent intent = builder.build(getActivity());
                    startActivityForResult(intent,PLACE_PICKER_REQUEST_CODE_INSTALL_BIN);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
                btnInstall.setClickable(true);
            }
        });

        return v ;
    }

    private void permissionCheck(){
        if(ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION )!= PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, FINE_LOCATION_REQUEST_CODE_INSTALL_BIN);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case FINE_LOCATION_REQUEST_CODE_INSTALL_BIN:
                if(grantResults[0] != PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(getContext(),"This Feature requires Location Access",Toast.LENGTH_LONG).show();
                }
                break;
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PLACE_PICKER_REQUEST_CODE_INSTALL_BIN){
            if(resultCode == RESULT_OK){
                Place place = PlacePicker.getPlace(getContext(),data);

                /* A Place object contains details about that place, such as its name, address
                and phone number. Extract the name, address, phone number, place ID and place types.
                 */
                final String name = place.getName().toString();
                final String address = place.getAddress().toString();
                final String placeId = place.getId();
                final LatLng latlng = place.getLatLng();
                Double lat = latlng.latitude;
                Double lng = latlng.longitude;


                String type = "installBin";
                BackgroundWorker backgroundWorker = new BackgroundWorker(getContext());
                backgroundWorker.execute(type, placeId, name, address, lat.toString(), lng.toString());

                tvDetails.setText("Selected Place for Installing Bin:-");
                tvPlaceName.setText(place.getName());
                tvPlaceAddress.setText("Address :"+place.getAddress());

                String type2 = "generateAllCombinations";
                BackgroundWorkerGenerateAllCombinations bg = new BackgroundWorkerGenerateAllCombinations(getContext());
                bg.execute(type2);

                /*if(place.getAttributions() == null){
                    webView.loadData("No Attributions found","text/html; charset=utf-8", "UTF-8");
                }else{
                    webView.loadData(place.getAttributions().toString(),"text/html; charset=utf-8", "UTF-8");
                }*/
            }
        }
    }



}
