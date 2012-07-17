package com.mygithubclient;

import java.util.Iterator;
import java.util.List;

import org.eclipse.egit.github.core.Repository;

import com.mygithubclient.Activity_RepoPage.LoadRepos;

import android.app.Activity;
import android.app.ProgressDialog;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;

public class Activity_ReposFollow extends Activity{

	
		private ListView mylist;
		private ProgressDialog pdg;
		private LazyAdapter2 lazyadapter;
		private Typeface font;
		
		
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			setContentView(R.layout.layout_reposfollow);
			
			 font = Typeface.createFromAsset(getAssets(), "GeosansLight.ttf");
			 
			 //------------Getting ID's of all elements------------------
			
			 mylist = (ListView) findViewById(R.id.lst_myrepofollow);
			 

			 //--------------Load Repos-------------------------
			 
			 new LoadRepos().execute();
		
		}
		
		public class LoadRepos extends AsyncTask<Void, Void, Void>{
			

			 
			@Override
			protected void onPreExecute() 
			{
			
			 try{
				 
				pdg = ProgressDialog.show(Activity_ReposFollow.this,"","Loading data...",true);
				
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
				
					
					  lazyadapter = new LazyAdapter2(Activity_ReposFollow.this, Activity_RepoPage.reponamefollow, Activity_RepoPage.repourlfollow,font);
				      mylist.setAdapter(lazyadapter);
				
					  try{
							
						  pdg.dismiss();
						  pdg = null;
					  }catch(Exception exp){
						  
						  
					  } 
					  
			}
		
		} 

	
}
