package list;


public class OddEvenLinkedList {
    public ListNode oddEvenList(ListNode head) {
    	if (head == null) 
    		return null;
    	int i = 1;
    	ListNode current = head;
    	ListNode oddHead = head;
    	ListNode evenHead = null;
    	ListNode oddPoint = oddHead;
    	ListNode evenPoint = evenHead;
    	while (current.next != null){
    		i++;
    		current = current.next;
    		if (i%2 == 0){
    			if (evenHead == null){
    				evenHead = current;
    				evenPoint = evenHead;
    			} else{
    				evenPoint.next = current;
    				evenPoint = current;
    			}
    		} else {
    			oddPoint.next = current;
    			oddPoint = current;
    		}
    	}

    	oddPoint.next = evenHead;
    	if (evenPoint != null){
    	    evenPoint.next = null;
    	}
    	return oddHead;
    	
//        ListNode oddDummy = new ListNode(0), evenDummy = new ListNode(0);
//        ListNode oddRear = oddDummy, evenRear = evenDummy, p = head;
//        for(int count = 1; p!=null; p=p.next, count++)
//            if(count%2 == 1){
//                oddRear.next = p;
//                oddRear = p;
//            }
//            else{
//                evenRear.next = p;
//                evenRear = p;
//            }
//        evenRear.next = oddRear.next = null;
//        oddRear.next = evenDummy.next;
//        return oddDummy.next;
    }
    public ListNode oddEvenList1(ListNode head) {
        if(head == null || head.next == null || head.next.next == null) return head;
        ListNode oddTail = head; //tracks the tail of all odd nodes
        ListNode seekOdd = head; //seeks next odd node
        while(seekOdd.next!=null && seekOdd.next.next!=null){
            ListNode bigEven = seekOdd.next; //biggest even node between oddTail and seekOdd
            seekOdd = seekOdd.next.next; 
            ListNode smallEven = oddTail.next; //smallest even node between oddTail and seekOdd
            oddTail.next = seekOdd;
            bigEven.next = seekOdd.next;
            seekOdd.next = smallEven;
            oddTail = seekOdd;
            seekOdd = bigEven;
        }
        return head;
    }
    public ListNode oddEvenList2(ListNode head) {
        if(head==null||head.next==null) return head;
        ListNode odd=head,ehead=head.next,even=ehead;
        while(even!=null&&even.next!=null){
            odd.next=even.next;
            odd=odd.next;
            even.next=odd.next;
            even=even.next;
        }
        odd.next=ehead;
        return head;
    }
	public static void main(String[] args){
		OddEvenLinkedList solutions = new OddEvenLinkedList();
    	ListNode head = new ListNode(1);
    	head.next = new ListNode(2);
    	head.next.next = new ListNode(3);
    	head.next.next.next = new ListNode(4);
    	head.next.next.next.next = new ListNode(5);
    	head.next.next.next.next.next = new ListNode(6);
    	head.next.next.next.next.next.next = new ListNode(7);
    	head.next.next.next.next.next.next.next = new ListNode(8);
		solutions.oddEvenList(head);
	}
}
