package com.mygithubclient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.RepositoryTag;
import org.eclipse.egit.github.core.client.PageIterator;
import org.eclipse.egit.github.core.event.Event;
import org.eclipse.egit.github.core.service.RepositoryService;
import org.eclipse.egit.github.core.service.UserService;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

public class Activity_RepoPage extends Activity {

	private ProgressDialog pdg; 
	private ArrayList<String> repoNames = new ArrayList<String>();
	private ArrayList<String> repoURL = new ArrayList<String>();
	private RepositoryService repoService;
	private ListView mylist;
	public static String myrepoUSER;
	private MenuItem menu_publicrepo,menu_repofollow;
	private Typeface font;
	public static ArrayList<String> publicreponame = new ArrayList<String>();
	public static ArrayList<String> publicrepourl = new ArrayList<String>();
	public static ArrayList<String> reponamefollow = new ArrayList<String>();
	public static ArrayList<String> repourlfollow = new ArrayList<String>();

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_repopage);
		
		font = Typeface.createFromAsset(getAssets(), "GeosansLight.ttf");
		
		//------------Getting ID's of all elements-----------------------
		
		mylist = (ListView) findViewById(R.id.lst_myrepolist);
	
		//----------Create Repo Service------------------------------
		
		repoService = new RepositoryService(Activity_LoginPage.client);
		
		
		publicreponame.clear();
		publicrepourl.clear();
		reponamefollow.clear();
		repourlfollow.clear();
		
		//---------------Load Repos--------------------------
		
		new LoadRepos().execute();
		
		
		
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu, menu);

		menu_publicrepo = (MenuItem) findViewById(R.id.menu_myownrepo);
		menu_repofollow = (MenuItem) findViewById(R.id.menu_repofollow);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {

		case R.id.menu_myownrepo:

			Intent intent = new Intent(Activity_RepoPage.this,Activity_PublicRepo.class);
			startActivityForResult(intent, 0);
			finish();

			break;

		case R.id.menu_repofollow:

			Intent intent2 = new Intent(Activity_RepoPage.this,Activity_ReposFollow.class);
			startActivityForResult(intent2, 0);
			finish();

			break;		

		}
		return true;
	}
	
	public class LoadRepos extends AsyncTask<Void, Void, Void>{
		

		 
		@Override
		protected void onPreExecute() 
		{
		
		 try{
			 
			pdg = ProgressDialog.show(Activity_RepoPage.this,"","Loading data...",true);
			
		 }catch(Exception exp){
			 
			 System.out.println("Inside progreess: "+exp.getMessage());
		 }
	  } 
	  
		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub
	

		     try {
		    	 
		    	
		        List<Repository> myRepos =  repoService.getRepositories();
		    	
		    	
		    	Iterator<Repository> ltr = myRepos.iterator();
		    	
		    	//Toast.makeText(Activity_RepoPage.this, "value : "+ myRepos.size(),Toast.LENGTH_SHORT).show();
		    	
		    	while(ltr.hasNext()){
		    		
		    		Repository repos = ltr.next();
		    		
		    		
		    		repoNames.add(repos.getName());
		    		
		    		repoURL.add(repos.getUrl());
		    		
		    		myrepoUSER = repos.getOwner().getLogin();
		    		
		    		if(repos.isPrivate()){
		    			
		    			/*repoNames.add(repos.getName());
			    		
			    		repoURL.add(repos.getUrl());*/
		    			
		    		}else{
		    		
		    			publicreponame.add(repos.getName());
		    			publicrepourl.add(repos.getUrl());
		    		
	    		
		    		}
		    		if(Activity_LoginPage.myUSER.equalsIgnoreCase(Activity_RepoPage.myrepoUSER)){
		    			
		    			
		    			
		    			
		    		}else{
		    			
		    			 reponamefollow.add(repos.getName());
		    			 repourlfollow.add(repos.getUrl());
		    			
		    		}
		    	/*	Toast.makeText(Activity_RepoPage.this, "value : "+ repos.getUrl(),Toast.LENGTH_SHORT).show();

		    		Toast.makeText(Activity_RepoPage.this, "value : "+ repos.getOwner().getLogin(), Toast.LENGTH_SHORT).show();
	*/	    	}
		
		     
		     } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
				         
			return null;
		}
		
		@Override
		protected void onPostExecute(Void res) {
			// TODO Auto-generated method stub
			
				
			      LazyAdapter2 mylazy = new LazyAdapter2(Activity_RepoPage.this, repoNames, repoURL,font);
			      mylist.setAdapter(mylazy);
			
				  try{
						
					  pdg.dismiss();
					  pdg = null;
				  }catch(Exception exp){
					  
					  
				  } 
				  
		}
	
	} 

	
}
