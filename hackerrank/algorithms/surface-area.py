
def getNeighbors(A, i, j ):
    res = []
    for [off_x, off_y] in [[-1, 0], [0, -1], [1, 0], [0, 1]]:
        x = i + off_x 
        y = j + off_y
        if x < 0 or x >= len(A):
            res.append(0)
            continue
        if y < 0 or y >= len(A[0]):
            res.append(0)
            continue
        res.append(A[x][y])
    return res

def surfaceArea(A):
    res = 2*len(A)*len(A[0])
    for i in range(len(A)):
        for j in range(len(A[0])):
            curr = A[i][j]
            if curr == 0:
                res -= 2
                continue
            neighbours = getNeighbors(A, i, j)
            diff = [curr - n for n in neighbours]
            positive_diff = filter(lambda x: x>0, diff)
            res += sum(positive_diff)
    return res

