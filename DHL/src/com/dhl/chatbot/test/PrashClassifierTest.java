package com.dhl.chatbot.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dhl.chatbot.classification.Document;
import com.dhl.chatbot.classification.PrashClassifier;

import junit.framework.TestCase;

/**
 * 
 * @author Prashant Padmanabhan
 *
 */
public class PrashClassifierTest extends TestCase {

	public void testA() {
		PrashClassifier naiveBayesClassifier = new PrashClassifier();
		Map<String, List<Document>> map = new HashMap<String, List<Document>>();
		List<Document> yesdocumentList = new ArrayList<Document>();
		List<Document> nodocumentList = new ArrayList<Document>();
		
		Document testDocument = new Document();
		List<String> list = new ArrayList<String>();
		list.add("Chinese");
		list.add("Chinese");
		list.add("Chinese");
		list.add("Tokyo");
		list.add("Japan");
		testDocument.setWords(list);
		
		Document yesDocument1 = new Document();
		List<String> yesDocumentList1 = new ArrayList<String>();
		yesDocumentList1.add("Chinese");
		yesDocumentList1.add("Beijing");
		yesDocumentList1.add("Chinese");
		yesDocument1.setWords(yesDocumentList1);
		yesDocument1.setDocumentClass("yes");
		
		Document yesDocument2 = new Document();
		List<String> yesDocumentList2 = new ArrayList<String>();
		yesDocumentList2.add("Chinese");
		yesDocumentList2.add("Chinese");
		yesDocumentList2.add("Sanghai");
		yesDocument2.setWords(yesDocumentList2);
		yesDocument2.setDocumentClass("yes");
		
		Document yesDocument3 = new Document();
		List<String> yesDocumentList3 = new ArrayList<String>();
		yesDocumentList3.add("Chinese");
		yesDocumentList3.add("Macao");
		yesDocument3.setWords(yesDocumentList3);
		yesDocument3.setDocumentClass("yes");
		
		yesdocumentList.add(yesDocument1);
		yesdocumentList.add(yesDocument2);
		yesdocumentList.add(yesDocument3);
		
		
		Document noDocument1 = new Document();
		List<String> noDocumentList1 = new ArrayList<String>();
		noDocumentList1.add("Tokyo");
		noDocumentList1.add("Japan");
		noDocumentList1.add("Chinese");
		noDocument1.setWords(noDocumentList1);
		noDocument1.setDocumentClass("no");
		
		nodocumentList.add(noDocument1);
		
		map.put("yes", yesdocumentList);
		map.put("no", nodocumentList);
		
		
		System.out.println(naiveBayesClassifier.classify(map, testDocument, 5, true));
	}
	
	public void testB() {
		PrashClassifier naiveBayesClassifier = new PrashClassifier();
		Map<String, List<Document>> map = new HashMap<String, List<Document>>();
		List<Document> yesdocumentList = new ArrayList<Document>();
		List<Document> nodocumentList = new ArrayList<Document>();
		
		Document testDocument = new Document();
		List<String> list = new ArrayList<String>();
		list.add("Taiwan");
		list.add("Taiwan");
		list.add("Sapporo");
		testDocument.setWords(list);
		
		Document yesDocument1 = new Document();
		List<String> yesDocumentList1 = new ArrayList<String>();
		yesDocumentList1.add("Taipei");
		yesDocumentList1.add("Taiwan");
		yesDocument1.setWords(yesDocumentList1);
		yesDocument1.setDocumentClass("yes");
		
		Document yesDocument2 = new Document();
		List<String> yesDocumentList2 = new ArrayList<String>();
		yesDocumentList2.add("Macao");
		yesDocumentList2.add("Taiwan");
		yesDocumentList2.add("Shanghai");
		yesDocument2.setWords(yesDocumentList2);
		yesDocument2.setDocumentClass("yes");
		
		
		
		yesdocumentList.add(yesDocument1);
		yesdocumentList.add(yesDocument2);
		
		
		Document noDocument1 = new Document();
		List<String> noDocumentList1 = new ArrayList<String>();
		noDocumentList1.add("Japan");
		noDocumentList1.add("Sapporo");
		noDocument1.setWords(noDocumentList1);
		noDocument1.setDocumentClass("no");
		
		Document noDocument2 = new Document();
		List<String> noDocumentList3 = new ArrayList<String>();
		noDocumentList3.add("Sapporo");
		noDocumentList3.add("Osaka");
		noDocumentList3.add("Taiwan");
		noDocument2.setWords(noDocumentList3);
		noDocument2.setDocumentClass("no");
		
		
		nodocumentList.add(noDocument1);
		nodocumentList.add(noDocument2);
		
		map.put("yes", yesdocumentList);
		map.put("no", nodocumentList);
		
		
		System.out.println(naiveBayesClassifier.classify(map, testDocument, 5, true));
	}
	
	
	public void testC() {
		PrashClassifier naiveBayesClassifier = new PrashClassifier();
		Map<String, List<Document>> map = new HashMap<String, List<Document>>();
		List<Document> loandocumentList = new ArrayList<Document>();
		List<Document> casadocumentList = new ArrayList<Document>();
		List<Document> tddocumentList = new ArrayList<Document>();
		
		Document testDocument = new Document();
		List<String> list = new ArrayList<String>();
		list.add("Charge");
		list.add("Term");
		list.add("Maturity");
		testDocument.setWords(list);
		
		Document yesDocument1 = new Document();
		List<String> yesDocumentList1 = new ArrayList<String>();
		yesDocumentList1.add("Accrual");
		yesDocumentList1.add("Rate");
		yesDocumentList1.add("Maturity");
		yesDocument1.setWords(yesDocumentList1);
		yesDocument1.setDocumentClass("loan");
		
		Document yesDocument2 = new Document();
		List<String> yesDocumentList2 = new ArrayList<String>();
		yesDocumentList2.add("Term");
		yesDocumentList2.add("Schedule");
		yesDocumentList2.add("Charge");
		yesDocument2.setWords(yesDocumentList2);
		yesDocument2.setDocumentClass("loan");
		
		loandocumentList.add(yesDocument1);
		loandocumentList.add(yesDocument2);
		
		
		Document noDocument1 = new Document();
		List<String> noDocumentList1 = new ArrayList<String>();
		noDocumentList1.add("Saving");
		noDocumentList1.add("Rate");
		noDocumentList1.add("Charge");
		noDocument1.setWords(noDocumentList1);
		noDocument1.setDocumentClass("casa");
		
		casadocumentList.add(noDocument1);
		
		
		Document tdDocument1 = new Document();
		List<String> tdDocumentList1 = new ArrayList<String>();
		tdDocumentList1.add("Rate");
		tdDocumentList1.add("Term");
		tdDocumentList1.add("Maturity");
		tdDocument1.setWords(tdDocumentList1);
		tdDocument1.setDocumentClass("td");
		
		tddocumentList.add(tdDocument1);
		
		map.put("loan", loandocumentList);
		map.put("casa", casadocumentList);
		map.put("td", tddocumentList);
		
		
		System.out.println(naiveBayesClassifier.classify(map, testDocument, 5, true));
	}

}
