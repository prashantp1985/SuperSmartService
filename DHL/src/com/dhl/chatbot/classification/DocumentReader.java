package com.dhl.chatbot.classification;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.dhl.chatbot.entities.PrashResponse;
import com.dhl.chatbot.exception.DHLException;
import com.dhl.chatbot.logging.TraceLogger;
import com.dhl.chatbot.persistence.Repository;

/**
 * 
 * @author Prashant Padmanabhan
 *
 */
public class DocumentReader {
	
	public static Map<String, List<Document>> classToDocumentMap = new HashMap<>(); 
	
	public Map<String, List<Document>> read() throws DHLException {
		if (classToDocumentMap != null) {
			classToDocumentMap = new HashMap<>(); 
			List<PrashResponse> prashResponseList = new ArrayList<>();  
			try {
				List<Object> responseList = Repository.getInstance().executeQuery("selectAllResponses", null);
		          
			    Iterator<Object> itr = responseList.iterator();  
			    while(itr.hasNext()){  
			    	PrashResponse prashResponse = (PrashResponse) itr.next();  
			    	prashResponseList.add(prashResponse);
			    }  
			    
			    
			} catch (Exception e) {
				new TraceLogger(DocumentReader.class).logError(e);
			}
			
			for (PrashResponse prashResponse : prashResponseList) {
				List<Document> documentList = new ArrayList<Document>();
				String documentClass = prashResponse.getResponseText();
				try {
					List<String> wordsList = new ArrayList<String>();
					String line = prashResponse.getQueryText();
					String[] words = line.split(" ");
					for (String word : words) {
						word = word.trim();
						if (! "".equals(word)) {
							wordsList.add(word);
						}
					}
					if (wordsList.isEmpty()) {
						throw new DHLException(DHLException.OPERATION_ERROR_CODE, DHLException.OPERATION_MESSAGE, new Exception());
					} else {
						documentList.add(new Document(documentClass, wordsList));
					}
				} catch (Exception e) {
					throw new DHLException(DHLException.OPERATION_ERROR_CODE, DHLException.OPERATION_MESSAGE, e);
				}
				if (documentList.isEmpty()) {
					throw new DHLException(DHLException.OPERATION_ERROR_CODE, DHLException.OPERATION_MESSAGE, new Exception());
				} else {
					classToDocumentMap.put(documentClass, documentList);	
				}
			}
		}
				
		return classToDocumentMap;
	}
	
	

	public Document read(String queryText) throws DHLException {
		Document document = null;
		try {
			List<String> wordsList = new ArrayList<String>();
			String[] words = queryText.split(" ");
			for (String word : words) {
				word = word.trim();
				if (! "".equals(word)) {
					wordsList.add(word);
				}
			}
			if (wordsList.isEmpty()) {
				throw new DHLException(DHLException.OPERATION_ERROR_CODE, DHLException.OPERATION_MESSAGE, new Exception());
			} else {
				document = new Document(null, wordsList);
			}	
		} catch (Exception e) {
			throw new DHLException(DHLException.OPERATION_ERROR_CODE, DHLException.OPERATION_MESSAGE, e);
		}
		
		return document;
	}
	
}
