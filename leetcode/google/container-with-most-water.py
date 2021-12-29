class Solution(object):
    def maxArea(self, height):
        """
        :type height: List[int]
        :rtype: int
        """
        a, b = 0, len(height)-1
        max_w = min(height[0], height[-1])*b 
        while a < b:
            a_h = height[a]
            b_h = height[b]
            water = min(a_h, b_h)*(b-a)
            if water>max_w:
                max_w=water 
            if a_h > b_h:
                b-=1
            else: 
                a+=1
        return max_w

for i in [[1,8,6,2,5,4,8,3,7],
        [1,1],
        [1,8,6,2,5,4,8,3,7,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1]]:
    print(i, Solution().maxArea(i))