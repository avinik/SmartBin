package com.mridul.smartbin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class AccountInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_info);

        ListView listView = (ListView)findViewById(R.id.listview_accountInfo);

        HashMap<String, String> hashMap = new HashMap<>();

        Intent i = getIntent();

        hashMap.put("E-mail :", i.getStringExtra("email"));
        hashMap.put("Mobile No. :", i.getStringExtra("mob_no"));
        hashMap.put("Name :", i.getStringExtra("name"));


        List<HashMap<String, String>> listItems = new ArrayList<>();
        SimpleAdapter adapter = new SimpleAdapter(this, listItems,R.layout.list_item_account_info, new String[]{"First Line","Second Line"}, new int[]{R.id.item,R.id.sub_item});

        Iterator it = hashMap.entrySet().iterator();
        while (it.hasNext()){
            HashMap<String , String> h = new HashMap<>();
            Map.Entry pair = (Map.Entry)it.next();
            h.put("First Line",pair.getKey().toString());
            h.put("Second Line", pair.getValue().toString());
            listItems.add(h);
        }

        listView.setAdapter(adapter);
        /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });*/
    }
}
