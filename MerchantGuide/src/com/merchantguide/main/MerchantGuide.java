package com.merchantguide.main;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.merchantguide.data.InputDataSet;
import com.merchantguide.processor.Processor;
import com.merchantguide.processor.RawDataParser;

public class MerchantGuide {
	private static final Logger LOGGER = Logger.getLogger(MerchantGuide.class.getName());

	/**
	 * @param args
	 * Main Starting point of th application. 
	 * It Parses the data and calls the starProcessing api of the processor instance.
	 */
	public static void main(String[] args) {
		try {
			InputDataSet dataSet = RawDataParser.buildRawData();
			Processor processor = new Processor(dataSet);
			processor.startProcessing();
			
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Exception occur", e);
		}
	}
}
