
package com.mygithubclient;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Activity_HomePage extends Activity {

    private ListView mylist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_homepage);

        // ----------------Getting ID's of all the elements-------------------

        mylist = (ListView)findViewById(R.id.lst_homelist);
        mylist.setAdapter(new ImageAdapter(this));

        // ---------------------List SetItemOnClick
        // Listener---------------------

        mylist.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                // TODO Auto-generated method stub

                TextView mytext = (TextView)arg1.findViewById(R.id.txt_homemenuname);
                // Toast.makeText(Activity_HomePage.this, "My text :"+
                // mytext.getText().toString(), Toast.LENGTH_SHORT).show();

                if (mytext.getText().toString().equalsIgnoreCase("My Profile")) {

                    Intent intent = new Intent(Activity_HomePage.this, Activity_ProfilePage.class);
                    startActivity(intent);

                } else if (mytext.getText().toString().equalsIgnoreCase("My Events")) {

                    Intent intent = new Intent(Activity_HomePage.this, Activity_EventsPage.class);
                    startActivity(intent);

                } else {

                    Intent intent = new Intent(Activity_HomePage.this, Activity_RepoPage.class);
                    startActivity(intent);

                }

            }
        });

    }

    public class ImageAdapter extends BaseAdapter {

        private Context mContext;

        public ImageAdapter(Context c) {
            mContext = c;
        }

        public int getCount() {
            return mThumbIds.length;
        }

        public Object getItem(int position) {
            return null;
        }

        public long getItemId(int position) {
            return 0;
        }

        // create a new ImageView for each item referenced by the Adapter
        public View getView(int position, View convertView, ViewGroup parent) {
            View v;
            final RelativeLayout rel;
            if (convertView == null) { // if it's not recycled, initialize some
                                       // attributes
                LayoutInflater li = getLayoutInflater();
                v = li.inflate(R.layout.custom_homelist, null);
                TextView tv = (TextView)v.findViewById(R.id.txt_homemenuname);
                tv.setText(mTextsIds[position]);
                final ImageView iv = (ImageView)v.findViewById(R.id.img_homelistimage);
                iv.setImageResource(mThumbIds[position]);

                // rel = (RelativeLayout) v.findViewById(R.id.rel_customlist);

            } else {
                v = (View)convertView;
            }
            return v;
        }

        // references to our images
        private Integer[] mThumbIds = {
                R.drawable.aboutme, R.drawable.myevents, R.drawable.update
        };

        // references to our texts
        private String[] mTextsIds = {
                "My Profile", "My Events", "My Repositories"
        };
    }

}
