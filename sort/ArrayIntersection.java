package sort;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
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
    /*
     * Given two arrays, write a function to compute their intersection. 
     * Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2,2]. 
     */
    public int[] intersection2(int[] num1, int[] num2){
    	Arrays.sort(num1);
    	Arrays.sort(num2);
    	int size = Math.min(num1.length, num2.length);
    	int[] result = new int[size];
    	int i=0,j=0,k=0;
    	while (i < num1.length && j < num2.length){
    		if (num1[i] == num2[j]){
    			result[k] = num1[i];
    			i++;
    			j++;
    			k++;
    		}else if (num1[i] < num2[j]){
    			i++;
    		}else{
    			j++;
    		}
    	}
    	int [] newArray = new int[k];
    	newArray = Arrays.copyOfRange(result, 0, k);
    	return newArray;
    }
    public int[] intersection3(int[] nums1, int[] nums2){
    	Map<Integer, Integer> map = new HashMap<Integer, Integer>();
    	int result[] = new int[Math.min(nums1.length, nums2.length)];
    	for (int i = 0; i< nums1.length;i++){
    		int num = map.getOrDefault(nums1[i], 0) +1;
    		map.put(nums1[i], num);
    	}
    	int j=0;
    	for (int i =0;i<nums2.length;i++){
    		if (map.containsKey(nums2[i]) && map.get(nums2[i]) != 0){
    			result[j] = nums2[i];
    			map.put(nums2[i], map.get(nums2[i]) -1);
    			j++;
    		}
    	}
    	int [] newArray = new int[j];
    	newArray = Arrays.copyOfRange(result, 0, j);
    	return newArray;
    }
    /*
     * java 8
     *  Map<Integer, Long> map = Arrays.stream(nums2).boxed().collect(Collectors.groupingBy(e->e, Collectors.counting()));
    return Arrays.stream(nums1).filter(e ->{
        if(!map.containsKey(e)) return false;
        map.put(e, map.get(e) - 1);
        if(map.get(e) == 0) map.remove(e);
        return true;
    }).toArray();
     */

}
