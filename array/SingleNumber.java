package array;

import java.util.HashSet;
import java.util.Set;

public class SingleNumber {
    public int singleNumber(int[] nums) {
    	int r = 0;
    	for (int i = 0; i< nums.length;i++){
    		r = r ^nums[i];
    	}
    	return r;
        
    }
    
    public int[] singleNumbers(int[] nums) {
    	Set<Integer> set = new HashSet<Integer>();
    	for (int i =0;i<nums.length;i++){
    		if(!set.contains(nums[i])){
    			set.add(nums[i]);
    		}else{
    			set.remove(nums[i]);
    		}
    	}
    	int[] indices = new int[set.size()];
    	int j = 0;
    	for (Integer i: set){
    		indices[j++] = i;
    	}
        return indices;
    }
    public int[] singleNumbers1(int[] nums){
        int res[] = new int[2];
        int xor = 0;
        
        for (int i = 0; i < nums.length; i++) {
            xor ^= nums[i];
        }
        
        int rightRes = xor & (~(xor - 1));
        
        int group1 = 0, group2 = 0;
        for (int i = 0; i < nums.length; i++) {
            if ((rightRes & nums[i]) == 0) {
                group1 ^= nums[i];
            } else {
                group2 ^= nums[i];
            }
        }
        
        res[0] = group1;
        res[1] = group2;
        return res;
    }
    public int[] singleNumber2(int[] nums) {
        // Pass 1 : 
        // Get the XOR of the two numbers we need to find
        int diff = 0;
        for (int num : nums) {
            diff ^= num;
        }
        // Get its last set bit
        diff &= -diff;
        
        // Pass 2 :
        int[] rets = {0, 0}; // this array stores the two numbers we will return
        for (int num : nums)
        {
            if ((num & diff) == 0) // the bit is not set
            {
                rets[0] ^= num;
            }
            else // the bit is set
            {
                rets[1] ^= num;
            }
        }
        return rets;
    }
    
    /*
     * Given an array of integers, every element appears three times except for one. Find that single one.
     * his solution is awesome. And it can easily be extended to 4,5,6...
The core idea is as follows. For one element x occurring P+1 times, we use P intermediate values, M[P].

For these intermediate values, we want to construct a loop.
[0,0,...0] ----> [0,x,...0] ----> [0,0,x...0] ... [0,0,...x] ----> [0,0,...x]. This can be achieved by following codes.
for(int j=0;j<P;j++)
{
int tmp = -1;
for(int k=0;k<P;k++)
{
if(k!=j)
{
tmp &= ~M[k];
}
}
M[j] = (x^M[j]) & tmp;
} 
     */
    
    /*
     * First time number appear -> save it in "ones"

Second time -> clear "ones" but save it in "twos" for later check

Third time -> try to save in "ones" but value saved in "twos" clear it.


Write all numbers in binary form, then for any bit 1 that appeared 3*n times (n is an integer), the bit can only present in numbers that appeared 3 times

e.g. 0010 0010 0010 1011 1011 1011 1000 (assuming 4-bit integers)
2(0010) and 11(1011) appeared 3 times, and digit counts are:

    Digits 3 2 1 0

    Counts 4 0 6 3

    Counts%3 1 0 0 0

Counts on 2,1,0 are all times of 3, the only digit index that has Counts % 3 != 0 is 3

Therefore, to find the number that appeared only 1 or 2 times, we only need to extract all bits that has Counts %3 != 0

Now consider how we could do this by bit manipulation

since counts % 3 has only 3 states: 0(00),1(01),2(10)
we could use a TWO BIT COUNTER (Two, One) to represent Counts % 3, now we could do a little research on state transitions, for each bit, let B be the input bit, we can enumerate the all possible state transitions, Two+, One+ is the new state of Two, One. (here we need to use some knowledge in Digital Logic Design)

    Two One B Two+ One+

    0 0 0 0 0

    0 0 1 0 1

    0 1 0 0 1

    0 1 1 1 0

    1 0 0 1 0

    1 0 1 0 0

    1 1 0 X X (X represents we don't care)

    1 1 1 X X

We could then draw the Karnaugh map to analyze the logic (https://en.wikipedia.org/wiki/Karnaugh_map), and then we get:

    One+ = (One ^ B) & (~Two)

    Two+ = (~One+) & (Two ^ B)

Now for int_32, we need only 2 int_32 two represent Two and One for each bit and update Two and One using the rules derived above
     */
    public int singleNumber3(int[] nums) {
        int ones = 0, twos = 0;
        for(int i = 0; i < nums.length; i++){
            ones = (ones ^ nums[i]) & ~twos;
            twos = (twos ^ nums[i]) & ~ones;
        }
        return ones;
        
    }
    
