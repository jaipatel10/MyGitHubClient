package com.mygithubclient;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;

public class Activity_PublicRepo extends Activity{

	
		private ListView mylist;
		private ProgressDialog pdg;
		private LazyAdapter2 lazyadapter;
		private Typeface font;
		
		
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			setContentView(R.layout.layout_mypublicrepos);
			
			font = Typeface.createFromAsset(getAssets(), "GeosansLight.ttf");
			
			//--------Getting ID's of all elements-----------------
			
			mylist = (ListView) findViewById(R.id.lst_mypublicrepolist);
			 
			//-------------Load Repositories-------------------------
			
			new LoadRepos().execute();
		
		}
		
		public class LoadRepos extends AsyncTask<Void, Void, Void>{
			

			 
			@Override
			protected void onPreExecute() 
			{
			
			 try{
				 
				pdg = ProgressDialog.show(Activity_PublicRepo.this,"","Loading data...",true);
				
			 }catch(Exception exp){
				 
				 System.out.println("Inside progreess: "+exp.getMessage());
			 }
		  } 
		  
			@Override
			protected Void doInBackground(Void... params) {
				// TODO Auto-generated method stub
		
				
					         
				return null;
			}
			
			@Override
			protected void onPostExecute(Void res) {
				// TODO Auto-generated method stub
				
					
					  mylist.setAdapter(null);	
					  lazyadapter = new LazyAdapter2(Activity_PublicRepo.this, Activity_RepoPage.publicreponame, Activity_RepoPage.publicrepourl,font);
				      mylist.setAdapter(lazyadapter);
				
					  try{
							
						  pdg.dismiss();
						  pdg = null;
					  }catch(Exception exp){
						  
						  
					  } 
					  
			}
		
		} 

	
}
