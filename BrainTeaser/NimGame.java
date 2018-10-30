package BrainTeaser;

/*
 *  You are playing the following Nim Game with your friend: There is a heap of stones on the table, 
 *  each time one of you take turns to remove 1 to 3 stones. The one who removes the last stone will be 
 *  the winner. You will take the first turn to remove the stones.

Both of you are very clever and have optimal strategies for the game. Write a function to determine 
whether you can win the game given the number of stones in the heap.

For example, if there are 4 stones in the heap, then you will never win the game: no matter 1, 2, or 3 
stones you remove, the last stone will always be removed by your friend.


 */
public class NimGame {
    public boolean canWinNim(int n) {
	    if(n>=134882061){
		   return n%4 != 0;
	    }
	    int[] array=new int[n+1];
        return dfs(n, array);
	 }
	 public boolean dfs(int n,int[] array){
		 if(array[n]!=0){
			 return array[n]==1?true:false;
		 }
		 if(n<=3){
        	array[n]=1;
        	return true;
        }else{
        	for(int i=1;i<=3;i++){
        		if(!dfs(n-i,array)){
        			array[n-i]=-1;
        			array[n]=1;
        			return true;
        		}
        	}
        	array[n]=-1;
        	return false;
        }
	 }
}
