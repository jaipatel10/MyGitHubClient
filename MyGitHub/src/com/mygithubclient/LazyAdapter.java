package com.mygithubclient;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class LazyAdapter extends BaseAdapter {
    
    private Activity activity;
    private Typeface myfont;
    private ArrayList<String> data = new ArrayList<String>();
    private ArrayList<String> myname = new ArrayList<String>();
    private ArrayList<String> repourl = new ArrayList<String>();
    private ArrayList<String> repodatearr = new ArrayList<String>();
    private static LayoutInflater inflater=null;
    ImageView image;
    
    public LazyAdapter(Activity a, ArrayList<String> d , ArrayList<String> name , ArrayList<String> myrepourl, ArrayList<String> myrepodate, Typeface font) {
        activity = a;
        repourl = myrepourl;
        repodatearr = myrepodate;
        data=d;
        myfont = font;
        myname = name;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
       
        
    }

    public int getCount() {
        return data.size();
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
            vi = inflater.inflate(R.layout.custom_eventlist, null);

        TextView text=(TextView)vi.findViewById(R.id.txt_nameofuserevent);
        TextView repopath = (TextView)vi.findViewById(R.id.txt_reponame);
        TextView repodate = (TextView)vi.findViewById(R.id.txt_repodate);
        image=(ImageView)vi.findViewById(R.id.img_userpic);
        text.setText(myname.get(position));
        text.setTypeface(myfont);
        repopath.setText(repourl.get(position));
        repopath.setTypeface(myfont);
        repodate.setText(repodatearr.get(position));
        repodate.setTypeface(myfont);
        downloadimage(data.get(position));
        
        return vi;
    }
    public void downloadimage(String url){
    	
    	
    	URL myFileUrl =null; 
    	
		
		 try {
	           myFileUrl= new URL(url);
	      } catch (MalformedURLException e) {
	           // TODO Auto-generated catch block
	           e.printStackTrace();
	      }
	      try {
	           HttpURLConnection conn= (HttpURLConnection)myFileUrl.openConnection();
	           conn.setDoInput(true);
	           conn.connect();
	           InputStream is = conn.getInputStream();

	          Bitmap mybitmap = BitmapFactory.decodeStream(is);
	          image.setImageBitmap(mybitmap); 
	          
	     
	      } catch (IOException e) {
	           // TODO Auto-generated catch block
	           e.printStackTrace();
	      }
    	
    	
    }
}