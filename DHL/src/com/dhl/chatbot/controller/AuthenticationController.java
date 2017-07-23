package com.dhl.chatbot.controller;


import com.dhl.chatbot.entities.UserDetails;
import com.dhl.chatbot.logging.TraceLogger;
import com.dhl.chatbot.persistence.Repository;

/**
 * 
 * @author Prashant Padmanabhan
 *
 */
public class AuthenticationController {
	
	public boolean doLogin(UserDetails userDetails) {
		boolean loginResult = false;
    	UserDetails userDetailsNew = new UserDetails();
    	userDetailsNew.setUserName(userDetails.getUserName());
		try {
			userDetailsNew = (UserDetails) Repository.getInstance().load(userDetailsNew);
	    	if(userDetails.isEqualTo(userDetailsNew)) {
	    		loginResult = true;
	    		userDetails.setUserType(userDetailsNew.getUserType());
	    	} 
		} catch (Exception e) {
			new TraceLogger(AuthenticationController.class).logError(e);
		}
		return loginResult;
	}
	

}
