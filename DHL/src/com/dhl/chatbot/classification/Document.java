package com.dhl.chatbot.classification;

import java.util.List;

/**
 * 
 * @author Prashant Padmanabhan
 *
 */
public class Document {
	private List<String> words = null;
	
	private String documentClass = null;
	
	public Document() {
		
	}
	
	public Document(String documentClass, List<String> words) {
		super();
		this.documentClass = documentClass;
		this.words = words;
	}
	public List<String> getWords() {
		return words;
	}
	public void setWords(List<String> words) {
		this.words = words;
	}
	public String getDocumentClass() {
		return documentClass;
	}
	public void setDocumentClass(String documentClass) {
		this.documentClass = documentClass;
	}
}
