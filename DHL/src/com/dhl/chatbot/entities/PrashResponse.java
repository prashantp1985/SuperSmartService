package com.dhl.chatbot.entities;
import java.io.Serializable;

//import org.hibernate.Hibernate;


/**
 * 
 * @author Prashant Padmanabhan
 *
 */
@SuppressWarnings("serial")
public class PrashResponse implements Serializable, Cloneable {

	private String queryText = null;
	
	private String responseText = null;
	
	
	public PrashResponse() {
		
	}
	public PrashResponse(String queryText, String responseText) {
		super();
		this.queryText = queryText;
		this.responseText = responseText;
	}

	public String getQueryText() {
		return queryText;
	}

	public void setQueryText(String queryText) {
		this.queryText = queryText;
	}

	public String getResponseText() {
		return responseText;
	}

	public void setResponseText(String responseText) {
		this.responseText = responseText;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((queryText == null) ? 0 : queryText.hashCode());
		result = prime * result + ((responseText == null) ? 0 : responseText.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PrashResponse other = (PrashResponse) obj;
		if (queryText == null) {
			if (other.queryText != null)
				return false;
		} else if (!queryText.equals(other.queryText))
			return false;
		if (responseText == null) {
			if (other.responseText != null)
				return false;
		} else if (!responseText.equals(other.responseText))
			return false;
		return true;
	}
	
	
}
