class Solution:
    def backspaceCompare(self, s: str, t: str) -> bool:
        def _apply_delete(s: str): 
            res = ""
            del_counter = 0
            for l in s[::-1]:
                if l == '#':
                    del_counter += 1
                    continue 
                if del_counter > 0:
                    del_counter -= 1
                    continue 
                res+= l
            return res 
        return  _apply_delete(t) ==_apply_delete(s)
        