package sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;


/*
 * Suppose you have a random list of people standing in a queue. Each person is described by a pair of 
 * integers (h, k), where h is the height of the person and k is the number of people in front of this person 
 * who have a height greater than or equal to h. Write an algorithm to reconstruct the queue. 
 */
public class ReconstructQueue {
//    public int[][] reconstructQueue(int[][] people) {
//    	//int people[][]={{7,0}, {4,4}, {7,1}, {5,0}, {6,1}, {5,2}};
//        Arrays.sort(people,new Comparator<int[]>(){
//           
//           public int compare(int[] o1, int[] o2){
//               return o1[0]!=o2[0]?-o1[0]+o2[0]:o1[1]-o2[1];
//           }
//        });
//        List<int[]> res = new LinkedList<int[]>();
//        for(int[] cur : people){
//            res.add(cur[1],cur);       
//        }
//        return res.toArray(new int[people.length][]);
//    }
	public int[][] reconstructQueue2(int [][] people){
		Arrays.sort(people, new Comparator<int[]>(){
			public int compare(int[] o1, int[] o2){
				if (o1[0] > o2[0]) return 1;
				else if (o1[0] < o2[0]) return -1;
				else return o2[1] - o1[1];
			}
		});
		List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < people.length; i++) {
            list.add(i);
        }
        int[][] res = new int[people.length][2];
        for (int i = 0; i < people.length; i++) {
            int index = list.get(people[i][1]);
            res[index][0] = people[i][0];
            res[index][1] = people[i][1];
            list.remove(people[i][1]);
        }
        return res;
		
	}
    
    public int[][] reconstructQueue1(int[][] people) {
        if(people.length == 0) return new int[][]{};

        //sort
        Arrays.sort(people,new mC());

        //put in a list
        List<int[]> list = new LinkedList<int[]>();
        list.add(people[0]);
        for (int i =1;i<people.length;i++) {
            list.add(people[i][1],people[i]);
        }
        
        //return result
        Iterator iterator = list.iterator();
        int i = 0;
        while (iterator.hasNext()) {
            people[i++] = (int[]) iterator.next();
        }
        return people;
    }
    class  mC implements Comparator<int[]> {
        public int compare(int[] o1, int[] o2) {
            if (o1[0] > o2[0]) return -1;
            else if (o1[0] < o2[0]) return 1;
            else {
                return o1[1] - o2[1];
            }
        }
    }
    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, new Comparator<int[]>(){
            public int compare(int [] a, int [] b) {
                int col1 = a[1] - b[1];
                if (col1 != 0) {
                    return col1;
                }
                return a[0] - b[0];
            }
        });
        for (int i = 1; i < people.length; i++) {
            
            int key = people[i][0];
            int count = people[i][1];
            
            int prevTallerOrEqual = 0;
            for (int j = 0; j < i; j++) {
                if (people[j][0] >= key) {
                    prevTallerOrEqual++;
                }
            }
            
            int j = i;
            while (j > 0 && prevTallerOrEqual > count) {
                people[j][0] = people[j-1][0];
                people[j][1] = people[j-1][1];
                j--;
                if (people[j][0] >= key) {
                    prevTallerOrEqual--;
                }
            }
            people[j][0] = key;
            people[j][1] = count;
        }
        return people;
    }
    
    public static void main(String[] args){
    	ReconstructQueue rq = new ReconstructQueue();
    	int people[][]={{7,0}, {4,4}, {7,1}, {5,0}, {6,1}, {5,2}};
    	rq.reconstructQueue2(people);
    	System.out.println("Length===============");
    	System.out.println(people.length);
    	for (int  i = 0; i<people.length;i++){
    		for (int j = 0;j<people[0].length;j++){
    			System.out.print(people[i][j]);
    		}
    		System.out.print("\n\t");
    	}
    }
    /*
     * People are only counting (in their k-value) taller or equal-height others standing in front of them. So a smallest person is completely irrelevant for all taller ones. And of all smallest people, the one standing most in the back is even completely irrelevant for everybody else. Nobody is counting that person. So we can first arrange everybody else, ignoring that one person. And then just insert that person appropriately. Now note that while this person is irrelevant for everybody else, everybody else is relevant for this person - this person counts exactly everybody in front of them. So their count-value tells you exactly the index they must be standing.

So you can first solve the sub-problem with all but that one person and then just insert that person appropriately. And you can solve that sub-problem the same way, first solving the sub-sub-problem with all but the last-smallest person of the subproblem. And so on. The base case is when you have the sub-...-sub-problem of zero people. You're then inserting the people in the reverse order, i.e., that overall last-smallest person in the very end and thus the first-tallest person in the very beginning. That's what the above solution does, Sorting the people from the first-tallest to the last-smallest, and inserting them one by one as appropriate.
     def reconstructQueue(self, people):
    people.sort(key=lambda (h, k): (-h, k))
    queue = []
    for p in people:
        queue.insert(p[1], p)
    return queue
     */

}
