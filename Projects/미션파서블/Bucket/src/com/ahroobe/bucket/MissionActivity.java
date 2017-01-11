package com.ahroobe.bucket;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class MissionActivity extends Activity {

	ArrayList<MissionGroup> ahroobe;
	static Typeface typeface;
	ListView list;
	AlertDialog newGoalDlg = null;
	AlertDialog reviseDlg = null;
	ScrollView scroll2;
	String path;
	File myfile;
	File file;
	FileWriter write;
	FileReader read;
	int goalnumber;
	EditText edit1;
	EditText edit2;
	String goal[];
	String rate[];
	int pict[];
	int total[];
	int done[];
	int pos;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mission);

		typeface = Typeface.createFromAsset(getAssets(), "fonts/yagan.ttf");

		ahroobe = new ArrayList<MissionGroup>();
		MissionGroup test;

		myfile = getDir("alldata", Activity.MODE_PRIVATE);
		path = myfile.getAbsolutePath();
		file = new File(path + "/important.txt");
		makingNewGoalDialog(R.layout.activity_newgoal);
		reviseDialog(R.layout.simpledialog);
		TextView txtt = (TextView)findViewById(R.id.explain2);
		txtt.setTypeface(typeface);
		try {

			read = new FileReader(file);

			String temp = "";
			int k = 0; // 타이틀수
			int j = 0; // 건너간 수
			boolean check = false;
			while (true) {
				int data = read.read();
				if (data == -1)
					break;
				char ch = (char) data;

				if (ch != '}' && j == 1) {
					temp += ch;
				} else {
					if (k == Share.pos) {
						check = true;

					}
					if (check && ch == '}') {
						j++;
					}
					if (ch == '{') {
						k++;
						j = 0;
						check = false;
					}
				}

			}
			read.close();
			goalnumber = Integer.parseInt(temp);
			pict = new int[goalnumber];
			goal = new String[goalnumber];
			rate = new String[goalnumber];
			total = new int[goalnumber];
			done = new int[goalnumber];

			read = new FileReader(file);
			k = 0;// 몇번째
			j = 0; // 목표명이랑.. 뭔지 그런거 세는거
			boolean start = false;
			temp = "";
			while (true) {
				int data = read.read();
				if (data == -1)
					break;
				char ch = (char) data;
				if (k == Share.pos) {
					start = true;
				}
				if (start && ch == '}') {
					j++;
				}
				if (start && j >= 6 && j <= 6 + 3 * goalnumber) {
					if (ch == '}') {
						if (j == 6) {
							temp = "";

						} else {
							if (j % 3 == 1) {
								goal[(j - 1) / 3 - 2] = temp;
							} else if (j % 3 == 2) {
								total[(j - 1) / 3 - 2] = Integer.parseInt(temp);
							} else if (j % 3 == 0) {
								done[(j - 1) / 3 - 2] = Integer.parseInt(temp);
							}
							temp = "";
						}
					} else {
						temp += ch;
					}
				}
				if (ch == '{') {
					k++;
					temp = "";
					j = 0;
					start = false;
				}
			}
			read.close();

		} catch (Exception eo) {

		}

		for (int i = 0; i < goalnumber; i++) {

			int k = 0;
			int j = R.drawable.unclear;
			if (total[i] != 0) {
				k = 100 * done[i] / total[i];
				if (k == 100)
					j = R.drawable.clear;
			}
			test = new MissionGroup(j, goal[i], "달성도: " + k + "%");
			ahroobe.add(test);
		}

		AhroobeAdapterA adapter = new AhroobeAdapterA(this,
				R.layout.listmission, ahroobe);

		TextView tes = (TextView) findViewById(R.id.title1);
		tes.setText(Share.realtitle);
		tes.setTypeface(typeface);
		list = (ListView) findViewById(R.id.listView2);
		list.setAdapter(adapter);

		scroll2 = (ScrollView) findViewById(R.id.scrollView1);

		list.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				scroll2.requestDisallowInterceptTouchEvent(true);
				return false;
			}

		});

		list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				Share.realgoal = goal[position];
				Share.position = position;
				Intent myIntent = new Intent(getApplicationContext(),
						AchieveActivity.class);
				startActivity(myIntent);
				finish();

			}
		});

		list.setOnItemLongClickListener(new OnItemLongClickListener() {

			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				pos = position;
				reviseDlg.show();
				return true;
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.makenew, menu);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.makenew) {

			newGoalDlg.show();
		}
		return super.onOptionsItemSelected(item);
	}

	private void makingNewGoalDialog(int resId) {
		View view = this.getLayoutInflater().inflate(R.layout.activity_newgoal,
				null);
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setView(view);
		newGoalDlg = builder.create();
		newGoalDlg.setCanceledOnTouchOutside(true);

		Button btn1 = (Button) view.findViewById(R.id.make2);
		Button btn2 = (Button) view.findViewById(R.id.cancel2);
		edit1 = (EditText) view.findViewById(R.id.editText10);
		edit2 = (EditText) view.findViewById(R.id.editText20);
		btn2.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				// 취소버튼
				if (newGoalDlg != null)
					newGoalDlg.cancel();
			}
		});

		btn1.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				// 만들기버튼
				try {
					boolean can = false;
					for (int i = 0; i < edit1.getText().toString().length(); i++) {
						if (edit1.getText().toString().charAt(i) == '{'
								|| edit1.getText().toString().charAt(i) == '}') {
							can = true;
							break;
						}
					}

					if (edit1.getText().toString().length() == 0) {
						Toast.makeText(getApplicationContext(),
								"타이틀 명을 입력해 주세요.", Toast.LENGTH_SHORT).show();
					} else if (edit2.getText().toString().length() == 0) {
						Toast.makeText(getApplicationContext(), "횟수를 입력해 주세요.",
								Toast.LENGTH_SHORT).show();
					} else if (can) {
						Toast.makeText(getApplicationContext(),
								"}나 {는 입력이 불가능합니다.", Toast.LENGTH_SHORT).show();
					} else {
						file = new File(path + "/important.txt");
						read = new FileReader(file);

						String temp = "";
						String sentence = "";
						int k = 0; // 타이틀수
						int j = 0; // 건너간 수
						boolean check = false;
						while (true) {
							int data = read.read();
							if (data == -1)
								break;
							char ch = (char) data;

							if (ch != '}' && j == 1) {
								temp += ch;
							} else {
								if (k == Share.pos) {
									check = true;

								}
								if (check && ch == '}') {
									j++;
								}
								if (ch == '{') {
									k++;
									j = 0;
									check = false;
								}
							}

						}
						goalnumber = Integer.parseInt(temp) + 1;
						read.close();

						read = new FileReader(file);
						k = 0; // 타이틀수
						j = 0; // 건너간 수
						check = false;
						boolean check1 = true;
						while (true) {
							int data = read.read();
							if (data == -1)
								break;
							char ch = (char) data;

							if (k==Share.pos && ch != '}' && j == 1 && check1) {
								sentence += String.valueOf(goalnumber);
								check1 = false;
							}else if(k==Share.pos && ch!='}' &&j==1){
								int uu=0;
							}else {

								if (k == Share.pos) {
									check = true;

								}
								if (check && ch == '}') {
									j++;
								}
								if (ch == '{') {
									k++;
									if (k == Share.pos + 1) {
										// 목표명}총횟수}한수}
										sentence += edit1.getText().toString()
												+ "}"
												+ edit2.getText().toString()
												+ "}0}";
									}
									j = 0;
								}
								sentence += ch;
							}
						}
						read.close();
						write = new FileWriter(file);
						write.write(sentence);
						write.close();

						Intent myIntent = new Intent(getApplicationContext(),
								MissionActivity.class);
						startActivity(myIntent);
						finish();
						if (newGoalDlg != null)
							newGoalDlg.cancel();
					}

				} catch (Exception eo) {

				}

			}
		});

		TextView goalname = (TextView) view.findViewById(R.id.goalname);
		TextView times = (TextView) view.findViewById(R.id.times);
		TextView qjs = (TextView) view.findViewById(R.id.qjs);
		goalname.setTypeface(typeface);
		times.setTypeface(typeface);
		qjs.setTypeface(typeface);
		btn1.setTypeface(typeface);
		btn2.setTypeface(typeface);

	}

	private void reviseDialog(int resId) {
		View view = this.getLayoutInflater().inflate(resId, null);
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setView(view);
		reviseDlg = builder.create();
		reviseDlg.setCanceledOnTouchOutside(true);

		Button btn1 = (Button) view.findViewById(R.id.revise1);
		Button btn2 = (Button) view.findViewById(R.id.delete1);
		btn1.setTypeface(typeface);
		btn2.setTypeface(typeface);
		btn2.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				new AlertDialog.Builder(MissionActivity.this)
						.setTitle("정말로요?")
						.setMessage(
								"한번 지워지면 다신 복구할 수 없어요.\n정말 삭제하시겠어요?\n포기하시려는 것은 아니죠?")
						.setPositiveButton("네",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int which) {

										// 삭제
										// pos <- 몇번째 goal, Share.pos<- 몇번째
										// title
										try {

											read = new FileReader(file);

											String temp = "";
											String temp2 = "";
											String sentence = "";
											int k = 0; // 타이틀수
											int j = 0; // 건너간 수
											boolean check = false;
											while (true) {
												int data = read.read();
												if (data == -1)
													break;
												char ch = (char) data;

												if (ch != '}' && j == 1) {
													temp += ch;
												} else if (ch != '}' && j == 2) {
													temp2 += ch;
												} else {
													if (k == Share.pos) {
														check = true;

													}
													if (check && ch == '}') {
														j++;
													}
													if (ch == '{') {
														k++;
														j = 0;
														check = false;
													}
												}

											}
											goalnumber = Integer.parseInt(temp) - 1;
											int achieve = Integer
													.parseInt(temp2) - 1;
											int achieve2 = achieve + 1;
											read.close();

											read = new FileReader(file);
											k = 0;
											j = 0;
											String tot = "";
											String ach = "";
											while (true) {
												int data = read.read();
												if (data == -1)
													break;
												char ch = (char) data;
												if (k == Share.pos && ch == '}')
													j++;
												if (j == 7 + 3 * pos)
													tot += ch;
												else if (j == 8 + 3 * pos)
													ach += ch;
												if (ch == '{') {
													k++;
													j = 0;
												}
											}
											if (ach.equals(tot)
													&& (!ach.equals("}0"))) {
												achieve2 = achieve;
											}

											read = new FileReader(file);
											k = 0;
											j = 0;

											while (true) {
												int data = read.read();
												if (data == -1)
													break;
												char ch = (char) data;

												if (k == Share.pos) {
													if (ch == '}')
														j++;

												}
												if (!(((6 + 3 * pos <= j) && (j <= 8 + 3 * pos))
														|| (j == 1) || (j == 2))) {
													if (j == 3 && ch == '}') {
														sentence += "}"
																+ String.valueOf(goalnumber)
																+ "}"
																+ String.valueOf(achieve2);
													}
													sentence += ch;
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
												MissionActivity.class);
										startActivity(myIntent);
										finish();

										if (reviseDlg != null)
											reviseDlg.cancel();

									}
								})
						.setNegativeButton("아니요",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int which) {
										if (reviseDlg != null)
											reviseDlg.cancel();
									}
								}).show();

			}
		});
		btn1.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				Toast.makeText(getApplicationContext(),
						"수정은 아쉽게도 타이틀만 지원해요 ㅠ^ㅠ", Toast.LENGTH_SHORT).show();
				if (reviseDlg != null)
					reviseDlg.cancel();
			}
		});

	}

	public void onBackPressed() {
		// your code.
		Intent myIntent = new Intent(getApplicationContext(),
				MainActivity.class);
		startActivity(myIntent);
		finish();
	}
}

