package com.dhl.chatbot.enumeration;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * 
 * @author Prashant Padmanabhan
 *
 */
public class ErrorMessageType {

     public static final String INVALID_USER_ID = "Invalid User name or Password";
     
     public static final String INVALID_USER_NAME = "Invalid User name";
     
     public static final String INVALID_USER_NAME_INPUT = "User name is blank or consists of only spaces";
     
     public static final String INVALID_PASSWORD = "User name or Password length is blank or consists of only spaces";
     
     public static final String INVALID_INPUT = "Invalid Input Data";
     
     public static final String PASSWORD_CONSTRAINT = "The password should not contain leading or trailing spaces, or = symbol";
     
     public static final String PASSWORD_LENGTH_CONSTRAINT = "The Password should be minimum 6 and maximum 8 characters in length";
     
     public static final String INVALID_INPUT_NUMBER = "Invalid Input. Please enter numbers greater than or equal to zero";
     
     public static final String LIMIT_EXCEEDED = "The limit maximum file limit is 11";
     
     public static final String PROCESSING_ERROR = "A processing error occurred";
}

