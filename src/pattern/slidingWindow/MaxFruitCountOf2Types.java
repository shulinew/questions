package pattern.slidingWindow;

import java.util.HashMap;
import java.util.Map;

public class MaxFruitCountOf2Types {
    public static int findLength(char[] arr) {
        int maxLength = 0;
        int windowStart = 0;
        Map<Character, Integer> characterIntegerMap = new HashMap<>();
        for(int windowEnd = 0; windowEnd < arr.length; windowEnd++) {
            characterIntegerMap.put(arr[windowEnd], characterIntegerMap.getOrDefault(arr[windowEnd], 0) + 1);
            //while loop only process each character once
            while (characterIntegerMap.size() > 2) {
                char leftCar = arr[windowStart];
                characterIntegerMap.put(leftCar, characterIntegerMap.get(leftCar) - 1);
                if (characterIntegerMap.get(leftCar) == 0) {
                    characterIntegerMap.remove(leftCar);
                }
                windowStart++;
            }
            maxLength = Math.max(maxLength, windowEnd - windowStart + 1);
        }
        return maxLength;
    }
}
