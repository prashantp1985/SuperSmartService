package com.dhl.chatbot.classification;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 
 * @author Prashant Padmanabhan
 *
 */
public class WordCalculator {
	
	public int getUniqueWordsCount(List<Document> documentList) {
		Set<String> uniqueWordsSet = new HashSet<String>();
		for (Document document : documentList) {
			uniqueWordsSet.addAll(document.getWords());
		}
		return uniqueWordsSet.size();
	}
	
	public int getWordCount(List<Document> documentList, String sampleWord, boolean isCaseSensitive) {
		int count = 0;
		for (Document document : documentList) {
			for (String word : document.getWords()) {
				if (isCaseSensitive) {
					if (word.equals(sampleWord)) {
						count++;
					}	
				} else {
					if (word.equalsIgnoreCase(sampleWord)) {
						count++;
					}	
				}
			}
		}
		return count;
	}

	public int getTotalWordCount(List<Document> documentList) {
		int count = 0;
		for (Document document : documentList) {
			count = count + document.getWords().size();
		}
		return count;
	}
	
}
