package com.dhl.chatbot.authentication;

import com.dhl.chatbot.exception.DHLException;

/**
 * 
 * @author Prashant Padmanabhan
 *
 */
public interface IAuthentication {
	
	public byte[] encrypt(String input) throws DHLException;

	public String decrypt(byte[] encryptionBytes) throws DHLException;
	
}
