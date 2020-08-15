package ch53;

public class CarDTO {
	private String license_number;
	private String company;
	private String type;
	private int year;
	private int efficiency;
	
	@Override
	public String toString() {
		return "CarDTO [license_number=" + license_number + ", company=" + company + ", type=" + type + ", year=" + year
				+ ", efficiency=" + efficiency + "]";
	}
	public CarDTO() {
		
	}
	public CarDTO(String license_number, String company, String type, int year, int efficiency) {
		super();
		this.license_number = license_number;
		this.company = company;
		this.type = type;
		this.year = year;
		this.efficiency = efficiency;
	}
	public String getLicense_number() {
		return license_number;
	}
	public void setLicense_number(String license_number) {
		this.license_number = license_number;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getEfficiency() {
		return efficiency;
	}
	public void setEfficiency(int efficiency) {
		this.efficiency = efficiency;
	}

}
