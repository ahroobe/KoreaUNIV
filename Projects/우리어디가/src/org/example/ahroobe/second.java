package org.example.ahroobe;

import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class second extends Activity {

	ImageView imageview1;
	ImageView imageview2;
	ImageView imageview3;
	ImageView imageview4;
	AlertDialog loadingDlg1 = null;
	ViewFlipper flipper2;
	TextView textview1;
	TextView textview2;
	TextView textview3;
	TextView textview4;
	TextView textview5;
	TextView textview6;
	Document doc1 = null;
	int check;
	private static String rssUrl;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bestitem);
		imageview4 = (ImageView) findViewById(R.id.besttheme);
		if (Sharing.theme == 6) {
			Sharing.temp = Sharing.shopform;
			imageview4.setImageResource(R.drawable.shopping);
		} else if (Sharing.theme == 5) {
			Sharing.temp = Sharing.eatform;
			imageview4.setImageResource(R.drawable.eat);
		} else if (Sharing.theme == 4) {
			Sharing.temp = Sharing.eseckform;
			imageview4.setImageResource(R.drawable.unique);
		} else if (Sharing.theme == 3) {
			Sharing.temp = Sharing.qjsghkform;
			imageview4.setImageResource(R.drawable.city);
		} else if (Sharing.theme == 2) {
			Sharing.temp = Sharing.univform;
			imageview4.setImageResource(R.drawable.university);
		} else {
			Sharing.temp = Sharing.jogform;
			imageview4.setImageResource(R.drawable.walk);
		}

		loadingDlg1(R.layout.loadingdialog);
		loadingDlg1.show();

		int i;

		// �����ҷ�����(����, �ϴû���)
		for (i = 0; i < 5; i++) {
			rssUrl = "http://www.kma.go.kr/wid/queryDFS.jsp?gridx="
					+ Sharing.temp[i].weather_X + "&gridy="
					+ Sharing.temp[i].weather_Y;
			GetXMLTask task = new GetXMLTask();
			task.execute(rssUrl);
		}

		Button button01 = (Button) findViewById(R.id.dataItem03);
		button01.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// ��ġ ���� Ȯ���� ���� ������ �޼ҵ� ȣ��
				Sharing.best = 1;
				Intent myIntent = new Intent(getApplicationContext(),
						Thrid.class);
				startActivity(myIntent);

			}
		});
		Button button02 = (Button) findViewById(R.id.dataItem06);
		button02.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				// ��ġ ���� Ȯ���� ���� ������ �޼ҵ� ȣ��
				Sharing.best = 2;
				Intent myIntent = new Intent(getApplicationContext(),
						Thrid.class);
				startActivity(myIntent);

			}
		});

		Button button03 = (Button) findViewById(R.id.dataItem09);
		button03.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				// ��ġ ���� Ȯ���� ���� ������ �޼ҵ� ȣ��
				Sharing.best = 3;
				Intent myIntent = new Intent(getApplicationContext(),
						Thrid.class);
				startActivity(myIntent);
			}
		});

	}

	private class GetXMLTask extends AsyncTask<String, Void, Document> {

		@Override
		protected Document doInBackground(String... urls) {
			// TODO Auto-generated method stub
			URL url;
			try {
				url = new URL(urls[0]);
				DocumentBuilderFactory dbf = DocumentBuilderFactory
						.newInstance();
				DocumentBuilder db = dbf.newDocumentBuilder(); // XML���� ���� ��ü��
																// ����
				doc1 = db.parse(new InputSource(url.openStream())); // XML������
																	// �Ľ��Ѵ�.
				doc1.getDocumentElement().normalize();

			} catch (Exception e) {
				Toast.makeText(getBaseContext(), "Parsing Error",
						Toast.LENGTH_SHORT).show();
			}
			return doc1;
		}

		@Override
		protected void onPostExecute(Document doc) {

			// data�±װ� �ִ� ��带 ã�Ƽ� ����Ʈ ���·� ���� ��ȯ
			NodeList nodeList = doc.getElementsByTagName("data");
			// data �±׸� ������ ��带 ã��, �������� ��� ������ ��ȯ

			Node node = nodeList.item(Sharing.timestandard); // data������Ʈ ���
			Element fstElmnt = (Element) node;
			NodeList nameList = fstElmnt.getElementsByTagName("reh");
			Element nameElement = (Element) nameList.item(0);
			nameList = nameElement.getChildNodes();
			Sharing.temp[check].moisture = ""
					+ ((Node) nameList.item(0)).getNodeValue();

			NodeList websiteList = fstElmnt.getElementsByTagName("wfKor");
			// <wfKor>����</wfKor> =====> <wfKor> �±��� ù��° �ڽĳ��� TextNode �̰�
			// TextNode�� ���� ����

			Sharing.temp[check].weather = websiteList.item(0).getChildNodes()
					.item(0).getNodeValue();

			NodeList tempElement = fstElmnt.getElementsByTagName("temp");

			Sharing.temp[check].temperature = ""
					+ tempElement.item(0).getChildNodes().item(0)
							.getNodeValue();

			check++;
			super.onPostExecute(doc);

			if (check == 5) {
				if (Sharing.wlrwjqpos) {
					String k;
					sorting(Sharing.temp);
					textview1 = (TextView) findViewById(R.id.dataItem01);
					textview2 = (TextView) findViewById(R.id.dataItem04);
					textview3 = (TextView) findViewById(R.id.dataItem07);
					textview4 = (TextView) findViewById(R.id.moist01);
					textview5 = (TextView) findViewById(R.id.moist02);
					textview6 = (TextView) findViewById(R.id.moist03);

					textview1.setText(Sharing.temp[0].name);
					textview2.setText(Sharing.temp[1].name);
					textview3.setText(Sharing.temp[2].name);

					textview4.setText("��� :" + Sharing.temp[0].temperature
							+ "��");
					textview5.setText("��� :" + Sharing.temp[1].temperature
							+ "��");
					textview6.setText("��� :" + Sharing.temp[2].temperature
							+ "��");
					imageview1 = (ImageView) findViewById(R.id.dataItem02);
					imageview2 = (ImageView) findViewById(R.id.dataItem05);
					imageview3 = (ImageView) findViewById(R.id.dataItem08);
					// ������ ���� ������ �ٸ����ϱ�
					k = Sharing.temp[0].weather;

					if (k.equals("����"))
						imageview1.setImageResource(R.drawable.sun);
					else if (k.equals("�帲"))
						imageview1.setImageResource(R.drawable.dark);
					else if (k.equals("���� ����"))
						imageview1.setImageResource(R.drawable.cloudlittle);
					else if (k.equals("���� ����"))
						imageview1.setImageResource(R.drawable.cloudmany);
					else if (k.equals("��"))
						imageview1.setImageResource(R.drawable.rain);
					else if (k.equals("��"))
						imageview1.setImageResource(R.drawable.snow);
					else
						imageview1.setImageResource(R.drawable.snowrain);
					k = Sharing.temp[1].weather;

					if (k.equals("����"))
						imageview2.setImageResource(R.drawable.sun);
					else if (k.equals("�帲"))
						imageview2.setImageResource(R.drawable.dark);
					else if (k.equals("���� ����"))
						imageview2.setImageResource(R.drawable.cloudlittle);
					else if (k.equals("���� ����"))
						imageview2.setImageResource(R.drawable.cloudmany);
					else if (k.equals("��"))
						imageview2.setImageResource(R.drawable.rain);
					else if (k.equals("��"))
						imageview2.setImageResource(R.drawable.snow);
					else
						imageview2.setImageResource(R.drawable.snowrain);

					k = Sharing.temp[2].weather;
					if (k.equals("����"))
						imageview3.setImageResource(R.drawable.sun);
					else if (k.equals("�帲"))
						imageview3.setImageResource(R.drawable.dark);
					else if (k.equals("���� ����"))
						imageview3.setImageResource(R.drawable.cloudlittle);
					else if (k.equals("���� ����"))
						imageview3.setImageResource(R.drawable.cloudmany);
					else if (k.equals("��"))
						imageview3.setImageResource(R.drawable.rain);
					else if (k.equals("��"))
						imageview3.setImageResource(R.drawable.snow);
					else
						imageview3.setImageResource(R.drawable.snowrain);

					if (loadingDlg1 != null)
						loadingDlg1.cancel();
				} else
					startLocationService();
			}
		}

	}// end inner class - GetXMLTask

	private void loadingDlg1(int resId) {
		View view = this.getLayoutInflater().inflate(resId, null);
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setView(view);
		loadingDlg1 = builder.create();
		flipper2 = (ViewFlipper) view.findViewById(R.id.viewFlipper_details2);
		final int slideTime = 300;// �̰� �����̵� �ð� ��
		if (flipper2 != null) {
			flipper2.setFlipInterval(slideTime);
			flipper2.startFlipping();
		}// �׳� �ø��� ���� �����.

	}

	private void startLocationService() {

		// ��ġ ������ ��ü ����
		LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		GPSListener gpsListener = new GPSListener();

		// GPS�� �̿��� ��ġ ��û
		manager.requestSingleUpdate(LocationManager.GPS_PROVIDER, gpsListener,
				null);

		// ��ġ Ȯ���� �ȵǴ� ��쿡�� �ֱٿ� Ȯ�ε� ��ġ ���� ���� Ȯ��
		try {
			Location lastLocation = manager
					.getLastKnownLocation(LocationManager.GPS_PROVIDER);
			if (lastLocation != null) {
				Double latitude = lastLocation.getLatitude();
				Double longitude = lastLocation.getLongitude();

				Sharing.location_x = latitude;
				Sharing.location_y = longitude;

			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	private class GPSListener implements LocationListener {
		/**
		 * ��ġ ������ Ȯ�ε� �� �ڵ� ȣ��Ǵ� �޼ҵ�
		 */

		public void onLocationChanged(Location location) {

			String k;

			Double latitude = location.getLatitude();
			Double longitude = location.getLongitude();

			Sharing.location_y = latitude;
			Sharing.location_x = longitude;

			sorting(Sharing.temp);
			textview1 = (TextView) findViewById(R.id.dataItem01);
			textview2 = (TextView) findViewById(R.id.dataItem04);
			textview3 = (TextView) findViewById(R.id.dataItem07);
			textview4 = (TextView) findViewById(R.id.moist01);
			textview5 = (TextView) findViewById(R.id.moist02);
			textview6 = (TextView) findViewById(R.id.moist03);

			textview1.setText(Sharing.temp[0].name);
			textview2.setText(Sharing.temp[1].name);
			textview3.setText(Sharing.temp[2].name);

			textview4.setText("��� :" + Sharing.temp[0].temperature + "��");
			textview5.setText("��� :" + Sharing.temp[1].temperature + "��");
			textview6.setText("��� :" + Sharing.temp[2].temperature + "��");
			imageview1 = (ImageView) findViewById(R.id.dataItem02);
			imageview2 = (ImageView) findViewById(R.id.dataItem05);
			imageview3 = (ImageView) findViewById(R.id.dataItem08);
			// ������ ���� ������ �ٸ����ϱ�
			k = Sharing.temp[0].weather;

			if (k.equals("����"))
				imageview1.setImageResource(R.drawable.sun);
			else if (k.equals("�帲"))
				imageview1.setImageResource(R.drawable.dark);
			else if (k.equals("���� ����"))
				imageview1.setImageResource(R.drawable.cloudlittle);
			else if (k.equals("���� ����"))
				imageview1.setImageResource(R.drawable.cloudmany);
			else if (k.equals("��"))
				imageview1.setImageResource(R.drawable.rain);
			else if (k.equals("��"))
				imageview1.setImageResource(R.drawable.snow);
			else
				imageview1.setImageResource(R.drawable.snowrain);
			k = Sharing.temp[1].weather;

			if (k.equals("����"))
				imageview2.setImageResource(R.drawable.sun);
			else if (k.equals("�帲"))
				imageview2.setImageResource(R.drawable.dark);
			else if (k.equals("���� ����"))
				imageview2.setImageResource(R.drawable.cloudlittle);
			else if (k.equals("���� ����"))
				imageview2.setImageResource(R.drawable.cloudmany);
			else if (k.equals("��"))
				imageview2.setImageResource(R.drawable.rain);
			else if (k.equals("��"))
				imageview2.setImageResource(R.drawable.snow);
			else
				imageview2.setImageResource(R.drawable.snowrain);

			k = Sharing.temp[2].weather;
			if (k.equals("����"))
				imageview3.setImageResource(R.drawable.sun);
			else if (k.equals("�帲"))
				imageview3.setImageResource(R.drawable.dark);
			else if (k.equals("���� ����"))
				imageview3.setImageResource(R.drawable.cloudlittle);
			else if (k.equals("���� ����"))
				imageview3.setImageResource(R.drawable.cloudmany);
			else if (k.equals("��"))
				imageview3.setImageResource(R.drawable.rain);
			else if (k.equals("��"))
				imageview3.setImageResource(R.drawable.snow);
			else
				imageview3.setImageResource(R.drawable.snowrain);

			if (loadingDlg1 != null)
				loadingDlg1.cancel();

		}

		public void onProviderDisabled(String provider) {
			Toast.makeText(getApplicationContext(), "GPS�� ���ּ���!!!",
					Toast.LENGTH_SHORT).show();
		}

		public void onProviderEnabled(String provider) {
		}

		public void onStatusChanged(String provider, int status, Bundle extras) {
		}

	}

	public void calculate(jogging[] jog) {

		double a = 0;
		double b = 0;
		double c = 0;
		double d = 0;
		double e = 0;

		for (int j = 0; j < jog.length; j++) {
			a = Math.sqrt((jog[j].latitude - Sharing.location_x)
					* (jog[j].latitude - Sharing.location_x)
					+ (jog[j].longtitude - Sharing.location_y)
					* (jog[j].longtitude - Sharing.location_y));
		
			
			double t = Double.parseDouble(jog[j].temperature);

			if (t == 22)
				b = 33;
			else if (t > 22)
				b = 33 - 2 * (t - 22);
			else
				b = 33 - (22 - t);

			double m = Double.parseDouble(jog[j].moisture);
			if (m == 65)
				c = 50;
			else if (m > 65)
				c = 50 - (m - 65);
			else
				c = 50 - (65 - m);

			// ��ȭ��
			if (Sharing.movie)
				d = jog[j].movie;

			
			// �ǳ�����
			if (jog[j].weather.equals("����")) {

				if (jog[j].in == 0)
					e = 10;
				else if (jog[j].in == 1)
					e = 9;
				else if (jog[j].in == 2)
					e = 8;
				else
					e = 8;
			}

			else if (jog[j].weather.equals("���� ����")) {

				if (jog[j].in == 0)
					e = 8;
				else if (jog[j].in == 1)
					e = 8;
				else if (jog[j].in == 2)
					e = 7;
				else
					e = 7;

			}
			

			else if (jog[j].weather.equals("���� ����")) {

				if (jog[j].in == 0)
					e = 5;
				else if (jog[j].in == 1)
					e = 7;
				else if (jog[j].in == 2)
					e = 7;
				else
					e = 7;
			}

			else if (jog[j].weather.equals("�帲")) {

				if (jog[j].in == 0)
					e = 2;
				else if (jog[j].in == 1)
					e = 4;
				else if (jog[j].in == 2)
					e = 5;
				else
					e = 7;
			}

			else {

				if (jog[j].in == 0)
					e = 0;
				else if (jog[j].in == 1)
					e = 2;
				else if (jog[j].in == 2)
					e = 4;
				else
					e = 7;
			}

			jog[j].calculate = -a*15 + 3 * b +  0.5* c + 1000 * d + 0.4 * e;
		}

	}

	public void sorting(jogging[] jog) {
		// selection sort, find out first,second,third.
		double max = 1;
		int maxindex = 0;
		calculate(jog);
		
		for (int i = 0; i < 3; i++) {
			max = 1;
			for (int j = i; j < jog.length; j++) {

				double k = jog[j].calculate;

				if (k > max) {
					max = k;
					maxindex = j;
				}
			}
			swap(jog, i, maxindex);
		}
		if (loadingDlg1 != null)
			loadingDlg1.cancel();

	}

	public static void swap(jogging[] arr, int i, int j) {
		jogging temp;
		temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

}