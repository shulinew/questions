package math;

/*
 * There are n bulbs that are initially off. You first turn on all the bulbs. Then, you turn off every second bulb. On the third round, you toggle every third bulb (turning on if it's off or turning off if it's on). For the ith round, you toggle every i bulb. For the nth round, you only toggle the last bulb. Find how many bulbs are on after n rounds. 
 * Given n = 3. 

At first, the three bulbs are [off, off, off].
After first round, the three bulbs are [on, on, on].
After second round, the three bulbs are [on, off, on].
After third round, the three bulbs are [on, off, off]. 

So you should return 1, because there is only one bulb is on.
https://discuss.leetcode.com/topic/38689/the-simplest-and-most-efficient-solution-well-explained/2
 */
public class BulbSwitcher {
    public int bulbSwitch(int n) {
        int on = 0;
        for (int i = 1; i * i <= n; i++)    {
            on++;
        }
            
        return on;
    }

}

/*
 * As we know that there are n bulbs, let's name them as 1, 2, 3, 4, ..., n.

    You first turn on all the bulbs.
    Then, you turn off every second bulb.(2, 4, 6, ...)
    On the third round, you toggle every third bulb.(3, 6, 9, ...)
    For the ith round, you toggle every i bulb.(i, 2i, 3i, ...)
    For the nth round, you only toggle the last bulb.(n)

If n > 6, you can find that bulb 6 is toggled in round 2 and 3.

Later, it will also be toggled in round 6, and round 6 will be the last round when bulb 6 is toggled.

Here, 2,3 and 6 are all factors of 6 (except 1).
*/
