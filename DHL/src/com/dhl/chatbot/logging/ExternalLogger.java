package com.dhl.chatbot.logging;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.log4j.RollingFileAppender;

/**
 * 
 * @author Prashant Padmanabhan
 *
 */
public class ExternalLogger extends RollingFileAppender implements ILogger {

	private static final String timestampString = "\\{timestamp\\}";
	
	private String date;

	protected String timestampPattern = null;
	
	private Logger logger = null;
	
	public ExternalLogger() {
		
	}
	
	@SuppressWarnings("unchecked")
	public ExternalLogger(Class clazz) {
		logger = Logger.getLogger(clazz);
	}

	private String getFileName(String fileName) {
        if (timestampPattern != null) {
            if (date == null) {
                date = new SimpleDateFormat(timestampPattern).format(Calendar.getInstance().getTime());
            }
            fileName = fileName.replaceAll(timestampString, date);
        }
        return fileName;
	}

	@Override
	public void setFile(String file) {
		file = getFileName(file);
		super.setFile(file);
	}

	@Override
	public void setFile(String fileName, boolean append, boolean bufferedIO,
			int bufferSize) throws IOException {
		fileName = getFileName(fileName);
		super.setFile(fileName, append, bufferedIO, bufferSize);
	}

	public String getTimestampPattern() {
		return timestampPattern;
	}

	public void setTimestampPattern(String timestampPattern) {
		this.timestampPattern = timestampPattern;
		if (super.getFile() != null) {
			setFile(super.getFile());
		}
	}

	@Override
	public void logError(Exception e) {
		logger.error(e);
	}

	@Override
	public void logFatal(String message, Throwable t) {
		if (t == null) {
			logger.fatal(message);
		} else {
			logger.fatal(message, t);
		}
	}

	@Override
	public void logInfo(String message) {
		logger.info(message);
	}

	@Override
	public void logDebug(String message) {
		logger.debug(message);
	}

}