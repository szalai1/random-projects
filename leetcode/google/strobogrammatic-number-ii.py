class Solution:
    def findStrobogrammatic(self, n: int) -> List[str]:
        i = n
        strs = []
        if n % 2 == 1:
            strs.append('1')
            strs.append('8')
            strs.append('0')
            i-=1
        else:
            strs.append('')
            
        while i > 0:
            i-=2
            next_strs = []
            for s in strs:
                next_strs.append('0' + s + '0')
                next_strs.append('1' + s + '1')
                next_strs.append('8' + s + '8')
                next_strs.append('9' + s + '6')
                next_strs.append('6' + s + '9')
            del strs
            strs = next_strs
        if n > 1:
            final = []
            for s in strs:
                if s[0] == '0':
                    continue
                final.append(s)
            del strs
            strs = final

                
        return strs
            
