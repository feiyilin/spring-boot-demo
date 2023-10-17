package com.xkcoding.java.base.leetcode;

/**
 * @author feiyilin
 * @date 2023/10/17 14:03
 */
/*
给你一个整数数组 prices ，其中 prices[i] 表示某支股票第 i 天的价格。

在每一天，你可以决定是否购买和/或出售股票。你在任何时候 最多 只能持有 一股 股票。你也可以先购买，然后在 同一天 出售。

返回 你能获得的 最大 利润 。

示例 1：

输入：prices = [7,1,5,3,6,4]
输出：7
解释：在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5 - 1 = 4 。
     随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6 - 3 = 3 。
     总利润为 4 + 3 = 7 。
示例 2：

输入：prices = [1,2,3,4,5]
输出：4
解释：在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5 - 1 = 4 。
     总利润为 4 。
示例 3：

输入：prices = [7,6,4,3,1]
输出：0
解释：在这种情况下, 交易无法获得正利润，所以不参与交易可以获得最大利润，最大利润为 0 。

提示：

1 <= prices.length <= 3 * 104
0 <= prices[i] <= 104
 */
public class 中等_买卖股票的最佳时机II_122 {

    public static void main(String[] args) {
        maxProfit(new int[]{7,6,4,3,1});
    }

    public static int maxProfit(int[] prices) {

        // stock和cash两个状态，维护两个状态
        // stock表示当前持股时最大的利润，cash表示当前不持股时最大的利润
        // 每天（i）结束只有两个状态：持股和不持股，决定每一天的状态是那个就能决定最大值，每天的最大利润考虑之前的天，具有前缀性 两个状态的算法是相互依赖的
        // 每天持股状态（前缀性）的最大利润：1、前一天的持股最大利润 2、前一天不持股最大利润 - 当天股价
        // 每天不持股状态（前缀性）的最大利润：1、前一天的不持股最大利润 2、前一天持股最大利润 + 当天股价
        // 最后一天的不持股状态肯定大于最后一天的持股状态
        int n = prices.length;
        int[] stock = new int[n];
        int[] cash = new int[n];

        stock[0] = -prices[0];
        cash[0] = 0;
        for (int i = 1; i < prices.length; i++) {
            stock[i] = Math.max(stock[i - 1], cash[i - 1] - prices[i]);
            cash[i] = Math.max(cash[i - 1], stock[i - 1] + prices[i]);
        }
        return cash[n - 1];
    }

    public int maxProfit2(int[] prices) {

        if (prices.length == 1) {
            return 0;
        }
        int res = 0;
        for (int i = 1; i < prices.length; i++) {
            int diff = prices[i] - prices[i - 1];
            if (diff > 0) {
                res += diff;
            }
        }
        return res;
    }
}
