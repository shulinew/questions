package bfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Deque;
import java.util.List;
import java.util.Queue;

/*
 *  There are a total of n courses you have to take, labeled from 0 to n - 1.
	Some courses may have prerequisites, for example to take course 0 you have to first take course 1, 
	which is expressed as a pair: [0,1] Given the total number of courses and a list of prerequisite pairs, 
	return the ordering of courses you should take to finish all courses. There may be multiple correct 
	orders, you just need to return one of them. If it is impossible to finish all courses, return an 
	empty array. 
 */
public class CourseScheduleII {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
    	int [] linkCounts = new int[numCourses];
    	List<List<Integer>> adjests = new ArrayList<List<Integer>>();
    	initilizeGraph(linkCounts, adjests, prerequisites);
        return bfs(linkCounts, adjests);
    }
    
    private void initilizeGraph(int[] linkCounts, List<List<Integer>> adjs, int[][] prerequisites) {
    	int size = linkCounts.length;
    	for (int i = 0; i < size; i++) {
    		adjs.add(new ArrayList<Integer>());
    	}
    	for (int[] pairs: prerequisites) {
    		adjs.get(pairs[1]).add(pairs[0]);
    		linkCounts[pairs[0]]++;
    	}
    }
    private int[] bfs(int[] linkCounts, List<List<Integer>> adjs) {
    	int[] order = new int[linkCounts.length];
    	Queue<Integer> toFinish = new ArrayDeque<Integer>();
    	for (int i = 0; i< linkCounts.length;i++) {
    		if (linkCounts[i] == 0) {
    			toFinish.offer(linkCounts[i]);
    		}
    	}
    	int finished = 0;
    	while ( !toFinish.isEmpty()) {
    		int from = toFinish.poll();
    		order[finished++] = from;
    		for (int course: adjs.get(from)) {
    			if (linkCounts[course]-- == 0) {
    				toFinish.offer(course);
    			}
    		}
    	}
    	return finished == linkCounts.length? order: new int[0];
    }
    @SuppressWarnings("unused")
    private int[] solveDfs(List<List<Integer>> adjs) {
//    	BitSet hasCycle = new BitSet(1);
    	BitSet finished = new BitSet(adjs.size());
    	BitSet onStack = new BitSet(adjs.size());
    	
    	Deque<Integer> order = new ArrayDeque<Integer>();
    	for (int i = adjs.size() -1; i>=0;i++) {
    		if (finished.get(i) == false && hasOrder(i, adjs, finished, onStack, order)) {
    			return new int[0];
    		}
    	}
    	int [] orderArray = new int[adjs.size()];
    	for (int i = 0; !order.isEmpty(); i++) {
    		orderArray[i] = order.pop();
    	}
    	return orderArray;
    }
    
    private boolean hasOrder(int from, List<List<Integer>> adjs, BitSet finished, BitSet onStack, Deque<Integer> order) {
    	finished.set(from);
    	onStack.set(from);
    	for (int to: adjs.get(from)) {
    		if(finished.get(to) == false) {
    			if (hasOrder(to, adjs, finished, onStack, order) == false) {
    				return false;
    			} else if (onStack.get(to) == true) {
    				return false;
    			}
    		}
    	}
    	onStack.clear(from);
    	order.push(from);
    	return true;
    }
    
    

}
