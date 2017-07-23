package com.dhl.chatbot.authentication;

import com.dhl.chatbot.exception.DHLException;
/**
 * 
 * @author Prashant Padmanabhan
 *
 */
public class Authentication {
	
	private static Authentication authentication = new Authentication();
	
	private Authentication() {
		
	}
	
	public static Authentication getInstance() {
		return authentication;
	}
	
	public byte[] encrypt(String password) throws DHLException {
		return EncrypterDecrypter.getInstance().encrypt(password);
	}

	public String decrypt(byte[] password) throws DHLException {
		return EncrypterDecrypter.getInstance().decrypt(password);
	}
	
}
