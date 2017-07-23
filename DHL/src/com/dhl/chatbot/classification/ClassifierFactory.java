package com.dhl.chatbot.classification;

import com.dhl.chatbot.enumeration.ClassifierType;

/**
 * 
 * @author Prashant Padmanabhan
 *
 */
public class ClassifierFactory {
	
	private static ClassifierFactory classifierFactory = new ClassifierFactory();
	
	private ClassifierFactory() {
		
	}
	
	public static ClassifierFactory getInstance() {
		return classifierFactory;
	}
	
	public IClassifier getClassifier(String classifierType) {
		IClassifier classifier = null;
		
		if (ClassifierType.PRASH.getValue().equals(classifierType)) {
			classifier = new PrashClassifier();
		}
		return classifier;
	}
}
