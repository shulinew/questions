
public class PalindromList {
    public boolean isPalindrome(ListNode head) {
    	if (head == null || head.next == null)
    		return true;
    	ListNode fast = head;
    	ListNode slow = head;
    	ListNode reverseHead = head;
    	ListNode start = new ListNode(0),temp = null,curr = head.next;
    	start.next = head;
    	while (fast.next != null && fast.next.next != null){
    		fast = fast.next.next;
    		
    		temp = curr.next;
    		curr.next = start.next;
    		head = curr;
    		
    		head = temp;
    		
			temp = head.next;
			head.next = curr;
			curr = head;
			head = temp;
//    		start.next = reverseHead.next;
//    		reverseHead.next = reverseHead.next.next;
//    		start.next.next = slow;
//    		slow = start.next;
	
    	}
    	if (fast != null && fast.next == null){
    		slow = slow.next;
    	}
    	reverseHead = reverseHead.next;
    	while (reverseHead != null){
    		if (slow.val != reverseHead.val)
    			return false;
    		slow = slow.next;
    		reverseHead = reverseHead.next;
    	}
    	return true;
        
    }
    public static void main(String [] args){
    	ListNode head = new ListNode(1);
    	head.next = new ListNode(2);
    	head.next.next = new ListNode(3);
    	head.next.next.next = new ListNode(4);
    	PalindromList test = new PalindromList();
    	test.isPalindrome(head);
    }
}
