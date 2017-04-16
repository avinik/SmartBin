package com.mridul.smartbin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;
import android.widget.SimpleAdapter;

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



        HashMap<String,String> hashMap = new HashMap<>();
        for(int i=0; i<3; i++){
            hashMap.put(""+(i+1)+" : ", "This should be a very long Notification to check abd verify proper indentation of the text entered.\n    This can be made Further long.");
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
            h.put("Second Line", "Notification "+count+" --> "+pair.getValue().toString());
            listItems.add(h);
        }

        listView.setAdapter(adapter);
    }
}
