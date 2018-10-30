
public class RemoveElements {
    public ListNode removeElements(ListNode head, int val) {
    	if (head == null)
    		return head;
    	ListNode curr = head,pre = null;
    	while (curr != null && curr.next != null){
    		if (curr.val == val){
    			curr.val = curr.next.val;
    			curr.next = curr.next.next;
    		} else {
    			pre = curr;
    			curr = curr.next;
    		}
    	}
        if (curr.val == val){
        	if (pre != null)
        		pre.next = null;
        	else
        		head = null;
        }
        return head;
        
//        ListNode fakeHead = new ListNode(-1);
//        fakeHead.next = head;
//        ListNode curr = head, prev = fakeHead;
//        while (curr != null) {
//            if (curr.val == val) {
//                prev.next = curr.next;
//            } else {
//                prev = prev.next;
//            }
//            curr = curr.next;
//        }
//        return fakeHead.next;
    }

}
