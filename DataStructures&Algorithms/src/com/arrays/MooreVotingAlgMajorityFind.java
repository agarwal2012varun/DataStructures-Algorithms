package com.arrays;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * The Motive of this algorithm is to find the majority. 
 * We first find the majority element in the array. and then validate whether the element
 * is really in majority or not.
 * 
 * To find we first initialize that the 0th index is the majority one, then we loop from 1 to n-1
 * if the next element is same then increments the cnt otherwise decrement the cnt.
 * If the cnt becomes 0. reinitialize the index ith as the majority one and the cnt to 0.
 * 
 * Now we need to validate whether the element return is rightly in majority or not for that we loop the whole
 * array and makes a counter.
 * If that element exists more that n/2 than that is the majority element
 */

public class MooreVotingAlgMajorityFind {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] tempArr = br.readLine().split(" ");
		int n = tempArr.length;
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(tempArr[i]);
		}

		int maj_elem = findMajority(arr, n);
		validatingMajority(maj_elem, arr, n);
	}

	private static void validatingMajority(int maj_elem, int[] arr, int n) {
		int cnt = 0;
		for (int i = 0; i < n; i++) {
			if (maj_elem == arr[i])
				cnt++;
		}

		if (cnt > n / 2)
			System.out.println(maj_elem);
		else
			System.out.println("NONE");
	}

	private static int findMajority(int[] arr, int n) {
		int maj_index = 0;
		int cnt = 1;
		for (int i = 0; i < n; i++) {
			if (arr[maj_index] == arr[i])
				cnt++;
			else
				cnt--;

			if (cnt == 0) {
				maj_index = i;
				cnt = 1;
			}
		}
		return arr[maj_index];
	}

}
