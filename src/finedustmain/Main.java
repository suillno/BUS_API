package finedustmain;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.json.JSONArray;
import org.json.JSONObject;

import vo.FineDustData;

public class Main extends JFrame {

	// 예시 
	// https://apis.data.go.kr/B552584/UlfptcaAlarmInqireSvc/getUlfptcaAlarmInfo?serviceKey=dfkqgFHSVFefp3CpeXZ5psT9s6iWXzaWacsy9b%2FgDvuEJpzyy9Vpah1z2ktIKXO85kIcRQAGZwdqUjUDbD6dkg%3D%3D&returnType=json&numOfRows=100&pageNo=1&year=2020&itemCode=PM10
	private static final String API_KEY = "dfkqgFHSVFefp3CpeXZ5psT9s6iWXzaWacsy9b%2FgDvuEJpzyy9Vpah1z2ktIKXO85kIcRQAGZwdqUjUDbD6dkg%3D%3D";
	private static final String BASE_URL = "https://apis.data.go.kr/B552584/UlfptcaAlarmInqireSvc/getUlfptcaAlarmInfo";
	
	private JTextField yearText;
	private JTextArea fineDustText;
	
	public static String getDustDate(int year) {
		String urlstr = BASE_URL + "?serviceKey=" + API_KEY + "&returnType=json&numOfRows=1&pageNo=1&year=" + year;
				System.out.println(urlstr);
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
				
				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();
				
				String responseStr = response.toString();
				System.out.println(responseStr);
				JSONObject obj = new JSONObject(responseStr.toString());
				JSONObject responseObj = obj.getJSONObject("response");
				JSONObject bodyObj = responseObj.getJSONObject("body");
				JSONArray itemsArray = bodyObj.getJSONArray("items");
				System.out.println(itemsArray);
				
				String districtName = itemsArray.getJSONObject(0).getString("districtName");
				String issueGbn = itemsArray.getJSONObject(0).getString("issueGbn");
				String issueDate = itemsArray.getJSONObject(0).getString("issueDate");
				String clearTime = itemsArray.getJSONObject(0).getString("clearTime");
				double issueVale = itemsArray.getJSONObject(0).getDouble("issueVal");
				
				FineDustData dustData = new FineDustData();
				dustData.setDistrictName(districtName);
				dustData.setIssueGbn(issueGbn);
				dustData.setIssueDate(issueDate);
				dustData.setClearTime(clearTime);
				dustData.setIssueVale(issueVale);
				
				
				
				
				return dustData.toString();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	
	public static void main(String[] args) {
		
		System.out.println(Main.getDustDate(2024));
	}

}
