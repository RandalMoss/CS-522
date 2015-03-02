package models;

public class Star {
	private int id;
	private String name;
	private String url;
	private String type;
	private int age;
	private int mass;
	private int luminosity;
	private int temperature;
	private String solarSystemName;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getMass() {
		return mass;
	}
	public void setMass(int mass) {
		this.mass = mass;
	}
	public int getLuminosity() {
		return luminosity;
	}
	public void setLuminosity(int luminosity) {
		this.luminosity = luminosity;
	}
	public int getTemperature() {
		return temperature;
	}
	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}
	public String getSolarSystemName() {
		return solarSystemName;
	}
	public void setSolarSystemName(String solarSystemName) {
		this.solarSystemName = solarSystemName;
	}
}
