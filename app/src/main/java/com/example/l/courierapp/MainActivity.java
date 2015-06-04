package com.example.l.courierapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;

import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends ActionBarActivity {

    private ListView mlistview;
    private static final String[] orderid = {"1506030000", "1506030001", "1506030002", "1506030003", "1506030004","1506030005","1506030006"};
    private static final String[] orderaddress = {"二期望海路55号","观日路44号厦门信息投资","软件园二期18号"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mlistview = (ListView)findViewById(R.id.listView);

        ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();

        for(int i=0; i<3; i++){
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("ItemTitle", "订单号："+orderid[i]);
            map.put("ItemText", "订单地址："+orderaddress[i]);
            listItem.add(map);
        }

    SimpleAdapter simpleAdapter = new SimpleAdapter(this, listItem,R.layout.listviewitem, new String[]{"ItemTitle", "ItemText"},
                    new int[] {R.id.ItemTitle,R.id.ItemText});



    mlistview.setAdapter(simpleAdapter);

    mlistview.setOnItemClickListener(new ListView.OnItemClickListener() {


        @Override
        public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
            Toast.makeText(getApplicationContext(), "你选择了第" + arg2 + "行", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), OrderInfoActivity.class);
            startActivity(intent);
        }});


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
