import bisect 

class Solution(object):
    def threeSum(self, nums):
        """
        :type nums: List[int]
        :rtype: List[List[int]]
        """
        if len(nums) < 3:
            return []
        nums = sorted(nums)
        good_lists = set()
        print(nums)
        for i in range(len(nums)-2):
            ii = nums[i]
            l = i + 1
            r = len(nums)-1
            while l < r: 
                x = nums[l]
                y = nums[r]
                s = x + y + ii
                if s > 0: 
                    r-=1
                elif s < 0:
                    l+=1
                else: 
                    good_lists.add((x,y,ii))
                    r-=1
                    l+=1
        return [list(l) for l in good_lists]
