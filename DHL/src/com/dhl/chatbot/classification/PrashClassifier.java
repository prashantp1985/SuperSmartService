package com.dhl.chatbot.classification;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author Prashant Padmanabhan
 *
 */
public class PrashClassifier implements IClassifier {
	public final byte smoothing = 1;
	
	public ClassfierResult classify(Map<String, List<Document>> documentMap, Document testDocument, int precision, boolean isCaseSensitive) {
		String resultClass = null;
		StringBuilder computation = new StringBuilder();
		double maxValue = 0.0;
		StringBuilder stringBuffer = new StringBuilder("#.");
		for (int i = 0; i < precision; i++) {
			stringBuffer.append("#");
		}
		DecimalFormat decimalFormatter = new DecimalFormat(stringBuffer.toString());
        decimalFormatter.setRoundingMode(RoundingMode.HALF_UP);
		Map<String, Double> resultMap = new HashMap<String, Double>();
		double totalDocuments = 0;
		List<Document> allDocumentList = new ArrayList<Document>();
		for (String documentClass : documentMap.keySet()) {
			List<Document> classDocumentList = documentMap.get(documentClass);
			allDocumentList.addAll(classDocumentList);
			totalDocuments = totalDocuments + classDocumentList.size();
		}
		WordCalculator wordCalculator = new WordCalculator();
		double uniqueWordsCount = wordCalculator.getUniqueWordsCount(allDocumentList);
		for (String documentClass : documentMap.keySet()) {
			double probablity = 1.0;
			List<Document> classDocumentList = documentMap.get(documentClass);
			int classCount = classDocumentList.size();
			for (String word : testDocument.getWords()) {
				double wordCount = wordCalculator.getWordCount(classDocumentList, word, isCaseSensitive);
				double wordsInClassCount = wordCalculator.getTotalWordCount(classDocumentList);
				double givenProbablity = ((smoothing + wordCount) / (wordsInClassCount + uniqueWordsCount));
				
                givenProbablity = Double.parseDouble(decimalFormatter.format(givenProbablity));
                computation.append(("p(" + word + "|" + documentClass + ") -> " + decimalFormatter.format(givenProbablity)));
				probablity = probablity * givenProbablity;
				probablity = Double.parseDouble(decimalFormatter.format(probablity));
			}
			double classProbablity = classCount / totalDocuments;  
			double posteriorProbability = classProbablity * probablity;
			posteriorProbability = Double.parseDouble(decimalFormatter.format(posteriorProbability));
			resultMap.put(documentClass, posteriorProbability);
			computation.append(documentClass + " -> " + decimalFormatter.format(posteriorProbability));
			if (posteriorProbability > maxValue) {
				maxValue = posteriorProbability;
				resultClass = documentClass;
			}
		}
		return new ClassfierResult(computation.toString(), resultClass,resultMap, precision);
	}
}
