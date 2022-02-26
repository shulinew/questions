package dp;

//https://leetcode.com/problems/minimum-cost-for-tickets/discuss/226659/Two-DP-solutions-with-pictures
public class MiniTrainCost {
    public int minCostTickets(int[] days, int[] costs) {
        boolean[] travelDays = new boolean[366];

        for (int i = 0; i < days.length; i++) {
            travelDays[days[i]] = true;
        }
        int[] calendarDays = new int[366];
        for (int i = 1; i < 366; i++) {
            if (!travelDays[i]) {
                calendarDays[i] = calendarDays[i-1];
            } else {
                calendarDays[i] = Math.min(Math.min(calendarDays[i-1] + costs[0], calendarDays[Math.max(0, i-7)] + costs[1]), calendarDays[Math.max(0, i-30)] + costs[2]);
            }
        }
        return calendarDays[365];
    }
    // since only look back 30 days, can use rolling array for 30
    public int minCostTickets1(int[] days, int[] costs) {
        int[] calendarDays = new int[30];
        int current = 0;
        for (int i = days[0]; i <= days[days.length-1]; i++) {
            if (i != days[current]) {
                calendarDays[i%30] = calendarDays[(i-1)%30];
            } else {
                calendarDays[i%30] = Math.min(Math.min(calendarDays[(i-1)%30] + costs[0], calendarDays[Math.max(0, i-7)%30] + costs[1]), calendarDays[Math.max(0, i-30)%30] + costs[2]);
                current++;
            }
        }
        return calendarDays[days[days.length-1] % 30];
    }
}
