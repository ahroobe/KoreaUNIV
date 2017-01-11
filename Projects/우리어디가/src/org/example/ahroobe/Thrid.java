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
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

public class Thrid extends Activity {

	Document doc = null;
	LinearLayout layout = null;
	String[] temp = new String[4];
	String[] weather = new String[4];
	ImageView weatherimg = null;
	ImageView weatherimg2 = null;
	ImageView weatherimg3 = null;
	ImageView weatherimg4 = null;
	TextView temptx = null;
	TextView temptx2 = null;
	TextView temptx3 = null;
	TextView temptx4 = null;
	AlertDialog loadingDlg = null;
	ViewFlipper flipper2;
	ViewFlipper flipper3;
	private static String rssUrl;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		loadingDlg(R.layout.loadingdialog);
		setContentView(R.layout.specific);
		loadingDlg.show();
		if(Sharing.wkcnl)
			Toast.makeText(getApplicationContext(), "자취 기록중엔 지도 정보를 볼 수 없어요.",
					Toast.LENGTH_SHORT).show();
		ImageView img1 = (ImageView)findViewById(R.id.specificpic1);
		ImageView img2 = (ImageView)findViewById(R.id.specificpic2);
		ImageView img3 = (ImageView)findViewById(R.id.explain);

		img1.setImageResource(Sharing.temp[Sharing.best-1].picture1);
		img2.setImageResource(Sharing.temp[Sharing.best-1].picture2);
		img3.setImageResource(Sharing.temp[Sharing.best-1].explain);
		
		rssUrl = "http://www.kma.go.kr/wid/queryDFS.jsp?gridx="
				+ Sharing.temp[Sharing.best - 1].weather_X + "&gridy="
				+ Sharing.temp[Sharing.best - 1].weather_Y;
		GetXMLTask task = new GetXMLTask();
		task.execute(rssUrl);
		flipper3 = (ViewFlipper) findViewById(R.id.viewFlipper_details1);
		final int slideTime = 3000;

		flipper3.setFlipInterval(slideTime);
		flipper3.startFlipping();

