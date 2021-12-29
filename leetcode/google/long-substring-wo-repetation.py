class Solution(object):
    def lengthOfLongestSubstring(self, s):
        """
        :type s: str
        :rtype: int
        """
        d = {}
        res = 0
        start = 0 
        for i in range(len(s)):
            l = s[i]
            if l in d:
                if res < len(d):
                    res = len(d)
                while s[start] != l:
                    del d[s[start]]
                    start += 1
                start+=1
            else:
                d[l] = i
        if res < len(d):
            res = len(d)
        return res 


print(Solution().lengthOfLongestSubstring(" "))