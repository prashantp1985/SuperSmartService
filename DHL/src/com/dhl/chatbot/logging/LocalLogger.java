package com.dhl.chatbot.logging;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 
 * @author Prashant Padmanabhan
 *
 */
public class LocalLogger {
	
	@SuppressWarnings("unchecked")
	public LocalLogger(Class class1) {
		// TODO Auto-generated constructor stub
	}

	public void logError(Exception e) {
		String path = System.getProperty("user.dir");
		try {
			FileWriter fileWriter = new FileWriter(new File(path + "\\error logs\\error log_.txt"));
			String stackTrace = " Message : " + e.getMessage() + " \n Cause : " + e.getCause() + " \n Stack Trace : ";
			for(StackTraceElement s : e.getStackTrace()) {
				stackTrace = stackTrace + s.toString() + "\n ";
			}
			fileWriter.write(stackTrace);
			fileWriter.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		} 
	}
	
	public void logInfo(String e) {
		String path = System.getProperty("user.dir");
		try {
			FileWriter fileWriter = new FileWriter(new File(path + "\\error logs\\error String log_.txt"));
			String stackTrace = "Message : " + e + " Cause : " + e + " Stack Trace : ";
			fileWriter.write(stackTrace);
			fileWriter.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		} 
	}
	
  
}
