from collections import Counter

class Solution(object):
    def totalFruit(self, fruits):
        """
        :type fruits: List[int]
        :rtype: int
        """
        d = Counter()
        tail = 0  
        res = 0
        for i in range(len(fruits)):
            fruit = fruits[i]
            d[fruit] += 1
            while len(d) > 2:
                d[fruits[tail]] -= 1
                if d[fruits[tail]] == 0:
                    d.pop(fruits[tail])
                tail += 1
            res = max(res, sum(d.values()) )
        return res
            


for i in [
 #   [1,2,3,2,2],
  #  [0,1,2,2],
   # [1,2,1],
    [3,3,3,1,2,1,1,2,3,3,4]]:
    print(Solution().totalFruit(i))