package org.example.ahroobe;




import org.example.ahroobe.Date.GPSListener1;

import android.app.Notification;
import android.app.NotificationManager;
import android.location.LocationManager;

public class Sharing {

	public static int timegap=30000;
	public static boolean pause=false;
	public static double location_x=0;
	public static double location_y=0;
	public static boolean distance = true;
	public static boolean sun = true;
	public static boolean movie = false;
	public static boolean wlrwjqpos=false;
	public static int timestandard=0;
	public static int theme = 0;
	public static int best = 1;
	public static boolean date=false;
	public static jogging[] eatform = new jogging[5];
	public static jogging[] jogform = new jogging[5];
	public static jogging[] qjsghkform = new jogging[5];
	public static jogging[] univform = new jogging[5];
	public static jogging[] shopform = new jogging[5];
	public static jogging[] eseckform = new jogging[5];
	public static jogging[] temp = new jogging[5];
	public static NotificationManager mNM;
	public static Notification mNoti;
	public static LocationManager manager;
	public static GPSListener1 gpsListener;
	public static boolean wkcnl=false;
	public static double[] xpath=new double[500];
	public static double[] ypath=new double[500];
	public static int numberofwkcnl=0;
	public static int arraysize=100;
	public static int datelast=60;
	public static boolean all=false;


}

class jogging {

	double latitude;// 위도,경도 값
	double longtitude;
	int weather_X;// 날씨 X,Y값
	int weather_Y;
	int picture1;// 이미지의 R id값
	int picture2;
	int explain;
	String weather;// 날씨정보 담은 값
	String moisture;
	String name;
	String temperature;
	int in;
	int movie;
	double calculate;

	jogging(String name, double latitude, double longtitude, int weather_X,
			int weather_Y, int picture1, int picture2, String weather,
			String moisture,String temperature,int in,int explain,int movie,double calculate) {
		this.latitude = latitude;
		this.longtitude = longtitude;
		this.weather_X = weather_X;
		this.weather_Y = weather_Y;
		this.picture1 = picture1;
		this.picture2 = picture2;
		this.weather = weather;
		this.moisture = moisture;
		this.name = name;
		this.temperature=temperature;
		this.in=in;
		this.explain=explain;
		this.movie=movie;
		this.calculate=calculate;
	}

}
