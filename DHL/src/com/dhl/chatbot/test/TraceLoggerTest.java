package com.dhl.chatbot.test;

import com.dhl.chatbot.exception.DHLException;
import com.dhl.chatbot.logging.TraceLogger;

import junit.framework.TestCase;

/**
 * 
 * @author Prashant Padmanabhan
 *
 */
public class TraceLoggerTest extends TestCase {
	
	private static TraceLogger logger = new TraceLogger(TraceLoggerTest.class);
	
	public void testLogInfo() throws DHLException {
		logger.logInfo("This is info");
	}
	
	public void testLogFatal() throws DHLException {
		logger.logFatal("This is fatal", new Throwable());
	}
	
	public void testLogDebug() throws DHLException {
		logger.logDebug("This is debug");
	}
	
	public void testLogException() throws DHLException {
		logger.logError(new Exception("This is error"));
	}

}
