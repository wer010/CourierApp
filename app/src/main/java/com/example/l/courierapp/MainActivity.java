package com.example.l.courierapp;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;


    private static final String[] orderid = {"1506030000", "1506030001", "1506030002", "1506030003", "1506030004","1506030005","1506030006"};
    private static final String[] orderaddress = {"二期望海路55号","观日路44号厦门信息投资","软件园二期18号"};
    private static final String[] phonenum = {"11111111","2222222222","33333333"};
    private static Order[] orders = {};
    private String s[] = {"全部订单","正在派送订单","已派送订单"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);


        mDrawerList.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,s));


        mDrawerList.setOnItemClickListener(new ListView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView arg0, View arg1, int arg2,long arg3) {
                //提示框显示的内容
                Fragment fragment = new PlanetFragment();
                Bundle args = new Bundle();
                args.putInt(PlanetFragment.ARG_PLANET_NUMBER, arg2);
                fragment.setArguments(args);

                // Insert the fragment by replacing any existing fragment
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.content_frame, fragment)
                        .commit();

                // Highlight the selected item, update the title, and close the drawer
                mDrawerList.setItemChecked(arg2, true);
                mDrawerLayout.closeDrawer(mDrawerList);
            }
        });



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

    public static class PlanetFragment extends Fragment {
        public static final String ARG_PLANET_NUMBER = "planet_number";
        private ListView mlistview;
        public PlanetFragment() {
            // Empty constructor required for fragment subclasses
        }

        public static Fragment newInstance(int position) {
            Fragment fragment = new PlanetFragment();
            Bundle args = new Bundle();
            args.putInt(PlanetFragment.ARG_PLANET_NUMBER, position);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.fragment_listview, container, false);
            mlistview = (ListView)rootView.findViewById(R.id.listView);
            ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String, Object>>();

            for(int i=0; i<3; i++){
                HashMap<String, Object> map = new HashMap<String, Object>();
                map.put("ItemTitle", "订单号："+orderid[i]);
                map.put("ItemText", "订单地址："+orderaddress[i]);
                map.put("ItemTime","送达时间:11:30");
                listItem.add(map);
            }

            SimpleAdapter simpleAdapter = new SimpleAdapter(getActivity(), listItem,R.layout.listviewitem, new String[]{"ItemTitle", "ItemText","ItemTime"},
                    new int[] {R.id.ItemTitle,R.id.ItemText,R.id.ItemTime});



            mlistview.setAdapter(simpleAdapter);

            mlistview.setOnItemClickListener(new ListView.OnItemClickListener() {


                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
                    Intent intent = new Intent(getActivity().getApplicationContext(), OrderInfoActivity.class);
                    intent.putExtra("address",orderaddress[arg2]);
                    intent.putExtra("orderid",orderid[arg2]);
                    intent.putExtra("phonenum",phonenum[arg2]);
                    startActivity(intent);
                }});
            return rootView;
        }
    }
}
