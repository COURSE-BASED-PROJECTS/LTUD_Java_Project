package model;

public class Payment_History {
	private Double minDebt;
	private String MaGD;
	private String CMND;
	private Double Debit;
	
	
	public Double getMinDebt() {
		return minDebt;
	}
	public void setMinDebt(Double minDebt) {
		this.minDebt = minDebt;
	}
	public String getMaGD() {
		return MaGD;
	}
	public void setMaGD(String maGD) {
		MaGD = maGD;
	}
	public String getCMND() {
		return CMND;
	}
	public void setCMND(String cMND) {
		CMND = cMND;
	}
	public Double getDebit() {
		return Debit;
	}
	public void setDebit(Double debit) {
		Debit = debit;
	}
	
	public static String randomCodeHistory() {
		char letterCode = (char)Math.floor(Math.random()*(90-65+1)+65);
		long numberCode = (long)Math.floor(Math.random()*(9999999+1)+9999999);
		
		return letterCode + String.valueOf(numberCode);
		
	}
	
//	public Payment_History(Double minDebt) {
//		this.minDebt = minDebt;
//	}

//	public Payment_History() {
//
//	}
//
//	public Double getMinDebt() {
//		return minDebt;
//	}
//
//	public void setMinDebt(Double minDebt) {
//		this.minDebt = minDebt;
//	}
	
	
	
}
