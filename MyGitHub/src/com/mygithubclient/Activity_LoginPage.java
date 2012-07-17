package com.mygithubclient;

import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.UserService;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Activity_LoginPage extends Activity{

    private Button btn_signin;
	private EditText ed_password;
	private EditText ed_username;
	private TextView txt_logintext;
	private ProgressDialog pdg;
	public static  GitHubClient client;
	private int result = 0;
	private ConnectivityManager connec;
	public static String myUSER;

	
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			setContentView(R.layout.layout_loginpage);
			
			//-----------------Getting ID's of All the Elements-----------------------
			
			this.btn_signin = (Button) findViewById(R.id.btn_signin);
			this.ed_password = (EditText) findViewById(R.id.ed_password);
			this.ed_username = (EditText) findViewById(R.id.ed_username);
			this.txt_logintext = (TextView) findViewById(R.id.txt_logintext);
			
			Typeface font = Typeface.createFromAsset(getAssets(), "GeosansLight.ttf");
			txt_logintext.setTypeface(font);
			btn_signin.setTypeface(font);
			
			//----------------Sign-in Button Click Event------------------------------
			
			this.btn_signin.setOnClickListener(new View.OnClickListener() {
				public void onClick(View target) {
					
					
					if(ed_username.getText().toString().equalsIgnoreCase("") || ed_password.getText().toString().equalsIgnoreCase("")){
						
						Toast.makeText(Activity_LoginPage.this,"Username or Password cannot be empty!!!", Toast.LENGTH_SHORT).show();
						
					}else{
						
						connec = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
						
						if ( connec.getNetworkInfo(0).getState() == NetworkInfo.State.CONNECTED ||
								  connec.getNetworkInfo(1).getState() == NetworkInfo.State.CONNECTED ) {

							new ValidateLogin().execute();
							
						}else{
							
							
							Toast.makeText(Activity_LoginPage.this, "No active connection to the internet detected.  Please check this and then try again.", Toast.LENGTH_LONG).show();
							
						}
							
						
					}
					
				}
			});
			
	
		
		
		}
		
	
	public class ValidateLogin extends AsyncTask<Void, Void, Void>{

				 
				@Override
				protected void onPreExecute() 
				{
				
				 try{
					 
					pdg = ProgressDialog.show(Activity_LoginPage.this,"","Authenticating ...",true);
					
				 }catch(Exception exp){
					 
					 System.out.println("Inside progreess: "+exp.getMessage());
				 }
			  } 
			  
				@Override
				protected Void doInBackground(Void... params) {
					// TODO Auto-generated method stub
			
				
					try{
						
						//------------------Client Login Validation--------------------------------
						
						client = new GitHubClient();
						client.setCredentials(ed_username.getText().toString(),ed_password.getText().toString());
						
						UserService myuser = new UserService(client);
					
						
						String mymailid = myuser.getUser().getEmail();
					
						
						myUSER = myuser.getUser().getLogin();
						
					}catch(Exception e){
						
						result = 1;
						
					}
					
			         
					return null;
				}
				@Override
				protected void onPostExecute(Void res) {
					// TODO Auto-generated method stub
				
					 if(result == 1){
						  
						  Toast.makeText(Activity_LoginPage.this,"Authauntication Failed!!! Try again...", Toast.LENGTH_SHORT).show();
						  ed_username.setText("");
						  ed_password.setText("");
						  
						  result = 0;
						  
						  try{
								
							  pdg.dismiss();
							  pdg = null;
						  }catch(Exception exp){
							  
							  
						  } 
							
						  
						  
					  }else{
						  
						  
						  try{
								
							  pdg.dismiss();
							  pdg = null;
						  }catch(Exception exp){
							  
							  
						  } 
					
						  
						  Intent intent = new Intent(Activity_LoginPage.this,Activity_HomePage.class);
						  startActivity(intent);
						  finish();
						  
						  
						  
						  
					  }
					
					
			
				 
				
					
				} 
			
				
			}

	
	
}
