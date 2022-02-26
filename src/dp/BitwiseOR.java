/*
*/

public class BitwisOR {
    public int subarrayBitwiseORs(int[] A) {
        Set<Integer> answer = new HashSet<Integer>();
        Set<Integer> current = new HashSet<Integer>();

        current.add(0);
        for (int x: A) {
            Set<Integer> current2 = new HashSet<Integer>();
            for (int y : current) {
                current2.add(x | y);
            }
            current2.add(x);
            current = current2;
            answer.addAll(current);
        }
        return answer.size();
    }
    public int subarrayBitwiseORs1(int[] A) {
        Set<Integer> answer = new HashSet<Integer>();
        // Set<Integer> current = new HashSet<Integer>(Arrays.asList(A));
        answer.add(A[i]);
        for (int i = 1; i < A.length; i++){
            for(int y: current){

            }
        }
    }
}