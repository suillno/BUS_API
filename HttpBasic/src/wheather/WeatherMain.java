package wheather;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import org.json.JSONArray;
import org.json.JSONObject;

import vo.weatherVO;

public class WeatherMain extends JFrame {
	// 상수 선언
	private static final String API_KEY = "75b94f5e783f089217e362b7cfe97ae6";
	private static final String BASE_URL = "https://api.openweathermap.org/data/2.5/weather";

	private JTextField cityTextField; // 검색 input
	private JTextArea wheatherTextArea;

	public WeatherMain() { // 생성자에 기본 창 설정
		setTitle("날씨 정보");
		setSize(500, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		JPanel inputPanel = new JPanel(); // 입력 필드 + 검색 버튼
		inputPanel.setLayout(new FlowLayout());

		JLabel cityLabel = new JLabel("도시명 : ");
		cityTextField = new JTextField(20); // 20글자를 가진 텍스트 필드 생성
		JButton btn = new JButton("확인");
		inputPanel.add(cityLabel);
		inputPanel.add(cityTextField);
		inputPanel.add(btn);

		wheatherTextArea = new JTextArea(); // 날씨정보 출력 영역
		wheatherTextArea.setEditable(false); // 정보출력창사용으로 정보입력 불가설정
		JScrollPane scrollPane = new JScrollPane(wheatherTextArea); // 스크롤바 생성

		add(inputPanel, BorderLayout.NORTH); // JFrame에 컴포넌트를 넣는다
		add(scrollPane, BorderLayout.CENTER);

		btn.addActionListener(e -> { // 버튼 클릭 처리
			String city = cityTextField.getText();
			int city2 = cityTextField.getText();
			
			String result = getWeatherDate(city);
			wheatherTextArea.setText(result);
		});
	}

	public static String getWeatherDate(String city) {
		String urlstr = BASE_URL + "?q=" + city + "&appid=" + API_KEY + "&units=metric";
		try {
			URL url = new URL(urlstr);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection(); 
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(5000); // 연결시간 초과설정
			conn.setReadTimeout(5000); // 읽기시간 초과설정

			int responseCode = conn.getResponseCode();

			if (responseCode == HttpURLConnection.HTTP_OK) {
				BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				String inputLine;
				StringBuilder response = new StringBuilder();
				// 비동기통신
				// cors : 동일한 도메인 내에서만 데이터 통신가능 규약
				// 다른도메인끼리 비동기 통신을 하려면
				// 1, 프록시 2, 백엔드에서 서버로 바로 접속
				
				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();

				String responseStr = response.toString();
				
				// 추의 사항 {}object 형태 []Array형태 기억하자
 				JSONObject obj = new JSONObject(responseStr.toString()); // 리턴 정보가 object 형태이므로 JSONObject 형태 변경

				JSONArray weather = obj.getJSONArray("weather"); // 날씨는 array형태이므로 obj에서 array형태로 "weather"을 찿아 추출
				
				double temp = obj.getJSONObject("main").getDouble("temp"); // object에서 main안의 temp double 값 출력
				int humidity = obj.getJSONObject("main").getInt("humidity"); // object에서 main안의 humidity int값 출력
				String wearherTxt = obj.getJSONArray("weather").getJSONObject(0).getString("description"); // obj에서 Array형태인 weather를 찿아 그 배열의 0째의 description값을 찿아서 String으로 변환한다.
				
				String str = String.format("온도 : %.1f℃\r\n습도 : %d\r\n날씨 : %s", temp, humidity, wearherTxt);
				
				return str;
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public static void main(String[] args) {
		// ui 구성
		// http 통신
		// 결과를 ui에 삽입
		SwingUtilities.invokeLater(() -> { // 스윙 ui를 GUI 스레드에서 실행
			WeatherMain main = new WeatherMain();
			main.setVisible(true);
		});

	}

}
