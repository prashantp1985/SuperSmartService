package com.dhl.chatbot.classification;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author Prashant Padmanabhan
 *
 */
public interface IClassifier {
	public ClassfierResult classify(Map<String, List<Document>> documentMap, Document testDocument, int precision, boolean isCaseSensitive);
}
