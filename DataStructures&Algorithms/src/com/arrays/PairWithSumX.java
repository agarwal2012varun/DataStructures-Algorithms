package com.arrays;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/* The code is to find whether any pair exists in an array with given sum
 * 
 * Basically we are sorting an array first, and then have two pointers first at the beginning of array
 * and the other at the end of the array and we are comparing whether sum is equal to given sum or not.
 * If sum is lesser then increment the ith pointer and if it is greater then decrementing the jth pointer
 * till my i<j.
 */
public class PairWithSumX {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] temparr = br.readLine().split(" ");
		int[] arr = new int[temparr.length];
		int n = arr.length;
		for(int i = 0;i < n;i++) {
			arr[i] = Integer.parseInt(temparr[i]);
		}
		int sum = Integer.parseInt(br.readLine());
		sort(arr,0,n-1);
		int i = 0;
		int j = n-1;
		boolean hasSum= false;
		while(i<j) {
			if(arr[i] + arr[j] == sum) {
				hasSum = true;
				break;
			}
			else if(arr[i] + arr[j] < sum)
				i++;
			else
				j--;
		}
		
		if(hasSum)
			System.out.println("Pair exists with given sum");
		else
			System.out.println("Pair does not exists");
		
	}

	private static void sort(int[] arr, int start, int end) {
		if(start < end) {
			int pIndex = partition(arr,start,end);
			sort(arr, start, pIndex-1);
			sort(arr, pIndex+1, end);
		}
	}

	private static int partition(int[] arr, int start, int end) {
		int pIndex = start;
		int pivot = arr[end];
		for(int i=start;i<end;i++) {
			if(arr[i] < pivot) {
				int temp = arr[pIndex];
				arr[pIndex] = arr[i];
				arr[i] = temp;
				pIndex++;
			}
		}
		arr[end] = arr[pIndex];
		arr[pIndex] = pivot;
		return pIndex;
	}
}
