package array;



import java.util.HashMap;

import java.util.Map;



/*
 *  Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
	For example,
	Given [100, 4, 200, 1, 3, 2],
	The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.
	Your algorithm should run in O(n) complexity. 
 */

public class LongestConsecutiveSequence {
	
	class UnionFind {
		
		private int [] parents;
		private int maxSize;
		private int [] size;
		
		public UnionFind(int n) {
			parents = new int[n];
			size = new int[n];
			maxSize = 1;
		    for (int i = 0; i < n; i++) {
			    parents[i] = i;
			    size[i] = 1;
		    }
		}
		public int root(int i) {
			while (i != parents[i]) {
				parents[i] = parents[parents[i]];
				i = parents[i];
			}
			return i;
		}
	
		public void union(int p, int q) {
			int rootP = root(p);
			int rootQ = root(q);
			if (rootP == rootQ) return;
			
			if (size[rootP] > size[rootQ]) {
				parents[rootQ] = rootP;
				size[rootP]+= size[rootQ];
				maxSize = Math.max(maxSize, size[rootP]);
			} else {
				parents[rootP] = rootQ;
				size[rootQ] += size[rootP];
				maxSize = Math.max(maxSize, size[rootQ]);
			}
		}
		
		public int maxSize() {
			return maxSize;
		}

        // returns the maxium size of union
        public int maxUnion(){ // O(n)
            int[] count = new int[parents.length];
            int max = 0;
            for(int i=0; i<parents.length; i++){
                count[root(i)] ++;
                max = Math.max(max, count[root(i)]);
            }
            return max;
        }
	}
	
    public int longestConsecutive(int[] nums) {
	    int n = nums.length;
	    UnionFind unionFind = new UnionFind(n);
	    Map<Integer, Integer> map = new HashMap<Integer, Integer>();
	    for (int i = 0; i<n; i++) {
	    	map.put(nums[i], i);
	    }
	    for (int num: nums) {
		    if (map.containsKey(num + 1)) {
		    	unionFind.union(map.get(num), map.get(num+1));
		    }
	    }
	    return unionFind.maxSize();
    }
        
    public int longestConsecutive3(int[] nums) {
        final int length = nums.length;
        final Map<Integer, Integer> elementIndexMap = new HashMap<Integer,Integer>();
        final UnionFind uf = new UnionFind(length);
        
        if (length <= 1) return length;
        for (int p = 0; p < length; p++) {
            final int i = nums[p];
            if (elementIndexMap.containsKey(i)) continue;
            if (elementIndexMap.containsKey(i+1)) uf.union(p, elementIndexMap.get(i+1));
            if (elementIndexMap.containsKey(i-1)) uf.union(p, elementIndexMap.get(i-1));
            elementIndexMap.put(i, p);
        }
        return uf.getHighestRank();
    }

        

        private final class UnionFind1 {

            final private int[] sequenceTree;

            final private int[] rank;

            private int highestRank;

            

            UnionFind(int length) {

                sequenceTree = new int[length];

                rank = new int[length];

                highestRank = 1;

                for (int i = 0; i < length; i++) {

                    sequenceTree[i] = i;

                    rank[i] = 1;

                }

            }

            

            void union(int p, int q) {

                final int pId = find(p); final int qId = find(q);

                

                if (pId == qId) return;

                

                int localHighestRank = 1;

                if (rank[pId] < rank[qId]) {

                    sequenceTree[pId] = qId;

                    rank[qId] += rank[pId];

                    localHighestRank = rank[qId];

                } else {

                    sequenceTree[qId] = pId;

                    rank[pId] += rank[qId];

                    localHighestRank = rank[pId];

                }

                highestRank = Math.max(highestRank, localHighestRank);

            }

            

            int find(int p) {

                while (p != sequenceTree[p]) {

                    sequenceTree[p] = sequenceTree[sequenceTree[p]];

                    p = sequenceTree[p];

                }

                return p;

            }

            

            int getHighestRank() { return highestRank; }

        }

}



    public int longestConsecutive1(int[] num) {

      int max = 0;

    	 

      Set<Integer> set = new HashSet<Integer>();

      for (int i = 0; i < nums.length; i++) {

        set.add(nums[i]);

      }

    	 

      for (int i = 0; i < nums.length; i++) {

        int count = 1;

    	   

        // look left

        int num = nums[i];

        while (set.contains(--num)) {

          count++;

          set.remove(num);

        }

    	   

        // look right

        num = nums[i];

        while (set.contains(++num)) {

          count++;

          set.remove(num);

        }

    	   

        max = Math.max(max, count);

      }

    	 

      return max;

    }}

    

    public int longestConsecutive2(int[] num) {

        int res = 0;

        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

        for (int n : num) {

            if (!map.containsKey(n)) {

                int left = (map.containsKey(n - 1)) ? map.get(n - 1) : 0;

                int right = (map.containsKey(n + 1)) ? map.get(n + 1) : 0;

                // sum: length of the sequence n is in

                int sum = left + right + 1;

                map.put(n, sum);

                

                // keep track of the max length 

                res = Math.max(res, sum);

                

                // extend the length to the boundary(s)

                // of the sequence

                // will do nothing if n has no neighbors

                map.put(n - left, sum);

                map.put(n + right, sum);

            }

            else {

                // duplicates

                continue;

            }

        }

        return res;

    }

    

    /*

     * First turn the input into a set of numbers. That takes O(n) and then we can ask in O(1) whether we have a certain number.



Then go through the numbers. If the number x is the start of a streak (i.e., x-1 is not in the set), then test y = x+1, x+2, x+3, ... and stop at the first number y not in the set. The length of the streak is then simply y-x and we update our global best with that. Since we check each streak only once, this is overall O(n). 

     */



}

