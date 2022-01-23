class Solution:
    def findMissingRanges(self, nums: List[int], lower: int, upper: int) -> List[str]:
        res = []
        i = 0
        while lower <= upper and i < len(nums):
            if nums[i] == lower: 
                lower += 1
            elif lower + 1 == nums[i]:
                res.append(f"{lower}")
                lower+=2
            elif lower < nums[i]:
                res.append(f"{lower}->{nums[i]-1}")
                lower = nums[i]+1
            else:
                pass
            i+=1
        if len(nums) > 0:
            if lower < upper:
                res.append(f"{lower}->{upper}")
            elif lower == upper:
                res.append(f"{upper}")
            else: 
                pass
        if len(nums) == 0:
            if lower == upper or lower+1 == upper:
                res.append(f"{upper}")
            else:
                res.append(f"{lower}->{upper}")
        return res 