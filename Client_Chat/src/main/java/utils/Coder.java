package utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by IAUglov on 05.03.14.
 */
public class Coder {
	public static String getHash(String str) {
		String salt = "SuperPurperSalt!@#^&*(TROLOLO";
		str += salt;
		MessageDigest md5;
		StringBuffer hexString = new StringBuffer();
		try {
			md5 = MessageDigest.getInstance("md5");
			md5.reset();
			md5.update(str.getBytes());
			byte messageDigest[] = md5.digest();
			for (int i = 0; i < messageDigest.length; i++) {
				hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
			}
		} catch (NoSuchAlgorithmException e) {
			// Logging error
		}
		return hexString.toString();
	}
}
