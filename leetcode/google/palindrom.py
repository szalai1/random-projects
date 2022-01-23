class Solution:
    def longestPalindrome(self, s: str) -> str:
        
        def _maxcenteredPalindrom(i, j):
            while i>0 and j<len(s)-1 and s[i-1] == s[j+1]:
                i-=1
                j+=1
                
            return i, j
        max_l, ii, jj = 1,0,0
        for i in range(len(s)-1):
            a, b = _maxcenteredPalindrom(i,i)
            if max_l < b-a+1:
                ii, jj, max_l = a, b, b-a+1
            if s[i] == s[i+1]:
                a, b = _maxcenteredPalindrom(i,i+1)
                if max_l < b-a + 1:
                    ii, jj, max_l = a, b, b-a+1
    
        return s[ii:jj+1]