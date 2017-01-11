package com.ahroobe.bucket;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class SplashActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		
		Handler hd = new Handler() {
			public void handleMessage(Message msg) {
				Intent myIntent = new Intent(getApplicationContext(),
						MainActivity.class);
				startActivity(myIntent);
				finish();
				
			}
		};
		hd.sendEmptyMessageDelayed(0, 1500);
	}
	
}
