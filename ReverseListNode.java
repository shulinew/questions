
public class ReverseListNode {
	
	public static ListNode reverse(ListNode head){	
		ListNode curr = head, temp = null;
		if (head == null) return head;
		curr = head.next;
		head.next = null;
		while (curr != null){
			temp = curr.next;
			curr.next = head;
			head = curr;
			curr = temp;
		}
		return head;
	}
    public static ListNode reverseBetween(ListNode head, int m, int n) {
    	if (head == null || m == n)
    		return head;
    	int i = 1;
    	ListNode curr = head,pre = curr, temp = null;
    	while (i < m && curr != null){
    		pre = curr;
    		curr = curr.next;
    		i++;
    	}
    	ListNode next = curr.next,mStart = curr;
    	curr.next = null;
    	while (next != null && i < n ){
    		temp = next.next;
    		next.next = curr;
    		curr = next;
    		next = temp;
    		i++;
    	}
    	if (m == 1){
    		head = curr;
    	}else{
    		pre.next = curr;
    	}
    	mStart.next = next;
    	return head;
    	/*
    	 *     ListNode dummyhead = new ListNode(0);
    dummyhead.next = head;
    ListNode sublisthead = new ListNode(0);
    ListNode sublisttail = new ListNode(0);
    int count = 1;
    ListNode pre_cur = dummyhead, cur = head;
    while(count <=n){
        ListNode temp = cur.next;
        if (count < m)
            pre_cur = cur;
        else if (count == m){
            sublisttail = cur;
            sublisthead.next = cur;
        }else if (count > m){
            cur.next = sublisthead.next;
            sublisthead.next = cur;
        }
        cur = temp;
        ++count;
    }
    pre_cur.next = sublisthead.next;
    sublisttail.next = cur;
    return dummyhead.next;
    	 */
    }

}
