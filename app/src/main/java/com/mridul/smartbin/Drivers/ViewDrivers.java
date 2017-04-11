package com.mridul.smartbin.Drivers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.mridul.smartbin.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ViewDrivers extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_drivers);

        Intent intent1 = getIntent();
        String data = intent1.getStringExtra("jsonString_driverDetails");
        final String data_copy = data ;

        Log.d("In ViewDrivers :", data);

        final ArrayList<String> DRIVER_NAME = new ArrayList<>();
        try {
            JSONArray ja =new JSONArray(data);
            JSONObject jo =null;

            DRIVER_NAME.clear();

            for(int i=0; i<ja.length(); i++ ){
                jo = ja.getJSONObject(i);

                String driver_name = jo.getString("name");
                DRIVER_NAME.add(driver_name);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        HashMap<String,String> hashMap = new HashMap<>();
        for(int i=0; i<DRIVER_NAME.size(); i++){
            hashMap.put(""+(i+1)+" : ",DRIVER_NAME.get(i));
        }


        final ListView listView = (ListView)findViewById(R.id.lv_view_drivers);

        List<HashMap<String, String>> listItems = new ArrayList<>();
        SimpleAdapter adapter = new SimpleAdapter(this, listItems,R.layout.list_items_view_drivers, new String[]{"First Line","Second Line"}, new int[]{R.id.item_driver,R.id.sub_item_driver});

        Iterator it = hashMap.entrySet().iterator();
        int count = 0 ;
        while (it.hasNext()){
            count++;
            HashMap<String , String> h = new HashMap<>();
            Map.Entry pair = (Map.Entry)it.next();
            h.put("First Line",""+count+" : ");
            h.put("Second Line", pair.getValue().toString());
            listItems.add(h);
        }

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int pos = position;
                HashMap<String,String> item = (HashMap<String, String>) listView.getItemAtPosition(pos);
                //Toast.makeText(ViewDrivers.this,item.get("Second Line").toString(),Toast.LENGTH_LONG).show();
                Intent intent = new Intent(ViewDrivers.this,EachDriverDetail.class);
                intent.putExtra("json_driver_detail_copy",data_copy);
                intent.putExtra("driver_name", item.get("Second Line").toString());
                startActivity(intent);
            }
        });


    }
}
