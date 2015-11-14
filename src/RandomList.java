
public class RandomList {
	/**
	 * Definition for singly-linked list with a random pointer.
	 * class RandomListNode {
	 *     int label;
	 *     RandomListNode next, random;
	 *     RandomListNode(int x) { this.label = x; }
	 * };
	 * An intuitive solution is to keep a hash table for each node in the list, via which we just need to iterate the list in 2 rounds respectively to create nodes and assign the values for their random pointers. As a result, the space complexity of this solution is O(N), although with a linear time complexity.
		As an optimised solution, we could reduce the space complexity into constant. The idea is to associate the original node with its copy node in a single linked list. In this way, we don't need extra space to keep track of the new nodes.
		The algorithm is composed of the follow three steps which are also 3 iteration rounds.
    Iterate the original list and duplicate each node. The duplicate of each node follows its original immediately.
    Iterate the new list and assign the random pointer for each duplicated node.
    Restore the original list and extract the duplicated nodes.

	 */
    public RandomListNode copyRandomList(RandomListNode head) {
        
    }

}
