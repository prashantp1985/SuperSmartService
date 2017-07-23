package com.dhl.chatbot.persistence;

import java.lang.reflect.Method;
import java.util.Properties;

import com.dhl.chatbot.logging.TraceLogger;
import com.dhl.chatbot.util.PropertyFileLoader;

/**
 * 
 * @author Prashant Padmanabhan
 *
 */
public class SessionFactory {
	private Class<ISession> clazz = null; 
	private static SessionFactory sessionType = new SessionFactory();

		   public static SessionFactory getInstance() {
				return sessionType;
			}
			
		   /**
			   * Constructor for ReportDriver
			   */
			@SuppressWarnings("unchecked")
			private SessionFactory() {
				try {
					Properties properties = PropertyFileLoader.loadPersistorSessionInputStream();
					clazz = (Class<ISession>) Class.forName((String) properties.get("sessionType"));
				} catch (Exception e) {
					e.printStackTrace();
					new TraceLogger(SessionFactory.class).logError(e);
				}
			}
			
			  public ISession getSession() throws Exception {
				  ISession result = null;
				  Method[] methods = clazz.getDeclaredMethods();
				    for (Method method : methods) {
				    	if ("getInstance".equals(method.getName())) {
				    		 result = (ISession) method.invoke(null, new Object[0]);
				    	}
				    }
				    return result;
				}

}