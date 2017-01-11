package com.ahroobe.bucket;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
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
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	AhroobeAdapter adapter;
	ArrayList<TestGroup> ahroobe;
	static Typeface typeface;
	static ListView list;
	AlertDialog reviseDlg = null;
	AlertDialog revDlg = null;
	int pos;
	ScrollView scroll;
	String path;
	File myfile;
	File file;
	FileWriter write;
	FileReader read;
	int totaltitlenumber;
	String[] name;
	int[] pict;
	String[] name1;
	String[] name2;
	String[] year;
	String[] month;
	String[] day;
	EditText edit;
	int totaltitlenumber1;
	int year1;
	int month1;
	int day1;
	int y = 0, m = 0, d = 0;
	EditText edit1;
	DatePicker date1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		TestGroup test;
		typeface = Typeface.createFromAsset(getAssets(), "fonts/yagan.ttf");
		myfile = getDir("alldata", Activity.MODE_PRIVATE);
		path = myfile.getAbsolutePath();
		file = new File(path + "/totalnumber.txt");
		ahroobe = new ArrayList<TestGroup>();
		try {
			if (file.exists()) {
				String temp = "";
				read = new FileReader(file);
				while (true) {
					int data = read.read();
					if (data == -1)
						break;
					temp += (char) data;
				}
				totaltitlenumber = Integer.parseInt(temp);
				read.close();
			} else {
				write = new FileWriter(file);
				write.write("0");
				write.close();
				totaltitlenumber = 0;
			}

		} catch (Exception eo) {

		}

		if (totaltitlenumber != 0) {

			name = new String[totaltitlenumber];
			name1 = new String[totaltitlenumber];
			name2 = new String[totaltitlenumber];
			year = new String[totaltitlenumber];
			month = new String[totaltitlenumber];
			day = new String[totaltitlenumber];
			pict = new int[totaltitlenumber];

			try {

				file = new File(path + "/important.txt");
				if (file.exists()) {
					read = new FileReader(file);
					String temp = "";
					int k = 0;// {수
					int j = 0; // }수
					while (true) {

						int data = read.read();

						if (data == -1) {
							break;
						}
						char ch = (char) data;
						if (ch == '{') {
							if (j == 5) {
								day[k] = temp;
							}

							int tot = Integer.parseInt(name1[k]);
							int ach = Integer.parseInt(name2[k]);
							int rate=0;
							if(tot!=0){
								rate=100*ach/tot;
							}
							pict[k] = R.drawable.unrank;
							if(rate<50){
								pict[k]=R.drawable.unrank;
							}else if(rate<70){
								pict[k]=R.drawable.bronze;
							}else if(rate<100){
								pict[k]=R.drawable.silver;
							}else
								pict[k]=R.drawable.gold;
							
							Calendar today = Calendar.getInstance();
							Calendar dday=Calendar.getInstance();
							dday.set(Integer.parseInt(year[k]),Integer.parseInt(month[k]),Integer.parseInt(day[k]));
							long ddday = dday.getTimeInMillis()/86400000;
							long tday = today.getTimeInMillis()/86400000;
							Calendar please = Calendar.getInstance();
							please.set(0,0,0);
							long so = please.getTimeInMillis()/86400000;
							long cal = tday-ddday;
							String d="";
							if(cal==0){
								d="-day";
							}else if(cal<0){
								d=cal+"";
							}else if(ddday==so){
								d="-∞";
							}else if(cal>0){
								d="+"+cal;
							}
							test = new TestGroup(pict[k], name[k], "달성률: "+rate+"%",
									"D"+d, year[k], month[k], day[k]);
							ahroobe.add(test);

							j = 0;
							temp = "";
							k++;
						} else if (ch == '}') {
							j++;
							if (j == 1) {
								name[k] = temp;
								temp = "";

							} else if (j == 2) {
								name1[k] =  temp;
								temp = "";
							} else if (j == 3) {
								name2[k] =  temp;
								temp = "";
							} else if (j == 4) {
								year[k] = temp;
								temp = "";
							} else if (j == 5) {
								month[k] = temp;
								temp = "";
							} else if (j == 6) {
								day[k] = temp;
								temp = "";
							}

						} else {
							temp += ch;
						}

					}

				}
			} catch (Exception eo) {

			}

		}
		
		revDialog(R.layout.activity_newtitle);

		adapter = new AhroobeAdapter(this, R.layout.listitem, ahroobe);
		TextView txtt = (TextView)findViewById(R.id.explain);
		txtt.setTypeface(typeface);
		list = (ListView) findViewById(R.id.listView1);
		list.setAdapter(adapter);
		scroll = (ScrollView) findViewById(R.id.scroll1);

		list.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				scroll.requestDisallowInterceptTouchEvent(true);
				return false;
			}

		});

		list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Share.realtitle = name[position];
				Share.pos=position;
				Intent myIntent = new Intent(getApplicationContext(),
						MissionActivity.class);
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
		
		reviseDialog(R.layout.simpledialog);

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

			Intent myIntent = new Intent(getApplicationContext(),
					NewTitleActivity.class);
			startActivity(myIntent);
			finish();
			
		}
		return super.onOptionsItemSelected(item);
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
				new AlertDialog.Builder(MainActivity.this)
						.setTitle("정말로요?")
						.setMessage("한번 지워지면 다신 복구할 수 없어요. \n정말 삭제하시겠어요?")
						.setPositiveButton("네",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int which) {

										if (reviseDlg != null)
											reviseDlg.cancel();

										// 삭제 시작해보자!!! pos -> 몇번째인지.

										try {
											file = new File(path
													+ "/totalnumber.txt");
											read = new FileReader(file);

											String temp = "";
											while (true) {
												int data = read.read();
												if (data == -1)
													break;
												temp += (char) data;
											}
											int k = Integer.parseInt(temp) - 1;
											read.close();

											write = new FileWriter(file);
											write.write(String.valueOf(k));
											write.close();

											file = new File(path
													+ "/important.txt");
											read = new FileReader(file);
											int j = 0; // 몇번째?
											boolean start = false;
											String after = "";
											while (true) {
												int data = read.read();
												if (data == -1)
													break;
												char ch = (char) data;

												if (j == pos)
													start = true;
												if (!start)
													after += ch;

												if (ch == '{') {
													j++;
													start = false;
												}
											}
											read.close();
											write = new FileWriter(file);
											write.write(after);
											write.close();
											reviseDlg.cancel();
											Intent myIntent = new Intent(
													getApplicationContext(),
													MainActivity.class);
											startActivity(myIntent);
											finish();

										} catch (Exception eo) {

										}

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

				revDlg.show();
				reviseDlg.cancel();
			}
		});

	}

	private void revDialog(int resId) {
		View view = this.getLayoutInflater().inflate(resId, null);
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setView(view);
		revDlg = builder.create();
		revDlg.setCanceledOnTouchOutside(true);

		Button btn1 = (Button) view.findViewById(R.id.make1);
		Button btn2 = (Button) view.findViewById(R.id.cancel1);
		btn1.setText("수　정");
		btn1.setTypeface(typeface);
		btn2.setTypeface(typeface);
		TextView txt = (TextView) view.findViewById(R.id.aaa);
		txt.setTypeface(MainActivity.typeface);
		TextView txt2 = (TextView) view.findViewById(R.id.bbb);
		txt2.setTypeface(MainActivity.typeface);
		TextView txt3 = (TextView) view.findViewById(R.id.ccc);
		txt3.setTypeface(MainActivity.typeface);
		edit1 = (EditText) view.findViewById(R.id.titlename);
		date1 = (DatePicker) view.findViewById(R.id.datePicker1);
		date1.init(2015, 1,
				1, new OnDateChangedListener() {
					@Override
					public void onDateChanged(DatePicker view, int year1,
							int month1, int day1) {
						y = year1;
						m = month1;
						d = day1;
					}
				});
		
		btn1.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				
				
				// 수정
				String changed = "";
				String cy, cm, cd;
				if (y == 0 && m == 0 && d == 0) {
					cy = "2015";
					cm = "1";
					cd = "1";
				} else {
					cy = String.valueOf(y);
					cm = String.valueOf(m);
					cd = String.valueOf(d);
				}
				
				if (edit1.getText().toString().length()==0) {
					Toast.makeText(getApplicationContext(), "타이틀 명을 입력해 주세요.",
							Toast.LENGTH_SHORT).show();
				} else {
					// 자이제시작이야.내꿈을
					try {
						changed=edit1.getText().toString();
					
						file = new File(path + "/important.txt");
						read = new FileReader(file);
						int j = 0; // 몇번째?
						int k = 0; // 타이틀이랑 년,월,일 찾기
						boolean start = false;
						String after = "";
						while (true) {
							
							int data = read.read();
							if (data == -1)
								break;
							char ch = (char) data;

							if (j == pos) {
								if (k == 0) {
									start = true;
								} else if (k == 3) {
									// 년
									start = true;
								} else if (k == 4) {
									// 월
									start = true;
								} else if (k == 5) {
									// 일
									start = true;
								} else {
									start = false;
								}
							}

							if (ch == '}' && j == pos) {
								if (k == 0) {
									after += changed + "}";
								} else if (k == 3) {
									// 년
									after += cy + "}";
								} else if (k == 4) {
									// 월
									after += cm + "}";
								} else if (k == 5) {
									after+=cd+"}";
								}
								k++;
							}

							if (!start)
								after += ch;

							if (ch == '{') {
								if (k == 5) {
									after += cd+"{";
								}
								j++;
								k = 0;
								start = false;
							}
						}
						read.close();
						write = new FileWriter(file);
						write.write(after);
						write.close();
						
						Intent myIntent = new Intent(
								getApplicationContext(),
								MainActivity.class);
						startActivity(myIntent);
						finish();
					} catch (Exception eo) {

					}
				}
			}
		});

		btn2.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				// 취소
				revDlg.cancel();
			}
		});

	}
}

