

public class Solutions {
	
	private int search(int[] array, int target){
		return binarySearch(array, target, 0, array.length-1);
	}
	
	private int binarySearch(int[] array, int target, int lower, int upper){
//		if (array.length == 0 && array[lower] != target){
//			return 0;
//		}else if (lower > upper){
//			return 0;
//		}else if (array[lower] > array[upper]){
//			return 0;
//		}
		int center = (upper - lower)/2+lower;
		if (array[center] == target){
			return array[center];
		}
		else if (array[center] > target){
			return binarySearch(array, target, lower, center-1);
		}
		else{
			return binarySearch(array, target, center+1, upper);
		}
	}
	
	/*
	 * Given an array of citations (each citation is a non-negative integer) 
	 * of a researcher, write a function to compute the researcher's h-index.
	 * According to the definition of h-index on Wikipedia: "A scientist has 
	 * index h if h of his/her N papers have at least h citations each, and the 
	 * other N  h papers have no more than h citations each."For example, given 
	 * citations = [3, 0, 6, 1, 5], which means the researcher has 5 papers in 
	 * total and each of them had received 3, 0, 6, 1, 5 citations respectively. 
	 * Since the researcher has 3 papers with at least 3 citations each and the 
	 * remaining two with no more than 3 citations each, his h-index is 3.
	 */
	
	private int hIndex(int[] citations) {
		int[] occurrences = new int[citations.length];
		for (int c: citations){
			if (c > citations.length){
				occurrences[citations.length] +=1;
			}else{
				occurrences[c] += 1;
			}
		}
		int total = 0;
		for (int i = occurrences.length-1;i>=0;i--){
			total += 1;
			if (total >= i){
				return i;
			}
		}
		return 0;
        
    }
	/*

	 */

	private class ListNode{
		private int val;
		private ListNode next;
		ListNode(int x){
			val = x;
			next = null;
		}
	}
    public ListNode insertionSortList(ListNode head) {
    	ListNode current = head;
    	ListNode newList = new ListNode(0);
    	ListNode pre = newList;
    	ListNode next = null;
    	while (current != null){
    		next = current.next;
    		while(pre.next != null && pre.next.val < current.val){
    			pre = pre.next;
    		}
            
    		current.next = pre.next;
    		pre.next = current;
    		current = next;
    		pre = newList;

    	}
		return head;
        
    }
    public boolean isAnagram(String s, String t){
    	int [] alphabets = new int[26];
    	for (int i=0;i<s.length();i++){
    		alphabets[s.charAt(i)-'a']++;
    	}
    	for (int i=0;i<t.length();i++){
    		alphabets[t.charAt(i)-'a']--;
    		if (alphabets[t.charAt(i)-'a'] < 0){
    			return false;
    		}
    	}
    	for (int i =0;i<alphabets.length;i++){
    		if (alphabets[i] > 0)
    			return false;
    	}
    	return true;
    }
	
    /*
     *  Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent, with the colors in the order red, white and blue.
		Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.
		Note:
		You are not suppose to use the library's sort function for this problem.
		click to show follow up.
		Follow up:
		A rather straight forward solution is a two-pass algorithm using counting sort.
		First, iterate the array counting number of 0's, 1's, and 2's, then overwrite array with total number of 0's, then 1's and followed by 2's.
		Could you come up with an one-pass algorithm using only constant space?
     */
    
    
    /*
     * Recursive way
     */
    
    public ListNode sortList1(ListNode head){
        if (head == null || head.next == null)
            return head;

          // step 1. cut the list to two halves
          ListNode prev = null, slow = head, fast = head;

          while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
          }

          prev.next = null;

          // step 2. sort each half
          ListNode l1 = sortList1(head);
          ListNode l2 = sortList1(slow);

