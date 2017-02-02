package com.merchantguide.util;

import java.util.Map;

public class MerchantGuideUtil {

	public String formRomanNumeral(String[] temp, int start, int end,Map<String,Character> inputValues) {

		String s = "";
		for (int i = start; i <= end; i++) {
			if(inputValues.containsKey(temp[i].toLowerCase()))
			{
				String s1 =getValue(temp[i],inputValues);
				s = s.concat(s1);
			}
		}
		return s;
	}

	private String getValue(String key,Map<String,Character> inputValues) {
		return inputValues.get(key.toLowerCase()).toString();
	}

	public int searchIndex(String[] temp, String key) {
		int n = temp.length;
		int isIndex = -1;
		for (int i = 0; i < n; i++) {
			if (temp[i].toLowerCase().equals(key.toLowerCase())) {
				isIndex = i;
				break;
			}
		}
		return isIndex;
	}
	 
	public boolean containsMetalNames(String[] temp, Map<String, Float> decimalValues,int isIndex,int quesIndex) {
		boolean contains = false;
		for(int i =isIndex;i <=quesIndex;i++) {
			if(decimalValues.containsKey(temp[i].toLowerCase())) {
				contains = true;
				break;
			}
		}
		return contains;
	}

	public boolean isValidQuesion(String[] temp, int isIndex, int quesIndex, Map<String, Float> decimalValues, Map<String, Character> inputValues) {
		boolean isValid=true;
		if(isIndex > quesIndex) {
			return false;
		}
		for(int i=isIndex;i<=quesIndex;i++) {
			if(!decimalValues.containsKey(temp[i].toLowerCase())) {
				if(!inputValues.containsKey(temp[i].toLowerCase())) {
					isValid = false;
					break;
				}
			}
		}
		return isValid;
	}
}
