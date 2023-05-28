class Solution {
    public int maxProfit(int[] prices) {
        int maxProfit = 0;
        int buyDay = 0;
        for (int day = 1; day < prices.length; day++) {
            if (prices[day] <= prices[buyDay]) {
                buyDay = day;
                continue;
            }
            maxProfit = Math.max(maxProfit, prices[day] - prices[buyDay]);
        }
        return maxProfit;
    }
}