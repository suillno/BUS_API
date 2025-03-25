package vo;

public class weatherVO {
	
	// 온도, 습도, 날씨
	private double temperature;
	private int humidity;
	private String descrption;

	public double getTemperature() {
		return temperature;
	}
	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}
	public int getHumidity() {
		return humidity;
	}
	public void setHumidity(int humidity) {
		this.humidity = humidity;
	}
	public String getDescrption() {
		return descrption;
	}
	public void setDescrption(String descrption) {
		this.descrption = descrption;
	}
	@Override
	public String toString() {
		return "weatherVO [temperature=" + temperature + ", humidity=" + humidity + ", descrption=" + descrption + "]";
	}
	
	
	
}