class MissionGroup {
	int pict;
	String name;
	String name1;

	MissionGroup(int pict, String name, String name1) {
		this.pict = pict;
		this.name = name;
		this.name1 = name1;
	}

}

class AhroobeAdapterA extends BaseAdapter {

	Context con;
	LayoutInflater inflacter;
	ArrayList<MissionGroup> arD;
	int layout;

	// 생성자
	public AhroobeAdapterA(Context con, int layout, ArrayList<MissionGroup> arD) {
		this.con = con;
		inflacter = (LayoutInflater) con
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.arD = arD;
		this.layout = layout;
	}

	@Override
	public int getCount() {
		return arD.size();
	}

	@Override
	public Object getItem(int position) {
		return arD.get(position).name;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	// 이게중요한건가 ㅇㅇㅇ
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {

		if (convertView == null) {
			convertView = inflacter.inflate(layout, parent, false);
		}

		// 이미지지정
		ImageView img = (ImageView) convertView.findViewById(R.id.image2);
		img.setImageResource(arD.get(position).pict);

		// 글자지정
		TextView txt = (TextView) convertView.findViewById(R.id.mission);
		txt.setText(arD.get(position).name);
		// 글자지정
		TextView txt1 = (TextView) convertView.findViewById(R.id.text4);
		txt1.setText(arD.get(position).name1);

		txt.setTypeface(MainActivity.typeface);
		txt1.setTypeface(MainActivity.typeface);
		

		return convertView;
	}

}