    /*
     * =======================================================================
     * Given an array of integers, every element appears three times except for one. Find that single one. 
     */
    
    public int singleNumberIII(int[] nums) {
        
    }
    
    /*
     * this kind of question the key idea is design a counter that record state. the problem can be every one occurs K times except one occurs M times. for this question, K =3 ,M = 1(or 2) .
so to represent 3 state, we need two bit. let say it is a and b, and c is the incoming bit.
then we can design a table to implement the state move.

this kind of question the key idea is design a counter that record state. the problem can be every one occurs K times except one occurs M times. for this question, K =3 ,M = 1(or 2) .
so to represent 3 state, we need two bit. let say it is a and b, and c is the incoming bit.
then we can design a table to implement the state move.

current   incoming  next
a b            c    a b
0 0            0    0 0
0 1            0    0 1
1 0            0    1 0
0 0            1    0 1
0 1            1    1 0
1 0            1    0 0

like circuit design, we can find out what the next state will be with the incoming bit.( we only need find the ones)
then we have for a to be 1, we have

    current   incoming  next
    a b            c    a b
    1 0            0    1 0
    0 1            1    1 0

and this is can be represented by

a=a&~b&~c + ~a&b&c

and b can do the same we , and we find that

b= ~a&b&~c+~a&~b&c

and this is the final formula of a and b and just one of the result set, because for different state move table definition, we can generate different formulas, and this one is may not the most optimised. as you may see other's answer that have a much simple formula, and that formula also corresponding to specific state move table. (if you like ,you can reverse their formula to a state move table, just using the same way but reversely)

for this questions we need to find the except one
as the question don't say if the one appears one time or two time ,
so for ab both

01 10 => 1
00 => 0

we should return a|b;
this is the key idea , we can design any based counter and find the occurs any times except one .
here is my code. with comment.
     */
    
    public int singleNumberBest(int[] nums) {
        //we need to implement a tree-time counter(base 3) that if a bit appears three time ,it will be zero.
        //#curent  income  ouput
        //# ab      c/c       ab/ab
        //# 00      1/0       01/00
        //# 01      1/0       10/01
        //# 10      1/0       00/10
        // a=~abc+a~b~c;
        // b=~a~bc+~ab~c;
        int a=0;
        int b=0;
        for(int c:nums){
            int ta=(~a&b&c)|(a&~b&~c);
            b=(~a&~b&c)|(~a&b&~c);
            a=ta;
        }
        //we need find the number that is 01,10 => 1, 00 => 0.
        return a|b;
        
    }
    
