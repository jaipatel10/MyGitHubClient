package com.mygithubclient;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.test.ActivityUnitTestCase;
import android.app.*;
import android.widget.*;
import android.view.*;

public class Activity_SplashScreen extends Activity {
    private TextView txt_splashtext;
	/** Called when the activity is first created. */
	
	private Thread mSplashThread;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_splashscreen);
        
        //-----------------Getting ID's of all elements-------------------
        
        this.txt_splashtext = (TextView) findViewById(R.id.txt_splashtext);
        
		Typeface font = Typeface.createFromAsset(getAssets(), "GeosansLight.ttf");
		
		txt_splashtext.setTypeface(font);

        
        final Activity_SplashScreen sPlashScreen = this;
        
        mSplashThread =  new Thread(){
            @Override
            public void run(){
                try {
                    synchronized(this){
                        // Wait for 5000 msec
                        wait(2000);
                    }
                }
                catch(InterruptedException ex){                    
                }
                finish();
                
               
                    // Run next activity
                    Intent intent = new Intent();
                    intent.setClass(sPlashScreen, Activity_LoginPage.class);
                    startActivity(intent);
                   // stop();          
                
                        
            }
        };
        mSplashThread.start();
        

        
    }
}