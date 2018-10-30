package array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

import array.Sum.Pair;

/*
 *  Given a non-empty array of integers, return the k most frequent elements.

For example,
Given [1,1,1,2,2,3] and k = 2, return [1,2]. 
 */
public class TopKFrequent {
    public List<Integer> topKFrequent(int[] nums, int k) {
//    	Arrays.sort(nums);
//    	int frequency = 0;
//    	int i=0;
//    	List<Integer> results = new ArrayList<Integer>();
//    	while (i< nums.length-1){
//    		if (nums[i] == nums[i+1]){
//    			frequency++;
//    		}else if (frequency >= k){
//    			results.add(nums[i]);
//    			frequency = 0;
//    		}else{
//    			frequency = 0;
//    		}
//    		i++;
//    	}
//    	if (frequency >= k-1){
//    		results.add(nums[i]);
//    	}
//    	return results;
        //count the frequency for each element
    	Map<Integer,Integer> map = new HashMap<Integer,Integer>();
    	for (int i=0;i<nums.length;i++){
    		if (map.get(nums[i]) == null){
    			map.put(nums[i], 1);
    		}else{
    			map.put(nums[i], map.get(nums[i])+1);
    		}
    	}
 
        // create a min heap
        PriorityQueue<Pair> queue = new PriorityQueue<Pair>(new Comparator<Pair>(){
            public int compare(Pair a, Pair b){
                return a.count-b.count;
            }
        });
 
        //maintain a heap of size k. 
        for(Map.Entry<Integer, Integer> entry: map.entrySet()){
            Pair p = new Pair(entry.getKey(), entry.getValue());
            queue.offer(p);
            if(queue.size()>k){
                queue.poll();
            }
        }
 
        //get all elements from the heap
        List<Integer> result = new ArrayList<Integer>();
        while(queue.size()>0){
            result.add(queue.poll().num);
        }
        //reverse the order
        Collections.reverse(result);
 
        return result;
    }
        public List<Integer> topKFrequent2(int[] nums, int k) {
            List<Integer> result = new ArrayList<Integer>();
     
            HashMap<Integer, Integer> counter = new HashMap<Integer, Integer>();
     
            for(int i: nums){
                if(counter.containsKey(i)){
                    counter.put(i, counter.get(i)+1);
                }else{
                    counter.put(i, 1);
                }    
            }
     
            TreeMap<Integer, Integer> sortedMap = new TreeMap<Integer, Integer>(new ValueComparator(counter));
            sortedMap.putAll(counter);
     
            int i=0;
            for(Map.Entry<Integer, Integer> entry: sortedMap.entrySet()){
                result.add(entry.getKey());
                i++;
                if(i==k)
                    break;
            }
     
            return result;
        
    }
        class ValueComparator implements Comparator<Integer>{
            HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
         
            public ValueComparator(HashMap<Integer, Integer> m){
                map.putAll(m);
            }
         
            public int compare(Integer i1, Integer i2){
                int diff = map.get(i2)-map.get(i1);
         
                if(diff==0){
                    return 1;
                }else{
                    return diff;
                }
            }
        }
        public List<Integer> topKFrequenc1(int[] nums, int k){
        	Map<Integer,Integer> map = new HashMap<Integer,Integer>();
        	for (int i=0;i<nums.length;i++){
        		if (map.get(nums[i]) == null){
        			map.put(nums[i], 1);
        		}else{
        			map.put(nums[i], map.get(nums[i])+1);
        		}
        	}
        	int max = 0;
        	for (Map.Entry<Integer, Integer>entry: map.entrySet()){
        		max = Math.max(entry.getValue(), max);
        	}
        	ArrayList<Integer>[] listArr = (ArrayList<Integer>[])new ArrayList[max];
        	for (int i = 0;i<max;i++){
        		listArr[i] = new ArrayList<Integer>();
        	}
        	for (Map.Entry<Integer, Integer> entry: map.entrySet()){
        		int num = entry.getKey();
        		int count = entry.getValue();
        		listArr[count-1].add(num);
        	}
        	List<Integer> result = new ArrayList<Integer>();
        	for (int j = listArr.length-1;j>=0;j--){
        		if (listArr[j].size() > 0){
        			for (int i:listArr[j]){
        				result.add(i);
        				if (result.size() == k){
        					return result;
        				}
        			}
        		}
        	}
        	return result;
        }

}
