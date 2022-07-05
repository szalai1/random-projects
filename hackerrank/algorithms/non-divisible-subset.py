from collections import Counter

def modulus_counter(nums, k):
    modulus = [n%k for n in nums]
    c = Counter(modulus)
    return c


def max_size(k, S):
    c = modulus_counter(S, k)
    max_size = 1 if c[0] > 0 else 0
    for i in range(1, int(k/2)+1):
        j = k-i
        if i == j:
            continue
        max_size += max(c[i], c[j])
        print(i,j, max_size )
    if k%2 == 0:
        max_size += 1 if c[0] > 0 else 0
    return max_size

print(max_size(3, [1, 7, 2, 4]))