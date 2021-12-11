package utils;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Password {
	public static void main(String[] args) {
		System.out.println(encrypt(""));
		System.out.println(encrypt("a"));
	}
	public static String encrypt(String pass) {
		String result = null;
		try {
			MessageDigest msd = MessageDigest.getInstance("MD5");
			
			byte[] passBytes = pass.getBytes("UTF-8");
			byte[] resultBytes = msd.digest(passBytes);
			
			BigInteger bigInt = new BigInteger(1, resultBytes);
			result = bigInt.toString(16);
			
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return result;
	}
}
