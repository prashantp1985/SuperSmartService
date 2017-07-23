package com.dhl.chatbot.logging;

/**
 * 
 * @author Prashant Padmanabhan
 *
 */
public interface ILogger {

	public void logError(Exception e);
	
	public void logInfo(String e);
	
	public void logDebug(String e);
	
	public void logFatal(String e, Throwable t);
	
}
