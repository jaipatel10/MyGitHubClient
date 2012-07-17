package com.mygithubclient;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.egit.github.core.service.UserService;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Activity_ProfilePage extends Activity{
	
	private ProgressDialog pdg;
	private ImageView img_profilepic;
	private TextView txt_nameofuser,txt_WebsiteURL, txt_emailid,txt_location,txt_companyname,txt_followers,txt_following,txt_collab,txt_privategist,txt_publicgist,txt_privaterepo,txt_publicrepo;
	private TextView txt_nameofuservalue, txt_emailidvalue,txt_locationvalue,txt_companynamevalue,txt_followersvalue,txt_followingvalue,txt_collabvalue,txt_privategistvalue,txt_publicgistvalue,txt_privaterepovalue,txt_publicrepovalue;
	private UserService myuser;
	private String user_mailid,user_name,user_imageurl,user_location,user_companyname,user_websiteURL;
	private int user_followers,user_following,user_collaborators,user_privategist,user_publicgist,user_privaterepo,user_publicrepo;
	private Bitmap mybitmap;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_profilepage);
		
		Typeface font = Typeface.createFromAsset(getAssets(), "GeosansLight.ttf");
		
		//------------------Getting ID's of all the elements---------------------
		
		img_profilepic = (ImageView) findViewById(R.id.img_profilepic);
		txt_nameofuser = (TextView) findViewById(R.id.txt_nameofuser);
		txt_WebsiteURL = (TextView) findViewById(R.id.txt_websiteURL);
		txt_emailid = (TextView) findViewById(R.id.txt_emailid);
		txt_location = (TextView) findViewById(R.id.txt_location);
		txt_companyname = (TextView) findViewById(R.id.txt_companyname);
		txt_followers = (TextView) findViewById(R.id.txt_followers);
		txt_following = (TextView) findViewById(R.id.txt_following);
		txt_collab = (TextView) findViewById(R.id.txt_collaborators);
		txt_privategist = (TextView) findViewById(R.id.txt_privategist);
		txt_publicgist = (TextView) findViewById(R.id.txt_publicgist);
		txt_privaterepo = (TextView) findViewById(R.id.txt_privaterepo);
		txt_publicrepo = (TextView) findViewById(R.id.txt_publicrepo);
		
		txt_locationvalue = (TextView) findViewById(R.id.txt_locationvalue);
		txt_companynamevalue = (TextView) findViewById(R.id.txt_companynamevalue);
		txt_followersvalue = (TextView) findViewById(R.id.txt_followrsvalue);
		txt_followingvalue = (TextView) findViewById(R.id.txt_followingvalue);
		txt_collabvalue = (TextView) findViewById(R.id.txt_collaboratesvalue);
		txt_privategistvalue = (TextView) findViewById(R.id.txt_privategistvalue);
		txt_publicgistvalue = (TextView) findViewById(R.id.txt_publicgistvalue);
		txt_privaterepovalue = (TextView) findViewById(R.id.txt_privaterepovalue);
		txt_publicrepovalue = (TextView) findViewById(R.id.txt_publicrepovalue);

		
		txt_locationvalue.setTypeface(font);
		txt_companynamevalue.setTypeface(font);
		txt_followersvalue.setTypeface(font);
		txt_followingvalue.setTypeface(font);
		txt_collabvalue.setTypeface(font);
		txt_privategistvalue.setTypeface(font);
		txt_publicgistvalue.setTypeface(font);
		txt_privategistvalue.setTypeface(font);
		txt_privaterepovalue.setTypeface(font);
		txt_publicrepovalue.setTypeface(font);
		txt_nameofuser.setTypeface(font);
		txt_emailid.setTypeface(font);
		txt_location.setTypeface(font);
		txt_WebsiteURL.setTypeface(font);
		txt_location.setTypeface(font);
		txt_companyname.setTypeface(font);
		txt_followers.setTypeface(font);
		txt_following.setTypeface(font);
		txt_collab.setTypeface(font);
		txt_privategist.setTypeface(font);
		txt_publicgist.setTypeface(font);
		txt_privaterepo.setTypeface(font);
		txt_publicrepo.setTypeface(font);
		
		
		//--------------Load Data-----------------------------
		
		new LoadData().execute();
		
	
	}
	
	public class LoadData extends AsyncTask<Void, Void, Void>{
		


		 
		@Override
		protected void onPreExecute() 
		{
		
		 try{
			 
			pdg = ProgressDialog.show(Activity_ProfilePage.this,"","Loading data...",true);
			
		 }catch(Exception exp){
			 
			 System.out.println("Inside progreess: "+exp.getMessage());
		 }
	  } 
	  
		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
	
		
			try{
				
				//---------------- Getting User Profile----------------------------
				
				myuser = new UserService(Activity_LoginPage.client);
				
				user_mailid = myuser.getUser().getEmail();
				
				user_name = myuser.getUser().getName();
				
				user_websiteURL = myuser.getUser().getUrl();
				
				user_imageurl = myuser.getUser().getAvatarUrl();
				
				user_location = myuser.getUser().getLocation();
				
				user_companyname = myuser.getUser().getCompany();
				
				user_collaborators = myuser.getUser().getCollaborators();
				
				user_followers = myuser.getUser().getFollowers();
				
				user_following = myuser.getUser().getFollowing();
				
				user_privategist = myuser.getUser().getPrivateGists();
				
				user_publicgist = myuser.getUser().getPublicGists();
				
				user_privaterepo = myuser.getUser().getOwnedPrivateRepos();
				
				user_publicrepo = myuser.getUser().getPublicRepos();
				
				URL myFileUrl =null; 
				
				 try {
			           myFileUrl= new URL(user_imageurl);
			      } catch (MalformedURLException e) {
			           // TODO Auto-generated catch block
			           e.printStackTrace();
			      }
			      try {
			           HttpURLConnection conn= (HttpURLConnection)myFileUrl.openConnection();
			           conn.setDoInput(true);
			           conn.connect();
			           InputStream is = conn.getInputStream();

			           mybitmap = BitmapFactory.decodeStream(is);
			           
			     
			      } catch (IOException e) {
			           // TODO Auto-generated catch block
			           e.printStackTrace();
			      }
				
				
			
				
			
			}catch(Exception e){
				
				
				
			}
			
	         
			return null;
		}
		@Override
		protected void onPostExecute(Void res) {
			// TODO Auto-generated method stub
			
				txt_nameofuser.setText(user_name);
				txt_emailid.setText(user_mailid);
				txt_WebsiteURL.setText(user_websiteURL);
				img_profilepic.setImageBitmap(mybitmap);
				txt_locationvalue.setText(" : "+user_location);
				txt_companynamevalue.setText(" : "+user_companyname);
				txt_followersvalue.setText(" : "+user_followers);
				txt_collabvalue.setText(" : "+user_collaborators);
				txt_followingvalue.setText(" : "+user_following);
				txt_privategistvalue.setText(" : "+user_privategist);
				txt_publicgistvalue.setText(" : "+user_publicgist);
				txt_privaterepovalue.setText(" : "+user_privaterepo);
				txt_publicrepovalue.setText(" : "+user_publicrepo);
				
				
				//Toast.makeText(Activity_ProfilePage.this,"MyURl: "+user_imageurl, Toast.LENGTH_SHORT).show();
				System.out.println("MyURL:: "+user_imageurl);
				  
				  try{
						
					  pdg.dismiss();
					  pdg = null;
				  }catch(Exception exp){
					  
					  
				  } 
			
	
		} 
	

	}



}
