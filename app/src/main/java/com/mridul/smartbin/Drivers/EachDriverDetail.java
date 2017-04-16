package com.mridul.smartbin.Drivers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.mridul.smartbin.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class EachDriverDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_each_driver_detail);


        Toolbar tbar = (Toolbar)findViewById(R.id.tbar_act_each_driver_detail);
        tbar.setTitle("Driver Details");
        tbar.setTitleTextColor(getResources().getColor(R.color.WHITE));
        setSupportActionBar(tbar);



        Intent intent1 = getIntent();
        String data_copy = intent1.getStringExtra("json_driver_detail_copy");
        String driver_NAME = intent1.getStringExtra("driver_name");
        String driver_USER_NAME = "";
        String driver_MOB_NO = "";
        String driver_VEHICLE_NO = "";
        String driver_password = "";

        Log.d("In EachDriverDetail :", data_copy);

        try {
            JSONArray ja =new JSONArray(data_copy);
            JSONObject jo =null;

            for(int i=0; i<ja.length(); i++ ){
                jo = ja.getJSONObject(i);

                String driver_name = jo.getString("name");
                if(driver_name.equals(driver_NAME)){
                    driver_USER_NAME = jo.getString("user_name");
                    driver_MOB_NO = jo.getString("mob_no");
                    driver_VEHICLE_NO = jo.getString("vehicle_no");
                    driver_password = jo.getString("password");
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("User Name : ", driver_USER_NAME);
        hashMap.put("Name : ", driver_NAME);
        hashMap.put("Mobile Number : ", driver_MOB_NO);
        hashMap.put("Vehicle Number : ", driver_VEHICLE_NO);
        hashMap.put("Password : ", driver_password);


        final ListView listView = (ListView)findViewById(R.id.lv_view_each_driver);

        List<HashMap<String, String>> listItems = new ArrayList<>();
        SimpleAdapter adapter = new SimpleAdapter(this, listItems,R.layout.list_items_view_drivers, new String[]{"First Line","Second Line"}, new int[]{R.id.item_driver,R.id.sub_item_driver});

        Iterator it = hashMap.entrySet().iterator();
        while (it.hasNext()){
            HashMap<String , String> h = new HashMap<>();
            Map.Entry pair = (Map.Entry)it.next();
            h.put("First Line", pair.getKey().toString());
            h.put("Second Line", pair.getValue().toString());
            listItems.add(h);
        }

        listView.setAdapter(adapter);
    }
}
