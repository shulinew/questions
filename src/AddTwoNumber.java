
public class AddTwoNumber {
	/*
	 * You are given two linked lists representing two non-negative numbers. 
	 * The digits are stored in reverse order and each of their nodes contain 
	 * a single digit. Add the two numbers and return it as a linked list.
	 */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    	ListNode newHead = new ListNode(0),curr = newHead;
    	int carryOn = 0, sum;
    	while(l1 != null && l2 != null){
    	    sum = l1.val + l2.val + carryOn;
    		if (sum >= 10 ){
    			carryOn = 1;
    			sum = sum - 10;
    		}else{
    			carryOn = 0;
    		}
    		curr.next = new ListNode(sum);
    		curr = curr.next;
    		l1 = l1.next;
    		l2 = l2.next;
    	}
    	while (l2 != null){
    		sum = l2.val + carryOn;
    		if (sum >= 10 ){
    			carryOn = 1;
    			sum = sum - 10;
    		}else{
    			carryOn = 0;
    		}
    		curr.next = new ListNode(sum);
    		curr = curr.next;
    		l2 = l2.next;
    	}
    	while (l1 != null){
    		sum = l1.val + carryOn;
    		if (sum >= 10 ){
    			carryOn = 1;
    			sum = sum - 10;
    		}else{
    			carryOn = 0;
    		}
    		curr.next = new ListNode(sum);
    		curr = curr.next;
    		l1 = l1.next;
    	}
    	if (carryOn != 0){
    		curr.next = new ListNode(carryOn);
    	}
    	return newHead.next;
    }
    
    public static void main(String[] args){
    	AddTwoNumber test = new AddTwoNumber();
    	ListNode l1 = new ListNode(9);
    	ListNode t2 = new ListNode(1);
    	ListNode t3 = new ListNode(6);
    	l1.next = t2;
    	t2.next = t3;
    	ListNode l2 = new ListNode(0);
    	test.addTwoNumbers(l1,  l2);
    	
    }

}