    /*
     * The usual bit manipulation code is bit hard to get and replicate. I like to think about the number in 32 bits and just count how many 1s are there in each bit, and sum %= 3 will clear it once it reaches 3. After running for all the numbers for each bit, if we have a 1, then that 1 belongs to the single number, we can simply move it back to its spot by doing ans |= sum << i;

This has complexity of O(32n), which is essentially O(n) and very easy to think and implement. Plus, you get a general solution for any times of occurrence. Say all the numbers have 5 times, just do sum %= 5.

public int singleNumber(int[] nums) {
    int ans = 0;
    for(int i = 0; i < 32; i++) {
        int sum = 0;
        for(int j = 0; j < nums.length; j++) {
            if(((nums[j] >> i) & 1) == 1) {
                sum++;
                sum %= 3;
            }
        }
        if(sum != 0) {
            ans |= sum << i;
        }
    }
    return ans;
}
     */
    
/*
 * =================================================================================
 * 

Statement of our problem: "Given an array of integers, every element appears k (k >1) times except for one, which appears p times(p>=1, p % k != 0). Find that single one."

As others pointed out, in order to apply the bitwise operations, we should rethink how integers are represented in computers -- by bits. To start, let's consider only one bit for now. 
Suppose we have an array of 1-bit numbers (which can only be 0 or 1), we'd like to count the number of '1's in the array such that whenever the counted number of '1' reaches a certain value, 
say k, the count returns to zero and starts over (In case you are curious, this k will be the same as the one in the problem statement above). To keep track of how many '1's we 
have encountered so far, we need a counter. Suppose the counter has m bits in binary form: xm, ..., x1 (from most significant bit to least significant bit). We can conclude at least 
the following four properties of the counter:

    There is an initial state of the counter, which for simplicity is zero;
    For each input from the array, if we hit a '0', the counter should remain unchanged;
    For each input from the array, if we hit a '1', the counter should increase by one;
    In order to cover k counts, we require 2^m >= k, which implies m >= logk.

Here is the key part: how each bit in the counter (x1 to xm) changes as we are scanning the array. Note we are prompted to use bitwise operations. In order to satisfy the second 
property, recall what bitwise operations will not change the operand if the other operand is 0? Yes, you got it: x = x | 0 and x = x ^ 0.

Okay, we have an expression now: x = x | i or x = x ^ i, where i is the scanned element from the array. Which one is better? We don't know yet. So, let's just do the actual 
counting:

At the beginning, all bits of the counter is initialized to zero, i.e., xm = 0, ..., x1 = 0. Since we are gonna choose bitwise operations that guarantee all bits of the counter 
remain unchanged if we hit '0's, the counter will be 0 until we hit the first '1' in the array. After we hit the first '1', we got: xm = 0, ...,x2 = 0, x1 = 1. Let's continue
 until we hit the second '1', after which we have: xm = 0, ..., x2 = 1, x1 = 0. Note that x1 changes from 1 to 0. For x1 = x1 | i, after the second count, x1 will still be 1. 
 So it's clear we should use x1 = x1 ^ i. What about x2, ..., xm? The idea is to find the condition under which x2, ..., xm will change their values. Take x2 as an example. 
 If we hit a '1', and we need to change the value of x2, what must the value of x1 be before we do the change? The answer is: x1 must be 1 otherwise we shouldn't change x2 because
  changing x1 from 0 to 1 will do the job. So x2 will change only if x1 and i are both 1, or mathematically, x2 = x2 ^ (x1 & i). Similarly xm will change only when xm-1, ..., x1 
  and i are all 1: xm = xm ^ (xm-1 & ... & x1 & i); Bingo, we've found the bitwise operations!

However, you may notice that the bitwise operations found above will count from 0 until 2^m - 1, instead of k. If k < 2^m - 1, we need some "cutting" mechanism to reinitialize the 
counter to 0 when the count reaches k. To this end, we apply bitwise AND to xm,..., x1 with some variable called mask, i.e., xm = xm & mask, ..., x1 = x1 & mask. 
If we can make sure the mask will be 0 only when the count reaches k and be 1 for all other count cases, then we are done. How do we achieve that? Try to think what distinguishes the case with k count from all other count cases. Yes, it's the count of '1's! For each count, we have unique values for each bit of the counter, which can be regarded as its state. If we write k in its binary form: km,..., k1. we can construct the mask as follows:

mask = ~(x1' & x2' & ... xm'), where xj' = xj if kj = 1 and xj' = ~xj if kj = 0 (j = 1 to m).

Let's do some examples:

k = 3: k1 = 1, k2 = 1, mask = ~(x1 & x2);

k = 5: k1 = 1, k2 = 0, k3 = 1, mask = ~(x1 & ~x2 & x3);

In summary, our algorithm will go like this:

for (int i : array) {

xm ^= (xm-1 & ... & x1 & i);

xm-1 ^= (xm-2 & ... & x1 & i);

.....

x1 ^= i;

mask = ~(x1' & x2' & ... xm') where xj' = xj if kj = 1 and xj' = ~xj if kj = 0 (j = 1 to m).

xm &= mask;

......

x1 &= mask;

}

Now it's time to generalize our results from 1-bit number case to 32-bit integers. One straightforward way would be creating 32 counters for each bit in the integer. You've probably already seen this in other posted codes. But if we take advantage of bitwise operations, we may be able to manage all the 32 counters "collectively". By saying "collectively" we mean using m 32-bit integers instead of 32 m-bit counters, where m is the minimum integer that satisfies m >= logk. The reason is that bitwise operations apply only to each bit so operations on different bits are independent of each other(kind obvious, right?). This allows us to group the corresponding bits of the 32 counters into one 32-bit integer. Since each counter has m bits, we end up with m 32-bit integers. Therefore, in the algorithm developed above, we just need to regard x1 to xm as 32-bit integers instead of 1-bit numbers and we are done. Easy, hum?

The last thing is what value we should return, or equivalently which one of x1 to xm will equal the single element. To get the correct answer, we need to understand what the m 32-bit integers x1 to xm represent. Take x1 as an example. x1 has 32 bits and let's label them as r (r = 1 to 32), After we are done scanning the input array, the value for the r-th bit of x1 will be determined by the r-th bit of all the elements in the array (more specifically, suppose the total count of '1' for the r-th bit of all the elements in the array is q, q' = q % k and in its binary form: q'm,...,q'1, then by definition the r-th bit of x1 will equal q'1). Now you can ask yourself this question: what does it imply if the r-th bit of x1 is '1'?

The answer is to find what can contribute to this '1'. Will an element that appears k times contribute? No. Why? Because for an element to contribute, it has to satisfy at least two conditions at the same time: the r-th bit of this element is '1' and the number of appearance of this '1' is not an integer multiple of k. The first condition is trivial. The second comes from the fact that whenever the number of '1' hit is k, the counter will go back to zero, which means the corresponding bit in x1 will be set to 0. For an element that appears k times, it's impossible to meet these two conditions simultaneously so it won't contribute. At last, only the single element which appears p (p%k != 0) times will contribute. If p > k, then the first k*[p/k] (denotes the integer part of p/k) single elements won't contribute either. Then we can always set p' = p % k and say the single element appears effectively p' times.

Let's write p' in its binary form: p'm, ..., p'1. (note that p' < k, so it will fit into m bits). Here I claim the condition for x1 to equal the single element is p'1 = 1. Quick proof: if the r-th bit of x1 is '1', we can safely say the r-th bit of the single element is also '1'. We are left to prove that if the r-th bit of x1 is '0', then the r-th bit of the single element can only be '0'. Just suppose in this case the r-th bit of the single element is '1', let's see what will happen. At the end of the scan, this '1' will be counted p' times. If we write p' in its binary form: p'm, ..., p'1, then by definition the r-th bit of x1 will equal p'1, which is '1'. This contradicts with the presumption that the r-th bit of x1 is '0'. Since this is true for all bits in x1, we can conclude x1 will equal the single element if p'1 = 1. Similarly we can show xj will equal the single element if p'j = 1(j = 1 to m). Now it's clear what we should return. Just express p' = p % k in its binary form, and return any of the corresponding xj as long as p'j = 1.

In total, the algorithm will run in O(n*logk) time and O(logk) space.

Hope this helps!

 */
}
