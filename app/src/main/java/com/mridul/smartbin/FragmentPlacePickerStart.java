package com.mridul.smartbin;

import android.Manifest;
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


public class FragmentPlacePickerStart extends Fragment {

    private static final int FINE_LOCATION_REQUEST_CODE = 301 ;
    private static final int PLACE_PICKER_REQUEST_CODE_START = 401 ;
    private static final int PLACE_PICKER_REQUEST_CODE_END = 402 ;


    Button btnStart ;
    Button btnEnd ;
    TextView tvPlaceName ;
    TextView tvPlaceAddress ;
    //WebView webView ;
    TextView tvDetails ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_place_picker_start,container,false);
        AfterLogin1.toolbar.setTitle("Start/End Positions");

        btnStart = (Button) v.findViewById(R.id.btn_path_start);
        btnEnd = (Button) v.findViewById(R.id.btn_path_end);
        tvPlaceName = (TextView)v.findViewById(R.id.tv_place_name);
        tvPlaceAddress = (TextView) v.findViewById(R.id.tv_place_address);
        //webView = (WebView) v.findViewById(R.id.webview_path_start);
        tvDetails = (TextView) v.findViewById(R.id.tv_detailText);

        permissionCheck();


        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnStart.setClickable(false);
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                try {
                    Intent intent = builder.build(getActivity());
                    startActivityForResult(intent,PLACE_PICKER_REQUEST_CODE_START);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
                btnStart.setClickable(true);
            }
        });

        btnEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnStart.setClickable(false);
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                try {
                    Intent intent = builder.build(getActivity());
                    startActivityForResult(intent,PLACE_PICKER_REQUEST_CODE_END);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }
                btnStart.setClickable(true);
            }
        });


        return v ;
    }

    private void permissionCheck(){
        if(ActivityCompat.checkSelfPermission(getContext(),Manifest.permission.ACCESS_FINE_LOCATION )!= PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, FINE_LOCATION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case FINE_LOCATION_REQUEST_CODE:
                if(grantResults[0] != PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(getContext(),"This Feature requires Location Access",Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PLACE_PICKER_REQUEST_CODE_START){
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


                String type = "pathStartingPosition";
                BackgroundWorker backgroundWorker = new BackgroundWorker(getContext());
                backgroundWorker.execute(type, placeId, name, address, lat.toString(), lng.toString());

                tvDetails.setText("Place selected for Start Position:-");
                tvPlaceName.setText(place.getName());
                tvPlaceAddress.setText("Address :"+place.getAddress());
                /*if(place.getAttributions() == null){
                    webView.loadData("No Attributions found","text/html; charset=utf-8", "UTF-8");
                }else{
                    webView.loadData(place.getAttributions().toString(),"text/html; charset=utf-8", "UTF-8");
                }*/
            }
        }
        else if(requestCode == PLACE_PICKER_REQUEST_CODE_END){

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


                    String type = "pathEndingPosition";
                    BackgroundWorker backgroundWorker = new BackgroundWorker(getContext());
                    backgroundWorker.execute(type, placeId, name, address, lat.toString(), lng.toString());

                    tvDetails.setText("Place selected for End Position:-");
                    tvPlaceName.setText(place.getName());
                    tvPlaceAddress.setText("Address :\n"+place.getAddress());
                    /*if(place.getAttributions() == null){
                        webView.loadData("No Attributions found","text/html; charset=utf-8", "UTF-8");
                    }else{
                        webView.loadData(place.getAttributions().toString(),"text/html; charset=utf-8", "UTF-8");
                    }*/
                }

        }






    }
}