		Button button3 = (Button) findViewById(R.id.mapp);
		button3.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// 위치 정보 확인을 위해 정의한 메소드 호출
				Intent myIntent = new Intent(getApplicationContext(),
						NaverMap.class);
				startActivity(myIntent);
			}
		});


	}

	private void loadingDlg(int resId) {
		View view = this.getLayoutInflater().inflate(resId, null);
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setView(view);
		loadingDlg = builder.create();
		flipper2 = (ViewFlipper) view.findViewById(R.id.viewFlipper_details2);
		final int slideTime = 300;// 이게 슬라이드 시간 텀
		if (flipper2 != null) {
			flipper2.setFlipInterval(slideTime);
			flipper2.startFlipping();
		}// 그냥 플리퍼 쓰는 방법임.

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
				DocumentBuilder db = dbf.newDocumentBuilder(); // XML문서 빌더 객체를
																// 생성
				doc = db.parse(new InputSource(url.openStream())); // XML문서를
																	// 파싱한다.
				doc.getDocumentElement().normalize();

			} catch (Exception e) {
				Toast.makeText(getBaseContext(), "Parsing Error",
						Toast.LENGTH_SHORT).show();
			}
			return doc;
		}

		@Override
		protected void onPostExecute(Document doc) {

			weatherimg = (ImageView) findViewById(R.id.nowweather);
			weatherimg2 = (ImageView) findViewById(R.id.weather3);
			weatherimg3 = (ImageView) findViewById(R.id.weather6);
			weatherimg4 = (ImageView) findViewById(R.id.weather9);
			temptx = (TextView) findViewById(R.id.nowtemp);
			temptx2 = (TextView) findViewById(R.id.temp3);
			temptx3 = (TextView) findViewById(R.id.temp6);
			temptx4 = (TextView) findViewById(R.id.temp9);
			// data태그가 있는 노드를 찾아서 리스트 형태로 만들어서 반환
			NodeList nodeList = doc.getElementsByTagName("data");
			// data 태그를 가지는 노드를 찾음, 계층적인 노드 구조를 반환

			for (int i = 0; i < 4; i++) {

				Node node = nodeList.item(i); // data엘리먼트 노드
				Element fstElmnt = (Element) node;
				NodeList nameList = fstElmnt.getElementsByTagName("temp");
				Element nameElement = (Element) nameList.item(0);
				nameList = nameElement.getChildNodes();
				temp[i] = "기온 " + ((Node) nameList.item(0)).getNodeValue()
						+ "도";

				NodeList websiteList = fstElmnt.getElementsByTagName("wfKor");
				// <wfKor>맑음</wfKor> =====> <wfKor> 태그의 첫번째 자식노드는 TextNode 이고
				// TextNode의 값은 맑음

				weather[i] = websiteList.item(0).getChildNodes().item(0)
						.getNodeValue()
						+ "";

			}

			if (weather[0].equals("맑음"))
				weatherimg.setImageResource(R.drawable.sun);
			else if (weather[0].equals("흐림"))
				weatherimg.setImageResource(R.drawable.dark);
			else if (weather[0].equals("구름 조금"))
				weatherimg.setImageResource(R.drawable.cloudlittle);
			else if (weather[0].equals("구름 많음"))
				weatherimg.setImageResource(R.drawable.cloudmany);
			else if (weather[0].equals("비"))
				weatherimg.setImageResource(R.drawable.rain);
			else if (weather[0].equals("눈"))
				weatherimg.setImageResource(R.drawable.snow);
			else
				weatherimg.setImageResource(R.drawable.snowrain);

			if (weather[1].equals("맑음"))
				weatherimg2.setImageResource(R.drawable.sun);
			else if (weather[1].equals("흐림"))
				weatherimg2.setImageResource(R.drawable.dark);
			else if (weather[1].equals("구름 조금"))
				weatherimg2.setImageResource(R.drawable.cloudlittle);
			else if (weather[1].equals("구름 많음"))
				weatherimg2.setImageResource(R.drawable.cloudmany);
			else if (weather[1].equals("비"))
				weatherimg2.setImageResource(R.drawable.rain);
			else if (weather[1].equals("눈"))
				weatherimg2.setImageResource(R.drawable.snow);
			else
				weatherimg2.setImageResource(R.drawable.snowrain);

			if (weather[2].equals("맑음"))
				weatherimg3.setImageResource(R.drawable.sun);
			else if (weather[2].equals("흐림"))
				weatherimg3.setImageResource(R.drawable.dark);
			else if (weather[2].equals("구름 조금"))
				weatherimg3.setImageResource(R.drawable.cloudlittle);
			else if (weather[2].equals("구름 많음"))
				weatherimg3.setImageResource(R.drawable.cloudmany);
			else if (weather[2].equals("비"))
				weatherimg3.setImageResource(R.drawable.rain);
			else if (weather[2].equals("눈"))
				weatherimg3.setImageResource(R.drawable.snow);
			else
				weatherimg3.setImageResource(R.drawable.snowrain);

			if (weather[3].equals("맑음"))
				weatherimg4.setImageResource(R.drawable.sun);
			else if (weather[3].equals("흐림"))
				weatherimg4.setImageResource(R.drawable.dark);
			else if (weather[3].equals("구름 조금"))
				weatherimg4.setImageResource(R.drawable.cloudlittle);
			else if (weather[3].equals("구름 많음"))
				weatherimg4.setImageResource(R.drawable.cloudmany);
			else if (weather[3].equals("비"))
				weatherimg4.setImageResource(R.drawable.rain);
			else if (weather[3].equals("눈"))
				weatherimg4.setImageResource(R.drawable.snow);
			else
				weatherimg4.setImageResource(R.drawable.snowrain);

			temptx.setText(temp[0]);
			temptx2.setText(temp[1]);
			temptx3.setText(temp[2]);
			temptx4.setText(temp[3]);
			super.onPostExecute(doc);

			loadingDlg.cancel();

		}

	}// end inner class - GetXMLTask

}
