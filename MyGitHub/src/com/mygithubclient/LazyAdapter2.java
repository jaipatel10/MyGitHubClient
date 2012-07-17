package com.mygithubclient;


import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class LazyAdapter2 extends BaseAdapter {
    
    private Activity activity;
    private ArrayList<String> myreponames = new ArrayList<String>();
    private ArrayList<String> myrepourls = new ArrayList<String>();

    private static LayoutInflater inflater=null;
    private Typeface myfont;
    ImageView image;
    
    public LazyAdapter2(Activity a, ArrayList<String> myrepos , ArrayList<String> myurls, Typeface font) {
        
    	activity = a;
        myreponames = myrepos;
        myrepourls = myurls;
        myfont = font;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        
    }

    public int getCount() {
        return myreponames.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }
    
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        if(convertView==null)
        
        vi = inflater.inflate(R.layout.custom_repolist, null);

        TextView reponame=(TextView)vi.findViewById(R.id.txt_reponamerepodetail);
        TextView repopath = (TextView)vi.findViewById(R.id.txt_repourlrepodetail);
        
        reponame.setText(myreponames.get(position));
        reponame.setTypeface(myfont);
        repopath.setText(myrepourls.get(position));
        repopath.setTypeface(myfont);
        
        return vi;
    }
    
}