package vo;

public class FineDustData {
	public String issueDate; // 경보발생일
	public String clearTime; // 경보해제일
	public String issueGbn; // 경보명
	public String districtName; // 지역
	public int issueVale; // 오염등급
	public String getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}
	public String getClearTime() {
		return clearTime;
	}
	public void setClearTime(String clearTime) {
		this.clearTime = clearTime;
	}
	public String getIssueGbn() {
		return issueGbn;
	}
	public void setIssueGbn(String issueGbn) {
		this.issueGbn = issueGbn;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public int getIssueVale() {
		return issueVale;
	}
	public void setIssueVale(int issueVale) {
		this.issueVale = issueVale;
	}
	public FineDustData() {
		super();
	}
	public FineDustData(String issueDate, String clearTime, String issueGbn, String districtName, int issueVale) {
		super();
		this.issueDate = issueDate;
		this.clearTime = clearTime;
		this.issueGbn = issueGbn;
		this.districtName = districtName;
		this.issueVale = issueVale;
	}
	@Override
	public String toString() {
		return "FineDustData [issueDate=" + issueDate + ", clearTime=" + clearTime + ", issueGbn=" + issueGbn
				+ ", districtName=" + districtName + ", issueVale=" + issueVale + "]";
	}
	
	
	
	
}
