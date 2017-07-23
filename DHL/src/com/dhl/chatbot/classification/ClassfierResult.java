package com.dhl.chatbot.classification;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Map;

/**
 * 
 * @author Prashant Padmanabhan
 *
 */
public class ClassfierResult {
	
	private String resultClass;
	
	private String computation;
	
	private int precision = 0;
	
	private Map<String, Double> resultMap;

	public ClassfierResult(String computation, String resultClass, Map<String, Double> resultMap, int precision) {
		super();
		this.computation = computation;
		this.resultClass = resultClass;
		this.resultMap = resultMap;
		this.precision = precision;
	}

	public String getResultClass() {
		return resultClass;
	}

	public String getComputation() {
		return computation;
	}

	public Map<String, Double> getResultMap() {
		return resultMap;
	}
	
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Computation result : \n");
		for (String key : resultMap.keySet()) {
			stringBuilder.append("P(" + key + "|test) = " 
					+ formatDecimalValue(resultMap.get(key)) + "\n");
		}
		stringBuilder.append("Test Set Class : " + resultClass + "\n");
		return stringBuilder.toString();
	}
	
	private String formatDecimalValue(double value) {
		StringBuilder stringBuffer = new StringBuilder("#.");
		for (int i = 0; i < precision; i++) {
			stringBuffer.append("#");
		}
		DecimalFormat decimalFormatter = new DecimalFormat(stringBuffer.toString());
        decimalFormatter.setRoundingMode(RoundingMode.HALF_UP);
        
        return decimalFormatter.format(value);
	}
	
}
