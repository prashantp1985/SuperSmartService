package com.dhl.chatbot.exception;

/**
 * 
 * @author Prashant Padmanabhan
 *
 */
public class DHLValidationException extends Exception {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String ENCRYPTION_DECRYPTION_OPERATION_ERROR_CODE = null;
	public static final String ENCRYPTION_DECRYPTION_OPERATION_MESSAGE = null;

	DHLValidationException (String errorCode, String errorMessage, Exception e) {
		
	}
}