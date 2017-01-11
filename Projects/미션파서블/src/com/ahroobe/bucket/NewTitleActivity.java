package com.ahroobe.bucket;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.EditText;
import android.widget.TextView;

public class NewTitleActivity extends Activity {

	String path;
	File file;
	File myfile;
	FileWriter write1;
	FileReader read1;
	int totaltitlenumber;
	EditText edit;
	DatePicker datepick;
	int year;
	int month;
	int day;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_newtitle);

		Typeface typeface;
		typeface = Typeface.createFromAsset(getAssets(), "fonts/yagan.ttf");

		TextView txt = (TextView) findViewById(R.id.aaa);
		txt.setTypeface(MainActivity.typeface);
		TextView txt2 = (TextView) findViewById(R.id.bbb);
		txt2.setTypeface(MainActivity.typeface);
		TextView txt3 = (TextView) findViewById(R.id.ccc);
		txt3.setTypeface(MainActivity.typeface);

		edit = (EditText) findViewById(R.id.titlename);

		Button btn = (Button) findViewById(R.id.cancel1);
		btn.setTypeface(MainActivity.typeface);
		Button btn1 = (Button) findViewById(R.id.make1);
		btn1.setTypeface(MainActivity.typeface);

		myfile = getDir("alldata", Activity.MODE_PRIVATE);
		path = myfile.getAbsolutePath();
		file = new File(path + "/totalnumber.txt");

		datepick = (DatePicker) findViewById(R.id.datePicker1);
		GregorianCalendar oCalen = new GregorianCalendar();

		btn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent myIntent = new Intent(getApplicationContext(),
						MainActivity.class);
				startActivity(myIntent);
				finish();
			}
		});
		btn1.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				boolean can=false;
				for(int i=0;i<edit.getText().toString().length();i++){
					if(edit.getText().toString().charAt(i)=='{' || edit.getText().toString().charAt(i)=='}'){
						can=true;
						break;
					}
				}
				if (edit.getText().toString().length() == 0) {
					Toast.makeText(getApplicationContext(), "타이틀 명을 입력해 주세요.",
							Toast.LENGTH_SHORT).show();
				}else if(can){ 
					Toast.makeText(getApplicationContext(), "}나 {는 입력이 불가능합니다.",
							Toast.LENGTH_SHORT).show();
				}
				else {
					try {
						if (file.exists()) {
							read1 = new FileReader(file);
							String temp = "";
							while (true) {
								int data = read1.read();
								if (data == -1)
									break;
								temp += (char) data;
							}
							totaltitlenumber = Integer.parseInt(temp);
							read1.close();
							int b = totaltitlenumber + 1;
							write1 = new FileWriter(file);
							write1.write(String.valueOf(b));
							write1.close();
							GregorianCalendar oCalen = new GregorianCalendar();
							if ((year == 0 && month == 0 && day == 0)
									|| ((year == oCalen.get(Calendar.YEAR)
											&& (month == oCalen
													.get(Calendar.MONTH)) && (day == oCalen
											.get(Calendar.DAY_OF_MONTH)))))   {

								year = 0;
								month = 0;
								day = 0;
							}
							file = new File(path + "/important.txt");
							write1 = new FileWriter(file, true);
							write1.write(edit.getText().toString() + "}" + "0"
									+ "}" + "0" + "}" + String.valueOf(year)
									+ "}" + String.valueOf(month) + "}"
									+ String.valueOf(day) + "}{");
							write1.close();
							if (MainActivity.list != null)
								((AhroobeAdapter) MainActivity.list
										.getAdapter())
										.notifyDataSetInvalidated();
						}
					} catch (Exception eo) {

					}

					Intent myIntent = new Intent(getApplicationContext(),
							MainActivity.class);
					startActivity(myIntent);
					finish();

				}
			}
		});

		datepick.init(oCalen.get(Calendar.YEAR), oCalen.get(Calendar.MONTH),
				oCalen.get(Calendar.DAY_OF_MONTH), new OnDateChangedListener() {
					@Override
					public void onDateChanged(DatePicker view, int year1,
							int month1, int day1) {
						year = year1;
						month = month1;
						day = day1;
					}
				});
	}

	@Override
	public void onBackPressed() {
		// your code.
		Intent myIntent = new Intent(getApplicationContext(),
				MainActivity.class);
		startActivity(myIntent);
		finish();
	}

}