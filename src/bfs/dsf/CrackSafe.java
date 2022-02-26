/*
 There is a box protected by a password. The password is n digits, where each letter can be one of the first k digits 0, 1, ..., k-1.

You can keep inputting the password, the password will automatically be matched against the last n digits entered.

For example, assuming the password is "345", I can open it when I type "012345", but I enter a total of 6 digits.

Please return any string of minimum length that is guaranteed to open the box after the entire string is inputted. 

Input: n = 1, k = 2
Output: "01"
Note: "10" will be accepted too.

Input: n = 2, k = 2
Output: "00110"
Note: "01100", "10011", "11001" will be accepted too.

Note:

    n will be in the range [1, 4].
    k will be in the range [1, 10].
    k^n will be at most 4096.
https://www.youtube.com/watch?v=iPLQgXUiU14
https://www.youtube.com/watch?v=iPLQgXUiU14
*/

public class crackSafe {
    public String crackSafe(int n, int k) {
        StringBuilder sb = new StringBuilder();
        int total = (int) (Math.pow(k, n));
        for (int i = 0; i < n; i++) sb.append('0');

        Set<String> visited = new HashSet<>();
        visited.add(sb.toString());

        dfs(sb, total, visited, n, k);

        return sb.toString();
    }

    private boolean dfs(StringBuilder sb, int goal, Set<String> visited, int n, int k) {
        if (visited.size() == goal) return true;
        String prev = sb.substring(sb.length() - n + 1, sb.length());
        for (int i = 0; i < k; i++) {
            String next = prev + i;
            if (!visited.contains(next)) {
                visited.add(next);
                sb.append(i);
                if (dfs(sb, goal, visited, n, k)) return true;
                else {
                    visited.remove(next);
                    sb.delete(sb.length() - 1, sb.length());
                }
            }
        }
        return false;
    }

    ////??????????????????
        public String crackSafe(int n, int k) {                
        final int total = (int) Math.pow(k, n), mod = (int) Math.pow(10, n - 1);
        final Map<Integer, Integer> map = new HashMap();
        map.put(0, 0);
        final Stack<Integer> stack = new Stack();
        stack.add(0);
        while (stack.size() != total) {
            int top = stack.peek();
            int prefix = (top % mod) * 10;
            for (int i = map.get(top); i < k; ++i) {
                int next = prefix + i;
                if (!map.containsKey(next)) {
                    map.put(top, i + 1);
                    map.put(next, 0);
                    stack.push(next);
                    break;
                }
            }
            if (top == stack.peek()) {
                map.remove(top);
                stack.pop();
            }
        }
        final StringBuilder sb = new StringBuilder();
        while (1 != stack.size()) {
            sb.append(stack.pop() % 10);
        }
        return String.format(String.format("%%0%dd", n), stack.pop()) + sb.reverse().toString();
    }

}