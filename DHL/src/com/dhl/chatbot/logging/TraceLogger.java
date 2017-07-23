package com.dhl.chatbot.logging;

/**
 * 
 * @author Prashant Padmanabhan
 *
 */
public class TraceLogger {
	
	private ILogger logger = null;
	
	@SuppressWarnings("unchecked")
	public TraceLogger(Class class1) {
		logger = new ExternalLogger(class1);
	}

	public void logError(Exception e) {
		logger.logError(e);
	}
	
	public void logInfo(String message) {
		logger.logInfo(message);
	}
	
	public void logFatal(String message, Throwable t) {
		logger.logFatal(message, t);
	}
	
	public void logDebug(String message) {
		logger.logDebug(message);
	}
}
