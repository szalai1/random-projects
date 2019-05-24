# count the number of subset of S where sum(S)=N
def  count(S: list, N: int)-> int: 
    if len(S) == 0:
        return 0
    count = N
    for i in S:
        subS = S
        subS.remove(i)
        count = f(subS, N-i)
    return minNum

for n in [1,2,3,4,5,6,7]:
    print(n, count([1,5,10, 20 , 50])


