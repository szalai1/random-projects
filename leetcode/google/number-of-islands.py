class Solution:
    def numIslands(self, grid: List[List[str]]) -> int:
        n, m = len(grid), len(grid[0])
        def _traverse_island(i: int, j: int) -> bool:
            if i < 0 or i>= n or j < 0 or j >= m:
                return False 
            res = grid[i][j] == "1"
            if res:
                grid[i][j] = "0"
                for x,y in [(-1,0), (0,-1), (1,0), (0,1)]:
                    _traverse_island(i+x, j+y)
            return res 
        
        result = 0
        for i in range(n):
            for j in range(m):
                if _traverse_island(i,j):
                    result += 1
        return result 
                    
                
        