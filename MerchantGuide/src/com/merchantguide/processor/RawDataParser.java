package com.merchantguide.processor;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import com.merchantguide.data.InputDataSet;
import com.merchantguide.util.MerchantUtilConstants;

public class RawDataParser {

	/**
	 * @return
	 * @throws IOException
	 * 
	 *             This method reads the input file present in the resources
	 *             folder and parses the data into appropriate data Structure
	 *             which will be used for processing of the result.
	 *             
	 *             
	 */
	public static InputDataSet buildRawData() throws IOException {
		InputDataSet data = new InputDataSet();
		String file = getFileInstance();
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line;
		while ((line = br.readLine()) != null) {
			String[] temp = line.split(" ");
			if (line.endsWith(MerchantUtilConstants.QUES)) {
				data.getQuestions().add(line);
			} else if (line.toLowerCase().endsWith(
					MerchantUtilConstants.CREDITS)) {
				data.getCreditsInfo().add(line);
			} else if (temp.length == 3
					&& temp[1].toLowerCase().equals(MerchantUtilConstants.IS)) {

				data.getInputValues().put(temp[0].toLowerCase(),
						temp[2].charAt(0));
			}
		}

		return data;
	}

	private static String getFileInstance() {
		return InputDataSet.class.getClassLoader()
				.getResource(MerchantUtilConstants.FILE_NAME).getFile();
	}

}
