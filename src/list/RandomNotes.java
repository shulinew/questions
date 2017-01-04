package list;

import java.util.Random;

/*
 * Given a singly linked list, return a random node's value from the linked list. 
 * Each node must have the same probability of being chosen.
 * Reservoir_sampling or Knuth's shuffle
 * https://discuss.leetcode.com/topic/53738/o-n-time-o-1-space-java-solution/3
 */
public class RandomNotes {
	ListNode head;
	Random randomGenerator;
	
	public RandomNotes(ListNode head){
		this.head = head;
		this.randomGenerator = new Random();
		
	}
	public int getRandom(){
		ListNode current = this.head;
		ListNode result = null;
		for (int i = 1; current != null;i++){
			if (randomGenerator.nextInt(i) == 0){
				result = current;
			}
			current = current.next;
		}
		return result.val;
	}
	
	public class ListNode {
	    int val;
	    ListNode next;
		ListNode(int x){
			val = x;
			next = null;
		}
	}
}
