def absolutePermutation(n, k):
    if k == 0:
        return list(range(1, n+1))
    if n % (2*k) != 0:
        return [-1]

    res = []
    block = list(range(k, 2*k)) + list(range(0, k))
    for b in range(0, int(n/(2*k))):
        offset = b*2*k
        res += map(lambda x: x+offset+1, block)
    return res



for test_case in [[10, 5], [10, 1], [7, 0]]:
    print(test_case, absolutePermutation(test_case[0], test_case[1]))