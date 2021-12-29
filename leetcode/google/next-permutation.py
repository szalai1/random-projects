class Solution(object):
    def nextPermutation(self, nums):
        """
        :type nums: List[int]
        :rtype: None Do not return anything, modify nums in-place instead.
        """
        i = len(nums) - 2
        while i > 0:
            b = nums[i]
            a = nums[i+1]
            if a > b:  
                break
            i-=1
        if i == 0 and nums[0] > nums[1]:
            nums.reverse()
            return
        n = nums[i]
        j = len(nums)-1
        while j > i:
            if nums[j] > n:
                break
            j-=1
        nums[i] = nums[j]
        nums[j] = n 
        tmp = nums[i+1:]
        tmp.reverse()
        nums[i+1:] = tmp
        

print(Solution().nextPermutation([1,2,3,4,3,2,1]))
print(Solution().nextPermutation([1,3,2]))
print(Solution().nextPermutation([1,3,2]))