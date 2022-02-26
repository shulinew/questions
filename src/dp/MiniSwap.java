/*
Example:
Input: A = [1,3,5,4], B = [1,2,3,7]
Output: 1
Explanation: 
Swap A[3] and B[3].  Then the sequences are:
A = [1, 3, 5, 7] and B = [1, 2, 3, 4]
which are both strictly increasing.
*/

public class MiniSwap {
    public int minSwap(int[] A, int[] B) {
        int length = A.length;
        int[] swap = new int[length];
        int[] noSwap = new int[length];
        swap[0] = 1;
        noSwap[0] = 0;
        for (int i = 1; i < length; i++){
            swap[i] = noSwap[i] = length;
            // In this case, either swap both or keep it same
            if (A[i] > A[i-1] && B[i] > B[i-1]){
                swap[i] = swap[i-1]+1;
                noSwap[i] = noSwap[i-1];
            }
            // either swap i-1th item or swap ith item
            if (A[i] > B[i-1] && B[i] > A[i-1]) {
                swap[i] = Math.min(swap[i], noSwap[i-1]+1); //swap ith item
                noSwap[i] = Math.min(noSwap[i], swap[i-1]); //swap i-1th item
            }
        }
        return Math.min(swap[length-1], noSwap[length-1]);
    }
    public int miniSwap1(int[] A, int[] B) {
        int length = A.length;
        int swap1 = 1;
        int noSwap1 = 0;
        for (int i = 1; i < length; i++){
            int swap2 = length;
            int noSwap2 = length;
            if (A[i] > A[i-1] && B[i] > B[i-1]){
                swap2 = swap1+1;
                noSwap2 = noSwap1;
            }
            // either swap i-1th item or swap ith item
            if (A[i] > B[i-1] && B[i] > A[i-1]) {
                swap2 = Math.min(swap2, noSwap1+1); //swap ith item
                noSwap2 = Math.min(noSwap2, swap1); //swap i-1th item
            }
            swap2 = swap1;
            noSwap2 = noSwap1;
        }
        return Math.min(swap1, noSwap1);
    }
}