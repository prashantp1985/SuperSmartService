package com.dhl.chatbot.test;

import junit.framework.TestCase;

import com.dhl.chatbot.authentication.Authentication;
import com.dhl.chatbot.exception.DHLException;

/**
 * 
 * @author Prashant Padmanabhan
 *
 */
public class AuthenticationTest extends TestCase {
	
	public void testEncrypt() throws DHLException {
		byte[] encryptedPassword = Authentication.getInstance().encrypt("prashant");
		StringBuilder string = new StringBuilder("Encrypted Password : "); 
		for (byte value : encryptedPassword) {
			string.append(value + ", ");
		}
		System.out.println(string.substring(0, string.length() - 2));
	}

	public void testDecrypt() throws DHLException {
		byte[] encryptedPassword = Authentication.getInstance().encrypt("prashant");
		System.out.println("Decrypted Password : " + Authentication.getInstance().decrypt(encryptedPassword));
	}
}
