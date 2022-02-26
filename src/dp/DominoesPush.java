/*
After each second, each domino that is falling to the left pushes the adjacent domino on the left.

Similarly, the dominoes falling to the right push their adjacent dominoes standing on the right.

When a vertical domino has dominoes falling on it from both sides, it stays still due to the balance of the forces.

For the purposes of this question, we will consider that a falling domino expends no additional 
force to a falling or already fallen domino.

Given a string "S" representing the initial state. S[i] = 'L', if the i-th domino has been pushed 
to the left; S[i] = 'R', if the i-th domino has been pushed to the right; S[i] = '.', if the 
i-th domino has not been pushed.
Find final states of dominos
Input: ".L.R...LR..L.."
Output: "LL.RR.LLRRLL.."

Input: "RR.L"
Output: "RR.L"
*/

public class DominoesPush {
    public String pushDominoes (String dominos) {
        int length = dominos.length();
        dominos = 'L' + dominos + 'R';
        StringBuilder result = new StringBuilder();
        for (int i = 0, j = 1; j < length+2; j++) {
           if (dominos.charAt(j) == '.') continue;
           int distance = j - i - 1;
           if (i != 0) {
               result.append(dominos.charAt(i));
           }
           if (dominos.charAt(i) == dominos.charAt(j)) {
               for (int k = 0; k < distance; k++) {
                   result.append(dominos.charAt(i));
               }
           } else if (dominos.charAt(i) == 'L' && dominos.charAt(j) == 'R') {
                for (int k = 0; k < distance; k++) {
                   result.append('.');
               }
           } else {
               for (int k = 0; k < distance/2; k++) result.append('R');
               if (distance %2 == 1) result.append('.');
               for (int k = 0; k < distance/2; k++) result.append('L');
           }
           i = j;
        }
        return result.toString();
    }
    /*
    Intuition

We can calculate the net force applied on every domino. The forces we care about are how close a 
domino is to a leftward 'R', and to a rightward 'L': the closer we are, the stronger the force.

Algorithm

Scanning from left to right, our force decays by 1 every iteration, and resets to N if we meet an
 'R', so that force[i] is higher (than force[j]) if and only if dominoes[i] is closer (looking 
 leftward) to 'R' (than dominoes[j]).

Similarly, scanning from right to left, we can find the force going rightward (closeness to 'L').

For some domino answer[i], if the forces are equal, then the answer is '.'. Otherwise, the answer 
is implied by whichever force is stronger.

Example

Here is a worked example on the string S = 'R.R...L': We find the force going from left to right 
7, 6, 7, 6, 5, 4, 0]. The force going from right to left is [0, 0, 0, -4, -5, -6, -7]. Combining them 
(taking their vector addition), the combined force is [7, 6, 7, 2, 0, -2, -7], for a final answer 
of RRRR.LL.
    */

        public String pushDominoes(String S) {
        char[] A = S.toCharArray();
        int N = A.length;
        int[] forces = new int[N];

        // Populate forces going from left to right
        int force = 0;
        for (int i = 0; i < N; ++i) {
            if (A[i] == 'R') force = N;
            else if (A[i] == 'L') force = 0;
            else force = Math.max(force - 1, 0);
            forces[i] += force;
        }

        // Populate forces going from right to left
        force = 0;
        for (int i = N-1; i >= 0; --i) {
            if (A[i] == 'L') force = N;
            else if (A[i] == 'R') force = 0;
            else force = Math.max(force - 1, 0);
            forces[i] -= force;
        }

        StringBuilder ans = new StringBuilder();
        for (int f: forces)
            ans.append(f > 0 ? 'R' : f < 0 ? 'L' : '.');
        return ans.toString();
    }

        public String pushDominoes(String dominoes) {
        char[] cs = dominoes.toCharArray();
        char left = '.';
        int lp = -1;    // left-point
        for (int i = 0; i < cs.length; i++) {
            if (cs[i] == '.')   continue;
            char right = '.';
            right = cs[i];
            if (left == '.' && right == 'L') {
                for (int j = lp + 1; j < i; j++) {
                    cs[j] = 'L';
                }
            } else if (left == 'R' && right == 'R') {
                for (int j = lp + 1; j < i; j++) {
                    cs[j] = 'R';
                }
            } else if (left == 'L' && right == 'L') {
                for (int j = lp + 1; j < i; j++) {
                    cs[j] = 'L';
                }
            } else if (left == 'R' && right == 'L') {
                for (int j = lp + 1, k = i - 1; j < k; j++,k--) {
                    cs[j] = 'R';
                    cs[k] = 'L';
                }
            }
            lp = i;
            left = right;
        }
        if (lp < cs.length - 1 && left == 'R') {
            for (int j = lp + 1; j < cs.length; j++) {
                cs[j] = 'R';
            }
        }
        return new String(cs);
    }
}