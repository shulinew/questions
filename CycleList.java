
public class CycleList {
	
	/*
	 *  Given a linked list, determine if it has a cycle in it.
		Follow up:
		Can you solve it without using extra space? 
	 */
	
    public boolean hasCycle(ListNode head) {
//    	ListNode cur = head,pre = head;
//    	while (cur != null && cur.next != null){
//    		if (cur.next == head )
//    			return true;
//    		cur = cur.next;
//    		pre.next = head;
//    		pre = cur;
//    	}
//    	return false;
    	ListNode fast = head, slow = head;
    	while (fast != null && fast.next != null){
    		fast = fast.next.next;
    		slow = slow.next;
    		if (fast == slow)
    			return true;
    	}
    	return false;
        
    }
    
    /*
     *  Given a linked list, return the node where the cycle begins. 
     *  If there is no cycle, return null.
		Note: Do not modify the linked list.
		Follow up:
		Can you solve it without using extra space? 
     */
    
    public ListNode detectCycle(ListNode head) {
    	ListNode fast = head, slow = head;
    	while (fast != null && fast.next != null){
    		fast = fast.next.next;
    		slow = slow.next;
    		if (fast == slow){
    			ListNode start = head;
    			while(start != slow){
    				slow = slow.next;
    				start = start.next;
    			}
    			return start;
    		}
    	}
    	return null;
        
    }

}
