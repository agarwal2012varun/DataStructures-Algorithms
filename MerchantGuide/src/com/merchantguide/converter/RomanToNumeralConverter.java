package com.merchantguide.converter;

import java.util.HashMap;
import java.util.Map;

import com.merchantguide.intf.Converter;
import com.merchantguide.intf.Validaor;
import com.merchantguide.util.MerchantUtilConstants;

public class RomanToNumeralConverter implements Converter{

	private Validaor validator;
	
	static Map<Character,Integer> romanValues;
	static {
		romanValues = new HashMap<Character,Integer>();
		romanValues.put('I', 1);
		romanValues.put('V', 5);
		romanValues.put('X', 10);
		romanValues.put('L', 50);
		romanValues.put('C', 100);
		romanValues.put('D', 500);
		romanValues.put('M', 1000);
	}
	
	public RomanToNumeralConverter() {
		validator = new RomanValidator();
	}
	
	@Override
	public int convertRomanToDecimal(String romanNumber) {
		validator.clearCache();
		int res = 0;
		int n = romanNumber.length();
		for(int i=0;i<n;i++) {
			Character temp = romanNumber.charAt(i);
			Character previousCh;
			if(i==0) {
				previousCh = Character.MIN_VALUE;
			}
			else {
				previousCh = romanNumber.charAt(i-1);
			}
			boolean isValid = validator.validate(temp,previousCh);
			if(!isValid) {
				throw new IllegalArgumentException(MerchantUtilConstants.NOT_VALID);
			}
			int value = romanValues.get(temp);
			if(i+1 < n) {
				int valueNext = romanValues.get(romanNumber.charAt(i+1));
				if(value >= valueNext) {
					res =  res + value;
				}
				else {
					boolean isSubtractionValid = validator.isSubtractionValid(temp, romanNumber.charAt(i+1));
					if(!isSubtractionValid) {
						throw new IllegalArgumentException(MerchantUtilConstants.NOT_VALID);
					}
					res = res + valueNext - value;
					i++;
				}
			}
			else {
				res = res + value;
                i++;
			}
		}
		return res;
	}

}