class TestGroup {
	int pict;
	String name;
	String name1;
	String name2;
	String year;
	String month;
	String day;

	TestGroup(int pict, String name, String name1, String name2, String year,
			String month, String day) {
		this.pict = pict;
		this.name = name;
		this.name1 = name1;
		this.name2 = name2;
		this.year = year;
		this.month = month;
		this.day = day;
	}

}

class AhroobeAdapter extends BaseAdapter {

	Context con;
	LayoutInflater inflacter;
	ArrayList<TestGroup> arD;
	int layout;

	// 생성자
	public AhroobeAdapter(Context con, int layout, ArrayList<TestGroup> arD) {
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
		ImageView img = (ImageView) convertView.findViewById(R.id.image1);
		img.setImageResource(arD.get(position).pict);
		// 글자지정
		TextView txt = (TextView) convertView.findViewById(R.id.text1);
		txt.setText(arD.get(position).name);
		// 글자지정
		TextView txt1 = (TextView) convertView.findViewById(R.id.text2);
		txt1.setText(arD.get(position).name1);
		// 글자지정
		TextView txt2 = (TextView) convertView.findViewById(R.id.text3);
		txt2.setText(arD.get(position).name2);

		txt.setTypeface(MainActivity.typeface);
		txt1.setTypeface(MainActivity.typeface);
		txt2.setTypeface(MainActivity.typeface);

		return convertView;
	}

}
