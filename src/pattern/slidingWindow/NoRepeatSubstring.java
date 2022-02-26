package pattern.slidingWindow;

import java.util.HashMap;
import java.util.Map;

public class NoRepeatSubstring {

    public static int findLength(String str) {
        int maxLen = 0;
        int windowStart = 0;
//        Set<Character> characterIntegerMap = new HashSet<>();
        Map<Character, Integer> characterIntegerMap = new HashMap<>();
        for(int windowEnd = 0; windowEnd < str.length(); windowEnd++) {
            char currentChar = str.charAt(windowEnd);
//            if (!characterIntegerMap.contains(currentChar)) {
//                characterIntegerMap.add(currentChar);
//            } else {
//                //Wrong: the windowStartt is not always windowEnd
//                windowStart = windowEnd;
//            }
            // in current window, there is no currentChar
            // in which condition, windowStart will be ahead of last index of currentChar
            if(characterIntegerMap.containsKey(currentChar)) {
                windowStart = Math.max(windowStart, characterIntegerMap.get(currentChar) + 1);
            }
            characterIntegerMap.put(currentChar, windowEnd);
            maxLen = Math.max(maxLen, windowEnd - windowStart + 1);
        }
        return maxLen;
    }
}
