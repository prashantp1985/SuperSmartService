package com.dhl.chatbot.exception;

import com.dhl.chatbot.logging.TraceLogger;

/**
 * 
 * @author Prashant Padmanabhan
 *
 */
public class DHLException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static TraceLogger logger = new TraceLogger(DHLException.class);
	
	public static int dccErrorCode = 100;
	public static final String ENCRYPTION_DECRYPTION_OPERATION_ERROR_CODE = String.valueOf(dccErrorCode++);
	public static final String ENCRYPTION_DECRYPTION_OPERATION_MESSAGE = "Encrption/Decryption operation resulted in exception";
	
	public static final String FILE_OPERATION_ERROR_CODE = String.valueOf(dccErrorCode++);
	public static final String FILE_OPERATION_MESSAGE = "File handling operation resulted in exception";

	public static final String OPERATION_ERROR_CODE = String.valueOf(dccErrorCode++);
	public static final String OPERATION_MESSAGE = "Operation resulted in exception";

	
	public DHLException (String errorCode, String errorMessage, Exception e) {
		e.printStackTrace();
		logger.logInfo("An exception occurred ErrorCode : " + errorCode + ", Error Message : " + errorMessage);
		logger.logError(e);
	}
}