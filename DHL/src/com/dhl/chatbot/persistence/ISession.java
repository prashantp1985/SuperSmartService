/**
 * This class indicates ISession.java
 */
package com.dhl.chatbot.persistence;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author Prashant Padmanabhan
 *
 */
public interface ISession {
	public void insert(Object object) throws Exception;
	
	public void delete(Object object) throws Exception;
	
	public void update(Object object) throws Exception;
	
	public Object load(Object object1) throws Exception; 
	
	public Object get(Object object1) throws Exception; 
	
	public List<Object> executeQuery(String queryName, Map<String, Object> parameterList) throws Exception; 
	
	public int executeUpdate(String queryName, Map<String, Object> parameterList) throws Exception; 
}
