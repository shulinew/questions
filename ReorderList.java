


public class ReorderList {
	/*
	 *  Given a singly linked list L: L0->L1->…->Ln-1->Ln,
		reorder it to: L0->Ln->L1->Ln-1->L2->Ln-2->…
		You must do this in-place without altering the nodes' values.
		For example,
		Given {1,2,3,4}, reorder it to {1,4,2,3}. 
	 */
    public void reorderList(ListNode head) {
    	if (head == null)
    		return;
    	//Separate the list into half
    	ListNode fast = head;
    	ListNode slow = head;
    	while (fast.next != null && fast.next.next != null){
    		slow = slow.next;
    		fast = fast.next.next;
    	}
    	
    	//Reverse the second half
    	ListNode secHead = slow.next,temp;
    	if (secHead != null && slow != fast){
	    	ListNode curr = secHead.next;
	    	secHead.next = null;
	    	while (curr != null){
	    		temp = curr.next;
	    		curr.next = secHead; 
	    		secHead = curr;
	    		curr = temp;
	    	}
	    	curr = head;
	    	ListNode secondCurr = secHead,temp1;
	    	while (curr != slow.next && secondCurr != null){
	    		temp = curr.next;
	    		curr.next = secondCurr;
	    		temp1 = secondCurr.next;
	    		secondCurr.next = temp;
	    		secondCurr = temp1;
	    		curr = temp;
	    	}
	    	curr.next = null;
    	}
    	
    	
    	
//        ListNode preMiddle=p1;
//        ListNode preCurrent=p1.next;
//        while(preCurrent.next!=null){
//            ListNode current=preCurrent.next;
//            preCurrent.next=current.next;
//            current.next=preMiddle.next;
//            preMiddle.next=current;
//        }
//
//        //Start reorder one by one  1->2->3->6->5->4 to 1->6->2->5->3->4
//        p1=head;
//        p2=preMiddle.next;
//        while(p1!=preMiddle){
//            preMiddle.next=p2.next;
//            p2.next=p1.next;
//            p1.next=p2;
//            p1=p2.next;
//            p2=preMiddle.next;
//        }
    }
    public static void main(String[] argus){
    	ListNode head = new ListNode(1);
    	head.next = new ListNode(2);
    	head.next.next = new ListNode(3);
    	head.next.next.next = new ListNode(3);
    	head.next.next.next.next = new ListNode(4);
    	ReorderList reorder = new ReorderList();
    	reorder.reorderList(head);
    }
}
