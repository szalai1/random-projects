
class Solution:
    def _neighbours(self, i: int, j: int, size: int) -> List[tuple[int, int]]:
        if i == size - 1 and j == size -1: 
            return []
        if i == size - 1:
            return [(i, j+1)]
        if j == size - 1:
            return [(i+1, j)]
        return [(i+1, j), (i, j+1)]
        
    def kthSmallest(self, matrix: List[List[int]], k: int) -> int:
        h = []
        n = len(matrix)
        c = 0
        heapq.heappush(h, (matrix[0][0], (0,0)))
        while c < k:
            c+=1
            smallest = heapq.heappop(h)
            (i, j) = smallest[1]
            for p in self._neighbours(i, j, n):
                (x, y) = p
                if matrix[x][y] is not None:
                    heapq.heappush(h, (matrix[x][y], p))
                    matrix[x][y] = None
        
        return smallest[0]
        
