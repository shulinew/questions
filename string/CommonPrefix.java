package string;

public class CommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        String result = "";
        if (strs == null || strs.length <= 0){
            return result;
        }
        int minSize = strs[0].length();
        int minIndex = 0;

        for (int i = 1; i < strs.length; i ++){
            if (strs[i].length() < minSize){
                minSize = strs[i].length();
                minIndex = i;

            }
        }

        for (int i = 0; i < strs.length; i ++){
            while (! strs[i].substring(0,minSize).equals(strs[minIndex].substring(0,minSize))){
                minSize --;
            }
            result = strs[minIndex].substring(0,minSize);
        }

        return result;
        
    }
    public String commonPrefix1(String[] strs){
    	String result = "";
    	if (strs == null || strs.length == 0){
    		return result;
    	}
    	result = strs[0];
    	for (int i = 1; i< strs.length;i++){
    		while(strs[i].indexOf(result) != 0){
    			result = result.substring(0,result.length() -1);
    		}
    	}
    	return result;
    }

}
