package model;

public class AccountCurrent {
	private static String usernameCurrent = "";

	public static String getUsernameCurrent() {
		return usernameCurrent;
	}

	public static void setUsernameCurrent(String usernameCurrent) {
		AccountCurrent.usernameCurrent = usernameCurrent;
	}
	
}
