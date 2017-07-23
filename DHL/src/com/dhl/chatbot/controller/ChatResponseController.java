package com.dhl.chatbot.controller;

import java.util.List;
import java.util.Map;

import com.dhl.chatbot.classification.ClassfierResult;
import com.dhl.chatbot.classification.ClassifierFactory;
import com.dhl.chatbot.classification.Document;
import com.dhl.chatbot.classification.DocumentReader;
import com.dhl.chatbot.classification.IClassifier;
import com.dhl.chatbot.entities.OrderDetails;
import com.dhl.chatbot.exception.DHLException;
import com.dhl.chatbot.logging.TraceLogger;
import com.dhl.chatbot.persistence.Repository;

/**
 * 
 * @author Prashant Padmanabhan
 *
 */
public class ChatResponseController {
	
	public String getResponse(String classifierType,  
			String testQuery, int precision, boolean isCaseSensitive) throws DHLException {
		if (testQuery.indexOf("DHL") == -1 && testQuery.indexOf("dhl") == -1) {
			IClassifier classfier = ClassifierFactory.getInstance().getClassifier(classifierType);
			DocumentReader documentReader = new DocumentReader();
			Map<String, List<Document>> documentMap = documentReader.read();
			Document testDocument = documentReader.read(testQuery);
			ClassfierResult classfierResult = classfier.classify(documentMap, testDocument, precision, isCaseSensitive);
			return classfierResult.getResultClass();
		} else {
			return fetchOrder(testQuery);
		}
	}
	
	private String fetchOrder(String testQuery) {
		String response = null;
		try {
			String valuesArray[] = testQuery.split(" ");
			OrderDetails orderDetails = null;
			for (String value : valuesArray) {
				if (value.indexOf("DHL") != -1 || value.indexOf("dhl") != -1) {
					orderDetails = new OrderDetails();
					orderDetails.setOrderNo(value);
					break;
				}
			}
			
			
			orderDetails = (OrderDetails) Repository.getInstance().get(orderDetails);
	         
			if (orderDetails != null) {
				response = "Order Details: " + orderDetails.getOtherDetails() + "\n, Order Status: " + orderDetails.getOrderStatus();
			} else {
				response = "I am sorry, Order details not found.Kindly check your order number again";
			}
		    
		} catch (Exception e) {
			new TraceLogger(ChatResponseController.class).logError(e);
		}
		return response;
	}
	
}
