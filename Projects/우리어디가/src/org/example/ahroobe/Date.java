package org.example.ahroobe;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import android.app.AlertDialog;
import android.view.View.OnClickListener;

public class Date extends Activity {

	AlertDialog mAutoClosingDlg1 = null;
	FileWriter write1 = null;
	FileReader read1 = null;
	File myfile;
	String path;
	File file;

	String[] xposition = new String[500];
	String[] yposition = new String[500];
	RadioButton rdb1;
	RadioButton rdb2;
	RadioButton rdb3;
	RadioButton rdb4;
	RadioButton rdb5;

	// int please = 0; ��������

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.datestart);
		autoClosingDialog(R.layout.datedialog);
		if (mAutoClosingDlg1 != null && !Sharing.date)
			mAutoClosingDlg1.show();
		Sharing.mNM = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		Sharing.mNoti = new NotificationCompat.Builder(getApplicationContext())
				.setContentTitle("�츮 ���?").setTicker("����Ʈ ����")
				.setContentText("����Ʈ ���� ��. ������ �������� �����ּ���!")
				.setSmallIcon(R.drawable.codnsicon).setAutoCancel(false)
				.build();
		
		
		myfile = getDir("dataaa", Activity.MODE_PRIVATE);
		path = myfile.getAbsolutePath();
		file = new File(path + "/ahroobepath.txt");

		if (!Sharing.date) {
			try {
				write1 = new FileWriter(file);
				write1.write("");
				write1.close();
			} catch (Exception eo) {

			}

			
		}

		Button term = (Button) findViewById(R.id.terminate);
		Button wkcnl = (Button) findViewById(R.id.mappp);
		Button pause = (Button) findViewById(R.id.pause);
		Button rest = (Button) findViewById(R.id.restart);
		Button setting = (Button) findViewById(R.id.resetting);

		pause.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				if (!Sharing.pause) {
					Sharing.pause = true;
					Sharing.manager.removeUpdates(Sharing.gpsListener);
					Toast.makeText(getApplicationContext(), "�Ͻ����� ��",
							Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(getApplicationContext(), "�̹� �Ͻ����� �� �����Դϴ�.",
							Toast.LENGTH_SHORT).show();
				}
			}
		});

		setting.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				if (Sharing.pause) {
					if (mAutoClosingDlg1 != null)
						mAutoClosingDlg1.show();
					Sharing.pause=false;
					
					
				} else {
					Toast.makeText(getApplicationContext(), "�Ͻ����� �� �缳���ϼ���.",
							Toast.LENGTH_SHORT).show();
				}
			}
		});

		rest.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if (Sharing.pause) {
					Sharing.pause = false;
					startLocationService();
					Toast.makeText(getApplicationContext(), "�����",
							Toast.LENGTH_SHORT).show();
				} else {
					Toast.makeText(getApplicationContext(), "�̹� ��� ���Դϴ�.",
							Toast.LENGTH_SHORT).show();
				}
			}
		});

		term.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				new AlertDialog.Builder(Date.this)
						.setTitle("���� ��� ����")
						.setMessage("�����Ͻø� ���� �����Ͱ� ������ϴ�. ���� �����Ͻðڽ��ϱ�?")
						.setPositiveButton("����",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int which) {
										Sharing.date = false;
										if (!Sharing.pause) {
											Sharing.manager
													.removeUpdates(Sharing.gpsListener);
										}
										Sharing.mNM.cancel(1);

									}
								})
						.setNegativeButton("���",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int which) {

									}
								}).show();

			}
		});
		wkcnl.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				for (int i = 0; i < 100; i++) {
					xposition[i] = "";
					yposition[i] = "";
				}
				Sharing.wkcnl = true;
				int i = 0;
				int j = 0;
				int k = 1;
				try {
					if (file.exists()) {

						read1 = new FileReader(file);
						while (true) {
							int data = read1.read();
							if (data == -1)
								break;
							char ch = (char) data;

							if (ch != '\n' && ch != ' ') {
								if (k % 2 == 1)
									yposition[j] += ch;
								else
									xposition[i] += ch;
							} else if (ch == '\n') {
								j++;
								i++;
								k++;

							} else {
								k++;
							}
						}
						k = i;
						read1.close();
					}
				} catch (Exception e) {

				}
				Sharing.numberofwkcnl = k;
				if (xposition != null && yposition != null && k > 1) {

					for (i = 0; i < k; i++) {
						Sharing.xpath[i] = Double.parseDouble(xposition[i]);
						Sharing.ypath[i] = Double.parseDouble(yposition[i]);
					}

					Intent myIntent = new Intent(getApplicationContext(),
							NaverMap.class);
					startActivity(myIntent);

				} else
					Toast.makeText(getApplicationContext(),
							"������ �ð��� ������ Ȯ���� �����մϴ�.", Toast.LENGTH_SHORT)
							.show();
			}
		});

	}

	private void autoClosingDialog(int resId) {
		View view = this.getLayoutInflater().inflate(R.layout.datedialog, null);
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setView(view);
		mAutoClosingDlg1 = builder.create();
		mAutoClosingDlg1.setCanceledOnTouchOutside(false);
		Button btn1 = (Button) view.findViewById(R.id.apply1);
		rdb2 = (RadioButton) view.findViewById(R.id.second30);
		rdb3 = (RadioButton) view.findViewById(R.id.minute1);
		rdb4 = (RadioButton) view.findViewById(R.id.minute3);
		rdb5 = (RadioButton) view.findViewById(R.id.minute5);
		btn1.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if (mAutoClosingDlg1 != null) {

					if (rdb2.isChecked())
						Sharing.timegap = 30000;
					else if (rdb3.isChecked())
						Sharing.timegap = 60000;
					else if (rdb4.isChecked())
						Sharing.timegap = 180000;
					else if (rdb5.isChecked())
						Sharing.timegap = 300000;
					else
						Toast.makeText(getApplicationContext(),
								"��� �ϳ��� �������ּ���.", Toast.LENGTH_SHORT).show();

					startLocationService();
					mAutoClosingDlg1.hide();

				}
			}
		});

	}

	private void startLocationService() {
		Sharing.date = true;
		// ��ġ ������ ��ü ����
		Sharing.manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		Sharing.gpsListener = new GPSListener1();
		// GPS�� �̿��� ��ġ ��û
		Sharing.manager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
				Sharing.timegap, 0, Sharing.gpsListener);
		Sharing.mNM.notify(1, Sharing.mNoti);
		// ��ġ Ȯ���� �ȵǴ� ��쿡�� �ֱٿ� Ȯ�ε� ��ġ ���� ���� Ȯ��
		try {
			Location lastLocation = Sharing.manager
					.getLastKnownLocation(LocationManager.GPS_PROVIDER);
			if (lastLocation != null) {
				Double latitude = lastLocation.getLatitude();
				Double longitude = lastLocation.getLongitude();

			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public class GPSListener1 implements LocationListener {
		/**
		 * ��ġ ������ Ȯ�ε� �� �ڵ� ȣ��Ǵ� �޼ҵ�
		 */
		public void onLocationChanged(Location location) {
			Double latitude = location.getLatitude();
			Double longitude = location.getLongitude();

			try {
				write1 = new FileWriter(file, true);
				write1.write(latitude + " " + longitude + "\n");
				write1.close();

				/*
				 * �������� please++; if (please > 100) { Sharing.date = false;
				 * Sharing.manager.removeUpdates(Sharing.gpsListener);
				 * Sharing.mNM.cancel(1); }
				 */
			} catch (Exception eo) {

			}

		}

		public void onProviderDisabled(String provider) {
			Toast.makeText(getApplicationContext(), "GPS�� ���ּ���!!!",
					Toast.LENGTH_SHORT).show();
			Sharing.mNM.cancel(1);
			Sharing.date=false;

		}

		public void onProviderEnabled(String provider) {
		}

		public void onStatusChanged(String provider, int status, Bundle extras) {
		}

	}

}
