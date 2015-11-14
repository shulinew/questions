
public class InsertSortList {
    public static ListNode insertionSortList(ListNode head) {
    	ListNode newHead = new ListNode(0);
    	ListNode curr = head, next = null, pre = newHead;
    	while (curr != null){
    		next = curr.next;
    		while(pre.next != null && pre.next.val < curr.val){
    			pre = pre.next;
    		}
    		curr.next = pre.next;
    		pre.next = curr;
    		pre = newHead;
    		curr = next;
    	}
        return newHead.next;
    }
    /*
     * Sort a linked list in O(n log n) time using constant space complexity.
     */
    public static ListNode sortList(ListNode head){
    	if (head == null || head.next == null)
    		return head;
    	ListNode slow = head, fast = head, pre = null;
    	while (fast != null && fast.next != null){
    		pre = slow;
    		slow = slow.next;
    		fast = fast.next.next;
    	}
    	pre.next = null;
    	ListNode l1 = sortList(head);
    	ListNode l2 = sortList(slow);
    	ListNode newHead = new ListNode(0), curr = newHead;
    	while (l1 != null && l2 != null){
    		if (l1.val <= l2.val){
    			curr.next = l1;
    			l1 = l1.next;
    		}else{
    			curr.next = l2;
    			l2 = l2.next;
    		}
    		curr = curr.next;
    	}
    	if (l1 != null){
    		curr.next = l1;
    	}
    	if (l2 != null){
    		curr.next = l2;
    	}
    	return newHead.next;
    }

}
