package model;

public enum F {
	F0("F0"),
	F1("F1"),
	F2("F2"),
	F3("F3");
	private final String status;
	F(String status){
		this.status = status;
	}
	public String getF() {
		return status;
	}
}
