package com.mridul.smartbin;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


public class FragmentAccountInfo extends Fragment {



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_account_info, container, false);
        AfterLogin1.toolbar.setTitle("Account Info");

        ListView listView = (ListView)v.findViewById(R.id.listview_accountInfo1);

        HashMap<String, String> hashMap = new HashMap<>();

        String email_user = getArguments().getString("email_current_user");
        String mob_no_user = getArguments().getString("mob_no_current_user");
        String name_user = getArguments().getString("name_current_user");
        /*String type="accountInfo";
        BackgroundWorker backgroundWorker = new BackgroundWorker(getContext());
        backgroundWorker.execute(type, email_user);*/



        hashMap.put("E-mail :", email_user);
        hashMap.put("Mobile No. :", mob_no_user);
        hashMap.put("Name :", name_user);


        List<HashMap<String, String>> listItems = new ArrayList<>();
        SimpleAdapter adapter = new SimpleAdapter(getContext(), listItems, R.layout.list_item_account_info, new String[]{"First Line", "Second Line"}, new int[]{R.id.item, R.id.sub_item});

        Iterator it = hashMap.entrySet().iterator();
        while (it.hasNext()) {
            HashMap<String, String> h = new HashMap<>();
            Map.Entry pair = (Map.Entry) it.next();
            h.put("First Line", pair.getKey().toString());
            h.put("Second Line", pair.getValue().toString());
            listItems.add(h);
        }

        listView.setAdapter(adapter);

        return v;
    }

}
