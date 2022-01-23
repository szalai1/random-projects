class Solution:
    invalid_jumps = {
        (1,3):2, (1,9):5, (1,7):4, (3,9):6, (3,7):5, (7,9):8, (2,8):5,(4,6):5
    }
    def numberOfPatterns(self, m: int, n: int) -> int:
        def _generate_pattern(so_far, last, n):
            if n == 0:
                return 1
            counter = 0
            for next_jump in range(1,10):
                if next_jump in so_far:
                    continue
                a, b = next_jump, last

                if a > b: 
                    a, b = b, a
                if self.invalid_jumps.get((a,b), last) in so_far:
                    so_far_c = so_far.copy()
                    so_far_c.add(next_jump)
                    counter += _generate_pattern(so_far_c, next_jump,  n-1)
            return counter

        counter = 0
        for l in range(m, n+1):
            for i in range(1,10):
                counter += _generate_pattern(set([i]), i, l-1)
        return counter



print(Solution().numberOfPatterns(1,1))
print(Solution().numberOfPatterns(1,2))
print(Solution().numberOfPatterns(1,3))
print(Solution().numberOfPatterns(1,4))

