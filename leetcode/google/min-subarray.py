class Solution:
    def splitArray(self, nums: List[int], m: int) -> int:
        F = {}
        def _min_max_reamining(current_i, current_m):
            if (current_i, current_m) in F:
                return F[(current_i, current_m)]
            if current_m == 1:
                F[(current_i, current_m)] = sum(nums[current_i:])
                return F[(current_i, current_m)] 
            split = 0 #nums[current_i]
            res  = float("inf")
            for i in range(current_i, len(nums)-current_m+1):
                split += nums[i]
                remaining_max = 0 
                res = min(res, max(_min_max_reamining(i+1, current_m-1), split))
                if res < split:
                    break
            F[(current_i, current_m)] = res
            return res 
        
        res =  _min_max_reamining(0, m)
        return res
                