
public class PartitionList {
	/*
	 * Given a linked list and a value x, partition it such that all nodes less than x come 
	 * before nodes greater than or equal to x.
		You should preserve the original relative order of the nodes in each of the two partitions.
		For example,
		Given 1->4->3->2->5->2 and x = 3,
		return 1->2->2->4->3->5. 
	 */
	
    public ListNode partition(ListNode head, int x) {
    	
    	ListNode head1 = new ListNode(0), curr1 = head1;
    	ListNode head2 = new ListNode(0), curr2 = head2;
    	while (head != null){
	    	if (head.val < x ){
	    		curr1.next = head;
	    		curr1 = curr1.next;
	    	}else{
	    		curr2.next = head;
	    		curr2 = curr2.next;
	    	}
	    	head = head.next;
    	}
    	curr1.next = head2.next;
    	curr2.next = null;
    	return head1.next;
    	
        
    }

}
