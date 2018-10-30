
public class RemoveNthFromEnd {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        
        ListNode fakeHead = new ListNode(0);
        ListNode slow = fakeHead;
        ListNode fast = fakeHead;
        slow.next = head;
        
        for (int i = 1; i<= n+1; i++){
        	fast = fast.next;
        }
        while (fast != null){
        	slow = slow.next;
        	fast = fast.next;
        }
        slow.next = slow.next.next;
        return fakeHead.next;
    }

}
