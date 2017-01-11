package org.example.ahroobe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;

public class FirstSplash extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.first_splash);
		Sharing.sun = true;
		Sharing.distance = true;

		Sharing.jogform[0] = new jogging("월드컵경기장", 126.8972328, 37.5682633, 59,
				127, R.drawable.worldcup1, R.drawable.worldcup2, "", "","",1,R.drawable.worldcupex,1,0);
		Sharing.jogform[1] = new jogging("마포대교", 126.9375034, 37.5340684, 59,
				126, R.drawable.mapo1, R.drawable.mapo2, "", "","",0,R.drawable.mapoex,0,0);
		Sharing.jogform[2] = new jogging("반포대교", 126.9964383, 37.5154772, 60,
				126, R.drawable.banpo1, R.drawable.banpo2, "", "","",0,R.drawable.banpoex,0,0);
		Sharing.jogform[3] = new jogging("서울숲", 127.039491, 37.5448142, 61,
				126, R.drawable.forest1, R.drawable.forest2, "", "","",0,R.drawable.seoulforestex,0,0);
		Sharing.jogform[4] = new jogging("남산", 126.9878596, 37.5511147, 60,
				126, R.drawable.namsan1, R.drawable.namsan2, "", "","",0,R.drawable.namsanex,0,0);

		Sharing.shopform[0] = new jogging("타임스퀘어", 126.9034060, 37.5171961, 59,
				126, R.drawable.timesquare1, R.drawable.timesquare2, "", "","",3,R.drawable.timesquareex,1,0);
		Sharing.shopform[1] = new jogging("여의도 ifc", 126.9257534,37.5253300, 59,
				126, R.drawable.ifc1, R.drawable.ifc2, "", "","",3,R.drawable.ifcex,1,0);
		Sharing.shopform[2] = new jogging("명동", 126.9846829,37.5629674, 60,
				127, R.drawable.myeongdong1, R.drawable.myeongdong2, "", "","",2,R.drawable.myeongdongex,1,0);
		Sharing.shopform[3] = new jogging("강남", 127.0289498,37.4992463, 61,
				125, R.drawable.gangnam1, R.drawable.gangnam1, "", "","",2,R.drawable.gangnamex,1,0);
		Sharing.shopform[4] = new jogging("비트플렉스 왕십리", 127.0382192,37.5611171, 61,
				127, R.drawable.ipark1, R.drawable.ipark2, "", "","",3,R.drawable.bitflexex,1,0);

		Sharing.eatform[0]= new jogging("이태원",126.992537, 37.5365026, 60,
				126, R.drawable.itaewon1, R.drawable.itaewon2, "", "","",1,R.drawable.itaewonex,0,0);
		Sharing.eatform[1]= new jogging("노량진",126.9416886, 37.514108, 59,
				125, R.drawable.noryangjin1, R.drawable.noryangjin2, "", "","",1,R.drawable.noryangjinex,0,0);
		Sharing.eatform[2]= new jogging("광장시장",126.9996180 ,37.5700790, 60,
				127, R.drawable.gwangjang1, R.drawable.gwangjang2, "", "","",2,R.drawable.gwangjangex,0,0);
		Sharing.eatform[3]= new jogging("상수동",126.921754, 37.549017, 59,
				126, R.drawable.sangsu1, R.drawable.sangsu2, "", "","",1,R.drawable.sangsuex,0,0);
		Sharing.eatform[4]= new jogging("인사동",126.9855594, 37.5746464, 60,
				127, R.drawable.insadong1, R.drawable.insadong1, "", "","",2,R.drawable.insadongex,0,0);
		
		
		Sharing.qjsghkform[0]= new jogging("명동",126.9846829,37.5629674, 60,
				127, R.drawable.myeongdong1, R.drawable.myeongdong2, "", "","",2,R.drawable.myeongdongex,1,0);
		Sharing.qjsghkform[1]= new jogging("홍대",126.9218877,37.5531791, 59,
				126, R.drawable.hongdae1, R.drawable.hongdae2, "", "","",2,R.drawable.hongdaeex,1,0);
		Sharing.qjsghkform[2]= new jogging("신촌",126.9370719, 37.5570995, 59,
				126, R.drawable.sinchon1, R.drawable.sinchon2, "", "","",2,R.drawable.sinchonex,1,0);
		Sharing.qjsghkform[3]= new jogging("강남",127.0289498,37.4992463, 61,
				125, R.drawable.gangnam1, R.drawable.gangnam1, "", "","",2,R.drawable.gangnamex,1,0);
		Sharing.qjsghkform[4]= new jogging("인사동",126.9855594, 37.5746464, 60,
				127, R.drawable.insadong1, R.drawable.insadong1, "", "","",2,R.drawable.insadongex,0,0);
		
		Sharing.univform[0]= new jogging("홍대",126.9218877,37.5531791, 59,
				126, R.drawable.hongdae1, R.drawable.hongdae2, "", "","",2,R.drawable.hongdaeex,1,0);
		Sharing.univform[1]= new jogging("신촌",126.9370719, 37.5570995, 59,
				126, R.drawable.sinchon1, R.drawable.sinchon2, "", "","",2,R.drawable.sinchonex,1,0);
		Sharing.univform[2]= new jogging("대학로",127.0011649,37.5830238, 60,
				127, R.drawable.daehakro1, R.drawable.daehakro2, "", "","",2,R.drawable.daehakroex,1,0);
		Sharing.univform[3]= new jogging("안암",127.0291594,37.5863268, 61,
				127, R.drawable.anam1, R.drawable.anam2, "", "","",1,R.drawable.anamex,1,0);
		Sharing.univform[4]= new jogging("건대입구",127.0701541,37.5424279, 62,
				126, R.drawable.gundae1, R.drawable.gundae2, "", "","",1,R.drawable.gundaeex,1,0);
		
		Sharing.eseckform[0]= new jogging("노량진",126.9416886, 37.514108, 59,
				125, R.drawable.noryangjin1, R.drawable.noryangjin2, "", "","",1,R.drawable.noryangjinex,0,0);
		Sharing.eseckform[1]= new jogging("광장시장",126.9996180 ,37.5700790, 60,
				127, R.drawable.gwangjang1, R.drawable.gwangjang2, "", "","",2,R.drawable.gwangjangex,0,0);
		Sharing.eseckform[2]= new jogging("잠실 롯데월드",127.0979614,37.5112448, 62,
				125, R.drawable.lotteworld1, R.drawable.lotteworld2, "", "","",3,R.drawable.lotteworldex,1,0);
		Sharing.eseckform[3]= new jogging("대학로",127.0011649,37.5830238, 60,
				127, R.drawable.daehakro1, R.drawable.daehakro2, "", "","",2,R.drawable.daehakroex,1,0);
		Sharing.eseckform[4]= new jogging("잠실 야구장",127.0718389,37.5119859, 62,
				125, R.drawable.jamsilbase1, R.drawable.jamsilbase1, "", "","",0,R.drawable.jamsilbaseex,0,0);
		
		
		Handler hd = new Handler() {
			public void handleMessage(Message msg) {
				finish();
			}
		};
		hd.sendEmptyMessageDelayed(0, 1500);

	}
}
