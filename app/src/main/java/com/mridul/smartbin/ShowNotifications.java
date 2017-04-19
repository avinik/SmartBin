package com.mridul.smartbin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ShowNotifications extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_notifications);

        Toolbar tbar = (Toolbar)findViewById(R.id.tbar_act_view_notifications);
        tbar.setTitle("Notifications");
        tbar.setTitleTextColor(getResources().getColor(R.color.WHITE));
        setSupportActionBar(tbar);



        // Do data parsing here .
        Intent intent1 = getIntent();
        String data = intent1.getStringExtra("jsonString_getNotification");
        //final String data_copy = data ;

        Log.d("In ViewDrivers :", data);

        ArrayList<String> BIN_ID = new ArrayList<>();
        ArrayList<String> NO_OF_TIMES = new ArrayList<>();
        ArrayList<String> NOTIFICATION = new ArrayList<>();
        try {
            JSONArray ja =new JSONArray(data);
            JSONObject jo =null;

            BIN_ID.clear();
            NO_OF_TIMES.clear();
            NOTIFICATION.clear();

            for(int i=0; i<ja.length(); i++ ){
                jo = ja.getJSONObject(i);

                String bin_id = jo.getString("bin_id");
                String no_of_times = jo.getString("numbers");
                String notification = jo.getString("message");
                int number = Integer.parseInt(no_of_times.trim());
                if(number >= 3) {
                    BIN_ID.add(bin_id);
                    NO_OF_TIMES.add(no_of_times);
                    NOTIFICATION.add(notification);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }



        HashMap<String,String> hashMap = new HashMap<>();
        for(int i=0; i<NO_OF_TIMES.size(); i++){
            hashMap.put(""+(i+1)+" : ", ""+BIN_ID.get(i)+"  has filled  "+NO_OF_TIMES.get(i)+"  times in last 7 days .\n   It is recommended to put an extra bin near that location .");
        }


        ListView listView = (ListView)findViewById(R.id.lv_view_notifications);

        List<HashMap<String, String>> listItems = new ArrayList<>();
        SimpleAdapter adapter = new SimpleAdapter(this, listItems,R.layout.list_item_show_notifications, new String[]{"First Line","Second Line"}, new int[]{R.id.item_notification,R.id.sub_item_notification});

        Iterator it = hashMap.entrySet().iterator();
        int count = 0 ;
        while (it.hasNext()){
            count++;
            HashMap<String , String> h = new HashMap<>();
            Map.Entry pair = (Map.Entry)it.next();
            h.put("First Line","Notification "+count+" : ");
            h.put("Second Line", " "+pair.getValue().toString());
            listItems.add(h);
        }

        listView.setAdapter(adapter);
    }
}
