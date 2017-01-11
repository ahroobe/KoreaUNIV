package org.example.ahroobe;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	AlertDialog mAutoClosingDlg = null;
	AlertDialog positionDlg = null;
	AlertDialog alt = null;
	CheckBox chb = null;
	CheckBox chb2 = null;
	CheckBox chb3 = null;
	CheckBox chb4 = null;
	CheckBox chb5 = null;
	boolean fail = false;
	RadioButton rdb1 = null;
	RadioButton rdb2 = null;
	RadioButton rdb3 = null;
	RadioButton rdb4 = null;
	String[] jooso = null;
	double[] lati = null;
	double[] longi = null;
	int check = 1;
	int size = 0;
	TextView add = null;

	Geocoder gc;
	EditText edt = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.firstdisplay);
		startActivity(new Intent(this, FirstSplash.class));
		Locale.setDefault(Locale.KOREA); // = ko_KO, 디폴트로 되어 있으면 말고
		gc = new Geocoder(getApplicationContext(), Locale.getDefault());
		enableGPSSetting();
		autoClosingDialog(R.layout.dialog);
		positionDialog(R.layout.geocoder);

		Button test = (Button) findViewById(R.id.test);
		Button test2 = (Button) findViewById(R.id.test1);
		Button test3 = (Button) findViewById(R.id.test2);
		Button test4 = (Button) findViewById(R.id.test3);
		Button test5 = (Button) findViewById(R.id.test4);
		Button test6 = (Button) findViewById(R.id.test5);
		Button test7 = (Button) findViewById(R.id.datego);
		Button test8 = (Button) findViewById(R.id.random);
		Button test9 = (Button) findViewById(R.id.seeall);
		Button test10 = (Button) findViewById(R.id.help);
		test.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Sharing.theme = 1;
				if (Sharing.wlrwjqpos && positionDlg != null)
					positionDlg.show();
				else {
					Intent myIntent = new Intent(getApplicationContext(),
							second.class);
					startActivity(myIntent);
				}
			}
		});

		test2.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Sharing.theme = 2;
				if (Sharing.wlrwjqpos && positionDlg != null)
					positionDlg.show();
				else {
					Intent myIntent = new Intent(getApplicationContext(),
							second.class);
					startActivity(myIntent);
				}
			}
		});

		test3.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Sharing.theme = 3;
				if (Sharing.wlrwjqpos && positionDlg != null)
					positionDlg.show();
				else {
					Intent myIntent = new Intent(getApplicationContext(),
							second.class);
					startActivity(myIntent);
				}
			}
		});

		test4.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Sharing.theme = 4;
				if (Sharing.wlrwjqpos && positionDlg != null)
					positionDlg.show();
				else {
					Intent myIntent = new Intent(getApplicationContext(),
							second.class);
					startActivity(myIntent);
				}
			}
		});

		test5.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Sharing.theme = 5;
				if (Sharing.wlrwjqpos && positionDlg != null)
					positionDlg.show();
				else {
					Intent myIntent = new Intent(getApplicationContext(),
							second.class);
					startActivity(myIntent);
				}
			}
		});

		test6.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Sharing.theme = 6;
				if (Sharing.wlrwjqpos && positionDlg != null)
					positionDlg.show();
				else {
					Intent myIntent = new Intent(getApplicationContext(),
							second.class);
					startActivity(myIntent);
				}
			}
		});

		test7.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent myIntent = new Intent(getApplicationContext(),
						Date.class);
				startActivity(myIntent);
			}
		});

		test8.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Sharing.theme = (int) (Math.random() * 6) + 1;
				if (Sharing.wlrwjqpos && positionDlg != null)
					positionDlg.show();
				else {
					Intent myIntent = new Intent(getApplicationContext(),
							second.class);
					startActivity(myIntent);
				}
			}
		});

		test9.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Sharing.all = true;
				Intent myIntent = new Intent(getApplicationContext(),
						NaverMap.class);
				startActivity(myIntent);
			}
		});

		test10.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				Intent myIntent = new Intent(getApplicationContext(),
						Geocoder11.class);
				startActivity(myIntent);
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu, menu);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.actionbar_search1 && mAutoClosingDlg != null) {
			mAutoClosingDlg.show();
		}
		return super.onOptionsItemSelected(item);
	}

	private void autoClosingDialog(int resId) {
		View view = this.getLayoutInflater().inflate(R.layout.dialog, null);
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setView(view);
		mAutoClosingDlg = builder.create();
		mAutoClosingDlg.setCanceledOnTouchOutside(true);
		Button btn = (Button) view.findViewById(R.id.button1);
		Button btn2 = (Button) view.findViewById(R.id.button2);
		chb = (CheckBox) view.findViewById(R.id.radioButton1);
		chb2 = (CheckBox) view.findViewById(R.id.radioButton2);
		chb3 = (CheckBox) view.findViewById(R.id.radioButton3);
		chb4 = (CheckBox) view.findViewById(R.id.radioButton4);

		rdb1 = (RadioButton) view.findViewById(R.id.datenow);
		rdb2 = (RadioButton) view.findViewById(R.id.date3hour);
		rdb3 = (RadioButton) view.findViewById(R.id.date6hour);
		rdb4 = (RadioButton) view.findViewById(R.id.date9hour);

		chb.setChecked(Sharing.distance);
		chb2.setChecked(Sharing.sun);
		chb3.setChecked(Sharing.movie);
		chb4.setChecked(Sharing.wlrwjqpos);

		btn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if (mAutoClosingDlg != null) {
					Sharing.distance = chb.isChecked();
					Sharing.sun = chb2.isChecked();
					Sharing.movie = chb3.isChecked();
					Sharing.wlrwjqpos = chb4.isChecked();

					if (rdb1.isChecked())
						Sharing.timestandard = 0;
					else if (rdb2.isChecked())
						Sharing.timestandard = 1;
					else if (rdb3.isChecked())
						Sharing.timestandard = 2;
					else
						Sharing.timestandard = 3;

					mAutoClosingDlg.hide();

				}
			}
		});
		btn2.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if (mAutoClosingDlg != null)
					mAutoClosingDlg.hide();
			}
		});
	}

	private void positionDialog(int resId) {
		View view = this.getLayoutInflater().inflate(R.layout.geocoder, null);
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setView(view);
		positionDlg = builder.create();
		positionDlg.setCanceledOnTouchOutside(false);
		Button cancc = (Button) view.findViewById(R.id.cancel2);

		Button find = (Button) view.findViewById(R.id.finding);
		edt = (EditText) view.findViewById(R.id.jooso);

		find.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				positionDlg.cancel();
				String searchStr = edt.getText().toString();
				searchLocation(searchStr);
				
			}
		});

		cancc.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if (positionDlg != null)
					positionDlg.cancel();
			}
		});

	}

	private void altDialog(int resId) {
		
		View view = this.getLayoutInflater().inflate(R.layout.altdialog, null);
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setView(view);
		alt = builder.create();
		alt.setCanceledOnTouchOutside(false);

		Button btn11 = (Button) view.findViewById(R.id.yesright);
		Button btn22 = (Button) view.findViewById(R.id.next);
		add = (TextView) view.findViewById(R.id.address);

		if (check == 1)
			add.setText("찾으시는 장소가\n"+jooso[check - 1] + "인가요?");

		btn11.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				// yes
				Sharing.location_x = longi[check - 1];
				Sharing.location_y = lati[check - 1];
				
				if (alt != null)
					alt.cancel();
				
				Intent myIntent = new Intent(getApplicationContext(),
						second.class);
				startActivity(myIntent);

				
			}
		});

		btn22.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				// next

				if (check == size) {
					Toast.makeText(getApplicationContext(), "찾지 못하였습니다.",
							Toast.LENGTH_SHORT).show();
					if (alt != null)
						alt.cancel();
				} else {
					if (alt != null)
						alt.cancel();
					if (alt != null) {
						check++;
						add.setText("찾으시는 장소가\n"+jooso[check - 1] + "인가요?");
						alt.show();
					}
				}
			}
		});

	}

	private void searchLocation(String searchStr) {

		List<Address> addressList = null;

		try {
			addressList = gc.getFromLocationName(searchStr, 3);

			if (addressList != null) {

				if (addressList.size() == 0) {
					new AlertDialog.Builder(this)
							.setTitle("찾기 실패")
							.setMessage("아무런 장소도 찾지 못하였습니다.")
							.setNeutralButton("닫기",
									new DialogInterface.OnClickListener() {

										@Override
										public void onClick(
												DialogInterface dialog,
												int which) {

										}
									}).show();

				} else {

					jooso = new String[addressList.size()];
					lati = new double[addressList.size()];
					longi = new double[addressList.size()];
					size = addressList.size();
					for (int i = 0; i < addressList.size(); i++) {

						jooso[i] = "";
						lati[i] = 0;
						longi[i] = 0;
					}
					for (int i = 0; i < addressList.size(); i++) {

						Address outAddr = addressList.get(i);
						int addrCount = outAddr.getMaxAddressLineIndex() + 1;

						for (int k = 0; k < addrCount; k++) {

							jooso[i] += outAddr.getAddressLine(k);
						}
						lati[i] = outAddr.getLatitude();
						longi[i] = outAddr.getLongitude();

					}
					check = 1;
					altDialog(R.layout.altdialog);
					if (alt != null)
						alt.show();

				}
			}
		} catch (IOException ex) {
			Log.d("Geocoding", "Exception" + ex.toString());
		}
	}

	private void enableGPSSetting() {

		ContentResolver res = getContentResolver();

		boolean gpsEnabled = Settings.Secure.isLocationProviderEnabled(res,
				LocationManager.GPS_PROVIDER);
		if (!gpsEnabled) {
			new AlertDialog.Builder(this)
					.setTitle("GPS 설정")
					.setMessage("GPS가 필요한 서비스입니다. \nGPS를 켜시겠습니까?")
					.setPositiveButton("GPS 켜기",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
									Intent intent = new Intent(
											Settings.ACTION_LOCATION_SOURCE_SETTINGS);
									startActivity(intent);

								}
							})
					.setNegativeButton("닫기",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {

								}
							}).show();
		}

	}

}