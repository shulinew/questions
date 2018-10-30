package string;

/*
 * Given a non-negative integer num, repeatedly add all its digits until the result has only one digit. 
 */
public class AddDigits {
    public int addDigits(int num) {
    	int temp = num;
    	int sum = 0;
    	while (temp >= 10){
    		sum = sum + temp % 10;
    		temp = temp/10;
    		if (temp < 10) {
    	    	sum += temp;
    	    	temp = sum;
    	    	sum = 0;
    		}
    	}
    	return temp;
        
    }
    
    public int addDigits1(int num){
    	if(num==0){return 0;}
    	return num%9==0?9:num - 9*(num/9);
    	
    	/*
    	 *        
    	 *        if (num % 9 == 0){
            return 9;
        }
        else {
            return num % 9;
        }
    	 */
    }
    public static void main(String[] args) {
    	AddDigits ad = new AddDigits();
    	ad.addDigits(19);
    }

}
