package com.abc.user;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class Utils.
 */
public class Utils {

	/**
	 * Next session id.
	 *
	 * @return the string
	 */
	public static String randomString() {
		SecureRandom random = new SecureRandom();
		String str= new BigInteger(240, random).toString(16);
		return "1000:" + str + ":" + str; 
	}

	/**
	 * Check nullorblank.
	 *
	 * @param str
	 *            the str
	 * @return true, if successful
	 */
	public static boolean checkNULLORBLANK(String str) {
		if (str == null)
			return true;
		if (StringUtils.isBlank(str))
			return true;
		return false;
	}
}