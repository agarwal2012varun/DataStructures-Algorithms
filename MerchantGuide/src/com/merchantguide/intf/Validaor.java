package com.merchantguide.intf;

public interface Validaor {

	/**
	 * @param ch
	 * @param previousCh
	 * @return
	 * 
	 *         This method validates the repeating and non-repeating
	 *         characters,how many times they c an be repeated as per the rules
	 */
	public boolean validate(Character ch, Character previousCh);

	/**
	 * @param ch
	 * @param nextCh
	 * @return
	 * 
	 *         This method validates the subtraction logic which number can be
	 *         subtacted with whom
	 */
	public boolean isSubtractionValid(Character ch, Character nextCh);

	/**
	 * Clears the cache which is maintained while processing a question
	 */
	public void clearCache();

}
