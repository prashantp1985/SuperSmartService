package com.dhl.chatbot.test;

import com.dhl.chatbot.entities.UserDetails;
import com.dhl.chatbot.logging.TraceLogger;
import com.dhl.chatbot.persistence.Repository;

import junit.framework.TestCase;

/**
 * 
 * @author Prashant Padmanabhan
 *
 */
public class RepositoryTest extends TestCase {
	
	private static TraceLogger logger = new TraceLogger(RepositoryTest.class);
	
	public void testLoad() throws Exception {
		UserDetails userDetails = new UserDetails();
		userDetails.setUserName("prashant");
		UserDetails userDetailsNew = (UserDetails) Repository.getInstance().load(userDetails);
		logger.logInfo(userDetailsNew.getPasswordString());
	}
	
}
