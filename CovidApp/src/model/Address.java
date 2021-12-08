package model;

public class Address {
	private String province;
	private String district;
	private String ward;

	public Address(String ward, String district, String province) {
		this.province = province;
		this.district = district;
		this.ward = ward;
	}
	public Address() {
		this.province = "";
		this.district = "";
		this.ward = "";
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getWard() {
		return ward;
	}

	public void setWard(String ward) {
		this.ward = ward;
	}
	@Override
	public String toString() {
		return ward + ", " + district + ", " + province;
	}
}
