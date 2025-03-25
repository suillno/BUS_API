package vo;

import java.util.HashMap;

import org.json.JSONObject;

public class PostVo {
	private int userId;
	private int id;
	private String title;
	private String body;
	
	
	/**
	 * String responseStr = response.toString(); // String 형태의 제이슨을 변수에 담는다
			JSONObject JsonObject = new JSONObject(responseStr); // 제이슨 오브젝트를 통해 키 벨류로 표기
			System.out.println(JsonObject.toString());
			String title = JsonObject.getString("title"); // 키를 변수로 설정하고 제이슨에서 값을가져와 변수에 담는다
			System.out.println(title);
	 */
	
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public PostVo() {
		super();
	}
	public PostVo(int userId, int id, String title, String body) {
		super();
		this.userId = userId;
		this.id = id;
		this.title = title;
		this.body = body;
	}
	@Override
	public String toString() {
		return "PostVo [userId=" + userId + ", id=" + id + ", title=" + title + ", body=" + body + "]";
	}
	
}
