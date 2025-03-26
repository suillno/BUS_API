package finedustmain;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
	private static List<String> list = new ArrayList<String>();
	
	public static String getDustDate(String year) {
		String urlstr = BASE_URL + "?serviceKey=" + API_KEY + "&returnType=json&numOfRows=100&pageNo=1&year=" + year;
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
				
				JSONObject obj = new JSONObject(responseStr.toString());
				
				JSONObject responseObj = obj.getJSONObject("response");
				JSONObject bodyObj = responseObj.getJSONObject("body");
				JSONArray itemsArray = bodyObj.getJSONArray("items");
				
				for(int i = 0; i < itemsArray.length(); i++) {
				String districtName = itemsArray.getJSONObject(i).getString("districtName");
				String issueGbn = itemsArray.getJSONObject(i).getString("issueGbn");
				String issueTime = itemsArray.getJSONObject(i).getString("issueTime");
				String issueDate = itemsArray.getJSONObject(i).getString("issueDate");
				String clearTime = itemsArray.getJSONObject(i).getString("clearTime");
				double issueVale = itemsArray.getJSONObject(i).getDouble("issueVal");
				
				FineDustData dustData = new FineDustData();
				dustData.setDistrictName(districtName);
				dustData.setIssueGbn(issueGbn);
				dustData.setIssueTime(issueTime);
				dustData.setIssueDate(issueDate);
				dustData.setClearTime(clearTime);
				dustData.setIssueVale(issueVale);
				
				list.add(dustData.toString());
				}
				
				if(list.isEmpty()) {
					JOptionPane.showMessageDialog(null, "해당 지역에 대한 경보 내역이 없습니다.", "오류", JOptionPane.ERROR_MESSAGE);
				}
				
				
				return list.toString();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	
	public static void main(String[] args) {
		
	}

}
