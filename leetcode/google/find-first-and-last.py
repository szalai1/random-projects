class Solution:
    def find(self, nums, n, left_side=True):
        left, right = 0, len(nums)-1
        midpoint = 0 
        if left_side and nums[0] == n: 
            return 0
        if not left_side and nums[-1] == n:
            return len(nums) -1 
        while left < right: 
            print(left, right)
            midpoint = left + int((right - left) / 2)  
            mid_val = nums[midpoint]
            if mid_val < n:
                left = midpoint + 1
            elif mid_val > n:
                right = midpoint - 1
            else:
                if left_side:
                    if nums[midpoint-1] != n:
                        return midpoint
                    right = midpoint - 1
                else:
                    if nums[midpoint+1] != n:
                        return midpoint
                    left = midpoint + 1
        print(left, right)
        if left_side and nums[right] == n:
            return right 
        if not left_side and nums[left] == n:
            return left

        return -1
        
        
    def searchRange(self, nums: List[int], target: int) -> List[int]:
        if len(nums) == 0:
            return [-1, -1]
        l = self.find(nums, target, left_side=True)

        r = self.find(nums, target, left_side=False)
        return [l, r]
        