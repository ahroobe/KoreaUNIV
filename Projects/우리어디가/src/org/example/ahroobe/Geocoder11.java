package org.example.ahroobe;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.location.Address;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.location.Geocoder;

public class Geocoder11 extends Activity {

	Geocoder gc;
	EditText edt = null;
	TextView textview = null;
	ImageView img1 = null;
	int i;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.help);
		i=0;
		Button btn1 = (Button) findViewById(R.id.left);
		Button btn2 = (Button) findViewById(R.id.right);
		img1 = (ImageView) findViewById(R.id.imggg);
		img1.setImageResource(R.drawable.help1);
		btn1.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				if(i==1){
					i--;
					img1.setImageResource(R.drawable.help1);
				}
				else if(i==2){
					i--;
					img1.setImageResource(R.drawable.help2);
				}
				else if(i==3){
					i--;
					img1.setImageResource(R.drawable.help3);
				}
				else if(i==4){
					i--;
					img1.setImageResource(R.drawable.help4);
				}
				else if (i==5){
					i--;
					img1.setImageResource(R.drawable.help5);
				}
				
			}
		});
		btn2.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				if(i==0){
					i++;
					img1.setImageResource(R.drawable.help2);
				}
				else if(i==1){
					i++;
					img1.setImageResource(R.drawable.help3);
				}
				else if(i==2){
					i++;
					img1.setImageResource(R.drawable.help4);
				}
				else if(i==3){
					i++;
					img1.setImageResource(R.drawable.help5);
				}
				else if(i==4){
					i++;
					img1.setImageResource(R.drawable.help6);
				}

			}
		});
	
	}
}