          // step 3. merge l1 and l2
          return merge(l1, l2);
    }
    //merge two sorted list
    
    private ListNode merge(ListNode l1, ListNode l2){
    	ListNode head = new ListNode(0);
    	ListNode l = head;
    	while (l1 != null && l2 != null){
    		if (l1.val < l2.val){
    			l.next = l1;
    			l1 = l1.next;
    		}else{
    			l.next = l2;
    			l2 = l2.next;
    		}
    		l = l.next;
    	}
    	if (l1 != null){
    		l.next = l1;
    	}
    	if (l2 != null){
    		l.next = l2;
    	}
    	return head.next;
    }
    
    private class MergeHelper {
        public ListNode newHead;
        public ListNode newTail;
	}
    
	public ListNode sortList(ListNode head) {
	    if ( head == null || head.next == null) {
	        return head;
	    }
	
	    ListNode dummyHeadOne = new ListNode(0);
	    ListNode dummyHeadTwo = new ListNode(0);
	    ListNode dummySortedHead = new ListNode(0);
	    ListNode dummySortedLast = dummySortedHead;
	    ListNode unvisitedNode = head;
	    MergeHelper mergeRst = new MergeHelper();
	
	    int listLength = 0;
	    int level = 0;
	    while ( unvisitedNode != null && unvisitedNode.next != null ) {
	        unvisitedNode = addNode ( dummyHeadOne, unvisitedNode, 1<<level);
	        unvisitedNode = addNode ( dummyHeadTwo, unvisitedNode, 1<<level);
	        merge ( dummyHeadOne.next, dummyHeadTwo.next, mergeRst);
	        dummySortedLast.next = mergeRst.newHead;
	        dummySortedLast = mergeRst.newTail;
	        listLength += 2;
	    }
	    if (unvisitedNode != null) {
	        dummySortedLast.next = unvisitedNode;
	        listLength ++;
	    }
	    level ++;
	
	    while ( listLength > 1 << level) {
	        dummySortedLast = dummySortedHead;
	        unvisitedNode = dummySortedHead.next;
	        while (unvisitedNode != null) {
	            unvisitedNode = addNode ( dummyHeadOne, unvisitedNode, 1<<level);
	            unvisitedNode = addNode ( dummyHeadTwo, unvisitedNode, 1<<level);
	            merge ( dummyHeadOne.next, dummyHeadTwo.next, mergeRst);
	            dummySortedLast.next = mergeRst.newHead;
	            dummySortedLast = mergeRst.newTail;
	        }
	        level ++;
	    }
	
	    return dummySortedHead.next;
	}
	
	/* merge listOne and listTwo. 
	Save the sorted list head into rst.newHead
	Save the last node of the sorted list into rst.newTail
	*/
	private void merge (ListNode listOne, ListNode listTwo, MergeHelper rst) {
	    ListNode dummyHead = new ListNode (0);
	    ListNode lastNode = dummyHead;
	    while (listOne != null && listTwo != null) {
	        if ( listOne.val < listTwo.val ) {
	            lastNode.next = listOne;
	            listOne = listOne.next;
	        } else {
	            lastNode.next = listTwo;
	            listTwo = listTwo.next;
	        }
	        lastNode = lastNode.next;
	    }
	
	    while (listOne != null) {
	        lastNode.next = listOne;
	        listOne = listOne.next;
	        lastNode = lastNode.next;
	    }
	    while ( listTwo != null ) {
	        lastNode.next = listTwo;
	        listTwo = listTwo.next;
	        lastNode = lastNode.next;
	    }
	    rst.newHead = dummyHead.next;
	    rst.newTail = lastNode;
	}

	/*
	 add at max #"count" nodes into "head" from "source"
	 return the new position of source after adding.
	*/
	private ListNode addNode ( ListNode head, ListNode source, int count ) {
	    while (count > 0 && source != null) {
	        head.next = source;
	        head = head.next; 
	        source = source.next;
	        count --;
	    }
	    head.next = null;
	    return source;
	}
	
	/*
	 * iven a string that contains only digits 0-9 and a target value, return all
		possibilities to add binary operators (not unary) +, -, or * between the
		digits so they evaluate to the target value.

		"123", 6 -> ["1+2+3", "1*2*3"]
		"232", 8 -> ["2*3+2", "2+3*2"]
		"105", 5 -> ["1*0+5","10-5"]
		"00", 0 -> ["0+0", "0-0", "0*0"]
		"3456237490", 9191 -> []

	 */
	public static void main(String[] args){
		Solutions solutions = new Solutions();
		int[] array = {19,10,32,89};
		solutions.search(array, 5);
	}

}
