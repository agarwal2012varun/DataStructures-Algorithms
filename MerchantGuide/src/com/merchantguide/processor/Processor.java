package com.merchantguide.processor;

import java.util.Map;
import java.util.Set;

import com.merchantguide.converter.RomanToNumeralConverter;
import com.merchantguide.data.InputDataSet;
import com.merchantguide.intf.Converter;
import com.merchantguide.util.MerchantGuideUtil;
import com.merchantguide.util.MerchantUtilConstants;

public class Processor {

	/**
	 * Converter interface is responsible for converting Romal numbers to its
	 * coreesponding integers MerchantGuideUtil is the util class containing
	 * methods for finding indexs or other util activities
	 */
	private Converter converter;
	private InputDataSet dataSet;
	private MerchantGuideUtil merchantUtil;

	public Processor(InputDataSet dataSet) {
		this.dataSet = dataSet;
		merchantUtil = new MerchantGuideUtil();
		converter = new RomanToNumeralConverter();
	}

	/**
	 * This is the method which process the entire flow for the application.
	 */
	public void startProcessing() {
		preProcess();
		answerQueries();
	}

	/**
	 * After all the preprocessing, the method validates the dataSet and update
	 * the result for the entire queries.
	 * 
	 */
	private void answerQueries() {
		for (String question : dataSet.getQuestions()) {
			String[] temp = question.split(" ");
			int isIndex = merchantUtil.searchIndex(temp,
					MerchantUtilConstants.IS);
			if (isIndex == -1) {
				System.out.println(MerchantUtilConstants.NO_IDEA);
			} else {
				int quesIndex = merchantUtil.searchIndex(temp,
						MerchantUtilConstants.QUES);
				if (merchantUtil.isValidQuesion(temp, isIndex + 1,
						quesIndex - 1, dataSet.getDecimalValues(),
						dataSet.getInputValues())) {
					String romanNumber = merchantUtil.formRomanNumeral(temp,
							isIndex + 1, quesIndex - 1,
							dataSet.getInputValues());
					int number = converter.convertRomanToDecimal(romanNumber);
					boolean contains = merchantUtil.containsMetalNames(temp,
							dataSet.getDecimalValues(), isIndex + 1,
							quesIndex - 1);
					if (contains) {
						number = updateResult(number, temp,
								dataSet.getDecimalValues(), isIndex + 1,
								quesIndex - 1);
					}
					print(temp, isIndex + 1, quesIndex - 1, number, contains);
				} else {
					System.out.println(MerchantUtilConstants.NO_IDEA);
				}
			}
		}
	}

	private void print(String[] temp, int isIndex, int quesIndex, int number,
			boolean contains) {
		for (int i = isIndex; i <= quesIndex; i++) {
			System.out.print(temp[i] + " ");
		}
		if (contains)
			System.out.print("is " + number + " Credits");
		else
			System.out.print("is " + number);

		System.out.println();
	}

	/**
	 * @param number
	 * @param temp
	 * @param decimalValues
	 * @param isIndex
	 * @param quesIndex
	 * @return
	 * 
	 *         This method is called from the answer queries method when while
	 *         generating the result. If there are metals also for that
	 *         updateResult is responsible for updating the result of particular
	 *         methods.
	 */
	private int updateResult(int number, String[] temp,
			Map<String, Float> decimalValues, int isIndex, int quesIndex) {
		if (number == 0)
			number = 1;
		for (int i = isIndex; i <= quesIndex; i++) {
			if (decimalValues.containsKey(temp[i].toLowerCase())) {
				number = number
						* decimalValues.get(temp[i].toLowerCase()).intValue();
			}
		}
		return number;
	}

	/**
	 * This method processes raw data and finds the cost of the metals and
	 * updating those costs in proper dataStructures.
	 */
	private void preProcess() {
		Set<String> credits = dataSet.getCreditsInfo();
		for (String credit : credits) {
			String[] temp = credit.split(" ");
			int isIndex = merchantUtil.searchIndex(temp,
					MerchantUtilConstants.IS);
			if (isIndex == -1) {
				throw new IllegalAccessError(
						MerchantUtilConstants.INDEX_NOT_FOUND);
			}

			float decimalValue = Float.parseFloat(temp[isIndex + 1]);
			String key = temp[isIndex - 1];
			String romanNumeral = merchantUtil.formRomanNumeral(temp, 0,
					isIndex - 1, dataSet.getInputValues());
			float value = converter.convertRomanToDecimal(romanNumeral);
			dataSet.getDecimalValues().put(key.toLowerCase(),
					decimalValue / value);
		}

	}

}
