
public class SwapPair {
	/*
	 *  Given a linked list, swap every two adjacent nodes and return its head.
		For example,
		Given 1->2->3->4, you should return the list as 2->1->4->3.
		Your algorithm should use only constant space. You may not modify the 
		values in the list, only nodes itself can be changed. 
	 */
    public ListNode swapPairs(ListNode head) {
    	if (head == null)
    		return head;
    	ListNode newHead = new ListNode(0), curr = head, temp = null, newCurr = newHead;

    	while (curr != null && curr.next != null){
    		temp = curr.next.next;
    		newCurr.next = curr.next;
    		curr.next.next = curr;
    		newCurr = curr;
    		curr = temp;
    	}
    	newCurr.next = curr;
        return newHead.next;
    }

}
