package com.dhl.chatbot.persistence;

/**
 * 
 * @author Prashant Padmanabhan
 *
 */
public class SQLQueries {
	public static String USER_VALIDATION = "select * from user_details " +
			"where user_id = ? and user_password = ?";
	
	
	
	
}
