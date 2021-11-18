package model;

public class Payment_History {
	private Double minDebt;

	public Payment_History(Double minDebt) {
		this.minDebt = minDebt;
	}

	public Payment_History() {

	}

	public Double getMinDebt() {
		return minDebt;
	}

	public void setMinDebt(Double minDebt) {
		this.minDebt = minDebt;
	}
	
	
	
}
