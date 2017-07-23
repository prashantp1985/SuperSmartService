package com.dhl.chatbot.util;

/**
 * 
 * @author Prashant Padmanabhan
 *
 */
public class ValidationHelper {

	/**
	 * This method is used to isNull
	 * @param object
	 * @return boolean
	 */
	public static boolean isNull(Object object) {
		return object == null ? true : false;
	}
	
	/**
	 * This method is used to isStringNullOrEmpty
	 * @param string
	 * @return boolean
	 */
	public static boolean isStringNullOrEmpty(String string) {
		boolean isStringNullOrEmpty = false;
		if (string != null) {
			if (string.trim().length() == 0) {
				isStringNullOrEmpty = true;
			}
		} else {
			isStringNullOrEmpty = true;
		}
		return isStringNullOrEmpty;
	}
	
	/**
	 * This method is used to isDouble
	 * @param string
	 * @return boolean
	 */
	public static boolean isDouble(String string) {
		boolean isDouble = true;
		try {
			if(! isStringNullOrEmpty(string)) {
				Double.parseDouble(string);
			} else {
				isDouble = false;
			}
		} catch (NumberFormatException numberFormatException) {
			isDouble = false;
		}
		return isDouble;
	}
	
	/**
	 * This method is used to isInteger
	 * @param string
	 * @return boolean
	 */
	public static boolean isInteger(String string) {
		boolean isInteger = true;
		try {
			if(! isStringNullOrEmpty(string)) {
				Integer.parseInt(string);
			} else {
				isInteger = false;
			}
		} catch (NumberFormatException numberFormatException) {
			isInteger = false;
		}
		return isInteger;
	}
	
	/**
	 * This method is used to isNumberPositive
	 * @param string
	 * @return boolean
	 */
	public static boolean isNumberPositive(String string) {
		boolean isPositive = true;
		try {
			if(! isStringNullOrEmpty(string)) {
				isPositive = Double.parseDouble(string) >= 0 ? true : false;
			} else {
				isPositive = false;
			}
		} catch (NumberFormatException numberFormatException) {
			isPositive = false;
		}
		return isPositive;
	} 
	
	/**
	 * This method is used to isNumberPositive
	 * @param string
	 * @return boolean
	 */
	public static boolean isNumberZero(String string) {
		boolean isZero = true;
		try {
			if(! isStringNullOrEmpty(string)) {
				isZero = Double.parseDouble(string) == 0 ? true : false;
			} else {
				isZero = false;
			}
		} catch (NumberFormatException numberFormatException) {
			isZero = false;
		}
		return isZero;
	} 
	
	/**
	 * This method is used to isNumberNegative
	 * @param string
	 * @return boolean
	 */
	public static boolean isNumberNegative(String string) {
		return ! isNumberPositive(string);
	}
	
}
