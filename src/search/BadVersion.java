package search;

public class BadVersion {
    public int firstBadVersion(int n) {
        int low = 1, high = n;
        
        while (low < high){
        	int current = (low + high)/2;
        	if (isBadVersion(current)){
        		high = current;
        	}else{
        		low = current + 1;
        	}
        }
        return low;
    }
    private boolean isBadVersion(int i){
    	return false;
    }

}
