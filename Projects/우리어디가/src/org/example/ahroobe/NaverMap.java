package org.example.ahroobe;

import java.io.FileReader;

import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.nhn.android.maps.NMapActivity;
import com.nhn.android.maps.NMapController;
import com.nhn.android.maps.NMapOverlay;
import com.nhn.android.maps.NMapOverlayItem;
import com.nhn.android.maps.NMapView;
import com.nhn.android.maps.NMapView.OnMapStateChangeListener;
import com.nhn.android.maps.NMapView.OnMapViewTouchEventListener;
import com.nhn.android.maps.maplib.NGeoPoint;
import com.nhn.android.maps.nmapmodel.NMapError;
import com.nhn.android.maps.overlay.NMapPOIdata;
import com.nhn.android.maps.overlay.NMapPOIitem;
import com.nhn.android.maps.overlay.NMapPathData;
import com.nhn.android.maps.overlay.NMapPathLineStyle;
import com.nhn.android.mapviewer.overlay.NMapCalloutOverlay;
import com.nhn.android.mapviewer.overlay.NMapOverlayManager;
import com.nhn.android.mapviewer.overlay.NMapPathDataOverlay;
import com.nhn.android.mapviewer.overlay.NMapOverlayManager.OnCalloutOverlayListener;
import com.nhn.android.mapviewer.overlay.NMapPOIdataOverlay;
import com.nhn.android.mapviewer.overlay.NMapPOIdataOverlay.OnStateChangeListener;

