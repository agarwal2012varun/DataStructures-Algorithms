package com.arrays;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.TreeMap;

/* We need to find the Majority Element in the array
 * Condition for Majority Element is that the number should appear more than n/2 times in an array
 * Ex: 3 3 4 2 4 4 2 4 4 Ans : 4
 * Ex: 3 3 4 2 4 4 2 4 Ans: NONE
 */

public class MajorityElement {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] tempArr = br.readLine().split(" ");
		int n = tempArr.length;
		int[] arr = new int[n];
		for(int i = 0;i < n;i++) {
			arr[i] = Integer.parseInt(tempArr[i]);
		}
		int val = Integer.MIN_VALUE;
		Map<Integer, Integer> map = new TreeMap<Integer,Integer>();
		for(int i=0;i<n;i++) {
			if(map.containsKey(arr[i])) {
				int value = map.get(arr[i]);
				value = value + 1;
				if(value > n/2) {
					val = arr[i];
					break;
				}
				else
					map.put(arr[i], value);
				
			}
			else
				map.put(arr[i], 1);
		}
		
		if(val != Integer.MIN_VALUE)
			System.out.println(val);
		else
			System.out.println("NONE");
	}
}
