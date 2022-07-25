def larrysArray(A):
    c = 0
    for i, v in enumerate(A):
        for j in range(i+1, len(A)):
            if v > A[j]:
                c += 1
    return 'YES' if c%2 == 0 else 'NO'