package dp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


// Longest Increasing Subsequence
//https://leetcode.com/problems/integer-break/
//https://leetcode.com/problems/increasing-subsequences/
//to read: https://leetcode.com/problems/shopping-offers/discuss/105214/A-just-for-fun-only-DP-solution
public class ShoppingOffers {
    public int shoppingOffers(List<Integer> prices, List<List<Integer>> special, List<Integer> needs) {
        return helper (prices, special, needs);
    }
    private int helper(List<Integer> prices, List<List<Integer>> special, List<Integer> needs) {
        int result = maximumTotal(needs, prices);
        int i = 0;
        for (List<Integer> offer: special) {
            List<Integer> clone = new ArrayList<Integer>(needs);
            for (i = 0; i < needs.size(); i++) {
                int diff = clone.get(i) - offer.get(i);
                if (diff < 0) break;
                clone.set(i, diff);
            }
            if (i == needs.size()) {
                result = Math.min(result, offer.get(i) + helper(prices, special, clone));
            }
        }
        return result;
    }
    private int maximumTotal(List<Integer> needs, List<Integer> prices) {
        int sum = 0;
        for (int i = 0; i < needs.size(); i++) {
            sum += needs.get(i) * prices.get(i);
        }
        return sum;
    }

//    [2,5]
//            [[3,0,5],[1,2,10]]
//            [3,2]
//    14

    public int shoppingOffersWithMemoization(List<Integer> prices, List<List<Integer>> special, List<Integer> needs) {
        Map<List<Integer>, Integer> memo = new HashMap<List<Integer>, Integer>();
        return helperWithMemoization(prices, special, needs, memo);
    }
    private int helperWithMemoization(List<Integer> prices, List<List<Integer>> special, List<Integer> needs, Map<List<Integer>, Integer> memo) {
        if (memo.containsKey(needs)) {
            return memo.get(needs);
        }
        int i = 0;
        int result = maximumTotal(needs, prices);
        for (List<Integer> offer: special) {
            ArrayList<Integer> clone = new ArrayList<Integer>(needs);
            for (i = 0; i < needs.size(); i++) {
                int difference = needs.get(i) - offer.get(i);
                if (difference < 0) break;
                clone.set(i, difference);
            }
            if (i == needs.size()) {
                result = Math.min(result, offer.get(i) + helperWithMemoization(prices, special, clone, memo));
            }
        }
        memo.put(needs, result);
        return result;
    }
    // Very similar to first one
    public int shoppingOffersDFS(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        return helper(price, special, needs, 0);
    }

    private int helper(List<Integer> price, List<List<Integer>> special, List<Integer> needs, int pos) {
        int local_min = maximumTotal(needs, price);
        for (int i = pos; i < special.size(); i++) {
            List<Integer> offer = special.get(i);
            List<Integer> temp = new ArrayList<Integer>();
            for (int j= 0; j < needs.size(); j++) {
                if (needs.get(j) < offer.get(j)) { // check if the current offer is valid
                    temp =  null;
                    break;
                }
                temp.add(needs.get(j) - offer.get(j));
            }

            if (temp != null) { // use the current offer and try next
                local_min = Math.min(local_min, offer.get(offer.size() - 1) + helper(price, special, temp, i));
            }
        }

        return  local_min;
    }
    public int shoppingOffersBackTrace(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        return backTrace(price, special, needs, 0);
    }
    private int backTrace(List<Integer> price, List<List<Integer>> special, List<Integer> needs, int start) {
        int result = maximumTotal(needs, price);
        for (int i = start; i < special.size(); i++) {
            List<Integer> offer = special.get(i);
            List<Integer> newNeeds = new ArrayList<Integer>();
            for (int j = 0; j < needs.size(); j++) {
                if (needs.get(j) < offer.get(j)) break;
                newNeeds.add(needs.get(j) - offer.get(j));
            }
            if (newNeeds.size() == needs.size()) {
                result = Math.min(result, offer.get(offer.size() - 1) + backTrace(price, special, newNeeds, i));
            }
        }
        return result;
    }

//    With memo seems do not help at all
    public int shoppingOffersBackTraceWithMemo(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        Map<List<Integer>, Integer> memo = new HashMap<List<Integer>, Integer>();
        return backTrace(price, special, needs, memo, 0);
    }
    private int backTrace(List<Integer> price, List<List<Integer>> special, List<Integer> needs, Map<List<Integer>, Integer> memo, int start) {
        if (memo.get(needs) != null)
            return memo.get(needs);
        int result = maximumTotal(needs, price);
        for (int i = start; i < special.size(); i++) {
            List<Integer> offer = special.get(i);
            List<Integer> newNeeds = new ArrayList<Integer>();
            for (int j = 0; j < needs.size(); j++) {
                if (needs.get(j) < offer.get(j)) break;
                newNeeds.add(needs.get(j) - offer.get(j));
            }
            if (newNeeds.size() == needs.size()) {
                result = Math.min(result, offer.get(offer.size() - 1) + backTrace(price, special, newNeeds, memo, i));
            }
        }
        memo.put(needs, result);
        return result;
    }
    public int shoppingOffersBackTrace1(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        return backTrace(price, special, needs);
    }
    private int backTrace(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        int total = 0;
        for (int i = 0; i < needs.size(); i++) {
            total += needs.get(i) * price.get(i);
        }
        for (List<Integer> offer: special) {
            if (canUseOffer(offer, needs)) {
                for (int i = 0; i < needs.size(); i++) {
                    needs.set(i, needs.get(i) - offer.get(i));
                }
                total = Math.min(total, offer.get(needs.size()) + backTrace(price, special, needs));
                for (int i = 0; i < needs.size(); i++) {
                    needs.set(i, needs.get(i) + offer.get(i));
                }
            }
        }
        return total;
    }
    private boolean canUseOffer(List<Integer> offer, List<Integer> needs) {
        for (int i = 0; i < needs.size(); i++) {
            if (needs.get(i) < offer.get(i)) return false;
        }
        return true;
    }

}
