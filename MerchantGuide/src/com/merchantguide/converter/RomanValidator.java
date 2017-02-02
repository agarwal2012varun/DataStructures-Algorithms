package com.merchantguide.converter;

import java.util.HashMap;
import java.util.Map;

import com.merchantguide.intf.Validaor;

/*
 * RomanValidator validates the logic such as D", "L", and "V" can never be repeated
 * or the subtraction logic which number can be subtacted with which only.
 * 
 */
public class RomanValidator implements Validaor {

	private Map<Character, Integer> count = new HashMap<Character, Integer>();

	@Override
	public boolean validate(Character ch, Character previousCh) {
		if (count.containsKey(ch)) {
			int value = count.get(ch);
			count.put(ch, value + 1);
		} else {
			count.put(ch, 1);
		}
		boolean isValid = validateNonRepeated(ch);
		if (isValid) {
			isValid = validateRepeatingCharacter(ch, previousCh);
		}
		return isValid;
	}

	/**
	 * @param ch
	 * @param previousCh
	 * @return
	 * 
	 *         This method checks that I ,X, C , M can be repeated only three
	 *         time or more if 3rd and fourth are seperated by smaller values.
	 */
	private boolean validateRepeatingCharacter(Character ch,
			Character previousCh) {
		boolean isValid = true;
		switch (ch) {
		case 'I':
			if (count.get(ch) > 3) {
				if (previousCh == 'I')
					isValid = false;
			}
			break;
		case 'X':
			if (count.get(ch) > 3) {
				if (previousCh == 'X')
					isValid = false;
			}
			break;
		case 'C':
			if (count.get(ch) > 3) {
				if (previousCh == 'C')
					isValid = false;
			}
			break;
		case 'M':
			if (count.get(ch) > 3) {
				if (previousCh == 'M')
					isValid = false;
			}
			break;
		default:
			break;
		}
		return isValid;
	}

	/**
	 * @param ch
	 * @return
	 * 
	 *         It validates character which can never be repeated.
	 */
	private boolean validateNonRepeated(Character ch) {
		boolean isValid = true;
		switch (ch) {
		case 'D':
			if (count.get(ch) > 1) {
				isValid = false;
			}
			break;
		case 'L':
			if (count.get(ch) > 1) {
				isValid = false;
			}
			break;
		case 'V':
			if (count.get(ch) > 1) {
				isValid = false;
			}
			break;
		default:
			break;
		}
		return isValid;
	}

	@Override
	public boolean isSubtractionValid(Character ch, Character nextCh) {
		boolean isValid = true;
		switch (ch) {
		case 'I':
			if (!(nextCh == 'V' || nextCh == 'X')) {
				isValid = false;
			}
			break;
		case 'X':
			if (!(nextCh == 'L' || nextCh == 'C')) {
				isValid = false;
			}
			break;
		case 'C':
			if (!(nextCh == 'D' || nextCh == 'M')) {
				isValid = false;
			}
			break;
		case 'V':
			isValid = false;
			break;
		case 'L':
			isValid = false;
			break;
		case 'D':
			isValid = false;
			break;
		default:
			break;
		}
		return isValid;
	}

	@Override
	public void clearCache() {
		count.clear();
	}

}
