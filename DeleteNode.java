
public class DeleteNode {
    public static void deleteNode(ListNode node) {
        if (node.next != null){
        	node.next = node.next.next;
        	node.val = node.next.val;
        }
        /*
         *         
        ListNode temp = node;
        while (node != null && node.next != null){
            node.val = node.next.val;
            temp = node;
            node = node.next;
        }
        temp.next = null;
         */
    }
}
