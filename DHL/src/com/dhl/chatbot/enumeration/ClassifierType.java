package com.dhl.chatbot.enumeration;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Prashant Padmanabhan
 *
 */
public enum ClassifierType {

	PRASH("PRASH");

	private final String value;

	ClassifierType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
	public static List<String> getClassifierTypes() {
		List<String> classifierList = new ArrayList<String>();
		classifierList.add(PRASH.toString());
		return classifierList;
	}
	
	public ClassifierType getClassifierType(String classifierType) {
		if (ClassifierType.PRASH.toString().equals(classifierType)) {
			return ClassifierType.PRASH;
		}
		return null;
	}
	
	 /* (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return value;
	}
	
}



