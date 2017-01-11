package com.ahroobe.bucket;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class AchieveActivity extends Activity {

	FileReader read;
	FileWriter write;
	File file;
	String path;
	File myfile;
	int tot;
	int done;
	int rate = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_achieve);

		Typeface typeface = Typeface.createFromAsset(getAssets(),
				"fonts/yagan.ttf");
		myfile = getDir("alldata", Activity.MODE_PRIVATE);
		path = myfile.getAbsolutePath();

		TextView text1 = (TextView) findViewById(R.id.achtitle);
		TextView text2 = (TextView) findViewById(R.id.achgoal);
		TextView text3 = (TextView) findViewById(R.id.achrate);
		TextView text4 = (TextView) findViewById(R.id.ask);
		text1.setTypeface(typeface);
		text2.setTypeface(typeface);
		text3.setTypeface(typeface);
		text4.setTypeface(typeface);
		text1.setText(Share.realtitle);
		text2.setText(Share.realgoal);
		ProgressBar progress = (ProgressBar) findViewById(R.id.progressBar1);
		progress.setIndeterminate(false);
		progress.setMax(100);
		Button btn1 = (Button) findViewById(R.id.achbtn);
		btn1.setTypeface(typeface);
		String temp = "";
		String temp2 = "";
		// %랑, progress 설정.
		file = new File(path + "/important.txt");
		try {
			read = new FileReader(file);
			int k = 0;
			int j = 0;

			while (true) {
				int data = read.read();
				if (data == -1)
					break;
				char ch = (char) data;
				if (k == Share.pos && ch=='}') {
					j++;
				}
				if(k==Share.pos){
					if ((j == 7 + 3 * Share.position) && ch != '}') {
						temp += ch;
					} else if ((j == 8 + 3 * Share.position) && ch != '}') {
						temp2 += ch;
					}
				}
				if (ch == '{') {
					k++;
					j = 0;
				}

			}
			read.close();
			tot = Integer.parseInt(temp);
			done = Integer.parseInt(temp2);

			if (tot != 0) {
				rate = done * 100 / tot;
			}

		} catch (Exception eo) {

		}

		text3.setText(rate + "%");
		text4.setText("총 " + temp + "번 중에 " + temp2 + "번 완료하셨네요.");
		progress.setProgress(rate);

		btn1.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				new AlertDialog.Builder(AchieveActivity.this)
						.setTitle("정말요?")
						.setMessage("완료하시겠어요? 달성 횟수에 +1이 올라갑니다.")
						.setPositiveButton("네",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int which) {

										// 달성완료 시
										if (rate == 100) {
											Toast.makeText(
													getApplicationContext(),
													"이미 완료하셨네요!",
													Toast.LENGTH_SHORT).show();
										} else {
											// 목표의 한 수 +1시키기.

											try {
												read = new FileReader(file);
												boolean change = false;
												if (done + 1 == tot) {
													change = true;
												}
												int k = 0;
												int j = 0;
												String sentence = "";
												String temp = "";
												int h = done + 1;
												while (true) {
													int data = read.read();
													if (data == -1)
														break;
													char ch = (char) data;
													if (k == Share.pos) {
														if(ch=='}')
															j++;
														if ((j == 9 + 3 * Share.position)
																&& ch == '}') {
															sentence += "}" + h;
														}
														if (change && (j == 2)
																&& (ch != '}')) {
															temp += ch;
														}
														if ((j == 3)
																&& ch == '}'
																&& change) {
															sentence += "}"
																	+ String.valueOf((Integer
																			.parseInt(temp) + 1));
														}
														if (!((j == 8 + 3 * Share.position) || ((j == 2) && change))) {
															sentence += ch;
														}
													}else{
														sentence+=ch;
													}
													if (ch == '{') {
														k++;
														j = 0;
													}

												}
												read.close();
												write = new FileWriter(file);
												write.write(sentence);
												write.close();

											} catch (Exception eo) {

											}

											Intent myIntent = new Intent(
													getApplicationContext(),
													AchieveActivity.class);
											startActivity(myIntent);
											finish();
										}

									}
								})
						.setNegativeButton("아니요",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int which) {

									}
								}).show();

			}
		});
	}

	@Override
	public void onBackPressed() {
		// your code.
		Intent myIntent = new Intent(getApplicationContext(),
				MissionActivity.class);
		startActivity(myIntent);
		finish();
	}

}
