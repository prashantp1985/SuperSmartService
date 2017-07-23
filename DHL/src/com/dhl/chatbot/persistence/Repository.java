package com.dhl.chatbot.persistence;

import java.util.List;
import java.util.Map;

import com.dhl.chatbot.logging.TraceLogger;

/**
 * 
 * @author Prashant Padmanabhan
 *
 */
public class Repository {

	private static Repository repository = new Repository();
	
	private static ISession session = null;
	
	private Repository() {
		
	}
	
	public static Repository getInstance() {
		try {
			session = (ISession) HibernateSession.getInstance();
		} catch (Exception e) {
			new TraceLogger(Repository.class).logError(e);
		}
		return repository;
	}
	
	public void insert(Object object) throws Exception {
		session.insert(object);
	}
	
	public void delete(Object object) throws Exception {
		session.delete(object);	
	}

	public void update(Object object) throws Exception {
		session.update(object);	
	}
	
	public Object load(Object object1) throws Exception {
		return session.load(object1);
	}
	
	public Object get(Object object1) throws Exception {
		return session.get(object1);
	}
	
	public List<Object> executeQuery(String queryName, Map<String, Object> parameterList) throws Exception {
		return session.executeQuery(queryName, parameterList);
	}
	
	public int executeUpdate(String queryName, Map<String, Object> parameterList) throws Exception {
		return session.executeUpdate(queryName, parameterList);
	}
}

