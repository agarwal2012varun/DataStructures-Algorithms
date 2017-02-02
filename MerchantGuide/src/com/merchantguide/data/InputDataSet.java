package com.merchantguide.data;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

public class InputDataSet {

	private Map<String,Character>  inputValues;
	private Set<String> questions;
	private Set<String> creditsInfo;
	private Map<String,Float> decimalValues;
	
	public Set<String> getCreditsInfo() {
		return creditsInfo;
	}

	public Map<String, Float> getDecimalValues() {
		return decimalValues;
	}

	public InputDataSet() {
		inputValues = new HashMap<String,Character>();
		questions = new LinkedHashSet<String>();
		creditsInfo = new HashSet<String>();
		decimalValues = new HashMap<String,Float>();
	}

	public Map<String, Character> getInputValues() {
		return inputValues;
	}

	public Set<String> getQuestions() {
		return questions;
	}
}
