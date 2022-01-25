class Solution:
    def maxProfit(self, prices: List[int]) -> int:
        max_gain = 0
        min_price = prices[0]
        for i in range(len(prices)-1):
            min_price = min(prices[i], min_price)
            next_day = prices[i+1]
            max_gain = max(max_gain, next_day-min_price)
        return max_gain