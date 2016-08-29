package sort;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/*
 * Given two arrays, write a function to compute their intersection. 
 * Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2]. 
 */
public class ArrayIntersection {
    public int[] intersection(int[] nums1, int[] nums2) {
    	Set<Integer> set = new HashSet<Integer>();
    	Set<Integer> interSet = new HashSet<Integer>();
    	for (int i = 0; i<nums1.length;i++){
    		set.add(nums1[i]);
    	}
    	for (int i=0;i<nums2.length;i++){
    		if(set.contains(nums2[i])){
    			interSet.add(nums2[i]);
    		}
    	}
        int[] result = new int[interSet.size()];
        int i  = 0;
        for (Integer num: interSet){
        	result[i++] = num;
        }
        return result;
    }
    
    public int[] intersection1(int[] nums1, int[] nums2) {
    	Set<Integer> set = new HashSet<Integer>();
    	Arrays.sort(nums1);
    	Arrays.sort(nums2);
    	int i=0, j=0;
    	while (i < nums1.length && j < nums2.length){
    		if (nums1[i] < nums2[j]){
    			i++;
    		}else if (nums1[i] > nums2[j]){
    			j++;
    		}else {
    			set.add(nums1[i]);
    			i++;
    			j++;
    		}
    	}
        int[] result = new int[set.size()];
        i  = 0;
        for (Integer num: set){
        	result[i++] = num;
        }
        return result;
    }
    
    public int[] intersectionWithBinarySearch(int[] nums1, int[] nums2) {
    	Set<Integer> set = new HashSet<Integer>();
    	Arrays.sort(nums1);
    	int i=0;
    	while (i < nums2.length){
    		if (binarySearch(nums2[i], nums1) != -1){
    			set.add(nums2[i]);
    		}
    	}
        int[] result = new int[set.size()];
        i  = 0;
        for (Integer num: set){
        	result[i++] = num;
        }
        return result;
    }
    private int binarySearch(int num, int[] array){
    	int low = 0;
    	int high = array.length - 1;
    	
    	while(high >= low){
    		int pos = low + (high-low)/2;
    		if (array[pos] > num){
    			high = pos - 1;
    		}else if (array[pos] < num){
    			low = pos + 1;
    		}else{
    			return pos;
    		}
    	}
    	return -1;
    }

}
