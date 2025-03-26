package finedustmain;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.json.JSONArray;
import org.json.JSONObject;

import vo.FineDustData;

public class DistrictMain {
	//URL을 상수 설정 및 value를 담을 리스트 선언
	private static final String API_KEY = "dfkqgFHSVFefp3CpeXZ5psT9s6iWXzaWacsy9b%2FgDvuEJpzyy9Vpah1z2ktIKXO85kIcRQAGZwdqUjUDbD6dkg%3D%3D";
	private static final String BASE_URL = "https://apis.data.go.kr/B552584/UlfptcaAlarmInqireSvc/getUlfptcaAlarmInfo";
	private static List<String> list = new ArrayList<String>();
	
	// 검색조건 year과 값출력후 재검색 변수 district 설정(year 년도검색, district 도시검색)
	public static String getDustDate(String year, String district) {
		// year변수와 url 조합
		String urlstr = BASE_URL + "?serviceKey=" + API_KEY + "&returnType=json&numOfRows=100&pageNo=1&year=" + year;
		
		// URL 객체 생성후 Http 통신을위한 GET방식 연결설정
		try { 
			URL url = new URL(urlstr);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection(); 
			conn.setRequestMethod("GET");
			conn.setConnectTimeout(5000); // 연결시간 초과설정
			conn.setReadTimeout(5000); // 읽기시간 초과설정
			
			// 서버 응답코드 출력
			int responseCode = conn.getResponseCode();
			
			// 서버 응답코드 200ok시 진행
			if (responseCode == HttpURLConnection.HTTP_OK) {
				
				// 서버응답을 바이트스트림으로 가져와 문자스트림으로 읽고 줄단위로 읽기위해 BufferedReader을 사용
				BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				String inputLine;
				StringBuilder response = new StringBuilder();
				
				// inputLine과 in.readLine()가 null이 될때까지 스트링빌더를 통해 response에 문자열 이어붙이기
				while ((inputLine = in.readLine()) != null) {
					response.append(inputLine);
				}
				in.close();
				
				// 문자열을 이어붙인 response를 String 형태로 선언
				String responseStr = response.toString();
				
				// String responseStr를 JSONObject 형태로 변경
				JSONObject obj = new JSONObject(responseStr.toString());
				
				// 최상위 JSONObject에서 필요한 하위 객체와 배열 추출
				JSONObject responseObj = obj.getJSONObject("response");
				JSONObject bodyObj = responseObj.getJSONObject("body");
				JSONArray itemsArray = bodyObj.getJSONArray("items");
				
				// JitemsArray의 각 요소를 순회하며, district가 일치하면 FineDustData 객체에 값 저장 후 리스트에 추가
				// 이후 해당 값을 list에 저장
				for(int i = 0; i < itemsArray.length(); i++) {
				String districtName = itemsArray.getJSONObject(i).getString("districtName");
				String issueGbn = itemsArray.getJSONObject(i).getString("issueGbn");
				String issueTime = itemsArray.getJSONObject(i).getString("issueTime");
				String issueDate = itemsArray.getJSONObject(i).getString("issueDate");
				String clearTime = itemsArray.getJSONObject(i).getString("clearTime");
				double issueVale = itemsArray.getJSONObject(i).getDouble("issueVal");
				
				if(districtName.equals(district)) {
				FineDustData dustData = new FineDustData();
				dustData.setDistrictName(districtName);
				dustData.setIssueGbn(issueGbn);
				dustData.setIssueTime(issueTime);
				dustData.setIssueDate(issueDate);
				dustData.setClearTime(clearTime);
				dustData.setIssueVale(issueVale);
				
				list.add(dustData.toString());
				}}
			
				// 일치하는 데이터가 없으면 오류 메시지 출력
				if(list.isEmpty()) {
					JOptionPane.showMessageDialog(null, "해당 지역에 대한 경보 내역이 없습니다.", "오류", JOptionPane.ERROR_MESSAGE);
				}
				
				// ArrayList 리스트의 내용을 문자열로 변환하여 반환하여 리턴
				return list.toString();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "잘못입력하셨습니다.";
	}

}
