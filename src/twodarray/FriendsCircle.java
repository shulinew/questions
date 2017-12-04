package twodarray;

/*
 *  There are N students in a class. Some of them are friends, while some are not. Their friendship is transitive in nature. For example, if A is a direct friend of B, and B is a 
 *  direct friend of C, then A is an indirect friend of C. And we defined a friend circle is a group of students who are direct or indirect friends.

Given a N*N matrix M representing the friend relationship between students in the class. If M[i][j] = 1, then the ith and jth students are direct friends with each other, otherwise
 not. And you have to output the total number of friend circles among all the students. 
Input: 
[[1,1,0],
 [1,1,0],
 [0,0,1]]
Output: 2
Explanation:The 0th and 1st students are direct friends, so they are in a friend circle. 
The 2nd student himself is in a friend circle. So return 2.
Input: 
[[1,1,0],
 [1,1,1],
 [0,1,1]]
Output: 1
Explanation:The 0th and 1st students are direct friends, the 1st and 2nd students are direct friends, 
so the 0th and 2nd students are indirect friends. All of them are in the same friend circle, so return 1.
 */
public class FriendsCircle {
//    public int findCircleNum(int[][] M) {
//        int n = M.length;
//        int m = M[0].length;
//        int middle = m%2 == 0? 0: m/2+1;
//        int total = (m + 1) * m/2 + middle;
//        int[] edges = new int[total];
//        int k = 0;
//        for (int i = 0; i < n; i++) {
//        	for (int j = i; j < m; j++) {
//        		edges[k++] = M[i][j];
//        	}
//        }
//        int[] parents = new int[total];
//        for (int i = 0;i < total; i++) {
//        	parents[i] = i;
//        }
	class UnionFind {
		private int count = 0;
		private int[] parents;
		private int[] rank;
		
		public UnionFind(int n) {
			count = n;
			parents = new int[n];
			rank = new int[n];
			for (int i = 0; i < n; i++) {
				parents[i] = i;
			}
		}
		public int find (int p) {
			while(p != parents[p]) {
				parents[p] = parents[parents[p]];
				p = parents[p];
			}
			return p;
		}
		public void union(int p, int q) {
			int rootP = find(p);
			int rootQ = find(q);
			if (rootP == rootQ) 
				return;
			if (rank[rootQ] > rank[rootP]) {
				parents[rootP] = rootQ;
			} else {
				parents[rootQ] = rootP;
				if(rank[rootQ] == rank[rootP]) {
					rank[rootP]++;
				}
			}
			count--;
		}
		public int count(){
			return count;
		}
	}
	public int findCircleNum(int[][] M) {
		int n = M.length;
		UnionFind unionFind = new UnionFind(n);
		for (int i = 0; i < n; i++) {
			for(int j = i+1; j < n; j++){
				if (M[i][j] == 1)
					unionFind.union(i, j);
			}
		}
		return unionFind.count();
	}
    public void dfs(int[][] M, int[] visited, int i) {
        for (int j = 0; j < M.length; j++) {
            if (M[i][j] == 1 && visited[j] == 0) {
                visited[j] = 1;
                dfs(M, visited, j);
            }
        }
    }
    public int findCircleNumDfs(int[][] M) {
        int[] visited = new int[M.length];
        int count = 0;
        for (int i = 0; i < M.length; i++) {
            if (visited[i] == 0) {
                dfs(M, visited, i);
                count++;
            }
        }
        return count;
    }
	public static void main(String[] args) {
		int[][] M = {{1,1,0}, {1,1,0},{0,0,1}};
		FriendsCircle fc = new FriendsCircle();
		fc.findCircleNum(M);
	}
}
