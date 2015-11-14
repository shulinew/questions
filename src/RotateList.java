
public class RotateList {
    /*
     * Given a list, rotate the list to the right by k places, where k is non-negative.
		For example:
		Given 1->2->3->4->5->NULL and k = 2,
		return 4->5->1->2->3->NULL
     */
    public ListNode rotateRight(ListNode head, int k) {
    	if (head == null || k == 0 || head.next == null)
    		return head;
    	int size = 1;
    	ListNode curr = head,end=head;
    	ListNode newHead = new ListNode(0);
    	while (end.next != null){
    		end = end.next;
    		size++;
    	}
    	if (k%size == 0)
	        return head;
    	int start = 1;
    	while(start < size - k%size){
    		curr = curr.next;
    		start++;
    	}
    	newHead.next = curr.next;
    	curr.next = null;
    	end.next = head;
    	return newHead.next;
    }

}