public class NaverMap extends NMapActivity implements OnMapStateChangeListener,
		OnMapViewTouchEventListener, OnCalloutOverlayListener {

	public static final String API_KEY = "e38aed696e84e85f6b1ef183d8462f7d";
	NMapView mMapView = null;
	NMapController mMapController = null;
	LinearLayout MapContainer;
	NMapPOIdata poiData = null;
	NMapViewerResourceProvider mMapViewerResourceProvider = null;
	NMapOverlayManager mOverlayManager;
	OnStateChangeListener onPOIdataStateChangeListener = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.maplayout);

		MapContainer = (LinearLayout) findViewById(R.id.map);
		// create map view
		mMapView = new NMapView(this);

		// set a registered API key for Open MapViewer Library
		mMapView.setApiKey(API_KEY);
		mMapViewerResourceProvider = new NMapViewerResourceProvider(this);

		mOverlayManager = new NMapOverlayManager(this, mMapView,
				mMapViewerResourceProvider);

		setContentView(mMapView);

		// initialize map view
		mMapView.setClickable(true);

		// register listener for map state changes
		mMapView.setOnMapStateChangeListener(this);
		mMapView.setOnMapViewTouchEventListener(this);

		mMapView.setBuiltInZoomControls(true, null);
		// use map controller to zoom in/out, pan and set map center, zoom level
		// etc.
		mMapController = mMapView.getMapController();

		if (!Sharing.wkcnl) {
			NMapPOIdata poiData = new NMapPOIdata(8, mMapViewerResourceProvider);
			int markerId = NMapPOIflagType.PIN;
			poiData.beginPOIdata(177);

			poiData.addPOIitem(126.92325, 37.555482, "Ŀ�Ǻ� 02-333-4278",
					markerId, 0);
			poiData.addPOIitem(126.9204900,37.5509300, "ȫ����뷡������", markerId, 0);
			poiData.addPOIitem(126.9250597, 37.5573195, "�Ե��ó׸�", markerId, 0);
			poiData.addPOIitem(126.922817, 37.553878, "������ 02-338-6274",
					markerId, 0);
			poiData.addPOIitem(126.9251198, 37.5564134,
					"cafeJOENILL 02-326-3476", markerId, 0);
			poiData.addPOIitem(126.9249144, 37.5559772, "���������� 02-325-8050",
					markerId, 0);
			poiData.addPOIitem(126.9234381, 37.5546329, "�ٺ�ġĿ��", markerId, 0);
			poiData.addPOIitem(126.9241968, 37.5543324, "��������н��� 02-2137-0000",
					markerId, 0);
			poiData.addPOIitem(126.8972328, 37.5682633, "�����Ű���� 02-2128-2000",
					markerId, 0);
			poiData.addPOIitem(126.8984121, 37.5687306, "���̴� 02-306-8176",
					markerId, 0);
			poiData.addPOIitem(126.8983810, 37.5687110, "�������ʽ� 070-8807-7961",
					markerId, 0);
			poiData.addPOIitem(126.8981103, 37.5691872, "cgv 1544-1122",
					markerId, 0);
			poiData.addPOIitem(126.8946657, 37.5624663, "��ȭ�� ����", markerId, 0);
			poiData.addPOIitem(126.8850689, 37.5675814, "�ϴð���", markerId, 0);
			poiData.addPOIitem(126.8748698, 37.5746304, "��������", markerId, 0);
			poiData.addPOIitem(126.887157, 37.5719953, "����õ����", markerId, 0);
			poiData.addPOIitem(126.9375034, 37.5340684, "�����뱳", markerId, 0);
			poiData.addPOIitem(126.9310886, 37.5262462, "���̿����̱� 02-780-2881",
					markerId, 0);
			poiData.addPOIitem(126.9439412, 37.5393818, "������ 02-711-6277",
					markerId, 0);
			poiData.addPOIitem(126.9448900, 37.5392530, "���� 02-702-3997",
					markerId, 0);
			poiData.addPOIitem(126.9442030, 37.5378030, "ī��57 02-3275-0057",
					markerId, 0);
			poiData.addPOIitem(126.9280462, 37.5322802,
					"���ǵ� �������� 02-586-0622 ", markerId, 0);
			poiData.addPOIitem(126.9256579, 37.5257498,
					"CGV 1544-1122", markerId, 0);
			poiData.addPOIitem(126.9422356, 37.5374086, "�����Ҹſ����� 02-6267-3920",
					markerId, 0);
			poiData.addPOIitem(126.9964383, 37.5154772, "�����뱳", markerId, 0);
			poiData.addPOIitem(126.9953977, 37.5122487, "������ 1566-3433",
					markerId, 0);
			poiData.addPOIitem(126.9951208, 37.5116904,
					"CNNCafe ������ 02-595-6674", markerId, 0);
			poiData.addPOIitem(126.9950236, 37.5100036, "���� �Ѱ����� 02-591-5943",
					markerId, 0);
			poiData.addPOIitem(126.9945764, 37.5120689,
					"���������䷹�����ä������ 02-3477-3100", markerId, 0);
			poiData.addPOIitem(127.0002077, 37.5125134, "��ũ����", markerId, 0);
			poiData.addPOIitem(127.0394911, 37.5448142, "���｣ 02-460-2905",
					markerId, 0);
			poiData.addPOIitem(127.0418533, 37.5457719, "�����Ľ�Ÿ 02-467-0779",
					markerId, 0);
			poiData.addPOIitem(127.0419246, 37.5458999, "����Ǫ�� 02-461-8200",
					markerId, 0);
			poiData.addPOIitem(127.0444155, 37.5469973, "������ 02-469-5162",
					markerId, 0);
			poiData.addPOIitem(127.0424342, 37.5458028, "�����ݸ�Ŀ�� 02-6405-8009",
					markerId, 0);
			poiData.addPOIitem(127.0436259, 37.5460623, "Ǫ���ʽ� ���� 02-544-5674",
					markerId, 0);
			poiData.addPOIitem(127.0438257, 37.5469325, "����ٹ� 070-8969-4321",
					markerId, 0);
			poiData.addPOIitem(126.9878596, 37.5511147, "N����Ÿ�� 02-3455-9277",
					markerId, 0);
			poiData.addPOIitem(126.988315, 37.551237, "�ݵ彺��ũ���Ӹ� 02-3789-1304",
					markerId, 0);
			poiData.addPOIitem(126.9889172, 37.5492745, "�����÷��̽� 02-3455-9230 ",
					markerId, 0);
			poiData.addPOIitem(126.9813313, 37.5530947, "���굵���� 02-754-7338",
					markerId, 0);
			poiData.addPOIitem(126.9803719, 37.553478, "���߱��ǻ���� 02-3789-1016",
					markerId, 0);
			poiData.addPOIitem(126.9859266, 37.5572350, "�̳��̾߸� 02-318-6696",
					markerId, 0);
			poiData.addPOIitem(126.985762, 37.5568899, "���굷� 02-777-7929",
					markerId, 0);
			poiData.addPOIitem(126.9882261, 37.5511806, "���׸� 02-3455-9297",
					markerId, 0);
			poiData.addPOIitem(127.0979614, 37.5112448, "�Ե����� 1661-2000",
					markerId, 0);
			poiData.addPOIitem(127.1011202, 37.5103175, "�����̴�ȣ��", markerId, 0);
			poiData.addPOIitem(127.0997862, 37.5114615, "�Ե�ȣ�ڿ��� 02-419-7000",
					markerId, 0);
			poiData.addPOIitem(127.0964127, 37.5117523, "�Ե���Ʈ 02-411-8025",
					markerId, 0);
			poiData.addPOIitem(127.0943742, 37.5115197,
					"��Ÿ���� ���������Ӹ����� 02-758-8118", markerId, 0);
			poiData.addPOIitem(127.0971321, 37.5106258, "�Ե��ó׸� 1544-8855",
					markerId, 0);
			poiData.addPOIitem(127.0993587, 37.5122522, "�Ե���ȭ�� 02-411-2500",
					markerId, 0);
			poiData.addPOIitem(127.000029, 37.5833461, "CGV 1544-1122",
					markerId, 0);
			poiData.addPOIitem(127.0020277, 37.5840865, "��ε���̾�ƮȦ 02-742-4224",
					markerId, 0);
			poiData.addPOIitem(127.0005987, 37.5821606, "��Ÿ��Ƽ 02-747-9139",
					markerId, 0);
			poiData.addPOIitem(127.0021509, 37.5837823, "�ҿ�ұ���", markerId, 0);
			poiData.addPOIitem(127.0035642, 37.5816683, "�浹�ұ��� 02-764-5715",
					markerId, 0);
			poiData.addPOIitem(127.0026772, 37.5815384, "�Ľ����� 070-7825-1992",
					markerId, 0);
			poiData.addPOIitem(127.0033947, 37.5828141, "���з�", markerId, 0);
			poiData.addPOIitem(127.0029226, 37.5811654, "�Ƹ��ڿ�������", markerId, 0);

			poiData.addPOIitem(127.0769113, 37.5088902, "�丮ī��ġŲ 070-4980-0073",
					markerId, 0);
			poiData.addPOIitem(127.081329, 37.509347, "ġŲ�ŴϾ� 02-2202-9255",
					markerId, 0);
			poiData.addPOIitem(127.0809731, 37.5107101, "���ġŲ 02-418-0810",
					markerId, 0);
			poiData.addPOIitem(127.0819774, 37.5094041, "�׳�ġŲ 02-423-4374",
					markerId, 0);
			poiData.addPOIitem(127.083302, 37.510964, "�丮ī�� 02-417-3599",
					markerId, 0);
			poiData.addPOIitem(127.079077, 37.510664, "��Ÿ���� 02-758-8124",
					markerId, 0);
			poiData.addPOIitem(127.0828889, 37.5094948,
					"��������þ�Ʈ���� 02-414-6197", markerId, 0);
			poiData.addPOIitem(126.9256575, 37.525765, "CGV 1544-1122",
					markerId, 0);
			poiData.addPOIitem(126.9266626, 37.5255053,
					"�ܷ��� ����ȣ�� 02-6137-7000", markerId, 0);
			poiData.addPOIitem(126.922381, 37.5261474, "���ǵ� ����", markerId, 0);
			poiData.addPOIitem(126.9261837, 37.5240624, "�纸�ٵ�� 02-780-4510",
					markerId, 0);
			poiData.addPOIitem(126.9265494, 37.5235424, "�ż��ѽ�", markerId, 0);
			poiData.addPOIitem(126.9252672, 37.5249605, "���� 02-6137-5070",
					markerId, 0);
			poiData.addPOIitem(126.9263648, 37.5237205, "�ٴٹ� 02-780-9291",
					markerId, 0);
			poiData.addPOIitem(126.9272445, 37.524832, "Ŀ�Ǻ� 02-783-3391",
					markerId, 0);

			poiData.addPOIitem(127.038384, 37.561058, "��Ų������ 02-2200-1519",
					markerId, 0);
			poiData.addPOIitem(127.0392408, 37.561019, "�Ƹ����� ī��", markerId, 0);
			poiData.addPOIitem(127.0383066, 37.5615705,
					"�׸�Ÿ�� 02-2200-1488(���ֿ�)", markerId, 0);
			poiData.addPOIitem(127.0400761, 37.560503, "�������", markerId, 0);
			poiData.addPOIitem(127.0381421, 37.5621934, "CGV 1544-1122",
					markerId, 0);
			poiData.addPOIitem(127.0360838, 37.5609569, "���붱����", markerId, 0);
			poiData.addPOIitem(127.0372475, 37.5616285, "�սʸ�����", markerId, 0);
			poiData.addPOIitem(127.0381474, 37.5619595, "�̸�Ʈ 02-2200-1234",
					markerId, 0);

			poiData.addPOIitem(126.9849378, 37.5742128, "������ 02-736-0088 ",
					markerId, 0);
			poiData.addPOIitem(126.9851129, 37.5740234, "������ ������ 02-730-8898",
					markerId, 0);
			poiData.addPOIitem(126.9838456, 37.574035, "�ȶ��� �࿡ 02-744-9240",
					markerId, 0);
			poiData.addPOIitem(126.985132, 37.574159, "���̼ո��� 02-732-1238",
					markerId, 0);
			poiData.addPOIitem(126.9839039, 37.574243, "�ſ����� 02-732-5257",
					markerId, 0);
			poiData.addPOIitem(126.9855776, 37.5743745, "����ٿ� 02-730-6305",
					markerId, 0);
			poiData.addPOIitem(126.985275, 37.5746333,
					"ä�Ŀ丮������ �������� 02-735-7171", markerId, 0);
			poiData.addPOIitem(126.9870479, 37.5761401, "������ 02-766-9090",
					markerId, 0);

			poiData.addPOIitem(126.9408956, 37.5153779, "�뷮�� ������� 02-814-2211",
					markerId, 0);
			poiData.addPOIitem(126.9421483, 37.5133179, "����� 02-822-2097",
					markerId, 0);
			poiData.addPOIitem(126.9368086, 37.5129706, "������ 02-6333-5300",
					markerId, 0);
			poiData.addPOIitem(126.940575, 37.5133333, "�轺Ų��� 02-823-1195",
					markerId, 0);
			poiData.addPOIitem(126.94339, 37.513936, "�Ҹ���Ŀ�� 02-827-0280",
					markerId, 0);
			poiData.addPOIitem(126.9451381, 37.5123763, "ũ���� 010-4922-8060",
					markerId, 0);
			poiData.addPOIitem(126.9430232, 37.513588, "�뷮�� ��Ÿ� ����", markerId,
					0);

			poiData.addPOIitem(126.9034060, 37.5171961, "Ÿ�ӽ����� 02-2638-2000",
					markerId, 0);
			poiData.addPOIitem(126.9035038, 37.5175472, "�´����� 02-2672-0682",
					markerId, 0);
			poiData.addPOIitem(126.9028914, 37.5172018, "�ŵ������� 02-2637-3040",
					markerId, 0);
			poiData.addPOIitem(126.9054112, 37.5171588, "�ż����ȭ�� 1588-1234",
					markerId, 0);
			poiData.addPOIitem(126.9028435, 37.5173856, "CGV 1544-1122",
					markerId, 0);
			poiData.addPOIitem(126.9033476, 37.5170956, "�������� 02-2638-2540",
					markerId, 0);
			poiData.addPOIitem(126.9030726, 37.5173407, "ī�丶���� 02-2069-1461",
					markerId, 0);

			poiData.addPOIitem(127.0007294, 37.5705942, "����׺�붱 02-2268-3344",
					markerId, 0);
			poiData.addPOIitem(127.0010909, 37.570264, "��ಿ�������� 02-2273-8330",
					markerId, 0);
			poiData.addPOIitem(126.9999376, 37.5703905, "â����ȸ 02-2266-6727",
					markerId, 0);
			poiData.addPOIitem(126.9999231, 37.5706027, "��ȸ�ڸ���02-2274-8344",
					markerId, 0);
			poiData.addPOIitem(126.999486, 37.571058, "�Ҹ���Ŀ�� 02-742-7393",
					markerId, 0);
			poiData.addPOIitem(126.9985273, 37.5699953, "���Ա�����", markerId, 0);

			poiData.addPOIitem(126.9961903, 37.5348509, "��Ʈ�� 02-795-6164",
					markerId, 0);
			poiData.addPOIitem(126.9955422, 37.5349877,
					"��Ʈ��ŷ������� 02-794-9919 ", markerId, 0);
			poiData.addPOIitem(126.9928260, 37.5347265, "Ÿ��ƾ 02-3785-3400",
					markerId, 0);
			poiData.addPOIitem(126.9945856, 37.5348143, "����Ÿ�� 02-794-8090",
					markerId, 0);
			poiData.addPOIitem(126.9942330, 37.5342663, "�����ϴٽ� 02-798-1312",
					markerId, 0);
			poiData.addPOIitem(126.9917110, 37.5397980, "�渮�ܱ�", markerId, 0);
			poiData.addPOIitem(126.9927940, 37.5411211, "����ũ 070-8156-5459",
					markerId, 0);

			poiData.addPOIitem(126.9220512, 37.5482135, "��������¸� 02-324-0202",
					markerId, 0);
			poiData.addPOIitem(126.9224342, 37.5491984, "���Ե����� 02-337-2207",
					markerId, 0);
			poiData.addPOIitem(126.9208189, 37.5482913, "�����Թڽ�����ũ 02-323-6771",
					markerId, 0);
			poiData.addPOIitem(126.9208452, 37.5479633, "�캣��Ŀ�� 02-794-5090",
					markerId, 0);
			poiData.addPOIitem(126.9219179, 37.5486149, "�ۺ�ũ 02-333-6919",
					markerId, 0);
			poiData.addPOIitem(126.9232213, 37.5488279, "ȣȣ�̿� 02-322-6473",
					markerId, 0);
			poiData.addPOIitem(126.9219852, 37.5491398, "�ҳ��ȸ 02-3141-1215",
					markerId, 0);
			poiData.addPOIitem(126.9221157, 37.5488024, "Ű��ī�� 070-4242-2529",
					markerId, 0);

			poiData.addPOIitem(126.9358453, 37.5561012, "�����ȭ�� 02-3145-2233 ", markerId, 0);
			poiData.addPOIitem(126.9375512, 37.5558389, "������ķ� 02-362-8592", markerId, 0);
			poiData.addPOIitem(126.9363865, 37.5573008, "��õ���߰��� ", markerId, 0);
			poiData.addPOIitem(126.9371765, 37.5562833, "The STAGE 02-312-9940", markerId, 0);
			poiData.addPOIitem(126.9365832,37.5556883 , "�����÷��̽� 02-3142-5995", markerId, 0);
			poiData.addPOIitem(126.9421085,37.5596084 , "�ް��ڽ� 1544-0070", markerId, 0);
			poiData.addPOIitem(126.9367506, 37.5579713, "ī������ 02-3142-0320", markerId, 0);
			poiData.addPOIitem(126.9359859, 37.5587879, "��ȭ��ī��", markerId, 0);
			poiData.addPOIitem(127.0006132, 37.5826802, "�̽�ź�� 02-744-9790", markerId, 0);
			poiData.addPOIitem(127.0012619, 37.5831785, "���Ŀ�� 02-764-6079", markerId, 0);
			poiData.addPOIitem(127.0023380 , 37.5829750, "�̳� 02-743-1011", markerId, 0);  // ����
			poiData.addPOIitem(127.000281, 37.5820781, "ī����ٹ� 010-9259-8759", markerId, 0);
			poiData.addPOIitem(127.000029, 37.5833461, "CGV 1544-1122", markerId, 0);
			poiData.addPOIitem(127.0011707, 37.5818133, "ī��ù���� 02-766-2337", markerId, 0);
			poiData.addPOIitem(127.001655, 37.5819667, "�и��ٹ� 02-742-2877", markerId, 0);
			poiData.addPOIitem(127.0035532, 37.5824795, "AN��ƮȦ 070-8817-5490", markerId, 0);
			
			poiData.addPOIitem(127.0291840,37.5874670, "���޻� 02-953-3394", markerId, 0);
			poiData.addPOIitem(127.0298550,37.5858230, "������", markerId, 0);
			poiData.addPOIitem(127.0298658,37.5873255, "���γ����� 02-6348-1352", markerId, 0);
			poiData.addPOIitem(127.0333413,37.5888365, "�߾ӱ���", markerId, 0);
			poiData.addPOIitem(127.0293221,37.5840452, "�纸�� ", markerId, 0);
			poiData.addPOIitem(127.0285690,37.5824877, "���ν��� 02-928-0401", markerId, 0);
			poiData.addPOIitem(127.0293473,37.5886810, "�ƴ� 02-922-5036", markerId, 0);
			poiData.addPOIitem(127.0310346,37.5867588, "KU�ó׸�Ʈ�� 02-924-6579", markerId, 0);
			
			poiData.addPOIitem(127.0704318,37.5413761, "��������1����", markerId, 0);
			poiData.addPOIitem(127.0702643,37.5409753, "���״���¤���㰥��", markerId, 0);
			poiData.addPOIitem(127.0694205,37.5413571, "�����³� 02-462-8821", markerId, 0);
			poiData.addPOIitem(127.0713414,37.5421438, "�ȸ�����ī��", markerId, 0);
			poiData.addPOIitem(127.0712593,37.5390969, "�Ե���ȭ�� 02-2218-2760", markerId, 0);
			poiData.addPOIitem(127.0700846,37.5405321, "�������ġ����ġ 02-464-6400", markerId, 0);
			poiData.addPOIitem(127.0704762,37.5408382, "ŽŽ 02-464-4565", markerId, 0);
			poiData.addPOIitem(127.0809282,37.5499163, "��̴���� 02-450-9311", markerId, 0);
			poiData.addPOIitem(127.0694394,37.5433735, "ȭ������� 02-265-3706", markerId, 0);
			poiData.addPOIitem(127.0723694,37.5388563, "�Ե��ó׸� 1544-8855", markerId, 0);
			
			poiData.addPOIitem(126.9855875,37.5626833, "������ 02-776-5348", markerId, 0);
			poiData.addPOIitem(126.9839326,37.5617536, "������ 02-777-8979", markerId, 0);
			poiData.addPOIitem(126.9849709,37.5610359, "���и�����", markerId, 0);
			poiData.addPOIitem(126.9810713,37.5610192, "�ż����ȭ�� 1588-1234", markerId, 0);
			poiData.addPOIitem(126.9872267,37.5634409, "���뼺�� 02-774-1784", markerId, 0);
			poiData.addPOIitem(126.9831873,37.5635197, "��������02-3783-5005", markerId, 0);
			poiData.addPOIitem(126.9826405,37.5633461, "CGV 1544-1122", markerId, 0);
			poiData.addPOIitem(126.9834106,37.5649583, "���ٹ�̽��� 02-755-0939", markerId, 0);
			
			poiData.addPOIitem(127.0264068,37.4982293, "�ް��ڽ� 1544-0070", markerId, 0);
			poiData.addPOIitem(127.0262445,37.5017999, "CGV 1544-1122", markerId, 0);
			poiData.addPOIitem(127.0274207,37.4992745, "���� 02-558-7415", markerId, 0);
			poiData.addPOIitem(127.0254570,37.4989070, "�����ΰ������ͽ�", markerId, 0);
			poiData.addPOIitem(127.0259657,37.5005351, "�����ٳ� 02-3481-7365", markerId, 0);
			poiData.addPOIitem(127.0241551,37.5038195, "�������� 02-4580-4771", markerId, 0);
			poiData.addPOIitem(127.0310883,37.4994847, "��Ų������ 02-2051-9484", markerId, 0);
			poiData.addPOIitem(127.0254288,37.5000647, "����� 02-592-1618", markerId, 0);
			poiData.addPOIitem(127.0253444,37.4994431, "�������� 02-3482-0959", markerId, 0);
			
			poiData.endPOIdata();

			NMapPOIdataOverlay poiDataOverlay = mOverlayManager
					.createPOIdataOverlay(poiData, null);

			poiDataOverlay
					.setOnStateChangeListener(onPOIdataStateChangeListener);
		} else if (!Sharing.all) {

			NMapPOIdata poiData = new NMapPOIdata(2, mMapViewerResourceProvider);
			int markerId = NMapPOIflagType.PIN;
			poiData.beginPOIdata(2);
			poiData.addPOIitem(Sharing.xpath[0], Sharing.ypath[0], "���",
					markerId, 0);
			poiData.addPOIitem(Sharing.xpath[Sharing.numberofwkcnl - 1],
					Sharing.ypath[Sharing.numberofwkcnl - 1], "����", markerId, 0);
			NMapPOIdataOverlay poiDataOverlay = mOverlayManager
					.createPOIdataOverlay(poiData, null);
			poiDataOverlay
					.setOnStateChangeListener(onPOIdataStateChangeListener);

			NMapPathData pathData = new NMapPathData(9);

			pathData.initPathData();

			pathData.addPathPoint(Sharing.xpath[0], Sharing.ypath[0],
					NMapPathLineStyle.TYPE_SOLID);

			for (int i = 1; i < Sharing.numberofwkcnl; i++) {
				pathData.addPathPoint(Sharing.xpath[i], Sharing.ypath[i], 0);
			}
			pathData.endPathData();

			NMapPathDataOverlay pathDataOverlay = mOverlayManager
					.createPathDataOverlay(pathData);
			pathDataOverlay.showAllPathData(0);

		}

		mOverlayManager
				.setOnCalloutOverlayListener((OnCalloutOverlayListener) this);

		// set the activity content to the map view

	}

	@Override
	public void onLongPress(NMapView arg0, MotionEvent arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onLongPressCanceled(NMapView arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onScroll(NMapView arg0, MotionEvent arg1, MotionEvent arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onSingleTapUp(NMapView arg0, MotionEvent arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTouchDown(NMapView arg0, MotionEvent arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTouchUp(NMapView arg0, MotionEvent arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onAnimationStateChange(NMapView arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMapCenterChange(NMapView arg0, NGeoPoint arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMapCenterChangeFine(NMapView arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onMapInitHandler(NMapView mapView, NMapError errorInfo) {

		if (errorInfo == null && Sharing.all) {
			mMapController.setMapCenter(new NGeoPoint(126.9783924, 37.5666263),
					7);
			Sharing.all = false;
		} else if (errorInfo == null && !Sharing.date) { // success
			mMapController.setMapCenter(new NGeoPoint(
					Sharing.temp[Sharing.best - 1].latitude,
					Sharing.temp[Sharing.best - 1].longtitude), 11);
		} else if (Sharing.date) {
			Sharing.wkcnl = false;
		} else {
			android.util.Log.e("NMAP",
					"onMapInitHandler: error=" + errorInfo.toString());
		}
	}

	@Override
	public void onZoomLevelChange(NMapView arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	public void onCalloutClick(NMapPOIdataOverlay poiDataOverlay,
			NMapPOIitem item) {

		Toast.makeText(NaverMap.this, "onCalloutClick: " + item.getTitle(),
				Toast.LENGTH_LONG).show();

	}

	public void onFocusChanged(NMapPOIdataOverlay poiDataOverlay,
			NMapPOIitem item) {
		if (item != null) {
			Log.i("", "onFocusChanged: " + item.toString());
		} else {
			Log.i("", "onFocusChanged: ");
		}
	}

	@Override
	public NMapCalloutOverlay onCreateCalloutOverlay(NMapOverlay itemOverlay,
			NMapOverlayItem overlayItem, Rect itemBounds) {
		// set your callout overlay
		return new NMapCalloutBasicOverlay(itemOverlay, overlayItem, itemBounds);
	}

}
