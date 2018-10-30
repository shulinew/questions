
public class MergeSortedList {
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    	ListNode newHead = new ListNode(0), curr = newHead;
    	while (l1 != null && l2 != null){
    		ListNode next = new ListNode(0);
    		if (l1.val <= l2.val){
    			next.val = l1.val;
    			l1 = l1.next;
    		}else{
    			next.val = l2.val;
    			l2 = l2.next;
    		}
    		curr.next = next;
    		curr = curr.next;
    	}
    	if (l1 == null ){
    		curr.next = l2;
    	}
    	if (l2 == null){
    		curr.next = l1;
    	}
    	return newHead.next;
    }

}
