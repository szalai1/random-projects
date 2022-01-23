from typing import List

class Solution:
    def oddEvenJumps(self, arr: List[int]) -> int:
        odd_jumps = [None]*len(arr)
        arr_s = list(enumerate(arr))
        arr_s.sort(key=lambda x: x[1])


        stack = []
        for i, v in arr_s:
            while stack and stack[-1] < i:
                odd_jumps[stack.pop()] = i
            stack.append(i)
        even_jump = [None]*len(arr)
        arr_s.sort(key=lambda x: x[1], reverse=True)
        for i, v in arr_s:
            while stack and stack[-1] < i:
                even_jump[stack.pop()] = i
            stack.append(i)
        can_jump_e = [False]*len(arr)
        can_jump_o = [False]*len(arr)
        can_jump_e[-1] = True
        can_jump_o[-1] = True

        for i in range(len(arr)-2, -1, -1):
            # can jump in odd
            # 1.x  can jump there x 
            # 2. can jump to where there is event number of job 
            if odd_jumps[i]:
                can_jump_o[i] = can_jump_e[odd_jumps[i]] 
            # can jump in even jumps
            # can even jumpt to where there is an odd jump 
            if even_jump[i]:
                can_jump_e[i] = can_jump_o[even_jump[i]]
        counter = 0 
        for i in range(len(arr)):
            if can_jump_e[i] or can_jump_o[i]:
                counter += 1 
        print("o: ", odd_jumps, "\ne:", even_jump, "\ne: ", can_jump_e, "\no: ", can_jump_o)
        return sum(can_jump_o) 

print(Solution().oddEvenJumps([10,13,12,14,15]))
print(Solution().oddEvenJumps([2,3,1,1,4]))
print(Solution().oddEvenJumps([5,1,3,4,2]))
print(Solution().oddEvenJumps([1,2,3,2,1,4,4,5]))