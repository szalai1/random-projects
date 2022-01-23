### O(N)
class Solution:
    def maxSubArray(self, nums: List[int]) -> int:
        min_sum = 0
        max_sum = float("-inf")
        s = 0
        n = len(nums)
        for i in range(n):
            s += nums[i]
            max_sum = max(max_sum, s-min_sum)
            min_sum = min(min_sum, s)
        return  max_sum


        