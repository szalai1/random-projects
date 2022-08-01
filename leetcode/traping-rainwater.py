class Solution:
    def trap(self, height: List[int]) -> int:
        max_back, max_forw = [], []
        max_b, max_f = 0, 0
        for i in range(len(height)):
            max_b = max(height[i], max_b)
            max_f = max(height[-i-1], max_f)
            max_back.append(max_b)
            max_forw.append(max_f)
        
        water = 0
        for i in range(len(height)):
            water += (min(max_back[i], max_forw[-i-1]) - height[i])
        return water
        
