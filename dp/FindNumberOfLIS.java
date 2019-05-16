/*
https://leetcode.com/problems/number-of-longest-increasing-subsequence/solution/
*/

public class FindNumberOfLIS {
    public int findNumberOfLIS(int[] nums) {
        int len = nums.length;
        if (len <= 1) return len;
        // length of longest subsequence ending at nums[i]
        int[] lengths = new int[len];
        // count of longest subsequence ending at nums[i]
        int[] counts = new int[len];
        Arrays.fill(counts, 1);
        for (int i = 0; i < len; i++){
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                   if (lengths[j] >= length[i]) {
                       lengths[i] = lengths[j] + 1;
                       counts[i] = counts[j];
                   } else if (lengths[j] + 1 == lengths[i]) {
                       counts[i] += counts[j];
                   }
                }
            }
        }
        int longest = 0;
        for (int i = 0; i < len ;i++){
            longest = Math.max(longest, lengths[i]);
        }
        int count = 0;
        for (int i = 0; i < len; i++){
            if (lengths[i] == longest){
                count += counts[i];
            }
        }
        return count;
    }
    // Use Segament tree
    class Node {
        int rangeLeft, rangeRight;
        Node left, right;
        Value val;
        public Node (int start, int end){
            rangeLeft = start;
            rangeRight = end;
            left = null;
            right = null;
            val = new Value(0,1);
        }
        public int getRangeMid() {
            return rangeLeft + (rangeRight - rangeLeft) / 2;
        }
        public Node getLeft() {
            if (left == null) {
                left = new Node(rangeLeft, getRangeMid());
            }
            return left;
        }
        public Node getRight() {
            if (right == null) {
                right = new Node(geRtangeMid()+1, rangeRight);
            }
            return right;
        }
    }
    class Value {
        int length;
        int count;
        public Value(int length, int count){
            this.length = length;
            this.count = count;
        }
    }
    public Value merge (Value v1, Value v2) {
        if (v1.length == v2.length){
           return v1.length == 0 ? new Value(0, 1) : new Value(v1.length, v1.count+v2.count);
        }
        return v1.length > v2.length ? v1 : v2;
    }
    public void insert(Node node, int key, Value val) {
        if (node.rangeLeft == node.rangeRight){
            node.val = merge (val, node.val);
            return;
        } else if (key <= node.getRangeMid()) {
            insert(node.getLeft(), key, val);
        } else {
            insert(node.getRight(), key, val);
        }
        node.val = merge(node.getLeft().val, node.getRight().val);
    }
    public Value query(Node node, int key){
        if (node.rangeRight <= key) return node.val;
        else if (node.rangeLeft > key) return new Value(0, 1);
        else return merge (node.getLeft().val, query(node.getRight(), key));
    }
    public int findNumberOfLISUsingTree(int[] nums){
        if (nums.length == 0) return 0;
        int min = nums[0], max = nums[0];
        for (int num: nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }
        Node root = new Node(min, max);
        for (int num: nums) {
            Value v = query(root, num-1);
            insert(root, num, new Value(v.length+1, v.count));
        }
        return root.val.count;
    }
}